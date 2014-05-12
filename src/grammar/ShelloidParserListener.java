// Generated from ShelloidParser.g4 by ANTLR 4.1
package com.shelloid.script.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShelloidParser}.
 */
public interface ShelloidParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShelloidParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(@NotNull ShelloidParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(@NotNull ShelloidParser.AssignStmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(@NotNull ShelloidParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(@NotNull ShelloidParser.ExprStmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(@NotNull ShelloidParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(@NotNull ShelloidParser.MethodCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(@NotNull ShelloidParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(@NotNull ShelloidParser.ParamListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#objExpr}.
	 * @param ctx the parse tree
	 */
	void enterObjExpr(@NotNull ShelloidParser.ObjExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#objExpr}.
	 * @param ctx the parse tree
	 */
	void exitObjExpr(@NotNull ShelloidParser.ObjExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull ShelloidParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull ShelloidParser.StmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(@NotNull ShelloidParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(@NotNull ShelloidParser.ReturnStmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull ShelloidParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull ShelloidParser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#declStmt}.
	 * @param ctx the parse tree
	 */
	void enterDeclStmt(@NotNull ShelloidParser.DeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#declStmt}.
	 * @param ctx the parse tree
	 */
	void exitDeclStmt(@NotNull ShelloidParser.DeclStmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(@NotNull ShelloidParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(@NotNull ShelloidParser.ScriptContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#objExprSeq}.
	 * @param ctx the parse tree
	 */
	void enterObjExprSeq(@NotNull ShelloidParser.ObjExprSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#objExprSeq}.
	 * @param ctx the parse tree
	 */
	void exitObjExprSeq(@NotNull ShelloidParser.ObjExprSeqContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelloidParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull ShelloidParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelloidParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull ShelloidParser.LiteralContext ctx);
}