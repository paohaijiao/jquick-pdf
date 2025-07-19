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
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXElementVisitor extends JPdfXDivVisitor {


    @Override
    public Object visitElement(JQuickPDFParser.ElementContext ctx) {
        String text=ctx.getText();
        if (ctx.paragraph() != null) {
            Paragraph paragraph= visitParagraph(ctx.paragraph());
            return paragraph;
        } else if (ctx.heading() != null) {
            return visitHeading(ctx.heading());
        } else if (ctx.list() != null) {
            return visitList(ctx.list());
        } else if (ctx.table() != null) {
            return  visitTable(ctx.table());
        } else if (ctx.image() != null) {
            return  visitImage(ctx.image());
        } else if (ctx.svg() != null) {
            return visitSvg(ctx.svg());
        } else if (ctx.div() != null) {
            return   visitDiv(ctx.div());
        } else if (ctx.template() != null) {
            return visitTemplate(ctx.template());
        }else if (ctx.span() != null) {
            return visitSpan(ctx.span());
        }
        else if (ctx.areaBreak() != null) {
            return visitAreaBreak(ctx.areaBreak());
        }else if(ctx.button()!=null){
            return visitButton(ctx.button());
        }
        return null;
    }


    @Override
    public IElement visitTemplate(JQuickPDFParser.TemplateContext ctx) {
        return null;
    }
}
