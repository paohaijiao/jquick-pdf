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
		T__45=46, T__46=47, COLON=48, SEMICOLON=49, A0=50, A1=51, A2=52, A3=53, 
		A4=54, A5=55, A6=56, A7=57, A8=58, A9=59, A10=60, B0=61, B1=62, B2=63, 
		B3=64, B4=65, B5=66, B6=67, B7=68, B8=69, B9=70, B10=71, DEFAULT=72, EXECUTIVE=73, 
		LEDGER=74, LEGAL=75, LETTER=76, TABLOID=77, AUTO=78, NUMBER=79, IDENTIFIER=80, 
		COLOR=81, STRING=82, PATH=83, WS=84, COMMENT=85, AT=86;
	public static final int
		RULE_document = 0, RULE_page = 1, RULE_pageLayout = 2, RULE_margins = 3, 
		RULE_layoutOption = 4, RULE_customOption = 5, RULE_element = 6, RULE_paragraph = 7, 
		RULE_heading = 8, RULE_list = 9, RULE_orderType = 10, RULE_listItem = 11, 
		RULE_table = 12, RULE_row = 13, RULE_col = 14, RULE_th = 15, RULE_td = 16, 
		RULE_tableCell = 17, RULE_colspan = 18, RULE_rowspan = 19, RULE_image = 20, 
		RULE_svg = 21, RULE_div = 22, RULE_template = 23, RULE_styleEle = 24, 
		RULE_style = 25, RULE_attr = 26, RULE_key = 27, RULE_value = 28, RULE_string = 29, 
		RULE_color = 30, RULE_number = 31, RULE_unit = 32, RULE_variable = 33;
	private static String[] makeRuleNames() {
		return new String[] {
			"document", "page", "pageLayout", "margins", "layoutOption", "customOption", 
			"element", "paragraph", "heading", "list", "orderType", "listItem", "table", 
			"row", "col", "th", "td", "tableCell", "colspan", "rowspan", "image", 
			"svg", "div", "template", "styleEle", "style", "attr", "key", "value", 
			"string", "color", "number", "unit", "variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'pdf'", "'{'", "'}'", "'page'", "'landscape'", "'margins'", "'CUSTOM'", 
			"'paragraph'", "'heading'", "'h1'", "'h2'", "'h3'", "'h4'", "'h5'", "'h6'", 
			"'ol'", "'('", "')'", "'ordered'", "'unordered'", "'<li'", "'>'", "'</li>'", 
			"'<table'", "'</table>'", "'<tr'", "'</tr>'", "'<th'", "'</th>'", "'<td'", 
			"'</td>'", "'cell'", "'colspan'", "'rowspan'", "'image'", "'svg'", "'div'", 
			"'template'", "'use'", "'style='", "'px'", "'pt'", "'mm'", "'cm'", "'in'", 
			"'%'", "'${'", "':'", "';'", "'A0'", "'A1'", "'A2'", "'A3'", "'A4'", 
			"'A5'", "'A6'", "'A7'", "'A8'", "'A9'", "'A10'", "'B0'", "'B1'", "'B2'", 
			"'B3'", "'B4'", "'B5'", "'B6'", "'B7'", "'B8'", "'B9'", "'B10'", "'DEFAULT'", 
			"'EXECUTIVE'", "'LEDGER'", "'LEGAL'", "'LETTER'", "'TABLOID'", "'auto'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"COLON", "SEMICOLON", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", 
			"A8", "A9", "A10", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", 
			"B9", "B10", "DEFAULT", "EXECUTIVE", "LEDGER", "LEGAL", "LETTER", "TABLOID", 
			"AUTO", "NUMBER", "IDENTIFIER", "COLOR", "STRING", "PATH", "WS", "COMMENT", 
			"AT"
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
			setState(68);
			match(T__0);
			setState(69);
			match(IDENTIFIER);
			setState(70);
			match(T__1);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(71);
				page();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
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
			setState(79);
			match(T__3);
			{
			setState(80);
			pageLayout();
			}
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(81);
				match(T__4);
				}
			}

			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(84);
				margins();
				}
			}

			setState(87);
			match(T__1);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 515412919040L) != 0)) {
				{
				{
				setState(88);
				element();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(94);
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
			setState(98);
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
				setState(96);
				layoutOption();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
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
			setState(100);
			match(T__5);
			setState(101);
			number();
			setState(102);
			unit();
			setState(103);
			number();
			setState(104);
			unit();
			setState(105);
			number();
			setState(106);
			unit();
			setState(107);
			number();
			setState(108);
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
			setState(110);
			_la = _input.LA(1);
			if ( !(((((_la - 50)) & ~0x3f) == 0 && ((1L << (_la - 50)) & 268435455L) != 0)) ) {
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
			setState(112);
			match(T__6);
			setState(113);
			number();
			setState(114);
			unit();
			setState(115);
			number();
			setState(116);
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
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				paragraph();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				heading();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 3);
				{
				setState(120);
				list();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 4);
				{
				setState(121);
				table();
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 5);
				{
				setState(122);
				image();
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 6);
				{
				setState(123);
				svg();
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 7);
				{
				setState(124);
				div();
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 8);
				{
				setState(125);
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
			setState(128);
			match(T__7);
			setState(129);
			string();
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(130);
				match(T__1);
				setState(131);
				style();
				setState(132);
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
			setState(136);
			match(T__8);
			setState(137);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 64512L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(138);
			string();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(139);
				match(T__1);
				setState(140);
				style();
				setState(141);
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
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
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
			setState(145);
			match(T__15);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(146);
				match(T__16);
				setState(147);
				style();
				setState(148);
				match(T__17);
				}
			}

			setState(152);
			match(T__1);
			setState(154); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(153);
				listItem();
				}
				}
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__20 );
			setState(158);
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
	public static class OrderTypeContext extends ParserRuleContext {
		public OrderTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterOrderType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitOrderType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitOrderType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderTypeContext orderType() throws RecognitionException {
		OrderTypeContext _localctx = new OrderTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_orderType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_la = _input.LA(1);
			if ( !(_la==T__18 || _la==T__19) ) {
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
	public static class ListItemContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
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
		enterRule(_localctx, 22, RULE_listItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__20);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(163);
				match(T__16);
				setState(164);
				style();
				setState(165);
				match(T__17);
				}
			}

			setState(169);
			match(T__21);
			setState(170);
			string();
			setState(171);
			match(T__22);
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
		enterRule(_localctx, 24, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(T__23);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__39) {
				{
				setState(174);
				styleEle();
				}
			}

			setState(177);
			match(T__21);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__25) {
				{
				{
				setState(178);
				row();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(T__24);
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
		enterRule(_localctx, 26, RULE_row);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(T__25);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__39) {
				{
				setState(187);
				styleEle();
				}
			}

			setState(190);
			match(T__21);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__27 || _la==T__29) {
				{
				{
				setState(191);
				col();
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(197);
			match(T__26);
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
		enterRule(_localctx, 28, RULE_col);
		try {
			setState(201);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__27:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				th();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
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
		enterRule(_localctx, 30, RULE_th);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(T__27);
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__39) {
				{
				setState(204);
				styleEle();
				}
			}

			setState(207);
			match(T__21);
			setState(208);
			value();
			setState(209);
			match(T__28);
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
		enterRule(_localctx, 32, RULE_td);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(T__29);
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__39) {
				{
				setState(212);
				styleEle();
				}
			}

			setState(215);
			match(T__21);
			setState(216);
			value();
			setState(217);
			match(T__30);
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
	public static class TableCellContext extends ParserRuleContext {
		public ColspanContext colspan() {
			return getRuleContext(ColspanContext.class,0);
		}
		public RowspanContext rowspan() {
			return getRuleContext(RowspanContext.class,0);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public TableCellContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableCell; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTableCell(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTableCell(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTableCell(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableCellContext tableCell() throws RecognitionException {
		TableCellContext _localctx = new TableCellContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_tableCell);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__31);
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(220);
				colspan();
				}
				break;
			}
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(223);
				rowspan();
				}
			}

			setState(226);
			match(T__1);
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 515412919040L) != 0)) {
				{
				{
				setState(227);
				element();
				}
				}
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(233);
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
	public static class ColspanContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ColspanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colspan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterColspan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitColspan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitColspan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColspanContext colspan() throws RecognitionException {
		ColspanContext _localctx = new ColspanContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_colspan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			number();
			setState(236);
			match(T__32);
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
	public static class RowspanContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public RowspanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rowspan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterRowspan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitRowspan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitRowspan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowspanContext rowspan() throws RecognitionException {
		RowspanContext _localctx = new RowspanContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_rowspan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			number();
			setState(239);
			match(T__33);
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
		public TerminalNode PATH() { return getToken(JQuickPDFParser.PATH, 0); }
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
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
		enterRule(_localctx, 40, RULE_image);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(T__34);
			setState(242);
			match(PATH);
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(243);
				match(T__1);
				setState(244);
				style();
				setState(245);
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
	public static class SvgContext extends ParserRuleContext {
		public TerminalNode PATH() { return getToken(JQuickPDFParser.PATH, 0); }
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
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
		enterRule(_localctx, 42, RULE_svg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(T__35);
			setState(250);
			match(PATH);
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(251);
				match(T__1);
				setState(252);
				style();
				setState(253);
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
	public static class DivContext extends ParserRuleContext {
		public StyleContext style() {
			return getRuleContext(StyleContext.class,0);
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
		enterRule(_localctx, 44, RULE_div);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(T__36);
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(258);
				match(T__16);
				setState(259);
				style();
				setState(260);
				match(T__17);
				}
			}

			setState(264);
			match(T__1);
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 515412919040L) != 0)) {
				{
				{
				setState(265);
				element();
				}
				}
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(271);
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
		enterRule(_localctx, 46, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(T__37);
			setState(274);
			match(IDENTIFIER);
			setState(275);
			match(T__1);
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 515412919040L) != 0)) {
				{
				{
				setState(276);
				element();
				}
				}
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(282);
			match(T__2);
			setState(283);
			match(T__38);
			setState(284);
			match(IDENTIFIER);
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AT) {
				{
				setState(285);
				match(AT);
				setState(286);
				number();
				setState(287);
				unit();
				setState(288);
				number();
				setState(289);
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
		enterRule(_localctx, 48, RULE_styleEle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(T__39);
			setState(294);
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
		enterRule(_localctx, 50, RULE_style);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			attr();
			setState(301);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(297);
					match(SEMICOLON);
					setState(298);
					attr();
					}
					} 
				}
				setState(303);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(304);
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
		enterRule(_localctx, 52, RULE_attr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			key();
			setState(308);
			match(COLON);
			setState(309);
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
		enterRule(_localctx, 54, RULE_key);
		try {
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(311);
				match(IDENTIFIER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
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
		enterRule(_localctx, 56, RULE_value);
		try {
			setState(318);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				match(IDENTIFIER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				string();
				}
				break;
			case T__46:
				enterOuterAlt(_localctx, 3);
				{
				setState(317);
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
		enterRule(_localctx, 58, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
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
		enterRule(_localctx, 60, RULE_color);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
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
		enterRule(_localctx, 62, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
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
		enterRule(_localctx, 64, RULE_unit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 138538465099776L) != 0)) ) {
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
		enterRule(_localctx, 66, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(T__46);
			setState(329);
			match(IDENTIFIER);
			setState(330);
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
		"\u0004\u0001V\u014d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0005\u0000I\b\u0000\n\u0000\f\u0000L\t"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001S\b\u0001\u0001\u0001\u0003\u0001V\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u0001Z\b\u0001\n\u0001\f\u0001]\t\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0003\u0002c\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006\u007f\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u0087\b\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0090\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u0097\b\t\u0001\t\u0001\t\u0004\t\u009b\b\t"+
		"\u000b\t\f\t\u009c\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00a8\b\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0003\f\u00b0\b\f"+
		"\u0001\f\u0001\f\u0005\f\u00b4\b\f\n\f\f\f\u00b7\t\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0003\r\u00bd\b\r\u0001\r\u0001\r\u0005\r\u00c1\b\r\n\r\f\r"+
		"\u00c4\t\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e\u00ca\b"+
		"\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u00ce\b\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u00d6"+
		"\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u00de\b\u0011\u0001\u0011\u0003\u0011\u00e1\b\u0011"+
		"\u0001\u0011\u0001\u0011\u0005\u0011\u00e5\b\u0011\n\u0011\f\u0011\u00e8"+
		"\t\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00f8\b\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0100"+
		"\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003"+
		"\u0016\u0107\b\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u010b\b\u0016"+
		"\n\u0016\f\u0016\u010e\t\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0116\b\u0017\n\u0017\f\u0017"+
		"\u0119\t\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0124\b\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0005\u0019\u012c\b\u0019\n\u0019\f\u0019\u012f\t\u0019\u0001\u0019\u0003"+
		"\u0019\u0132\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u013a\b\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0003\u001c\u013f\b\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0000\u0000\"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@B\u0000\u0004\u0001"+
		"\u00002M\u0001\u0000\n\u000f\u0001\u0000\u0013\u0014\u0001\u0000).\u0150"+
		"\u0000D\u0001\u0000\u0000\u0000\u0002O\u0001\u0000\u0000\u0000\u0004b"+
		"\u0001\u0000\u0000\u0000\u0006d\u0001\u0000\u0000\u0000\bn\u0001\u0000"+
		"\u0000\u0000\np\u0001\u0000\u0000\u0000\f~\u0001\u0000\u0000\u0000\u000e"+
		"\u0080\u0001\u0000\u0000\u0000\u0010\u0088\u0001\u0000\u0000\u0000\u0012"+
		"\u0091\u0001\u0000\u0000\u0000\u0014\u00a0\u0001\u0000\u0000\u0000\u0016"+
		"\u00a2\u0001\u0000\u0000\u0000\u0018\u00ad\u0001\u0000\u0000\u0000\u001a"+
		"\u00ba\u0001\u0000\u0000\u0000\u001c\u00c9\u0001\u0000\u0000\u0000\u001e"+
		"\u00cb\u0001\u0000\u0000\u0000 \u00d3\u0001\u0000\u0000\u0000\"\u00db"+
		"\u0001\u0000\u0000\u0000$\u00eb\u0001\u0000\u0000\u0000&\u00ee\u0001\u0000"+
		"\u0000\u0000(\u00f1\u0001\u0000\u0000\u0000*\u00f9\u0001\u0000\u0000\u0000"+
		",\u0101\u0001\u0000\u0000\u0000.\u0111\u0001\u0000\u0000\u00000\u0125"+
		"\u0001\u0000\u0000\u00002\u0128\u0001\u0000\u0000\u00004\u0133\u0001\u0000"+
		"\u0000\u00006\u0139\u0001\u0000\u0000\u00008\u013e\u0001\u0000\u0000\u0000"+
		":\u0140\u0001\u0000\u0000\u0000<\u0142\u0001\u0000\u0000\u0000>\u0144"+
		"\u0001\u0000\u0000\u0000@\u0146\u0001\u0000\u0000\u0000B\u0148\u0001\u0000"+
		"\u0000\u0000DE\u0005\u0001\u0000\u0000EF\u0005P\u0000\u0000FJ\u0005\u0002"+
		"\u0000\u0000GI\u0003\u0002\u0001\u0000HG\u0001\u0000\u0000\u0000IL\u0001"+
		"\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000"+
		"KM\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000MN\u0005\u0003\u0000"+
		"\u0000N\u0001\u0001\u0000\u0000\u0000OP\u0005\u0004\u0000\u0000PR\u0003"+
		"\u0004\u0002\u0000QS\u0005\u0005\u0000\u0000RQ\u0001\u0000\u0000\u0000"+
		"RS\u0001\u0000\u0000\u0000SU\u0001\u0000\u0000\u0000TV\u0003\u0006\u0003"+
		"\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0001\u0000"+
		"\u0000\u0000W[\u0005\u0002\u0000\u0000XZ\u0003\f\u0006\u0000YX\u0001\u0000"+
		"\u0000\u0000Z]\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000[\\\u0001"+
		"\u0000\u0000\u0000\\^\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000"+
		"^_\u0005\u0003\u0000\u0000_\u0003\u0001\u0000\u0000\u0000`c\u0003\b\u0004"+
		"\u0000ac\u0003\n\u0005\u0000b`\u0001\u0000\u0000\u0000ba\u0001\u0000\u0000"+
		"\u0000c\u0005\u0001\u0000\u0000\u0000de\u0005\u0006\u0000\u0000ef\u0003"+
		">\u001f\u0000fg\u0003@ \u0000gh\u0003>\u001f\u0000hi\u0003@ \u0000ij\u0003"+
		">\u001f\u0000jk\u0003@ \u0000kl\u0003>\u001f\u0000lm\u0003@ \u0000m\u0007"+
		"\u0001\u0000\u0000\u0000no\u0007\u0000\u0000\u0000o\t\u0001\u0000\u0000"+
		"\u0000pq\u0005\u0007\u0000\u0000qr\u0003>\u001f\u0000rs\u0003@ \u0000"+
		"st\u0003>\u001f\u0000tu\u0003@ \u0000u\u000b\u0001\u0000\u0000\u0000v"+
		"\u007f\u0003\u000e\u0007\u0000w\u007f\u0003\u0010\b\u0000x\u007f\u0003"+
		"\u0012\t\u0000y\u007f\u0003\u0018\f\u0000z\u007f\u0003(\u0014\u0000{\u007f"+
		"\u0003*\u0015\u0000|\u007f\u0003,\u0016\u0000}\u007f\u0003.\u0017\u0000"+
		"~v\u0001\u0000\u0000\u0000~w\u0001\u0000\u0000\u0000~x\u0001\u0000\u0000"+
		"\u0000~y\u0001\u0000\u0000\u0000~z\u0001\u0000\u0000\u0000~{\u0001\u0000"+
		"\u0000\u0000~|\u0001\u0000\u0000\u0000~}\u0001\u0000\u0000\u0000\u007f"+
		"\r\u0001\u0000\u0000\u0000\u0080\u0081\u0005\b\u0000\u0000\u0081\u0086"+
		"\u0003:\u001d\u0000\u0082\u0083\u0005\u0002\u0000\u0000\u0083\u0084\u0003"+
		"2\u0019\u0000\u0084\u0085\u0005\u0003\u0000\u0000\u0085\u0087\u0001\u0000"+
		"\u0000\u0000\u0086\u0082\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000"+
		"\u0000\u0000\u0087\u000f\u0001\u0000\u0000\u0000\u0088\u0089\u0005\t\u0000"+
		"\u0000\u0089\u008a\u0007\u0001\u0000\u0000\u008a\u008f\u0003:\u001d\u0000"+
		"\u008b\u008c\u0005\u0002\u0000\u0000\u008c\u008d\u00032\u0019\u0000\u008d"+
		"\u008e\u0005\u0003\u0000\u0000\u008e\u0090\u0001\u0000\u0000\u0000\u008f"+
		"\u008b\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090"+
		"\u0011\u0001\u0000\u0000\u0000\u0091\u0096\u0005\u0010\u0000\u0000\u0092"+
		"\u0093\u0005\u0011\u0000\u0000\u0093\u0094\u00032\u0019\u0000\u0094\u0095"+
		"\u0005\u0012\u0000\u0000\u0095\u0097\u0001\u0000\u0000\u0000\u0096\u0092"+
		"\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0001\u0000\u0000\u0000\u0098\u009a\u0005\u0002\u0000\u0000\u0099\u009b"+
		"\u0003\u0016\u000b\u0000\u009a\u0099\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f"+
		"\u0005\u0003\u0000\u0000\u009f\u0013\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0007\u0002\u0000\u0000\u00a1\u0015\u0001\u0000\u0000\u0000\u00a2\u00a7"+
		"\u0005\u0015\u0000\u0000\u00a3\u00a4\u0005\u0011\u0000\u0000\u00a4\u00a5"+
		"\u00032\u0019\u0000\u00a5\u00a6\u0005\u0012\u0000\u0000\u00a6\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a3\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005"+
		"\u0016\u0000\u0000\u00aa\u00ab\u0003:\u001d\u0000\u00ab\u00ac\u0005\u0017"+
		"\u0000\u0000\u00ac\u0017\u0001\u0000\u0000\u0000\u00ad\u00af\u0005\u0018"+
		"\u0000\u0000\u00ae\u00b0\u00030\u0018\u0000\u00af\u00ae\u0001\u0000\u0000"+
		"\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b5\u0005\u0016\u0000\u0000\u00b2\u00b4\u0003\u001a\r\u0000"+
		"\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b8\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b9\u0005\u0019\u0000\u0000\u00b9\u0019\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bc\u0005\u001a\u0000\u0000\u00bb\u00bd\u00030\u0018\u0000\u00bc"+
		"\u00bb\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd"+
		"\u00be\u0001\u0000\u0000\u0000\u00be\u00c2\u0005\u0016\u0000\u0000\u00bf"+
		"\u00c1\u0003\u001c\u000e\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c1"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c5\u0001\u0000\u0000\u0000\u00c4"+
		"\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005\u001b\u0000\u0000\u00c6"+
		"\u001b\u0001\u0000\u0000\u0000\u00c7\u00ca\u0003\u001e\u000f\u0000\u00c8"+
		"\u00ca\u0003 \u0010\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00c9\u00c8"+
		"\u0001\u0000\u0000\u0000\u00ca\u001d\u0001\u0000\u0000\u0000\u00cb\u00cd"+
		"\u0005\u001c\u0000\u0000\u00cc\u00ce\u00030\u0018\u0000\u00cd\u00cc\u0001"+
		"\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d0\u0005\u0016\u0000\u0000\u00d0\u00d1\u0003"+
		"8\u001c\u0000\u00d1\u00d2\u0005\u001d\u0000\u0000\u00d2\u001f\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d5\u0005\u001e\u0000\u0000\u00d4\u00d6\u00030\u0018"+
		"\u0000\u00d5\u00d4\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7\u00d8\u0005\u0016\u0000"+
		"\u0000\u00d8\u00d9\u00038\u001c\u0000\u00d9\u00da\u0005\u001f\u0000\u0000"+
		"\u00da!\u0001\u0000\u0000\u0000\u00db\u00dd\u0005 \u0000\u0000\u00dc\u00de"+
		"\u0003$\u0012\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000\u00dd\u00de\u0001"+
		"\u0000\u0000\u0000\u00de\u00e0\u0001\u0000\u0000\u0000\u00df\u00e1\u0003"+
		"&\u0013\u0000\u00e0\u00df\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e6\u0005\u0002"+
		"\u0000\u0000\u00e3\u00e5\u0003\f\u0006\u0000\u00e4\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e5\u00e8\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7\u00e9\u0001\u0000\u0000"+
		"\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005\u0003\u0000"+
		"\u0000\u00ea#\u0001\u0000\u0000\u0000\u00eb\u00ec\u0003>\u001f\u0000\u00ec"+
		"\u00ed\u0005!\u0000\u0000\u00ed%\u0001\u0000\u0000\u0000\u00ee\u00ef\u0003"+
		">\u001f\u0000\u00ef\u00f0\u0005\"\u0000\u0000\u00f0\'\u0001\u0000\u0000"+
		"\u0000\u00f1\u00f2\u0005#\u0000\u0000\u00f2\u00f7\u0005S\u0000\u0000\u00f3"+
		"\u00f4\u0005\u0002\u0000\u0000\u00f4\u00f5\u00032\u0019\u0000\u00f5\u00f6"+
		"\u0005\u0003\u0000\u0000\u00f6\u00f8\u0001\u0000\u0000\u0000\u00f7\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8)\u0001"+
		"\u0000\u0000\u0000\u00f9\u00fa\u0005$\u0000\u0000\u00fa\u00ff\u0005S\u0000"+
		"\u0000\u00fb\u00fc\u0005\u0002\u0000\u0000\u00fc\u00fd\u00032\u0019\u0000"+
		"\u00fd\u00fe\u0005\u0003\u0000\u0000\u00fe\u0100\u0001\u0000\u0000\u0000"+
		"\u00ff\u00fb\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000"+
		"\u0100+\u0001\u0000\u0000\u0000\u0101\u0106\u0005%\u0000\u0000\u0102\u0103"+
		"\u0005\u0011\u0000\u0000\u0103\u0104\u00032\u0019\u0000\u0104\u0105\u0005"+
		"\u0012\u0000\u0000\u0105\u0107\u0001\u0000\u0000\u0000\u0106\u0102\u0001"+
		"\u0000\u0000\u0000\u0106\u0107\u0001\u0000\u0000\u0000\u0107\u0108\u0001"+
		"\u0000\u0000\u0000\u0108\u010c\u0005\u0002\u0000\u0000\u0109\u010b\u0003"+
		"\f\u0006\u0000\u010a\u0109\u0001\u0000\u0000\u0000\u010b\u010e\u0001\u0000"+
		"\u0000\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000"+
		"\u0000\u0000\u010d\u010f\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000"+
		"\u0000\u0000\u010f\u0110\u0005\u0003\u0000\u0000\u0110-\u0001\u0000\u0000"+
		"\u0000\u0111\u0112\u0005&\u0000\u0000\u0112\u0113\u0005P\u0000\u0000\u0113"+
		"\u0117\u0005\u0002\u0000\u0000\u0114\u0116\u0003\f\u0006\u0000\u0115\u0114"+
		"\u0001\u0000\u0000\u0000\u0116\u0119\u0001\u0000\u0000\u0000\u0117\u0115"+
		"\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000\u0000\u0000\u0118\u011a"+
		"\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u011a\u011b"+
		"\u0005\u0003\u0000\u0000\u011b\u011c\u0005\'\u0000\u0000\u011c\u0123\u0005"+
		"P\u0000\u0000\u011d\u011e\u0005V\u0000\u0000\u011e\u011f\u0003>\u001f"+
		"\u0000\u011f\u0120\u0003@ \u0000\u0120\u0121\u0003>\u001f\u0000\u0121"+
		"\u0122\u0003@ \u0000\u0122\u0124\u0001\u0000\u0000\u0000\u0123\u011d\u0001"+
		"\u0000\u0000\u0000\u0123\u0124\u0001\u0000\u0000\u0000\u0124/\u0001\u0000"+
		"\u0000\u0000\u0125\u0126\u0005(\u0000\u0000\u0126\u0127\u00032\u0019\u0000"+
		"\u01271\u0001\u0000\u0000\u0000\u0128\u012d\u00034\u001a\u0000\u0129\u012a"+
		"\u00051\u0000\u0000\u012a\u012c\u00034\u001a\u0000\u012b\u0129\u0001\u0000"+
		"\u0000\u0000\u012c\u012f\u0001\u0000\u0000\u0000\u012d\u012b\u0001\u0000"+
		"\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000\u012e\u0131\u0001\u0000"+
		"\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u0130\u0132\u00051\u0000"+
		"\u0000\u0131\u0130\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000"+
		"\u0000\u01323\u0001\u0000\u0000\u0000\u0133\u0134\u00036\u001b\u0000\u0134"+
		"\u0135\u00050\u0000\u0000\u0135\u0136\u00038\u001c\u0000\u01365\u0001"+
		"\u0000\u0000\u0000\u0137\u013a\u0005P\u0000\u0000\u0138\u013a\u0003:\u001d"+
		"\u0000\u0139\u0137\u0001\u0000\u0000\u0000\u0139\u0138\u0001\u0000\u0000"+
		"\u0000\u013a7\u0001\u0000\u0000\u0000\u013b\u013f\u0005P\u0000\u0000\u013c"+
		"\u013f\u0003:\u001d\u0000\u013d\u013f\u0003B!\u0000\u013e\u013b\u0001"+
		"\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013e\u013d\u0001"+
		"\u0000\u0000\u0000\u013f9\u0001\u0000\u0000\u0000\u0140\u0141\u0005R\u0000"+
		"\u0000\u0141;\u0001\u0000\u0000\u0000\u0142\u0143\u0005Q\u0000\u0000\u0143"+
		"=\u0001\u0000\u0000\u0000\u0144\u0145\u0005O\u0000\u0000\u0145?\u0001"+
		"\u0000\u0000\u0000\u0146\u0147\u0007\u0003\u0000\u0000\u0147A\u0001\u0000"+
		"\u0000\u0000\u0148\u0149\u0005/\u0000\u0000\u0149\u014a\u0005P\u0000\u0000"+
		"\u014a\u014b\u0005\u0003\u0000\u0000\u014bC\u0001\u0000\u0000\u0000\u001f"+
		"JRU[b~\u0086\u008f\u0096\u009c\u00a7\u00af\u00b5\u00bc\u00c2\u00c9\u00cd"+
		"\u00d5\u00dd\u00e0\u00e6\u00f7\u00ff\u0106\u010c\u0117\u0123\u012d\u0131"+
		"\u0139\u013e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}