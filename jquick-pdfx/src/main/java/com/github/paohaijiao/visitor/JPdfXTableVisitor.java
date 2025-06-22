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

import com.github.paohaijiao.model.paragraph.JParagraphModel;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;


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

    @Override
    public Table visitTable(JQuickPDFParser.TableContext ctx) {
        Document document = new Document(pdf);
        Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
        for (int i = 1; i <= 5; i++) {
            table.addHeaderCell(new Cell().add(new Paragraph("Column " + i).setBold()));
        }
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 5; j++) {
                table.addCell(new Cell().add(new Paragraph("Row " + i + ", Col " + j)));
            }
        }
        document.add(table);
        document.close();
        return null;
    }



    @Override
    public Void visitTableCell(JQuickPDFParser.TableCellContext ctx) {
        if (ctx.colspan() != null) {
            visitColspan(ctx.colspan());
        }
        if (ctx.rowspan() != null) {
            visitRowspan(ctx.rowspan());
        }
        for (JQuickPDFParser.ElementContext i : ctx.element()) {
            visitElement(i);
        }
        return null;
    }

    private Paragraph buildParagraph(JParagraphModel data) {
        try {
            Table table = new Table(new float[]{1, 2, 1}); // 列宽比例
            // table.setWidthPercent(100); // 宽度百分比
            //  table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.setMarginTop(10);
            table.setMarginBottom(10);
            Cell cell = new Cell().add(new Paragraph("Header"))
                    // .setBackgroundColor(Color.LIGHT_GRAY)
                    // .setFontColor(Color.WHITE)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold();

            //table.setBorder(new SolidBorder(Color.BLACK, 1));
            boolean alternate = false;
//            for (Cell tableCell : table.getCells()) {
//                if (alternate) {
//                    tableCell.setBackgroundColor(new DeviceRgb(240, 240, 240));
//                }
//                alternate = !alternate;
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
