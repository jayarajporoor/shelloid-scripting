/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public static final String SHELLOID_PREFIX = "$";
    private static boolean useReflection = false;
        
    private Interpreter()
    {
    }        
    
    public static void useReflection()
    {
        useReflection = true;
    }    

    public static void useDirect()
    {
        useReflection = false;
    }    

    public static Interpreter getInstance(){
        return instance;
    }
    
    public Env execute(CompiledScript script, HashMap<String, Object> globals) throws InterpreterException
    {
        Env globalsEnv = new Env(globals);
        Env env = new Env(globalsEnv);
        execute(script, script.getSource(), env);
        return env;
    }
    
    public Object execute(CompiledScript script, ScriptSource src, Env env) throws InterpreterException
    {
        Iterator<CompiledStmt> it = script.getStmts().iterator();
        while(it.hasNext())
        {
            CompiledStmt stmt = it.next();
            executeStmt(stmt, src, env);
        }
        Object ret = env.getVarDirect("$ret");
        return ret;
    }
    
    public void executeStmt(CompiledStmt stmt, ScriptSource src, Env env) throws InterpreterException
    {
        Object result = null;
        switch(stmt.kind)
        {
            case DECL_STMT:
                if(stmt.expr != null)
                {
                    result = evalExpr(stmt.expr, src, env);
                }
                env.addVar(stmt.id, result);
                break;
            case ASSIGN_STMT:
                result = evalExpr(stmt.expr, src, env);
                if(env.getGlobals().hasVar(stmt.id))
                {
                    throw new InterpreterException(stmt.getSrcCtx(), 
                            "Attempt to assign global/predefined variable: " + stmt.id);                    
                }
                if(!env.setVar(stmt.id, result))
                {
                    throw new InterpreterException(stmt.getSrcCtx(), 
                            "Attempt to assign undefined variable: " + stmt.id);
                }
                break;
            case RET_STMT:
                result = evalExpr(stmt.expr, src, env);
                env.addVar("$ret", result);
                break;
                
            case EXPR_STMT:
                evalExpr(stmt.expr, src, env);
                break;
        }
    }
    
    public Object evalExpr(CompiledExpr expr, ScriptSource src, Env env) throws InterpreterException
    { 
        try
        {
            switch(expr.kind)
            {
                case LITERAL_EXPR:
                    return expr.value;
                case OP_EXPR:
                    Object leval = evalExpr(expr.lexpr, src, env);
                    Object reval = expr.rexpr != null ? 
                                        evalExpr(expr.rexpr, src, env) : null;
                    return evalOp((String)expr.value, leval, reval, src, env);
                case SCRIPT_EXPR:
                    return expr.value;
                case ASYNC_INDEX_EXPR:
                    int index = ((Long)expr.value).intValue();
                    return new AsyncInfo(index);
                case OBJ_EXPR_SEQ: 
                    return evalObjExprSeq(expr, src, env);
                default:
                    throw new InterpreterException(expr.getSrcCtx(), "Unknown Expression!");
            }
        }catch(InterpreterException e)
        {
            e.setSourceCtx(expr.getSrcCtx());
            throw e;
        }
    }

    static Object fieldAccessMethodType = null;
    static Class[] noParams = {};
    
    public Object invokeFieldGetter(String id, Object obj) throws Throwable
    {
        String methodName = "$get_" + id;        
        if(useReflection)
        {
            Method method = obj.getClass().getDeclaredMethod(methodName, noParams);
            return method.invoke(obj);            
        }else
        {
            if (fieldAccessMethodType == null) {
                fieldAccessMethodType = MethodType.methodType(Object.class);
            }
            MethodHandle methodHandle = MethodHandles.lookup().findVirtual(obj.getClass(), methodName,
                    (MethodType) fieldAccessMethodType);

            return methodHandle.invoke(obj);
        }
    }
    
    public Object invokeMethod(String id, Object obj, ArrayList<Class<?> > paramTypes, 
            ArrayList<Object> params) throws Throwable
    {
        String methodName =  SHELLOID_PREFIX + id;
        if(useReflection)
        {
            Method method = obj.getClass().getDeclaredMethod(methodName, paramTypes.toArray(noParams));
            return method.invoke(obj, params.toArray());            
        }else
        {
            MethodType methodType = MethodType.methodType(Object.class, paramTypes);
            MethodHandle methodHandle = MethodHandles.lookup().findVirtual(obj.getClass(), methodName, methodType);
            return methodHandle.invokeWithArguments(params);            
        }
    }
    public Object evalObjExprSeq(CompiledExpr expr, ScriptSource src, Env env) 
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
                    try
                    {
                        obj = invokeFieldGetter(id, obj);
                    }
                    catch(NoSuchMethodException e)
                    {
                        throw new InterpreterException(objExpr.getSrcCtx(), 
                                            "No such field: " + id + " : " + e.getMessage(), e);
                    }
                    catch(IllegalAccessException e)
                    {
                        throw new InterpreterException(objExpr.getSrcCtx(), 
                                                    "Can't access field: " + id + ": " + 
                                                            e.getMessage(), e);
                    }                    
                    catch(Throwable e)
                    {
                        throw new InterpreterException(objExpr.getSrcCtx(), 
                                            "Field access threw exception: " + id + ": " 
                                             + e.getMessage(), e);                    
                    }
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
                ArrayList<Class<?> > paramTypes = new ArrayList<>();
                if(!useReflection)
                {
                    params.add(obj);//first the target object for method invocation
                }
                if(objExpr.params != null)
                {
                    Iterator<CompiledExpr> paramsIt = objExpr.params.iterator();
                    while (paramsIt.hasNext()) {
                        CompiledExpr paramExpr = paramsIt.next();
                        Object param = evalExpr(paramExpr, src, env);
                        params.add(param);
                        paramTypes.add(param.getClass());
                    }
                }
                params.add(src);
                params.add(env);
                paramTypes.add(ScriptSource.class);
                paramTypes.add(Env.class);

                try{
                    return invokeMethod(id, obj, paramTypes, params);
                }catch(NoSuchMethodException e)
                {
                    throw new InterpreterException(objExpr.getSrcCtx(), 
                                              "No such method: " + id + ": " + e.getMessage(), e);
                }
                catch(IllegalAccessException e)
                {
                    throw new InterpreterException(objExpr.getSrcCtx(), 
                                                "Can't access method: " + id + ": " + 
                                                        e.getMessage(), e);
                }catch(Throwable e)
                {
                    throw new InterpreterException(objExpr.getSrcCtx(), 
                                            "Method threw exception: " + id + ": " 
                                             + e.getMessage(), e);                    
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
                            ScriptSource src, Env env) throws InterpreterException
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
                case ">": return  m > n;
                case ">=": return m >= n;
                case "<": return m < n;
                case "<=": return m <= n;
                case "==": return m.equals(n);
                case "!=": return !m.equals(n);                    
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
                case ">": return flipped? n > m : m > n;
                case ">=": return flipped? n >= m : m >= n;
                case "<": return flipped? n < m : m < n;
                case "<=": return flipped? n <= m : m <= n;
                case "==": return m.equals(n);
                case "!=": return !m.equals(n);
                default:
                     throw new InterpreterException("Illegal operation: " + op + 
                             " on " + leval + " and " + reval);
            }            

        }else
        if(leval instanceof String || reval instanceof String)
        {
            switch(op)
            {
                case "+":
                    return leval.toString() + reval.toString();
                case "==":
                     return leval.toString().equals(reval.toString());
                case "!=":
                     return !leval.toString().equals(reval.toString());   
                default:
                    throw new InterpreterException("Illegal string operation: " + op +
                             " on " + leval + " and " + reval);

            }
                
        }else
        if(leval instanceof Boolean && reval instanceof Boolean)
        {
            Boolean m = (Boolean)leval;
            Boolean n = (Boolean)reval;
            switch(op)
            {
                case "||": return m || n;
                case "&&": return m && n;
                case "==": return m.equals(n);
                case "!=": return !m.equals(n);
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
