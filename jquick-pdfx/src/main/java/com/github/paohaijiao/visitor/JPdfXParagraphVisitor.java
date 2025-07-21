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
import com.github.paohaijiao.util.JStringUtils;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.ILeafElement;
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
public class JPdfXParagraphVisitor extends JPdfXSpanVisitor  {
    @Override
    public Paragraph visitParagraph(JQuickPDFParser.ParagraphContext ctx) {
        String text = "";
        Object value = null;
        if (ctx.elemValue() != null) {
            value =visitElemValue(ctx.elemValue());
            if (null != value && value instanceof String) {
                text = (String) value;
            }
        }
        Paragraph h1 = new Paragraph(JStringUtils.trim(text));
        h1.setFont(JFontProviderFactory.defualtFont());
        saveSub(h1,value);
        JStyleAttributes jStyleAttributes = new JStyleAttributes();
        if (ctx.styleEle() != null) {
            jStyleAttributes = visitStyleEle(ctx.styleEle());
        }
        super.buildStyle(h1, jStyleAttributes);
        return h1;
    }

    private void saveSub(Paragraph paragraph,Object object) {
        if(null!=object&&object instanceof java.util.List) {
            java.util.List<Object> list=(java.util.List<Object>) object;
            list.forEach(e -> {
                if (e instanceof String) {
                    paragraph.add((String) e);
                }
                if (e instanceof ILeafElement) {
                    paragraph.add((ILeafElement) e);
                }
                if (e instanceof IBlockElement) {
                    paragraph.add((IBlockElement) e);
                }
            });
        }
    }

}
