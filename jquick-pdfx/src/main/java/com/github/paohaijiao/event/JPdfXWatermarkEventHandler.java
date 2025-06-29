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

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;

import java.io.IOException;

/**
 * packageName com.github.paohaijiao.event
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXWatermarkEventHandler
 * @date 2025/6/22
 * @description
 */
public class JPdfXWatermarkEventHandler implements IEventHandler {
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), docEvent.getDocument());
        canvas.saveState()
                .setFillColor(ColorConstants.LIGHT_GRAY)
                .setTextMatrix(30, 30)
                .beginText()
                .setFontAndSize(font, 60)
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL)
                .setTextMatrix(AffineTransform.getRotateInstance(Math.PI / 4))
                .showText("DRAFT")
                .endText()
                .restoreState();

        canvas.release();
    }
}
