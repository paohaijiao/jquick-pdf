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
import com.github.paohaijiao.visitor.element.JQuickElementRender;
import com.github.paohaijiao.visitor.element.JQuickParagraphElementRender;
import com.github.paohaijiao.visitor.element.JQuickTextElementRender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public JQuickElementRender visitParagraph(JQuickPDFParser.ParagraphContext ctx) {
        JStyleAttributes style = ctx.styleEle() != null ? visitStyleEle(ctx.styleEle()) : new JStyleAttributes();
        style.putIfAbsent("line-height", "20");
        StringBuilder text = new StringBuilder();
        List<JQuickElementRender> children = new ArrayList<>();
        boolean hasInlineElement = false;
        if (ctx.elemValue() != null) {
            List<Object> values = visitElemValue(ctx.elemValue());
            for (Object value : values) {
                if (value instanceof JQuickElementRender) {
                    // 行内子元素（<span>、<tab> 等）与裸文本可以混排，需按声明顺序保留。
                    hasInlineElement = true;
                    children.add((JQuickElementRender) value);
                } else if (value != null) {
                    String piece = JStringUtils.trim(String.valueOf(value));
                    text.append(piece);
                    if (!piece.isEmpty()) {
                        children.add(new JQuickTextElementRender(piece));
                    }
                }
            }
        }
        if (!hasInlineElement) {
            // 纯文本段落：直接复用文本渲染器。它是块级元素，排版时应用自身的上下外边距。
            JQuickTextElementRender textElement = new JQuickTextElementRender(JStringUtils.trim(text.toString()), style);
            textElement.setBlockLevel(true);
            super.buildStyle(textElement, style);
            return textElement;
        }
        // 含行内子元素的段落（<span>、<tab> 等）：交给段落容器按行内流依次排布。
        for (JQuickElementRender child : children) {
            if (child instanceof JQuickTextElementRender) {
                JQuickTextElementRender inlineText = (JQuickTextElementRender) child;
                inlineText.setInline(true);
                inheritStyle(inlineText, style);
            }
        }
        JQuickParagraphElementRender paragraph = new JQuickParagraphElementRender(children, style);
        super.buildStyle(paragraph, style);
        return paragraph;
    }

    /**
     * 行内文本继承段落的样式（HTML 的样式继承语义）：仅补齐子元素未声明的属性，
     * 并跳过段落的块级外边距与对齐方式，避免行内文本被段落再次定位。
     */
    private void inheritStyle(JQuickTextElementRender child, JStyleAttributes paragraphStyle) {
        JStyleAttributes childStyle = child.getStyle();
        if (childStyle == null) {
            childStyle = new JStyleAttributes();
            childStyle.putAll(paragraphStyle);
            child.setStyle(childStyle);
            return;
        }
        for (Map.Entry<String, String> entry : paragraphStyle.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("margin") || "textAlignment".equals(key) || "text-align".equals(key)) {
                continue;
            }
            childStyle.putIfAbsent(key, entry.getValue());
        }
    }
}
