/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author Jayaraj Poroor
 */
public interface ScriptSource extends Serializable{
    public String getLocation();
    public long   getVersion();
    public InputStream getInputStream() throws IOException;
}
