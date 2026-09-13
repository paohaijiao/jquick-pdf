/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.event;

import com.github.paohaijiao.config.JHeaderConfig;
import com.github.paohaijiao.enums.JAlign;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;

public class JHeaderHandler {

    private final JHeaderConfig headerConfig;

    public JHeaderHandler(JHeaderConfig headerConfig) {
        this.headerConfig = headerConfig;
    }

    public void render(PDDocument document, PDPage page) throws IOException {
        if (headerConfig == null || !headerConfig.isEnabled()) {
            return;
        }
        PDRectangle pageSize = page.getMediaBox();
        PDFont font = headerConfig.getFont().resolve(document);
        String text = headerConfig.getText() == null ? "" : headerConfig.getText();
        float y = pageSize.getUpperRightY() - headerConfig.getHeight() / 2f;

        try (PDPageContentStream stream = new PDPageContentStream(document, page,
                PDPageContentStream.AppendMode.PREPEND, true, true)) {
            if (headerConfig.getBackgroundColor() != null) {
                stream.setNonStrokingColor(headerConfig.getBackgroundColor());
                stream.addRect(pageSize.getLowerLeftX(),
                        pageSize.getUpperRightY() - headerConfig.getHeight(),
                        pageSize.getWidth(), headerConfig.getHeight());
                stream.fill();
            }
            stream.beginText();
            stream.setFont(font, headerConfig.getFontSize());
            stream.setNonStrokingColor(headerConfig.getFontColor());
            stream.newLineAtOffset(calculateXPosition(font, text, pageSize), y);
            stream.showText(text);
            stream.endText();
        }
    }

    private float calculateXPosition(PDFont font, String text, PDRectangle pageSize)
            throws IOException {
        float width = font.getStringWidth(text) / 1000f * headerConfig.getFontSize();
        JAlign alignment = headerConfig.getAlignment();
        if (alignment == JAlign.right) {
            return pageSize.getUpperRightX() - 20f - width;
        }
        if (alignment == JAlign.center || alignment == JAlign.justify) {
            return pageSize.getLowerLeftX() + (pageSize.getWidth() - width) / 2f;
        }
        return pageSize.getLowerLeftX() + 20f;
    }
}
