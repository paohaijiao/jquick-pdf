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

public class TabTest {

    private static final String PATH = "target/outline_tabs.pdf";

    public static void main(String[] args) throws IOException {
        File output = new File(PATH);
        File parent = output.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                float tabHeight = 30f;
                float tabWidth = page.getMediaBox().getWidth() / 3f;
                float y = page.getMediaBox().getHeight() - tabHeight;
                PDColor tabColor = new PDColor(new float[]{0.83f, 0.83f, 0.83f}, PDDeviceRGB.INSTANCE);
                PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                drawTab(stream, font, tabColor, 0f, y, tabWidth, tabHeight, "Tab 1");
                drawTab(stream, font, tabColor, tabWidth, y, tabWidth, tabHeight, "Tab 2");
                drawText(stream, font, 12f, "Current Tab Content", 48f, y - 36f);
            }
            document.save(output);
        }
    }

    private static void drawTab(PDPageContentStream stream, PDType1Font font, PDColor color,
                                float x, float y, float width, float height, String label) throws IOException {
        stream.setNonStrokingColor(color);
        stream.addRect(x, y, width, height);
        stream.fill();
        stream.setStrokingColor(160, 160, 160);
        stream.addRect(x, y, width, height);
        stream.stroke();
        float textWidth = font.getStringWidth(label) / 1000f * 11f;
        drawText(stream, font, 11f, label, x + (width - textWidth) / 2f, y + 10f);
    }

    private static void drawText(PDPageContentStream stream, PDType1Font font, float size,
                                 String value, float x, float y) throws IOException {
        stream.beginText();
        stream.setFont(font, size);
        stream.newLineAtOffset(x, y);
        stream.showText(value);
        stream.endText();
    }
}
