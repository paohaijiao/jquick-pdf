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
	 * Visit a parse tree produced by {@link JQuickPDFParser#page}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPage(JQuickPDFParser.PageContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#pageLayout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPageLayout(JQuickPDFParser.PageLayoutContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#margins}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMargins(JQuickPDFParser.MarginsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#layoutOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayoutOption(JQuickPDFParser.LayoutOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#customOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCustomOption(JQuickPDFParser.CustomOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(JQuickPDFParser.ElementContext ctx);
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
	 * Visit a parse tree produced by {@link JQuickPDFParser#orderType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderType(JQuickPDFParser.OrderTypeContext ctx);
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
	 * Visit a parse tree produced by {@link JQuickPDFParser#tableCell}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableCell(JQuickPDFParser.TableCellContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#colspan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColspan(JQuickPDFParser.ColspanContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#rowspan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowspan(JQuickPDFParser.RowspanContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#image}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImage(JQuickPDFParser.ImageContext ctx);
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
	 * Visit a parse tree produced by {@link JQuickPDFParser#color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor(JQuickPDFParser.ColorContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(JQuickPDFParser.NumberContext ctx);
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
}