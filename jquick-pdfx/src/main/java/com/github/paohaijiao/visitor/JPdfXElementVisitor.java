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

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.github.paohaijiao.parser.JQuickPDFParser;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXElementVisitor extends JPdfXImageVisitor {


    @Override
    public Void visitElement(JQuickPDFParser.ElementContext ctx) {
//        if (ctx.paragraph() != null) {
//             visitParagraph(ctx.paragraph());
//        } else if (ctx.heading() != null) {
//
//        } else if (ctx.list() != null) {
//             visitList(ctx.list());
//        } else if (ctx.table() != null) {
//             visitTable(ctx.table());
//        } else if (ctx.image() != null) {
//             visitImage(ctx.image());
//        } else if (ctx.svg() != null) {
//             visitSvg(ctx.svg());
//        } else if (ctx.div() != null) {
//             visitDiv(ctx.div());
//        } else if (ctx.template() != null) {
//             visitTemplate(ctx.template());
//        }
        visitHeading(ctx.heading());

        return null;
    }


    @Override
    public Image visitSvg(JQuickPDFParser.SvgContext ctx) {
        return null;

    }

    @Override
    public IElement visitDiv(JQuickPDFParser.DivContext ctx) {
//        Div div = new Div();
//
//        for (JQuickPDFParser.ElementContext elementCtx : ctx.element()) {
//        }
//
//        return div;
        return null;
    }

    @Override
    public IElement visitTemplate(JQuickPDFParser.TemplateContext ctx) {
//        Div templateContent = new Div();
//        for (JQuickPDFParser.ElementContext elementCtx : ctx.element()) {
//
//        }
//        if (ctx.IDENTIFIER(1) != null) {
//        }
//
//        return templateContent;
        return null;
    }
}
