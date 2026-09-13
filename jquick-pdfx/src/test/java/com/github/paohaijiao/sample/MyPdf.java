package com.github.paohaijiao.sample;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

import java.io.File;
import java.io.IOException;

/**
 * PDFBox 报告生成示例。
 */
public class MyPdf {

    public static final String OUTPUT = "target/sample-report.pdf";

    public static void main(String[] args) throws IOException {
        new MyPdf().create(OUTPUT);
    }

    public void create(String destination) throws IOException {
        File file = new File(destination);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                PDType1Font titleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                PDColor theme = new PDColor(new float[]{37 / 255f, 98 / 255f, 206 / 255f}, PDDeviceRGB.INSTANCE);
                drawText(stream, titleFont, 24f, theme, "Health Report", 210f, 760f);
                drawText(stream, titleFont, 14f, theme, "Summary", 60f, 700f);
                drawText(stream, bodyFont, 11f, null, "This PDFBox sample verifies basic report rendering.", 60f, 675f);
                drawText(stream, bodyFont, 11f, null, "It includes a title, section heading and body text.", 60f, 655f);
                stream.setStrokingColor(theme);
                stream.setLineWidth(1f);
                stream.moveTo(60f, 690f);
                stream.lineTo(535f, 690f);
                stream.stroke();
            }
            document.save(file);
        }
    }

    private void drawText(PDPageContentStream stream, PDType1Font font, float size, PDColor color,
                          String value, float x, float y) throws IOException {
        stream.beginText();
        stream.setFont(font, size);
        if (color != null) {
            stream.setNonStrokingColor(color);
        }
        stream.newLineAtOffset(x, y);
        stream.showText(value);
        stream.endText();
    }
}
