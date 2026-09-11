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
import com.github.paohaijiao.visitor.element.JQuickDivElementRender;
import com.github.paohaijiao.visitor.element.JQuickElementRender;
import com.github.paohaijiao.visitor.element.JQuickTextElementRender;

import java.util.ArrayList;
import java.util.List;

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
    public JQuickDivElementRender visitDiv(JQuickPDFParser.DivContext ctx) {
        JStyleAttributes style;
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        List<JQuickElementRender> children = new ArrayList<>();
        if (null != ctx.value()) {
            Object value = visitValue(ctx.value());
            if (value != null) {
                String text = JStringUtils.trim(value.toString());
                if (text != null && !text.isEmpty()) {
                    children.add(new JQuickTextElementRender(text, style));
                }
            }
        }
        if (null != ctx.element() && !ctx.element().isEmpty()) {
            for (JQuickPDFParser.ElementContext elementContext : ctx.element()) {
                Object object = visitElement(elementContext);
                if (object instanceof JQuickElementRender) {
                    children.add((JQuickElementRender) object);
                } else if (object instanceof String) {
                    String text = JStringUtils.trim(object.toString());
                    if (text != null && !text.isEmpty()) {
                        children.add(new JQuickTextElementRender(text, style));
                    }
                }
            }
        }
        JQuickDivElementRender div = new JQuickDivElementRender(style, children);
        super.buildStyle(div, style);
        return div;
    }
}
