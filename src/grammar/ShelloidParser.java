// Generated from E:\shelloid\shelloid-script-engine\src\grammar\ShelloidParser.g4 by ANTLR 4.1
package com.shelloid.script.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ShelloidParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LT=21, RC=2, RP=4, LETTER=33, MOD=14, GTE=23, LP=3, FLOAT=9, INT=7, EQUALS=17, 
		NOT=20, MINUS=16, ID=28, AND=18, SEMI=32, TRUE=10, MUL=13, LTE=24, WS=29, 
		NEQ=25, COMMA=31, OR=19, RETURN=27, ASSIGN=6, LC=1, GT=22, PLUS=15, VAR=5, 
		DIGIT=34, DIV=12, COMMENT=30, DOT=26, FALSE=11, STRING=8;
	public static final String[] tokenNames = {
		"<INVALID>", "'{'", "'}'", "'('", "')'", "'var'", "'='", "INT", "STRING", 
		"FLOAT", "'true'", "'false'", "'/'", "'*'", "'%'", "'+'", "'-'", "'=='", 
		"'&&'", "'||'", "'!'", "'<'", "'>'", "'>='", "'<='", "'!='", "'.'", "'return'", 
		"ID", "WS", "COMMENT", "','", "';'", "LETTER", "DIGIT"
	};
	public static final int
		RULE_script = 0, RULE_stmt = 1, RULE_declStmt = 2, RULE_assignStmt = 3, 
		RULE_exprStmt = 4, RULE_returnStmt = 5, RULE_expr = 6, RULE_objExpr = 7, 
		RULE_objExprSeq = 8, RULE_methodCall = 9, RULE_paramList = 10, RULE_literal = 11;
	public static final String[] ruleNames = {
		"script", "stmt", "declStmt", "assignStmt", "exprStmt", "returnStmt", 
		"expr", "objExpr", "objExprSeq", "methodCall", "paramList", "literal"
	};

	@Override
	public String getGrammarFileName() { return "ShelloidParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public ShelloidParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ScriptContext extends ParserRuleContext {
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(ShelloidParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(ShelloidParser.SEMI, i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitScript(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24); stmt();
				setState(25); match(SEMI);
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LC) | (1L << LP) | (1L << VAR) | (1L << INT) | (1L << STRING) | (1L << FLOAT) | (1L << TRUE) | (1L << FALSE) | (1L << MINUS) | (1L << NOT) | (1L << RETURN) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public DeclStmtContext declStmt() {
			return getRuleContext(DeclStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public AssignStmtContext assignStmt() {
			return getRuleContext(AssignStmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		try {
			setState(35);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(31); declStmt();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(32); assignStmt();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(33); exprStmt();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(34); returnStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclStmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ShelloidParser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(ShelloidParser.ID, 0); }
		public TerminalNode VAR() { return getToken(ShelloidParser.VAR, 0); }
		public DeclStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterDeclStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitDeclStmt(this);
		}
	}

	public final DeclStmtContext declStmt() throws RecognitionException {
		DeclStmtContext _localctx = new DeclStmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); match(VAR);
			setState(38); match(ID);
			setState(41);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(39); match(ASSIGN);
				setState(40); expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignStmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ShelloidParser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(ShelloidParser.ID, 0); }
		public AssignStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterAssignStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitAssignStmt(this);
		}
	}

	public final AssignStmtContext assignStmt() throws RecognitionException {
		AssignStmtContext _localctx = new AssignStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assignStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); match(ID);
			setState(44); match(ASSIGN);
			setState(45); expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprStmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitExprStmt(this);
		}
	}

	public final ExprStmtContext exprStmt() throws RecognitionException {
		ExprStmtContext _localctx = new ExprStmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_exprStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(ShelloidParser.RETURN, 0); }
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitReturnStmt(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); match(RETURN);
			setState(50); expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public int _p;
		public Token op;
		public TerminalNode GTE() { return getToken(ShelloidParser.GTE, 0); }
		public TerminalNode MUL() { return getToken(ShelloidParser.MUL, 0); }
		public TerminalNode EQUALS() { return getToken(ShelloidParser.EQUALS, 0); }
		public TerminalNode AND() { return getToken(ShelloidParser.AND, 0); }
		public TerminalNode LP() { return getToken(ShelloidParser.LP, 0); }
		public TerminalNode OR() { return getToken(ShelloidParser.OR, 0); }
		public TerminalNode LTE() { return getToken(ShelloidParser.LTE, 0); }
		public TerminalNode MINUS() { return getToken(ShelloidParser.MINUS, 0); }
		public TerminalNode RP() { return getToken(ShelloidParser.RP, 0); }
		public ObjExprSeqContext objExprSeq() {
			return getRuleContext(ObjExprSeqContext.class,0);
		}
		public ScriptContext script() {
			return getRuleContext(ScriptContext.class,0);
		}
		public TerminalNode NEQ() { return getToken(ShelloidParser.NEQ, 0); }
		public TerminalNode DIV() { return getToken(ShelloidParser.DIV, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode LT() { return getToken(ShelloidParser.LT, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode NOT() { return getToken(ShelloidParser.NOT, 0); }
		public TerminalNode PLUS() { return getToken(ShelloidParser.PLUS, 0); }
		public TerminalNode GT() { return getToken(ShelloidParser.GT, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode RC() { return getToken(ShelloidParser.RC, 0); }
		public TerminalNode MOD() { return getToken(ShelloidParser.MOD, 0); }
		public TerminalNode LC() { return getToken(ShelloidParser.LC, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, RULE_expr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			switch (_input.LA(1)) {
			case NOT:
				{
				setState(53); ((ExprContext)_localctx).op = match(NOT);
				setState(54); expr(16);
				}
				break;
			case MINUS:
				{
				setState(55); ((ExprContext)_localctx).op = match(MINUS);
				setState(56); expr(15);
				}
				break;
			case ID:
				{
				setState(57); objExprSeq();
				}
				break;
			case LP:
				{
				setState(58); match(LP);
				setState(59); expr(0);
				setState(60); match(RP);
				}
				break;
			case INT:
			case STRING:
			case FLOAT:
			case TRUE:
			case FALSE:
				{
				setState(62); literal();
				}
				break;
			case LC:
				{
				setState(63); match(LC);
				setState(64); script();
				setState(65); match(RC);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(110);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(108);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(69);
						if (!(14 >= _localctx._p)) throw new FailedPredicateException(this, "14 >= $_p");
						setState(70); ((ExprContext)_localctx).op = match(MUL);
						setState(71); expr(15);
						}
						break;

					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(72);
						if (!(13 >= _localctx._p)) throw new FailedPredicateException(this, "13 >= $_p");
						setState(73); ((ExprContext)_localctx).op = match(DIV);
						setState(74); expr(14);
						}
						break;

					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(75);
						if (!(12 >= _localctx._p)) throw new FailedPredicateException(this, "12 >= $_p");
						setState(76); ((ExprContext)_localctx).op = match(MOD);
						setState(77); expr(13);
						}
						break;

					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(78);
						if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "11 >= $_p");
						setState(79); ((ExprContext)_localctx).op = match(PLUS);
						setState(80); expr(12);
						}
						break;

					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(81);
						if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "10 >= $_p");
						setState(82); ((ExprContext)_localctx).op = match(MINUS);
						setState(83); expr(11);
						}
						break;

					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(84);
						if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
						setState(85); ((ExprContext)_localctx).op = match(EQUALS);
						setState(86); expr(10);
						}
						break;

					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(87);
						if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "8 >= $_p");
						setState(88); ((ExprContext)_localctx).op = match(LT);
						setState(89); expr(9);
						}
						break;

					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(90);
						if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "7 >= $_p");
						setState(91); ((ExprContext)_localctx).op = match(GT);
						setState(92); expr(8);
						}
						break;

					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(93);
						if (!(6 >= _localctx._p)) throw new FailedPredicateException(this, "6 >= $_p");
						setState(94); ((ExprContext)_localctx).op = match(LTE);
						setState(95); expr(7);
						}
						break;

					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(96);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(97); ((ExprContext)_localctx).op = match(GTE);
						setState(98); expr(6);
						}
						break;

					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(99);
						if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
						setState(100); ((ExprContext)_localctx).op = match(NEQ);
						setState(101); expr(5);
						}
						break;

					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(102);
						if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
						setState(103); ((ExprContext)_localctx).op = match(AND);
						setState(104); expr(4);
						}
						break;

					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(105);
						if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
						setState(106); ((ExprContext)_localctx).op = match(OR);
						setState(107); expr(3);
						}
						break;
					}
					} 
				}
				setState(112);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ObjExprContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ShelloidParser.ID, 0); }
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public ObjExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterObjExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitObjExpr(this);
		}
	}

	public final ObjExprContext objExpr() throws RecognitionException {
		ObjExprContext _localctx = new ObjExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_objExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113); match(ID);
			setState(115);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(114); methodCall();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjExprSeqContext extends ParserRuleContext {
		public ObjExprContext objExpr(int i) {
			return getRuleContext(ObjExprContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(ShelloidParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(ShelloidParser.DOT, i);
		}
		public List<ObjExprContext> objExpr() {
			return getRuleContexts(ObjExprContext.class);
		}
		public ObjExprSeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objExprSeq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterObjExprSeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitObjExprSeq(this);
		}
	}

	public final ObjExprSeqContext objExprSeq() throws RecognitionException {
		ObjExprSeqContext _localctx = new ObjExprSeqContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_objExprSeq);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(117); objExpr();
			setState(122);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(118); match(DOT);
					setState(119); objExpr();
					}
					} 
				}
				setState(124);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodCallContext extends ParserRuleContext {
		public TerminalNode LP() { return getToken(ShelloidParser.LP, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public TerminalNode RP() { return getToken(ShelloidParser.RP, 0); }
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitMethodCall(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_methodCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125); match(LP);
			setState(127);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LC) | (1L << LP) | (1L << INT) | (1L << STRING) | (1L << FLOAT) | (1L << TRUE) | (1L << FALSE) | (1L << MINUS) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				setState(126); paramList();
				}
			}

			setState(129); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(ShelloidParser.COMMA); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(ShelloidParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitParamList(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); expr(0);
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(132); match(COMMA);
				setState(133); expr(0);
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(ShelloidParser.TRUE, 0); }
		public TerminalNode FLOAT() { return getToken(ShelloidParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(ShelloidParser.INT, 0); }
		public TerminalNode STRING() { return getToken(ShelloidParser.STRING, 0); }
		public TerminalNode FALSE() { return getToken(ShelloidParser.FALSE, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShelloidParserListener ) ((ShelloidParserListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << STRING) | (1L << FLOAT) | (1L << TRUE) | (1L << FALSE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 14 >= _localctx._p;

		case 1: return 13 >= _localctx._p;

		case 2: return 12 >= _localctx._p;

		case 3: return 11 >= _localctx._p;

		case 4: return 10 >= _localctx._p;

		case 5: return 9 >= _localctx._p;

		case 6: return 8 >= _localctx._p;

		case 7: return 7 >= _localctx._p;

		case 8: return 6 >= _localctx._p;

		case 9: return 5 >= _localctx._p;

		case 10: return 4 >= _localctx._p;

		case 11: return 3 >= _localctx._p;

		case 12: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3$\u0090\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\6\2\36\n\2\r\2\16\2\37\3\3\3\3\3\3\3\3"+
		"\5\3&\n\3\3\4\3\4\3\4\3\4\5\4,\n\4\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bF\n\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\7\bo\n\b\f\b\16\br\13\b\3\t\3\t\5\tv\n\t\3\n\3\n\3\n"+
		"\7\n{\n\n\f\n\16\n~\13\n\3\13\3\13\5\13\u0082\n\13\3\13\3\13\3\f\3\f\3"+
		"\f\7\f\u0089\n\f\f\f\16\f\u008c\13\f\3\r\3\r\3\r\2\16\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\2\3\3\2\t\r\u009e\2\35\3\2\2\2\4%\3\2\2\2\6\'\3\2\2\2\b-"+
		"\3\2\2\2\n\61\3\2\2\2\f\63\3\2\2\2\16E\3\2\2\2\20s\3\2\2\2\22w\3\2\2\2"+
		"\24\177\3\2\2\2\26\u0085\3\2\2\2\30\u008d\3\2\2\2\32\33\5\4\3\2\33\34"+
		"\7\"\2\2\34\36\3\2\2\2\35\32\3\2\2\2\36\37\3\2\2\2\37\35\3\2\2\2\37 \3"+
		"\2\2\2 \3\3\2\2\2!&\5\6\4\2\"&\5\b\5\2#&\5\n\6\2$&\5\f\7\2%!\3\2\2\2%"+
		"\"\3\2\2\2%#\3\2\2\2%$\3\2\2\2&\5\3\2\2\2\'(\7\7\2\2(+\7\36\2\2)*\7\b"+
		"\2\2*,\5\16\b\2+)\3\2\2\2+,\3\2\2\2,\7\3\2\2\2-.\7\36\2\2./\7\b\2\2/\60"+
		"\5\16\b\2\60\t\3\2\2\2\61\62\5\16\b\2\62\13\3\2\2\2\63\64\7\35\2\2\64"+
		"\65\5\16\b\2\65\r\3\2\2\2\66\67\b\b\1\2\678\7\26\2\28F\5\16\b\29:\7\22"+
		"\2\2:F\5\16\b\2;F\5\22\n\2<=\7\5\2\2=>\5\16\b\2>?\7\6\2\2?F\3\2\2\2@F"+
		"\5\30\r\2AB\7\3\2\2BC\5\2\2\2CD\7\4\2\2DF\3\2\2\2E\66\3\2\2\2E9\3\2\2"+
		"\2E;\3\2\2\2E<\3\2\2\2E@\3\2\2\2EA\3\2\2\2Fp\3\2\2\2GH\6\b\2\3HI\7\17"+
		"\2\2Io\5\16\b\2JK\6\b\3\3KL\7\16\2\2Lo\5\16\b\2MN\6\b\4\3NO\7\20\2\2O"+
		"o\5\16\b\2PQ\6\b\5\3QR\7\21\2\2Ro\5\16\b\2ST\6\b\6\3TU\7\22\2\2Uo\5\16"+
		"\b\2VW\6\b\7\3WX\7\23\2\2Xo\5\16\b\2YZ\6\b\b\3Z[\7\27\2\2[o\5\16\b\2\\"+
		"]\6\b\t\3]^\7\30\2\2^o\5\16\b\2_`\6\b\n\3`a\7\32\2\2ao\5\16\b\2bc\6\b"+
		"\13\3cd\7\31\2\2do\5\16\b\2ef\6\b\f\3fg\7\33\2\2go\5\16\b\2hi\6\b\r\3"+
		"ij\7\24\2\2jo\5\16\b\2kl\6\b\16\3lm\7\25\2\2mo\5\16\b\2nG\3\2\2\2nJ\3"+
		"\2\2\2nM\3\2\2\2nP\3\2\2\2nS\3\2\2\2nV\3\2\2\2nY\3\2\2\2n\\\3\2\2\2n_"+
		"\3\2\2\2nb\3\2\2\2ne\3\2\2\2nh\3\2\2\2nk\3\2\2\2or\3\2\2\2pn\3\2\2\2p"+
		"q\3\2\2\2q\17\3\2\2\2rp\3\2\2\2su\7\36\2\2tv\5\24\13\2ut\3\2\2\2uv\3\2"+
		"\2\2v\21\3\2\2\2w|\5\20\t\2xy\7\34\2\2y{\5\20\t\2zx\3\2\2\2{~\3\2\2\2"+
		"|z\3\2\2\2|}\3\2\2\2}\23\3\2\2\2~|\3\2\2\2\177\u0081\7\5\2\2\u0080\u0082"+
		"\5\26\f\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2"+
		"\u0083\u0084\7\6\2\2\u0084\25\3\2\2\2\u0085\u008a\5\16\b\2\u0086\u0087"+
		"\7!\2\2\u0087\u0089\5\16\b\2\u0088\u0086\3\2\2\2\u0089\u008c\3\2\2\2\u008a"+
		"\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\27\3\2\2\2\u008c\u008a\3\2\2"+
		"\2\u008d\u008e\t\2\2\2\u008e\31\3\2\2\2\f\37%+Enpu|\u0081\u008a";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}