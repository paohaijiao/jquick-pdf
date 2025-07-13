package com.github.paohaijiao.sample;

import com.itextpdf.commons.actions.sequence.AbstractIdentifiableElement;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.OverflowPropertyValue;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;


/**
 * @author: laoliangliang
 * @description:
 * @create: 2020/5/20 8:34
 **/
public class ReportComponent {
    public static Paragraph getCatelogDottedLine(int type) {
        Paragraph element;
        if (type == 1) {
            element = new Paragraph("------------------------------------------------------------------------");
        } else {
            element = new Paragraph("------------------------------------------------");
        }
        AbstractIdentifiableElement abstractIdentifiableElement=null;
        element.setProperty(Property.OVERFLOW_X, OverflowPropertyValue.HIDDEN);
        element.setTextAlignment(TextAlignment.RIGHT);
        element.setMarginRight(20);
        return element;
    }

    public static Cell getDefaultCell() {
        return new Cell().addStyle(ReportStyle.getDefaultCell());
    }

    public static Cell getTableCell() {
        return new Cell().setTextAlignment(TextAlignment.CENTER).addStyle(ReportStyle.getTableCell());
    }

    public static Cell getDefaultCell(int rowspan, int colspan) {
        return new Cell(rowspan, colspan).addStyle(ReportStyle.getDefaultCell());
    }

    public static Cell getCatelogCell() {
        return new Cell().addStyle(ReportStyle.getCatelogCell());
    }

    public static Cell getCatelogCell(int colsapn) {
        return new Cell(1, colsapn).addStyle(ReportStyle.getCatelogCell());
    }

    public static Cell getSignCell() {
        return new Cell().addStyle(ReportStyle.getSignCell());
    }

    public static Paragraph getSignParagraph(String text) {
        return new Paragraph(text).addStyle(ReportStyle.getSignStyle());
    }

    public static Text getSecondTitle(String context){
        return new Text(context).addStyle(ReportStyle.getSecondTitleStyle());
    }

    public static Text getThirdTitle(String context){
        return new Text(context).addStyle(ReportStyle.getThirdTitleStyle());
    }

    public static Paragraph getHeaderLineText(String context){
        return new Paragraph(context).setTextAlignment(TextAlignment.CENTER).setFontSize(12).setMarginTop(-20);
    }

    public static Paragraph getTitleParagraph(Text context){
        return new Paragraph(context).setMargin(0);
    }

    public static PdfAction getCatalogPageAction(PdfDocument pdf){
        PdfDestination dest = PdfExplicitDestination.createXYZ(pdf.getPage(6), 60, -10, 1);
        return PdfAction.createGoTo(dest);
    }

}