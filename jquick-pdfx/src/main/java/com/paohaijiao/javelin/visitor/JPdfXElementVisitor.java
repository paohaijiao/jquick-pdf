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

import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.paohaijiao.javelin.parser.JQuickPDFParser;

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
    public IElement  visitElement(JQuickPDFParser.ElementContext ctx) {
        if (ctx.paragraph() != null) {
            return visitParagraph(ctx.paragraph());
        } else if (ctx.heading() != null) {
            return visitHeading(ctx.heading());
        } else if (ctx.list() != null) {
            return visitList(ctx.list());
        } else if (ctx.table() != null) {
            return visitTable(ctx.table());
        } else if (ctx.image() != null) {
            return visitImage(ctx.image());
        } else if (ctx.svg() != null) {
            return visitSvg(ctx.svg());
        } else if (ctx.div() != null) {
            return visitDiv(ctx.div());
        } else if (ctx.template() != null) {
            return visitTemplate(ctx.template());
        }
        return null;
    }







    @Override
    public Image visitSvg(JQuickPDFParser.SvgContext ctx) {
        return null;

    }

    @Override
    public IElement visitDiv(JQuickPDFParser.DivContext ctx) {
        Div div = new Div();
        if (ctx.divStyle() != null) {

        }
        for (JQuickPDFParser.ElementContext elementCtx : ctx.element()) {
            IElement element = visitElement(elementCtx);
            if (element instanceof BlockElement) {
                div.add((BlockElement) element);
            }
        }

        return div;
    }

    @Override
    public IElement visitTemplate(JQuickPDFParser.TemplateContext ctx) {
        Div templateContent = new Div();
        for (JQuickPDFParser.ElementContext elementCtx : ctx.element()) {
            IElement element = visitElement(elementCtx);
            if (element instanceof BlockElement) {
                templateContent.add((BlockElement) element);
            }
        }
        if (ctx.ID(1) != null) {
        }

        return templateContent;
    }
}
