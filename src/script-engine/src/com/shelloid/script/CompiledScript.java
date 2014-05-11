/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.util.ArrayList;

/**
 *
 * @author Jayaraj Poroor
 */
public class CompiledScript {
    ArrayList<CompiledStmt> stmts = new ArrayList<CompiledStmt>();
    ScriptSource src;
    public CompiledScript(ScriptSource src)
    {
        this.src = src;
    }
    
    public void addStmt(CompiledStmt stmt)
    {
        stmts.add(stmt);
    }
    
    ArrayList<CompiledStmt> getStmts()
    {
        return stmts;
    }
}
