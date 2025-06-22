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
		T__59=60, A0=61, A1=62, A2=63, A3=64, A4=65, A5=66, A6=67, A7=68, A8=69, 
		A9=70, A10=71, B0=72, B1=73, B2=74, B3=75, B4=76, B5=77, B6=78, B7=79, 
		B8=80, B9=81, B10=82, DEFAULT=83, EXECUTIVE=84, LEDGER=85, LEGAL=86, LETTER=87, 
		TABLOID=88, ID=89, AUTO=90, IDENTIFIER=91, STRING=92, PATH=93, NUMBER=94, 
		COLOR=95, WS=96, COMMENT=97, AT=98;
	public static final int
		RULE_document = 0, RULE_page = 1, RULE_pageLayout = 2, RULE_margins = 3, 
		RULE_layoutOption = 4, RULE_customOption = 5, RULE_element = 6, RULE_paragraph = 7, 
		RULE_heading = 8, RULE_list = 9, RULE_orderType = 10, RULE_listItem = 11, 
		RULE_table = 12, RULE_tableRow = 13, RULE_tableCell = 14, RULE_colspan = 15, 
		RULE_rowspan = 16, RULE_image = 17, RULE_svg = 18, RULE_div = 19, RULE_template = 20, 
		RULE_paragraphStyle = 21, RULE_paragraphStyleType = 22, RULE_textStyle = 23, 
		RULE_alignment = 24, RULE_alignmentLocation = 25, RULE_spacing = 26, RULE_marginOrPadding = 27, 
		RULE_imageStyle = 28, RULE_imageStyleItem = 29, RULE_svgStyle = 30, RULE_svgStyleItem = 31, 
		RULE_divStyle = 32, RULE_divStyleItem = 33, RULE_dimension = 34, RULE_background = 35, 
		RULE_border = 36, RULE_borderRounded = 37, RULE_opacity = 38, RULE_scale = 39, 
		RULE_float = 40, RULE_floatDirect = 41, RULE_string = 42, RULE_color = 43, 
		RULE_number = 44, RULE_unit = 45, RULE_variable = 46;
	private static String[] makeRuleNames() {
		return new String[] {
			"document", "page", "pageLayout", "margins", "layoutOption", "customOption", 
			"element", "paragraph", "heading", "list", "orderType", "listItem", "table", 
			"tableRow", "tableCell", "colspan", "rowspan", "image", "svg", "div", 
			"template", "paragraphStyle", "paragraphStyleType", "textStyle", "alignment", 
			"alignmentLocation", "spacing", "marginOrPadding", "imageStyle", "imageStyleItem", 
			"svgStyle", "svgStyleItem", "divStyle", "divStyleItem", "dimension", 
			"background", "border", "borderRounded", "opacity", "scale", "float", 
			"floatDirect", "string", "color", "number", "unit", "variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'pdf'", "'{'", "'}'", "'page'", "'landscape'", "'margins'", "'CUSTOM'", 
			"'paragraph'", "'heading'", "'h1'", "'h2'", "'h3'", "'h4'", "'h5'", "'h6'", 
			"'list'", "'ordered'", "'unordered'", "'item'", "'table'", "'cols'", 
			"'row'", "'cell'", "'colspan'", "'rowspan'", "'image'", "'svg'", "'div'", 
			"'('", "')'", "'template'", "'use'", "'font'", "'size'", "'color'", "'bold'", 
			"'italic'", "'underline'", "'align'", "'left'", "'right'", "'center'", 
			"'justify'", "'margin'", "'padding'", "'width'", "'height'", "'background'", 
			"'border'", "'rounded'", "'opacity'", "'scale'", "'float'", "'px'", "'pt'", 
			"'mm'", "'cm'", "'in'", "'%'", "'#{'", "'A0'", "'A1'", "'A2'", "'A3'", 
			"'A4'", "'A5'", "'A6'", "'A7'", "'A8'", "'A9'", "'A10'", "'B0'", "'B1'", 
			"'B2'", "'B3'", "'B4'", "'B5'", "'B6'", "'B7'", "'B8'", "'B9'", "'B10'", 
			"'DEFAULT'", "'EXECUTIVE'", "'LEDGER'", "'LEGAL'", "'LETTER'", "'TABLOID'", 
			null, "'auto'"
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
			null, "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", 
			"B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "DEFAULT", 
			"EXECUTIVE", "LEDGER", "LEGAL", "LETTER", "TABLOID", "ID", "AUTO", "IDENTIFIER", 
			"STRING", "PATH", "NUMBER", "COLOR", "WS", "COMMENT", "AT"
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
		public TerminalNode ID() { return getToken(JQuickPDFParser.ID, 0); }
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
			setState(94);
			match(T__0);
			setState(95);
			match(ID);
			setState(96);
			match(T__1);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(97);
				page();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
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
			setState(105);
			match(T__3);
			{
			setState(106);
			pageLayout();
			}
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(107);
				match(T__4);
				}
			}

			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(110);
				margins();
				}
			}

			setState(113);
			match(T__1);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2618360576L) != 0)) {
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
			setState(120);
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
			setState(124);
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
				setState(122);
				layoutOption();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
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
			setState(126);
			match(T__5);
			setState(127);
			number();
			setState(128);
			unit();
			setState(129);
			number();
			setState(130);
			unit();
			setState(131);
			number();
			setState(132);
			unit();
			setState(133);
			number();
			setState(134);
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
			setState(136);
			_la = _input.LA(1);
			if ( !(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & 268435455L) != 0)) ) {
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
			setState(138);
			match(T__6);
			setState(139);
			number();
			setState(140);
			unit();
			setState(141);
			number();
			setState(142);
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
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				paragraph();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				heading();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				list();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				table();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 5);
				{
				setState(148);
				image();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 6);
				{
				setState(149);
				svg();
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 7);
				{
				setState(150);
				div();
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 8);
				{
				setState(151);
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
		public ParagraphStyleContext paragraphStyle() {
			return getRuleContext(ParagraphStyleContext.class,0);
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
			setState(154);
			match(T__7);
			setState(155);
			string();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(156);
				match(T__1);
				setState(157);
				paragraphStyle();
				setState(158);
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
		public List<TextStyleContext> textStyle() {
			return getRuleContexts(TextStyleContext.class);
		}
		public TextStyleContext textStyle(int i) {
			return getRuleContext(TextStyleContext.class,i);
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
			setState(162);
			match(T__8);
			setState(163);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 64512L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(164);
			string();
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(165);
				match(T__1);
				setState(167); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(166);
					textStyle();
					}
					}
					setState(169); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1090921693184L) != 0) );
				setState(171);
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
		public OrderTypeContext orderType() {
			return getRuleContext(OrderTypeContext.class,0);
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
			setState(175);
			match(T__15);
			{
			setState(176);
			orderType();
			}
			setState(177);
			match(T__1);
			setState(179); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(178);
				listItem();
				}
				}
				setState(181); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__18 );
			setState(183);
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
			setState(185);
			_la = _input.LA(1);
			if ( !(_la==T__16 || _la==T__17) ) {
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(T__18);
			setState(188);
			string();
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
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<TableRowContext> tableRow() {
			return getRuleContexts(TableRowContext.class);
		}
		public TableRowContext tableRow(int i) {
			return getRuleContext(TableRowContext.class,i);
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
			setState(190);
			match(T__19);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(191);
				match(T__20);
				setState(192);
				number();
				}
			}

			setState(195);
			match(T__1);
			setState(197); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(196);
				tableRow();
				}
				}
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__21 );
			setState(201);
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
	public static class TableRowContext extends ParserRuleContext {
		public List<TableCellContext> tableCell() {
			return getRuleContexts(TableCellContext.class);
		}
		public TableCellContext tableCell(int i) {
			return getRuleContext(TableCellContext.class,i);
		}
		public TableRowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableRow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTableRow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTableRow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTableRow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableRowContext tableRow() throws RecognitionException {
		TableRowContext _localctx = new TableRowContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_tableRow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(T__21);
			setState(204);
			match(T__1);
			setState(206); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(205);
				tableCell();
				}
				}
				setState(208); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__22 );
			setState(210);
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
		enterRule(_localctx, 28, RULE_tableCell);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(T__22);
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(213);
				colspan();
				}
				break;
			}
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(216);
				rowspan();
				}
			}

			setState(219);
			match(T__1);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2618360576L) != 0)) {
				{
				{
				setState(220);
				element();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
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
		enterRule(_localctx, 30, RULE_colspan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			number();
			setState(229);
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
		enterRule(_localctx, 32, RULE_rowspan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			number();
			setState(232);
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
	public static class ImageContext extends ParserRuleContext {
		public TerminalNode PATH() { return getToken(JQuickPDFParser.PATH, 0); }
		public ImageStyleContext imageStyle() {
			return getRuleContext(ImageStyleContext.class,0);
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
		enterRule(_localctx, 34, RULE_image);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(T__25);
			setState(235);
			match(PATH);
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(236);
				match(T__1);
				setState(237);
				imageStyle();
				setState(238);
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
		public SvgStyleContext svgStyle() {
			return getRuleContext(SvgStyleContext.class,0);
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
		enterRule(_localctx, 36, RULE_svg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(T__26);
			setState(243);
			match(PATH);
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(244);
				match(T__1);
				setState(245);
				svgStyle();
				setState(246);
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
		public DivStyleContext divStyle() {
			return getRuleContext(DivStyleContext.class,0);
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
		enterRule(_localctx, 38, RULE_div);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(T__27);
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(251);
				match(T__28);
				setState(252);
				divStyle();
				setState(253);
				match(T__29);
				}
			}

			setState(257);
			match(T__1);
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2618360576L) != 0)) {
				{
				{
				setState(258);
				element();
				}
				}
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(264);
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
		public List<TerminalNode> ID() { return getTokens(JQuickPDFParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(JQuickPDFParser.ID, i);
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
		enterRule(_localctx, 40, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(T__30);
			setState(267);
			match(ID);
			setState(268);
			match(T__1);
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2618360576L) != 0)) {
				{
				{
				setState(269);
				element();
				}
				}
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(275);
			match(T__2);
			setState(276);
			match(T__31);
			setState(277);
			match(ID);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AT) {
				{
				setState(278);
				match(AT);
				setState(279);
				number();
				setState(280);
				unit();
				setState(281);
				number();
				setState(282);
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
	public static class ParagraphStyleContext extends ParserRuleContext {
		public List<ParagraphStyleTypeContext> paragraphStyleType() {
			return getRuleContexts(ParagraphStyleTypeContext.class);
		}
		public ParagraphStyleTypeContext paragraphStyleType(int i) {
			return getRuleContext(ParagraphStyleTypeContext.class,i);
		}
		public ParagraphStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paragraphStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterParagraphStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitParagraphStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitParagraphStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParagraphStyleContext paragraphStyle() throws RecognitionException {
		ParagraphStyleContext _localctx = new ParagraphStyleContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_paragraphStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(286);
				paragraphStyleType();
				}
				}
				setState(289); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 53867479826432L) != 0) );
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
	public static class ParagraphStyleTypeContext extends ParserRuleContext {
		public TextStyleContext textStyle() {
			return getRuleContext(TextStyleContext.class,0);
		}
		public AlignmentContext alignment() {
			return getRuleContext(AlignmentContext.class,0);
		}
		public SpacingContext spacing() {
			return getRuleContext(SpacingContext.class,0);
		}
		public ParagraphStyleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paragraphStyleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterParagraphStyleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitParagraphStyleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitParagraphStyleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParagraphStyleTypeContext paragraphStyleType() throws RecognitionException {
		ParagraphStyleTypeContext _localctx = new ParagraphStyleTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_paragraphStyleType);
		try {
			setState(294);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				textStyle();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				alignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(293);
				spacing();
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
	public static class TextStyleContext extends ParserRuleContext {
		public TextStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textStyle; }
	 
		public TextStyleContext() { }
		public void copyFrom(TextStyleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TextStyleUnderlineContext extends TextStyleContext {
		public TextStyleUnderlineContext(TextStyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTextStyleUnderline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTextStyleUnderline(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTextStyleUnderline(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TextStylefontContext extends TextStyleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TextStylefontContext(TextStyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTextStylefont(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTextStylefont(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTextStylefont(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TextStyleItalicContext extends TextStyleContext {
		public TextStyleItalicContext(TextStyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTextStyleItalic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTextStyleItalic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTextStyleItalic(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TextStyleSizeContext extends TextStyleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public TextStyleSizeContext(TextStyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTextStyleSize(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTextStyleSize(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTextStyleSize(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TextStyleColorContext extends TextStyleContext {
		public ColorContext color() {
			return getRuleContext(ColorContext.class,0);
		}
		public TextStyleColorContext(TextStyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTextStyleColor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTextStyleColor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTextStyleColor(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TextStyleAlignContext extends TextStyleContext {
		public TerminalNode ID() { return getToken(JQuickPDFParser.ID, 0); }
		public TextStyleAlignContext(TextStyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTextStyleAlign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTextStyleAlign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTextStyleAlign(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TextStyleBoldContext extends TextStyleContext {
		public TextStyleBoldContext(TextStyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterTextStyleBold(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitTextStyleBold(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitTextStyleBold(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextStyleContext textStyle() throws RecognitionException {
		TextStyleContext _localctx = new TextStyleContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_textStyle);
		try {
			setState(309);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__32:
				_localctx = new TextStylefontContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(296);
				match(T__32);
				setState(297);
				string();
				}
				break;
			case T__33:
				_localctx = new TextStyleSizeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(298);
				match(T__33);
				setState(299);
				number();
				setState(300);
				unit();
				}
				break;
			case T__34:
				_localctx = new TextStyleColorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(302);
				match(T__34);
				setState(303);
				color();
				}
				break;
			case T__35:
				_localctx = new TextStyleBoldContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(304);
				match(T__35);
				}
				break;
			case T__36:
				_localctx = new TextStyleItalicContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(305);
				match(T__36);
				}
				break;
			case T__37:
				_localctx = new TextStyleUnderlineContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(306);
				match(T__37);
				}
				break;
			case T__38:
				_localctx = new TextStyleAlignContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(307);
				match(T__38);
				setState(308);
				match(ID);
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
	public static class AlignmentContext extends ParserRuleContext {
		public AlignmentLocationContext alignmentLocation() {
			return getRuleContext(AlignmentLocationContext.class,0);
		}
		public AlignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterAlignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitAlignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitAlignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlignmentContext alignment() throws RecognitionException {
		AlignmentContext _localctx = new AlignmentContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_alignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(T__38);
			{
			setState(312);
			alignmentLocation();
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
	public static class AlignmentLocationContext extends ParserRuleContext {
		public AlignmentLocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alignmentLocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterAlignmentLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitAlignmentLocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitAlignmentLocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlignmentLocationContext alignmentLocation() throws RecognitionException {
		AlignmentLocationContext _localctx = new AlignmentLocationContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_alignmentLocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16492674416640L) != 0)) ) {
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
	public static class SpacingContext extends ParserRuleContext {
		public MarginOrPaddingContext marginOrPadding() {
			return getRuleContext(MarginOrPaddingContext.class,0);
		}
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
		public SpacingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spacing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterSpacing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitSpacing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitSpacing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpacingContext spacing() throws RecognitionException {
		SpacingContext _localctx = new SpacingContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_spacing);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(316);
			marginOrPadding();
			}
			setState(320); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(317);
				number();
				setState(318);
				unit();
				}
				}
				setState(322); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMBER );
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
	public static class MarginOrPaddingContext extends ParserRuleContext {
		public MarginOrPaddingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_marginOrPadding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterMarginOrPadding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitMarginOrPadding(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitMarginOrPadding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MarginOrPaddingContext marginOrPadding() throws RecognitionException {
		MarginOrPaddingContext _localctx = new MarginOrPaddingContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_marginOrPadding);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			_la = _input.LA(1);
			if ( !(_la==T__43 || _la==T__44) ) {
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
	public static class ImageStyleContext extends ParserRuleContext {
		public List<ImageStyleItemContext> imageStyleItem() {
			return getRuleContexts(ImageStyleItemContext.class);
		}
		public ImageStyleItemContext imageStyleItem(int i) {
			return getRuleContext(ImageStyleItemContext.class,i);
		}
		public ImageStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imageStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterImageStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitImageStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitImageStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImageStyleContext imageStyle() throws RecognitionException {
		ImageStyleContext _localctx = new ImageStyleContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_imageStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(326);
				imageStyleItem();
				}
				}
				setState(329); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 3025873179508736L) != 0) );
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
	public static class ImageStyleItemContext extends ParserRuleContext {
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public BorderContext border() {
			return getRuleContext(BorderContext.class,0);
		}
		public OpacityContext opacity() {
			return getRuleContext(OpacityContext.class,0);
		}
		public ImageStyleItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imageStyleItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterImageStyleItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitImageStyleItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitImageStyleItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImageStyleItemContext imageStyleItem() throws RecognitionException {
		ImageStyleItemContext _localctx = new ImageStyleItemContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_imageStyleItem);
		try {
			setState(334);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__33:
			case T__45:
			case T__46:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				dimension();
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				border();
				}
				break;
			case T__50:
				enterOuterAlt(_localctx, 3);
				{
				setState(333);
				opacity();
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
	public static class SvgStyleContext extends ParserRuleContext {
		public List<SvgStyleItemContext> svgStyleItem() {
			return getRuleContexts(SvgStyleItemContext.class);
		}
		public SvgStyleItemContext svgStyleItem(int i) {
			return getRuleContext(SvgStyleItemContext.class,i);
		}
		public SvgStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_svgStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterSvgStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitSvgStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitSvgStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SvgStyleContext svgStyle() throws RecognitionException {
		SvgStyleContext _localctx = new SvgStyleContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_svgStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(336);
				svgStyleItem();
				}
				}
				setState(339); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4714723039772672L) != 0) );
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
	public static class SvgStyleItemContext extends ParserRuleContext {
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public ScaleContext scale() {
			return getRuleContext(ScaleContext.class,0);
		}
		public SvgStyleItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_svgStyleItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterSvgStyleItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitSvgStyleItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitSvgStyleItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SvgStyleItemContext svgStyleItem() throws RecognitionException {
		SvgStyleItemContext _localctx = new SvgStyleItemContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_svgStyleItem);
		try {
			setState(343);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__33:
			case T__45:
			case T__46:
				enterOuterAlt(_localctx, 1);
				{
				setState(341);
				dimension();
				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 2);
				{
				setState(342);
				scale();
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
	public static class DivStyleContext extends ParserRuleContext {
		public List<DivStyleItemContext> divStyleItem() {
			return getRuleContexts(DivStyleItemContext.class);
		}
		public DivStyleItemContext divStyleItem(int i) {
			return getRuleContext(DivStyleItemContext.class,i);
		}
		public DivStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterDivStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitDivStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitDivStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DivStyleContext divStyle() throws RecognitionException {
		DivStyleContext _localctx = new DivStyleContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_divStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(345);
				divStyleItem();
				}
				}
				setState(348); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 10115524155408384L) != 0) );
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
	public static class DivStyleItemContext extends ParserRuleContext {
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public BackgroundContext background() {
			return getRuleContext(BackgroundContext.class,0);
		}
		public BorderContext border() {
			return getRuleContext(BorderContext.class,0);
		}
		public SpacingContext spacing() {
			return getRuleContext(SpacingContext.class,0);
		}
		public FloatContext float_() {
			return getRuleContext(FloatContext.class,0);
		}
		public DivStyleItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divStyleItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterDivStyleItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitDivStyleItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitDivStyleItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DivStyleItemContext divStyleItem() throws RecognitionException {
		DivStyleItemContext _localctx = new DivStyleItemContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_divStyleItem);
		try {
			setState(355);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__33:
			case T__45:
			case T__46:
				enterOuterAlt(_localctx, 1);
				{
				setState(350);
				dimension();
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
				background();
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 3);
				{
				setState(352);
				border();
				}
				break;
			case T__43:
			case T__44:
				enterOuterAlt(_localctx, 4);
				{
				setState(353);
				spacing();
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 5);
				{
				setState(354);
				float_();
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
	public static class DimensionContext extends ParserRuleContext {
		public DimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimension; }
	 
		public DimensionContext() { }
		public void copyFrom(DimensionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DimensionHeightContext extends DimensionContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public DimensionHeightContext(DimensionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterDimensionHeight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitDimensionHeight(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitDimensionHeight(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DimensionSizeContext extends DimensionContext {
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
		public DimensionSizeContext(DimensionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterDimensionSize(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitDimensionSize(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitDimensionSize(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DimensionWidthContext extends DimensionContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public DimensionWidthContext(DimensionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterDimensionWidth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitDimensionWidth(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitDimensionWidth(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DimensionContext dimension() throws RecognitionException {
		DimensionContext _localctx = new DimensionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_dimension);
		try {
			setState(371);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__45:
				_localctx = new DimensionWidthContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				match(T__45);
				setState(358);
				number();
				setState(359);
				unit();
				}
				break;
			case T__46:
				_localctx = new DimensionHeightContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(361);
				match(T__46);
				setState(362);
				number();
				setState(363);
				unit();
				}
				break;
			case T__33:
				_localctx = new DimensionSizeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(365);
				match(T__33);
				setState(366);
				number();
				setState(367);
				unit();
				setState(368);
				number();
				setState(369);
				unit();
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
	public static class BackgroundContext extends ParserRuleContext {
		public ColorContext color() {
			return getRuleContext(ColorContext.class,0);
		}
		public BackgroundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_background; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterBackground(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitBackground(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitBackground(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BackgroundContext background() throws RecognitionException {
		BackgroundContext _localctx = new BackgroundContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_background);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(T__47);
			setState(374);
			color();
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
	public static class BorderContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public ColorContext color() {
			return getRuleContext(ColorContext.class,0);
		}
		public BorderRoundedContext borderRounded() {
			return getRuleContext(BorderRoundedContext.class,0);
		}
		public BorderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_border; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterBorder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitBorder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitBorder(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BorderContext border() throws RecognitionException {
		BorderContext _localctx = new BorderContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_border);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			match(T__48);
			setState(377);
			number();
			setState(378);
			unit();
			setState(379);
			color();
			setState(381);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__49) {
				{
				setState(380);
				borderRounded();
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
	public static class BorderRoundedContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public BorderRoundedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_borderRounded; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterBorderRounded(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitBorderRounded(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitBorderRounded(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BorderRoundedContext borderRounded() throws RecognitionException {
		BorderRoundedContext _localctx = new BorderRoundedContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_borderRounded);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			match(T__49);
			setState(384);
			number();
			setState(385);
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
	public static class OpacityContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public OpacityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opacity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterOpacity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitOpacity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitOpacity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpacityContext opacity() throws RecognitionException {
		OpacityContext _localctx = new OpacityContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_opacity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(T__50);
			setState(388);
			number();
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
	public static class ScaleContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ScaleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scale; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterScale(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitScale(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitScale(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScaleContext scale() throws RecognitionException {
		ScaleContext _localctx = new ScaleContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_scale);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			match(T__51);
			setState(391);
			number();
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
	public static class FloatContext extends ParserRuleContext {
		public FloatDirectContext floatDirect() {
			return getRuleContext(FloatDirectContext.class,0);
		}
		public FloatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_float; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitFloat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatContext float_() throws RecognitionException {
		FloatContext _localctx = new FloatContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_float);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(T__52);
			{
			setState(394);
			floatDirect();
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
	public static class FloatDirectContext extends ParserRuleContext {
		public FloatDirectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatDirect; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).enterFloatDirect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickPDFListener ) ((JQuickPDFListener)listener).exitFloatDirect(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickPDFVisitor ) return ((JQuickPDFVisitor<? extends T>)visitor).visitFloatDirect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatDirectContext floatDirect() throws RecognitionException {
		FloatDirectContext _localctx = new FloatDirectContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_floatDirect);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			_la = _input.LA(1);
			if ( !(_la==T__39 || _la==T__40) ) {
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
		enterRule(_localctx, 84, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
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
		enterRule(_localctx, 86, RULE_color);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
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
		enterRule(_localctx, 88, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
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
		enterRule(_localctx, 90, RULE_unit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1134907106097364992L) != 0)) ) {
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
		enterRule(_localctx, 92, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			match(T__59);
			setState(407);
			match(IDENTIFIER);
			setState(408);
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
		"\u0004\u0001b\u019b\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"-\u0007-\u0002.\u0007.\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000c\b\u0000\n\u0000\f\u0000f\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001m\b\u0001\u0001\u0001"+
		"\u0003\u0001p\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001t\b\u0001\n\u0001"+
		"\f\u0001w\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0003"+
		"\u0002}\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0099\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007\u00a1\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0004\b\u00a8"+
		"\b\b\u000b\b\f\b\u00a9\u0001\b\u0001\b\u0003\b\u00ae\b\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0004\t\u00b4\b\t\u000b\t\f\t\u00b5\u0001\t\u0001\t"+
		"\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00c2\b\f\u0001\f\u0001\f\u0004\f\u00c6\b\f\u000b\f\f\f\u00c7"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0004\r\u00cf\b\r\u000b\r\f\r"+
		"\u00d0\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e\u00d7\b\u000e"+
		"\u0001\u000e\u0003\u000e\u00da\b\u000e\u0001\u000e\u0001\u000e\u0005\u000e"+
		"\u00de\b\u000e\n\u000e\f\u000e\u00e1\t\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u00f1\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u00f9\b\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0100\b\u0013\u0001\u0013\u0001"+
		"\u0013\u0005\u0013\u0104\b\u0013\n\u0013\f\u0013\u0107\t\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014"+
		"\u010f\b\u0014\n\u0014\f\u0014\u0112\t\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0003\u0014\u011d\b\u0014\u0001\u0015\u0004\u0015\u0120\b\u0015"+
		"\u000b\u0015\f\u0015\u0121\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u0127\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0003\u0017\u0136\b\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0004\u001a\u0141\b\u001a\u000b\u001a\f\u001a\u0142\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0004\u001c\u0148\b\u001c\u000b\u001c\f"+
		"\u001c\u0149\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u014f\b\u001d"+
		"\u0001\u001e\u0004\u001e\u0152\b\u001e\u000b\u001e\f\u001e\u0153\u0001"+
		"\u001f\u0001\u001f\u0003\u001f\u0158\b\u001f\u0001 \u0004 \u015b\b \u000b"+
		" \f \u015c\u0001!\u0001!\u0001!\u0001!\u0001!\u0003!\u0164\b!\u0001\""+
		"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0003\"\u0174\b\"\u0001#\u0001#\u0001"+
		"#\u0001$\u0001$\u0001$\u0001$\u0001$\u0003$\u017e\b$\u0001%\u0001%\u0001"+
		"%\u0001%\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001(\u0001(\u0001"+
		"(\u0001)\u0001)\u0001*\u0001*\u0001+\u0001+\u0001,\u0001,\u0001-\u0001"+
		"-\u0001.\u0001.\u0001.\u0001.\u0001.\u0000\u0000/\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"02468:<>@BDFHJLNPRTVXZ\\\u0000\u0007\u0001\u0000=X\u0001\u0000\n\u000f"+
		"\u0001\u0000\u0011\u0012\u0001\u0000(+\u0001\u0000,-\u0001\u0000()\u0001"+
		"\u00006;\u019e\u0000^\u0001\u0000\u0000\u0000\u0002i\u0001\u0000\u0000"+
		"\u0000\u0004|\u0001\u0000\u0000\u0000\u0006~\u0001\u0000\u0000\u0000\b"+
		"\u0088\u0001\u0000\u0000\u0000\n\u008a\u0001\u0000\u0000\u0000\f\u0098"+
		"\u0001\u0000\u0000\u0000\u000e\u009a\u0001\u0000\u0000\u0000\u0010\u00a2"+
		"\u0001\u0000\u0000\u0000\u0012\u00af\u0001\u0000\u0000\u0000\u0014\u00b9"+
		"\u0001\u0000\u0000\u0000\u0016\u00bb\u0001\u0000\u0000\u0000\u0018\u00be"+
		"\u0001\u0000\u0000\u0000\u001a\u00cb\u0001\u0000\u0000\u0000\u001c\u00d4"+
		"\u0001\u0000\u0000\u0000\u001e\u00e4\u0001\u0000\u0000\u0000 \u00e7\u0001"+
		"\u0000\u0000\u0000\"\u00ea\u0001\u0000\u0000\u0000$\u00f2\u0001\u0000"+
		"\u0000\u0000&\u00fa\u0001\u0000\u0000\u0000(\u010a\u0001\u0000\u0000\u0000"+
		"*\u011f\u0001\u0000\u0000\u0000,\u0126\u0001\u0000\u0000\u0000.\u0135"+
		"\u0001\u0000\u0000\u00000\u0137\u0001\u0000\u0000\u00002\u013a\u0001\u0000"+
		"\u0000\u00004\u013c\u0001\u0000\u0000\u00006\u0144\u0001\u0000\u0000\u0000"+
		"8\u0147\u0001\u0000\u0000\u0000:\u014e\u0001\u0000\u0000\u0000<\u0151"+
		"\u0001\u0000\u0000\u0000>\u0157\u0001\u0000\u0000\u0000@\u015a\u0001\u0000"+
		"\u0000\u0000B\u0163\u0001\u0000\u0000\u0000D\u0173\u0001\u0000\u0000\u0000"+
		"F\u0175\u0001\u0000\u0000\u0000H\u0178\u0001\u0000\u0000\u0000J\u017f"+
		"\u0001\u0000\u0000\u0000L\u0183\u0001\u0000\u0000\u0000N\u0186\u0001\u0000"+
		"\u0000\u0000P\u0189\u0001\u0000\u0000\u0000R\u018c\u0001\u0000\u0000\u0000"+
		"T\u018e\u0001\u0000\u0000\u0000V\u0190\u0001\u0000\u0000\u0000X\u0192"+
		"\u0001\u0000\u0000\u0000Z\u0194\u0001\u0000\u0000\u0000\\\u0196\u0001"+
		"\u0000\u0000\u0000^_\u0005\u0001\u0000\u0000_`\u0005Y\u0000\u0000`d\u0005"+
		"\u0002\u0000\u0000ac\u0003\u0002\u0001\u0000ba\u0001\u0000\u0000\u0000"+
		"cf\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000"+
		"\u0000eg\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000gh\u0005\u0003"+
		"\u0000\u0000h\u0001\u0001\u0000\u0000\u0000ij\u0005\u0004\u0000\u0000"+
		"jl\u0003\u0004\u0002\u0000km\u0005\u0005\u0000\u0000lk\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000np\u0003\u0006"+
		"\u0003\u0000on\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pq\u0001"+
		"\u0000\u0000\u0000qu\u0005\u0002\u0000\u0000rt\u0003\f\u0006\u0000sr\u0001"+
		"\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000"+
		"uv\u0001\u0000\u0000\u0000vx\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000"+
		"\u0000xy\u0005\u0003\u0000\u0000y\u0003\u0001\u0000\u0000\u0000z}\u0003"+
		"\b\u0004\u0000{}\u0003\n\u0005\u0000|z\u0001\u0000\u0000\u0000|{\u0001"+
		"\u0000\u0000\u0000}\u0005\u0001\u0000\u0000\u0000~\u007f\u0005\u0006\u0000"+
		"\u0000\u007f\u0080\u0003X,\u0000\u0080\u0081\u0003Z-\u0000\u0081\u0082"+
		"\u0003X,\u0000\u0082\u0083\u0003Z-\u0000\u0083\u0084\u0003X,\u0000\u0084"+
		"\u0085\u0003Z-\u0000\u0085\u0086\u0003X,\u0000\u0086\u0087\u0003Z-\u0000"+
		"\u0087\u0007\u0001\u0000\u0000\u0000\u0088\u0089\u0007\u0000\u0000\u0000"+
		"\u0089\t\u0001\u0000\u0000\u0000\u008a\u008b\u0005\u0007\u0000\u0000\u008b"+
		"\u008c\u0003X,\u0000\u008c\u008d\u0003Z-\u0000\u008d\u008e\u0003X,\u0000"+
		"\u008e\u008f\u0003Z-\u0000\u008f\u000b\u0001\u0000\u0000\u0000\u0090\u0099"+
		"\u0003\u000e\u0007\u0000\u0091\u0099\u0003\u0010\b\u0000\u0092\u0099\u0003"+
		"\u0012\t\u0000\u0093\u0099\u0003\u0018\f\u0000\u0094\u0099\u0003\"\u0011"+
		"\u0000\u0095\u0099\u0003$\u0012\u0000\u0096\u0099\u0003&\u0013\u0000\u0097"+
		"\u0099\u0003(\u0014\u0000\u0098\u0090\u0001\u0000\u0000\u0000\u0098\u0091"+
		"\u0001\u0000\u0000\u0000\u0098\u0092\u0001\u0000\u0000\u0000\u0098\u0093"+
		"\u0001\u0000\u0000\u0000\u0098\u0094\u0001\u0000\u0000\u0000\u0098\u0095"+
		"\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0097"+
		"\u0001\u0000\u0000\u0000\u0099\r\u0001\u0000\u0000\u0000\u009a\u009b\u0005"+
		"\b\u0000\u0000\u009b\u00a0\u0003T*\u0000\u009c\u009d\u0005\u0002\u0000"+
		"\u0000\u009d\u009e\u0003*\u0015\u0000\u009e\u009f\u0005\u0003\u0000\u0000"+
		"\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u009c\u0001\u0000\u0000\u0000"+
		"\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u000f\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a3\u0005\t\u0000\u0000\u00a3\u00a4\u0007\u0001\u0000\u0000\u00a4"+
		"\u00ad\u0003T*\u0000\u00a5\u00a7\u0005\u0002\u0000\u0000\u00a6\u00a8\u0003"+
		".\u0017\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000"+
		"\u0000\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005\u0003"+
		"\u0000\u0000\u00ac\u00ae\u0001\u0000\u0000\u0000\u00ad\u00a5\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u0011\u0001\u0000"+
		"\u0000\u0000\u00af\u00b0\u0005\u0010\u0000\u0000\u00b0\u00b1\u0003\u0014"+
		"\n\u0000\u00b1\u00b3\u0005\u0002\u0000\u0000\u00b2\u00b4\u0003\u0016\u000b"+
		"\u0000\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b8\u0005\u0003\u0000"+
		"\u0000\u00b8\u0013\u0001\u0000\u0000\u0000\u00b9\u00ba\u0007\u0002\u0000"+
		"\u0000\u00ba\u0015\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\u0013\u0000"+
		"\u0000\u00bc\u00bd\u0003T*\u0000\u00bd\u0017\u0001\u0000\u0000\u0000\u00be"+
		"\u00c1\u0005\u0014\u0000\u0000\u00bf\u00c0\u0005\u0015\u0000\u0000\u00c0"+
		"\u00c2\u0003X,\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c5\u0005"+
		"\u0002\u0000\u0000\u00c4\u00c6\u0003\u001a\r\u0000\u00c5\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000"+
		"\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000"+
		"\u0000\u0000\u00c9\u00ca\u0005\u0003\u0000\u0000\u00ca\u0019\u0001\u0000"+
		"\u0000\u0000\u00cb\u00cc\u0005\u0016\u0000\u0000\u00cc\u00ce\u0005\u0002"+
		"\u0000\u0000\u00cd\u00cf\u0003\u001c\u000e\u0000\u00ce\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\u0005\u0003\u0000\u0000\u00d3\u001b\u0001\u0000"+
		"\u0000\u0000\u00d4\u00d6\u0005\u0017\u0000\u0000\u00d5\u00d7\u0003\u001e"+
		"\u000f\u0000\u00d6\u00d5\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000"+
		"\u0000\u0000\u00d7\u00d9\u0001\u0000\u0000\u0000\u00d8\u00da\u0003 \u0010"+
		"\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00d9\u00da\u0001\u0000\u0000"+
		"\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00df\u0005\u0002\u0000"+
		"\u0000\u00dc\u00de\u0003\f\u0006\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000"+
		"\u00de\u00e1\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000"+
		"\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00e2\u0001\u0000\u0000\u0000"+
		"\u00e1\u00df\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005\u0003\u0000\u0000"+
		"\u00e3\u001d\u0001\u0000\u0000\u0000\u00e4\u00e5\u0003X,\u0000\u00e5\u00e6"+
		"\u0005\u0018\u0000\u0000\u00e6\u001f\u0001\u0000\u0000\u0000\u00e7\u00e8"+
		"\u0003X,\u0000\u00e8\u00e9\u0005\u0019\u0000\u0000\u00e9!\u0001\u0000"+
		"\u0000\u0000\u00ea\u00eb\u0005\u001a\u0000\u0000\u00eb\u00f0\u0005]\u0000"+
		"\u0000\u00ec\u00ed\u0005\u0002\u0000\u0000\u00ed\u00ee\u00038\u001c\u0000"+
		"\u00ee\u00ef\u0005\u0003\u0000\u0000\u00ef\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f0\u00ec\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f1#\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005\u001b\u0000\u0000\u00f3"+
		"\u00f8\u0005]\u0000\u0000\u00f4\u00f5\u0005\u0002\u0000\u0000\u00f5\u00f6"+
		"\u0003<\u001e\u0000\u00f6\u00f7\u0005\u0003\u0000\u0000\u00f7\u00f9\u0001"+
		"\u0000\u0000\u0000\u00f8\u00f4\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001"+
		"\u0000\u0000\u0000\u00f9%\u0001\u0000\u0000\u0000\u00fa\u00ff\u0005\u001c"+
		"\u0000\u0000\u00fb\u00fc\u0005\u001d\u0000\u0000\u00fc\u00fd\u0003@ \u0000"+
		"\u00fd\u00fe\u0005\u001e\u0000\u0000\u00fe\u0100\u0001\u0000\u0000\u0000"+
		"\u00ff\u00fb\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000"+
		"\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u0105\u0005\u0002\u0000\u0000"+
		"\u0102\u0104\u0003\f\u0006\u0000\u0103\u0102\u0001\u0000\u0000\u0000\u0104"+
		"\u0107\u0001\u0000\u0000\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0105"+
		"\u0106\u0001\u0000\u0000\u0000\u0106\u0108\u0001\u0000\u0000\u0000\u0107"+
		"\u0105\u0001\u0000\u0000\u0000\u0108\u0109\u0005\u0003\u0000\u0000\u0109"+
		"\'\u0001\u0000\u0000\u0000\u010a\u010b\u0005\u001f\u0000\u0000\u010b\u010c"+
		"\u0005Y\u0000\u0000\u010c\u0110\u0005\u0002\u0000\u0000\u010d\u010f\u0003"+
		"\f\u0006\u0000\u010e\u010d\u0001\u0000\u0000\u0000\u010f\u0112\u0001\u0000"+
		"\u0000\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000"+
		"\u0000\u0000\u0111\u0113\u0001\u0000\u0000\u0000\u0112\u0110\u0001\u0000"+
		"\u0000\u0000\u0113\u0114\u0005\u0003\u0000\u0000\u0114\u0115\u0005 \u0000"+
		"\u0000\u0115\u011c\u0005Y\u0000\u0000\u0116\u0117\u0005b\u0000\u0000\u0117"+
		"\u0118\u0003X,\u0000\u0118\u0119\u0003Z-\u0000\u0119\u011a\u0003X,\u0000"+
		"\u011a\u011b\u0003Z-\u0000\u011b\u011d\u0001\u0000\u0000\u0000\u011c\u0116"+
		"\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d)\u0001"+
		"\u0000\u0000\u0000\u011e\u0120\u0003,\u0016\u0000\u011f\u011e\u0001\u0000"+
		"\u0000\u0000\u0120\u0121\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000"+
		"\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122+\u0001\u0000\u0000"+
		"\u0000\u0123\u0127\u0003.\u0017\u0000\u0124\u0127\u00030\u0018\u0000\u0125"+
		"\u0127\u00034\u001a\u0000\u0126\u0123\u0001\u0000\u0000\u0000\u0126\u0124"+
		"\u0001\u0000\u0000\u0000\u0126\u0125\u0001\u0000\u0000\u0000\u0127-\u0001"+
		"\u0000\u0000\u0000\u0128\u0129\u0005!\u0000\u0000\u0129\u0136\u0003T*"+
		"\u0000\u012a\u012b\u0005\"\u0000\u0000\u012b\u012c\u0003X,\u0000\u012c"+
		"\u012d\u0003Z-\u0000\u012d\u0136\u0001\u0000\u0000\u0000\u012e\u012f\u0005"+
		"#\u0000\u0000\u012f\u0136\u0003V+\u0000\u0130\u0136\u0005$\u0000\u0000"+
		"\u0131\u0136\u0005%\u0000\u0000\u0132\u0136\u0005&\u0000\u0000\u0133\u0134"+
		"\u0005\'\u0000\u0000\u0134\u0136\u0005Y\u0000\u0000\u0135\u0128\u0001"+
		"\u0000\u0000\u0000\u0135\u012a\u0001\u0000\u0000\u0000\u0135\u012e\u0001"+
		"\u0000\u0000\u0000\u0135\u0130\u0001\u0000\u0000\u0000\u0135\u0131\u0001"+
		"\u0000\u0000\u0000\u0135\u0132\u0001\u0000\u0000\u0000\u0135\u0133\u0001"+
		"\u0000\u0000\u0000\u0136/\u0001\u0000\u0000\u0000\u0137\u0138\u0005\'"+
		"\u0000\u0000\u0138\u0139\u00032\u0019\u0000\u01391\u0001\u0000\u0000\u0000"+
		"\u013a\u013b\u0007\u0003\u0000\u0000\u013b3\u0001\u0000\u0000\u0000\u013c"+
		"\u0140\u00036\u001b\u0000\u013d\u013e\u0003X,\u0000\u013e\u013f\u0003"+
		"Z-\u0000\u013f\u0141\u0001\u0000\u0000\u0000\u0140\u013d\u0001\u0000\u0000"+
		"\u0000\u0141\u0142\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000"+
		"\u0000\u0142\u0143\u0001\u0000\u0000\u0000\u01435\u0001\u0000\u0000\u0000"+
		"\u0144\u0145\u0007\u0004\u0000\u0000\u01457\u0001\u0000\u0000\u0000\u0146"+
		"\u0148\u0003:\u001d\u0000\u0147\u0146\u0001\u0000\u0000\u0000\u0148\u0149"+
		"\u0001\u0000\u0000\u0000\u0149\u0147\u0001\u0000\u0000\u0000\u0149\u014a"+
		"\u0001\u0000\u0000\u0000\u014a9\u0001\u0000\u0000\u0000\u014b\u014f\u0003"+
		"D\"\u0000\u014c\u014f\u0003H$\u0000\u014d\u014f\u0003L&\u0000\u014e\u014b"+
		"\u0001\u0000\u0000\u0000\u014e\u014c\u0001\u0000\u0000\u0000\u014e\u014d"+
		"\u0001\u0000\u0000\u0000\u014f;\u0001\u0000\u0000\u0000\u0150\u0152\u0003"+
		">\u001f\u0000\u0151\u0150\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000"+
		"\u0000\u0000\u0153\u0151\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000"+
		"\u0000\u0000\u0154=\u0001\u0000\u0000\u0000\u0155\u0158\u0003D\"\u0000"+
		"\u0156\u0158\u0003N\'\u0000\u0157\u0155\u0001\u0000\u0000\u0000\u0157"+
		"\u0156\u0001\u0000\u0000\u0000\u0158?\u0001\u0000\u0000\u0000\u0159\u015b"+
		"\u0003B!\u0000\u015a\u0159\u0001\u0000\u0000\u0000\u015b\u015c\u0001\u0000"+
		"\u0000\u0000\u015c\u015a\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000"+
		"\u0000\u0000\u015dA\u0001\u0000\u0000\u0000\u015e\u0164\u0003D\"\u0000"+
		"\u015f\u0164\u0003F#\u0000\u0160\u0164\u0003H$\u0000\u0161\u0164\u0003"+
		"4\u001a\u0000\u0162\u0164\u0003P(\u0000\u0163\u015e\u0001\u0000\u0000"+
		"\u0000\u0163\u015f\u0001\u0000\u0000\u0000\u0163\u0160\u0001\u0000\u0000"+
		"\u0000\u0163\u0161\u0001\u0000\u0000\u0000\u0163\u0162\u0001\u0000\u0000"+
		"\u0000\u0164C\u0001\u0000\u0000\u0000\u0165\u0166\u0005.\u0000\u0000\u0166"+
		"\u0167\u0003X,\u0000\u0167\u0168\u0003Z-\u0000\u0168\u0174\u0001\u0000"+
		"\u0000\u0000\u0169\u016a\u0005/\u0000\u0000\u016a\u016b\u0003X,\u0000"+
		"\u016b\u016c\u0003Z-\u0000\u016c\u0174\u0001\u0000\u0000\u0000\u016d\u016e"+
		"\u0005\"\u0000\u0000\u016e\u016f\u0003X,\u0000\u016f\u0170\u0003Z-\u0000"+
		"\u0170\u0171\u0003X,\u0000\u0171\u0172\u0003Z-\u0000\u0172\u0174\u0001"+
		"\u0000\u0000\u0000\u0173\u0165\u0001\u0000\u0000\u0000\u0173\u0169\u0001"+
		"\u0000\u0000\u0000\u0173\u016d\u0001\u0000\u0000\u0000\u0174E\u0001\u0000"+
		"\u0000\u0000\u0175\u0176\u00050\u0000\u0000\u0176\u0177\u0003V+\u0000"+
		"\u0177G\u0001\u0000\u0000\u0000\u0178\u0179\u00051\u0000\u0000\u0179\u017a"+
		"\u0003X,\u0000\u017a\u017b\u0003Z-\u0000\u017b\u017d\u0003V+\u0000\u017c"+
		"\u017e\u0003J%\u0000\u017d\u017c\u0001\u0000\u0000\u0000\u017d\u017e\u0001"+
		"\u0000\u0000\u0000\u017eI\u0001\u0000\u0000\u0000\u017f\u0180\u00052\u0000"+
		"\u0000\u0180\u0181\u0003X,\u0000\u0181\u0182\u0003Z-\u0000\u0182K\u0001"+
		"\u0000\u0000\u0000\u0183\u0184\u00053\u0000\u0000\u0184\u0185\u0003X,"+
		"\u0000\u0185M\u0001\u0000\u0000\u0000\u0186\u0187\u00054\u0000\u0000\u0187"+
		"\u0188\u0003X,\u0000\u0188O\u0001\u0000\u0000\u0000\u0189\u018a\u0005"+
		"5\u0000\u0000\u018a\u018b\u0003R)\u0000\u018bQ\u0001\u0000\u0000\u0000"+
		"\u018c\u018d\u0007\u0005\u0000\u0000\u018dS\u0001\u0000\u0000\u0000\u018e"+
		"\u018f\u0005\\\u0000\u0000\u018fU\u0001\u0000\u0000\u0000\u0190\u0191"+
		"\u0005_\u0000\u0000\u0191W\u0001\u0000\u0000\u0000\u0192\u0193\u0005^"+
		"\u0000\u0000\u0193Y\u0001\u0000\u0000\u0000\u0194\u0195\u0007\u0006\u0000"+
		"\u0000\u0195[\u0001\u0000\u0000\u0000\u0196\u0197\u0005<\u0000\u0000\u0197"+
		"\u0198\u0005[\u0000\u0000\u0198\u0199\u0005\u0003\u0000\u0000\u0199]\u0001"+
		"\u0000\u0000\u0000\"dlou|\u0098\u00a0\u00a9\u00ad\u00b5\u00c1\u00c7\u00d0"+
		"\u00d6\u00d9\u00df\u00f0\u00f8\u00ff\u0105\u0110\u011c\u0121\u0126\u0135"+
		"\u0142\u0149\u014e\u0153\u0157\u015c\u0163\u0173\u017d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}