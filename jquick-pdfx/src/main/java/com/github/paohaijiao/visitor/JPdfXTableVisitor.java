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
import com.github.paohaijiao.model.table.JColumnModel;
import com.github.paohaijiao.model.table.JRowModel;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
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

    private static final DeviceRgb HEADER_COLOR = new DeviceRgb(66, 133, 244); // 蓝色表头
    private static final DeviceRgb HEADER_TEXT_COLOR = new DeviceRgb(255, 255, 255); // 白色文字
    private static final DeviceRgb EVEN_ROW_COLOR = new DeviceRgb(245, 245, 245); // 浅灰色偶数行
    private static final DeviceRgb TEXT_COLOR = new DeviceRgb(51, 51, 51); // 深灰色文字
    private static final DeviceRgb BORDER_COLOR = new DeviceRgb(221, 221, 221);
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
            JQuickPDFParser.RowContext tempRowContext = ctx.row(0);
            JRowModel tempItem = visitRow(tempRowContext);
            Table table = new Table(tempItem.getColumnList().size())
                    .setHorizontalAlignment(HorizontalAlignment.CENTER)
                    .setMarginBottom(30);
            table.setFont(JFontProviderFactory.defualtFont());
            Border tableBorder = new SolidBorder(BORDER_COLOR, 1);
            for (int i = 0; i < ctx.row().size(); i++) {
                JQuickPDFParser.RowContext rowContext = ctx.row(i);
                JRowModel item = visitRow(rowContext);
                for (JColumnModel column : item.getColumnList()) {
                    List<Object> value = column.getObject();
                    if(th.equals(column.getType())){
                        Cell headerCell = new Cell();
                        saveSub(headerCell, value);
                        headerCell.setBackgroundColor(HEADER_COLOR);
                        headerCell.setFontColor(HEADER_TEXT_COLOR);
                        headerCell.setTextAlignment(TextAlignment.CENTER);
                        headerCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
                        headerCell.setWidth(250);
                        headerCell.setBorder(tableBorder);
                        headerCell.setPadding(8);
                        headerCell.setFont(JFontProviderFactory.defualtFont());
                        super.buildStyle(headerCell, column.getStyle());
                        table.addHeaderCell(headerCell);
                    }
                    if(td.equals(column.getType())){
                        DeviceRgb rowColor = (i % 2 == 0) ? EVEN_ROW_COLOR : null;
                        Cell cell = new Cell();
                        saveSub(cell, value);
                        cell.setFontColor(TEXT_COLOR);
                        cell.setBorder(tableBorder);
                        cell.setPadding(8);
                        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
                        if (rowColor != null) {
                            cell.setBackgroundColor(rowColor);
                        }
                        cell.setFont(JFontProviderFactory.defualtFont());
                        super.buildStyle(cell, column.getStyle());
                        table.addCell(cell);
                    }

                }
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
        List<Object> value = null;
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
        List<Object> value = null;
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
                if (e instanceof Paragraph) {
                    cell.add((Paragraph) e);
                }
                if (e instanceof String) {
                    Paragraph paragraph = new Paragraph((String)e);
                    paragraph.setFont(JFontProviderFactory.defualtFont());
                    cell.add(paragraph);
                }
            });
        }
    }


}
