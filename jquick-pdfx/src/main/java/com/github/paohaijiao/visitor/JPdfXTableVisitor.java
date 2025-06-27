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
import com.github.paohaijiao.model.list.JListItemModel;
import com.github.paohaijiao.model.paragraph.JParagraphModel;
import com.github.paohaijiao.model.table.JColumnModel;
import com.github.paohaijiao.model.table.JRowModel;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.kernel.pdf.PdfName.BaseFont;


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
    public Void visitTable(JQuickPDFParser.TableContext ctx) {
        Document document = new Document(pdf);
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        for (int i = 0; i < ctx.row().size(); i++) {
            JQuickPDFParser.RowContext rowContext = ctx.row(i);
            JRowModel item = visitRow(rowContext);
            for (JColumnModel column : item.getColumnList()) {
                if (column.getType().equals(th)) {
                    Paragraph paragraph = new Paragraph(column.getText());
                    //super.buildStyle(paragraph, column.getStyle());
                    Cell cell = new Cell().add(paragraph);
                    super.buildStyle(cell, column.getStyle());
                    table.addHeaderCell(cell);
                } else {
                    Paragraph paragraph = new Paragraph(column.getText());
                    //super.buildStyle(paragraph, column.getStyle());
                    Cell cell = new Cell().add(paragraph);
                    super.buildStyle(cell, column.getStyle());
                    table.addCell(cell);
                }

            }
        }
        super.buildStyle(table, style);
        document.add(table);
        document.close();
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
        String text = "";
        if (null != ctx.value()) {
            text = (String) visitValue(ctx.value());
        }
        JColumnModel model = new JColumnModel();
        model.setStyle(style);
        model.setText(text);
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
        String text = "";
        String st=ctx.value().getText();
        if (null != ctx.value()) {
            text =  visitValue(ctx.value()).toString();
        }
        JColumnModel model = new JColumnModel();
        model.setStyle(style);
        model.setText(text);
        model.setType(th);
        return model;
    }


}
