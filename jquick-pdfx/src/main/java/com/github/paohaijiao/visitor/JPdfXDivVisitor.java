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

import com.github.paohaijiao.factory.JFontProviderFactory;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.element.JQuickAreaBreakElementRender;
import com.github.paohaijiao.visitor.element.JQuickButtonElementRender;
import com.github.paohaijiao.visitor.element.JQuickCheckBoxElementRender;
import com.github.paohaijiao.visitor.element.JQuickComboBoxElementRender;
import com.github.paohaijiao.visitor.element.JQuickImageElementRender;
import com.github.paohaijiao.visitor.element.JQuickLineSeparatorElementRender;
import com.github.paohaijiao.visitor.element.JQuickListElementRender;
import com.github.paohaijiao.visitor.element.JQuickSvgElementRender;
import com.github.paohaijiao.visitor.element.JQuickTabElementRender;
import com.github.paohaijiao.visitor.element.JQuickTableElementRender;
import com.github.paohaijiao.visitor.element.JQuickTextAreaElementRender;
import com.itextpdf.layout.element.*;

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
        div.setFont(JFontProviderFactory.defualtFont());
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
            p.setFont(JFontProviderFactory.defualtFont());
            div.add(p);
        }
        if (null != ctx.element() && !ctx.element().isEmpty()) {
            for (JQuickPDFParser.ElementContext styleContext : ctx.element()) {
                Object object = visitElement(styleContext);
                if (null != object && object instanceof Image) {
                    Image image = (Image) object;
                    div.add(image);
                }
                if (null != object && object instanceof IBlockElement) {
                    IBlockElement blockElement = (IBlockElement) object;
                    doc.add(blockElement);
                }
                if (null != object && object instanceof JQuickAreaBreakElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickButtonElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickCheckBoxElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickComboBoxElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickTextAreaElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickImageElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickLineSeparatorElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickTabElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickListElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickSvgElementRender) {
                    return div;
                }
                if (null != object && object instanceof JQuickTableElementRender) {
                    return div;
                }
                if (null != object && object instanceof String) {
                    Paragraph paragraph = new Paragraph((String) object);
                    paragraph.setFont(JFontProviderFactory.defualtFont());
                    doc.add(paragraph);
                }
            }

        }
        super.buildStyle(div, style);
        return div;
    }


}
