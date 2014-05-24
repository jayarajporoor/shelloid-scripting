/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script.lib;

import com.shelloid.script.CompiledScript;
import com.shelloid.script.Env;
import com.shelloid.script.Interpreter;
import com.shelloid.script.InterpreterException;
import com.shelloid.script.ScriptBin;
import com.shelloid.script.ScriptSource;
import com.shelloid.script.ShelloidObject;
import java.util.ArrayList;

/**
 *
 * @author Jayaraj Poroor
 */
public class ImplicitObject implements ShelloidObject{
    public Object $if(Boolean cond, CompiledScript ifScript, CompiledScript elseScript,
            ScriptSource src, Env env) throws InterpreterException
    {
        CompiledScript script = cond ? ifScript : elseScript;
        Env newEnv = new Env(env);
        Interpreter.getInstance().execute(script, src, newEnv);
        return null;
    }   

    public Object $exec(CompiledScript script, ScriptSource src, Env env) throws InterpreterException
    {
        Env newEnv = new Env(env);
        return Interpreter.getInstance().execute(script, src, newEnv);
    }   
    
}
