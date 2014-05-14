/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jayaraj Poroor
 */
public class CompileCtx {
    ScriptBin bin;
    boolean isAsync;
    static final HashSet<String> builtins = new HashSet<>();
    static
    {
        builtins.add("args");
        builtins.add("event");
    }
    HashSet<String> vars = new HashSet<>();
    HashMap<String, Object> globals;
    CompileCtx parentCtx;
    CompileCtx(ScriptBin bin, boolean isAsync, HashMap<String, Object> globals, 
                            CompileCtx parentCtx)
    {
        this.bin = bin;
        this.isAsync = isAsync;
        this.globals = globals;
        this.parentCtx = parentCtx;
    }
    
    public void addVar(String var)
    {        
        vars.add(var);
    }
    
    public boolean hasVar(String var)
    {
        return vars.contains(var);
    }
    
    public boolean isGlobal(String var)
    {
        return globals.containsKey(var);
    }
    
    public boolean isBuiltin(String var)
    {
        return builtins.contains(var);
    }
    
    public boolean varIsDefined(String var)
    {
        boolean isDefined = vars.contains(var);
        if(isDefined)
        {
            return true;
        }
        else
        {
            if(isAsync || parentCtx == null)
                return false;
            else
                return parentCtx.varIsDefined(var);
        }
    }
}
