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
import com.github.paohaijiao.visitor.element.JQuickListElementRender;
import com.github.paohaijiao.visitor.element.JQuickTextElementRender;

import java.util.ArrayList;
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
public class JPdfXListVisitor extends JPdfXTableVisitor {

    @Override
    public JQuickListElementRender visitList(JQuickPDFParser.ListContext ctx) {
        JStyleAttributes style;
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        List<JQuickListElementRender.Item> items = new ArrayList<>();
        if (ctx.listItem() != null && !ctx.listItem().isEmpty()) {
            for (JQuickPDFParser.ListItemContext listItemContext : ctx.listItem()) {
                JStyleAttributes itemStyle = null == listItemContext.styleEle()
                        ? new JStyleAttributes() : visitStyleEle(listItemContext.styleEle());
                List<JQuickListElementRender.Run> runs = visitListItemRuns(listItemContext, itemStyle);
                if (!runs.isEmpty()) {
                    items.add(new JQuickListElementRender.Item(runs, itemStyle));
                }
            }
        }
        boolean ordered = isOrderedList(ctx);
        JQuickListElementRender list = new JQuickListElementRender(ordered, items, style);
        super.buildStyle(list, style);
        return list;
    }

    /** 收集 {@code <li>} 的行内片段：纯文本与 span 均保留各自样式，避免内容或样式丢失。 */
    private List<JQuickListElementRender.Run> visitListItemRuns(JQuickPDFParser.ListItemContext ctx,
                                                                JStyleAttributes itemStyle) {
        List<JQuickListElementRender.Run> runs = new ArrayList<>();
        JQuickPDFParser.ElemValueContext elemValue = ctx.elemValue();
        if (null == elemValue) {
            return runs;
        }
        if (null != elemValue.value()) {
            addRun(runs, visitValue(elemValue.value()), itemStyle);
        } else if (null != elemValue.element()) {
            for (JQuickPDFParser.ElementContext elementContext : elemValue.element()) {
                Object object = visitElement(elementContext);
                if (object instanceof JQuickTextElementRender) {
                    JQuickTextElementRender text = (JQuickTextElementRender) object;
                    addRun(runs, text.getContent(), text.getStyle() == null ? itemStyle : text.getStyle());
                } else {
                    addRun(runs, object, itemStyle);
                }
            }
        }
        return runs;
    }

    private void addRun(List<JQuickListElementRender.Run> runs, Object value, JStyleAttributes style) {
        if (null == value) {
            return;
        }
        String text = JStringUtils.trim(value.toString());
        if (null != text && !text.isEmpty()) {
            runs.add(new JQuickListElementRender.Run(text, style));
        }
    }

    @Override
    public String visitListItem(JQuickPDFParser.ListItemContext ctx) {
        java.util.List<Object> subelem = null;
        if (ctx.elemValue() != null) {
            subelem = visitElemValue(ctx.elemValue());
        }
        return mergeText(subelem);
    }

    @Override
    public String visitListType(JQuickPDFParser.ListTypeContext ctx) {
        if (ctx == null) {
            return null;
        }
        return ctx.getText();
    }

    private boolean isOrderedList(JQuickPDFParser.ListContext ctx) {
        String source = ctx == null ? "" : ctx.getText().toLowerCase();
        return source.contains("ordered") || source.contains("<ol")
                || source.contains("number") || source.contains("listtype=ol");
    }

    private String mergeText(List<Object> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Object e : list) {
            if (e instanceof String) {
                builder.append(e);
            }
        }
        return trim(builder.toString());
    }
}
