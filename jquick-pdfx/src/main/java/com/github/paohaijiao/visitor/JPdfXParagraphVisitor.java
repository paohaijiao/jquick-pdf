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
import com.github.paohaijiao.model.paragraph.JParagraphModel;
import com.github.paohaijiao.model.style.JStyleModel;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.sample.ReportStyle;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.util.ArrayList;
/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXParagraphVisitor extends JPdfXSpanVisitor  {
    @Override
    public Paragraph visitParagraph(JQuickPDFParser.ParagraphContext ctx) {
        Object value = null;
        if (ctx.elemValue() != null) {
            value =visitElemValue(ctx.elemValue());
        }
        String text="";
        java.util.List<BlockElement<?>> elements=new ArrayList<>();
        if (null != ctx.elemValue()) {
            Object  val = visitElemValue(ctx.elemValue());
            if (null != val && val instanceof String) {
                text = (String) val;
            } else if (null != val && val instanceof List) {
                elements=buildSubElem(val);
            }
        }
        Paragraph h1 = new Paragraph(text);
        if(!elements.isEmpty()){
            elements.forEach(e->{
                h1.add(e);
            });
        }
        JStyleAttributes jStyleAttributes = new JStyleAttributes();
        if (ctx.styleEle() != null) {
            jStyleAttributes = visitStyleEle(ctx.styleEle());
        }
        super.buildStyle(h1, jStyleAttributes);
        return h1;
    }

}
