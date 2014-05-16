/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script.lib;

import com.shelloid.script.CompiledScript;
import com.shelloid.script.Env;
import com.shelloid.script.Interpreter;
import com.shelloid.script.ScriptBin;
import com.shelloid.script.ShelloidObject;
import java.util.ArrayList;

/**
 *
 * @author Jayaraj Poroor
 */
public class ImplicitObject implements ShelloidObject {
    public static final Class[] ifParamTypes = {Boolean.class, CompiledScript.class, CompiledScript.class};
    @Override
    public Object getField(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object invokeMethod(String name, ArrayList<Object> params, ScriptBin bin, Env env) 
                    throws Exception {
        switch(name)
        {
            case "if":
                ParamChecker.checkParams("if", params, ifParamTypes);
                Boolean cond = (Boolean) params.get(0);
                CompiledScript ifScript = (CompiledScript) params.get(1);
                CompiledScript elseScript = (CompiledScript) params.get(2);
                CompiledScript script = cond ? ifScript : elseScript;
                Env newEnv = new Env(env);
                Interpreter.getInstance().executeScript(script, bin, newEnv);
                return null;
            default:
                throw new UnsupportedOperationException("Not supported yet: " + name);
        }
    }
    
}
