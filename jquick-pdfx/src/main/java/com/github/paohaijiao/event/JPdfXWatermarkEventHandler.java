/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.event;

import com.github.paohaijiao.config.JWaterRemarkConfig;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;

public class JPdfXWatermarkEventHandler {

    private final JWaterRemarkConfig watermarkConfig;

    public JPdfXWatermarkEventHandler(JWaterRemarkConfig watermarkConfig) {
        this.watermarkConfig = watermarkConfig;
    }

    public void render(PDDocument document, PDPage page) throws IOException {
        if (watermarkConfig == null || !Boolean.TRUE.equals(watermarkConfig.getEnabled())) {
            return;
        }
        PDRectangle pageSize = page.getMediaBox();
        PDFont font = watermarkConfig.getFont().resolve(document);
        String text = watermarkConfig.getWatermarkText() == null ? "" : watermarkConfig.getWatermarkText();
        float fontSize = 60f;
        float centerX = pageSize.getLowerLeftX() + pageSize.getWidth() / 2f;
        float centerY = pageSize.getLowerLeftY() + pageSize.getHeight() / 2f;
        float width = font.getStringWidth(text) / 1000f * fontSize;

        try (PDPageContentStream stream = new PDPageContentStream(document, page,
                PDPageContentStream.AppendMode.PREPEND, true, true)) {
            PDExtendedGraphicsState state = new PDExtendedGraphicsState();
            state.setNonStrokingAlphaConstant(watermarkConfig.getFillOpacity());
            stream.setGraphicsStateParameters(state);
            stream.beginText();
            stream.setFont(font, fontSize);
            stream.setTextMatrix(Matrix.getRotateInstance((float) (Math.PI / 4),
                    centerX - width / 2f, centerY));
            stream.showText(text);
            stream.endText();
        }
    }
}
