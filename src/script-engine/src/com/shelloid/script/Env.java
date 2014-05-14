/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.util.HashMap;

/**
 *
 * @author Jayaraj Poroor
 */
public class Env {
    HashMap<String, Object> vars = new HashMap<String, Object>();
    Env parent;
    Env globals;
    boolean isAsync;
    
    public Env(Env parent, boolean isAsync)
    {
        this.parent = parent;
        this.isAsync = isAsync;
        this.globals = parent.globals;
    }

    public Env(HashMap<String, Object> globals)
    {
        this.vars = globals;
        this.parent = null;
        this.isAsync = false;
        this.globals = this;
    }
    
    public Env getGlobals()
    {
        return globals;
    }
    
    public boolean hasVar(String var)
    {
        return this.vars.containsKey(var);
    }

    public Object getVar(String var)
    {
        Object val = vars.get(var);
        if(val != null)
        {
            return val;
        }
        else
        {
            if(isAsync || parent == null)
                return null;
            else
                return parent.getVar(var);
        }
    }

    public void addVar(String var, Object value)
    {
        vars.put(var, value);
    }

    public boolean setVar(String var, Object value)
    {
        if(vars.containsKey(var))
        {
            vars.put(var, value);
            return true;
        }
        else
        {
            if(isAsync || parent == null)
                return false;
            else
                return parent.setVar(var, value);
        }
    }    
}
