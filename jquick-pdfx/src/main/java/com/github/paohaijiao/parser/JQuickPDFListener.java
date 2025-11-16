// Generated from D:/idea/jthornruleGrammer/pdf/JQuickPDF.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JQuickPDFParser}.
 */
public interface JQuickPDFListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#document}.
     *
     * @param ctx the parse tree
     */
    void enterDocument(JQuickPDFParser.DocumentContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#document}.
     *
     * @param ctx the parse tree
     */
    void exitDocument(JQuickPDFParser.DocumentContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#doc}.
     *
     * @param ctx the parse tree
     */
    void enterDoc(JQuickPDFParser.DocContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#doc}.
     *
     * @param ctx the parse tree
     */
    void exitDoc(JQuickPDFParser.DocContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#docType}.
     *
     * @param ctx the parse tree
     */
    void enterDocType(JQuickPDFParser.DocTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#docType}.
     *
     * @param ctx the parse tree
     */
    void exitDocType(JQuickPDFParser.DocTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#html}.
     *
     * @param ctx the parse tree
     */
    void enterHtml(JQuickPDFParser.HtmlContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#html}.
     *
     * @param ctx the parse tree
     */
    void exitHtml(JQuickPDFParser.HtmlContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#body}.
     *
     * @param ctx the parse tree
     */
    void enterBody(JQuickPDFParser.BodyContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#body}.
     *
     * @param ctx the parse tree
     */
    void exitBody(JQuickPDFParser.BodyContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#element}.
     *
     * @param ctx the parse tree
     */
    void enterElement(JQuickPDFParser.ElementContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#element}.
     *
     * @param ctx the parse tree
     */
    void exitElement(JQuickPDFParser.ElementContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#comboBoxField}.
     *
     * @param ctx the parse tree
     */
    void enterComboBoxField(JQuickPDFParser.ComboBoxFieldContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#comboBoxField}.
     *
     * @param ctx the parse tree
     */
    void exitComboBoxField(JQuickPDFParser.ComboBoxFieldContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#checkbox}.
     *
     * @param ctx the parse tree
     */
    void enterCheckbox(JQuickPDFParser.CheckboxContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#checkbox}.
     *
     * @param ctx the parse tree
     */
    void exitCheckbox(JQuickPDFParser.CheckboxContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#checkboxStatus}.
     *
     * @param ctx the parse tree
     */
    void enterCheckboxStatus(JQuickPDFParser.CheckboxStatusContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#checkboxStatus}.
     *
     * @param ctx the parse tree
     */
    void exitCheckboxStatus(JQuickPDFParser.CheckboxStatusContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#areaBreak}.
     *
     * @param ctx the parse tree
     */
    void enterAreaBreak(JQuickPDFParser.AreaBreakContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#areaBreak}.
     *
     * @param ctx the parse tree
     */
    void exitAreaBreak(JQuickPDFParser.AreaBreakContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#button}.
     *
     * @param ctx the parse tree
     */
    void enterButton(JQuickPDFParser.ButtonContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#button}.
     *
     * @param ctx the parse tree
     */
    void exitButton(JQuickPDFParser.ButtonContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#paragraph}.
     *
     * @param ctx the parse tree
     */
    void enterParagraph(JQuickPDFParser.ParagraphContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#paragraph}.
     *
     * @param ctx the parse tree
     */
    void exitParagraph(JQuickPDFParser.ParagraphContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#heading}.
     *
     * @param ctx the parse tree
     */
    void enterHeading(JQuickPDFParser.HeadingContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#heading}.
     *
     * @param ctx the parse tree
     */
    void exitHeading(JQuickPDFParser.HeadingContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#list}.
     *
     * @param ctx the parse tree
     */
    void enterList(JQuickPDFParser.ListContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#list}.
     *
     * @param ctx the parse tree
     */
    void exitList(JQuickPDFParser.ListContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#listItem}.
     *
     * @param ctx the parse tree
     */
    void enterListItem(JQuickPDFParser.ListItemContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#listItem}.
     *
     * @param ctx the parse tree
     */
    void exitListItem(JQuickPDFParser.ListItemContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#listType}.
     *
     * @param ctx the parse tree
     */
    void enterListType(JQuickPDFParser.ListTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#listType}.
     *
     * @param ctx the parse tree
     */
    void exitListType(JQuickPDFParser.ListTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#table}.
     *
     * @param ctx the parse tree
     */
    void enterTable(JQuickPDFParser.TableContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#table}.
     *
     * @param ctx the parse tree
     */
    void exitTable(JQuickPDFParser.TableContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#row}.
     *
     * @param ctx the parse tree
     */
    void enterRow(JQuickPDFParser.RowContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#row}.
     *
     * @param ctx the parse tree
     */
    void exitRow(JQuickPDFParser.RowContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#col}.
     *
     * @param ctx the parse tree
     */
    void enterCol(JQuickPDFParser.ColContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#col}.
     *
     * @param ctx the parse tree
     */
    void exitCol(JQuickPDFParser.ColContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#th}.
     *
     * @param ctx the parse tree
     */
    void enterTh(JQuickPDFParser.ThContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#th}.
     *
     * @param ctx the parse tree
     */
    void exitTh(JQuickPDFParser.ThContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#td}.
     *
     * @param ctx the parse tree
     */
    void enterTd(JQuickPDFParser.TdContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#td}.
     *
     * @param ctx the parse tree
     */
    void exitTd(JQuickPDFParser.TdContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#elemValue}.
     *
     * @param ctx the parse tree
     */
    void enterElemValue(JQuickPDFParser.ElemValueContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#elemValue}.
     *
     * @param ctx the parse tree
     */
    void exitElemValue(JQuickPDFParser.ElemValueContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#image}.
     *
     * @param ctx the parse tree
     */
    void enterImage(JQuickPDFParser.ImageContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#image}.
     *
     * @param ctx the parse tree
     */
    void exitImage(JQuickPDFParser.ImageContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#src}.
     *
     * @param ctx the parse tree
     */
    void enterSrc(JQuickPDFParser.SrcContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#src}.
     *
     * @param ctx the parse tree
     */
    void exitSrc(JQuickPDFParser.SrcContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#alt}.
     *
     * @param ctx the parse tree
     */
    void enterAlt(JQuickPDFParser.AltContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#alt}.
     *
     * @param ctx the parse tree
     */
    void exitAlt(JQuickPDFParser.AltContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#svg}.
     *
     * @param ctx the parse tree
     */
    void enterSvg(JQuickPDFParser.SvgContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#svg}.
     *
     * @param ctx the parse tree
     */
    void exitSvg(JQuickPDFParser.SvgContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#div}.
     *
     * @param ctx the parse tree
     */
    void enterDiv(JQuickPDFParser.DivContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#div}.
     *
     * @param ctx the parse tree
     */
    void exitDiv(JQuickPDFParser.DivContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#htmlPageBreak}.
     *
     * @param ctx the parse tree
     */
    void enterHtmlPageBreak(JQuickPDFParser.HtmlPageBreakContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#htmlPageBreak}.
     *
     * @param ctx the parse tree
     */
    void exitHtmlPageBreak(JQuickPDFParser.HtmlPageBreakContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#lineSeparator}.
     *
     * @param ctx the parse tree
     */
    void enterLineSeparator(JQuickPDFParser.LineSeparatorContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#lineSeparator}.
     *
     * @param ctx the parse tree
     */
    void exitLineSeparator(JQuickPDFParser.LineSeparatorContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#tab}.
     *
     * @param ctx the parse tree
     */
    void enterTab(JQuickPDFParser.TabContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#tab}.
     *
     * @param ctx the parse tree
     */
    void exitTab(JQuickPDFParser.TabContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#textArea}.
     *
     * @param ctx the parse tree
     */
    void enterTextArea(JQuickPDFParser.TextAreaContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#textArea}.
     *
     * @param ctx the parse tree
     */
    void exitTextArea(JQuickPDFParser.TextAreaContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#tree}.
     *
     * @param ctx the parse tree
     */
    void enterTree(JQuickPDFParser.TreeContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#tree}.
     *
     * @param ctx the parse tree
     */
    void exitTree(JQuickPDFParser.TreeContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#span}.
     *
     * @param ctx the parse tree
     */
    void enterSpan(JQuickPDFParser.SpanContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#span}.
     *
     * @param ctx the parse tree
     */
    void exitSpan(JQuickPDFParser.SpanContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#lbr}.
     *
     * @param ctx the parse tree
     */
    void enterLbr(JQuickPDFParser.LbrContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#lbr}.
     *
     * @param ctx the parse tree
     */
    void exitLbr(JQuickPDFParser.LbrContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#rbr}.
     *
     * @param ctx the parse tree
     */
    void enterRbr(JQuickPDFParser.RbrContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#rbr}.
     *
     * @param ctx the parse tree
     */
    void exitRbr(JQuickPDFParser.RbrContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#template}.
     *
     * @param ctx the parse tree
     */
    void enterTemplate(JQuickPDFParser.TemplateContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#template}.
     *
     * @param ctx the parse tree
     */
    void exitTemplate(JQuickPDFParser.TemplateContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#styleEle}.
     *
     * @param ctx the parse tree
     */
    void enterStyleEle(JQuickPDFParser.StyleEleContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#styleEle}.
     *
     * @param ctx the parse tree
     */
    void exitStyleEle(JQuickPDFParser.StyleEleContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#style}.
     *
     * @param ctx the parse tree
     */
    void enterStyle(JQuickPDFParser.StyleContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#style}.
     *
     * @param ctx the parse tree
     */
    void exitStyle(JQuickPDFParser.StyleContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#attr}.
     *
     * @param ctx the parse tree
     */
    void enterAttr(JQuickPDFParser.AttrContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#attr}.
     *
     * @param ctx the parse tree
     */
    void exitAttr(JQuickPDFParser.AttrContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#key}.
     *
     * @param ctx the parse tree
     */
    void enterKey(JQuickPDFParser.KeyContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#key}.
     *
     * @param ctx the parse tree
     */
    void exitKey(JQuickPDFParser.KeyContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#value}.
     *
     * @param ctx the parse tree
     */
    void enterValue(JQuickPDFParser.ValueContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#value}.
     *
     * @param ctx the parse tree
     */
    void exitValue(JQuickPDFParser.ValueContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#string}.
     *
     * @param ctx the parse tree
     */
    void enterString(JQuickPDFParser.StringContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#string}.
     *
     * @param ctx the parse tree
     */
    void exitString(JQuickPDFParser.StringContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#marginValue}.
     *
     * @param ctx the parse tree
     */
    void enterMarginValue(JQuickPDFParser.MarginValueContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#marginValue}.
     *
     * @param ctx the parse tree
     */
    void exitMarginValue(JQuickPDFParser.MarginValueContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#number}.
     *
     * @param ctx the parse tree
     */
    void enterNumber(JQuickPDFParser.NumberContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#number}.
     *
     * @param ctx the parse tree
     */
    void exitNumber(JQuickPDFParser.NumberContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#borderType}.
     *
     * @param ctx the parse tree
     */
    void enterBorderType(JQuickPDFParser.BorderTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#borderType}.
     *
     * @param ctx the parse tree
     */
    void exitBorderType(JQuickPDFParser.BorderTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#unit}.
     *
     * @param ctx the parse tree
     */
    void enterUnit(JQuickPDFParser.UnitContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#unit}.
     *
     * @param ctx the parse tree
     */
    void exitUnit(JQuickPDFParser.UnitContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#variable}.
     *
     * @param ctx the parse tree
     */
    void enterVariable(JQuickPDFParser.VariableContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#variable}.
     *
     * @param ctx the parse tree
     */
    void exitVariable(JQuickPDFParser.VariableContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#addressOf}.
     *
     * @param ctx the parse tree
     */
    void enterAddressOf(JQuickPDFParser.AddressOfContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#addressOf}.
     *
     * @param ctx the parse tree
     */
    void exitAddressOf(JQuickPDFParser.AddressOfContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickPDFParser#color}.
     *
     * @param ctx the parse tree
     */
    void enterColor(JQuickPDFParser.ColorContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickPDFParser#color}.
     *
     * @param ctx the parse tree
     */
    void exitColor(JQuickPDFParser.ColorContext ctx);
}