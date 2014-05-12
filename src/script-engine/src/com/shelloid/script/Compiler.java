/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;
import com.shelloid.script.parser.ShelloidLexer;
import com.shelloid.script.parser.ShelloidParser;
import com.shelloid.script.parser.ShelloidParser.AssignStmtContext;
import com.shelloid.script.parser.ShelloidParser.DeclStmtContext;
import com.shelloid.script.parser.ShelloidParser.ExprContext;
import com.shelloid.script.parser.ShelloidParser.ExprStmtContext;
import com.shelloid.script.parser.ShelloidParser.LiteralContext;
import com.shelloid.script.parser.ShelloidParser.MethodCallContext;
import com.shelloid.script.parser.ShelloidParser.ObjExprContext;
import com.shelloid.script.parser.ShelloidParser.ObjExprSeqContext;
import com.shelloid.script.parser.ShelloidParser.ParamListContext;
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
    
    public ScriptBin compile(ScriptSource src) throws IOException, CompilerException
    {
        InputStream is = src.getInputStream();
        ShelloidLexer lexer = new ShelloidLexer(new ANTLRInputStream(is));
        ShelloidParser parser = new ShelloidParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new CustomErrorListener());
        ScriptContext script = parser.script();
        if(errorMsgs.isEmpty())
        {
            ScriptBin bin = new ScriptBin();            
            CompiledScript cscript = translateScript(script, false, bin);
            bin.setScript(cscript);
            bin.setSrc(src);
            return bin;
        }else
        {
            throw new CompilerException(errorMsgs);
        }
    }
    
    CompiledScript translateScript(ScriptContext script, boolean isAsync, ScriptBin bin)
    {
        CompiledScript cscript = new CompiledScript(
                                        new SourceCtx(script.start, script.stop)
                                );
        Iterator<StmtContext> it = script.stmt().iterator();
        while(it.hasNext())
        {
            StmtContext stmt = it.next();
            CompiledStmt cstmt = translateStmt(stmt, bin);
            cscript.addStmt(cstmt);
        }
        return cscript;
    }
    
    CompiledStmt translateStmt(StmtContext stmt, ScriptBin bin)
    {
        CompiledStmt cstmt = new CompiledStmt( new SourceCtx(stmt.start, stmt.stop));
        AssignStmtContext assignStmt = stmt.assignStmt();
        DeclStmtContext   declStmt   = stmt.declStmt();
        ExprStmtContext   exprStmt   = stmt.exprStmt();
        ExprContext       expr = null;
        if(assignStmt != null)
        {
            cstmt.kind = CompiledStmt.StmtKind.ASSIGN_STMT;            
            cstmt.id = assignStmt.ID().getText();
            expr = assignStmt.expr();            
        }else
        if(declStmt != null)
        {
            cstmt.kind = CompiledStmt.StmtKind.DECL_STMT;            
            cstmt.id = assignStmt.ID().getText();
            expr = assignStmt.expr();
        }else
        if(exprStmt != null)
        {
            cstmt.kind = CompiledStmt.StmtKind.EXPR_STMT;
            expr = exprStmt.expr();
        }
        
        if(expr != null)
        {
            CompiledExpr cexpr = translateExpr(expr, bin);
            cstmt.expr = cexpr;
        }
        return null;
    }        
        
    CompiledExpr translateExpr(ExprContext expr, ScriptBin bin)
    {
        if(expr.objExprSeq() != null)
        {
            return translateObjExprSeq(expr.objExprSeq(), bin);
        }else
        if(expr.literal() != null)
        {
            return translateLiteral(expr.literal(), bin);
        }else
        if(expr.script() != null)
        {
            CompiledExpr cexpr = new CompiledExpr(new SourceCtx(expr.start, expr.stop));            
            boolean isAsync = (expr.ASYNC() != null);
            CompiledScript cscript = translateScript(expr.script(), isAsync, bin);
            if(isAsync)
            {
                ArrayList<CompiledScript> asyncs = bin.getAsyncs();
                int index = asyncs.size(); //must do before calling add(...)
                asyncs.add(cscript);
                cexpr.kind = CompiledExpr.ExprKind.ASYNC_INDEX_EXPR;
                cexpr.value = index;
            }else
            {
                cexpr.kind = CompiledExpr.ExprKind.SCRIPT_EXPR;
                cexpr.value = cscript;
            }
            return cexpr;
        }
        else
        {
            CompiledExpr cexpr = new CompiledExpr(new SourceCtx(expr.start, expr.stop));            
            cexpr.kind = CompiledExpr.ExprKind.OP_EXPR;
            cexpr.op = expr.op.getText();
            cexpr.lexpr = translateExpr(expr.expr(0), bin);
            if(expr.expr().size() > 1)
                cexpr.rexpr = translateExpr(expr.expr(1), bin);
            return cexpr;
        }
    }
    
    CompiledExpr translateObjExprSeq(ObjExprSeqContext objExprSeq, ScriptBin bin)
    {
        CompiledExpr cexpr = new CompiledExpr(
                                new SourceCtx(objExprSeq.start, objExprSeq.stop)
                            );
        Iterator<ObjExprContext> it = objExprSeq.objExpr().iterator();
        ArrayList<CompiledObjExpr> cObjExprs = new ArrayList<CompiledObjExpr>();
        while(it.hasNext())
        {
            ObjExprContext objExpr = it.next();
            CompiledObjExpr cobjExpr = translateObjExpr(objExpr, bin);
            cObjExprs.add(cobjExpr);
        }
        cexpr.kind = CompiledExpr.ExprKind.OBJ_EXPR_SEQ;
        cexpr.value = cObjExprs;
        
        return cexpr;
    }
    
    CompiledObjExpr translateObjExpr(ObjExprContext objExpr, ScriptBin bin)
    {
        CompiledObjExpr cobjExpr = new CompiledObjExpr(
                                        new SourceCtx(objExpr.start, objExpr.stop)
                                );
        cobjExpr.id = objExpr.ID().getText();
        MethodCallContext methodCall = objExpr.methodCall();
        if(methodCall == null)
        {
            cobjExpr.kind = CompiledObjExpr.ObjExprKind.OBJ_REF;
        }else
        {
            cobjExpr.kind = CompiledObjExpr.ObjExprKind.METHOD_CALL;
            ParamListContext paramList = methodCall.paramList();
            if(paramList != null)
            {
                ArrayList<CompiledExpr> cparams = new ArrayList<CompiledExpr>();
                Iterator<ExprContext> it = paramList.expr().iterator();
                while(it.hasNext())
                {
                    ExprContext expr = it.next();
                    cparams.add(translateExpr(expr, bin));
                }
                cobjExpr.params = cparams;
            }
        }
        return cobjExpr;
    }
    
    CompiledExpr translateLiteral(LiteralContext literal, ScriptBin bin)
    {
        CompiledExpr cexpr = new CompiledExpr(new SourceCtx(literal.start, literal.stop));
        cexpr.kind = CompiledExpr.ExprKind.LITERAL_EXPR;
        String value = null;
        try
        {
            if(literal.TRUE() != null)
                cexpr.value = new Boolean(true);
            else
            if(literal.FALSE() != null)
                cexpr.value = new Boolean(false);
            else
            if(literal.INT() != null)
            {
                value = literal.INT().getText();
                cexpr.value = Long.parseLong(value);
            }else
            if(literal.FLOAT() != null)
            {
                value = literal.FLOAT().getText();
                cexpr.value = Double.parseDouble(value);
            }else
            if(literal.STRING() != null)
            {
                value = literal.STRING().getText();
                cexpr.value = value.substring(1, value.length() - 1);//remove quotes    
            }
        }catch(NumberFormatException e)
        {
            compileError(literal.start, "Invalid number format: " + value);
        }
        return cexpr;
    }
    
    void compileError(Token token, String msg)
    {
        String errorMsg
                = "Syntax Error at: " + token.getLine() + ": " + token.getCharPositionInLine()
                + " near " + token.getText() + ", cause: " + msg;
        errorMsgs.add(errorMsg);
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
