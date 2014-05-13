/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

/**
 *
 * @author Jayaraj Poroor
 */
public class InterpreterException extends Exception{
    SourceCtx srcCtx;
    public InterpreterException(SourceCtx srcCtx, String msg)
    {
        super(msg);
        this.srcCtx = srcCtx;
    }

    public InterpreterException(SourceCtx srcCtx, String msg, Throwable cause)
    {
        super(msg, cause);
        this.srcCtx = srcCtx;
    }
    
    public InterpreterException(String msg)
    {
        super(msg);
    }
    
    public SourceCtx getSourceCtx()
    {
        return srcCtx;
    }
    
    public void setSourceCtx(SourceCtx srcCtx)
    {
        this.srcCtx = srcCtx;
    }
}
