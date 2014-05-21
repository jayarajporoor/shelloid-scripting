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
public class AsyncInfo implements Serializable {
    private ScriptSource src;
    private int          index;
    
    public AsyncInfo(ScriptSource src, int index)
    {
        this.src = src;
        this.index = index;
    }

    /**
     * @return the src
     */
    public ScriptSource getSrc() {
        return src;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }
}
