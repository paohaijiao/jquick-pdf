/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.paohaijiao.event;

import com.github.paohaijiao.config.JWaterRemarkConfig;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;
import java.io.InputStream;

/**
 * PDFBox 水印处理器。
 *
 * <p>支持：文字 / 图片水印、单枚居中或对角平铺、半透明、旋转角度、
 * 按页规则（首页不水印、仅正文、奇偶页分开等，见 {@link JWatermarkPageRule}）。</p>
 *
 * <p>水印在正文渲染完成后统一盖印（AppendMode.PREPEND 置于内容之下）。</p>
 */
public class JPdfXWatermarkEventHandler {

    private final JWaterRemarkConfig watermarkConfig;

    public JPdfXWatermarkEventHandler(JWaterRemarkConfig watermarkConfig) {
        this.watermarkConfig = watermarkConfig;
    }

    /**
     * 对整份文档按配置盖印水印（内部按页规则逐页判断）。
     *
     * @param document 目标文档
     * @throws IOException 绘制失败
     */
    public void renderAll(PDDocument document) throws IOException {
        if (watermarkConfig == null || !Boolean.TRUE.equals(watermarkConfig.getEnabled())) {
            return;
        }
        int pageCount = document.getNumberOfPages();
        for (int i = 0; i < pageCount; i++) {
            renderPage(document, document.getPage(i), i);
        }
    }

    /**
     * 对单页盖印水印（0-based 页索引用于按页规则判断）。
     */
    public void renderPage(PDDocument document, PDPage page, int pageIndex) throws IOException {
        if (watermarkConfig == null || !Boolean.TRUE.equals(watermarkConfig.getEnabled())) {
            return;
        }
        JWaterRemarkConfig rule = watermarkConfig;
        if (rule.getPageRule() != null && !rule.getPageRule().matches(pageIndex)) {
            return;
        }
        if (rule.getImagePath() != null && !rule.getImagePath().trim().isEmpty()) {
            drawImageWatermark(document, page);
        } else {
            drawTextWatermark(document, page);
        }
    }

    private void drawTextWatermark(PDDocument document, PDPage page) throws IOException {
        PDFont font = watermarkConfig.getFont().resolve(document);
        String text = watermarkConfig.getWatermarkText() == null ? "" : watermarkConfig.getWatermarkText();
        if (text.isEmpty()) {
            return;
        }
        float fontSize = watermarkConfig.getFontSize() == null ? 60f : watermarkConfig.getFontSize();
        float radians = (float) Math.toRadians(
                watermarkConfig.getRotationDegrees() == null ? 45f : watermarkConfig.getRotationDegrees());
        PDRectangle pageSize = page.getMediaBox();
        float textWidth = font.getStringWidth(text) / 1000f * fontSize;
        float centerX = pageSize.getLowerLeftX() + pageSize.getWidth() / 2f;
        float centerY = pageSize.getLowerLeftY() + pageSize.getHeight() / 2f;

        try (PDPageContentStream stream = openStream(document, page)) {
            applyOpacity(stream);
            stream.setFont(font, fontSize);
            if (Boolean.TRUE.equals(watermarkConfig.getTiled())) {
                float stepX = textWidth
                        + (watermarkConfig.getTileGapX() == null ? 150f : watermarkConfig.getTileGapX());
                float stepY = fontSize
                        + (watermarkConfig.getTileGapY() == null ? 150f : watermarkConfig.getTileGapY());
                for (float y = pageSize.getUpperRightY(); y > pageSize.getLowerLeftY() - stepY; y -= stepY) {
                    for (float x = pageSize.getLowerLeftX() - textWidth;
                         x < pageSize.getUpperRightX() + textWidth; x += stepX) {
                        stream.beginText();
                        stream.setTextMatrix(Matrix.getRotateInstance(radians, x, y));
                        stream.showText(text);
                        stream.endText();
                    }
                }
            } else {
                stream.beginText();
                stream.setTextMatrix(Matrix.getRotateInstance(radians, centerX - textWidth / 2f, centerY));
                stream.showText(text);
                stream.endText();
            }
        }
    }

