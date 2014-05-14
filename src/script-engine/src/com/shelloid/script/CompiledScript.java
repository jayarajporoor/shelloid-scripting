/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jayaraj Poroor
 */
public class CompiledScript extends CompiledObject{
    ArrayList<CompiledStmt> stmts = new ArrayList<CompiledStmt>();
    boolean isAsync;
    transient ArrayList<CompiledScript> asyncs = new ArrayList<CompiledScript>();
    
    public CompiledScript(SourceCtx srcCtx)
    {
        this(srcCtx, false);
    }
    
    public CompiledScript(SourceCtx srcCtx, boolean isAsync)
    {
        super(srcCtx);
        this.isAsync = isAsync;        
    }
    
    public boolean isAsync()
    {
        return isAsync;
    }
    
    void setAsync(boolean isAsync) {
        this.isAsync = isAsync;
    }    
    
    public void addStmt(CompiledStmt stmt)
    {
        stmts.add(stmt);
    }
    
    ArrayList<CompiledStmt> getStmts()
    {
        return stmts;
    }
    
    ArrayList<CompiledScript> getAsyncs()
    {
        return this.asyncs;
    }

    public void setAsyncs(ArrayList<CompiledScript> asyncs)
    {
        this.asyncs = asyncs;
    }
}
