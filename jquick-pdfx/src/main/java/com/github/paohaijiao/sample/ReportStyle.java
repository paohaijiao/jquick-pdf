package com.github.paohaijiao.sample;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.properties.VerticalAlignment;

/**
 * @author: laoliangliang
 * @description:
 * @create: 2020/5/20 8:34
 **/
public class ReportStyle {
    public static Style getDefaultPoint() {
        Style style = new Style();
        style.setWidth(6);
        style.setHeight(6);
        style.setMarginRight(6);
        return style;
    }

    public static Style getTitleStyle() {
        Style style = new Style();
        style.setFontSize(20);
        style.setBold();
        style.setFontColor(ReportColor.getThemeColor());
        return style;
    }

    public static Style getSecondTitleStyle() {
        Style style = new Style();
        style.setFontSize(14);
        style.setFontColor(ReportColor.getThemeColor());
        return style;
    }

    public static Style getThirdTitleStyle() {
        Style style = new Style();
        style.setFontSize(12);
        style.setFontColor(ReportColor.getThemeColor());
        return style;
    }

    public static Style getThreeTitleOrangeStyle() {
        Style style = new Style();
        style.setFontSize(12);
        style.setFontColor(ReportColor.getOrangeColor());
        return style;
    }

    public static Style getIconStyle() {
        Style style = new Style();
        style.setHeight(18);
        style.setWidth(15);
        style.setMarginBottom(-2);
        style.setMarginRight(2);
        return style;
    }

    public static Style getLargeIconStyle() {
        Style style = new Style();
        style.setHeight(72);
        style.setWidth(72);
        return style;
    }

    public static Style getDefaultCell() {
        Style style = new Style();
        style.setPadding(10);
        style.setBorder(Border.NO_BORDER);
        style.setVerticalAlignment(VerticalAlignment.MIDDLE);
        return style;
    }

    public static Style getTableCell() {
        Style style = new Style();
        style.setVerticalAlignment(VerticalAlignment.MIDDLE);
        style.setBorder(new SolidBorder(ReportColor.getThemeColor(), 1));
        return style;
    }

    public static Style getCatelogCell() {
        Style style = new Style();
        style.setBorder(Border.NO_BORDER);
        style.setVerticalAlignment(VerticalAlignment.MIDDLE);
        return style;
    }

    public static Style getDefaultTableOuter() {
        Style style = new Style();
        style.setBorder(new SolidBorder(ReportColor.getThemeColor(), 1));
        style.setPaddings(10, 20, 10, 20);
        return style;
    }

    public static Style getSignCell() {
        Style style = new Style();
        style.setBorder(Border.NO_BORDER);
        return style;
    }

    public static Style getSignStyle() {
        Style style = new Style();
        style.setFontSize(11);
        return style;
    }

    public static Style getTableHeader() {
        Style style = new Style();
        style.setBackgroundColor(ReportColor.getThemeColor()).setFontColor(ColorConstants.WHITE);
        return style;
    }
}