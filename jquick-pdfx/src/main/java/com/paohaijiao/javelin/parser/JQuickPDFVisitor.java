// Generated from D:/idea/jthornruleGrammer/pdf/JQuickPDF.g4 by ANTLR 4.13.2
package com.paohaijiao.javelin.parser;
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
	 * Visit a parse tree produced by {@link JQuickPDFParser#tableRow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableRow(JQuickPDFParser.TableRowContext ctx);
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
	 * Visit a parse tree produced by {@link JQuickPDFParser#paragraphStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParagraphStyle(JQuickPDFParser.ParagraphStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#paragraphStyleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParagraphStyleType(JQuickPDFParser.ParagraphStyleTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textStylefont}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextStylefont(JQuickPDFParser.TextStylefontContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textStyleSize}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextStyleSize(JQuickPDFParser.TextStyleSizeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textStyleColor}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextStyleColor(JQuickPDFParser.TextStyleColorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textStyleBold}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextStyleBold(JQuickPDFParser.TextStyleBoldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textStyleItalic}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextStyleItalic(JQuickPDFParser.TextStyleItalicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textStyleUnderline}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextStyleUnderline(JQuickPDFParser.TextStyleUnderlineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textStyleAlign}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextStyleAlign(JQuickPDFParser.TextStyleAlignContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#alignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlignment(JQuickPDFParser.AlignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#alignmentLocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlignmentLocation(JQuickPDFParser.AlignmentLocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#spacing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpacing(JQuickPDFParser.SpacingContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#marginOrPadding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMarginOrPadding(JQuickPDFParser.MarginOrPaddingContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#imageStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImageStyle(JQuickPDFParser.ImageStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#imageStyleItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImageStyleItem(JQuickPDFParser.ImageStyleItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#svgStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSvgStyle(JQuickPDFParser.SvgStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#svgStyleItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSvgStyleItem(JQuickPDFParser.SvgStyleItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#divStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivStyle(JQuickPDFParser.DivStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#divStyleItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivStyleItem(JQuickPDFParser.DivStyleItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dimensionWidth}
	 * labeled alternative in {@link JQuickPDFParser#dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionWidth(JQuickPDFParser.DimensionWidthContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dimensionHeight}
	 * labeled alternative in {@link JQuickPDFParser#dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionHeight(JQuickPDFParser.DimensionHeightContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dimensionSize}
	 * labeled alternative in {@link JQuickPDFParser#dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionSize(JQuickPDFParser.DimensionSizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#background}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackground(JQuickPDFParser.BackgroundContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#border}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBorder(JQuickPDFParser.BorderContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#borderRounded}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBorderRounded(JQuickPDFParser.BorderRoundedContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#opacity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpacity(JQuickPDFParser.OpacityContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#scale}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScale(JQuickPDFParser.ScaleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#float}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(JQuickPDFParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickPDFParser#floatDirect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatDirect(JQuickPDFParser.FloatDirectContext ctx);
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
}