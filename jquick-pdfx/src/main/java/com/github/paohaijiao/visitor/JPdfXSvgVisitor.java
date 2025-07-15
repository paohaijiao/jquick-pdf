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
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.svg.converter.SvgConverter;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXSvgVisitor extends JPdfXImageVisitor {


    @Override
    public IElement visitSvg(JQuickPDFParser.SvgContext ctx) {
        String src = null;
        if (ctx.src() != null) {
            src = visitSrc(ctx.src());
        }
        String value = null;
        if (ctx.value() != null) {
            value = visitValue(ctx.value()).toString();
        }
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        float width = style.getWidth() == null ? 50f : Float.parseFloat(style.getWidth());
        float height = style.getHeight() == null ? 600f : Float.parseFloat(style.getHeight());
        Paragraph paragraph = new Paragraph(value == null ? "" : value);
        super.buildStyle(paragraph, style);
        doc.add(paragraph);
        SvgConverter.drawOnDocument(src, doc.getPdfDocument(), 1, width, height);
        return null;
    }

}
