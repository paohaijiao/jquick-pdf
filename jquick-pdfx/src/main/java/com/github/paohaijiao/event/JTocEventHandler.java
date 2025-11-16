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

import com.github.paohaijiao.config.JCatalogConfig;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;

public class JTocEventHandler implements IEventHandler {

    private PdfOutline rootOutline;

    private JCatalogConfig config;

    public JTocEventHandler(PdfDocument pdfDoc, JCatalogConfig pdfConfig) {
        this.rootOutline = pdfDoc.getOutlines(false);
        this.config = pdfConfig;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        if (config.getStartPage() == 2) {
            PdfDestination dest = PdfExplicitDestination.createFit(page);
            PdfOutline outline = rootOutline.addOutline("重要章节");
            outline.addDestination(dest);
        }
        config.setStartPage(config.getStartPage() + 1);
    }
}
