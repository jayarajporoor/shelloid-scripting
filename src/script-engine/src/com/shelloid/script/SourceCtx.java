/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import org.antlr.v4.runtime.Token;

/**
 *
 * @author Jayaraj Poroor
 */
public class SourceCtx {
    private TokenInfo start;
    private TokenInfo end;
    
    public SourceCtx(Token start, Token end)
    {
        this.start = new TokenInfo(start);
        this.end = new TokenInfo(end);
    }

    /**
     * @return the start
     */
    public TokenInfo getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public TokenInfo getEnd() {
        return end;
    }
}
