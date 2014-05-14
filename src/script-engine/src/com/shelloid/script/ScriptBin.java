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
public class ScriptBin{
    private CompiledScript script;
    private ArrayList<CompiledScript> asyncs = new ArrayList<CompiledScript>();    
    
    public ScriptBin()
    {
    }    

    /**
     * @return the script
     */
    public CompiledScript getScript() {
        return script;
    }
    
    /**
     * @param script the script to set
     */
    public void setScript(CompiledScript script) {
        this.script = script;
    }

    /**
     * @return the asyncs
     */
    public ArrayList<CompiledScript> getAsyncs() {
        return asyncs;
    }

    /**
     * @param asyncs the asyncs to set
     */
    public void setAsyncs(ArrayList<CompiledScript> asyncs) {
        this.asyncs = asyncs;
    }
}
