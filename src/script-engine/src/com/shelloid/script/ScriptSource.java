/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.io.Serializable;

/**
 *
 * @author Jayaraj Poroor
 */
public class ScriptSource implements Serializable {
    private long scriptId;
    private long versionId;
    public ScriptSource()
    {
        
    }    
    /**
     * @return the scriptId
     */
    public long getScriptId() {
        return scriptId;
    }

    /**
     * @param scriptId the scriptId to set
     */
    public void setScriptId(long scriptId) {
        this.scriptId = scriptId;
    }

    /**
     * @return the versionId
     */
    public long getVersionId() {
        return versionId;
    }

    /**
     * @param versionId the versionId to set
     */
    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }
}
