/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.io.Serializable;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Jayaraj Poroor
 */
public class TokenInfo implements Serializable {
    private int line;
    private int charPos;
    private String text;
    
    public TokenInfo(Token token)
    {
        line = token.getLine();
        charPos = token.getCharPositionInLine();
        text = token.getText();
    }

    /**
     * @return the line
     */
    public int getLine() {
        return line;
    }

    /**
     * @return the charPos
     */
    public int getCharPos() {
        return charPos;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }
    
    public String toString()
    {
        return "Line : " + line + ", char: " + charPos + " near " + text;
    }
}
