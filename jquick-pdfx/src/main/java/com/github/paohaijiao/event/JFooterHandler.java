/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.event;

import com.github.paohaijiao.config.JFooterConfig;
import com.github.paohaijiao.enums.JAlign;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;

public class JFooterHandler {

    private final JFooterConfig footerConfig;

    public JFooterHandler(JFooterConfig footerConfig) {
        this.footerConfig = footerConfig;
    }

    public void render(PDDocument document, PDPage page, int pageNumber) throws IOException {
        if (footerConfig == null || !footerConfig.isEnabled()) {
            return;
        }
        PDRectangle pageSize = page.getMediaBox();
        PDFont font = footerConfig.getFont().resolve(document);
        String text = footerConfig.isShowPageNumber()
                ? String.format(footerConfig.getPageNumberFormat(), pageNumber) : "";
        float y = footerConfig.getHeight() / 2f;

        try (PDPageContentStream stream = new PDPageContentStream(document, page,
                PDPageContentStream.AppendMode.PREPEND, true, true)) {
            if (footerConfig.getBackgroundColor() != null) {
                stream.setNonStrokingColor(footerConfig.getBackgroundColor());
                stream.addRect(pageSize.getLowerLeftX(), pageSize.getLowerLeftY(),
                        pageSize.getWidth(), footerConfig.getHeight());
                stream.fill();
            }
            stream.beginText();
            stream.setFont(font, footerConfig.getFontSize());
            stream.setNonStrokingColor(footerConfig.getFontColor());
            stream.newLineAtOffset(calculateXPosition(font, text, pageSize), y);
            stream.showText(text);
            stream.endText();
        }
    }

    private float calculateXPosition(PDFont font, String text, PDRectangle pageSize)
            throws IOException {
        float width = font.getStringWidth(text) / 1000f * footerConfig.getFontSize();
        JAlign alignment = footerConfig.getAlignment();
        if (alignment == JAlign.right) {
            return pageSize.getUpperRightX() - 20f - width;
        }
        if (alignment == JAlign.center || alignment == JAlign.justify) {
            return pageSize.getLowerLeftX() + (pageSize.getWidth() - width) / 2f;
        }
        return pageSize.getLowerLeftX() + 20f;
    }
}
