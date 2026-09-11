/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.extension.tab;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.element.JQuickElementRender;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * packageName com.github.paohaijiao.extension.tab
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class TabContainerRenderer {

    private static final float DEFAULT_FONT_SIZE = 12f;

    private final TabContainer modelElement;

    public TabContainerRenderer(TabContainer modelElement) {
        this.modelElement = modelElement;
    }

    public TabContainerRenderer getNextRenderer() {
        return new TabContainerRenderer(modelElement);
    }

    public void draw(PDPageContentStream stream,
                     JQuickRenderContext context) throws IOException {
        if (stream == null || context == null || modelElement == null) {
            return;
        }
        List<TabPage> tabs = modelElement.getTabs();
        if (tabs == null || tabs.isEmpty()) {
            return;
        }
        float width = resolveWidth(context);
        if (width <= 0f) {
            return;
        }
        float tabHeight = modelElement.getTabHeight();
        float contentHeight = modelElement.getContentHeight();
        float totalHeight = tabHeight + contentHeight;
        if (context.getLayoutEngine() != null) {
            context.getLayoutEngine().ensureSpace(totalHeight + modelElement.getMarginBottom(), false);
            stream = context.getLayoutEngine().getStream();
        }
        float startX = context.getCursorX();
        float topY = context.getCursorY();
        drawTabBar(stream, context, tabs, startX, topY, width, tabHeight);
        drawContentBox(stream, startX, topY - tabHeight, width, contentHeight);
        drawActiveContent(stream, context, tabs, startX, topY, width, tabHeight, contentHeight);
        context.setCursorY(topY - totalHeight - modelElement.getMarginBottom());
    }

    private float resolveWidth(JQuickRenderContext context) {
        if (modelElement.getWidth() > 0f) {
            return modelElement.getWidth();
        }
        if (context.getWidth() > 0f) {
            return context.getWidth();
        }
        float[] margins = context.getMargins();
        if (context.getPageWidth() > 0f && margins != null && margins.length >= 4) {
            return context.getPageWidth() - margins[1] - margins[3];
        }
        return 500f;
    }

    private void drawTabBar(PDPageContentStream stream,
                            JQuickRenderContext context,
                            List<TabPage> tabs,
                            float startX,
                            float topY,
                            float width,
                            float tabHeight) throws IOException {
        float tabWidth = width / tabs.size();
        float tabX = startX;
        for (int i = 0; i < tabs.size(); i++) {
            TabPage tab = tabs.get(i);
            boolean active = tab.isActive();
            PDColor fillColor = active ? modelElement.getActiveTabBackgroundColor() : modelElement.getTabBackgroundColor();
            stream.setNonStrokingColor(fillColor);
            stream.addRect(tabX, topY - tabHeight, tabWidth, tabHeight);
            stream.fill();
            stream.setStrokingColor(modelElement.getTabBorderColor());
            stream.setLineWidth(1f);
            stream.addRect(tabX, topY - tabHeight, tabWidth, tabHeight);
            stream.stroke();
            PDFont font = resolveFont(context, tab.getFont());
            drawCenteredText(stream, font, modelElement.getTabTextColor(), tab.getTitle(), tabX, topY - tabHeight,
                    tabWidth, tabHeight, DEFAULT_FONT_SIZE);
            tabX += tabWidth;
        }
    }

    private void drawContentBox(PDPageContentStream stream,
                                float startX,
                                float topY,
                                float width,
                                float contentHeight) throws IOException {
        stream.setStrokingColor(modelElement.getTabBorderColor());
        stream.setLineWidth(1f);
        stream.addRect(startX, topY - contentHeight, width, contentHeight);
        stream.stroke();
    }

    private void drawActiveContent(PDPageContentStream stream,
                                   JQuickRenderContext context,
                                   List<TabPage> tabs,
                                   float startX,
                                   float topY,
                                   float width,
                                   float tabHeight,
                                   float contentHeight) throws IOException {
        int activeIndex = Math.max(0, Math.min(modelElement.getActiveTabIndex(), tabs.size() - 1));
        TabPage activeTab = tabs.get(activeIndex);
        float padding = modelElement.getTabPadding();
        float contentX = startX + padding;
        float contentY = topY - tabHeight - padding;
        float contentWidth = Math.max(0f, width - padding * 2f);
        float contentAreaHeight = Math.max(0f, contentHeight - padding * 2f);
        PDFont font = resolveFont(context, activeTab.getFont());
        float y = contentY;
        for (Object content : activeTab.getContents()) {
            if (content == null) {
                continue;
            }
            if (content instanceof JQuickElementRender) {
                float oldCursorX = context.getCursorX();
                float oldCursorY = context.getCursorY();
                float oldWidth = context.getWidth();
                float oldHeight = context.getHeight();
                context.setCursorX(contentX);
                context.setCursorY(y);
                context.setWidth(contentWidth);
                context.setHeight(contentAreaHeight);
                ((JQuickElementRender) content).draw(stream, context);
                y = context.getCursorY() - padding / 2f;
                context.setCursorX(oldCursorX);
                context.setCursorY(oldCursorY);
                context.setWidth(oldWidth);
                context.setHeight(oldHeight);
                if (context.getLayoutEngine() != null) {
                    stream = context.getLayoutEngine().getStream();
                }
            } else {
                y = drawTextBlock(stream, font, modelElement.getTabTextColor(), String.valueOf(content),
                        contentX, y, contentWidth, DEFAULT_FONT_SIZE);
            }
        }
    }

    private float drawTextBlock(PDPageContentStream stream,
                                PDFont font,
                                PDColor color,
                                String text,
                                float x,
                                float topY,
                                float width,
                                float fontSize) throws IOException {
        if (text == null || text.isEmpty()) {
            return topY;
        }
        float lineHeight = fontSize + 6f;
        float y = topY;
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            y -= lineHeight;
            drawText(stream, font, color, line, x, y, width, fontSize, false);
        }
        return y;
    }

    private void drawCenteredText(PDPageContentStream stream,
                                  PDFont font,
                                  PDColor color,
                                  String text,
                                  float x,
                                  float y,
                                  float width,
                                  float height,
                                  float fontSize) throws IOException {
        float baseline = y + (height - fontSize) / 2f + 2f;
        drawText(stream, font, color, text, x, baseline, width, fontSize, true);
    }

    private void drawText(PDPageContentStream stream,
                          PDFont font,
                          PDColor color,
                          String text,
                          float x,
                          float baseline,
                          float width,
                          float fontSize,
                          boolean center) throws IOException {
        if (text == null || text.isEmpty()) {
            return;
        }
        String safeText = safeText(font, text);
        float textWidth = font.getStringWidth(safeText) / 1000f * fontSize;
        float textX = center ? x + Math.max(0f, (width - textWidth) / 2f) : x;
        stream.beginText();
        stream.setFont(font, fontSize);
        stream.setNonStrokingColor(color);
        stream.setTextMatrix(Matrix.getTranslateInstance(textX, baseline));
        stream.showText(safeText);
        stream.endText();
    }

    private PDFont resolveFont(JQuickRenderContext context,
                               PDFont preferredFont) throws IOException {
        if (preferredFont != null) {
            return preferredFont;
        }
        if (context.getFont() != null) {
            return context.getFont();
        }
        PDDocument document = context.getDocument();
        if (document != null) {
            PDFont font = loadChineseFont(document);
            if (font != null) {
                return font;
            }
        }
        return new PDType1Font(Standard14Fonts.FontName.HELVETICA);
    }

    private PDFont loadChineseFont(PDDocument document) throws IOException {
        InputStream inputStream = TabContainerRenderer.class.getClassLoader().getResourceAsStream("fonts/simhei.ttf");
        if (inputStream != null) {
            try {
                return PDType0Font.load(document, inputStream);
            } finally {
                inputStream.close();
            }
        }
        String[] candidates = new String[]{
                "fonts/simhei.ttf",
                "jquick-pdf-font/src/main/resources/fonts/simhei.ttf",
                "../jquick-pdf-font/src/main/resources/fonts/simhei.ttf"
        };
        for (String candidate : candidates) {
            File fontFile = new File(candidate);
            if (fontFile.exists()) {
                return PDType0Font.load(document, fontFile);
            }
        }
        return null;
    }

    private String safeText(PDFont font, String text) {
        if (!(font instanceof PDType1Font)) {
            return text;
        }
        StringBuilder builder = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            builder.append(ch <= 255 ? ch : '?');
        }
        return builder.toString();
    }
}
