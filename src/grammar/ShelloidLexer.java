// Generated from ShelloidLexer.g4 by ANTLR 4.1
package com.shelloid.script.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ShelloidLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LC=1, RC=2, LP=3, RP=4, VAR=5, ASSIGN=6, INT=7, STRING=8, FLOAT=9, TRUE=10, 
		FALSE=11, DIV=12, MUL=13, MOD=14, PLUS=15, MINUS=16, EQUALS=17, AND=18, 
		OR=19, NOT=20, LT=21, GT=22, GTE=23, LTE=24, NEQ=25, DOT=26, RETURN=27, 
		ASYNC=28, ID=29, WS=30, COMMENT=31, COMMA=32, SEMI=33, LETTER=34, DIGIT=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'{'", "'}'", "'('", "')'", "'var'", "'='", "INT", "STRING", "FLOAT", 
		"'true'", "'false'", "'/'", "'*'", "'%'", "'+'", "'-'", "'=='", "'&&'", 
		"'||'", "'!'", "'<'", "'>'", "'>='", "'<='", "'!='", "'.'", "'return'", 
		"'async'", "ID", "WS", "COMMENT", "','", "';'", "LETTER", "DIGIT"
	};
	public static final String[] ruleNames = {
		"LC", "RC", "LP", "RP", "VAR", "ASSIGN", "INT", "STRING", "FLOAT", "TRUE", 
		"FALSE", "DIV", "MUL", "MOD", "PLUS", "MINUS", "EQUALS", "AND", "OR", 
		"NOT", "LT", "GT", "GTE", "LTE", "NEQ", "DOT", "RETURN", "ASYNC", "ID", 
		"WS", "COMMENT", "COMMA", "SEMI", "ESC", "LETTER", "DIGIT"
	};


	public ShelloidLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ShelloidLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 29: WS_action((RuleContext)_localctx, actionIndex); break;

		case 30: COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2%\u00ee\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\b\6\b[\n\b\r\b\16\b\\\3\t\3\t\3\t\7\tb\n\t\f\t\16\t"+
		"e\13\t\3\t\3\t\3\n\6\nj\n\n\r\n\16\nk\3\n\3\n\6\np\n\n\r\n\16\nq\3\n\3"+
		"\n\6\nv\n\n\r\n\16\nw\5\nz\n\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\7\36\u00bc"+
		"\n\36\f\36\16\36\u00bf\13\36\3\37\6\37\u00c2\n\37\r\37\16\37\u00c3\3\37"+
		"\3\37\3 \3 \3 \3 \7 \u00cc\n \f \16 \u00cf\13 \3 \3 \3 \3 \3 \3 \7 \u00d7"+
		"\n \f \16 \u00da\13 \3 \5 \u00dd\n \3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3#\5"+
		"#\u00e9\n#\3$\3$\3%\3%\5c\u00cd\u00d8&\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1"+
		"\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37"+
		"\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1"+
		"\65\34\1\67\35\19\36\1;\37\1= \2?!\3A\"\1C#\1E\2\1G$\1I%\1\3\2\6\5\2#"+
		"#//AA\5\2\13\f\17\17\"\"\4\2C\\c|\3\2\62;\u00fb\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3K\3\2\2\2\5M\3\2\2"+
		"\2\7O\3\2\2\2\tQ\3\2\2\2\13S\3\2\2\2\rW\3\2\2\2\17Z\3\2\2\2\21^\3\2\2"+
		"\2\23y\3\2\2\2\25{\3\2\2\2\27\u0080\3\2\2\2\31\u0086\3\2\2\2\33\u0088"+
		"\3\2\2\2\35\u008a\3\2\2\2\37\u008c\3\2\2\2!\u008e\3\2\2\2#\u0090\3\2\2"+
		"\2%\u0093\3\2\2\2\'\u0096\3\2\2\2)\u0099\3\2\2\2+\u009b\3\2\2\2-\u009d"+
		"\3\2\2\2/\u009f\3\2\2\2\61\u00a2\3\2\2\2\63\u00a5\3\2\2\2\65\u00a8\3\2"+
		"\2\2\67\u00aa\3\2\2\29\u00b1\3\2\2\2;\u00b7\3\2\2\2=\u00c1\3\2\2\2?\u00dc"+
		"\3\2\2\2A\u00e0\3\2\2\2C\u00e2\3\2\2\2E\u00e8\3\2\2\2G\u00ea\3\2\2\2I"+
		"\u00ec\3\2\2\2KL\7}\2\2L\4\3\2\2\2MN\7\177\2\2N\6\3\2\2\2OP\7*\2\2P\b"+
		"\3\2\2\2QR\7+\2\2R\n\3\2\2\2ST\7x\2\2TU\7c\2\2UV\7t\2\2V\f\3\2\2\2WX\7"+
		"?\2\2X\16\3\2\2\2Y[\5I%\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]"+
		"\20\3\2\2\2^c\7$\2\2_b\5E#\2`b\13\2\2\2a_\3\2\2\2a`\3\2\2\2be\3\2\2\2"+
		"cd\3\2\2\2ca\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7$\2\2g\22\3\2\2\2hj\5I%\2"+
		"ih\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2mo\7\60\2\2np\5I%\2"+
		"on\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2rz\3\2\2\2su\7\60\2\2tv\5I%\2"+
		"ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yi\3\2\2\2ys\3\2\2\2"+
		"z\24\3\2\2\2{|\7v\2\2|}\7t\2\2}~\7w\2\2~\177\7g\2\2\177\26\3\2\2\2\u0080"+
		"\u0081\7h\2\2\u0081\u0082\7c\2\2\u0082\u0083\7n\2\2\u0083\u0084\7u\2\2"+
		"\u0084\u0085\7g\2\2\u0085\30\3\2\2\2\u0086\u0087\7\61\2\2\u0087\32\3\2"+
		"\2\2\u0088\u0089\7,\2\2\u0089\34\3\2\2\2\u008a\u008b\7\'\2\2\u008b\36"+
		"\3\2\2\2\u008c\u008d\7-\2\2\u008d \3\2\2\2\u008e\u008f\7/\2\2\u008f\""+
		"\3\2\2\2\u0090\u0091\7?\2\2\u0091\u0092\7?\2\2\u0092$\3\2\2\2\u0093\u0094"+
		"\7(\2\2\u0094\u0095\7(\2\2\u0095&\3\2\2\2\u0096\u0097\7~\2\2\u0097\u0098"+
		"\7~\2\2\u0098(\3\2\2\2\u0099\u009a\7#\2\2\u009a*\3\2\2\2\u009b\u009c\7"+
		">\2\2\u009c,\3\2\2\2\u009d\u009e\7@\2\2\u009e.\3\2\2\2\u009f\u00a0\7@"+
		"\2\2\u00a0\u00a1\7?\2\2\u00a1\60\3\2\2\2\u00a2\u00a3\7>\2\2\u00a3\u00a4"+
		"\7?\2\2\u00a4\62\3\2\2\2\u00a5\u00a6\7#\2\2\u00a6\u00a7\7?\2\2\u00a7\64"+
		"\3\2\2\2\u00a8\u00a9\7\60\2\2\u00a9\66\3\2\2\2\u00aa\u00ab\7t\2\2\u00ab"+
		"\u00ac\7g\2\2\u00ac\u00ad\7v\2\2\u00ad\u00ae\7w\2\2\u00ae\u00af\7t\2\2"+
		"\u00af\u00b0\7p\2\2\u00b08\3\2\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7u\2"+
		"\2\u00b3\u00b4\7{\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7e\2\2\u00b6:\3\2"+
		"\2\2\u00b7\u00bd\5G$\2\u00b8\u00bc\5G$\2\u00b9\u00bc\5I%\2\u00ba\u00bc"+
		"\t\2\2\2\u00bb\u00b8\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00ba\3\2\2\2\u00bc"+
		"\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be<\3\2\2\2"+
		"\u00bf\u00bd\3\2\2\2\u00c0\u00c2\t\3\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c3"+
		"\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5"+
		"\u00c6\b\37\2\2\u00c6>\3\2\2\2\u00c7\u00c8\7\61\2\2\u00c8\u00c9\7,\2\2"+
		"\u00c9\u00cd\3\2\2\2\u00ca\u00cc\13\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf"+
		"\3\2\2\2\u00cd\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf"+
		"\u00cd\3\2\2\2\u00d0\u00d1\7,\2\2\u00d1\u00dd\7\61\2\2\u00d2\u00d3\7\61"+
		"\2\2\u00d3\u00d4\7\61\2\2\u00d4\u00d8\3\2\2\2\u00d5\u00d7\13\2\2\2\u00d6"+
		"\u00d5\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d8\u00d6\3\2"+
		"\2\2\u00d9\u00db\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00dd\7\f\2\2\u00dc"+
		"\u00c7\3\2\2\2\u00dc\u00d2\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\b "+
		"\3\2\u00df@\3\2\2\2\u00e0\u00e1\7.\2\2\u00e1B\3\2\2\2\u00e2\u00e3\7=\2"+
		"\2\u00e3D\3\2\2\2\u00e4\u00e5\7^\2\2\u00e5\u00e9\7$\2\2\u00e6\u00e7\7"+
		"^\2\2\u00e7\u00e9\7^\2\2\u00e8\u00e4\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9"+
		"F\3\2\2\2\u00ea\u00eb\t\4\2\2\u00ebH\3\2\2\2\u00ec\u00ed\t\5\2\2\u00ed"+
		"J\3\2\2\2\21\2\\ackqwy\u00bb\u00bd\u00c3\u00cd\u00d8\u00dc\u00e8";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}