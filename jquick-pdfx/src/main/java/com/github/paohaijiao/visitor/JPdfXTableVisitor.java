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
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

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

    private static final String th = "th";
    private static final String td = "td";

    @Override
    public Table visitTable(JQuickPDFParser.TableContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        if(null!=ctx.row()&&!ctx.row().isEmpty()){
            int colSize = ctx.row(0).col().size();
            Table table = new Table(UnitValue.createPercentArray(colSize)).useAllAvailableWidth();
            for (int i = 0; i < ctx.row().size(); i++) {
                JQuickPDFParser.RowContext rowContext = ctx.row(i);
                JRowModel item = visitRow(rowContext);
                for (JColumnModel column : item.getColumnList()) {
                    String text="";
                    if(null!=column.getObject()&&column.getObject() instanceof String){
                        text=column.getObject().toString();
                    }
                    Paragraph paragraph = new Paragraph(text);
                    buildParagraphStyle(paragraph);
                    super.buildStyle(paragraph, column.getStyle());
                    Cell cell = new Cell().add(paragraph);
                    saveSub(cell,column.getObject());
                    buildStyle(cell);
                    super.buildStyle(cell, column.getStyle());
                    table.addCell(cell);
                }
                table.startNewRow();
            }
            super.buildStyle(table, style);
            return table;
        }
        return null;

    }

    @Override
    public JRowModel visitRow(JQuickPDFParser.RowContext ctx) {
        JRowModel jRowModel = new JRowModel();
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
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
        } else {
            return visitTh(ctx.th());
        }
    }

    @Override
    public JColumnModel visitTh(JQuickPDFParser.ThContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        Object value =null;
        if (null != ctx.elemValue()) {
            value =visitElemValue(ctx.elemValue());
        }
        JColumnModel model = new JColumnModel();
        model.setStyle(style);
        model.setObject(value);
        model.setType(th);
        return model;
    }

    @Override
    public JColumnModel visitTd(JQuickPDFParser.TdContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        Object value =null;
        if (null != ctx.elemValue()) {
            value =visitElemValue(ctx.elemValue());
        }
        JColumnModel model = new JColumnModel();
        model.setStyle(style);
        model.setObject(value);
        model.setType(td);
        return model;
    }

    private Style buildStyle(Cell cell){
        Style deFaultStyle = new Style();
        deFaultStyle.setPadding(10);
        deFaultStyle.setBorder(Border.NO_BORDER);
        deFaultStyle.setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.addStyle(deFaultStyle);
        return deFaultStyle;
    }
    private Style buildParagraphStyle(Paragraph paragraph){
        Style deFaultStyle = new Style();
        deFaultStyle.setFontSize(11);
        paragraph.addStyle(deFaultStyle);
        return deFaultStyle;
    }
    private void saveSub(Cell cell,Object object) {
        if(null!=object&&object instanceof java.util.List) {
            java.util.List<Object> list=(java.util.List<Object>) object;
            list.forEach(e -> {
                if (e instanceof IBlockElement) {
                    cell.add((IBlockElement) e);
                }
                if (e instanceof Image) {
                    cell.add((Image) e);
                }
            });
        }
    }


}
