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

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.sample.ReportPainting;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.properties.AreaBreakType;

import java.io.File;
import java.io.FileNotFoundException;
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

    private String fileName = "d://test//DivBasedHeadings.pdf";

    public JPdfXCommonVisitor() throws FileNotFoundException {
        this.context = new JContext();
        initPdf(fileName);
    }

    public JPdfXCommonVisitor(JContext context) throws FileNotFoundException {
        this.context = context;
        initPdf(fileName);
    }

    public JPdfXCommonVisitor(String outputPath) throws IOException {
        initPdf(fileName);
    }

    @Override
    public Void visitDocument(JQuickPDFParser.DocumentContext ctx) {
        if (null != ctx.doc()) {
            visitDoc(ctx.doc());
        }
        return null;
    }

    @Override
    public Void visitDoc(JQuickPDFParser.DocContext ctx) {
        if (null != ctx.html()) {
            visitHtml(ctx.html());
        }
        return null;
    }

    @Override
    public Void visitHtml(JQuickPDFParser.HtmlContext ctx) {
        if (null != ctx.head()) {
            visitHead(ctx.head());
        }
        if (null != ctx.body()) {
            visitBody(ctx.body());
        }
        pdf.close();
        return null;
    }

    @Override
    public Void visitHead(JQuickPDFParser.HeadContext ctx) {
        if (null != ctx.headStyle()) {
            visitHeadStyle(ctx.headStyle());
        }
        return null;
    }

    @Override
    public Void visitHeadStyle(JQuickPDFParser.HeadStyleContext ctx) {
        if (null != ctx.headStyleOption()) {
            visitHeadStyleOption(ctx.headStyleOption());
        }
        if (null != ctx.bodyStyleOption()) {
            visitBodyStyleOption(ctx.bodyStyleOption());
        }
        return null;
    }

    @Override
    public Void visitHeadStyleOption(JQuickPDFParser.HeadStyleOptionContext ctx) {
        if (ctx.style() != null && !ctx.style().isEmpty()) {
            for (JQuickPDFParser.StyleContext styleContext : ctx.style()) {
                JStyleAttributes style = visitStyle(styleContext);
            }
        }
        return null;
    }

    @Override
    public Void visitBodyStyleOption(JQuickPDFParser.BodyStyleOptionContext ctx) {
        if (ctx.style() != null && !ctx.style().isEmpty()) {
            for (JQuickPDFParser.StyleContext styleContext : ctx.style()) {
                JStyleAttributes style = visitStyle(styleContext);
            }
        }
        return null;
    }

    @Override
    public Void visitBody(JQuickPDFParser.BodyContext ctx) {
        String text=ctx.getText();
        if (ctx.element() != null && !ctx.element().isEmpty()) {
            for (JQuickPDFParser.ElementContext elementContext : ctx.element()) {
                String elementTxt=elementContext.getText();
                Object object=visitElement(elementContext);
                if (object instanceof Image) {
                    Image image=(Image)object;
                    doc.add(image);
                }
                if (object instanceof IBlockElement) {
                    IBlockElement blockElement=(IBlockElement)object;
                    doc.add(blockElement);
                }
                if (object instanceof AreaBreak) {
                    AreaBreak areaBreak=(AreaBreak)object;
                    doc.add(areaBreak);
                }
            }
        }
//        ReportPainting painting = new ReportPainting(pdf, font);
//        painting.drawHello("image/纸质报告-03.png");
//        painting.close();
        return null;
    }


}
