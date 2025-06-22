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
	 * @param ctx the parse tree
	 */
	void enterDocument(JQuickPDFParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(JQuickPDFParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#page}.
	 * @param ctx the parse tree
	 */
	void enterPage(JQuickPDFParser.PageContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#page}.
	 * @param ctx the parse tree
	 */
	void exitPage(JQuickPDFParser.PageContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#pageLayout}.
	 * @param ctx the parse tree
	 */
	void enterPageLayout(JQuickPDFParser.PageLayoutContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#pageLayout}.
	 * @param ctx the parse tree
	 */
	void exitPageLayout(JQuickPDFParser.PageLayoutContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#margins}.
	 * @param ctx the parse tree
	 */
	void enterMargins(JQuickPDFParser.MarginsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#margins}.
	 * @param ctx the parse tree
	 */
	void exitMargins(JQuickPDFParser.MarginsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#layoutOption}.
	 * @param ctx the parse tree
	 */
	void enterLayoutOption(JQuickPDFParser.LayoutOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#layoutOption}.
	 * @param ctx the parse tree
	 */
	void exitLayoutOption(JQuickPDFParser.LayoutOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#customOption}.
	 * @param ctx the parse tree
	 */
	void enterCustomOption(JQuickPDFParser.CustomOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#customOption}.
	 * @param ctx the parse tree
	 */
	void exitCustomOption(JQuickPDFParser.CustomOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(JQuickPDFParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(JQuickPDFParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#paragraph}.
	 * @param ctx the parse tree
	 */
	void enterParagraph(JQuickPDFParser.ParagraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#paragraph}.
	 * @param ctx the parse tree
	 */
	void exitParagraph(JQuickPDFParser.ParagraphContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#heading}.
	 * @param ctx the parse tree
	 */
	void enterHeading(JQuickPDFParser.HeadingContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#heading}.
	 * @param ctx the parse tree
	 */
	void exitHeading(JQuickPDFParser.HeadingContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(JQuickPDFParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(JQuickPDFParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#listEle}.
	 * @param ctx the parse tree
	 */
	void enterListEle(JQuickPDFParser.ListEleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#listEle}.
	 * @param ctx the parse tree
	 */
	void exitListEle(JQuickPDFParser.ListEleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#listItem}.
	 * @param ctx the parse tree
	 */
	void enterListItem(JQuickPDFParser.ListItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#listItem}.
	 * @param ctx the parse tree
	 */
	void exitListItem(JQuickPDFParser.ListItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(JQuickPDFParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(JQuickPDFParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#row}.
	 * @param ctx the parse tree
	 */
	void enterRow(JQuickPDFParser.RowContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#row}.
	 * @param ctx the parse tree
	 */
	void exitRow(JQuickPDFParser.RowContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#col}.
	 * @param ctx the parse tree
	 */
	void enterCol(JQuickPDFParser.ColContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#col}.
	 * @param ctx the parse tree
	 */
	void exitCol(JQuickPDFParser.ColContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#th}.
	 * @param ctx the parse tree
	 */
	void enterTh(JQuickPDFParser.ThContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#th}.
	 * @param ctx the parse tree
	 */
	void exitTh(JQuickPDFParser.ThContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#td}.
	 * @param ctx the parse tree
	 */
	void enterTd(JQuickPDFParser.TdContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#td}.
	 * @param ctx the parse tree
	 */
	void exitTd(JQuickPDFParser.TdContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#image}.
	 * @param ctx the parse tree
	 */
	void enterImage(JQuickPDFParser.ImageContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#image}.
	 * @param ctx the parse tree
	 */
	void exitImage(JQuickPDFParser.ImageContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#src}.
	 * @param ctx the parse tree
	 */
	void enterSrc(JQuickPDFParser.SrcContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#src}.
	 * @param ctx the parse tree
	 */
	void exitSrc(JQuickPDFParser.SrcContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#alt}.
	 * @param ctx the parse tree
	 */
	void enterAlt(JQuickPDFParser.AltContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#alt}.
	 * @param ctx the parse tree
	 */
	void exitAlt(JQuickPDFParser.AltContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#svg}.
	 * @param ctx the parse tree
	 */
	void enterSvg(JQuickPDFParser.SvgContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#svg}.
	 * @param ctx the parse tree
	 */
	void exitSvg(JQuickPDFParser.SvgContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#div}.
	 * @param ctx the parse tree
	 */
	void enterDiv(JQuickPDFParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#div}.
	 * @param ctx the parse tree
	 */
	void exitDiv(JQuickPDFParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#template}.
	 * @param ctx the parse tree
	 */
	void enterTemplate(JQuickPDFParser.TemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#template}.
	 * @param ctx the parse tree
	 */
	void exitTemplate(JQuickPDFParser.TemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#styleEle}.
	 * @param ctx the parse tree
	 */
	void enterStyleEle(JQuickPDFParser.StyleEleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#styleEle}.
	 * @param ctx the parse tree
	 */
	void exitStyleEle(JQuickPDFParser.StyleEleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#style}.
	 * @param ctx the parse tree
	 */
	void enterStyle(JQuickPDFParser.StyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#style}.
	 * @param ctx the parse tree
	 */
	void exitStyle(JQuickPDFParser.StyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#attr}.
	 * @param ctx the parse tree
	 */
	void enterAttr(JQuickPDFParser.AttrContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#attr}.
	 * @param ctx the parse tree
	 */
	void exitAttr(JQuickPDFParser.AttrContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(JQuickPDFParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(JQuickPDFParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(JQuickPDFParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(JQuickPDFParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(JQuickPDFParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(JQuickPDFParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#color}.
	 * @param ctx the parse tree
	 */
	void enterColor(JQuickPDFParser.ColorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#color}.
	 * @param ctx the parse tree
	 */
	void exitColor(JQuickPDFParser.ColorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(JQuickPDFParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(JQuickPDFParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnit(JQuickPDFParser.UnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnit(JQuickPDFParser.UnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(JQuickPDFParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(JQuickPDFParser.VariableContext ctx);
}