    private void drawImageWatermark(PDDocument document, PDPage page) throws IOException {
        PDImageXObject image = resolveImage(document);
        if (image == null) {
            return;
        }
        PDRectangle pageSize = page.getMediaBox();
        float scale = watermarkConfig.getImageScale() == null ? 0.4f : watermarkConfig.getImageScale();
        float drawWidth = pageSize.getWidth() * Math.max(0.05f, Math.min(1f, scale));
        float drawHeight = drawWidth * image.getHeight() / (float) image.getWidth();
        float radians = (float) Math.toRadians(
                watermarkConfig.getRotationDegrees() == null ? 45f : watermarkConfig.getRotationDegrees());
        float centerX = pageSize.getLowerLeftX() + pageSize.getWidth() / 2f;
        float centerY = pageSize.getLowerLeftY() + pageSize.getHeight() / 2f;

        try (PDPageContentStream stream = openStream(document, page)) {
            applyOpacity(stream);
            if (Boolean.TRUE.equals(watermarkConfig.getTiled())) {
                float stepX = drawWidth
                        + (watermarkConfig.getTileGapX() == null ? 150f : watermarkConfig.getTileGapX());
                float stepY = drawHeight
                        + (watermarkConfig.getTileGapY() == null ? 150f : watermarkConfig.getTileGapY());
                for (float y = pageSize.getLowerLeftY() + drawHeight;
                     y < pageSize.getUpperRightY() + drawHeight; y += stepY) {
                    for (float x = pageSize.getLowerLeftX() - drawWidth;
                         x < pageSize.getUpperRightX() + drawWidth; x += stepX) {
                        drawRotatedImage(stream, image, x + drawWidth / 2f, y - drawHeight / 2f,
                                radians, drawWidth, drawHeight);
                    }
                }
            } else {
                drawRotatedImage(stream, image, centerX, centerY, radians, drawWidth, drawHeight);
            }
        }
    }

    private void drawRotatedImage(PDPageContentStream stream, PDImageXObject image,
                                  float centerX, float centerY, float radians,
                                  float width, float height) throws IOException {
        stream.saveGraphicsState();
        stream.transform(Matrix.getTranslateInstance(centerX, centerY));
        stream.transform(Matrix.getRotateInstance(radians, 0f, 0f));
        stream.drawImage(image, -width / 2f, -height / 2f, width, height);
        stream.restoreGraphicsState();
    }

    private void applyOpacity(PDPageContentStream stream) throws IOException {
        PDExtendedGraphicsState state = new PDExtendedGraphicsState();
        state.setNonStrokingAlphaConstant(watermarkConfig.getFillOpacity());
        state.setStrokingAlphaConstant(watermarkConfig.getFillOpacity());
        stream.setGraphicsStateParameters(state);
    }

    private PDPageContentStream openStream(PDDocument document, PDPage page) throws IOException {
        return new PDPageContentStream(document, page,
                PDPageContentStream.AppendMode.PREPEND, true, true);
    }

    private PDImageXObject resolveImage(PDDocument document) throws IOException {
        String path = watermarkConfig.getImagePath();
        if (path == null || path.trim().isEmpty()) {
            return null;
        }
        String trimmed = path.trim();
        if (trimmed.startsWith("classpath:")) {
            String resource = trimmed.substring("classpath:".length());
            InputStream is = Thread.currentThread().getContextClassLoader() == null
                    ? JPdfXWatermarkEventHandler.class.getResourceAsStream(resource)
                    : Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            if (is == null) {
                return null;
            }
            try (InputStream in = is) {
                return PDImageXObject.createFromByteArray(document, readAll(in), resource);
            }
        }
        return PDImageXObject.createFromFile(trimmed, document);
    }

    private static byte[] readAll(InputStream is) throws IOException {
        java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
        byte[] block = new byte[4096];
        int read;
        while ((read = is.read(block)) != -1) {
            buffer.write(block, 0, read);
        }
        return buffer.toByteArray();
    }
}
