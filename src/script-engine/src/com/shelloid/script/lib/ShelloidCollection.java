/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script.lib;

import com.shelloid.script.CompiledScript;
import com.shelloid.script.Env;
import com.shelloid.script.ScriptBin;
import com.shelloid.script.ShelloidObject;

/**
 *
 * @author Jayaraj Poroor
 */
public interface ShelloidCollection extends ShelloidObject{
    public Object $each(CompiledScript script, ScriptBin bin, Env env);
    public Long $get_count();
}
