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
import com.github.paohaijiao.visitor.element.JQuickListElementRender;

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
        List<String> items = new ArrayList<>();
        if (ctx.listItem() != null && !ctx.listItem().isEmpty()) {
            for (JQuickPDFParser.ListItemContext listItemContext : ctx.listItem()) {
                String item = visitListItem(listItemContext);
                if (item != null && !item.isEmpty()) {
                    items.add(item);
                }
            }
        }
        boolean ordered = isOrderedList(ctx);
        JQuickListElementRender list = new JQuickListElementRender(ordered, items, style);
        super.buildStyle(list, style);
        return list;
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
