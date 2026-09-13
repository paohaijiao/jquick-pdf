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
import com.github.paohaijiao.util.JStringUtils;
import com.github.paohaijiao.visitor.element.JQuickTextElementRender;

import java.util.List;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXParagraphVisitor extends JPdfXSpanVisitor {

    @Override
    public JQuickTextElementRender visitParagraph(JQuickPDFParser.ParagraphContext ctx) {
        String text = "";
        Object value = null;
        if (ctx.elemValue() != null) {
            value = visitElemValue(ctx.elemValue());
        }
        if (value instanceof String) {
            text = (String) value;
        }
        if (value instanceof List) {
            text = mergeText((List<?>) value);
        }
        JStyleAttributes jStyleAttributes = new JStyleAttributes();
        if (ctx.styleEle() != null) {
            jStyleAttributes = visitStyleEle(ctx.styleEle());
        }
        jStyleAttributes.putIfAbsent("line-height", "20");
        JQuickTextElementRender textElement = new JQuickTextElementRender(JStringUtils.trim(text), jStyleAttributes);
        // <p> 是块级元素：排版时应用自身的上下外边距，与相邻块保持声明好的间距。
        textElement.setBlockLevel(true);
        super.buildStyle(textElement, jStyleAttributes);
        return textElement;
    }

    private String mergeText(List<?> list) {
        StringBuilder builder = new StringBuilder();
        for (Object item : list) {
            if (item instanceof String) {
                builder.append(item);
            }
        }
        return builder.toString();
    }
}
