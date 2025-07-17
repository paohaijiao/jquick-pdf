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
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.UnitValue;

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
    public Div visitDiv(JQuickPDFParser.DivContext ctx) {
        Div div = new Div();
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        String value = null;
        if (null != ctx.value()) {
            value = visitValue(ctx.value()).toString();
            Paragraph p = new Paragraph(value);
            div.add(p);
        }
        if (null != ctx.element()&& !ctx.element().isEmpty()) {
            for (JQuickPDFParser.ElementContext styleContext : ctx.element()) {
                Object object=visitElement(styleContext);
                if (null!=object &&object instanceof Image) {
                    Image image=(Image)object;
                    div.add(image);
                }
                if (null!=object && object instanceof IBlockElement) {
                    IBlockElement blockElement=(IBlockElement)object;
                    doc.add(blockElement);
                }
                if (null!=object && object instanceof AreaBreak) {
                    AreaBreak areaBreak=(AreaBreak)object;
                    doc.add(areaBreak);
                }
            }

        }
        super.buildStyle(div, style);
        return div;
    }


}
