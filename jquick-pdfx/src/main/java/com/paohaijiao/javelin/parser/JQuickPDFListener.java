// Generated from D:/idea/jthornruleGrammer/pdf/JQuickPDF.g4 by ANTLR 4.13.2
package com.paohaijiao.javelin.parser;
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
	 * Enter a parse tree produced by {@link JQuickPDFParser#orderType}.
	 * @param ctx the parse tree
	 */
	void enterOrderType(JQuickPDFParser.OrderTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#orderType}.
	 * @param ctx the parse tree
	 */
	void exitOrderType(JQuickPDFParser.OrderTypeContext ctx);
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
	 * Enter a parse tree produced by {@link JQuickPDFParser#tableRow}.
	 * @param ctx the parse tree
	 */
	void enterTableRow(JQuickPDFParser.TableRowContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#tableRow}.
	 * @param ctx the parse tree
	 */
	void exitTableRow(JQuickPDFParser.TableRowContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#tableCell}.
	 * @param ctx the parse tree
	 */
	void enterTableCell(JQuickPDFParser.TableCellContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#tableCell}.
	 * @param ctx the parse tree
	 */
	void exitTableCell(JQuickPDFParser.TableCellContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#colspan}.
	 * @param ctx the parse tree
	 */
	void enterColspan(JQuickPDFParser.ColspanContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#colspan}.
	 * @param ctx the parse tree
	 */
	void exitColspan(JQuickPDFParser.ColspanContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#rowspan}.
	 * @param ctx the parse tree
	 */
	void enterRowspan(JQuickPDFParser.RowspanContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#rowspan}.
	 * @param ctx the parse tree
	 */
	void exitRowspan(JQuickPDFParser.RowspanContext ctx);
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
	 * Enter a parse tree produced by {@link JQuickPDFParser#paragraphStyle}.
	 * @param ctx the parse tree
	 */
	void enterParagraphStyle(JQuickPDFParser.ParagraphStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#paragraphStyle}.
	 * @param ctx the parse tree
	 */
	void exitParagraphStyle(JQuickPDFParser.ParagraphStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#paragraphStyleType}.
	 * @param ctx the parse tree
	 */
	void enterParagraphStyleType(JQuickPDFParser.ParagraphStyleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#paragraphStyleType}.
	 * @param ctx the parse tree
	 */
	void exitParagraphStyleType(JQuickPDFParser.ParagraphStyleTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textStylefont}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void enterTextStylefont(JQuickPDFParser.TextStylefontContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textStylefont}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void exitTextStylefont(JQuickPDFParser.TextStylefontContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textStyleSize}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void enterTextStyleSize(JQuickPDFParser.TextStyleSizeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textStyleSize}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void exitTextStyleSize(JQuickPDFParser.TextStyleSizeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textStyleColor}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void enterTextStyleColor(JQuickPDFParser.TextStyleColorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textStyleColor}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void exitTextStyleColor(JQuickPDFParser.TextStyleColorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textStyleBold}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void enterTextStyleBold(JQuickPDFParser.TextStyleBoldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textStyleBold}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void exitTextStyleBold(JQuickPDFParser.TextStyleBoldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textStyleItalic}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void enterTextStyleItalic(JQuickPDFParser.TextStyleItalicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textStyleItalic}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void exitTextStyleItalic(JQuickPDFParser.TextStyleItalicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textStyleUnderline}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void enterTextStyleUnderline(JQuickPDFParser.TextStyleUnderlineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textStyleUnderline}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void exitTextStyleUnderline(JQuickPDFParser.TextStyleUnderlineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textStyleAlign}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void enterTextStyleAlign(JQuickPDFParser.TextStyleAlignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textStyleAlign}
	 * labeled alternative in {@link JQuickPDFParser#textStyle}.
	 * @param ctx the parse tree
	 */
	void exitTextStyleAlign(JQuickPDFParser.TextStyleAlignContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#alignment}.
	 * @param ctx the parse tree
	 */
	void enterAlignment(JQuickPDFParser.AlignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#alignment}.
	 * @param ctx the parse tree
	 */
	void exitAlignment(JQuickPDFParser.AlignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#alignmentLocation}.
	 * @param ctx the parse tree
	 */
	void enterAlignmentLocation(JQuickPDFParser.AlignmentLocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#alignmentLocation}.
	 * @param ctx the parse tree
	 */
	void exitAlignmentLocation(JQuickPDFParser.AlignmentLocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#spacing}.
	 * @param ctx the parse tree
	 */
	void enterSpacing(JQuickPDFParser.SpacingContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#spacing}.
	 * @param ctx the parse tree
	 */
	void exitSpacing(JQuickPDFParser.SpacingContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#marginOrPadding}.
	 * @param ctx the parse tree
	 */
	void enterMarginOrPadding(JQuickPDFParser.MarginOrPaddingContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#marginOrPadding}.
	 * @param ctx the parse tree
	 */
	void exitMarginOrPadding(JQuickPDFParser.MarginOrPaddingContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#imageStyle}.
	 * @param ctx the parse tree
	 */
	void enterImageStyle(JQuickPDFParser.ImageStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#imageStyle}.
	 * @param ctx the parse tree
	 */
	void exitImageStyle(JQuickPDFParser.ImageStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#imageStyleItem}.
	 * @param ctx the parse tree
	 */
	void enterImageStyleItem(JQuickPDFParser.ImageStyleItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#imageStyleItem}.
	 * @param ctx the parse tree
	 */
	void exitImageStyleItem(JQuickPDFParser.ImageStyleItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#svgStyle}.
	 * @param ctx the parse tree
	 */
	void enterSvgStyle(JQuickPDFParser.SvgStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#svgStyle}.
	 * @param ctx the parse tree
	 */
	void exitSvgStyle(JQuickPDFParser.SvgStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#svgStyleItem}.
	 * @param ctx the parse tree
	 */
	void enterSvgStyleItem(JQuickPDFParser.SvgStyleItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#svgStyleItem}.
	 * @param ctx the parse tree
	 */
	void exitSvgStyleItem(JQuickPDFParser.SvgStyleItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#divStyle}.
	 * @param ctx the parse tree
	 */
	void enterDivStyle(JQuickPDFParser.DivStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#divStyle}.
	 * @param ctx the parse tree
	 */
	void exitDivStyle(JQuickPDFParser.DivStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#divStyleItem}.
	 * @param ctx the parse tree
	 */
	void enterDivStyleItem(JQuickPDFParser.DivStyleItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#divStyleItem}.
	 * @param ctx the parse tree
	 */
	void exitDivStyleItem(JQuickPDFParser.DivStyleItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dimensionWidth}
	 * labeled alternative in {@link JQuickPDFParser#dimension}.
	 * @param ctx the parse tree
	 */
	void enterDimensionWidth(JQuickPDFParser.DimensionWidthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dimensionWidth}
	 * labeled alternative in {@link JQuickPDFParser#dimension}.
	 * @param ctx the parse tree
	 */
	void exitDimensionWidth(JQuickPDFParser.DimensionWidthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dimensionHeight}
	 * labeled alternative in {@link JQuickPDFParser#dimension}.
	 * @param ctx the parse tree
	 */
	void enterDimensionHeight(JQuickPDFParser.DimensionHeightContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dimensionHeight}
	 * labeled alternative in {@link JQuickPDFParser#dimension}.
	 * @param ctx the parse tree
	 */
	void exitDimensionHeight(JQuickPDFParser.DimensionHeightContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dimensionSize}
	 * labeled alternative in {@link JQuickPDFParser#dimension}.
	 * @param ctx the parse tree
	 */
	void enterDimensionSize(JQuickPDFParser.DimensionSizeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dimensionSize}
	 * labeled alternative in {@link JQuickPDFParser#dimension}.
	 * @param ctx the parse tree
	 */
	void exitDimensionSize(JQuickPDFParser.DimensionSizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#background}.
	 * @param ctx the parse tree
	 */
	void enterBackground(JQuickPDFParser.BackgroundContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#background}.
	 * @param ctx the parse tree
	 */
	void exitBackground(JQuickPDFParser.BackgroundContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#border}.
	 * @param ctx the parse tree
	 */
	void enterBorder(JQuickPDFParser.BorderContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#border}.
	 * @param ctx the parse tree
	 */
	void exitBorder(JQuickPDFParser.BorderContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#borderRounded}.
	 * @param ctx the parse tree
	 */
	void enterBorderRounded(JQuickPDFParser.BorderRoundedContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#borderRounded}.
	 * @param ctx the parse tree
	 */
	void exitBorderRounded(JQuickPDFParser.BorderRoundedContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#opacity}.
	 * @param ctx the parse tree
	 */
	void enterOpacity(JQuickPDFParser.OpacityContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#opacity}.
	 * @param ctx the parse tree
	 */
	void exitOpacity(JQuickPDFParser.OpacityContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#scale}.
	 * @param ctx the parse tree
	 */
	void enterScale(JQuickPDFParser.ScaleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#scale}.
	 * @param ctx the parse tree
	 */
	void exitScale(JQuickPDFParser.ScaleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#float}.
	 * @param ctx the parse tree
	 */
	void enterFloat(JQuickPDFParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#float}.
	 * @param ctx the parse tree
	 */
	void exitFloat(JQuickPDFParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickPDFParser#floatDirect}.
	 * @param ctx the parse tree
	 */
	void enterFloatDirect(JQuickPDFParser.FloatDirectContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickPDFParser#floatDirect}.
	 * @param ctx the parse tree
	 */
	void exitFloatDirect(JQuickPDFParser.FloatDirectContext ctx);
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
}