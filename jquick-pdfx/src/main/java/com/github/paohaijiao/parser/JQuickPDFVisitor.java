// Generated from D:/idea/jthornruleGrammer/pdf/JQuickPDF.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JQuickPDFParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JQuickPDFVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(JQuickPDFParser.DocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#doc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoc(JQuickPDFParser.DocContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#docType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocType(JQuickPDFParser.DocTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#html}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtml(JQuickPDFParser.HtmlContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(JQuickPDFParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#headStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeadStyle(JQuickPDFParser.HeadStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#headStyleOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeadStyleOption(JQuickPDFParser.HeadStyleOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#bodyStyleOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBodyStyleOption(JQuickPDFParser.BodyStyleOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(JQuickPDFParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(JQuickPDFParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#comboBoxField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComboBoxField(JQuickPDFParser.ComboBoxFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#checkbox}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckbox(JQuickPDFParser.CheckboxContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#areaBreak}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAreaBreak(JQuickPDFParser.AreaBreakContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#button}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitButton(JQuickPDFParser.ButtonContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#paragraph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParagraph(JQuickPDFParser.ParagraphContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#heading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeading(JQuickPDFParser.HeadingContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(JQuickPDFParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#listEle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListEle(JQuickPDFParser.ListEleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#listItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListItem(JQuickPDFParser.ListItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable(JQuickPDFParser.TableContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#row}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRow(JQuickPDFParser.RowContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#col}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCol(JQuickPDFParser.ColContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#th}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTh(JQuickPDFParser.ThContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#td}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTd(JQuickPDFParser.TdContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#elemValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemValue(JQuickPDFParser.ElemValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#image}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImage(JQuickPDFParser.ImageContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#src}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSrc(JQuickPDFParser.SrcContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#alt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlt(JQuickPDFParser.AltContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#svg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSvg(JQuickPDFParser.SvgContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(JQuickPDFParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#htmlPageBreak}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlPageBreak(JQuickPDFParser.HtmlPageBreakContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#inputButton}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputButton(JQuickPDFParser.InputButtonContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#inputField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputField(JQuickPDFParser.InputFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#lineSeparator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineSeparator(JQuickPDFParser.LineSeparatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#link}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLink(JQuickPDFParser.LinkContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#listBoxField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListBoxField(JQuickPDFParser.ListBoxFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#pageCountElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPageCountElement(JQuickPDFParser.PageCountElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#pageTargetCountElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPageTargetCountElement(JQuickPDFParser.PageTargetCountElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#radio}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRadio(JQuickPDFParser.RadioContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#runningElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRunningElement(JQuickPDFParser.RunningElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#tab}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTab(JQuickPDFParser.TabContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#textArea}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextArea(JQuickPDFParser.TextAreaContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#span}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpan(JQuickPDFParser.SpanContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#lbr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLbr(JQuickPDFParser.LbrContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#rbr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRbr(JQuickPDFParser.RbrContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(JQuickPDFParser.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#styleEle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleEle(JQuickPDFParser.StyleEleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyle(JQuickPDFParser.StyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#attr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(JQuickPDFParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey(JQuickPDFParser.KeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(JQuickPDFParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(JQuickPDFParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#marginValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMarginValue(JQuickPDFParser.MarginValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(JQuickPDFParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#borderType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBorderType(JQuickPDFParser.BorderTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnit(JQuickPDFParser.UnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(JQuickPDFParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor(JQuickPDFParser.ColorContext ctx);
}