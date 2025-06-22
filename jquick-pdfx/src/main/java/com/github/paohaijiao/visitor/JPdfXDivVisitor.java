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
package com.github.paohaijiao.visitor;

import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.borders.SolidBorder;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXDivVisitor
 * @date 2025/6/15
 * @description
 */
public class JPdfXDivVisitor extends JPdfXSvgVisitor {
    @Override
    public Void visitDiv(JQuickPDFParser.DivContext ctx) {
        Paragraph divHeading = new Paragraph("Section Title in Div")
                .setFontSize(18)
                .setBold()
                .setMarginBottom(5);
        Div headerDiv = new Div()
                .setBorder(new SolidBorder(1))
                .setPadding(10)
                .setMarginBottom(20);
        headerDiv.add(divHeading);
        Document document = new Document(this.pdf);
        document.add(headerDiv);
        document.close();
        return null;
    }






}
