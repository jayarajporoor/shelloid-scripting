/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Jayaraj Poroor
 */
public class Interpreter {
    
    static Interpreter instance = new Interpreter();
    static final String implicit = "$implicit";
    
    public Interpreter()
    {
    }
    
    public static Interpreter getInstance(){
        return instance;
    }
    
    public Env execute(ScriptBin bin, HashMap<String, Object> globals) throws InterpreterException
    {
        Env env = new Env(globals);
        CompiledScript script = bin.getScript();
        executeScript(script, bin, env);
        return env;
    }
    
    public void executeScript(CompiledScript script, ScriptBin bin, Env env) throws InterpreterException
    {
        Iterator<CompiledStmt> it = script.getStmts().iterator();
        while(it.hasNext())
        {
            CompiledStmt stmt = it.next();
            executeStmt(stmt, bin, env);
        }
    }
    
    public void executeStmt(CompiledStmt stmt, ScriptBin bin, Env env) throws InterpreterException
    {
        Object result = null;
        switch(stmt.kind)
        {
            case DECL_STMT:
                if(stmt.expr != null)
                {
                    result = evalExpr(stmt.expr, bin, env);
                }
                env.addVar(stmt.id, result);
                break;
            case ASSIGN_STMT:
                result = evalExpr(stmt.expr, bin, env);
                if(!env.setVar(stmt.id, result))
                {
                    throw new InterpreterException(stmt.getSrcCtx(), 
                            "Attempt to assign undefined variable: " + stmt.id);
                }
                break;
            case EXPR_STMT:
                evalExpr(stmt.expr, bin, env);
                break;
        }
    }
    
    public Object evalExpr(CompiledExpr expr, ScriptBin bin, Env env) throws InterpreterException
    { 
        try
        {
            switch(expr.kind)
            {
                case LITERAL_EXPR:
                    return expr.value;
                case OP_EXPR:
                    Object leval = evalExpr(expr.lexpr, bin, env);
                    Object reval = expr.rexpr != null ? 
                                        evalExpr(expr.rexpr, bin, env) : null;
                    return evalOp((String)expr.value, leval, reval, bin, env);
                case SCRIPT_EXPR:
                    return expr.value;
                case ASYNC_INDEX_EXPR:
                    int index = ((Long)expr.value).intValue();                    
                    return bin.getAsyncs().get(index);
                case OBJ_EXPR_SEQ: 
                    return evalObjExprSeq(expr, bin, env);
                default:
                    throw new InterpreterException(expr.getSrcCtx(), "Unknown Expression!");
            }
        }catch(InterpreterException e)
        {
            e.setSourceCtx(expr.getSrcCtx());
            throw e;
        }
    }
    
    static final MethodType scriptableMethodType = MethodType.methodType(
                                            Object.class, 
                                            String.class,
                                            ArrayList.class,
                                            ArrayList.class, 
                                            ScriptBin.class, 
                                            Env.class);
    static MethodHandle scriptableMethod = null;
    
