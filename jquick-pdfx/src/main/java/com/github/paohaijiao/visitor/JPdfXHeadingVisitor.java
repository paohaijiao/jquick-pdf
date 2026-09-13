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
public class JPdfXHeadingVisitor extends JPdfXParagraphVisitor {

    @Override
    public JQuickTextElementRender visitHeading(JQuickPDFParser.HeadingContext ctx) {
        int level = 1;
        if (ctx.number() != null && !ctx.number().isEmpty()) {
            level = Integer.parseInt(ctx.number().get(0).getText());
        }
        List<Object> value = null;
        if (ctx.elemValue() != null) {
            value = visitElemValue(ctx.elemValue());
        }
        JStyleAttributes style;
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        applyHeadingDefaults(style, level);
        String text = buildHeadingText(value);
        JQuickTextElementRender textElement = new JQuickTextElementRender(trim(text), style);
        // <h1>~<h6> 是块级元素：排版时应用自身的上下外边距，与相邻块保持声明好的间距。
        textElement.setBlockLevel(true);
        super.buildStyle(textElement, style);
        return textElement;
    }

    private String buildHeadingText(List<Object> values) {
        if (values == null || values.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Object value : values) {
            if (value instanceof String) {
                builder.append(value);
            }
        }
        return builder.toString();
    }

    private void applyHeadingDefaults(JStyleAttributes style, int level) {
        style.putIfAbsent("font-weight", "bold");
        style.putIfAbsent("text-align", "left");
        switch (level) {
            case 1:
                style.putIfAbsent("font-size", "24");
                style.putIfAbsent("line-height", "34");
                break;
            case 2:
                style.putIfAbsent("font-size", "20");
                style.putIfAbsent("line-height", "30");
                break;
            case 3:
                style.putIfAbsent("font-size", "16");
                style.putIfAbsent("line-height", "24");
                break;
            case 4:
                style.putIfAbsent("font-size", "14");
                style.putIfAbsent("line-height", "22");
                break;
            case 5:
                style.putIfAbsent("font-size", "12");
                style.putIfAbsent("line-height", "20");
                break;
            case 6:
                style.putIfAbsent("font-size", "10");
                style.putIfAbsent("line-height", "18");
                break;
            default:
                style.putIfAbsent("font-size", "14");
                style.putIfAbsent("line-height", "22");
                break;
        }
    }
}
