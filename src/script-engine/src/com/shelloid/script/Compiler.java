/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;
import com.shelloid.script.parser.ShelloidLexer;
import com.shelloid.script.parser.ShelloidParser;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
/**
 *
 * @author Jayaraj Poroor
 */
public class Compiler {
    public void compile(InputStream is) throws IOException
    {
        ShelloidLexer lexer = new ShelloidLexer(new ANTLRInputStream(is));
    }
}
