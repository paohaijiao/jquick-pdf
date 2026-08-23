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
import com.github.paohaijiao.model.table.JColumnModel;
import com.github.paohaijiao.model.table.JRowModel;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.element.JQuickTableElementRender;

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
public class JPdfXTableVisitor extends JPdfXHeadingVisitor {

    private static final String TH = "th";

    private static final String TD = "td";

    @Override
    public JQuickTableElementRender visitTable(JQuickPDFParser.TableContext ctx) {
        JStyleAttributes style;
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        List<JRowModel> rows = new ArrayList<>();
        if (ctx.row() != null && !ctx.row().isEmpty()) {
            for (JQuickPDFParser.RowContext rowContext : ctx.row()) {
                rows.add(visitRow(rowContext));
            }
        }
        JQuickTableElementRender table = new JQuickTableElementRender(rows, style);
        super.buildStyle(table, style);
        return table;
    }

    @Override
    public JRowModel visitRow(JQuickPDFParser.RowContext ctx) {
        JRowModel jRowModel = new JRowModel();
        JStyleAttributes style;
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        jRowModel.setStyle(style);
        List<JColumnModel> columnList = new ArrayList<>();
        for (int i = 0; i < ctx.col().size(); i++) {
            JQuickPDFParser.ColContext colContext = ctx.col(i);
            JColumnModel item = visitCol(colContext);
            columnList.add(item);
        }
        jRowModel.setColumnList(columnList);
        return jRowModel;
    }

    @Override
    public JColumnModel visitCol(JQuickPDFParser.ColContext ctx) {
        if (ctx.td() != null) {
            return visitTd(ctx.td());
        }
        return visitTh(ctx.th());
    }

    @Override
    public JColumnModel visitTh(JQuickPDFParser.ThContext ctx) {
        JStyleAttributes style;
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        List<Object> value = null;
        if (ctx.elemValue() != null) {
            value = visitElemValue(ctx.elemValue());
        }
        JColumnModel model = new JColumnModel();
        model.setStyle(style);
        model.setObject(value);
        model.setType(TH);
        return model;
    }

    @Override
    public JColumnModel visitTd(JQuickPDFParser.TdContext ctx) {
        JStyleAttributes style;
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        List<Object> value = null;
        if (ctx.elemValue() != null) {
            value = visitElemValue(ctx.elemValue());
        }
        JColumnModel model = new JColumnModel();
        model.setStyle(style);
        model.setObject(value);
        model.setType(TD);
        return model;
    }
}
