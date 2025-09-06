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
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, COLON=62, SEMICOLON=63, DEFAULT=64, EXECUTIVE=65, 
		LEDGER=66, LEGAL=67, LETTER=68, TABLOID=69, AUTO=70, DOCTYPE=71, NUMBERUNIT=72, 
		NUMBER=73, UNIT=74, STRING=75, BORDERTYPE=76, CMYK_PERCENT=77, CMYK_COLOR=78, 
		RGB_COLOR=79, COLORENUM=80, IDENTIFIER=81, WS=82, COMMENT=83, HEX_HEX=84;
	public static final int
		RULE_document = 0, RULE_doc = 1, RULE_docType = 2, RULE_html = 3, RULE_body = 4, 
		RULE_element = 5, RULE_comboBoxField = 6, RULE_checkbox = 7, RULE_checkboxStatus = 8, 
		RULE_areaBreak = 9, RULE_button = 10, RULE_paragraph = 11, RULE_heading = 12, 
		RULE_list = 13, RULE_listItem = 14, RULE_listType = 15, RULE_table = 16, 
		RULE_row = 17, RULE_col = 18, RULE_th = 19, RULE_td = 20, RULE_elemValue = 21, 
		RULE_image = 22, RULE_src = 23, RULE_alt = 24, RULE_svg = 25, RULE_div = 26, 
		RULE_htmlPageBreak = 27, RULE_lineSeparator = 28, RULE_tab = 29, RULE_textArea = 30, 
		RULE_tree = 31, RULE_span = 32, RULE_lbr = 33, RULE_rbr = 34, RULE_template = 35, 
		RULE_styleEle = 36, RULE_style = 37, RULE_attr = 38, RULE_key = 39, RULE_value = 40, 
		RULE_string = 41, RULE_marginValue = 42, RULE_number = 43, RULE_borderType = 44, 
		RULE_unit = 45, RULE_variable = 46, RULE_addressOf = 47, RULE_color = 48;
	private static String[] makeRuleNames() {
		return new String[] {
			"document", "doc", "docType", "html", "body", "element", "comboBoxField", 
			"checkbox", "checkboxStatus", "areaBreak", "button", "paragraph", "heading", 
			"list", "listItem", "listType", "table", "row", "col", "th", "td", "elemValue", 
			"image", "src", "alt", "svg", "div", "htmlPageBreak", "lineSeparator", 
			"tab", "textArea", "tree", "span", "lbr", "rbr", "template", "styleEle", 
			"style", "attr", "key", "value", "string", "marginValue", "number", "borderType", 
			"unit", "variable", "addressOf", "color"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'<pdf>'", "'</pdf>'", "'<body>'", "'</body>'", "'<comboBoxField'", 
			"'>'", "'</comboBoxField>'", "'<checkbox'", "'</checkbox>'", "'checked'", 
			"'<areaBreak'", "'</areaBreak>'", "'<button'", "'</button>'", "'<p'", 
			"'</p>'", "'<h'", "'</h'", "'<list'", "'listType='", "'</list>'", "'<li'", 
			"'</li>'", "'<table'", "'</table>'", "'<tr'", "'</tr>'", "'<th'", "'</th>'", 
			"'<td'", "'</td>'", "'<image'", "'</image>'", "'src'", "'='", "'alt'", 
			"'<svg'", "'</svg>'", "'<div'", "'</div>'", "'<htmlPageBreak'", "'</htmlPageBreak>'", 
			"'<lineSeparator'", "'</lineSeparator>'", "'<tab'", "'</tab>'", "'<textArea'", 
			"'</textArea>'", "'<tree'", "'</tree>'", "'<span'", "'</span>'", "'<br'", 
			"'<template>'", "'&'", "'</template>'", "'style='", "'${'", "'}'", "'&{'", 
			"'#'", "':'", "';'", "'DEFAULT'", "'EXECUTIVE'", "'LEDGER'", "'LEGAL'", 
			"'LETTER'", "'TABLOID'", "'auto'", "'<!DOCTYPE html>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "COLON", "SEMICOLON", "DEFAULT", "EXECUTIVE", "LEDGER", "LEGAL", 
			"LETTER", "TABLOID", "AUTO", "DOCTYPE", "NUMBERUNIT", "NUMBER", "UNIT", 
			"STRING", "BORDERTYPE", "CMYK_PERCENT", "CMYK_COLOR", "RGB_COLOR", "COLORENUM", 
			"IDENTIFIER", "WS", "COMMENT", "HEX_HEX"
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
			setState(98);
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
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOCTYPE) {
				{
				setState(100);
				docType();
				}
			}

			setState(103);
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
			setState(105);
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
			setState(107);
			match(T__0);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(108);
				body();
				}
			}

			setState(111);
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
		enterRule(_localctx, 8, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__2);
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 309247132912232736L) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 523L) != 0)) {
					{
					{
					setState(114);
					element();
					}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(122);
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
		public SpanContext span() {
			return getRuleContext(SpanContext.class,0);
		}
		public TemplateContext template() {
			return getRuleContext(TemplateContext.class,0);
		}
		public AreaBreakContext areaBreak() {
			return getRuleContext(AreaBreakContext.class,0);
		}
		public ButtonContext button() {
			return getRuleContext(ButtonContext.class,0);
		}
		public CheckboxContext checkbox() {
			return getRuleContext(CheckboxContext.class,0);
		}
		public ComboBoxFieldContext comboBoxField() {
			return getRuleContext(ComboBoxFieldContext.class,0);
		}
		public HtmlPageBreakContext htmlPageBreak() {
			return getRuleContext(HtmlPageBreakContext.class,0);
		}
		public LineSeparatorContext lineSeparator() {
			return getRuleContext(LineSeparatorContext.class,0);
		}
		public TabContext tab() {
			return getRuleContext(TabContext.class,0);
		}
		public TextAreaContext textArea() {
			return getRuleContext(TextAreaContext.class,0);
		}
		public TreeContext tree() {
			return getRuleContext(TreeContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
		enterRule(_localctx, 10, RULE_element);
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				paragraph();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				heading();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				list();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 4);
				{
				setState(127);
				table();
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 5);
				{
				setState(128);
				image();
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 6);
				{
				setState(129);
				svg();
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 7);
				{
				setState(130);
				div();
				}
				break;
			case T__50:
				enterOuterAlt(_localctx, 8);
				{
				setState(131);
				span();
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 9);
				{
				setState(132);
				template();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 10);
				{
				setState(133);
				areaBreak();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 11);
				{
				setState(134);
				button();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 12);
				{
				setState(135);
				checkbox();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 13);
				{
				setState(136);
				comboBoxField();
				}
				break;
			case T__40:
				enterOuterAlt(_localctx, 14);
				{
				setState(137);
				htmlPageBreak();
				}
				break;
			case T__42:
				enterOuterAlt(_localctx, 15);
				{
				setState(138);
				lineSeparator();
				}
				break;
			case T__44:
				enterOuterAlt(_localctx, 16);
				{
				setState(139);
				tab();
				}
				break;
			case T__46:
				enterOuterAlt(_localctx, 17);
				{
				setState(140);
				textArea();
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 18);
				{
				setState(141);
				tree();
				}
				break;
			case T__57:
			case NUMBERUNIT:
			case NUMBER:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 19);
				{
				setState(142);
				value();
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
	public static class ComboBoxFieldContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public ComboBoxFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comboBoxField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterComboBoxField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitComboBoxField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitComboBoxField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComboBoxFieldContext comboBoxField() throws RecognitionException {
		ComboBoxFieldContext _localctx = new ComboBoxFieldContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_comboBoxField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(T__4);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(146);
				styleEle();
				}
			}

			setState(149);
			match(T__5);
			setState(150);
			value();
			setState(151);
			match(T__6);
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
	public static class CheckboxContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public CheckboxStatusContext checkboxStatus() {
			return getRuleContext(CheckboxStatusContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public CheckboxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkbox; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterCheckbox(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitCheckbox(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitCheckbox(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CheckboxContext checkbox() throws RecognitionException {
		CheckboxContext _localctx = new CheckboxContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_checkbox);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__7);
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(154);
				styleEle();
				}
			}

			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(157);
				checkboxStatus();
				}
			}

			setState(160);
			match(T__5);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & 8568833L) != 0)) {
				{
				setState(161);
				value();
				}
			}

			setState(164);
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
	public static class CheckboxStatusContext extends ParserRuleContext {
		public CheckboxStatusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkboxStatus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterCheckboxStatus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitCheckboxStatus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitCheckboxStatus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CheckboxStatusContext checkboxStatus() throws RecognitionException {
		CheckboxStatusContext _localctx = new CheckboxStatusContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_checkboxStatus);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(T__9);
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
	public static class AreaBreakContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
		public AreaBreakContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_areaBreak; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterAreaBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitAreaBreak(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitAreaBreak(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AreaBreakContext areaBreak() throws RecognitionException {
		AreaBreakContext _localctx = new AreaBreakContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_areaBreak);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__10);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(169);
				styleEle();
				}
			}

			setState(172);
			match(T__5);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(173);
				match(IDENTIFIER);
				}
			}

			setState(176);
			match(T__11);
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
	public static class ButtonContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ButtonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_button; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterButton(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitButton(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitButton(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ButtonContext button() throws RecognitionException {
		ButtonContext _localctx = new ButtonContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_button);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(T__12);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(179);
				styleEle();
				}
			}

			setState(182);
			match(T__5);
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & 8568833L) != 0)) {
				{
				setState(183);
				value();
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
	public static class ParagraphContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public LbrContext lbr() {
			return getRuleContext(LbrContext.class,0);
		}
		public ElemValueContext elemValue() {
			return getRuleContext(ElemValueContext.class,0);
		}
		public RbrContext rbr() {
			return getRuleContext(RbrContext.class,0);
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
		enterRule(_localctx, 22, RULE_paragraph);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__14);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(189);
				styleEle();
				}
			}

			setState(192);
			match(T__5);
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(193);
				lbr();
				}
				break;
			}
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(196);
				elemValue();
				}
				break;
			}
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__52) {
				{
				setState(199);
				rbr();
				}
			}

			setState(202);
			match(T__15);
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
		public ElemValueContext elemValue() {
			return getRuleContext(ElemValueContext.class,0);
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
		enterRule(_localctx, 24, RULE_heading);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(T__16);
			setState(205);
			number();
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(206);
				styleEle();
				}
			}

			setState(209);
			match(T__5);
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(210);
				elemValue();
				}
				break;
			}
			setState(213);
			match(T__17);
			setState(214);
			number();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(215);
				match(T__5);
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
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
		enterRule(_localctx, 26, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(T__18);
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(219);
				styleEle();
				}
			}

			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(222);
				match(T__19);
				setState(223);
				value();
				}
			}

			setState(226);
			match(T__5);
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(227);
				listItem();
				}
				}
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(233);
			match(T__20);
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
		public ElemValueContext elemValue() {
			return getRuleContext(ElemValueContext.class,0);
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
			setState(235);
			match(T__21);
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(236);
				styleEle();
				}
			}

			setState(239);
			match(T__5);
			setState(241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(240);
				elemValue();
				}
				break;
			}
			setState(243);
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
	public static class ListTypeContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public ElemValueContext elemValue() {
			return getRuleContext(ElemValueContext.class,0);
		}
		public ListTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterListType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitListType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitListType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListTypeContext listType() throws RecognitionException {
		ListTypeContext _localctx = new ListTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_listType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(T__21);
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(246);
				styleEle();
				}
			}

			setState(249);
			match(T__5);
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(250);
				elemValue();
				}
				break;
			}
			setState(253);
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
		enterRule(_localctx, 32, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(T__23);
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(256);
				styleEle();
				}
			}

			setState(259);
			match(T__5);
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__25) {
				{
				{
				setState(260);
				row();
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(266);
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
		enterRule(_localctx, 34, RULE_row);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__25);
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(269);
				styleEle();
				}
			}

			setState(272);
			match(T__5);
			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__27 || _la==T__29) {
				{
				{
				setState(273);
				col();
				}
				}
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(279);
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
		enterRule(_localctx, 36, RULE_col);
		try {
			setState(283);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__27:
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				th();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
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
		public ElemValueContext elemValue() {
			return getRuleContext(ElemValueContext.class,0);
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
		enterRule(_localctx, 38, RULE_th);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(T__27);
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(286);
				styleEle();
				}
			}

			setState(289);
			match(T__5);
			setState(290);
			elemValue();
			setState(291);
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
		public ElemValueContext elemValue() {
			return getRuleContext(ElemValueContext.class,0);
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
		enterRule(_localctx, 40, RULE_td);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(T__29);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(294);
				styleEle();
				}
			}

			setState(297);
			match(T__5);
			setState(298);
			elemValue();
			setState(299);
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
	public static class ElemValueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public ElemValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elemValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterElemValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitElemValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitElemValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElemValueContext elemValue() throws RecognitionException {
		ElemValueContext _localctx = new ElemValueContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_elemValue);
		int _la;
		try {
			setState(310);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(301);
				value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(308);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(305);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 309247132912232736L) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 523L) != 0)) {
						{
						{
						setState(302);
						element();
						}
						}
						setState(307);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
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
		enterRule(_localctx, 44, RULE_image);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(T__31);
			setState(314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(313);
				src();
				}
			}

			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(316);
				styleEle();
				}
			}

			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__35) {
				{
				setState(319);
				alt();
				}
			}

			setState(322);
			match(T__5);
			setState(324);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & 8568833L) != 0)) {
				{
				setState(323);
				value();
				}
			}

			setState(326);
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
		enterRule(_localctx, 46, RULE_src);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(T__33);
			setState(329);
			match(T__34);
			setState(330);
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
		enterRule(_localctx, 48, RULE_alt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			match(T__35);
			setState(333);
			match(T__34);
			setState(334);
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
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public AddressOfContext addressOf() {
			return getRuleContext(AddressOfContext.class,0);
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
		enterRule(_localctx, 50, RULE_svg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(T__36);
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(337);
				styleEle();
				}
			}

			setState(340);
			match(T__5);
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__57) {
				{
				setState(341);
				variable();
				}
			}

			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__59) {
				{
				setState(344);
				addressOf();
				}
			}

			setState(347);
			match(T__37);
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
		enterRule(_localctx, 52, RULE_div);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			match(T__38);
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(350);
				styleEle();
				}
			}

			setState(353);
			match(T__5);
			setState(360);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(357);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(354);
						element();
						}
						} 
					}
					setState(359);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				}
				}
				break;
			}
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & 8568833L) != 0)) {
				{
				setState(362);
				value();
				}
			}

			setState(365);
			match(T__39);
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
	public static class HtmlPageBreakContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public HtmlPageBreakContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_htmlPageBreak; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterHtmlPageBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitHtmlPageBreak(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitHtmlPageBreak(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HtmlPageBreakContext htmlPageBreak() throws RecognitionException {
		HtmlPageBreakContext _localctx = new HtmlPageBreakContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_htmlPageBreak);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			match(T__40);
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(368);
				styleEle();
				}
			}

			setState(371);
			match(T__5);
			setState(372);
			match(IDENTIFIER);
			setState(373);
			match(T__41);
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
	public static class LineSeparatorContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public LineSeparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lineSeparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterLineSeparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitLineSeparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitLineSeparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineSeparatorContext lineSeparator() throws RecognitionException {
		LineSeparatorContext _localctx = new LineSeparatorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_lineSeparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(T__42);
			setState(377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(376);
				styleEle();
				}
			}

			setState(379);
			match(T__5);
			setState(380);
			match(IDENTIFIER);
			setState(381);
			match(T__43);
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
	public static class TabContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public TabContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tab; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTab(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTab(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTab(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TabContext tab() throws RecognitionException {
		TabContext _localctx = new TabContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_tab);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			match(T__44);
			setState(385);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(384);
				styleEle();
				}
			}

			setState(387);
			match(T__5);
			setState(388);
			match(T__45);
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
	public static class TextAreaContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TextAreaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textArea; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTextArea(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTextArea(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTextArea(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextAreaContext textArea() throws RecognitionException {
		TextAreaContext _localctx = new TextAreaContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_textArea);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			match(T__46);
			setState(392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(391);
				styleEle();
				}
			}

			setState(394);
			match(T__5);
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & 8568833L) != 0)) {
				{
				setState(395);
				value();
				}
			}

			setState(398);
			match(T__47);
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
	public static class TreeContext extends ParserRuleContext {
		public StyleEleContext styleEle() {
			return getRuleContext(StyleEleContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public AddressOfContext addressOf() {
			return getRuleContext(AddressOfContext.class,0);
		}
		public TreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TreeContext tree() throws RecognitionException {
		TreeContext _localctx = new TreeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_tree);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(T__48);
			setState(402);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(401);
				styleEle();
				}
			}

			setState(404);
			match(T__5);
			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__57) {
				{
				setState(405);
				variable();
				}
			}

			setState(409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__59) {
				{
				setState(408);
				addressOf();
				}
			}

			setState(411);
			match(T__49);
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
		enterRule(_localctx, 64, RULE_span);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			match(T__50);
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(414);
				styleEle();
				}
			}

			setState(417);
			match(T__5);
			setState(419);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(418);
				lbr();
				}
				break;
			}
			setState(422);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & 8568833L) != 0)) {
				{
				setState(421);
				value();
				}
			}

			setState(425);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__52) {
				{
				setState(424);
				rbr();
				}
			}

			setState(427);
			match(T__51);
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
		enterRule(_localctx, 66, RULE_lbr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			match(T__52);
			setState(431);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(430);
				styleEle();
				}
			}

			setState(433);
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
		enterRule(_localctx, 68, RULE_rbr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(T__52);
			setState(437);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(436);
				styleEle();
				}
			}

			setState(439);
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
	public static class TemplateContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 70, RULE_template);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			match(T__53);
			setState(442);
			match(T__54);
			setState(443);
			match(IDENTIFIER);
			setState(444);
			match(T__55);
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
		enterRule(_localctx, 72, RULE_styleEle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			match(T__56);
			setState(448);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				setState(447);
				match(STRING);
				}
				break;
			}
			setState(451);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING || _la==IDENTIFIER) {
				{
				setState(450);
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
		enterRule(_localctx, 74, RULE_style);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			attr();
			setState(460);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(455);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SEMICOLON) {
						{
						setState(454);
						match(SEMICOLON);
						}
					}

					setState(457);
					attr();
					}
					} 
				}
				setState(462);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
			}
			setState(464);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(463);
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
		enterRule(_localctx, 76, RULE_attr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			key();
			setState(467);
			match(COLON);
			setState(468);
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
		enterRule(_localctx, 78, RULE_key);
		try {
			setState(472);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(470);
				match(IDENTIFIER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(471);
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
		public MarginValueContext marginValue() {
			return getRuleContext(MarginValueContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 80, RULE_value);
		try {
			setState(479);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBERUNIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(474);
				marginValue();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(475);
				match(IDENTIFIER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(476);
				string();
				}
				break;
			case T__57:
				enterOuterAlt(_localctx, 4);
				{
				setState(477);
				variable();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 5);
				{
				setState(478);
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
		enterRule(_localctx, 82, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
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
		enterRule(_localctx, 84, RULE_marginValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			match(NUMBERUNIT);
			setState(484);
			match(NUMBERUNIT);
			setState(485);
			match(NUMBERUNIT);
			setState(486);
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
		enterRule(_localctx, 86, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
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
		enterRule(_localctx, 88, RULE_borderType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
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
		enterRule(_localctx, 90, RULE_unit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
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
		enterRule(_localctx, 92, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			match(T__57);
			setState(495);
			match(IDENTIFIER);
			setState(496);
			match(T__58);
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
	public static class AddressOfContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickPDFParser.IDENTIFIER, 0); }
		public AddressOfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addressOf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterAddressOf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitAddressOf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitAddressOf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddressOfContext addressOf() throws RecognitionException {
		AddressOfContext _localctx = new AddressOfContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_addressOf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498);
			match(T__59);
			setState(499);
			match(IDENTIFIER);
			setState(500);
			match(T__58);
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
		enterRule(_localctx, 96, RULE_color);
		try {
			setState(508);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__60:
				enterOuterAlt(_localctx, 1);
				{
				setState(502);
				match(T__60);
				setState(503);
				match(HEX_HEX);
				}
				break;
			case RGB_COLOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(504);
				match(RGB_COLOR);
				}
				break;
			case CMYK_COLOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(505);
				match(CMYK_COLOR);
				}
				break;
			case CMYK_PERCENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(506);
				match(CMYK_PERCENT);
				}
				break;
			case COLORENUM:
				enterOuterAlt(_localctx, 5);
				{
				setState(507);
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

	public static final String _serializedATN =
		"\u0004\u0001T\u01ff\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0003\u0001f\b\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0003\u0003n\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0005\u0004t\b\u0004\n\u0004\f\u0004"+
		"w\t\u0004\u0003\u0004y\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005\u0090\b\u0005\u0001\u0006\u0001\u0006\u0003\u0006\u0094\b\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u009c\b\u0007\u0001\u0007\u0003\u0007\u009f\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u00a3\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0003\t\u00ab\b\t\u0001\t\u0001\t\u0003\t\u00af"+
		"\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0003\n\u00b5\b\n\u0001\n\u0001\n"+
		"\u0003\n\u00b9\b\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u00bf\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00c3\b\u000b\u0001"+
		"\u000b\u0003\u000b\u00c6\b\u000b\u0001\u000b\u0003\u000b\u00c9\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u00d0\b\f\u0001"+
		"\f\u0001\f\u0003\f\u00d4\b\f\u0001\f\u0001\f\u0001\f\u0003\f\u00d9\b\f"+
		"\u0001\r\u0001\r\u0003\r\u00dd\b\r\u0001\r\u0001\r\u0003\r\u00e1\b\r\u0001"+
		"\r\u0001\r\u0005\r\u00e5\b\r\n\r\f\r\u00e8\t\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00ee\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e"+
		"\u00f2\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u00f8\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00fc\b\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u0102\b\u0010\u0001"+
		"\u0010\u0001\u0010\u0005\u0010\u0106\b\u0010\n\u0010\f\u0010\u0109\t\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0003\u0011\u010f\b\u0011"+
		"\u0001\u0011\u0001\u0011\u0005\u0011\u0113\b\u0011\n\u0011\f\u0011\u0116"+
		"\t\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0003\u0012\u011c"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0003\u0013\u0120\b\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u0128\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0005\u0015\u0130\b\u0015\n\u0015\f\u0015\u0133\t\u0015\u0003"+
		"\u0015\u0135\b\u0015\u0003\u0015\u0137\b\u0015\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u013b\b\u0016\u0001\u0016\u0003\u0016\u013e\b\u0016\u0001"+
		"\u0016\u0003\u0016\u0141\b\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0145"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0003\u0019\u0153\b\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0157"+
		"\b\u0019\u0001\u0019\u0003\u0019\u015a\b\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u001a\u0001\u001a\u0003\u001a\u0160\b\u001a\u0001\u001a\u0001\u001a"+
		"\u0005\u001a\u0164\b\u001a\n\u001a\f\u001a\u0167\t\u001a\u0003\u001a\u0169"+
		"\b\u001a\u0001\u001a\u0003\u001a\u016c\b\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0003\u001b\u0172\b\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0003\u001c\u017a\b\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0003\u001d\u0182\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e"+
		"\u0001\u001e\u0003\u001e\u0189\b\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u018d\b\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0003\u001f"+
		"\u0193\b\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0197\b\u001f\u0001"+
		"\u001f\u0003\u001f\u019a\b\u001f\u0001\u001f\u0001\u001f\u0001 \u0001"+
		" \u0003 \u01a0\b \u0001 \u0001 \u0003 \u01a4\b \u0001 \u0003 \u01a7\b"+
		" \u0001 \u0003 \u01aa\b \u0001 \u0001 \u0001!\u0001!\u0003!\u01b0\b!\u0001"+
		"!\u0001!\u0001\"\u0001\"\u0003\"\u01b6\b\"\u0001\"\u0001\"\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001$\u0001$\u0003$\u01c1\b$\u0001$\u0003$\u01c4"+
		"\b$\u0001%\u0001%\u0003%\u01c8\b%\u0001%\u0005%\u01cb\b%\n%\f%\u01ce\t"+
		"%\u0001%\u0003%\u01d1\b%\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0003"+
		"\'\u01d9\b\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0003(\u01e0\b(\u0001"+
		")\u0001)\u0001*\u0001*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001,\u0001"+
		",\u0001-\u0001-\u0001.\u0001.\u0001.\u0001.\u0001/\u0001/\u0001/\u0001"+
		"/\u00010\u00010\u00010\u00010\u00010\u00010\u00030\u01fd\b0\u00010\u0000"+
		"\u00001\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`\u0000\u0000\u022a"+
		"\u0000b\u0001\u0000\u0000\u0000\u0002e\u0001\u0000\u0000\u0000\u0004i"+
		"\u0001\u0000\u0000\u0000\u0006k\u0001\u0000\u0000\u0000\bq\u0001\u0000"+
		"\u0000\u0000\n\u008f\u0001\u0000\u0000\u0000\f\u0091\u0001\u0000\u0000"+
		"\u0000\u000e\u0099\u0001\u0000\u0000\u0000\u0010\u00a6\u0001\u0000\u0000"+
		"\u0000\u0012\u00a8\u0001\u0000\u0000\u0000\u0014\u00b2\u0001\u0000\u0000"+
		"\u0000\u0016\u00bc\u0001\u0000\u0000\u0000\u0018\u00cc\u0001\u0000\u0000"+
		"\u0000\u001a\u00da\u0001\u0000\u0000\u0000\u001c\u00eb\u0001\u0000\u0000"+
		"\u0000\u001e\u00f5\u0001\u0000\u0000\u0000 \u00ff\u0001\u0000\u0000\u0000"+
		"\"\u010c\u0001\u0000\u0000\u0000$\u011b\u0001\u0000\u0000\u0000&\u011d"+
		"\u0001\u0000\u0000\u0000(\u0125\u0001\u0000\u0000\u0000*\u0136\u0001\u0000"+
		"\u0000\u0000,\u0138\u0001\u0000\u0000\u0000.\u0148\u0001\u0000\u0000\u0000"+
		"0\u014c\u0001\u0000\u0000\u00002\u0150\u0001\u0000\u0000\u00004\u015d"+
		"\u0001\u0000\u0000\u00006\u016f\u0001\u0000\u0000\u00008\u0177\u0001\u0000"+
		"\u0000\u0000:\u017f\u0001\u0000\u0000\u0000<\u0186\u0001\u0000\u0000\u0000"+
		">\u0190\u0001\u0000\u0000\u0000@\u019d\u0001\u0000\u0000\u0000B\u01ad"+
		"\u0001\u0000\u0000\u0000D\u01b3\u0001\u0000\u0000\u0000F\u01b9\u0001\u0000"+
		"\u0000\u0000H\u01be\u0001\u0000\u0000\u0000J\u01c5\u0001\u0000\u0000\u0000"+
		"L\u01d2\u0001\u0000\u0000\u0000N\u01d8\u0001\u0000\u0000\u0000P\u01df"+
		"\u0001\u0000\u0000\u0000R\u01e1\u0001\u0000\u0000\u0000T\u01e3\u0001\u0000"+
		"\u0000\u0000V\u01e8\u0001\u0000\u0000\u0000X\u01ea\u0001\u0000\u0000\u0000"+
		"Z\u01ec\u0001\u0000\u0000\u0000\\\u01ee\u0001\u0000\u0000\u0000^\u01f2"+
		"\u0001\u0000\u0000\u0000`\u01fc\u0001\u0000\u0000\u0000bc\u0003\u0002"+
		"\u0001\u0000c\u0001\u0001\u0000\u0000\u0000df\u0003\u0004\u0002\u0000"+
		"ed\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000"+
		"\u0000gh\u0003\u0006\u0003\u0000h\u0003\u0001\u0000\u0000\u0000ij\u0005"+
		"G\u0000\u0000j\u0005\u0001\u0000\u0000\u0000km\u0005\u0001\u0000\u0000"+
		"ln\u0003\b\u0004\u0000ml\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000op\u0005\u0002\u0000\u0000p\u0007\u0001\u0000"+
		"\u0000\u0000qx\u0005\u0003\u0000\u0000rt\u0003\n\u0005\u0000sr\u0001\u0000"+
		"\u0000\u0000tw\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001"+
		"\u0000\u0000\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000"+
		"xu\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000"+
		"\u0000z{\u0005\u0004\u0000\u0000{\t\u0001\u0000\u0000\u0000|\u0090\u0003"+
		"\u0016\u000b\u0000}\u0090\u0003\u0018\f\u0000~\u0090\u0003\u001a\r\u0000"+
		"\u007f\u0090\u0003 \u0010\u0000\u0080\u0090\u0003,\u0016\u0000\u0081\u0090"+
		"\u00032\u0019\u0000\u0082\u0090\u00034\u001a\u0000\u0083\u0090\u0003@"+
		" \u0000\u0084\u0090\u0003F#\u0000\u0085\u0090\u0003\u0012\t\u0000\u0086"+
		"\u0090\u0003\u0014\n\u0000\u0087\u0090\u0003\u000e\u0007\u0000\u0088\u0090"+
		"\u0003\f\u0006\u0000\u0089\u0090\u00036\u001b\u0000\u008a\u0090\u0003"+
		"8\u001c\u0000\u008b\u0090\u0003:\u001d\u0000\u008c\u0090\u0003<\u001e"+
		"\u0000\u008d\u0090\u0003>\u001f\u0000\u008e\u0090\u0003P(\u0000\u008f"+
		"|\u0001\u0000\u0000\u0000\u008f}\u0001\u0000\u0000\u0000\u008f~\u0001"+
		"\u0000\u0000\u0000\u008f\u007f\u0001\u0000\u0000\u0000\u008f\u0080\u0001"+
		"\u0000\u0000\u0000\u008f\u0081\u0001\u0000\u0000\u0000\u008f\u0082\u0001"+
		"\u0000\u0000\u0000\u008f\u0083\u0001\u0000\u0000\u0000\u008f\u0084\u0001"+
		"\u0000\u0000\u0000\u008f\u0085\u0001\u0000\u0000\u0000\u008f\u0086\u0001"+
		"\u0000\u0000\u0000\u008f\u0087\u0001\u0000\u0000\u0000\u008f\u0088\u0001"+
		"\u0000\u0000\u0000\u008f\u0089\u0001\u0000\u0000\u0000\u008f\u008a\u0001"+
		"\u0000\u0000\u0000\u008f\u008b\u0001\u0000\u0000\u0000\u008f\u008c\u0001"+
		"\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u008e\u0001"+
		"\u0000\u0000\u0000\u0090\u000b\u0001\u0000\u0000\u0000\u0091\u0093\u0005"+
		"\u0005\u0000\u0000\u0092\u0094\u0003H$\u0000\u0093\u0092\u0001\u0000\u0000"+
		"\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000"+
		"\u0000\u0095\u0096\u0005\u0006\u0000\u0000\u0096\u0097\u0003P(\u0000\u0097"+
		"\u0098\u0005\u0007\u0000\u0000\u0098\r\u0001\u0000\u0000\u0000\u0099\u009b"+
		"\u0005\b\u0000\u0000\u009a\u009c\u0003H$\u0000\u009b\u009a\u0001\u0000"+
		"\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u009e\u0001\u0000"+
		"\u0000\u0000\u009d\u009f\u0003\u0010\b\u0000\u009e\u009d\u0001\u0000\u0000"+
		"\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a0\u00a2\u0005\u0006\u0000\u0000\u00a1\u00a3\u0003P(\u0000\u00a2"+
		"\u00a1\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005\t\u0000\u0000\u00a5\u000f"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005\n\u0000\u0000\u00a7\u0011\u0001"+
		"\u0000\u0000\u0000\u00a8\u00aa\u0005\u000b\u0000\u0000\u00a9\u00ab\u0003"+
		"H$\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ae\u0005\u0006\u0000"+
		"\u0000\u00ad\u00af\u0005Q\u0000\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000"+
		"\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000"+
		"\u00b0\u00b1\u0005\f\u0000\u0000\u00b1\u0013\u0001\u0000\u0000\u0000\u00b2"+
		"\u00b4\u0005\r\u0000\u0000\u00b3\u00b5\u0003H$\u0000\u00b4\u00b3\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b8\u0005\u0006\u0000\u0000\u00b7\u00b9\u0003"+
		"P(\u0000\u00b8\u00b7\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000"+
		"\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\u000e\u0000"+
		"\u0000\u00bb\u0015\u0001\u0000\u0000\u0000\u00bc\u00be\u0005\u000f\u0000"+
		"\u0000\u00bd\u00bf\u0003H$\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00be"+
		"\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c2\u0005\u0006\u0000\u0000\u00c1\u00c3\u0003B!\u0000\u00c2\u00c1\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c4\u00c6\u0003*\u0015\u0000\u00c5\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6\u00c8\u0001\u0000"+
		"\u0000\u0000\u00c7\u00c9\u0003D\"\u0000\u00c8\u00c7\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000"+
		"\u0000\u00ca\u00cb\u0005\u0010\u0000\u0000\u00cb\u0017\u0001\u0000\u0000"+
		"\u0000\u00cc\u00cd\u0005\u0011\u0000\u0000\u00cd\u00cf\u0003V+\u0000\u00ce"+
		"\u00d0\u0003H$\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d3\u0005"+
		"\u0006\u0000\u0000\u00d2\u00d4\u0003*\u0015\u0000\u00d3\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000"+
		"\u0000\u0000\u00d5\u00d6\u0005\u0012\u0000\u0000\u00d6\u00d8\u0003V+\u0000"+
		"\u00d7\u00d9\u0005\u0006\u0000\u0000\u00d8\u00d7\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u0019\u0001\u0000\u0000\u0000"+
		"\u00da\u00dc\u0005\u0013\u0000\u0000\u00db\u00dd\u0003H$\u0000\u00dc\u00db"+
		"\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd\u00e0"+
		"\u0001\u0000\u0000\u0000\u00de\u00df\u0005\u0014\u0000\u0000\u00df\u00e1"+
		"\u0003P(\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e6\u0005\u0006"+
		"\u0000\u0000\u00e3\u00e5\u0003\u001c\u000e\u0000\u00e4\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e8\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7\u00e9\u0001\u0000"+
		"\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005\u0015"+
		"\u0000\u0000\u00ea\u001b\u0001\u0000\u0000\u0000\u00eb\u00ed\u0005\u0016"+
		"\u0000\u0000\u00ec\u00ee\u0003H$\u0000\u00ed\u00ec\u0001\u0000\u0000\u0000"+
		"\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000"+
		"\u00ef\u00f1\u0005\u0006\u0000\u0000\u00f0\u00f2\u0003*\u0015\u0000\u00f1"+
		"\u00f0\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2"+
		"\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005\u0017\u0000\u0000\u00f4"+
		"\u001d\u0001\u0000\u0000\u0000\u00f5\u00f7\u0005\u0016\u0000\u0000\u00f6"+
		"\u00f8\u0003H$\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001"+
		"\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000\u0000\u00f9\u00fb\u0005"+
		"\u0006\u0000\u0000\u00fa\u00fc\u0003*\u0015\u0000\u00fb\u00fa\u0001\u0000"+
		"\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000"+
		"\u0000\u0000\u00fd\u00fe\u0005\u0017\u0000\u0000\u00fe\u001f\u0001\u0000"+
		"\u0000\u0000\u00ff\u0101\u0005\u0018\u0000\u0000\u0100\u0102\u0003H$\u0000"+
		"\u0101\u0100\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000"+
		"\u0102\u0103\u0001\u0000\u0000\u0000\u0103\u0107\u0005\u0006\u0000\u0000"+
		"\u0104\u0106\u0003\"\u0011\u0000\u0105\u0104\u0001\u0000\u0000\u0000\u0106"+
		"\u0109\u0001\u0000\u0000\u0000\u0107\u0105\u0001\u0000\u0000\u0000\u0107"+
		"\u0108\u0001\u0000\u0000\u0000\u0108\u010a\u0001\u0000\u0000\u0000\u0109"+
		"\u0107\u0001\u0000\u0000\u0000\u010a\u010b\u0005\u0019\u0000\u0000\u010b"+
		"!\u0001\u0000\u0000\u0000\u010c\u010e\u0005\u001a\u0000\u0000\u010d\u010f"+
		"\u0003H$\u0000\u010e\u010d\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000"+
		"\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u0114\u0005\u0006"+
		"\u0000\u0000\u0111\u0113\u0003$\u0012\u0000\u0112\u0111\u0001\u0000\u0000"+
		"\u0000\u0113\u0116\u0001\u0000\u0000\u0000\u0114\u0112\u0001\u0000\u0000"+
		"\u0000\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0117\u0001\u0000\u0000"+
		"\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0117\u0118\u0005\u001b\u0000"+
		"\u0000\u0118#\u0001\u0000\u0000\u0000\u0119\u011c\u0003&\u0013\u0000\u011a"+
		"\u011c\u0003(\u0014\u0000\u011b\u0119\u0001\u0000\u0000\u0000\u011b\u011a"+
		"\u0001\u0000\u0000\u0000\u011c%\u0001\u0000\u0000\u0000\u011d\u011f\u0005"+
		"\u001c\u0000\u0000\u011e\u0120\u0003H$\u0000\u011f\u011e\u0001\u0000\u0000"+
		"\u0000\u011f\u0120\u0001\u0000\u0000\u0000\u0120\u0121\u0001\u0000\u0000"+
		"\u0000\u0121\u0122\u0005\u0006\u0000\u0000\u0122\u0123\u0003*\u0015\u0000"+
		"\u0123\u0124\u0005\u001d\u0000\u0000\u0124\'\u0001\u0000\u0000\u0000\u0125"+
		"\u0127\u0005\u001e\u0000\u0000\u0126\u0128\u0003H$\u0000\u0127\u0126\u0001"+
		"\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u0129\u0001"+
		"\u0000\u0000\u0000\u0129\u012a\u0005\u0006\u0000\u0000\u012a\u012b\u0003"+
		"*\u0015\u0000\u012b\u012c\u0005\u001f\u0000\u0000\u012c)\u0001\u0000\u0000"+
		"\u0000\u012d\u0137\u0003P(\u0000\u012e\u0130\u0003\n\u0005\u0000\u012f"+
		"\u012e\u0001\u0000\u0000\u0000\u0130\u0133\u0001\u0000\u0000\u0000\u0131"+
		"\u012f\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000\u0132"+
		"\u0135\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0134"+
		"\u0131\u0001\u0000\u0000\u0000\u0134\u0135\u0001\u0000\u0000\u0000\u0135"+
		"\u0137\u0001\u0000\u0000\u0000\u0136\u012d\u0001\u0000\u0000\u0000\u0136"+
		"\u0134\u0001\u0000\u0000\u0000\u0137+\u0001\u0000\u0000\u0000\u0138\u013a"+
		"\u0005 \u0000\u0000\u0139\u013b\u0003.\u0017\u0000\u013a\u0139\u0001\u0000"+
		"\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b\u013d\u0001\u0000"+
		"\u0000\u0000\u013c\u013e\u0003H$\u0000\u013d\u013c\u0001\u0000\u0000\u0000"+
		"\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u0140\u0001\u0000\u0000\u0000"+
		"\u013f\u0141\u00030\u0018\u0000\u0140\u013f\u0001\u0000\u0000\u0000\u0140"+
		"\u0141\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000\u0142"+
		"\u0144\u0005\u0006\u0000\u0000\u0143\u0145\u0003P(\u0000\u0144\u0143\u0001"+
		"\u0000\u0000\u0000\u0144\u0145\u0001\u0000\u0000\u0000\u0145\u0146\u0001"+
		"\u0000\u0000\u0000\u0146\u0147\u0005!\u0000\u0000\u0147-\u0001\u0000\u0000"+
		"\u0000\u0148\u0149\u0005\"\u0000\u0000\u0149\u014a\u0005#\u0000\u0000"+
		"\u014a\u014b\u0003P(\u0000\u014b/\u0001\u0000\u0000\u0000\u014c\u014d"+
		"\u0005$\u0000\u0000\u014d\u014e\u0005#\u0000\u0000\u014e\u014f\u0003P"+
		"(\u0000\u014f1\u0001\u0000\u0000\u0000\u0150\u0152\u0005%\u0000\u0000"+
		"\u0151\u0153\u0003H$\u0000\u0152\u0151\u0001\u0000\u0000\u0000\u0152\u0153"+
		"\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000\u0000\u0000\u0154\u0156"+
		"\u0005\u0006\u0000\u0000\u0155\u0157\u0003\\.\u0000\u0156\u0155\u0001"+
		"\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000\u0157\u0159\u0001"+
		"\u0000\u0000\u0000\u0158\u015a\u0003^/\u0000\u0159\u0158\u0001\u0000\u0000"+
		"\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000"+
		"\u0000\u015b\u015c\u0005&\u0000\u0000\u015c3\u0001\u0000\u0000\u0000\u015d"+
		"\u015f\u0005\'\u0000\u0000\u015e\u0160\u0003H$\u0000\u015f\u015e\u0001"+
		"\u0000\u0000\u0000\u015f\u0160\u0001\u0000\u0000\u0000\u0160\u0161\u0001"+
		"\u0000\u0000\u0000\u0161\u0168\u0005\u0006\u0000\u0000\u0162\u0164\u0003"+
		"\n\u0005\u0000\u0163\u0162\u0001\u0000\u0000\u0000\u0164\u0167\u0001\u0000"+
		"\u0000\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000"+
		"\u0000\u0000\u0166\u0169\u0001\u0000\u0000\u0000\u0167\u0165\u0001\u0000"+
		"\u0000\u0000\u0168\u0165\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000"+
		"\u0000\u0000\u0169\u016b\u0001\u0000\u0000\u0000\u016a\u016c\u0003P(\u0000"+
		"\u016b\u016a\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000\u0000"+
		"\u016c\u016d\u0001\u0000\u0000\u0000\u016d\u016e\u0005(\u0000\u0000\u016e"+
		"5\u0001\u0000\u0000\u0000\u016f\u0171\u0005)\u0000\u0000\u0170\u0172\u0003"+
		"H$\u0000\u0171\u0170\u0001\u0000\u0000\u0000\u0171\u0172\u0001\u0000\u0000"+
		"\u0000\u0172\u0173\u0001\u0000\u0000\u0000\u0173\u0174\u0005\u0006\u0000"+
		"\u0000\u0174\u0175\u0005Q\u0000\u0000\u0175\u0176\u0005*\u0000\u0000\u0176"+
		"7\u0001\u0000\u0000\u0000\u0177\u0179\u0005+\u0000\u0000\u0178\u017a\u0003"+
		"H$\u0000\u0179\u0178\u0001\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000"+
		"\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017b\u017c\u0005\u0006\u0000"+
		"\u0000\u017c\u017d\u0005Q\u0000\u0000\u017d\u017e\u0005,\u0000\u0000\u017e"+
		"9\u0001\u0000\u0000\u0000\u017f\u0181\u0005-\u0000\u0000\u0180\u0182\u0003"+
		"H$\u0000\u0181\u0180\u0001\u0000\u0000\u0000\u0181\u0182\u0001\u0000\u0000"+
		"\u0000\u0182\u0183\u0001\u0000\u0000\u0000\u0183\u0184\u0005\u0006\u0000"+
		"\u0000\u0184\u0185\u0005.\u0000\u0000\u0185;\u0001\u0000\u0000\u0000\u0186"+
		"\u0188\u0005/\u0000\u0000\u0187\u0189\u0003H$\u0000\u0188\u0187\u0001"+
		"\u0000\u0000\u0000\u0188\u0189\u0001\u0000\u0000\u0000\u0189\u018a\u0001"+
		"\u0000\u0000\u0000\u018a\u018c\u0005\u0006\u0000\u0000\u018b\u018d\u0003"+
		"P(\u0000\u018c\u018b\u0001\u0000\u0000\u0000\u018c\u018d\u0001\u0000\u0000"+
		"\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e\u018f\u00050\u0000\u0000"+
		"\u018f=\u0001\u0000\u0000\u0000\u0190\u0192\u00051\u0000\u0000\u0191\u0193"+
		"\u0003H$\u0000\u0192\u0191\u0001\u0000\u0000\u0000\u0192\u0193\u0001\u0000"+
		"\u0000\u0000\u0193\u0194\u0001\u0000\u0000\u0000\u0194\u0196\u0005\u0006"+
		"\u0000\u0000\u0195\u0197\u0003\\.\u0000\u0196\u0195\u0001\u0000\u0000"+
		"\u0000\u0196\u0197\u0001\u0000\u0000\u0000\u0197\u0199\u0001\u0000\u0000"+
		"\u0000\u0198\u019a\u0003^/\u0000\u0199\u0198\u0001\u0000\u0000\u0000\u0199"+
		"\u019a\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b"+
		"\u019c\u00052\u0000\u0000\u019c?\u0001\u0000\u0000\u0000\u019d\u019f\u0005"+
		"3\u0000\u0000\u019e\u01a0\u0003H$\u0000\u019f\u019e\u0001\u0000\u0000"+
		"\u0000\u019f\u01a0\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001\u0000\u0000"+
		"\u0000\u01a1\u01a3\u0005\u0006\u0000\u0000\u01a2\u01a4\u0003B!\u0000\u01a3"+
		"\u01a2\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4"+
		"\u01a6\u0001\u0000\u0000\u0000\u01a5\u01a7\u0003P(\u0000\u01a6\u01a5\u0001"+
		"\u0000\u0000\u0000\u01a6\u01a7\u0001\u0000\u0000\u0000\u01a7\u01a9\u0001"+
		"\u0000\u0000\u0000\u01a8\u01aa\u0003D\"\u0000\u01a9\u01a8\u0001\u0000"+
		"\u0000\u0000\u01a9\u01aa\u0001\u0000\u0000\u0000\u01aa\u01ab\u0001\u0000"+
		"\u0000\u0000\u01ab\u01ac\u00054\u0000\u0000\u01acA\u0001\u0000\u0000\u0000"+
		"\u01ad\u01af\u00055\u0000\u0000\u01ae\u01b0\u0003H$\u0000\u01af\u01ae"+
		"\u0001\u0000\u0000\u0000\u01af\u01b0\u0001\u0000\u0000\u0000\u01b0\u01b1"+
		"\u0001\u0000\u0000\u0000\u01b1\u01b2\u0005\u0006\u0000\u0000\u01b2C\u0001"+
		"\u0000\u0000\u0000\u01b3\u01b5\u00055\u0000\u0000\u01b4\u01b6\u0003H$"+
		"\u0000\u01b5\u01b4\u0001\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000\u0000"+
		"\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0005\u0006\u0000"+
		"\u0000\u01b8E\u0001\u0000\u0000\u0000\u01b9\u01ba\u00056\u0000\u0000\u01ba"+
		"\u01bb\u00057\u0000\u0000\u01bb\u01bc\u0005Q\u0000\u0000\u01bc\u01bd\u0005"+
		"8\u0000\u0000\u01bdG\u0001\u0000\u0000\u0000\u01be\u01c0\u00059\u0000"+
		"\u0000\u01bf\u01c1\u0005K\u0000\u0000\u01c0\u01bf\u0001\u0000\u0000\u0000"+
		"\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1\u01c3\u0001\u0000\u0000\u0000"+
		"\u01c2\u01c4\u0003J%\u0000\u01c3\u01c2\u0001\u0000\u0000\u0000\u01c3\u01c4"+
		"\u0001\u0000\u0000\u0000\u01c4I\u0001\u0000\u0000\u0000\u01c5\u01cc\u0003"+
		"L&\u0000\u01c6\u01c8\u0005?\u0000\u0000\u01c7\u01c6\u0001\u0000\u0000"+
		"\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000\u0000"+
		"\u0000\u01c9\u01cb\u0003L&\u0000\u01ca\u01c7\u0001\u0000\u0000\u0000\u01cb"+
		"\u01ce\u0001\u0000\u0000\u0000\u01cc\u01ca\u0001\u0000\u0000\u0000\u01cc"+
		"\u01cd\u0001\u0000\u0000\u0000\u01cd\u01d0\u0001\u0000\u0000\u0000\u01ce"+
		"\u01cc\u0001\u0000\u0000\u0000\u01cf\u01d1\u0005?\u0000\u0000\u01d0\u01cf"+
		"\u0001\u0000\u0000\u0000\u01d0\u01d1\u0001\u0000\u0000\u0000\u01d1K\u0001"+
		"\u0000\u0000\u0000\u01d2\u01d3\u0003N\'\u0000\u01d3\u01d4\u0005>\u0000"+
		"\u0000\u01d4\u01d5\u0003P(\u0000\u01d5M\u0001\u0000\u0000\u0000\u01d6"+
		"\u01d9\u0005Q\u0000\u0000\u01d7\u01d9\u0003R)\u0000\u01d8\u01d6\u0001"+
		"\u0000\u0000\u0000\u01d8\u01d7\u0001\u0000\u0000\u0000\u01d9O\u0001\u0000"+
		"\u0000\u0000\u01da\u01e0\u0003T*\u0000\u01db\u01e0\u0005Q\u0000\u0000"+
		"\u01dc\u01e0\u0003R)\u0000\u01dd\u01e0\u0003\\.\u0000\u01de\u01e0\u0003"+
		"V+\u0000\u01df\u01da\u0001\u0000\u0000\u0000\u01df\u01db\u0001\u0000\u0000"+
		"\u0000\u01df\u01dc\u0001\u0000\u0000\u0000\u01df\u01dd\u0001\u0000\u0000"+
		"\u0000\u01df\u01de\u0001\u0000\u0000\u0000\u01e0Q\u0001\u0000\u0000\u0000"+
		"\u01e1\u01e2\u0005K\u0000\u0000\u01e2S\u0001\u0000\u0000\u0000\u01e3\u01e4"+
		"\u0005H\u0000\u0000\u01e4\u01e5\u0005H\u0000\u0000\u01e5\u01e6\u0005H"+
		"\u0000\u0000\u01e6\u01e7\u0005H\u0000\u0000\u01e7U\u0001\u0000\u0000\u0000"+
		"\u01e8\u01e9\u0005I\u0000\u0000\u01e9W\u0001\u0000\u0000\u0000\u01ea\u01eb"+
		"\u0005L\u0000\u0000\u01ebY\u0001\u0000\u0000\u0000\u01ec\u01ed\u0005H"+
		"\u0000\u0000\u01ed[\u0001\u0000\u0000\u0000\u01ee\u01ef\u0005:\u0000\u0000"+
		"\u01ef\u01f0\u0005Q\u0000\u0000\u01f0\u01f1\u0005;\u0000\u0000\u01f1]"+
		"\u0001\u0000\u0000\u0000\u01f2\u01f3\u0005<\u0000\u0000\u01f3\u01f4\u0005"+
		"Q\u0000\u0000\u01f4\u01f5\u0005;\u0000\u0000\u01f5_\u0001\u0000\u0000"+
		"\u0000\u01f6\u01f7\u0005=\u0000\u0000\u01f7\u01fd\u0005T\u0000\u0000\u01f8"+
		"\u01fd\u0005O\u0000\u0000\u01f9\u01fd\u0005N\u0000\u0000\u01fa\u01fd\u0005"+
		"M\u0000\u0000\u01fb\u01fd\u0005P\u0000\u0000\u01fc\u01f6\u0001\u0000\u0000"+
		"\u0000\u01fc\u01f8\u0001\u0000\u0000\u0000\u01fc\u01f9\u0001\u0000\u0000"+
		"\u0000\u01fc\u01fa\u0001\u0000\u0000\u0000\u01fc\u01fb\u0001\u0000\u0000"+
		"\u0000\u01fda\u0001\u0000\u0000\u0000Femux\u008f\u0093\u009b\u009e\u00a2"+
		"\u00aa\u00ae\u00b4\u00b8\u00be\u00c2\u00c5\u00c8\u00cf\u00d3\u00d8\u00dc"+
		"\u00e0\u00e6\u00ed\u00f1\u00f7\u00fb\u0101\u0107\u010e\u0114\u011b\u011f"+
		"\u0127\u0131\u0134\u0136\u013a\u013d\u0140\u0144\u0152\u0156\u0159\u015f"+
		"\u0165\u0168\u016b\u0171\u0179\u0181\u0188\u018c\u0192\u0196\u0199\u019f"+
		"\u01a3\u01a6\u01a9\u01af\u01b5\u01c0\u01c3\u01c7\u01cc\u01d0\u01d8\u01df"+
		"\u01fc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}