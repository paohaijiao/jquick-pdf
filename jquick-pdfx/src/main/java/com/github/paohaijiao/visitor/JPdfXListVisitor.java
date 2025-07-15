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

import com.github.paohaijiao.enums.JListType;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.model.list.JListItemModel;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;


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
    public Div visitList(JQuickPDFParser.ListContext ctx) {
        JListType listType = JListType.ol;
        if (null != ctx.listEle() && !ctx.listEle().isEmpty()) {
            listType = visitListEle(ctx.listEle().get(0));
        }
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        if (JListType.ol.getCode().equals(listType.getCode())) {
            Div ol = new Div();
            for (int i = 0; i < ctx.listItem().size(); i++) {
                JQuickPDFParser.ListItemContext listItemContext = ctx.listItem(i);
                JListItemModel item = visitListItem(listItemContext);
                String text = String.format("%s.%s", i + 1, item.getText());
                Paragraph paragraph = new Paragraph(text);
              //  super.buildStyle(paragraph, item.getStyle());
                ol.add(paragraph);
            }
            super.buildStyle(ol, style);
            return ol;
        } else {
            Div ul = new Div();
            for (int i = 0; i < ctx.listItem().size(); i++) {
                JQuickPDFParser.ListItemContext listItemContext = ctx.listItem(i);
                JListItemModel item = visitListItem(listItemContext);
                String text = String.format("• %s", item.getText());
                Paragraph paragraph = new Paragraph(text);
             //   super.buildStyle(paragraph, item.getStyle());
                ul.add(paragraph);
            }
      //      super.buildStyle(ul, style);
            return ul;
        }
    }

    @Override
    public JListType visitListEle(JQuickPDFParser.ListEleContext ctx) {
        String type = ctx.getText();
        return JListType.codeOf(type);
    }

    @Override
    public JListItemModel visitListItem(JQuickPDFParser.ListItemContext ctx) {
        JListItemModel item = new JListItemModel();
        JStyleAttributes style = new JStyleAttributes();
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        String value = "";
        if (ctx.value() != null) {
            value = (String) visitValue(ctx.value());
        }
        item.setText(value);
        item.setStyle(style);
        return item;
    }


}
