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
package com.paohaijiao.javelin.visitor;

import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.paohaijiao.javelin.model.list.JListModel;
import com.paohaijiao.javelin.parser.JQuickPDFParser;

import java.util.ArrayList;


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
    public String visitOrderType(JQuickPDFParser.OrderTypeContext ctx) {
        return ctx.getText();
    }


    @Override
    public List visitList(JQuickPDFParser.ListContext ctx) {
        if(ctx.orderType() != null) {
            visitOrderType(ctx.orderType());
        }
        java.util.List<String> list=new ArrayList<>();
        for (JQuickPDFParser.ListItemContext item:ctx.listItem()){
            String items=visitListItem(item);
            list.add(items);
        }
        JListModel jListModel=new JListModel();
        jListModel.setList(list);
        List jList=buildHeading(jListModel);
        return jList;
    }
    @Override
    public String visitListItem(JQuickPDFParser.ListItemContext ctx) {
        return visitString(ctx.string());
    }
    private List buildHeading(JListModel data)  {
        try{
            List list = new List()
            .setSymbolIndent(data.getSymbolIndent()) // 列表符号缩进
            .setListSymbol(data.getSymbol()) // 无序列表符号
                    //.setListSymbol(ListNumberingType.DECIMAL) // 有序列表
                    //.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA))
            .setFontSize(data.getFontSize());
            if(null!=data.getList()&&data.getList().size()>0){
                data.getList().forEach(item->{
                    list.add(new ListItem(item));
                });

            }
//            for (IElement item : list.getChildren()) {
//                if (item instanceof ListItem) {
//                    ((ListItem) item).setFontColor(Color.DARK_GRAY);
//                }
//            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }



}
