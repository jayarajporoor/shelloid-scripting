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
    boolean isAsync;
    
    public Env(Env parent, boolean isAsync)
    {
        this.parent = parent;
        this.isAsync = isAsync;
    }

    public Env(HashMap<String, Object> globals)
    {
        this.vars = globals;
        this.parent = null;
        isAsync = false;
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
            if(isAsync)
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
            if(isAsync)
                return false;
            else
                return parent.setVar(var, value);
        }
    }    
}
