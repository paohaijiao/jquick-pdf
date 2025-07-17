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
import com.itextpdf.layout.element.*;

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
    public List visitList(JQuickPDFParser.ListContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        List list=new List();
        if(null!=ctx.listItem()&&!ctx.listItem().isEmpty()){
            for (JQuickPDFParser.ListItemContext listItemContext:ctx.listItem()){
                ListItem item=visitListItem(listItemContext);
                list.add(item);
            }
        }
        super.buildStyle(list, style);
        return list;
    }

    @Override
    public JListType visitListEle(JQuickPDFParser.ListEleContext ctx) {
        String type = ctx.getText();
        return JListType.codeOf(type);
    }

    @Override
    public ListItem visitListItem(JQuickPDFParser.ListItemContext ctx) {
        ListItem item = new ListItem();
        JStyleAttributes style = new JStyleAttributes();
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        Object value = null;
        if (ctx.elemValue() != null) {
            value = (String) visitElemValue(ctx.elemValue());
        }
        String text = "";
        if(null!=value&&value instanceof String){
            text=(String)value;
        }
        item.setListSymbol(new Text(text));
        saveSub(item,value);
        super.buildStyle(item, style);
        return item;
    }
    private void saveSub(ListItem listItem,Object object) {
        if(null!=object&&object instanceof java.util.List) {
            java.util.List<Object> list=(java.util.List<Object>) object;
            list.forEach(e -> {
                if (e instanceof IBlockElement) {
                    listItem.add((IBlockElement) e);
                }
            });
        }
    }

}
