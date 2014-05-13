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
    
    public void execute(ScriptBin bin, HashMap<String, Object> globals)
    {
        Env env = new Env(globals);
        CompiledScript script = bin.getScript();
        executeScript(script, env);
    }
    
    public void executeScript(CompiledScript script, Env env)
    {
        Iterator<CompiledStmt> it = script.getStmts().iterator();
        while(it.hasNext())
        {
            CompiledStmt stmt = it.next();
            executeStmt(stmt, env);
        }
    }
    
    public void executeStmt(CompiledStmt stmt, Env env)
    {
        switch(stmt.kind)
        {
            case DECL_STMT:
                Object result = null;
                if(stmt.expr != null)
                    result = evaluateExpr(stmt.expr, env);
                env.addVar(stmt.id, result);
                break;
            case ASSIGN_STMT:
                break;
            case EXPR_STMT:
                break;
        }
    }
    
    public Object evaluateExpr(CompiledExpr expr, Env env)
    {
        return null;
    }
}
