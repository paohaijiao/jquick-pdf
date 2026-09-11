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

public class PdfListGenerator {

    public static final String DEST = "target/list_example.pdf";
    private static final PDColor HEADER_COLOR = color(66, 133, 244);
    private static final PDColor HEADER_TEXT_COLOR = color(255, 255, 255);
    private static final PDColor EVEN_ROW_COLOR = color(245, 245, 245);
    private static final PDColor TEXT_COLOR = color(51, 51, 51);
    private static final PDColor BORDER_COLOR = color(221, 221, 221);

    public static void main(String[] args) throws IOException {
        new PdfListGenerator().createPdf(DEST);
    }

    public void createPdf(String destination) throws IOException {
        File file = new File(destination);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        String[] headers = {"Product ID", "Product Name", "Price"};
        String[][] data = {
                {"P001", "Smart Watch Pro", "1299"},
                {"P002", "Wireless Headphones", "799"},
                {"P003", "Portable Charger 20000mAh", "199"},
                {"P004", "Smart Band", "159"},
                {"P005", "HD Camera 1080P", "299"}
        };
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                PDType1Font regular = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                PDType1Font bold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                float[] widths = {120f, 280f, 100f};
                float x = 48f;
                float top = page.getMediaBox().getHeight() - 72f;
                drawText(stream, bold, 18f, TEXT_COLOR, "Product Information", centerX(48f, 500f, 18f, bold, "Product Information"), top);
                float rowTop = top - 36f;
                drawRow(stream, headers, x, rowTop, widths, HEADER_COLOR, HEADER_TEXT_COLOR, bold, true);
                for (int row = 0; row < data.length; row++) {
                    PDColor background = row % 2 == 0 ? EVEN_ROW_COLOR : null;
                    drawRow(stream, data[row], x, rowTop - (row + 1) * 32f, widths, background, TEXT_COLOR, regular, false);
                }
                String[] total = {"", "Total", "2755"};
                drawRow(stream, total, x, rowTop - (data.length + 1) * 32f, widths,
                        color(232, 240, 254), TEXT_COLOR, bold, false);
            }
            document.save(file);
        }
    }

    private void drawRow(PDPageContentStream stream, String[] values, float x, float top, float[] widths,
                         PDColor background, PDColor textColor, PDType1Font font, boolean centered) throws IOException {
        float height = 32f;
        float currentX = x;
        for (int column = 0; column < widths.length; column++) {
            if (background != null) {
                stream.setNonStrokingColor(background);
                stream.addRect(currentX, top - height, widths[column], height);
                stream.fill();
            }
            stream.setStrokingColor(BORDER_COLOR);
            stream.addRect(currentX, top - height, widths[column], height);
            stream.stroke();
            String value = values[column];
            float textWidth = font.getStringWidth(value) / 1000f * 10f;
            float textX = centered || column == 2 ? currentX + (widths[column] - textWidth) / 2f : currentX + 8f;
            drawText(stream, font, 10f, textColor, value, textX, top - 20f);
            currentX += widths[column];
        }
    }

    private float centerX(float x, float width, float fontSize, PDType1Font font, String value) throws IOException {
        return x + (width - font.getStringWidth(value) / 1000f * fontSize) / 2f;
    }

    private void drawText(PDPageContentStream stream, PDType1Font font, float size, PDColor color,
                          String text, float x, float y) throws IOException {
        stream.beginText();
        stream.setFont(font, size);
        stream.setNonStrokingColor(color);
        stream.newLineAtOffset(x, y);
        stream.showText(text);
        stream.endText();
    }

    private static PDColor color(int red, int green, int blue) {
        return new PDColor(new float[]{red / 255f, green / 255f, blue / 255f}, PDDeviceRGB.INSTANCE);
    }
}
