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
    private ScriptSource src;//stored only for async scripts and top-level script.
    long asyncIndex;
    
    public CompiledScript(SourceCtx srcCtx)
    {
        super(srcCtx);
        asyncIndex = -1;
    }
        
    public boolean isAsync()
    {
        return asyncIndex >= 0;
    }
    
    void setAsyncInfo(long asyncIndex, ScriptSource src) {
        assert(asyncIndex >= 0 ? (src != null) : true);
        this.asyncIndex = asyncIndex;
        this.setSrc(src);
    }    
    
    public long getAsyncIndex()
    {
        return asyncIndex;
    }
    
    public ScriptSource getSource()
    {
        return this.src;
    }
    
    public void addStmt(CompiledStmt stmt)
    {
        stmts.add(stmt);
    }
    
    ArrayList<CompiledStmt> getStmts()
    {
        return stmts;
    }

    /**
     * @param src the src to set
     */
    public void setSrc(ScriptSource src) {
        this.src = src;
    }
}
