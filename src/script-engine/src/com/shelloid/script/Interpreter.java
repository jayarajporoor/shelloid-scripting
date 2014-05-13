/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Jayaraj Poroor
 */
public class Interpreter {
    public Interpreter()
    {
    }
    
    public void execute(ScriptBin bin, HashMap<String, Object> globals) throws InterpreterException
    {
        Env env = new Env(globals);
        CompiledScript script = bin.getScript();
        executeScript(script, bin, env);
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
        switch(stmt.kind)
        {
            case DECL_STMT:
                Object result = null;
                if(stmt.expr != null)
                    result = evalExpr(stmt.expr, env);
                env.addVar(stmt.id, result);
                break;
            case ASSIGN_STMT:
                break;
            case EXPR_STMT:
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
                    return null;//TODO
            }
        }catch(InterpreterException e)
        {
            e.setSourceCtx(expr.getSrcCtx());
            throw e;
        }
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
