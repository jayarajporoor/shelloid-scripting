/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;
import com.shelloid.script.parser.ShelloidLexer;
import com.shelloid.script.parser.ShelloidParser;
import com.shelloid.script.parser.ShelloidParser.ScriptContext;
import com.shelloid.script.parser.ShelloidParser.StmtContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
/**
 *
 * @author Jayaraj Poroor
 */
public class Compiler {
    ArrayList<String> errorMsgs = new ArrayList();
    
    public CompiledScript compile(ScriptSource src) throws IOException, CompilerException
    {
        InputStream is = src.getInputStream();
        ShelloidLexer lexer = new ShelloidLexer(new ANTLRInputStream(is));
        ShelloidParser parser = new ShelloidParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new CustomErrorListener());
        ScriptContext script = parser.script();
        if(errorMsgs.isEmpty())
        {
            return translateScript(script, src);
        }else
        {
            throw new CompilerException(errorMsgs);
        }
    }
    
    CompiledScript translateScript(ScriptContext script, ScriptSource src)
    {
        CompiledScript cscript = new CompiledScript(src);
        Iterator<StmtContext> it = script.stmt().iterator();
        while(it.hasNext())
        {
            StmtContext stmt = it.next();
            CompiledStmt cstmt = translateStmt(stmt);
            cscript.addStmt(cstmt);
        }
        return cscript;
    }
    
    CompiledStmt translateStmt(StmtContext stmt)
    {
        return null;
    }
    
    class CustomErrorListener extends BaseErrorListener
    {
        public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, 
                                int line, int charPositionInLine, String msg, 
                                RecognitionException e)
        {
            String tokenText = (offendingSymbol instanceof Token) ? 
                               ((Token) offendingSymbol).getText() : "<unknown token>";
            String errorMsg = 
                           "Syntax Error at: " + line + ": " + charPositionInLine+
                           " near " + tokenText;
            errorMsgs.add(errorMsg);
        }
    }
}