    public Object evalObjExprSeq(CompiledExpr expr, ScriptBin bin, Env env) 
                                                    throws InterpreterException
    {
        ArrayList<CompiledObjExpr> objExprs
                = (ArrayList<CompiledObjExpr>) expr.value;
        Iterator<CompiledObjExpr> it = objExprs.iterator();
        boolean isRootVar = true;
        Object obj = null;
        while (it.hasNext()) {
            CompiledObjExpr objExpr = it.next();
            String id = (String) objExpr.id;
            if (isRootVar) {
                if (objExpr.kind == CompiledObjExpr.ObjExprKind.METHOD_CALL) {
                    obj = env.getVar(implicit);
                    if (obj == null) {
                        throw new InterpreterException(objExpr.getSrcCtx(),
                                "Couldn't obtain implicit object: " + implicit);
                    }                    
                } else {
                    obj = env.getVar(id);
                    if (obj == null) {
                        throw new InterpreterException(objExpr.getSrcCtx(),
                                "Undefined variable: " + objExpr.id);
                    }
                }
            } else {
                if (!(obj instanceof ShelloidObject)) {
                    throw new InterpreterException(objExpr.getSrcCtx(),
                            "Attempt to access field of a non-shelloid object: "
                            + id);
                }
                if(objExpr.kind == CompiledObjExpr.ObjExprKind.OBJ_REF)
                {
                    obj = ((ShelloidObject)obj).getField(id);
                }
            }
            if (objExpr.kind == CompiledObjExpr.ObjExprKind.METHOD_CALL) {
                if(!(obj instanceof ShelloidObject))
                {
                    throw new InterpreterException(objExpr.getSrcCtx(),
                            "Attempt to invoke method of a non-shelloid object: "
                            + id + "(...)");                    
                }
                ArrayList<Object> params = new ArrayList<>();
                Iterator<CompiledExpr> paramsIt = objExpr.params.iterator();
                while (paramsIt.hasNext()) {
                    CompiledExpr paramExpr = paramsIt.next();
                    Object param = evalExpr(paramExpr, bin, env);
                    params.add(param);
                }
                try{
                    if(scriptableMethod == null)//taken once and cached as static.
                    {
                        scriptableMethod = MethodHandles.lookup().findVirtual(
                            ShelloidObject.class, "invokeMethod", scriptableMethodType);
                    }
                    return scriptableMethod.invokeExact(id, params, bin, env);
                }catch(NoSuchMethodException e)
                {
                    throw new InterpreterException(objExpr.getSrcCtx(), 
                                                    "No such method: " + id, e);
                }
                catch(IllegalAccessException e)
                {
                    throw new InterpreterException(objExpr.getSrcCtx(), 
                                                "Can't access method: " + id, e);
                }catch(Throwable e)
                {
                    throw new InterpreterException(objExpr.getSrcCtx(), 
                                            "Method threw exception: " + id, e);                    
                }
            }
            
            isRootVar = false;
        }
        
        if(obj == null)
        {
            throw new InterpreterException(expr.getSrcCtx(),
                    "The object expression sequence evaluated to null");            
        }
        return obj;
    }
    
    public Object evalOp(String op, Object leval, Object reval, 
                            ScriptBin bin, Env env) throws InterpreterException
    {
        if(leval instanceof Long && reval instanceof Long)
        {
            Long m = (Long)leval;
            Long n = (Long) reval;
            switch(op)
            {
                case "+": return m + n;
                case "-": return m - n;
                case "*": return m * n;
                case "/": return m / n;
                case "%": return m % n;
                default:
                     throw new InterpreterException("Illegal operation: " + op + 
                             " on " + leval + " and " + reval);
            }
        }else
        if(leval instanceof Number && reval instanceof Number)
        {
            Double m = null;
            Long n = null;
            boolean flipped = false;
            if(leval instanceof Double)
            {
                m = (Double) leval;
                n = (Long) reval;
            }else
            {
                m = (Double) reval;
                n = (Long) leval;
                flipped = true;
            }
            switch(op)
            {
                case "+": return m + n;
                case "-": return flipped ? n - m : m - n;
                case "*": return m * n;
                case "/": return flipped? n /m : m / n;
                default:
                     throw new InterpreterException("Illegal operation: " + op + 
                             " on " + leval + " and " + reval);
            }            

        }else
        if(leval instanceof String || reval instanceof String)
        {
            if(op.equals("+"))
                return leval.toString() + reval.toString();
            else
                throw new InterpreterException("Illegal string operation: " + op +
                             " on " + leval + " and " + reval);
                
        }else
        if(leval instanceof Boolean && reval instanceof Boolean)
        {
            Boolean m = (Boolean)leval;
            Boolean n = (Boolean)reval;
            switch(op)
            {
                case "||": return m || n;
                case "&&": return m && n;
                default:
                     throw new InterpreterException("Illegal boolean operation: " + op + 
                             " on " + leval + " and " + reval);
            }                        
        }else
        if(leval instanceof Boolean && reval == null)
        {
            if(op.equals("!"))
            {
                Boolean m = (Boolean)leval;
                return !m;
            }else
                throw new InterpreterException("Illegal unary boolean operation: " + op + 
                           " on " + leval);
                
        }else
            throw new InterpreterException("Illegal operation: " + op + 
                             " on " + leval + 
                            (reval == null ? "" : " and " + reval)
            );            
    }
}
