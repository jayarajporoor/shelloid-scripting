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
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
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
    
    public ScriptBin compile(ScriptSource src, HashMap<String, Object> globals) 
                                        throws IOException, CompilerException
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
            CompileCtx ctx = new CompileCtx(bin, false, globals, null);
            CompiledScript cscript = translateScript(script, ctx);
            bin.setScript(cscript);
            bin.setSrc(src);
            return bin;
        }else
        {
            throw new CompilerException(errorMsgs);
        }
    }
    
    CompiledScript translateScript(ScriptContext script, CompileCtx ctx)
    {
        CompiledScript cscript = new CompiledScript(
                                        new SourceCtx(script.start, script.stop)
                                );
        Iterator<StmtContext> it = script.stmt().iterator();
        while(it.hasNext())
        {
            StmtContext stmt = it.next();
            CompiledStmt cstmt = translateStmt(stmt, ctx);
            cscript.addStmt(cstmt);
        }
        return cscript;
    }
    
    CompiledStmt translateStmt(StmtContext stmt, CompileCtx ctx)
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
            if(!ctx.varIsDefined(cstmt.id) && !ctx.isGlobal(cstmt.id) && 
                    !ctx.isBuiltin(cstmt.id))
            {
                compileError(assignStmt.ID().getSymbol(), "Undefined variable.");
            }
        }else
        if(declStmt != null)
        {
            cstmt.kind = CompiledStmt.StmtKind.DECL_STMT;            
            cstmt.id = assignStmt.ID().getText();
            expr = assignStmt.expr();//may be null
            if(ctx.isGlobal(cstmt.id) || ctx.isBuiltin(cstmt.id))
            {
                compileError(assignStmt.ID().getSymbol(), "Attempt to redefine global/builtin variable");
            }else
            if(ctx.hasVar(cstmt.id))
            {
                compileError(assignStmt.ID().getSymbol(), "Variable is already defined.");
            }else
            {
                ctx.addVar(cstmt.id);  
            }
        }else
        if(exprStmt != null)
        {
            cstmt.kind = CompiledStmt.StmtKind.EXPR_STMT;
            expr = exprStmt.expr();
        }
        
        if(expr != null)
        {
            CompiledExpr cexpr = translateExpr(expr, ctx);
            cstmt.expr = cexpr;
        }
        return null;
    }        
        
    CompiledExpr translateExpr(ExprContext expr, CompileCtx ctx)
    {
        if(expr.objExprSeq() != null)
        {
            return translateObjExprSeq(expr.objExprSeq(), ctx);
        }else
        if(expr.literal() != null)
        {
            return translateLiteral(expr.literal(), ctx);
        }else
        if(expr.script() != null)
        {
            CompiledExpr cexpr = new CompiledExpr(new SourceCtx(expr.start, expr.stop));            
            boolean isAsync = (expr.ASYNC() != null);
            CompileCtx newCtx = new CompileCtx(ctx.bin, isAsync, ctx.globals, ctx);
            CompiledScript cscript = translateScript(expr.script(), newCtx);
            if(isAsync)
            {
                ArrayList<CompiledScript> asyncs = ctx.bin.getAsyncs();
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
            cexpr.value = expr.op.getText();
            cexpr.lexpr = translateExpr(expr.expr(0), ctx);
            if(expr.expr().size() > 1)
                cexpr.rexpr = translateExpr(expr.expr(1), ctx);
            return cexpr;
        }
    }
    
    CompiledExpr translateObjExprSeq(ObjExprSeqContext objExprSeq, CompileCtx ctx)
    {
        CompiledExpr cexpr = new CompiledExpr(
                                new SourceCtx(objExprSeq.start, objExprSeq.stop)
                            );
        Iterator<ObjExprContext> it = objExprSeq.objExpr().iterator();
        ArrayList<CompiledObjExpr> cObjExprs = new ArrayList<CompiledObjExpr>();
        boolean rootVar = true;
        while(it.hasNext())
        {
            ObjExprContext objExpr = it.next();
            CompiledObjExpr cobjExpr = translateObjExpr(objExpr, ctx);
            cObjExprs.add(cobjExpr);
            if(rootVar && cobjExpr.kind == CompiledObjExpr.ObjExprKind.OBJ_REF)
            {
                if(!ctx.varIsDefined(cobjExpr.id) && !ctx.isGlobal(cobjExpr.id)
                        && !ctx.isBuiltin(cobjExpr.id))
                {
                    compileError(objExpr.ID().getSymbol(), "Undefined variable.");
                }                         
            }
            rootVar = false;
        }
        cexpr.kind = CompiledExpr.ExprKind.OBJ_EXPR_SEQ;
        cexpr.value = cObjExprs;
        
        return cexpr;
    }
    
    CompiledObjExpr translateObjExpr(ObjExprContext objExpr, CompileCtx ctx)
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
                boolean rootVar = true;
                while(it.hasNext())
                {
                    ExprContext expr = it.next();
                    cparams.add(translateExpr(expr, ctx));
                }
                cobjExpr.params = cparams;
            }
        }
        return cobjExpr;
    }
    
    CompiledExpr translateLiteral(LiteralContext literal, CompileCtx ctx)
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
