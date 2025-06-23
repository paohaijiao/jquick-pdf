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

import com.github.paohaijiao.model.style.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.github.paohaijiao.param.JContext;

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
    private String fileName="d://test//DivBasedHeadings.pdf";
    public JPdfXCommonVisitor() throws FileNotFoundException {
        this.context=new JContext();
        PdfDocument pdf = new PdfDocument(new PdfWriter(fileName));
        this.pdf=pdf;
    }
    public JPdfXCommonVisitor(JContext context) throws FileNotFoundException {
        this.context=context;
        PdfDocument pdf = new PdfDocument(new PdfWriter(fileName));
        this.pdf=pdf;

    }
    public JPdfXCommonVisitor(String outputPath) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(fileName));
        this.pdf=pdf;
    }

    @Override
    public Void visitDocument(JQuickPDFParser.DocumentContext ctx) {
        if(null!=ctx.doc()){
            visitDoc(ctx.doc());
        }
        return null;
    }
    @Override
    public Void visitDoc(JQuickPDFParser.DocContext ctx) {
        if(null!=ctx.html()){
            visitHtml(ctx.html());
        }
        return null;
    }
    @Override
    public Void visitHtml(JQuickPDFParser.HtmlContext ctx) {
        if(null!=ctx.head()){
            visitHead(ctx.head());
        }
        if(null!=ctx.body()){
            visitBody(ctx.body());
        }
        return null;
    }
    @Override
    public Void visitHead(JQuickPDFParser.HeadContext ctx) {
        if(null!=ctx.headStyle()){
            visitHeadStyle(ctx.headStyle());
        }
        return null;
    }
    @Override
    public Void visitHeadStyle(JQuickPDFParser.HeadStyleContext ctx) {
        if(null!=ctx.headStyleOption()){
            visitHeadStyleOption(ctx.headStyleOption());
        }
        if(null!=ctx.bodyStyleOption()){
            visitBodyStyleOption(ctx.bodyStyleOption());
        }
        return null;
    }
    @Override
    public Void visitHeadStyleOption(JQuickPDFParser.HeadStyleOptionContext ctx) {
        if(ctx.style()!=null&&!ctx.style().isEmpty()){
            for (JQuickPDFParser.StyleContext styleContext :ctx.style()) {
                JStyleAttributes style= visitStyle(styleContext);
            }
        }
        return null;
    }
    @Override
    public Void visitBodyStyleOption(JQuickPDFParser.BodyStyleOptionContext ctx) {
        if(ctx.style()!=null&&!ctx.style().isEmpty()){
            for (JQuickPDFParser.StyleContext styleContext :ctx.style()) {
                JStyleAttributes style= visitStyle(styleContext);
            }
        }
        return null;
    }

    @Override
    public Void visitBody(JQuickPDFParser.BodyContext ctx) {
        if(ctx.element()!=null&&!ctx.element().isEmpty()){
            for (JQuickPDFParser.ElementContext styleContext :ctx.element()) {
                 visitElement(styleContext);
            }
        }
        return null;
    }





}
