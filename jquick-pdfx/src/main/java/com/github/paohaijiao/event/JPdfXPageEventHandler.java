/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.event;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;

/**
 * PDFBox 没有 iText 的页面事件机制；在页面创建后显式调用 render。
 */
public class JPdfXPageEventHandler {

    private static final String HEADER_TEXT = "Page Header";
    private static final float FONT_SIZE = 10f;

    public void render(PDDocument document, PDPage page) throws IOException {
        PDRectangle pageSize = page.getMediaBox();
        PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        float textWidth = font.getStringWidth(HEADER_TEXT) / 1000f * FONT_SIZE;
        float x = pageSize.getLowerLeftX() + (pageSize.getWidth() - textWidth) / 2f;
        float y = pageSize.getUpperRightY() - 20f;

        try (PDPageContentStream stream = new PDPageContentStream(document, page,
                PDPageContentStream.AppendMode.PREPEND, true, true)) {
            stream.beginText();
            stream.setFont(font, FONT_SIZE);
            stream.newLineAtOffset(x, y);
            stream.showText(HEADER_TEXT);
            stream.endText();
        }
    }
}
