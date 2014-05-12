/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.io.Serializable;

/**
 *
 * @author Jayaraj Poroor
 */
public class CompiledObject implements Serializable{
    SourceCtx srcCtx;
    public CompiledObject(SourceCtx srcCtx)
    {
        this.srcCtx = srcCtx;
    }
    
    SourceCtx getSrcCtx()
    {
        return srcCtx;
    }
}
