/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.util.ArrayList;

/**
 *
 * @author Jayaraj Poroor
 */
public interface ShelloidObject {
    public Object getField(String id) throws Exception;
    public Object invokeMethod(String name, ArrayList<Object> params, ScriptBin bin, Env env) throws Exception;
}