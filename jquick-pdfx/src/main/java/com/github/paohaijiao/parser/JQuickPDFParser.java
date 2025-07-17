// Generated from D:/my/jthornruleGrammer/pdf/JQuickPDF.g4 by ANTLR 4.13.2
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
		T__45=46, T__46=47, COLON=48, SEMICOLON=49, DEFAULT=50, EXECUTIVE=51, 
		LEDGER=52, LEGAL=53, LETTER=54, TABLOID=55, AUTO=56, DOCTYPE=57, NUMBERUNIT=58, 
		NUMBER=59, UNIT=60, STRING=61, BORDERTYPE=62, CMYK_PERCENT=63, CMYK_COLOR=64, 
		RGB_COLOR=65, COLORENUM=66, IDENTIFIER=67, STYLEVALUE=68, NON_WHITESPACE_STRING=69, 
		WS=70, COMMENT=71, AT=72, TEXT=73, HEX_HEX=74;
	public static final int
		RULE_document = 0, RULE_doc = 1, RULE_docType = 2, RULE_html = 3, RULE_head = 4, 
		RULE_headStyle = 5, RULE_headStyleOption = 6, RULE_bodyStyleOption = 7, 
		RULE_body = 8, RULE_element = 9, RULE_paragraph = 10, RULE_heading = 11, 
		RULE_list = 12, RULE_listEle = 13, RULE_listItem = 14, RULE_table = 15, 
		RULE_row = 16, RULE_col = 17, RULE_th = 18, RULE_td = 19, RULE_image = 20, 
		RULE_src = 21, RULE_alt = 22, RULE_svg = 23, RULE_div = 24, RULE_span = 25, 
		RULE_lbr = 26, RULE_rbr = 27, RULE_template = 28, RULE_styleEle = 29, 
		RULE_style = 30, RULE_attr = 31, RULE_key = 32, RULE_value = 33, RULE_string = 34, 
		RULE_marginValue = 35, RULE_number = 36, RULE_borderType = 37, RULE_unit = 38, 
		RULE_variable = 39, RULE_color = 40, RULE_u = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"document", "doc", "docType", "html", "head", "headStyle", "headStyleOption", 
			"bodyStyleOption", "body", "element", "paragraph", "heading", "list", 
			"listEle", "listItem", "table", "row", "col", "th", "td", "image", "src", 
			"alt", "svg", "div", "span", "lbr", "rbr", "template", "styleEle", "style", 
			"attr", "key", "value", "string", "marginValue", "number", "borderType", 
			"unit", "variable", "color", "u"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'<html>'", "'</html>'", "'<head>'", "'</head>'", "'<style>'", 
			"'</style>'", "'@page'", "'{'", "'}'", "'<body>'", "'</body>'", "'<p'", 
			"'>'", "'</p>'", "'<h'", "'</h'", "'<'", "'</'", "'ol'", "'ul'", "'<li'", 
			"'</li>'", "'<table'", "'</table>'", "'<tr'", "'</tr>'", "'<th'", "'</th>'", 
			"'<td'", "'</td>'", "'<image'", "'</image>'", "'src'", "'='", "'alt'", 
			"'<svg'", "'</svg>'", "'<div'", "'</div>'", "'<span'", "'</span>'", "'<br'", 
			"'template'", "'use'", "'style='", "'${'", "'#'", "':'", "';'", "'DEFAULT'", 
			"'EXECUTIVE'", "'LEDGER'", "'LEGAL'", "'LETTER'", "'TABLOID'", "'auto'", 
			"'<!DOCTYPE html>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"COLON", "SEMICOLON", "DEFAULT", "EXECUTIVE", "LEDGER", "LEGAL", "LETTER", 
			"TABLOID", "AUTO", "DOCTYPE", "NUMBERUNIT", "NUMBER", "UNIT", "STRING", 
			"BORDERTYPE", "CMYK_PERCENT", "CMYK_COLOR", "RGB_COLOR", "COLORENUM", 
			"IDENTIFIER", "STYLEVALUE", "NON_WHITESPACE_STRING", "WS", "COMMENT", 
			"AT", "TEXT", "HEX_HEX"
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
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			doc();
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
	public static class DocContext extends ParserRuleContext {
		public HtmlContext html() {
			return getRuleContext(HtmlContext.class,0);
		}
		public DocTypeContext docType() {
			return getRuleContext(DocTypeContext.class,0);
		}
		public DocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterDoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitDoc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitDoc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocContext doc() throws RecognitionException {
		DocContext _localctx = new DocContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOCTYPE) {
				{
				setState(86);
				docType();
				}
			}

			setState(89);
			html();
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
	public static class DocTypeContext extends ParserRuleContext {
		public TerminalNode DOCTYPE() { return getToken(JQuickPDFParser.DOCTYPE, 0); }
		public DocTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_docType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterDocType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitDocType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitDocType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocTypeContext docType() throws RecognitionException {
		DocTypeContext _localctx = new DocTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_docType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(DOCTYPE);
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
	public static class HtmlContext extends ParserRuleContext {
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public HtmlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterHtml(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitHtml(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitHtml(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HtmlContext html() throws RecognitionException {
		HtmlContext _localctx = new HtmlContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_html);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__0);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(94);
				head();
				}
			}

			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(97);
				body();
				}
			}

			setState(100);
			match(T__1);
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
	public static class HeadContext extends ParserRuleContext {
		public HeadStyleContext headStyle() {
			return getRuleContext(HeadStyleContext.class,0);
		}
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_head);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__2);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(103);
				headStyle();
				}
			}

			setState(106);
			match(T__3);
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
	public static class HeadStyleContext extends ParserRuleContext {
		public HeadStyleOptionContext headStyleOption() {
			return getRuleContext(HeadStyleOptionContext.class,0);
		}
		public BodyStyleOptionContext bodyStyleOption() {
			return getRuleContext(BodyStyleOptionContext.class,0);
		}
		public HeadStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterHeadStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitHeadStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitHeadStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadStyleContext headStyle() throws RecognitionException {
		HeadStyleContext _localctx = new HeadStyleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_headStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__4);
			{
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(109);
				headStyleOption();
				}
			}

			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(112);
				bodyStyleOption();
				}
			}

			}
			setState(115);
			match(T__5);
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
	public static class HeadStyleOptionContext extends ParserRuleContext {
		public List<StyleContext> style() {
			return getRuleContexts(StyleContext.class);
		}
		public StyleContext style(int i) {
			return getRuleContext(StyleContext.class,i);
		}
		public HeadStyleOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headStyleOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterHeadStyleOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitHeadStyleOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitHeadStyleOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadStyleOptionContext headStyleOption() throws RecognitionException {
		HeadStyleOptionContext _localctx = new HeadStyleOptionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_headStyleOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__6);
			setState(118);
			match(T__7);
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING || _la==IDENTIFIER) {
				{
				{
				setState(119);
				style();
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(125);
			match(T__8);
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
	public static class BodyStyleOptionContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
		public List<StyleContext> style() {
			return getRuleContexts(StyleContext.class);
		}
		public StyleContext style(int i) {
			return getRuleContext(StyleContext.class,i);
		}
		public BodyStyleOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bodyStyleOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterBodyStyleOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitBodyStyleOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitBodyStyleOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyStyleOptionContext bodyStyleOption() throws RecognitionException {
		BodyStyleOptionContext _localctx = new BodyStyleOptionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bodyStyleOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(IDENTIFIER);
			setState(128);
			match(T__7);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING || _la==IDENTIFIER) {
				{
				{
				setState(129);
				style();
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(135);
			match(T__8);
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
	public static class BodyContext extends ParserRuleContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__9);
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9141846446080L) != 0)) {
					{
					{
					setState(138);
					element();
					}
					}
					setState(143);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(146);
			match(T__10);
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
		enterRule(_localctx, 18, RULE_element);
		try {
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				paragraph();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				heading();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				list();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 4);
				{
				setState(151);
				table();
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 5);
				{
				setState(152);
				image();
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 6);
				{
				setState(153);
				svg();
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 7);
				{
				setState(154);
				div();
				}
				break;
			case T__42:
				enterOuterAlt(_localctx, 8);
				{
				setState(155);
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
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public LbrContext lbr() {
			return getRuleContext(LbrContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public RbrContext rbr() {
			return getRuleContext(RbrContext.class,0);
		}
		public List<ImageContext> image() {
			return getRuleContexts(ImageContext.class);
		}
		public ImageContext image(int i) {
			return getRuleContext(ImageContext.class,i);
		}
		public List<SpanContext> span() {
			return getRuleContexts(SpanContext.class);
		}
		public SpanContext span(int i) {
			return getRuleContext(SpanContext.class,i);
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
		enterRule(_localctx, 20, RULE_paragraph);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__11);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(159);
				styleEle();
				}
			}

			setState(162);
			match(T__12);
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(163);
				lbr();
				}
				break;
			}
			{
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(166);
				image();
				}
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__39) {
					{
					{
					setState(172);
					span();
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & 148942849L) != 0)) {
				{
				setState(180);
				value();
				}
			}

			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__41) {
				{
				setState(183);
				rbr();
				}
			}

			setState(186);
			match(T__13);
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
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
		enterRule(_localctx, 22, RULE_heading);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__14);
			setState(189);
			number();
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(190);
				styleEle();
				}
			}

			setState(193);
			match(T__12);
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & 148942849L) != 0)) {
				{
				setState(194);
				value();
				}
			}

			setState(197);
			match(T__15);
			setState(198);
			number();
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(199);
				match(T__12);
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
		public List<ListEleContext> listEle() {
			return getRuleContexts(ListEleContext.class);
		}
		public ListEleContext listEle(int i) {
			return getRuleContext(ListEleContext.class,i);
		}
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
		enterRule(_localctx, 24, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(T__16);
			setState(203);
			listEle();
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(204);
				styleEle();
				}
			}

			setState(207);
			match(T__12);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__20) {
				{
				{
				setState(208);
				listItem();
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(214);
			match(T__17);
			setState(215);
			listEle();
			setState(216);
			match(T__12);
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
	public static class ListEleContext extends ParserRuleContext {
		public ListEleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listEle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterListEle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitListEle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitListEle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListEleContext listEle() throws RecognitionException {
		ListEleContext _localctx = new ListEleContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_listEle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
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
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
		enterRule(_localctx, 28, RULE_listItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(T__20);
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(221);
				styleEle();
				}
			}

			setState(224);
			match(T__12);
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & 148942849L) != 0)) {
				{
				setState(225);
				value();
				}
			}

			setState(228);
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
		enterRule(_localctx, 30, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(T__22);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(231);
				styleEle();
				}
			}

			setState(234);
			match(T__12);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__24) {
				{
				{
				setState(235);
				row();
				}
				}
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(241);
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
		enterRule(_localctx, 32, RULE_row);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(T__24);
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(244);
				styleEle();
				}
			}

			setState(247);
			match(T__12);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__26 || _la==T__28) {
				{
				{
				setState(248);
				col();
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(254);
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
		enterRule(_localctx, 34, RULE_col);
		try {
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				th();
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
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
		enterRule(_localctx, 36, RULE_th);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(T__26);
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(261);
				styleEle();
				}
			}

			setState(264);
			match(T__12);
			setState(265);
			value();
			setState(266);
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
		enterRule(_localctx, 38, RULE_td);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__28);
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(269);
				styleEle();
				}
			}

			setState(272);
			match(T__12);
			setState(273);
			value();
			setState(274);
			match(T__29);
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
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
			setState(276);
			match(T__30);
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__32) {
				{
				setState(277);
				src();
				}
			}

			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(280);
				styleEle();
				}
			}

			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__34) {
				{
				setState(283);
				alt();
				}
			}

			setState(286);
			match(T__12);
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & 148942849L) != 0)) {
				{
				setState(287);
				value();
				}
			}

			setState(290);
			match(T__31);
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
		enterRule(_localctx, 42, RULE_src);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(T__32);
			setState(293);
			match(T__33);
			setState(294);
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
		enterRule(_localctx, 44, RULE_alt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(T__34);
			setState(297);
			match(T__33);
			setState(298);
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
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
		enterRule(_localctx, 46, RULE_svg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(T__35);
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__32) {
				{
				setState(301);
				src();
				}
			}

			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(304);
				styleEle();
				}
			}

			setState(307);
			match(T__12);
			setState(309);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & 148942849L) != 0)) {
				{
				setState(308);
				value();
				}
			}

			setState(311);
			match(T__36);
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
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
		enterRule(_localctx, 48, RULE_div);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			match(T__37);
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(314);
				styleEle();
				}
			}

			setState(317);
			match(T__12);
			setState(324);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9141846446080L) != 0)) {
					{
					{
					setState(318);
					element();
					}
					}
					setState(323);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & 148942849L) != 0)) {
				{
				setState(326);
				value();
				}
			}

			setState(329);
			match(T__38);
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
	public static class SpanContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public LbrContext lbr() {
			return getRuleContext(LbrContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public RbrContext rbr() {
			return getRuleContext(RbrContext.class,0);
		}
		public SpanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_span; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterSpan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitSpan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitSpan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpanContext span() throws RecognitionException {
		SpanContext _localctx = new SpanContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_span);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(T__39);
			setState(333);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(332);
				styleEle();
				}
			}

			setState(335);
			match(T__12);
			setState(337);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(336);
				lbr();
				}
				break;
			}
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & 148942849L) != 0)) {
				{
				setState(339);
				value();
				}
			}

			setState(343);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__41) {
				{
				setState(342);
				rbr();
				}
			}

			setState(345);
			match(T__40);
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
	public static class LbrContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public LbrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lbr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterLbr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitLbr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitLbr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LbrContext lbr() throws RecognitionException {
		LbrContext _localctx = new LbrContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_lbr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			match(T__41);
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(348);
				styleEle();
				}
			}

			setState(351);
			match(T__12);
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
	public static class RbrContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public RbrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rbr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterRbr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitRbr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitRbr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RbrContext rbr() throws RecognitionException {
		RbrContext _localctx = new RbrContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_rbr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			match(T__41);
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(354);
				styleEle();
				}
			}

			setState(357);
			match(T__12);
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
		enterRule(_localctx, 56, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(T__42);
			setState(360);
			match(IDENTIFIER);
			setState(361);
			match(T__7);
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9141846446080L) != 0)) {
				{
				{
				setState(362);
				element();
				}
				}
				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(368);
			match(T__8);
			setState(369);
			match(T__43);
			setState(370);
			match(IDENTIFIER);
			setState(377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AT) {
				{
				setState(371);
				match(AT);
				setState(372);
				number();
				setState(373);
				unit();
				setState(374);
				number();
				setState(375);
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
		public TerminalNode STRING() { return getToken(JQuickPDFParser.STRING, 0); }
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
		enterRule(_localctx, 58, RULE_styleEle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(T__44);
			setState(381);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(380);
				match(STRING);
				}
				break;
			}
			setState(384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING || _la==IDENTIFIER) {
				{
				setState(383);
				style();
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
		enterRule(_localctx, 60, RULE_style);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			attr();
			setState(393);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(388);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SEMICOLON) {
						{
						setState(387);
						match(SEMICOLON);
						}
					}

					setState(390);
					attr();
					}
					} 
				}
				setState(395);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			}
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(396);
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
		enterRule(_localctx, 62, RULE_attr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			key();
			setState(400);
			match(COLON);
			setState(401);
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
		enterRule(_localctx, 64, RULE_key);
		try {
			setState(405);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(403);
				match(IDENTIFIER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
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
		public MarginValueContext marginValue() {
			return getRuleContext(MarginValueContext.class,0);
		}
		public TerminalNode STYLEVALUE() { return getToken(JQuickPDFParser.STYLEVALUE, 0); }
		public TerminalNode NON_WHITESPACE_STRING() { return getToken(JQuickPDFParser.NON_WHITESPACE_STRING, 0); }
		public TerminalNode TEXT() { return getToken(JQuickPDFParser.TEXT, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
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
		enterRule(_localctx, 66, RULE_value);
		try {
			setState(415);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(407);
				match(IDENTIFIER);
				}
				break;
			case NUMBERUNIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(408);
				marginValue();
				}
				break;
			case STYLEVALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(409);
				match(STYLEVALUE);
				}
				break;
			case NON_WHITESPACE_STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(410);
				match(NON_WHITESPACE_STRING);
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 5);
				{
				setState(411);
				match(TEXT);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 6);
				{
				setState(412);
				string();
				}
				break;
			case T__45:
				enterOuterAlt(_localctx, 7);
				{
				setState(413);
				variable();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 8);
				{
				setState(414);
				number();
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
		enterRule(_localctx, 68, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
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
	public static class MarginValueContext extends ParserRuleContext {
		public List<TerminalNode> NUMBERUNIT() { return getTokens(JQuickPDFParser.NUMBERUNIT); }
		public TerminalNode NUMBERUNIT(int i) {
			return getToken(JQuickPDFParser.NUMBERUNIT, i);
		}
		public MarginValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_marginValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterMarginValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitMarginValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitMarginValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MarginValueContext marginValue() throws RecognitionException {
		MarginValueContext _localctx = new MarginValueContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_marginValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			match(NUMBERUNIT);
			setState(420);
			match(NUMBERUNIT);
			setState(421);
			match(NUMBERUNIT);
			setState(422);
			match(NUMBERUNIT);
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
		enterRule(_localctx, 72, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
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
	public static class BorderTypeContext extends ParserRuleContext {
		public TerminalNode BORDERTYPE() { return getToken(JQuickPDFParser.BORDERTYPE, 0); }
		public BorderTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_borderType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterBorderType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitBorderType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitBorderType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BorderTypeContext borderType() throws RecognitionException {
		BorderTypeContext _localctx = new BorderTypeContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_borderType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(BORDERTYPE);
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
		public TerminalNode NUMBERUNIT() { return getToken(JQuickPDFParser.NUMBERUNIT, 0); }
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
		enterRule(_localctx, 76, RULE_unit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(428);
			match(NUMBERUNIT);
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
		enterRule(_localctx, 78, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			match(T__45);
			setState(431);
			match(IDENTIFIER);
			setState(432);
			match(T__8);
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
		public TerminalNode HEX_HEX() { return getToken(JQuickPDFParser.HEX_HEX, 0); }
		public TerminalNode RGB_COLOR() { return getToken(JQuickPDFParser.RGB_COLOR, 0); }
		public TerminalNode CMYK_COLOR() { return getToken(JQuickPDFParser.CMYK_COLOR, 0); }
		public TerminalNode CMYK_PERCENT() { return getToken(JQuickPDFParser.CMYK_PERCENT, 0); }
		public TerminalNode COLORENUM() { return getToken(JQuickPDFParser.COLORENUM, 0); }
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
		enterRule(_localctx, 80, RULE_color);
		try {
			setState(440);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__46:
				enterOuterAlt(_localctx, 1);
				{
				setState(434);
				match(T__46);
				setState(435);
				match(HEX_HEX);
				}
				break;
			case RGB_COLOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(436);
				match(RGB_COLOR);
				}
				break;
			case CMYK_COLOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(437);
				match(CMYK_COLOR);
				}
				break;
			case CMYK_PERCENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(438);
				match(CMYK_PERCENT);
				}
				break;
			case COLORENUM:
				enterOuterAlt(_localctx, 5);
				{
				setState(439);
				match(COLORENUM);
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
	public static class UContext extends ParserRuleContext {
		public TerminalNode NUMBERUNIT() { return getToken(JQuickPDFParser.NUMBERUNIT, 0); }
		public UContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_u; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterU(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitU(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitU(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UContext u() throws RecognitionException {
		UContext _localctx = new UContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_u);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			match(NUMBERUNIT);
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
		"\u0004\u0001J\u01bd\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0001\u0000\u0001\u0000\u0001\u0001\u0003\u0001"+
		"X\b\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0003\u0003`\b\u0003\u0001\u0003\u0003\u0003c\b\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0003\u0004i\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0003\u0005o\b\u0005\u0001"+
		"\u0005\u0003\u0005r\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006y\b\u0006\n\u0006\f\u0006|\t\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0083"+
		"\b\u0007\n\u0007\f\u0007\u0086\t\u0007\u0001\u0007\u0001\u0007\u0001\b"+
		"\u0001\b\u0005\b\u008c\b\b\n\b\f\b\u008f\t\b\u0003\b\u0091\b\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u009d\b\t\u0001\n\u0001\n\u0003\n\u00a1\b\n\u0001\n\u0001\n"+
		"\u0003\n\u00a5\b\n\u0001\n\u0005\n\u00a8\b\n\n\n\f\n\u00ab\t\n\u0001\n"+
		"\u0005\n\u00ae\b\n\n\n\f\n\u00b1\t\n\u0003\n\u00b3\b\n\u0001\n\u0003\n"+
		"\u00b6\b\n\u0001\n\u0003\n\u00b9\b\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00c0\b\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00c4\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00c9"+
		"\b\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u00ce\b\f\u0001\f\u0001\f\u0005"+
		"\f\u00d2\b\f\n\f\f\f\u00d5\t\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r"+
		"\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e\u00df\b\u000e\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00e3\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u00e9\b\u000f\u0001\u000f\u0001\u000f\u0005\u000f"+
		"\u00ed\b\u000f\n\u000f\f\u000f\u00f0\t\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0003\u0010\u00f6\b\u0010\u0001\u0010\u0001\u0010\u0005"+
		"\u0010\u00fa\b\u0010\n\u0010\f\u0010\u00fd\t\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u0103\b\u0011\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u0107\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0003\u0013\u010f\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u0117\b\u0014"+
		"\u0001\u0014\u0003\u0014\u011a\b\u0014\u0001\u0014\u0003\u0014\u011d\b"+
		"\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0121\b\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0003\u0017\u012f"+
		"\b\u0017\u0001\u0017\u0003\u0017\u0132\b\u0017\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u0136\b\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u013c\b\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0140\b"+
		"\u0018\n\u0018\f\u0018\u0143\t\u0018\u0003\u0018\u0145\b\u0018\u0001\u0018"+
		"\u0003\u0018\u0148\b\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u014e\b\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0152\b"+
		"\u0019\u0001\u0019\u0003\u0019\u0155\b\u0019\u0001\u0019\u0003\u0019\u0158"+
		"\b\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0003\u001a\u015e"+
		"\b\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0003\u001b\u0164"+
		"\b\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0005\u001c\u016c\b\u001c\n\u001c\f\u001c\u016f\t\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0003\u001c\u017a\b\u001c\u0001\u001d\u0001\u001d"+
		"\u0003\u001d\u017e\b\u001d\u0001\u001d\u0003\u001d\u0181\b\u001d\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u0185\b\u001e\u0001\u001e\u0005\u001e\u0188"+
		"\b\u001e\n\u001e\f\u001e\u018b\t\u001e\u0001\u001e\u0003\u001e\u018e\b"+
		"\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0003"+
		" \u0196\b \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0003"+
		"!\u01a0\b!\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001$\u0001"+
		"$\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0001(\u0001"+
		"(\u0001(\u0001(\u0001(\u0001(\u0003(\u01b9\b(\u0001)\u0001)\u0001)\u0000"+
		"\u0000*\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR\u0000\u0001\u0001\u0000"+
		"\u0013\u0014\u01dc\u0000T\u0001\u0000\u0000\u0000\u0002W\u0001\u0000\u0000"+
		"\u0000\u0004[\u0001\u0000\u0000\u0000\u0006]\u0001\u0000\u0000\u0000\b"+
		"f\u0001\u0000\u0000\u0000\nl\u0001\u0000\u0000\u0000\fu\u0001\u0000\u0000"+
		"\u0000\u000e\u007f\u0001\u0000\u0000\u0000\u0010\u0089\u0001\u0000\u0000"+
		"\u0000\u0012\u009c\u0001\u0000\u0000\u0000\u0014\u009e\u0001\u0000\u0000"+
		"\u0000\u0016\u00bc\u0001\u0000\u0000\u0000\u0018\u00ca\u0001\u0000\u0000"+
		"\u0000\u001a\u00da\u0001\u0000\u0000\u0000\u001c\u00dc\u0001\u0000\u0000"+
		"\u0000\u001e\u00e6\u0001\u0000\u0000\u0000 \u00f3\u0001\u0000\u0000\u0000"+
		"\"\u0102\u0001\u0000\u0000\u0000$\u0104\u0001\u0000\u0000\u0000&\u010c"+
		"\u0001\u0000\u0000\u0000(\u0114\u0001\u0000\u0000\u0000*\u0124\u0001\u0000"+
		"\u0000\u0000,\u0128\u0001\u0000\u0000\u0000.\u012c\u0001\u0000\u0000\u0000"+
		"0\u0139\u0001\u0000\u0000\u00002\u014b\u0001\u0000\u0000\u00004\u015b"+
		"\u0001\u0000\u0000\u00006\u0161\u0001\u0000\u0000\u00008\u0167\u0001\u0000"+
		"\u0000\u0000:\u017b\u0001\u0000\u0000\u0000<\u0182\u0001\u0000\u0000\u0000"+
		">\u018f\u0001\u0000\u0000\u0000@\u0195\u0001\u0000\u0000\u0000B\u019f"+
		"\u0001\u0000\u0000\u0000D\u01a1\u0001\u0000\u0000\u0000F\u01a3\u0001\u0000"+
		"\u0000\u0000H\u01a8\u0001\u0000\u0000\u0000J\u01aa\u0001\u0000\u0000\u0000"+
		"L\u01ac\u0001\u0000\u0000\u0000N\u01ae\u0001\u0000\u0000\u0000P\u01b8"+
		"\u0001\u0000\u0000\u0000R\u01ba\u0001\u0000\u0000\u0000TU\u0003\u0002"+
		"\u0001\u0000U\u0001\u0001\u0000\u0000\u0000VX\u0003\u0004\u0002\u0000"+
		"WV\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000"+
		"\u0000YZ\u0003\u0006\u0003\u0000Z\u0003\u0001\u0000\u0000\u0000[\\\u0005"+
		"9\u0000\u0000\\\u0005\u0001\u0000\u0000\u0000]_\u0005\u0001\u0000\u0000"+
		"^`\u0003\b\u0004\u0000_^\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000"+
		"`b\u0001\u0000\u0000\u0000ac\u0003\u0010\b\u0000ba\u0001\u0000\u0000\u0000"+
		"bc\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0005\u0002\u0000"+
		"\u0000e\u0007\u0001\u0000\u0000\u0000fh\u0005\u0003\u0000\u0000gi\u0003"+
		"\n\u0005\u0000hg\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000ij\u0001"+
		"\u0000\u0000\u0000jk\u0005\u0004\u0000\u0000k\t\u0001\u0000\u0000\u0000"+
		"ln\u0005\u0005\u0000\u0000mo\u0003\f\u0006\u0000nm\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000oq\u0001\u0000\u0000\u0000pr\u0003\u000e\u0007"+
		"\u0000qp\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000rs\u0001\u0000"+
		"\u0000\u0000st\u0005\u0006\u0000\u0000t\u000b\u0001\u0000\u0000\u0000"+
		"uv\u0005\u0007\u0000\u0000vz\u0005\b\u0000\u0000wy\u0003<\u001e\u0000"+
		"xw\u0001\u0000\u0000\u0000y|\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000"+
		"\u0000z{\u0001\u0000\u0000\u0000{}\u0001\u0000\u0000\u0000|z\u0001\u0000"+
		"\u0000\u0000}~\u0005\t\u0000\u0000~\r\u0001\u0000\u0000\u0000\u007f\u0080"+
		"\u0005C\u0000\u0000\u0080\u0084\u0005\b\u0000\u0000\u0081\u0083\u0003"+
		"<\u001e\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000"+
		"\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0087\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000"+
		"\u0000\u0000\u0087\u0088\u0005\t\u0000\u0000\u0088\u000f\u0001\u0000\u0000"+
		"\u0000\u0089\u0090\u0005\n\u0000\u0000\u008a\u008c\u0003\u0012\t\u0000"+
		"\u008b\u008a\u0001\u0000\u0000\u0000\u008c\u008f\u0001\u0000\u0000\u0000"+
		"\u008d\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000"+
		"\u008e\u0091\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000"+
		"\u0090\u008d\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000"+
		"\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u000b\u0000\u0000"+
		"\u0093\u0011\u0001\u0000\u0000\u0000\u0094\u009d\u0003\u0014\n\u0000\u0095"+
		"\u009d\u0003\u0016\u000b\u0000\u0096\u009d\u0003\u0018\f\u0000\u0097\u009d"+
		"\u0003\u001e\u000f\u0000\u0098\u009d\u0003(\u0014\u0000\u0099\u009d\u0003"+
		".\u0017\u0000\u009a\u009d\u00030\u0018\u0000\u009b\u009d\u00038\u001c"+
		"\u0000\u009c\u0094\u0001\u0000\u0000\u0000\u009c\u0095\u0001\u0000\u0000"+
		"\u0000\u009c\u0096\u0001\u0000\u0000\u0000\u009c\u0097\u0001\u0000\u0000"+
		"\u0000\u009c\u0098\u0001\u0000\u0000\u0000\u009c\u0099\u0001\u0000\u0000"+
		"\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009b\u0001\u0000\u0000"+
		"\u0000\u009d\u0013\u0001\u0000\u0000\u0000\u009e\u00a0\u0005\f\u0000\u0000"+
		"\u009f\u00a1\u0003:\u001d\u0000\u00a0\u009f\u0001\u0000\u0000\u0000\u00a0"+
		"\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2"+
		"\u00a4\u0005\r\u0000\u0000\u00a3\u00a5\u00034\u001a\u0000\u00a4\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a8\u0003(\u0014\u0000\u00a7\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a8\u00ab\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00b2\u0001"+
		"\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ac\u00ae\u0003"+
		"2\u0019\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ae\u00b1\u0001\u0000"+
		"\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000"+
		"\u0000\u0000\u00b0\u00b3\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000"+
		"\u0000\u0000\u00b2\u00af\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000"+
		"\u0000\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u00b6\u0003B!\u0000"+
		"\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b8\u0001\u0000\u0000\u0000\u00b7\u00b9\u00036\u001b\u0000\u00b8"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9"+
		"\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\u000e\u0000\u0000\u00bb"+
		"\u0015\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\u000f\u0000\u0000\u00bd"+
		"\u00bf\u0003H$\u0000\u00be\u00c0\u0003:\u001d\u0000\u00bf\u00be\u0001"+
		"\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c3\u0005\r\u0000\u0000\u00c2\u00c4\u0003B"+
		"!\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005\u0010\u0000"+
		"\u0000\u00c6\u00c8\u0003H$\u0000\u00c7\u00c9\u0005\r\u0000\u0000\u00c8"+
		"\u00c7\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9"+
		"\u0017\u0001\u0000\u0000\u0000\u00ca\u00cb\u0005\u0011\u0000\u0000\u00cb"+
		"\u00cd\u0003\u001a\r\u0000\u00cc\u00ce\u0003:\u001d\u0000\u00cd\u00cc"+
		"\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cf"+
		"\u0001\u0000\u0000\u0000\u00cf\u00d3\u0005\r\u0000\u0000\u00d0\u00d2\u0003"+
		"\u001c\u000e\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d6\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001"+
		"\u0000\u0000\u0000\u00d6\u00d7\u0005\u0012\u0000\u0000\u00d7\u00d8\u0003"+
		"\u001a\r\u0000\u00d8\u00d9\u0005\r\u0000\u0000\u00d9\u0019\u0001\u0000"+
		"\u0000\u0000\u00da\u00db\u0007\u0000\u0000\u0000\u00db\u001b\u0001\u0000"+
		"\u0000\u0000\u00dc\u00de\u0005\u0015\u0000\u0000\u00dd\u00df\u0003:\u001d"+
		"\u0000\u00de\u00dd\u0001\u0000\u0000\u0000\u00de\u00df\u0001\u0000\u0000"+
		"\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00e2\u0005\r\u0000\u0000"+
		"\u00e1\u00e3\u0003B!\u0000\u00e2\u00e1\u0001\u0000\u0000\u0000\u00e2\u00e3"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e5"+
		"\u0005\u0016\u0000\u0000\u00e5\u001d\u0001\u0000\u0000\u0000\u00e6\u00e8"+
		"\u0005\u0017\u0000\u0000\u00e7\u00e9\u0003:\u001d\u0000\u00e8\u00e7\u0001"+
		"\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001"+
		"\u0000\u0000\u0000\u00ea\u00ee\u0005\r\u0000\u0000\u00eb\u00ed\u0003 "+
		"\u0010\u0000\u00ec\u00eb\u0001\u0000\u0000\u0000\u00ed\u00f0\u0001\u0000"+
		"\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000"+
		"\u0000\u0000\u00ef\u00f1\u0001\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000"+
		"\u0000\u0000\u00f1\u00f2\u0005\u0018\u0000\u0000\u00f2\u001f\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f5\u0005\u0019\u0000\u0000\u00f4\u00f6\u0003:\u001d"+
		"\u0000\u00f5\u00f4\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u00fb\u0005\r\u0000\u0000"+
		"\u00f8\u00fa\u0003\"\u0011\u0000\u00f9\u00f8\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fd\u0001\u0000\u0000\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fb"+
		"\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fe\u0001\u0000\u0000\u0000\u00fd"+
		"\u00fb\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005\u001a\u0000\u0000\u00ff"+
		"!\u0001\u0000\u0000\u0000\u0100\u0103\u0003$\u0012\u0000\u0101\u0103\u0003"+
		"&\u0013\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0102\u0101\u0001\u0000"+
		"\u0000\u0000\u0103#\u0001\u0000\u0000\u0000\u0104\u0106\u0005\u001b\u0000"+
		"\u0000\u0105\u0107\u0003:\u001d\u0000\u0106\u0105\u0001\u0000\u0000\u0000"+
		"\u0106\u0107\u0001\u0000\u0000\u0000\u0107\u0108\u0001\u0000\u0000\u0000"+
		"\u0108\u0109\u0005\r\u0000\u0000\u0109\u010a\u0003B!\u0000\u010a\u010b"+
		"\u0005\u001c\u0000\u0000\u010b%\u0001\u0000\u0000\u0000\u010c\u010e\u0005"+
		"\u001d\u0000\u0000\u010d\u010f\u0003:\u001d\u0000\u010e\u010d\u0001\u0000"+
		"\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0110\u0001\u0000"+
		"\u0000\u0000\u0110\u0111\u0005\r\u0000\u0000\u0111\u0112\u0003B!\u0000"+
		"\u0112\u0113\u0005\u001e\u0000\u0000\u0113\'\u0001\u0000\u0000\u0000\u0114"+
		"\u0116\u0005\u001f\u0000\u0000\u0115\u0117\u0003*\u0015\u0000\u0116\u0115"+
		"\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0119"+
		"\u0001\u0000\u0000\u0000\u0118\u011a\u0003:\u001d\u0000\u0119\u0118\u0001"+
		"\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000\u011a\u011c\u0001"+
		"\u0000\u0000\u0000\u011b\u011d\u0003,\u0016\u0000\u011c\u011b\u0001\u0000"+
		"\u0000\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d\u011e\u0001\u0000"+
		"\u0000\u0000\u011e\u0120\u0005\r\u0000\u0000\u011f\u0121\u0003B!\u0000"+
		"\u0120\u011f\u0001\u0000\u0000\u0000\u0120\u0121\u0001\u0000\u0000\u0000"+
		"\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0123\u0005 \u0000\u0000\u0123"+
		")\u0001\u0000\u0000\u0000\u0124\u0125\u0005!\u0000\u0000\u0125\u0126\u0005"+
		"\"\u0000\u0000\u0126\u0127\u0003B!\u0000\u0127+\u0001\u0000\u0000\u0000"+
		"\u0128\u0129\u0005#\u0000\u0000\u0129\u012a\u0005\"\u0000\u0000\u012a"+
		"\u012b\u0003B!\u0000\u012b-\u0001\u0000\u0000\u0000\u012c\u012e\u0005"+
		"$\u0000\u0000\u012d\u012f\u0003*\u0015\u0000\u012e\u012d\u0001\u0000\u0000"+
		"\u0000\u012e\u012f\u0001\u0000\u0000\u0000\u012f\u0131\u0001\u0000\u0000"+
		"\u0000\u0130\u0132\u0003:\u001d\u0000\u0131\u0130\u0001\u0000\u0000\u0000"+
		"\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0133\u0001\u0000\u0000\u0000"+
		"\u0133\u0135\u0005\r\u0000\u0000\u0134\u0136\u0003B!\u0000\u0135\u0134"+
		"\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0137"+
		"\u0001\u0000\u0000\u0000\u0137\u0138\u0005%\u0000\u0000\u0138/\u0001\u0000"+
		"\u0000\u0000\u0139\u013b\u0005&\u0000\u0000\u013a\u013c\u0003:\u001d\u0000"+
		"\u013b\u013a\u0001\u0000\u0000\u0000\u013b\u013c\u0001\u0000\u0000\u0000"+
		"\u013c\u013d\u0001\u0000\u0000\u0000\u013d\u0144\u0005\r\u0000\u0000\u013e"+
		"\u0140\u0003\u0012\t\u0000\u013f\u013e\u0001\u0000\u0000\u0000\u0140\u0143"+
		"\u0001\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000\u0000\u0141\u0142"+
		"\u0001\u0000\u0000\u0000\u0142\u0145\u0001\u0000\u0000\u0000\u0143\u0141"+
		"\u0001\u0000\u0000\u0000\u0144\u0141\u0001\u0000\u0000\u0000\u0144\u0145"+
		"\u0001\u0000\u0000\u0000\u0145\u0147\u0001\u0000\u0000\u0000\u0146\u0148"+
		"\u0003B!\u0000\u0147\u0146\u0001\u0000\u0000\u0000\u0147\u0148\u0001\u0000"+
		"\u0000\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149\u014a\u0005\'\u0000"+
		"\u0000\u014a1\u0001\u0000\u0000\u0000\u014b\u014d\u0005(\u0000\u0000\u014c"+
		"\u014e\u0003:\u001d\u0000\u014d\u014c\u0001\u0000\u0000\u0000\u014d\u014e"+
		"\u0001\u0000\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f\u0151"+
		"\u0005\r\u0000\u0000\u0150\u0152\u00034\u001a\u0000\u0151\u0150\u0001"+
		"\u0000\u0000\u0000\u0151\u0152\u0001\u0000\u0000\u0000\u0152\u0154\u0001"+
		"\u0000\u0000\u0000\u0153\u0155\u0003B!\u0000\u0154\u0153\u0001\u0000\u0000"+
		"\u0000\u0154\u0155\u0001\u0000\u0000\u0000\u0155\u0157\u0001\u0000\u0000"+
		"\u0000\u0156\u0158\u00036\u001b\u0000\u0157\u0156\u0001\u0000\u0000\u0000"+
		"\u0157\u0158\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000"+
		"\u0159\u015a\u0005)\u0000\u0000\u015a3\u0001\u0000\u0000\u0000\u015b\u015d"+
		"\u0005*\u0000\u0000\u015c\u015e\u0003:\u001d\u0000\u015d\u015c\u0001\u0000"+
		"\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u015f\u0001\u0000"+
		"\u0000\u0000\u015f\u0160\u0005\r\u0000\u0000\u01605\u0001\u0000\u0000"+
		"\u0000\u0161\u0163\u0005*\u0000\u0000\u0162\u0164\u0003:\u001d\u0000\u0163"+
		"\u0162\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000\u0000\u0164"+
		"\u0165\u0001\u0000\u0000\u0000\u0165\u0166\u0005\r\u0000\u0000\u01667"+
		"\u0001\u0000\u0000\u0000\u0167\u0168\u0005+\u0000\u0000\u0168\u0169\u0005"+
		"C\u0000\u0000\u0169\u016d\u0005\b\u0000\u0000\u016a\u016c\u0003\u0012"+
		"\t\u0000\u016b\u016a\u0001\u0000\u0000\u0000\u016c\u016f\u0001\u0000\u0000"+
		"\u0000\u016d\u016b\u0001\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000"+
		"\u0000\u016e\u0170\u0001\u0000\u0000\u0000\u016f\u016d\u0001\u0000\u0000"+
		"\u0000\u0170\u0171\u0005\t\u0000\u0000\u0171\u0172\u0005,\u0000\u0000"+
		"\u0172\u0179\u0005C\u0000\u0000\u0173\u0174\u0005H\u0000\u0000\u0174\u0175"+
		"\u0003H$\u0000\u0175\u0176\u0003L&\u0000\u0176\u0177\u0003H$\u0000\u0177"+
		"\u0178\u0003L&\u0000\u0178\u017a\u0001\u0000\u0000\u0000\u0179\u0173\u0001"+
		"\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a9\u0001\u0000"+
		"\u0000\u0000\u017b\u017d\u0005-\u0000\u0000\u017c\u017e\u0005=\u0000\u0000"+
		"\u017d\u017c\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000\u0000"+
		"\u017e\u0180\u0001\u0000\u0000\u0000\u017f\u0181\u0003<\u001e\u0000\u0180"+
		"\u017f\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000\u0181"+
		";\u0001\u0000\u0000\u0000\u0182\u0189\u0003>\u001f\u0000\u0183\u0185\u0005"+
		"1\u0000\u0000\u0184\u0183\u0001\u0000\u0000\u0000\u0184\u0185\u0001\u0000"+
		"\u0000\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u0186\u0188\u0003>\u001f"+
		"\u0000\u0187\u0184\u0001\u0000\u0000\u0000\u0188\u018b\u0001\u0000\u0000"+
		"\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189\u018a\u0001\u0000\u0000"+
		"\u0000\u018a\u018d\u0001\u0000\u0000\u0000\u018b\u0189\u0001\u0000\u0000"+
		"\u0000\u018c\u018e\u00051\u0000\u0000\u018d\u018c\u0001\u0000\u0000\u0000"+
		"\u018d\u018e\u0001\u0000\u0000\u0000\u018e=\u0001\u0000\u0000\u0000\u018f"+
		"\u0190\u0003@ \u0000\u0190\u0191\u00050\u0000\u0000\u0191\u0192\u0003"+
		"B!\u0000\u0192?\u0001\u0000\u0000\u0000\u0193\u0196\u0005C\u0000\u0000"+
		"\u0194\u0196\u0003D\"\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0195"+
		"\u0194\u0001\u0000\u0000\u0000\u0196A\u0001\u0000\u0000\u0000\u0197\u01a0"+
		"\u0005C\u0000\u0000\u0198\u01a0\u0003F#\u0000\u0199\u01a0\u0005D\u0000"+
		"\u0000\u019a\u01a0\u0005E\u0000\u0000\u019b\u01a0\u0005I\u0000\u0000\u019c"+
		"\u01a0\u0003D\"\u0000\u019d\u01a0\u0003N\'\u0000\u019e\u01a0\u0003H$\u0000"+
		"\u019f\u0197\u0001\u0000\u0000\u0000\u019f\u0198\u0001\u0000\u0000\u0000"+
		"\u019f\u0199\u0001\u0000\u0000\u0000\u019f\u019a\u0001\u0000\u0000\u0000"+
		"\u019f\u019b\u0001\u0000\u0000\u0000\u019f\u019c\u0001\u0000\u0000\u0000"+
		"\u019f\u019d\u0001\u0000\u0000\u0000\u019f\u019e\u0001\u0000\u0000\u0000"+
		"\u01a0C\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005=\u0000\u0000\u01a2E"+
		"\u0001\u0000\u0000\u0000\u01a3\u01a4\u0005:\u0000\u0000\u01a4\u01a5\u0005"+
		":\u0000\u0000\u01a5\u01a6\u0005:\u0000\u0000\u01a6\u01a7\u0005:\u0000"+
		"\u0000\u01a7G\u0001\u0000\u0000\u0000\u01a8\u01a9\u0005;\u0000\u0000\u01a9"+
		"I\u0001\u0000\u0000\u0000\u01aa\u01ab\u0005>\u0000\u0000\u01abK\u0001"+
		"\u0000\u0000\u0000\u01ac\u01ad\u0005:\u0000\u0000\u01adM\u0001\u0000\u0000"+
		"\u0000\u01ae\u01af\u0005.\u0000\u0000\u01af\u01b0\u0005C\u0000\u0000\u01b0"+
		"\u01b1\u0005\t\u0000\u0000\u01b1O\u0001\u0000\u0000\u0000\u01b2\u01b3"+
		"\u0005/\u0000\u0000\u01b3\u01b9\u0005J\u0000\u0000\u01b4\u01b9\u0005A"+
		"\u0000\u0000\u01b5\u01b9\u0005@\u0000\u0000\u01b6\u01b9\u0005?\u0000\u0000"+
		"\u01b7\u01b9\u0005B\u0000\u0000\u01b8\u01b2\u0001\u0000\u0000\u0000\u01b8"+
		"\u01b4\u0001\u0000\u0000\u0000\u01b8\u01b5\u0001\u0000\u0000\u0000\u01b8"+
		"\u01b6\u0001\u0000\u0000\u0000\u01b8\u01b7\u0001\u0000\u0000\u0000\u01b9"+
		"Q\u0001\u0000\u0000\u0000\u01ba\u01bb\u0005:\u0000\u0000\u01bbS\u0001"+
		"\u0000\u0000\u0000;W_bhnqz\u0084\u008d\u0090\u009c\u00a0\u00a4\u00a9\u00af"+
		"\u00b2\u00b5\u00b8\u00bf\u00c3\u00c8\u00cd\u00d3\u00de\u00e2\u00e8\u00ee"+
		"\u00f5\u00fb\u0102\u0106\u010e\u0116\u0119\u011c\u0120\u012e\u0131\u0135"+
		"\u013b\u0141\u0144\u0147\u014d\u0151\u0154\u0157\u015d\u0163\u016d\u0179"+
		"\u017d\u0180\u0184\u0189\u018d\u0195\u019f\u01b8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}