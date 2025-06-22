// Generated from D:/idea/jthornruleGrammer/pdf/JQuickPDF.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class JQuickPDFParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		COLON=46, SEMICOLON=47, A0=48, A1=49, A2=50, A3=51, A4=52, A5=53, A6=54, 
		A7=55, A8=56, A9=57, A10=58, B0=59, B1=60, B2=61, B3=62, B4=63, B5=64, 
		B6=65, B7=66, B8=67, B9=68, B10=69, DEFAULT=70, EXECUTIVE=71, LEDGER=72, 
		LEGAL=73, LETTER=74, TABLOID=75, AUTO=76, NUMBER=77, IDENTIFIER=78, COLOR=79, 
		STRING=80, PATH=81, WS=82, COMMENT=83, AT=84;
	public static final int
		RULE_document = 0, RULE_page = 1, RULE_pageLayout = 2, RULE_margins = 3, 
		RULE_layoutOption = 4, RULE_customOption = 5, RULE_element = 6, RULE_paragraph = 7, 
		RULE_heading = 8, RULE_list = 9, RULE_listItem = 10, RULE_table = 11, 
		RULE_row = 12, RULE_col = 13, RULE_th = 14, RULE_td = 15, RULE_image = 16, 
		RULE_src = 17, RULE_alt = 18, RULE_svg = 19, RULE_div = 20, RULE_template = 21, 
		RULE_styleEle = 22, RULE_style = 23, RULE_attr = 24, RULE_key = 25, RULE_value = 26, 
		RULE_string = 27, RULE_color = 28, RULE_number = 29, RULE_unit = 30, RULE_variable = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"document", "page", "pageLayout", "margins", "layoutOption", "customOption", 
			"element", "paragraph", "heading", "list", "listItem", "table", "row", 
			"col", "th", "td", "image", "src", "alt", "svg", "div", "template", "styleEle", 
			"style", "attr", "key", "value", "string", "color", "number", "unit", 
			"variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'pdf'", "'{'", "'}'", "'page'", "'landscape'", "'margins'", "'CUSTOM'", 
			"'paragraph'", "'heading'", "'h1'", "'h2'", "'h3'", "'h4'", "'h5'", "'h6'", 
			"'<ol'", "'>'", "'</ol>'", "'<li'", "'</li>'", "'<table'", "'</table>'", 
			"'<tr'", "'</tr>'", "'<th'", "'</th>'", "'<td'", "'</td>'", "'<image'", 
			"'src'", "'='", "'alt'", "'<svg'", "'<div'", "'</div>'", "'template'", 
			"'use'", "'style='", "'px'", "'pt'", "'mm'", "'cm'", "'in'", "'%'", "'${'", 
			"':'", "';'", "'A0'", "'A1'", "'A2'", "'A3'", "'A4'", "'A5'", "'A6'", 
			"'A7'", "'A8'", "'A9'", "'A10'", "'B0'", "'B1'", "'B2'", "'B3'", "'B4'", 
			"'B5'", "'B6'", "'B7'", "'B8'", "'B9'", "'B10'", "'DEFAULT'", "'EXECUTIVE'", 
			"'LEDGER'", "'LEGAL'", "'LETTER'", "'TABLOID'", "'auto'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "COLON", 
			"SEMICOLON", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", 
			"A10", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", 
			"DEFAULT", "EXECUTIVE", "LEDGER", "LEGAL", "LETTER", "TABLOID", "AUTO", 
			"NUMBER", "IDENTIFIER", "COLOR", "STRING", "PATH", "WS", "COMMENT", "AT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "JQuickPDF.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JQuickPDFParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DocumentContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
		public List<PageContext> page() {
			return getRuleContexts(PageContext.class);
		}
		public PageContext page(int i) {
			return getRuleContext(PageContext.class,i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitDocument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitDocument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_document);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__0);
			setState(65);
			match(IDENTIFIER);
			setState(66);
			match(T__1);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(67);
				page();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
			match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PageContext extends ParserRuleContext {
		public PageLayoutContext pageLayout() {
			return getRuleContext(PageLayoutContext.class,0);
		}
		public MarginsContext margins() {
			return getRuleContext(MarginsContext.class,0);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public PageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_page; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterPage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitPage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitPage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PageContext page() throws RecognitionException {
		PageContext _localctx = new PageContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_page);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__3);
			{
			setState(76);
			pageLayout();
			}
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(77);
				match(T__4);
				}
			}

			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(80);
				margins();
				}
			}

			setState(83);
			match(T__1);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 95028314880L) != 0)) {
				{
				{
				setState(84);
				element();
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(90);
			match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PageLayoutContext extends ParserRuleContext {
		public LayoutOptionContext layoutOption() {
			return getRuleContext(LayoutOptionContext.class,0);
		}
		public CustomOptionContext customOption() {
			return getRuleContext(CustomOptionContext.class,0);
		}
		public PageLayoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pageLayout; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterPageLayout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitPageLayout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitPageLayout(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PageLayoutContext pageLayout() throws RecognitionException {
		PageLayoutContext _localctx = new PageLayoutContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pageLayout);
		try {
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case A0:
			case A1:
			case A2:
			case A3:
			case A4:
			case A5:
			case A6:
			case A7:
			case A8:
			case A9:
			case A10:
			case B0:
			case B1:
			case B2:
			case B3:
			case B4:
			case B5:
			case B6:
			case B7:
			case B8:
			case B9:
			case B10:
			case DEFAULT:
			case EXECUTIVE:
			case LEDGER:
			case LEGAL:
			case LETTER:
			case TABLOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				layoutOption();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				customOption();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class MarginsContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<UnitContext> unit() {
			return getRuleContexts(UnitContext.class);
		}
		public UnitContext unit(int i) {
			return getRuleContext(UnitContext.class,i);
		}
		public MarginsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_margins; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterMargins(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitMargins(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitMargins(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MarginsContext margins() throws RecognitionException {
		MarginsContext _localctx = new MarginsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_margins);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__5);
			setState(97);
			number();
			setState(98);
			unit();
			setState(99);
			number();
			setState(100);
			unit();
			setState(101);
			number();
			setState(102);
			unit();
			setState(103);
			number();
			setState(104);
			unit();
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

	@SuppressWarnings("CheckReturnValue")
	public static class LayoutOptionContext extends ParserRuleContext {
		public TerminalNode A0() { return getToken(JQuickPDFParser.A0, 0); }
		public TerminalNode A1() { return getToken(JQuickPDFParser.A1, 0); }
		public TerminalNode A2() { return getToken(JQuickPDFParser.A2, 0); }
		public TerminalNode A3() { return getToken(JQuickPDFParser.A3, 0); }
		public TerminalNode A4() { return getToken(JQuickPDFParser.A4, 0); }
		public TerminalNode A5() { return getToken(JQuickPDFParser.A5, 0); }
		public TerminalNode A6() { return getToken(JQuickPDFParser.A6, 0); }
		public TerminalNode A7() { return getToken(JQuickPDFParser.A7, 0); }
		public TerminalNode A8() { return getToken(JQuickPDFParser.A8, 0); }
		public TerminalNode A9() { return getToken(JQuickPDFParser.A9, 0); }
		public TerminalNode A10() { return getToken(JQuickPDFParser.A10, 0); }
		public TerminalNode B0() { return getToken(JQuickPDFParser.B0, 0); }
		public TerminalNode B1() { return getToken(JQuickPDFParser.B1, 0); }
		public TerminalNode B2() { return getToken(JQuickPDFParser.B2, 0); }
		public TerminalNode B3() { return getToken(JQuickPDFParser.B3, 0); }
		public TerminalNode B4() { return getToken(JQuickPDFParser.B4, 0); }
		public TerminalNode B5() { return getToken(JQuickPDFParser.B5, 0); }
		public TerminalNode B6() { return getToken(JQuickPDFParser.B6, 0); }
		public TerminalNode B7() { return getToken(JQuickPDFParser.B7, 0); }
		public TerminalNode B8() { return getToken(JQuickPDFParser.B8, 0); }
		public TerminalNode B9() { return getToken(JQuickPDFParser.B9, 0); }
		public TerminalNode B10() { return getToken(JQuickPDFParser.B10, 0); }
		public TerminalNode DEFAULT() { return getToken(JQuickPDFParser.DEFAULT, 0); }
		public TerminalNode EXECUTIVE() { return getToken(JQuickPDFParser.EXECUTIVE, 0); }
		public TerminalNode LEDGER() { return getToken(JQuickPDFParser.LEDGER, 0); }
		public TerminalNode LEGAL() { return getToken(JQuickPDFParser.LEGAL, 0); }
		public TerminalNode LETTER() { return getToken(JQuickPDFParser.LETTER, 0); }
		public TerminalNode TABLOID() { return getToken(JQuickPDFParser.TABLOID, 0); }
		public LayoutOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_layoutOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterLayoutOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitLayoutOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitLayoutOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LayoutOptionContext layoutOption() throws RecognitionException {
		LayoutOptionContext _localctx = new LayoutOptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_layoutOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_la = _input.LA(1);
			if ( !(((((_la - 48)) & ~0x3f) == 0 && ((1L << (_la - 48)) & 268435455L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CustomOptionContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<UnitContext> unit() {
			return getRuleContexts(UnitContext.class);
		}
		public UnitContext unit(int i) {
			return getRuleContext(UnitContext.class,i);
		}
		public CustomOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_customOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterCustomOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitCustomOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitCustomOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CustomOptionContext customOption() throws RecognitionException {
		CustomOptionContext _localctx = new CustomOptionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_customOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__6);
			setState(109);
			number();
			setState(110);
			unit();
			setState(111);
			number();
			setState(112);
			unit();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ElementContext extends ParserRuleContext {
		public ParagraphContext paragraph() {
			return getRuleContext(ParagraphContext.class,0);
		}
		public HeadingContext heading() {
			return getRuleContext(HeadingContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public ImageContext image() {
			return getRuleContext(ImageContext.class,0);
		}
		public SvgContext svg() {
			return getRuleContext(SvgContext.class,0);
		}
		public DivContext div() {
			return getRuleContext(DivContext.class,0);
		}
		public TemplateContext template() {
			return getRuleContext(TemplateContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_element);
		try {
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				paragraph();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				heading();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				list();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				table();
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 5);
				{
				setState(118);
				image();
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 6);
				{
				setState(119);
				svg();
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 7);
				{
				setState(120);
				div();
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 8);
				{
				setState(121);
				template();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ParagraphContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
		}
		public ParagraphContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paragraph; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterParagraph(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitParagraph(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitParagraph(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParagraphContext paragraph() throws RecognitionException {
		ParagraphContext _localctx = new ParagraphContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_paragraph);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__7);
			setState(125);
			string();
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(126);
				match(T__1);
				setState(127);
				style();
				setState(128);
				match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class HeadingContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
		}
		public HeadingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heading; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterHeading(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitHeading(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitHeading(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadingContext heading() throws RecognitionException {
		HeadingContext _localctx = new HeadingContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_heading);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(T__8);
			setState(133);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 64512L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(134);
			string();
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(135);
				match(T__1);
				setState(136);
				style();
				setState(137);
				match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ListContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public List<ListItemContext> listItem() {
			return getRuleContexts(ListItemContext.class);
		}
		public ListItemContext listItem(int i) {
			return getRuleContext(ListItemContext.class,i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__15);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(142);
				styleEle();
				}
			}

			setState(145);
			match(T__16);
			setState(147); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(146);
				listItem();
				}
				}
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__18 );
			setState(151);
			match(T__17);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ListItemContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public ListItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterListItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitListItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitListItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListItemContext listItem() throws RecognitionException {
		ListItemContext _localctx = new ListItemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_listItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__18);
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(154);
				styleEle();
				}
			}

			setState(157);
			match(T__16);
			setState(158);
			string();
			setState(159);
			match(T__19);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TableContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public List<RowContext> row() {
			return getRuleContexts(RowContext.class);
		}
		public RowContext row(int i) {
			return getRuleContext(RowContext.class,i);
		}
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__20);
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(162);
				styleEle();
				}
			}

			setState(165);
			match(T__16);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(166);
				row();
				}
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(172);
			match(T__21);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RowContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public List<ColContext> col() {
			return getRuleContexts(ColContext.class);
		}
		public ColContext col(int i) {
			return getRuleContext(ColContext.class,i);
		}
		public RowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_row; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterRow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitRow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitRow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowContext row() throws RecognitionException {
		RowContext _localctx = new RowContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_row);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(T__22);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(175);
				styleEle();
				}
			}

			setState(178);
			match(T__16);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__24 || _la==T__26) {
				{
				{
				setState(179);
				col();
				}
				}
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(185);
			match(T__23);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ColContext extends ParserRuleContext {
		public ThContext th() {
			return getRuleContext(ThContext.class,0);
		}
		public TdContext td() {
			return getRuleContext(TdContext.class,0);
		}
		public ColContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_col; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterCol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitCol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitCol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColContext col() throws RecognitionException {
		ColContext _localctx = new ColContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_col);
		try {
			setState(189);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				th();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
				td();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ThContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public ThContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_th; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTh(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThContext th() throws RecognitionException {
		ThContext _localctx = new ThContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_th);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(T__24);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(192);
				styleEle();
				}
			}

			setState(195);
			match(T__16);
			setState(196);
			value();
			setState(197);
			match(T__25);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TdContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public TdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_td; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TdContext td() throws RecognitionException {
		TdContext _localctx = new TdContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_td);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(T__26);
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(200);
				styleEle();
				}
			}

			setState(203);
			match(T__16);
			setState(204);
			value();
			setState(205);
			match(T__27);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ImageContext extends ParserRuleContext {
		public SrcContext src() {
			return getRuleContext(SrcContext.class,0);
		}
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public AltContext alt() {
			return getRuleContext(AltContext.class,0);
		}
		public ImageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_image; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterImage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitImage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitImage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImageContext image() throws RecognitionException {
		ImageContext _localctx = new ImageContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_image);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(T__28);
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__29) {
				{
				setState(208);
				src();
				}
			}

			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(211);
				styleEle();
				}
			}

			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__31) {
				{
				setState(214);
				alt();
				}
			}

			setState(217);
			match(T__16);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SrcContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public SrcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_src; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterSrc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitSrc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitSrc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SrcContext src() throws RecognitionException {
		SrcContext _localctx = new SrcContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_src);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__29);
			setState(220);
			match(T__30);
			setState(221);
			value();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AltContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public AltContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterAlt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitAlt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitAlt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AltContext alt() throws RecognitionException {
		AltContext _localctx = new AltContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_alt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(T__31);
			setState(224);
			match(T__30);
			setState(225);
			value();
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

	@SuppressWarnings("CheckReturnValue")
	public static class SvgContext extends ParserRuleContext {
		public SrcContext src() {
			return getRuleContext(SrcContext.class,0);
		}
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public SvgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_svg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterSvg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitSvg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitSvg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SvgContext svg() throws RecognitionException {
		SvgContext _localctx = new SvgContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_svg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(T__32);
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__29) {
				{
				setState(228);
				src();
				}
			}

			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(231);
				styleEle();
				}
			}

			setState(234);
			match(T__16);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DivContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public DivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_div; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DivContext div() throws RecognitionException {
		DivContext _localctx = new DivContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_div);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(T__33);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(237);
				styleEle();
				}
			}

			setState(240);
			match(T__16);
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 95028314880L) != 0)) {
				{
				{
				setState(241);
				element();
				}
				}
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(247);
			match(T__34);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TemplateContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(JQuickPDFParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(JQuickPDFParser.IDENTIFIER, i);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public TerminalNode AT() { return getToken(JQuickPDFParser.AT, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<UnitContext> unit() {
			return getRuleContexts(UnitContext.class);
		}
		public UnitContext unit(int i) {
			return getRuleContext(UnitContext.class,i);
		}
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTemplate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(T__35);
			setState(250);
			match(IDENTIFIER);
			setState(251);
			match(T__1);
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 95028314880L) != 0)) {
				{
				{
				setState(252);
				element();
				}
				}
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(258);
			match(T__2);
			setState(259);
			match(T__36);
			setState(260);
			match(IDENTIFIER);
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AT) {
				{
				setState(261);
				match(AT);
				setState(262);
				number();
				setState(263);
				unit();
				setState(264);
				number();
				setState(265);
				unit();
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

	@SuppressWarnings("CheckReturnValue")
	public static class StyleEleContext extends ParserRuleContext {
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
		}
		public StyleEleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_styleEle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterStyleEle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitStyleEle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitStyleEle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StyleEleContext styleEle() throws RecognitionException {
		StyleEleContext _localctx = new StyleEleContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_styleEle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(T__37);
			setState(270);
			style();
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

	@SuppressWarnings("CheckReturnValue")
	public static class StyleContext extends ParserRuleContext {
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(JQuickPDFParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(JQuickPDFParser.SEMICOLON, i);
		}
		public StyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_style; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StyleContext style() throws RecognitionException {
		StyleContext _localctx = new StyleContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_style);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			attr();
			setState(277);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(273);
					match(SEMICOLON);
					setState(274);
					attr();
					}
					} 
				}
				setState(279);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(280);
				match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AttrContext extends ParserRuleContext {
		public KeyContext key() {
			return getRuleContext(KeyContext.class,0);
		}
		public TerminalNode COLON() { return getToken(JQuickPDFParser.COLON, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public AttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitAttr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitAttr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttrContext attr() throws RecognitionException {
		AttrContext _localctx = new AttrContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_attr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			key();
			setState(284);
			match(COLON);
			setState(285);
			value();
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

	@SuppressWarnings("CheckReturnValue")
	public static class KeyContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_key);
		try {
			setState(289);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(287);
				match(IDENTIFIER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(288);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_value);
		try {
			setState(294);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				match(IDENTIFIER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				string();
				}
				break;
			case T__44:
				enterOuterAlt(_localctx, 3);
				{
				setState(293);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(JQuickPDFParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(STRING);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ColorContext extends ParserRuleContext {
		public TerminalNode COLOR() { return getToken(JQuickPDFParser.COLOR, 0); }
		public ColorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_color; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterColor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitColor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitColor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorContext color() throws RecognitionException {
		ColorContext _localctx = new ColorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_color);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(COLOR);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(JQuickPDFParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(NUMBER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class UnitContext extends ParserRuleContext {
		public UnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnitContext unit() throws RecognitionException {
		UnitContext _localctx = new UnitContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_unit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 34634616274944L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(T__44);
			setState(305);
			match(IDENTIFIER);
			setState(306);
			match(T__2);
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

	public static final String _serializedATN =
		"\u0004\u0001T\u0135\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000E\b\u0000\n\u0000\f\u0000H\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001O\b\u0001\u0001\u0001"+
		"\u0003\u0001R\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001V\b\u0001\n\u0001"+
		"\f\u0001Y\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0003"+
		"\u0002_\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006{\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007\u0083\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u008c\b\b\u0001\t\u0001\t\u0003\t\u0090\b\t\u0001\t\u0001\t"+
		"\u0004\t\u0094\b\t\u000b\t\f\t\u0095\u0001\t\u0001\t\u0001\n\u0001\n\u0003"+
		"\n\u009c\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00a4\b\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00a8\b\u000b"+
		"\n\u000b\f\u000b\u00ab\t\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f"+
		"\u0003\f\u00b1\b\f\u0001\f\u0001\f\u0005\f\u00b5\b\f\n\f\f\f\u00b8\t\f"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0003\r\u00be\b\r\u0001\u000e\u0001\u000e"+
		"\u0003\u000e\u00c2\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u00ca\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u00d2\b\u0010"+
		"\u0001\u0010\u0003\u0010\u00d5\b\u0010\u0001\u0010\u0003\u0010\u00d8\b"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u00e6\b\u0013\u0001\u0013\u0003\u0013\u00e9\b\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u00ef\b\u0014"+
		"\u0001\u0014\u0001\u0014\u0005\u0014\u00f3\b\u0014\n\u0014\f\u0014\u00f6"+
		"\t\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0005\u0015\u00fe\b\u0015\n\u0015\f\u0015\u0101\t\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u010c\b\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0114\b\u0017"+
		"\n\u0017\f\u0017\u0117\t\u0017\u0001\u0017\u0003\u0017\u011a\b\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0003"+
		"\u0019\u0122\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0127"+
		"\b\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0000\u0000 \u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>\u0000\u0003"+
		"\u0001\u00000K\u0001\u0000\n\u000f\u0001\u0000\',\u013a\u0000@\u0001\u0000"+
		"\u0000\u0000\u0002K\u0001\u0000\u0000\u0000\u0004^\u0001\u0000\u0000\u0000"+
		"\u0006`\u0001\u0000\u0000\u0000\bj\u0001\u0000\u0000\u0000\nl\u0001\u0000"+
		"\u0000\u0000\fz\u0001\u0000\u0000\u0000\u000e|\u0001\u0000\u0000\u0000"+
		"\u0010\u0084\u0001\u0000\u0000\u0000\u0012\u008d\u0001\u0000\u0000\u0000"+
		"\u0014\u0099\u0001\u0000\u0000\u0000\u0016\u00a1\u0001\u0000\u0000\u0000"+
		"\u0018\u00ae\u0001\u0000\u0000\u0000\u001a\u00bd\u0001\u0000\u0000\u0000"+
		"\u001c\u00bf\u0001\u0000\u0000\u0000\u001e\u00c7\u0001\u0000\u0000\u0000"+
		" \u00cf\u0001\u0000\u0000\u0000\"\u00db\u0001\u0000\u0000\u0000$\u00df"+
		"\u0001\u0000\u0000\u0000&\u00e3\u0001\u0000\u0000\u0000(\u00ec\u0001\u0000"+
		"\u0000\u0000*\u00f9\u0001\u0000\u0000\u0000,\u010d\u0001\u0000\u0000\u0000"+
		".\u0110\u0001\u0000\u0000\u00000\u011b\u0001\u0000\u0000\u00002\u0121"+
		"\u0001\u0000\u0000\u00004\u0126\u0001\u0000\u0000\u00006\u0128\u0001\u0000"+
		"\u0000\u00008\u012a\u0001\u0000\u0000\u0000:\u012c\u0001\u0000\u0000\u0000"+
		"<\u012e\u0001\u0000\u0000\u0000>\u0130\u0001\u0000\u0000\u0000@A\u0005"+
		"\u0001\u0000\u0000AB\u0005N\u0000\u0000BF\u0005\u0002\u0000\u0000CE\u0003"+
		"\u0002\u0001\u0000DC\u0001\u0000\u0000\u0000EH\u0001\u0000\u0000\u0000"+
		"FD\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GI\u0001\u0000\u0000"+
		"\u0000HF\u0001\u0000\u0000\u0000IJ\u0005\u0003\u0000\u0000J\u0001\u0001"+
		"\u0000\u0000\u0000KL\u0005\u0004\u0000\u0000LN\u0003\u0004\u0002\u0000"+
		"MO\u0005\u0005\u0000\u0000NM\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000"+
		"\u0000OQ\u0001\u0000\u0000\u0000PR\u0003\u0006\u0003\u0000QP\u0001\u0000"+
		"\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SW\u0005"+
		"\u0002\u0000\u0000TV\u0003\f\u0006\u0000UT\u0001\u0000\u0000\u0000VY\u0001"+
		"\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XZ\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000Z[\u0005\u0003\u0000"+
		"\u0000[\u0003\u0001\u0000\u0000\u0000\\_\u0003\b\u0004\u0000]_\u0003\n"+
		"\u0005\u0000^\\\u0001\u0000\u0000\u0000^]\u0001\u0000\u0000\u0000_\u0005"+
		"\u0001\u0000\u0000\u0000`a\u0005\u0006\u0000\u0000ab\u0003:\u001d\u0000"+
		"bc\u0003<\u001e\u0000cd\u0003:\u001d\u0000de\u0003<\u001e\u0000ef\u0003"+
		":\u001d\u0000fg\u0003<\u001e\u0000gh\u0003:\u001d\u0000hi\u0003<\u001e"+
		"\u0000i\u0007\u0001\u0000\u0000\u0000jk\u0007\u0000\u0000\u0000k\t\u0001"+
		"\u0000\u0000\u0000lm\u0005\u0007\u0000\u0000mn\u0003:\u001d\u0000no\u0003"+
		"<\u001e\u0000op\u0003:\u001d\u0000pq\u0003<\u001e\u0000q\u000b\u0001\u0000"+
		"\u0000\u0000r{\u0003\u000e\u0007\u0000s{\u0003\u0010\b\u0000t{\u0003\u0012"+
		"\t\u0000u{\u0003\u0016\u000b\u0000v{\u0003 \u0010\u0000w{\u0003&\u0013"+
		"\u0000x{\u0003(\u0014\u0000y{\u0003*\u0015\u0000zr\u0001\u0000\u0000\u0000"+
		"zs\u0001\u0000\u0000\u0000zt\u0001\u0000\u0000\u0000zu\u0001\u0000\u0000"+
		"\u0000zv\u0001\u0000\u0000\u0000zw\u0001\u0000\u0000\u0000zx\u0001\u0000"+
		"\u0000\u0000zy\u0001\u0000\u0000\u0000{\r\u0001\u0000\u0000\u0000|}\u0005"+
		"\b\u0000\u0000}\u0082\u00036\u001b\u0000~\u007f\u0005\u0002\u0000\u0000"+
		"\u007f\u0080\u0003.\u0017\u0000\u0080\u0081\u0005\u0003\u0000\u0000\u0081"+
		"\u0083\u0001\u0000\u0000\u0000\u0082~\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0001\u0000\u0000\u0000\u0083\u000f\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\u0005\t\u0000\u0000\u0085\u0086\u0007\u0001\u0000\u0000\u0086\u008b\u0003"+
		"6\u001b\u0000\u0087\u0088\u0005\u0002\u0000\u0000\u0088\u0089\u0003.\u0017"+
		"\u0000\u0089\u008a\u0005\u0003\u0000\u0000\u008a\u008c\u0001\u0000\u0000"+
		"\u0000\u008b\u0087\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000"+
		"\u0000\u008c\u0011\u0001\u0000\u0000\u0000\u008d\u008f\u0005\u0010\u0000"+
		"\u0000\u008e\u0090\u0003,\u0016\u0000\u008f\u008e\u0001\u0000\u0000\u0000"+
		"\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000"+
		"\u0091\u0093\u0005\u0011\u0000\u0000\u0092\u0094\u0003\u0014\n\u0000\u0093"+
		"\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095"+
		"\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096"+
		"\u0097\u0001\u0000\u0000\u0000\u0097\u0098\u0005\u0012\u0000\u0000\u0098"+
		"\u0013\u0001\u0000\u0000\u0000\u0099\u009b\u0005\u0013\u0000\u0000\u009a"+
		"\u009c\u0003,\u0016\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e"+
		"\u0005\u0011\u0000\u0000\u009e\u009f\u00036\u001b\u0000\u009f\u00a0\u0005"+
		"\u0014\u0000\u0000\u00a0\u0015\u0001\u0000\u0000\u0000\u00a1\u00a3\u0005"+
		"\u0015\u0000\u0000\u00a2\u00a4\u0003,\u0016\u0000\u00a3\u00a2\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a9\u0005\u0011\u0000\u0000\u00a6\u00a8\u0003\u0018"+
		"\f\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8\u00ab\u0001\u0000\u0000"+
		"\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ac\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000"+
		"\u0000\u00ac\u00ad\u0005\u0016\u0000\u0000\u00ad\u0017\u0001\u0000\u0000"+
		"\u0000\u00ae\u00b0\u0005\u0017\u0000\u0000\u00af\u00b1\u0003,\u0016\u0000"+
		"\u00b0\u00af\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b6\u0005\u0011\u0000\u0000"+
		"\u00b3\u00b5\u0003\u001a\r\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b8\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b6"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b9\u0001\u0000\u0000\u0000\u00b8"+
		"\u00b6\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005\u0018\u0000\u0000\u00ba"+
		"\u0019\u0001\u0000\u0000\u0000\u00bb\u00be\u0003\u001c\u000e\u0000\u00bc"+
		"\u00be\u0003\u001e\u000f\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd"+
		"\u00bc\u0001\u0000\u0000\u0000\u00be\u001b\u0001\u0000\u0000\u0000\u00bf"+
		"\u00c1\u0005\u0019\u0000\u0000\u00c0\u00c2\u0003,\u0016\u0000\u00c1\u00c0"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005\u0011\u0000\u0000\u00c4\u00c5"+
		"\u00034\u001a\u0000\u00c5\u00c6\u0005\u001a\u0000\u0000\u00c6\u001d\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c9\u0005\u001b\u0000\u0000\u00c8\u00ca\u0003"+
		",\u0016\u0000\u00c9\u00c8\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005\u0011"+
		"\u0000\u0000\u00cc\u00cd\u00034\u001a\u0000\u00cd\u00ce\u0005\u001c\u0000"+
		"\u0000\u00ce\u001f\u0001\u0000\u0000\u0000\u00cf\u00d1\u0005\u001d\u0000"+
		"\u0000\u00d0\u00d2\u0003\"\u0011\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000"+
		"\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d5\u0003,\u0016\u0000\u00d4\u00d3\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d7\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d8\u0003$\u0012\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7\u00d8"+
		"\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00da"+
		"\u0005\u0011\u0000\u0000\u00da!\u0001\u0000\u0000\u0000\u00db\u00dc\u0005"+
		"\u001e\u0000\u0000\u00dc\u00dd\u0005\u001f\u0000\u0000\u00dd\u00de\u0003"+
		"4\u001a\u0000\u00de#\u0001\u0000\u0000\u0000\u00df\u00e0\u0005 \u0000"+
		"\u0000\u00e0\u00e1\u0005\u001f\u0000\u0000\u00e1\u00e2\u00034\u001a\u0000"+
		"\u00e2%\u0001\u0000\u0000\u0000\u00e3\u00e5\u0005!\u0000\u0000\u00e4\u00e6"+
		"\u0003\"\u0011\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001"+
		"\u0000\u0000\u0000\u00e6\u00e8\u0001\u0000\u0000\u0000\u00e7\u00e9\u0003"+
		",\u0016\u0000\u00e8\u00e7\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000"+
		"\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005\u0011"+
		"\u0000\u0000\u00eb\'\u0001\u0000\u0000\u0000\u00ec\u00ee\u0005\"\u0000"+
		"\u0000\u00ed\u00ef\u0003,\u0016\u0000\u00ee\u00ed\u0001\u0000\u0000\u0000"+
		"\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000"+
		"\u00f0\u00f4\u0005\u0011\u0000\u0000\u00f1\u00f3\u0003\f\u0006\u0000\u00f2"+
		"\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f6\u0001\u0000\u0000\u0000\u00f4"+
		"\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5"+
		"\u00f7\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f7"+
		"\u00f8\u0005#\u0000\u0000\u00f8)\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005"+
		"$\u0000\u0000\u00fa\u00fb\u0005N\u0000\u0000\u00fb\u00ff\u0005\u0002\u0000"+
		"\u0000\u00fc\u00fe\u0003\f\u0006\u0000\u00fd\u00fc\u0001\u0000\u0000\u0000"+
		"\u00fe\u0101\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000"+
		"\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0102\u0001\u0000\u0000\u0000"+
		"\u0101\u00ff\u0001\u0000\u0000\u0000\u0102\u0103\u0005\u0003\u0000\u0000"+
		"\u0103\u0104\u0005%\u0000\u0000\u0104\u010b\u0005N\u0000\u0000\u0105\u0106"+
		"\u0005T\u0000\u0000\u0106\u0107\u0003:\u001d\u0000\u0107\u0108\u0003<"+
		"\u001e\u0000\u0108\u0109\u0003:\u001d\u0000\u0109\u010a\u0003<\u001e\u0000"+
		"\u010a\u010c\u0001\u0000\u0000\u0000\u010b\u0105\u0001\u0000\u0000\u0000"+
		"\u010b\u010c\u0001\u0000\u0000\u0000\u010c+\u0001\u0000\u0000\u0000\u010d"+
		"\u010e\u0005&\u0000\u0000\u010e\u010f\u0003.\u0017\u0000\u010f-\u0001"+
		"\u0000\u0000\u0000\u0110\u0115\u00030\u0018\u0000\u0111\u0112\u0005/\u0000"+
		"\u0000\u0112\u0114\u00030\u0018\u0000\u0113\u0111\u0001\u0000\u0000\u0000"+
		"\u0114\u0117\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000\u0000"+
		"\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u0119\u0001\u0000\u0000\u0000"+
		"\u0117\u0115\u0001\u0000\u0000\u0000\u0118\u011a\u0005/\u0000\u0000\u0119"+
		"\u0118\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000\u011a"+
		"/\u0001\u0000\u0000\u0000\u011b\u011c\u00032\u0019\u0000\u011c\u011d\u0005"+
		".\u0000\u0000\u011d\u011e\u00034\u001a\u0000\u011e1\u0001\u0000\u0000"+
		"\u0000\u011f\u0122\u0005N\u0000\u0000\u0120\u0122\u00036\u001b\u0000\u0121"+
		"\u011f\u0001\u0000\u0000\u0000\u0121\u0120\u0001\u0000\u0000\u0000\u0122"+
		"3\u0001\u0000\u0000\u0000\u0123\u0127\u0005N\u0000\u0000\u0124\u0127\u0003"+
		"6\u001b\u0000\u0125\u0127\u0003>\u001f\u0000\u0126\u0123\u0001\u0000\u0000"+
		"\u0000\u0126\u0124\u0001\u0000\u0000\u0000\u0126\u0125\u0001\u0000\u0000"+
		"\u0000\u01275\u0001\u0000\u0000\u0000\u0128\u0129\u0005P\u0000\u0000\u0129"+
		"7\u0001\u0000\u0000\u0000\u012a\u012b\u0005O\u0000\u0000\u012b9\u0001"+
		"\u0000\u0000\u0000\u012c\u012d\u0005M\u0000\u0000\u012d;\u0001\u0000\u0000"+
		"\u0000\u012e\u012f\u0007\u0002\u0000\u0000\u012f=\u0001\u0000\u0000\u0000"+
		"\u0130\u0131\u0005-\u0000\u0000\u0131\u0132\u0005N\u0000\u0000\u0132\u0133"+
		"\u0005\u0003\u0000\u0000\u0133?\u0001\u0000\u0000\u0000\u001fFNQW^z\u0082"+
		"\u008b\u008f\u0095\u009b\u00a3\u00a9\u00b0\u00b6\u00bd\u00c1\u00c9\u00d1"+
		"\u00d4\u00d7\u00e5\u00e8\u00ee\u00f4\u00ff\u010b\u0115\u0119\u0121\u0126";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}