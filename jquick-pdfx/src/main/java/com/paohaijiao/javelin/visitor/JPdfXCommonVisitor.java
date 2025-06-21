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
package com.paohaijiao.javelin.visitor;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.paohaijiao.javelin.parser.JQuickPDFParser;

import java.io.IOException;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXCommonVisitor extends JPdfXElementVisitor {
    public JPdfXCommonVisitor(String outputPath) throws IOException {
        PdfWriter writer = new PdfWriter(outputPath);
        this.pdfDoc = new PdfDocument(writer);
    }
    @Override
    public Void visitDocument(JQuickPDFParser.DocumentContext ctx) {
        for (JQuickPDFParser.PageContext pageCtx : ctx.page()) {
            visitPage(pageCtx);
        }
        if (document != null) {
            document.close();
        }
        return null;
    }
    @Override
    public Void visitPage(JQuickPDFParser.PageContext ctx) {
        PageSize pageSize = PageSize.A4;
        if (ctx.pageLayout() != null) {
            visitPageLayout(ctx.pageLayout());
        }
        if (ctx.pageLayout() != null) {
            pageSize = visitLayoutOption(ctx.pageLayout().layoutOption());
        }
        PdfPage currentPage = pdfDoc.addNewPage(pageSize);
//        if (ctx.landscape() != null) {
//            currentPage.setRotation(90);
//            pageSize = pageSize.rotate();
//        }
        float marginLeft = 72;
        float marginRight = 72;
        float marginTop = 72;
        float marginBottom = 72;
        if (ctx.margins() != null) {
            marginLeft = convertToPoints(Float.parseFloat(ctx.margins().number(0).getText()),
                    ctx.margins().unit(0).getText());
            marginTop = convertToPoints(Float.parseFloat(ctx.margins().number(1).getText()),
                    ctx.margins().unit(1).getText());
            marginRight = convertToPoints(Float.parseFloat(ctx.margins().number(2).getText()),
                    ctx.margins().unit(2).getText());
            marginBottom = convertToPoints(Float.parseFloat(ctx.margins().number(3).getText()),
                    ctx.margins().unit(3).getText());
        }
        if (document == null) {
            document = new Document(pdfDoc, pageSize);
        }
        document.setMargins(marginTop, marginRight, marginBottom, marginLeft);
        pdfDoc.addNewPage(currentPageSize);
        for (JQuickPDFParser.ElementContext elemCtx : ctx.element()) {
            visitElement(elemCtx);
        }
        if (document != null) {
            document.close();
        }
        return null;
    }

    @Override
    public Void visitPageLayout(JQuickPDFParser.PageLayoutContext ctx) {
        if (null!=ctx.layoutOption()) {
            currentPageSize =visitLayoutOption(ctx.layoutOption());
        }else if(null!=ctx.customOption()){
            currentPageSize =visitCustomOption(ctx.customOption());
        }else{
            currentPageSize=PageSize.A4;
        }
        return null;
    }

    @Override
    public Void visitMargins(JQuickPDFParser.MarginsContext ctx) {
        currentMargins[0] = convertToPoints(Float.parseFloat(ctx.number(0).getText()), ctx.unit(0).getText());
        currentMargins[1] = convertToPoints(Float.parseFloat(ctx.number(1).getText()), ctx.unit(1).getText());
        currentMargins[2] = convertToPoints(Float.parseFloat(ctx.number(2).getText()), ctx.unit(2).getText());
        currentMargins[3] = convertToPoints(Float.parseFloat(ctx.number(3).getText()), ctx.unit(3).getText());
        return null;
    }


}
