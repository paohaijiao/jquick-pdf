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
package com.github.paohaijiao.event;

import com.github.paohaijiao.config.JFooterConfig;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.properties.TextAlignment;

/**
 * packageName com.github.paohaijiao.event
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class JFooterHandler implements IEventHandler {
    private final JFooterConfig footerConfig;

    public JFooterHandler(JFooterConfig footerConfig) {
        this.footerConfig = footerConfig;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(),
                page.getResources(),
                docEvent.getDocument());

        if (footerConfig.isEnabled()) {
            drawFooter(canvas, pageSize, docEvent);
        }
        canvas.release();
    }


    private void drawFooter(PdfCanvas canvas, Rectangle pageSize, PdfDocumentEvent docEvent) {
        PdfFont font = footerConfig.getFont();
        if (font == null) {
            font = docEvent.getDocument().getDefaultFont();
        }
        float x = calculateXPosition(footerConfig.getAlignment(), pageSize);
        float y = footerConfig.getHeight() / 2;
        String footerText = "";
        if (footerConfig.isShowPageNumber()) {
            int pageNumber = docEvent.getDocument().getPageNumber(docEvent.getPage());
            footerText = String.format(footerConfig.getPageNumberFormat(), pageNumber);
        }
        if (footerConfig.getBackgroundColor() != null) {
            canvas.saveState()
                    .setFillColor(footerConfig.getBackgroundColor())
                    .rectangle(pageSize.getLeft(),
                            0,
                            pageSize.getWidth(),
                            footerConfig.getHeight())
                    .fill()
                    .restoreState();
        }
//        if (footerConfig.getBorder() != null) {
//            drawBorder(canvas, pageSize, 0, footerConfig.getBorder());
//        }
        canvas.beginText()
                .setFontAndSize(font, footerConfig.getFontSize())
                .setColor(footerConfig.getFontColor(), true)
                .moveText(x, y)
                .showText(footerText)
                .endText();
    }

    private float calculateXPosition(TextAlignment alignment, Rectangle pageSize) {
        switch (alignment) {
            case LEFT:
                return pageSize.getLeft() + 20;
            case RIGHT:
                return pageSize.getRight() - 20;
            case CENTER:
            default:
                return pageSize.getWidth() / 2;
        }
    }

//    private void drawBorder(PdfCanvas canvas, Rectangle pageSize, float y, BorderConfig border) {
//        canvas.saveState()
//                .setStrokeColor(border.getColor())
//                .setLineWidth(border.getWidth())
//                .moveTo(pageSize.getLeft(), y)
//                .lineTo(pageSize.getRight(), y)
//                .stroke()
//                .restoreState();
//    }
//
//    private void drawLogo(PdfCanvas canvas, Rectangle pageSize, ImageConfig logo) {
//        // 实现Logo绘制逻辑
//        // ...
//    }
}
