/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.extension.svg;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.util.JSvgUtil;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.element.JQuickElementRender;
import com.github.paohaijiao.visitor.element.JQuickSvgElementRender;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * SVG 元素的 PDFBox 渲染入口。
 */
public class SvgImage implements JQuickElementRender {

    private final String svgContent;
    private float explicitWidth = -1f;
    private float explicitHeight = -1f;

    public SvgImage(String svgContent) {
        this(svgContent, -1f, -1f);
    }

    public SvgImage(String svgContent, float width, float height) {
        this.svgContent = svgContent;
        float[] dimensions = JSvgUtil.parseSvgDimensions(svgContent);
        float svgWidth = dimensions[0];
        float svgHeight = dimensions[1];
        explicitWidth = width > 0f ? width : svgWidth;
        explicitHeight = height > 0f ? height : svgHeight;
        if (width > 0f && height <= 0f && svgWidth > 0f) {
            explicitHeight = svgHeight * width / svgWidth;
        } else if (height > 0f && width <= 0f && svgHeight > 0f) {
            explicitWidth = svgWidth * height / svgHeight;
        }
    }

    public SvgImage(InputStream svgStream) throws IOException {
        this(readContent(svgStream));
    }

    public SvgImage(InputStream svgStream, float width, float height) throws IOException {
        this(readContent(svgStream), width, height);
    }

    public SvgImage(URL svgUrl) throws IOException {
        this(readUrl(svgUrl));
    }

    public SvgImage(URL svgUrl, float width, float height) throws IOException {
        this(readUrl(svgUrl), width, height);
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        JStyleAttributes style = new JStyleAttributes();
        if (explicitWidth > 0f) {
            style.put("width", String.valueOf(explicitWidth) + "pt");
        }
        if (explicitHeight > 0f) {
            style.put("height", String.valueOf(explicitHeight) + "pt");
        }
        new JQuickSvgElementRender(svgContent, style).draw(stream, context);
    }

    public String getSvgContent() {
        return svgContent;
    }

    public float getExplicitWidth() {
        return explicitWidth;
    }

    public float getExplicitHeight() {
        return explicitHeight;
    }

    private static String readUrl(URL svgUrl) throws IOException {
        try (InputStream stream = svgUrl.openStream()) {
            return readContent(stream);
        }
    }

    private static String readContent(InputStream stream) throws IOException {
        try (InputStream input = stream;
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            return new String(output.toByteArray(), StandardCharsets.UTF_8);
        }
    }
}
