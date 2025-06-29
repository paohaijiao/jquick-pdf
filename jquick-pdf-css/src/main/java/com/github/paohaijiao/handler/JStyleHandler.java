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
package com.github.paohaijiao.handler;

import com.github.paohaijiao.factory.JStyleRendererFactory;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.render.JStyleRenderer;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

/**
 * packageName com.github.paohaijiao.handler
 *
 * @author Martin
 * @version 1.0.0
 * @className JStyleHandler
 * @date 2025/6/27
 * @description
 */
public class JStyleHandler {
    public static void applyStyles(IElement element, JStyleAttributes styles) {
        if (element == null || styles == null) return;
        JStyleRenderer renderer = JStyleRendererFactory.getRenderer(element);
        renderer.applyStyles(element, styles);
    }

    public static void main(String[] args) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter("d://test//hello.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Paragraph paragraph = new Paragraph("Hello World");
        JStyleAttributes styles = new JStyleAttributes();
        styles.setBackgroundColor("#f0f0f0");
        styles.setColor("#333333");
        styles.setFontSize("16");
        styles.setPadding("10px");
        styles.setTextAlign("center");
        JStyleHandler.applyStyles(paragraph, styles);
        Document document = new Document(pdf);
        document.add(paragraph);
        document.close();

    }
}
