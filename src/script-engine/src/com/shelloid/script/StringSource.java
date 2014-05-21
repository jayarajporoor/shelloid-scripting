/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jayaraj Poroor
 */
public class StringSource implements ScriptSource {
    String loc;
    String src;
    
    public StringSource(String loc, String src)
    {
        this.loc = loc;
        this.src = src;
    }
    

    @Override
    public String getLocation() {
        return loc;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(src.getBytes());
    }

    @Override
    public long getVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
