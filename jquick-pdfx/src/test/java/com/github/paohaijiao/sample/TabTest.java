package com.github.paohaijiao.sample;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfStampAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

public class TabTest {
    static String path="d://test//outline_tabs.pdf";
    public static void main(String[] args) throws FileNotFoundException {
        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(path));
             Document document = new Document(pdfDoc)) {
             pdfDoc.addNewPage();
            PageSize pageSize = pdfDoc.getDefaultPageSize();
            int tabHeight = 30;
            int tabWidth = (int)pageSize.getWidth() / 3;
            int height=(int)pageSize.getHeight() - tabHeight;
            PdfAnnotation tab1 = new PdfStampAnnotation(new Rectangle(0, height, tabWidth, tabHeight))
                    .setContents("Tab 1")
                    .setColor(ColorConstants.LIGHT_GRAY)
                    .setTitle(new PdfString("Tab1"));
            pdfDoc.getFirstPage().addAnnotation(tab1);

            // 创建Tab 2注释
            PdfAnnotation tab2 = new PdfStampAnnotation(
                    new Rectangle(tabWidth, pageSize.getHeight() - tabHeight, tabWidth, tabHeight))
                    .setContents("Tab 2")
                    .setColor(ColorConstants.LIGHT_GRAY)
                    .setTitle(new PdfString("Tab 2"));
            pdfDoc.getFirstPage().addAnnotation(tab2);

            // 添加内容区域
            document.add(new Paragraph("Current Tab Content")
                    .setMarginTop(tabHeight + 10));
        }
    }
}
