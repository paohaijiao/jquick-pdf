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
    static {
        RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
            T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
            T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, T__28 = 29, T__29 = 30, T__30 = 31,
            T__31 = 32, T__32 = 33, T__33 = 34, T__34 = 35, T__35 = 36, T__36 = 37, T__37 = 38,
            T__38 = 39, T__39 = 40, T__40 = 41, T__41 = 42, T__42 = 43, T__43 = 44, T__44 = 45,
            T__45 = 46, T__46 = 47, T__47 = 48, T__48 = 49, COLON = 50, SEMICOLON = 51, DEFAULT = 52,
            EXECUTIVE = 53, LEDGER = 54, LEGAL = 55, LETTER = 56, TABLOID = 57, AUTO = 58, DOCTYPE = 59,
            NUMBER = 60, IDENTIFIER = 61, COLOR = 62, STRING = 63, WS = 64, COMMENT = 65, AT = 66;
    public static final int
            RULE_document = 0, RULE_doc = 1, RULE_docType = 2, RULE_html = 3, RULE_head = 4,
            RULE_headStyle = 5, RULE_headStyleOption = 6, RULE_bodyStyleOption = 7,
            RULE_body = 8, RULE_element = 9, RULE_paragraph = 10, RULE_heading = 11,
            RULE_list = 12, RULE_listEle = 13, RULE_listItem = 14, RULE_table = 15,
            RULE_row = 16, RULE_col = 17, RULE_th = 18, RULE_td = 19, RULE_image = 20,
            RULE_src = 21, RULE_alt = 22, RULE_svg = 23, RULE_div = 24, RULE_template = 25,
            RULE_styleEle = 26, RULE_style = 27, RULE_attr = 28, RULE_key = 29, RULE_value = 30,
            RULE_string = 31, RULE_color = 32, RULE_number = 33, RULE_unit = 34, RULE_variable = 35;

    private static String[] makeRuleNames() {
        return new String[]{
                "document", "doc", "docType", "html", "head", "headStyle", "headStyleOption",
                "bodyStyleOption", "body", "element", "paragraph", "heading", "list",
                "listEle", "listItem", "table", "row", "col", "th", "td", "image", "src",
                "alt", "svg", "div", "template", "styleEle", "style", "attr", "key",
                "value", "string", "color", "number", "unit", "variable"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'<html>'", "'</html>'", "'<head>'", "'</head>'", "'<style>'",
                "'</style>'", "'@page'", "'{'", "'}'", "'<body>'", "'</body>'", "'<p'",
                "'>'", "'</p>'", "'<h'", "'</h'", "'<'", "'</'", "'ol'", "'ul'", "'<li'",
                "'</li>'", "'<table'", "'</table>'", "'<tr'", "'</tr>'", "'<th'", "'</th>'",
                "'<td'", "'</td>'", "'<image'", "'</image>'", "'src'", "'='", "'alt'",
                "'<svg'", "'</svg>'", "'<div'", "'</div>'", "'template'", "'use'", "'style='",
                "'px'", "'pt'", "'mm'", "'cm'", "'in'", "'%'", "'${'", "':'", "';'",
                "'DEFAULT'", "'EXECUTIVE'", "'LEDGER'", "'LEGAL'", "'LETTER'", "'TABLOID'",
                "'auto'", "'<!DOCTYPE html>'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, "COLON", "SEMICOLON", "DEFAULT", "EXECUTIVE", "LEDGER", "LEGAL",
                "LETTER", "TABLOID", "AUTO", "DOCTYPE", "NUMBER", "IDENTIFIER", "COLOR",
                "STRING", "WS", "COMMENT", "AT"
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
    public String getGrammarFileName() {
        return "JQuickPDF.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public JQuickPDFParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DocumentContext extends ParserRuleContext {
        public DocContext doc() {
            return getRuleContext(DocContext.class, 0);
        }

        public DocumentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_document;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterDocument(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitDocument(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitDocument(this);
            else return visitor.visitChildren(this);
        }
    }

    public final DocumentContext document() throws RecognitionException {
        DocumentContext _localctx = new DocumentContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_document);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(72);
                doc();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DocContext extends ParserRuleContext {
        public HtmlContext html() {
            return getRuleContext(HtmlContext.class, 0);
        }

        public DocTypeContext docType() {
            return getRuleContext(DocTypeContext.class, 0);
        }

        public DocContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_doc;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterDoc(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitDoc(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitDoc(this);
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
                setState(75);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == DOCTYPE) {
                    {
                        setState(74);
                        docType();
                    }
                }

                setState(77);
                html();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DocTypeContext extends ParserRuleContext {
        public TerminalNode DOCTYPE() {
            return getToken(JQuickPDFParser.DOCTYPE, 0);
        }

        public DocTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_docType;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterDocType(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitDocType(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitDocType(this);
            else return visitor.visitChildren(this);
        }
    }

    public final DocTypeContext docType() throws RecognitionException {
        DocTypeContext _localctx = new DocTypeContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_docType);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(79);
                match(DOCTYPE);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlContext extends ParserRuleContext {
        public HeadContext head() {
            return getRuleContext(HeadContext.class, 0);
        }

        public BodyContext body() {
            return getRuleContext(BodyContext.class, 0);
        }

        public HtmlContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_html;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterHtml(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitHtml(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitHtml(this);
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
                setState(81);
                match(T__0);
                setState(83);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__2) {
                    {
                        setState(82);
                        head();
                    }
                }

                setState(86);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__9) {
                    {
                        setState(85);
                        body();
                    }
                }

                setState(88);
                match(T__1);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HeadContext extends ParserRuleContext {
        public HeadStyleContext headStyle() {
            return getRuleContext(HeadStyleContext.class, 0);
        }

        public HeadContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_head;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterHead(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitHead(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitHead(this);
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
                setState(90);
                match(T__2);
                setState(92);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__4) {
                    {
                        setState(91);
                        headStyle();
                    }
                }

                setState(94);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HeadStyleContext extends ParserRuleContext {
        public HeadStyleOptionContext headStyleOption() {
            return getRuleContext(HeadStyleOptionContext.class, 0);
        }

        public BodyStyleOptionContext bodyStyleOption() {
            return getRuleContext(BodyStyleOptionContext.class, 0);
        }

        public HeadStyleContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_headStyle;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterHeadStyle(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitHeadStyle(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitHeadStyle(this);
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
                setState(96);
                match(T__4);
                {
                    setState(98);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == T__6) {
                        {
                            setState(97);
                            headStyleOption();
                        }
                    }

                    setState(101);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == IDENTIFIER) {
                        {
                            setState(100);
                            bodyStyleOption();
                        }
                    }

                }
                setState(103);
                match(T__5);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
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
            return getRuleContext(StyleContext.class, i);
        }

        public HeadStyleOptionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_headStyleOption;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterHeadStyleOption(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitHeadStyleOption(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitHeadStyleOption(this);
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
                setState(105);
                match(T__6);
                setState(106);
                match(T__7);
                setState(110);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == IDENTIFIER || _la == STRING) {
                    {
                        {
                            setState(107);
                            style();
                        }
                    }
                    setState(112);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(113);
                match(T__8);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BodyStyleOptionContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return getToken(JQuickPDFParser.IDENTIFIER, 0);
        }

        public List<StyleContext> style() {
            return getRuleContexts(StyleContext.class);
        }

        public StyleContext style(int i) {
            return getRuleContext(StyleContext.class, i);
        }

        public BodyStyleOptionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_bodyStyleOption;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterBodyStyleOption(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitBodyStyleOption(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitBodyStyleOption(this);
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
                setState(115);
                match(IDENTIFIER);
                setState(116);
                match(T__7);
                setState(120);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == IDENTIFIER || _la == STRING) {
                    {
                        {
                            setState(117);
                            style();
                        }
                    }
                    setState(122);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(123);
                match(T__8);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
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
            return getRuleContext(ElementContext.class, i);
        }

        public BodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_body;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterBody(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitBody(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitBody(this);
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
                setState(125);
                match(T__9);
                setState(132);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 9, _ctx)) {
                    case 1: {
                        setState(129);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1445265051648L) != 0)) {
                            {
                                {
                                    setState(126);
                                    element();
                                }
                            }
                            setState(131);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                    }
                    break;
                }
                setState(134);
                match(T__10);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ElementContext extends ParserRuleContext {
        public ParagraphContext paragraph() {
            return getRuleContext(ParagraphContext.class, 0);
        }

        public HeadingContext heading() {
            return getRuleContext(HeadingContext.class, 0);
        }

        public ListContext list() {
            return getRuleContext(ListContext.class, 0);
        }

        public TableContext table() {
            return getRuleContext(TableContext.class, 0);
        }

        public ImageContext image() {
            return getRuleContext(ImageContext.class, 0);
        }

        public SvgContext svg() {
            return getRuleContext(SvgContext.class, 0);
        }

        public DivContext div() {
            return getRuleContext(DivContext.class, 0);
        }

        public TemplateContext template() {
            return getRuleContext(TemplateContext.class, 0);
        }

        public ElementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_element;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterElement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitElement(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitElement(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ElementContext element() throws RecognitionException {
        ElementContext _localctx = new ElementContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_element);
        try {
            setState(144);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__11:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(136);
                    paragraph();
                }
                break;
                case T__14:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(137);
                    heading();
                }
                break;
                case T__16:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(138);
                    list();
                }
                break;
                case T__22:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(139);
                    table();
                }
                break;
                case T__30:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(140);
                    image();
                }
                break;
                case T__35:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(141);
                    svg();
                }
                break;
                case T__37:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(142);
                    div();
                }
                break;
                case T__39:
                    enterOuterAlt(_localctx, 8);
                {
                    setState(143);
                    template();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ParagraphContext extends ParserRuleContext {
        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public ParagraphContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_paragraph;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterParagraph(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitParagraph(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitParagraph(this);
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
                setState(146);
                match(T__11);
                setState(148);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(147);
                        styleEle();
                    }
                }

                setState(150);
                match(T__12);
                setState(152);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -5764044573080813568L) != 0)) {
                    {
                        setState(151);
                        value();
                    }
                }

                setState(154);
                match(T__13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
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
            return getRuleContext(NumberContext.class, i);
        }

        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public HeadingContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_heading;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterHeading(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitHeading(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitHeading(this);
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
                setState(156);
                match(T__14);
                setState(157);
                number();
                setState(159);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(158);
                        styleEle();
                    }
                }

                setState(161);
                match(T__12);
                setState(163);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -5764044573080813568L) != 0)) {
                    {
                        setState(162);
                        value();
                    }
                }

                setState(165);
                match(T__15);
                setState(166);
                number();
                setState(168);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__12) {
                    {
                        setState(167);
                        match(T__12);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
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
            return getRuleContext(ListEleContext.class, i);
        }

        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public List<ListItemContext> listItem() {
            return getRuleContexts(ListItemContext.class);
        }

        public ListItemContext listItem(int i) {
            return getRuleContext(ListItemContext.class, i);
        }

        public ListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_list;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterList(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitList(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitList(this);
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
                setState(170);
                match(T__16);
                setState(171);
                listEle();
                setState(173);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(172);
                        styleEle();
                    }
                }

                setState(175);
                match(T__12);
                setState(179);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__20) {
                    {
                        {
                            setState(176);
                            listItem();
                        }
                    }
                    setState(181);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(182);
                match(T__17);
                setState(183);
                listEle();
                setState(184);
                match(T__12);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ListEleContext extends ParserRuleContext {
        public ListEleContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_listEle;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterListEle(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitListEle(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitListEle(this);
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
                setState(186);
                _la = _input.LA(1);
                if (!(_la == T__18 || _la == T__19)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ListItemContext extends ParserRuleContext {
        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public ListItemContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_listItem;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterListItem(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitListItem(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitListItem(this);
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
                setState(188);
                match(T__20);
                setState(190);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(189);
                        styleEle();
                    }
                }

                setState(192);
                match(T__12);
                setState(194);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -5764044573080813568L) != 0)) {
                    {
                        setState(193);
                        value();
                    }
                }

                setState(196);
                match(T__21);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TableContext extends ParserRuleContext {
        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public List<RowContext> row() {
            return getRuleContexts(RowContext.class);
        }

        public RowContext row(int i) {
            return getRuleContext(RowContext.class, i);
        }

        public TableContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_table;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterTable(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitTable(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitTable(this);
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
                setState(198);
                match(T__22);
                setState(200);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(199);
                        styleEle();
                    }
                }

                setState(202);
                match(T__12);
                setState(206);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__24) {
                    {
                        {
                            setState(203);
                            row();
                        }
                    }
                    setState(208);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(209);
                match(T__23);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class RowContext extends ParserRuleContext {
        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public List<ColContext> col() {
            return getRuleContexts(ColContext.class);
        }

        public ColContext col(int i) {
            return getRuleContext(ColContext.class, i);
        }

        public RowContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_row;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterRow(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitRow(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitRow(this);
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
                setState(211);
                match(T__24);
                setState(213);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(212);
                        styleEle();
                    }
                }

                setState(215);
                match(T__12);
                setState(219);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__26 || _la == T__28) {
                    {
                        {
                            setState(216);
                            col();
                        }
                    }
                    setState(221);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(222);
                match(T__25);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ColContext extends ParserRuleContext {
        public ThContext th() {
            return getRuleContext(ThContext.class, 0);
        }

        public TdContext td() {
            return getRuleContext(TdContext.class, 0);
        }

        public ColContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_col;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterCol(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitCol(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitCol(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ColContext col() throws RecognitionException {
        ColContext _localctx = new ColContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_col);
        try {
            setState(226);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__26:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(224);
                    th();
                }
                break;
                case T__28:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(225);
                    td();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ThContext extends ParserRuleContext {
        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public ThContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_th;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterTh(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitTh(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitTh(this);
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
                setState(228);
                match(T__26);
                setState(230);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(229);
                        styleEle();
                    }
                }

                setState(232);
                match(T__12);
                setState(233);
                value();
                setState(234);
                match(T__27);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TdContext extends ParserRuleContext {
        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public TdContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_td;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterTd(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitTd(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitTd(this);
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
                setState(236);
                match(T__28);
                setState(238);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(237);
                        styleEle();
                    }
                }

                setState(240);
                match(T__12);
                setState(241);
                value();
                setState(242);
                match(T__29);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ImageContext extends ParserRuleContext {
        public SrcContext src() {
            return getRuleContext(SrcContext.class, 0);
        }

        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public AltContext alt() {
            return getRuleContext(AltContext.class, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public ImageContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_image;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterImage(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitImage(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitImage(this);
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
                setState(244);
                match(T__30);
                setState(246);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__32) {
                    {
                        setState(245);
                        src();
                    }
                }

                setState(249);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(248);
                        styleEle();
                    }
                }

                setState(252);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__34) {
                    {
                        setState(251);
                        alt();
                    }
                }

                setState(254);
                match(T__12);
                setState(256);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -5764044573080813568L) != 0)) {
                    {
                        setState(255);
                        value();
                    }
                }

                setState(258);
                match(T__31);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SrcContext extends ParserRuleContext {
        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public SrcContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_src;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterSrc(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitSrc(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitSrc(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SrcContext src() throws RecognitionException {
        SrcContext _localctx = new SrcContext(_ctx, getState());
        enterRule(_localctx, 42, RULE_src);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(260);
                match(T__32);
                setState(261);
                match(T__33);
                setState(262);
                value();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AltContext extends ParserRuleContext {
        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public AltContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_alt;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterAlt(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitAlt(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitAlt(this);
            else return visitor.visitChildren(this);
        }
    }

    public final AltContext alt() throws RecognitionException {
        AltContext _localctx = new AltContext(_ctx, getState());
        enterRule(_localctx, 44, RULE_alt);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(264);
                match(T__34);
                setState(265);
                match(T__33);
                setState(266);
                value();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SvgContext extends ParserRuleContext {
        public SrcContext src() {
            return getRuleContext(SrcContext.class, 0);
        }

        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public SvgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_svg;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterSvg(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitSvg(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitSvg(this);
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
                setState(268);
                match(T__35);
                setState(270);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__32) {
                    {
                        setState(269);
                        src();
                    }
                }

                setState(273);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(272);
                        styleEle();
                    }
                }

                setState(275);
                match(T__12);
                setState(277);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -5764044573080813568L) != 0)) {
                    {
                        setState(276);
                        value();
                    }
                }

                setState(279);
                match(T__36);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DivContext extends ParserRuleContext {
        public StyleEleContext styleEle() {
            return getRuleContext(StyleEleContext.class, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public List<ElementContext> element() {
            return getRuleContexts(ElementContext.class);
        }

        public ElementContext element(int i) {
            return getRuleContext(ElementContext.class, i);
        }

        public DivContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_div;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterDiv(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitDiv(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitDiv(this);
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
                setState(281);
                match(T__37);
                setState(283);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__41) {
                    {
                        setState(282);
                        styleEle();
                    }
                }

                setState(285);
                match(T__12);
                setState(292);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 36, _ctx)) {
                    case 1: {
                        setState(289);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1445265051648L) != 0)) {
                            {
                                {
                                    setState(286);
                                    element();
                                }
                            }
                            setState(291);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                    }
                    break;
                }
                setState(295);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -5764044573080813568L) != 0)) {
                    {
                        setState(294);
                        value();
                    }
                }

                setState(297);
                match(T__38);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TemplateContext extends ParserRuleContext {
        public List<TerminalNode> IDENTIFIER() {
            return getTokens(JQuickPDFParser.IDENTIFIER);
        }

        public TerminalNode IDENTIFIER(int i) {
            return getToken(JQuickPDFParser.IDENTIFIER, i);
        }

        public List<ElementContext> element() {
            return getRuleContexts(ElementContext.class);
        }

        public ElementContext element(int i) {
            return getRuleContext(ElementContext.class, i);
        }

        public TerminalNode AT() {
            return getToken(JQuickPDFParser.AT, 0);
        }

        public List<NumberContext> number() {
            return getRuleContexts(NumberContext.class);
        }

        public NumberContext number(int i) {
            return getRuleContext(NumberContext.class, i);
        }

        public List<UnitContext> unit() {
            return getRuleContexts(UnitContext.class);
        }

        public UnitContext unit(int i) {
            return getRuleContext(UnitContext.class, i);
        }

        public TemplateContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_template;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterTemplate(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitTemplate(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitTemplate(this);
            else return visitor.visitChildren(this);
        }
    }

    public final TemplateContext template() throws RecognitionException {
        TemplateContext _localctx = new TemplateContext(_ctx, getState());
        enterRule(_localctx, 50, RULE_template);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(299);
                match(T__39);
                setState(300);
                match(IDENTIFIER);
                setState(301);
                match(T__7);
                setState(305);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1445265051648L) != 0)) {
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
                setState(308);
                match(T__8);
                setState(309);
                match(T__40);
                setState(310);
                match(IDENTIFIER);
                setState(317);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == AT) {
                    {
                        setState(311);
                        match(AT);
                        setState(312);
                        number();
                        setState(313);
                        unit();
                        setState(314);
                        number();
                        setState(315);
                        unit();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StyleEleContext extends ParserRuleContext {
        public TerminalNode STRING() {
            return getToken(JQuickPDFParser.STRING, 0);
        }

        public StyleContext style() {
            return getRuleContext(StyleContext.class, 0);
        }

        public StyleEleContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_styleEle;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterStyleEle(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitStyleEle(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitStyleEle(this);
            else return visitor.visitChildren(this);
        }
    }

    public final StyleEleContext styleEle() throws RecognitionException {
        StyleEleContext _localctx = new StyleEleContext(_ctx, getState());
        enterRule(_localctx, 52, RULE_styleEle);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(319);
                match(T__41);
                setState(321);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 40, _ctx)) {
                    case 1: {
                        setState(320);
                        match(STRING);
                    }
                    break;
                }
                setState(324);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == IDENTIFIER || _la == STRING) {
                    {
                        setState(323);
                        style();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
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
            return getRuleContext(AttrContext.class, i);
        }

        public List<TerminalNode> SEMICOLON() {
            return getTokens(JQuickPDFParser.SEMICOLON);
        }

        public TerminalNode SEMICOLON(int i) {
            return getToken(JQuickPDFParser.SEMICOLON, i);
        }

        public StyleContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_style;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterStyle(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitStyle(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitStyle(this);
            else return visitor.visitChildren(this);
        }
    }

    public final StyleContext style() throws RecognitionException {
        StyleContext _localctx = new StyleContext(_ctx, getState());
        enterRule(_localctx, 54, RULE_style);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(326);
                attr();
                setState(333);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 43, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(328);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la == SEMICOLON) {
                                    {
                                        setState(327);
                                        match(SEMICOLON);
                                    }
                                }

                                setState(330);
                                attr();
                            }
                        }
                    }
                    setState(335);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 43, _ctx);
                }
                setState(337);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == SEMICOLON) {
                    {
                        setState(336);
                        match(SEMICOLON);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AttrContext extends ParserRuleContext {
        public KeyContext key() {
            return getRuleContext(KeyContext.class, 0);
        }

        public TerminalNode COLON() {
            return getToken(JQuickPDFParser.COLON, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public AttrContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_attr;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterAttr(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitAttr(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitAttr(this);
            else return visitor.visitChildren(this);
        }
    }

    public final AttrContext attr() throws RecognitionException {
        AttrContext _localctx = new AttrContext(_ctx, getState());
        enterRule(_localctx, 56, RULE_attr);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(339);
                key();
                setState(340);
                match(COLON);
                setState(341);
                value();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class KeyContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return getToken(JQuickPDFParser.IDENTIFIER, 0);
        }

        public StringContext string() {
            return getRuleContext(StringContext.class, 0);
        }

        public KeyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_key;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterKey(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitKey(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitKey(this);
            else return visitor.visitChildren(this);
        }
    }

    public final KeyContext key() throws RecognitionException {
        KeyContext _localctx = new KeyContext(_ctx, getState());
        enterRule(_localctx, 58, RULE_key);
        try {
            setState(345);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case IDENTIFIER:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(343);
                    match(IDENTIFIER);
                }
                break;
                case STRING:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(344);
                    string();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ValueContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return getToken(JQuickPDFParser.IDENTIFIER, 0);
        }

        public StringContext string() {
            return getRuleContext(StringContext.class, 0);
        }

        public VariableContext variable() {
            return getRuleContext(VariableContext.class, 0);
        }

        public NumberContext number() {
            return getRuleContext(NumberContext.class, 0);
        }

        public ValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_value;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitValue(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitValue(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ValueContext value() throws RecognitionException {
        ValueContext _localctx = new ValueContext(_ctx, getState());
        enterRule(_localctx, 60, RULE_value);
        try {
            setState(351);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case IDENTIFIER:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(347);
                    match(IDENTIFIER);
                }
                break;
                case STRING:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(348);
                    string();
                }
                break;
                case T__48:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(349);
                    variable();
                }
                break;
                case NUMBER:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(350);
                    number();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StringContext extends ParserRuleContext {
        public TerminalNode STRING() {
            return getToken(JQuickPDFParser.STRING, 0);
        }

        public StringContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_string;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterString(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitString(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitString(this);
            else return visitor.visitChildren(this);
        }
    }

    public final StringContext string() throws RecognitionException {
        StringContext _localctx = new StringContext(_ctx, getState());
        enterRule(_localctx, 62, RULE_string);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(353);
                match(STRING);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ColorContext extends ParserRuleContext {
        public TerminalNode COLOR() {
            return getToken(JQuickPDFParser.COLOR, 0);
        }

        public ColorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_color;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterColor(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitColor(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitColor(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ColorContext color() throws RecognitionException {
        ColorContext _localctx = new ColorContext(_ctx, getState());
        enterRule(_localctx, 64, RULE_color);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(355);
                match(COLOR);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class NumberContext extends ParserRuleContext {
        public TerminalNode NUMBER() {
            return getToken(JQuickPDFParser.NUMBER, 0);
        }

        public NumberContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_number;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterNumber(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitNumber(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitNumber(this);
            else return visitor.visitChildren(this);
        }
    }

    public final NumberContext number() throws RecognitionException {
        NumberContext _localctx = new NumberContext(_ctx, getState());
        enterRule(_localctx, 66, RULE_number);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(357);
                match(NUMBER);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class UnitContext extends ParserRuleContext {
        public UnitContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_unit;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterUnit(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitUnit(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor) return ((JQuickPDFVisitor<? extends T>) visitor).visitUnit(this);
            else return visitor.visitChildren(this);
        }
    }

    public final UnitContext unit() throws RecognitionException {
        UnitContext _localctx = new UnitContext(_ctx, getState());
        enterRule(_localctx, 68, RULE_unit);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(359);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & 554153860399104L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class VariableContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return getToken(JQuickPDFParser.IDENTIFIER, 0);
        }

        public VariableContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variable;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).enterVariable(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JQuickPDFListener) ((JQuickPDFListener) listener).exitVariable(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JQuickPDFVisitor)
                return ((JQuickPDFVisitor<? extends T>) visitor).visitVariable(this);
            else return visitor.visitChildren(this);
        }
    }

    public final VariableContext variable() throws RecognitionException {
        VariableContext _localctx = new VariableContext(_ctx, getState());
        enterRule(_localctx, 70, RULE_variable);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(361);
                match(T__48);
                setState(362);
                match(IDENTIFIER);
                setState(363);
                match(T__8);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static final String _serializedATN =
            "\u0004\u0001B\u016e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002" +
                    "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
                    "\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002" +
                    "\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002" +
                    "\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f" +
                    "\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012" +
                    "\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015" +
                    "\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018" +
                    "\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b" +
                    "\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e" +
                    "\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002" +
                    "#\u0007#\u0001\u0000\u0001\u0000\u0001\u0001\u0003\u0001L\b\u0001\u0001" +
                    "\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0003" +
                    "\u0003T\b\u0003\u0001\u0003\u0003\u0003W\b\u0003\u0001\u0003\u0001\u0003" +
                    "\u0001\u0004\u0001\u0004\u0003\u0004]\b\u0004\u0001\u0004\u0001\u0004" +
                    "\u0001\u0005\u0001\u0005\u0003\u0005c\b\u0005\u0001\u0005\u0003\u0005" +
                    "f\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006" +
                    "\u0005\u0006m\b\u0006\n\u0006\f\u0006p\t\u0006\u0001\u0006\u0001\u0006" +
                    "\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007w\b\u0007\n\u0007\f\u0007" +
                    "z\t\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0005\b\u0080\b\b\n" +
                    "\b\f\b\u0083\t\b\u0003\b\u0085\b\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001" +
                    "\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0091\b\t\u0001\n\u0001" +
                    "\n\u0003\n\u0095\b\n\u0001\n\u0001\n\u0003\n\u0099\b\n\u0001\n\u0001\n" +
                    "\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00a0\b\u000b\u0001\u000b" +
                    "\u0001\u000b\u0003\u000b\u00a4\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b" +
                    "\u0003\u000b\u00a9\b\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u00ae\b\f\u0001" +
                    "\f\u0001\f\u0005\f\u00b2\b\f\n\f\f\f\u00b5\t\f\u0001\f\u0001\f\u0001\f" +
                    "\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e\u00bf\b\u000e" +
                    "\u0001\u000e\u0001\u000e\u0003\u000e\u00c3\b\u000e\u0001\u000e\u0001\u000e" +
                    "\u0001\u000f\u0001\u000f\u0003\u000f\u00c9\b\u000f\u0001\u000f\u0001\u000f" +
                    "\u0005\u000f\u00cd\b\u000f\n\u000f\f\u000f\u00d0\t\u000f\u0001\u000f\u0001" +
                    "\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u00d6\b\u0010\u0001\u0010\u0001" +
                    "\u0010\u0005\u0010\u00da\b\u0010\n\u0010\f\u0010\u00dd\t\u0010\u0001\u0010" +
                    "\u0001\u0010\u0001\u0011\u0001\u0011\u0003\u0011\u00e3\b\u0011\u0001\u0012" +
                    "\u0001\u0012\u0003\u0012\u00e7\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012" +
                    "\u0001\u0012\u0001\u0013\u0001\u0013\u0003\u0013\u00ef\b\u0013\u0001\u0013" +
                    "\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0003\u0014" +
                    "\u00f7\b\u0014\u0001\u0014\u0003\u0014\u00fa\b\u0014\u0001\u0014\u0003" +
                    "\u0014\u00fd\b\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0101\b\u0014" +
                    "\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015" +
                    "\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017" +
                    "\u0003\u0017\u010f\b\u0017\u0001\u0017\u0003\u0017\u0112\b\u0017\u0001" +
                    "\u0017\u0001\u0017\u0003\u0017\u0116\b\u0017\u0001\u0017\u0001\u0017\u0001" +
                    "\u0018\u0001\u0018\u0003\u0018\u011c\b\u0018\u0001\u0018\u0001\u0018\u0005" +
                    "\u0018\u0120\b\u0018\n\u0018\f\u0018\u0123\t\u0018\u0003\u0018\u0125\b" +
                    "\u0018\u0001\u0018\u0003\u0018\u0128\b\u0018\u0001\u0018\u0001\u0018\u0001" +
                    "\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u0130\b\u0019\n" +
                    "\u0019\f\u0019\u0133\t\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001" +
                    "\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003" +
                    "\u0019\u013e\b\u0019\u0001\u001a\u0001\u001a\u0003\u001a\u0142\b\u001a" +
                    "\u0001\u001a\u0003\u001a\u0145\b\u001a\u0001\u001b\u0001\u001b\u0003\u001b" +
                    "\u0149\b\u001b\u0001\u001b\u0005\u001b\u014c\b\u001b\n\u001b\f\u001b\u014f" +
                    "\t\u001b\u0001\u001b\u0003\u001b\u0152\b\u001b\u0001\u001c\u0001\u001c" +
                    "\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0003\u001d\u015a\b\u001d" +
                    "\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0160\b\u001e" +
                    "\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001" +
                    "#\u0001#\u0001#\u0001#\u0001#\u0000\u0000$\u0000\u0002\u0004\u0006\b\n" +
                    "\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0246" +
                    "8:<>@BDF\u0000\u0002\u0001\u0000\u0013\u0014\u0001\u0000+0\u0180\u0000" +
                    "H\u0001\u0000\u0000\u0000\u0002K\u0001\u0000\u0000\u0000\u0004O\u0001" +
                    "\u0000\u0000\u0000\u0006Q\u0001\u0000\u0000\u0000\bZ\u0001\u0000\u0000" +
                    "\u0000\n`\u0001\u0000\u0000\u0000\fi\u0001\u0000\u0000\u0000\u000es\u0001" +
                    "\u0000\u0000\u0000\u0010}\u0001\u0000\u0000\u0000\u0012\u0090\u0001\u0000" +
                    "\u0000\u0000\u0014\u0092\u0001\u0000\u0000\u0000\u0016\u009c\u0001\u0000" +
                    "\u0000\u0000\u0018\u00aa\u0001\u0000\u0000\u0000\u001a\u00ba\u0001\u0000" +
                    "\u0000\u0000\u001c\u00bc\u0001\u0000\u0000\u0000\u001e\u00c6\u0001\u0000" +
                    "\u0000\u0000 \u00d3\u0001\u0000\u0000\u0000\"\u00e2\u0001\u0000\u0000" +
                    "\u0000$\u00e4\u0001\u0000\u0000\u0000&\u00ec\u0001\u0000\u0000\u0000(" +
                    "\u00f4\u0001\u0000\u0000\u0000*\u0104\u0001\u0000\u0000\u0000,\u0108\u0001" +
                    "\u0000\u0000\u0000.\u010c\u0001\u0000\u0000\u00000\u0119\u0001\u0000\u0000" +
                    "\u00002\u012b\u0001\u0000\u0000\u00004\u013f\u0001\u0000\u0000\u00006" +
                    "\u0146\u0001\u0000\u0000\u00008\u0153\u0001\u0000\u0000\u0000:\u0159\u0001" +
                    "\u0000\u0000\u0000<\u015f\u0001\u0000\u0000\u0000>\u0161\u0001\u0000\u0000" +
                    "\u0000@\u0163\u0001\u0000\u0000\u0000B\u0165\u0001\u0000\u0000\u0000D" +
                    "\u0167\u0001\u0000\u0000\u0000F\u0169\u0001\u0000\u0000\u0000HI\u0003" +
                    "\u0002\u0001\u0000I\u0001\u0001\u0000\u0000\u0000JL\u0003\u0004\u0002" +
                    "\u0000KJ\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LM\u0001\u0000" +
                    "\u0000\u0000MN\u0003\u0006\u0003\u0000N\u0003\u0001\u0000\u0000\u0000" +
                    "OP\u0005;\u0000\u0000P\u0005\u0001\u0000\u0000\u0000QS\u0005\u0001\u0000" +
                    "\u0000RT\u0003\b\u0004\u0000SR\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000" +
                    "\u0000TV\u0001\u0000\u0000\u0000UW\u0003\u0010\b\u0000VU\u0001\u0000\u0000" +
                    "\u0000VW\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0005\u0002" +
                    "\u0000\u0000Y\u0007\u0001\u0000\u0000\u0000Z\\\u0005\u0003\u0000\u0000" +
                    "[]\u0003\n\u0005\u0000\\[\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000" +
                    "\u0000]^\u0001\u0000\u0000\u0000^_\u0005\u0004\u0000\u0000_\t\u0001\u0000" +
                    "\u0000\u0000`b\u0005\u0005\u0000\u0000ac\u0003\f\u0006\u0000ba\u0001\u0000" +
                    "\u0000\u0000bc\u0001\u0000\u0000\u0000ce\u0001\u0000\u0000\u0000df\u0003" +
                    "\u000e\u0007\u0000ed\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000" +
                    "fg\u0001\u0000\u0000\u0000gh\u0005\u0006\u0000\u0000h\u000b\u0001\u0000" +
                    "\u0000\u0000ij\u0005\u0007\u0000\u0000jn\u0005\b\u0000\u0000km\u00036" +
                    "\u001b\u0000lk\u0001\u0000\u0000\u0000mp\u0001\u0000\u0000\u0000nl\u0001" +
                    "\u0000\u0000\u0000no\u0001\u0000\u0000\u0000oq\u0001\u0000\u0000\u0000" +
                    "pn\u0001\u0000\u0000\u0000qr\u0005\t\u0000\u0000r\r\u0001\u0000\u0000" +
                    "\u0000st\u0005=\u0000\u0000tx\u0005\b\u0000\u0000uw\u00036\u001b\u0000" +
                    "vu\u0001\u0000\u0000\u0000wz\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000" +
                    "\u0000xy\u0001\u0000\u0000\u0000y{\u0001\u0000\u0000\u0000zx\u0001\u0000" +
                    "\u0000\u0000{|\u0005\t\u0000\u0000|\u000f\u0001\u0000\u0000\u0000}\u0084" +
                    "\u0005\n\u0000\u0000~\u0080\u0003\u0012\t\u0000\u007f~\u0001\u0000\u0000" +
                    "\u0000\u0080\u0083\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000" +
                    "\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0085\u0001\u0000\u0000" +
                    "\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0084\u0081\u0001\u0000\u0000" +
                    "\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000" +
                    "\u0000\u0086\u0087\u0005\u000b\u0000\u0000\u0087\u0011\u0001\u0000\u0000" +
                    "\u0000\u0088\u0091\u0003\u0014\n\u0000\u0089\u0091\u0003\u0016\u000b\u0000" +
                    "\u008a\u0091\u0003\u0018\f\u0000\u008b\u0091\u0003\u001e\u000f\u0000\u008c" +
                    "\u0091\u0003(\u0014\u0000\u008d\u0091\u0003.\u0017\u0000\u008e\u0091\u0003" +
                    "0\u0018\u0000\u008f\u0091\u00032\u0019\u0000\u0090\u0088\u0001\u0000\u0000" +
                    "\u0000\u0090\u0089\u0001\u0000\u0000\u0000\u0090\u008a\u0001\u0000\u0000" +
                    "\u0000\u0090\u008b\u0001\u0000\u0000\u0000\u0090\u008c\u0001\u0000\u0000" +
                    "\u0000\u0090\u008d\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000" +
                    "\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u0013\u0001\u0000\u0000" +
                    "\u0000\u0092\u0094\u0005\f\u0000\u0000\u0093\u0095\u00034\u001a\u0000" +
                    "\u0094\u0093\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000" +
                    "\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0098\u0005\r\u0000\u0000\u0097" +
                    "\u0099\u0003<\u001e\u0000\u0098\u0097\u0001\u0000\u0000\u0000\u0098\u0099" +
                    "\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009b" +
                    "\u0005\u000e\u0000\u0000\u009b\u0015\u0001\u0000\u0000\u0000\u009c\u009d" +
                    "\u0005\u000f\u0000\u0000\u009d\u009f\u0003B!\u0000\u009e\u00a0\u00034" +
                    "\u001a\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000" +
                    "\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a3\u0005\r\u0000" +
                    "\u0000\u00a2\u00a4\u0003<\u001e\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000" +
                    "\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000" +
                    "\u00a5\u00a6\u0005\u0010\u0000\u0000\u00a6\u00a8\u0003B!\u0000\u00a7\u00a9" +
                    "\u0005\r\u0000\u0000\u00a8\u00a7\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001" +
                    "\u0000\u0000\u0000\u00a9\u0017\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005" +
                    "\u0011\u0000\u0000\u00ab\u00ad\u0003\u001a\r\u0000\u00ac\u00ae\u00034" +
                    "\u001a\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000" +
                    "\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00b3\u0005\r\u0000" +
                    "\u0000\u00b0\u00b2\u0003\u001c\u000e\u0000\u00b1\u00b0\u0001\u0000\u0000" +
                    "\u0000\u00b2\u00b5\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001\u0000\u0000" +
                    "\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\u00b6\u0001\u0000\u0000" +
                    "\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005\u0012\u0000" +
                    "\u0000\u00b7\u00b8\u0003\u001a\r\u0000\u00b8\u00b9\u0005\r\u0000\u0000" +
                    "\u00b9\u0019\u0001\u0000\u0000\u0000\u00ba\u00bb\u0007\u0000\u0000\u0000" +
                    "\u00bb\u001b\u0001\u0000\u0000\u0000\u00bc\u00be\u0005\u0015\u0000\u0000" +
                    "\u00bd\u00bf\u00034\u001a\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00be" +
                    "\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0" +
                    "\u00c2\u0005\r\u0000\u0000\u00c1\u00c3\u0003<\u001e\u0000\u00c2\u00c1" +
                    "\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c4" +
                    "\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005\u0016\u0000\u0000\u00c5\u001d" +
                    "\u0001\u0000\u0000\u0000\u00c6\u00c8\u0005\u0017\u0000\u0000\u00c7\u00c9" +
                    "\u00034\u001a\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001" +
                    "\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u00ce\u0005" +
                    "\r\u0000\u0000\u00cb\u00cd\u0003 \u0010\u0000\u00cc\u00cb\u0001\u0000" +
                    "\u0000\u0000\u00cd\u00d0\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000" +
                    "\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d1\u0001\u0000" +
                    "\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1\u00d2\u0005\u0018" +
                    "\u0000\u0000\u00d2\u001f\u0001\u0000\u0000\u0000\u00d3\u00d5\u0005\u0019" +
                    "\u0000\u0000\u00d4\u00d6\u00034\u001a\u0000\u00d5\u00d4\u0001\u0000\u0000" +
                    "\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000\u0000" +
                    "\u0000\u00d7\u00db\u0005\r\u0000\u0000\u00d8\u00da\u0003\"\u0011\u0000" +
                    "\u00d9\u00d8\u0001\u0000\u0000\u0000\u00da\u00dd\u0001\u0000\u0000\u0000" +
                    "\u00db\u00d9\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000" +
                    "\u00dc\u00de\u0001\u0000\u0000\u0000\u00dd\u00db\u0001\u0000\u0000\u0000" +
                    "\u00de\u00df\u0005\u001a\u0000\u0000\u00df!\u0001\u0000\u0000\u0000\u00e0" +
                    "\u00e3\u0003$\u0012\u0000\u00e1\u00e3\u0003&\u0013\u0000\u00e2\u00e0\u0001" +
                    "\u0000\u0000\u0000\u00e2\u00e1\u0001\u0000\u0000\u0000\u00e3#\u0001\u0000" +
                    "\u0000\u0000\u00e4\u00e6\u0005\u001b\u0000\u0000\u00e5\u00e7\u00034\u001a" +
                    "\u0000\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000" +
                    "\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00e9\u0005\r\u0000\u0000" +
                    "\u00e9\u00ea\u0003<\u001e\u0000\u00ea\u00eb\u0005\u001c\u0000\u0000\u00eb" +
                    "%\u0001\u0000\u0000\u0000\u00ec\u00ee\u0005\u001d\u0000\u0000\u00ed\u00ef" +
                    "\u00034\u001a\u0000\u00ee\u00ed\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001" +
                    "\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u00f1\u0005" +
                    "\r\u0000\u0000\u00f1\u00f2\u0003<\u001e\u0000\u00f2\u00f3\u0005\u001e" +
                    "\u0000\u0000\u00f3\'\u0001\u0000\u0000\u0000\u00f4\u00f6\u0005\u001f\u0000" +
                    "\u0000\u00f5\u00f7\u0003*\u0015\u0000\u00f6\u00f5\u0001\u0000\u0000\u0000" +
                    "\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u00f9\u0001\u0000\u0000\u0000" +
                    "\u00f8\u00fa\u00034\u001a\u0000\u00f9\u00f8\u0001\u0000\u0000\u0000\u00f9" +
                    "\u00fa\u0001\u0000\u0000\u0000\u00fa\u00fc\u0001\u0000\u0000\u0000\u00fb" +
                    "\u00fd\u0003,\u0016\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fc\u00fd" +
                    "\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe\u0100" +
                    "\u0005\r\u0000\u0000\u00ff\u0101\u0003<\u001e\u0000\u0100\u00ff\u0001" +
                    "\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u0102\u0001" +
                    "\u0000\u0000\u0000\u0102\u0103\u0005 \u0000\u0000\u0103)\u0001\u0000\u0000" +
                    "\u0000\u0104\u0105\u0005!\u0000\u0000\u0105\u0106\u0005\"\u0000\u0000" +
                    "\u0106\u0107\u0003<\u001e\u0000\u0107+\u0001\u0000\u0000\u0000\u0108\u0109" +
                    "\u0005#\u0000\u0000\u0109\u010a\u0005\"\u0000\u0000\u010a\u010b\u0003" +
                    "<\u001e\u0000\u010b-\u0001\u0000\u0000\u0000\u010c\u010e\u0005$\u0000" +
                    "\u0000\u010d\u010f\u0003*\u0015\u0000\u010e\u010d\u0001\u0000\u0000\u0000" +
                    "\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0111\u0001\u0000\u0000\u0000" +
                    "\u0110\u0112\u00034\u001a\u0000\u0111\u0110\u0001\u0000\u0000\u0000\u0111" +
                    "\u0112\u0001\u0000\u0000\u0000\u0112\u0113\u0001\u0000\u0000\u0000\u0113" +
                    "\u0115\u0005\r\u0000\u0000\u0114\u0116\u0003<\u001e\u0000\u0115\u0114" +
                    "\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u0117" +
                    "\u0001\u0000\u0000\u0000\u0117\u0118\u0005%\u0000\u0000\u0118/\u0001\u0000" +
                    "\u0000\u0000\u0119\u011b\u0005&\u0000\u0000\u011a\u011c\u00034\u001a\u0000" +
                    "\u011b\u011a\u0001\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000" +
                    "\u011c\u011d\u0001\u0000\u0000\u0000\u011d\u0124\u0005\r\u0000\u0000\u011e" +
                    "\u0120\u0003\u0012\t\u0000\u011f\u011e\u0001\u0000\u0000\u0000\u0120\u0123" +
                    "\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0121\u0122" +
                    "\u0001\u0000\u0000\u0000\u0122\u0125\u0001\u0000\u0000\u0000\u0123\u0121" +
                    "\u0001\u0000\u0000\u0000\u0124\u0121\u0001\u0000\u0000\u0000\u0124\u0125" +
                    "\u0001\u0000\u0000\u0000\u0125\u0127\u0001\u0000\u0000\u0000\u0126\u0128" +
                    "\u0003<\u001e\u0000\u0127\u0126\u0001\u0000\u0000\u0000\u0127\u0128\u0001" +
                    "\u0000\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000\u0129\u012a\u0005" +
                    "\'\u0000\u0000\u012a1\u0001\u0000\u0000\u0000\u012b\u012c\u0005(\u0000" +
                    "\u0000\u012c\u012d\u0005=\u0000\u0000\u012d\u0131\u0005\b\u0000\u0000" +
                    "\u012e\u0130\u0003\u0012\t\u0000\u012f\u012e\u0001\u0000\u0000\u0000\u0130" +
                    "\u0133\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u0131" +
                    "\u0132\u0001\u0000\u0000\u0000\u0132\u0134\u0001\u0000\u0000\u0000\u0133" +
                    "\u0131\u0001\u0000\u0000\u0000\u0134\u0135\u0005\t\u0000\u0000\u0135\u0136" +
                    "\u0005)\u0000\u0000\u0136\u013d\u0005=\u0000\u0000\u0137\u0138\u0005B" +
                    "\u0000\u0000\u0138\u0139\u0003B!\u0000\u0139\u013a\u0003D\"\u0000\u013a" +
                    "\u013b\u0003B!\u0000\u013b\u013c\u0003D\"\u0000\u013c\u013e\u0001\u0000" +
                    "\u0000\u0000\u013d\u0137\u0001\u0000\u0000\u0000\u013d\u013e\u0001\u0000" +
                    "\u0000\u0000\u013e3\u0001\u0000\u0000\u0000\u013f\u0141\u0005*\u0000\u0000" +
                    "\u0140\u0142\u0005?\u0000\u0000\u0141\u0140\u0001\u0000\u0000\u0000\u0141" +
                    "\u0142\u0001\u0000\u0000\u0000\u0142\u0144\u0001\u0000\u0000\u0000\u0143" +
                    "\u0145\u00036\u001b\u0000\u0144\u0143\u0001\u0000\u0000\u0000\u0144\u0145" +
                    "\u0001\u0000\u0000\u0000\u01455\u0001\u0000\u0000\u0000\u0146\u014d\u0003" +
                    "8\u001c\u0000\u0147\u0149\u00053\u0000\u0000\u0148\u0147\u0001\u0000\u0000" +
                    "\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149\u014a\u0001\u0000\u0000" +
                    "\u0000\u014a\u014c\u00038\u001c\u0000\u014b\u0148\u0001\u0000\u0000\u0000" +
                    "\u014c\u014f\u0001\u0000\u0000\u0000\u014d\u014b\u0001\u0000\u0000\u0000" +
                    "\u014d\u014e\u0001\u0000\u0000\u0000\u014e\u0151\u0001\u0000\u0000\u0000" +
                    "\u014f\u014d\u0001\u0000\u0000\u0000\u0150\u0152\u00053\u0000\u0000\u0151" +
                    "\u0150\u0001\u0000\u0000\u0000\u0151\u0152\u0001\u0000\u0000\u0000\u0152" +
                    "7\u0001\u0000\u0000\u0000\u0153\u0154\u0003:\u001d\u0000\u0154\u0155\u0005" +
                    "2\u0000\u0000\u0155\u0156\u0003<\u001e\u0000\u01569\u0001\u0000\u0000" +
                    "\u0000\u0157\u015a\u0005=\u0000\u0000\u0158\u015a\u0003>\u001f\u0000\u0159" +
                    "\u0157\u0001\u0000\u0000\u0000\u0159\u0158\u0001\u0000\u0000\u0000\u015a" +
                    ";\u0001\u0000\u0000\u0000\u015b\u0160\u0005=\u0000\u0000\u015c\u0160\u0003" +
                    ">\u001f\u0000\u015d\u0160\u0003F#\u0000\u015e\u0160\u0003B!\u0000\u015f" +
                    "\u015b\u0001\u0000\u0000\u0000\u015f\u015c\u0001\u0000\u0000\u0000\u015f" +
                    "\u015d\u0001\u0000\u0000\u0000\u015f\u015e\u0001\u0000\u0000\u0000\u0160" +
                    "=\u0001\u0000\u0000\u0000\u0161\u0162\u0005?\u0000\u0000\u0162?\u0001" +
                    "\u0000\u0000\u0000\u0163\u0164\u0005>\u0000\u0000\u0164A\u0001\u0000\u0000" +
                    "\u0000\u0165\u0166\u0005<\u0000\u0000\u0166C\u0001\u0000\u0000\u0000\u0167" +
                    "\u0168\u0007\u0001\u0000\u0000\u0168E\u0001\u0000\u0000\u0000\u0169\u016a" +
                    "\u00051\u0000\u0000\u016a\u016b\u0005=\u0000\u0000\u016b\u016c\u0005\t" +
                    "\u0000\u0000\u016cG\u0001\u0000\u0000\u0000/KSV\\benx\u0081\u0084\u0090" +
                    "\u0094\u0098\u009f\u00a3\u00a8\u00ad\u00b3\u00be\u00c2\u00c8\u00ce\u00d5" +
                    "\u00db\u00e2\u00e6\u00ee\u00f6\u00f9\u00fc\u0100\u010e\u0111\u0115\u011b" +
                    "\u0121\u0124\u0127\u0131\u013d\u0141\u0144\u0148\u014d\u0151\u0159\u015f";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}