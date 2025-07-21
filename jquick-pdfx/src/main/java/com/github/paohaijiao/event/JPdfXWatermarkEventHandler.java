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

import com.github.paohaijiao.config.JWaterRemarkConfig;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

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

    private JWaterRemarkConfig watermarkConfig;

    public JPdfXWatermarkEventHandler(JWaterRemarkConfig watermarkConfig) {
        this.watermarkConfig = watermarkConfig;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
        pdfCanvas.saveState();
        PdfExtGState gs = new PdfExtGState();
        gs.setFillOpacity(watermarkConfig.getFillOpacity());
        pdfCanvas.setExtGState(gs);
        Canvas canvas = new Canvas(pdfCanvas, page.getPageSize());
        canvas.showTextAligned(
                new Paragraph(watermarkConfig.getWatermarkText()).setFont(watermarkConfig.getFont())
                        .setFontSize(60)
                        .setRotationAngle(Math.PI / 4),
                page.getPageSize().getWidth() / 2,
                page.getPageSize().getHeight() / 2,
                TextAlignment.CENTER,
                VerticalAlignment.MIDDLE);
        canvas.close();
        pdfCanvas.restoreState();
        pdfCanvas.release();
    }
}
