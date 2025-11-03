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
import com.github.paohaijiao.model.JStyleListAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.sample.ReportColor;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.ListNumberingType;
import com.itextpdf.layout.properties.TextAlignment;


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
    private static final Color PRIMARY_COLOR = new DeviceRgb(52, 152, 219);
    private static final Color SUCCESS_COLOR = new DeviceRgb(46, 204, 113);
    private static final Color WARNING_COLOR = new DeviceRgb(241, 196, 15);
    private static final Color DANGER_COLOR = new DeviceRgb(231, 76, 60);
    private static final Color LIGHT_BG = new DeviceRgb(248, 249, 250);
    private static final Color BORDER_COLOR = new DeviceRgb(234, 234, 234);

    @Override
    public List visitList(JQuickPDFParser.ListContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        List list = new List(ListNumberingType.ROMAN_UPPER);
        list.setFont(JFontProviderFactory.defualtFont());
        list.setMarginLeft(20);
        list.setSymbolIndent(15);
        list.setTextAlignment(TextAlignment.JUSTIFIED);
        super.buildStyle(list, style);
        if(null!=ctx.listItem()&&!ctx.listItem().isEmpty()){
            for (JQuickPDFParser.ListItemContext listItemContext:ctx.listItem()){
                ListItem item=visitListItem(listItemContext);
                list.add(item);
            }
        }
//        super.buildStyle(list, style);
//        JStyleListAttributes newStyle=new JStyleListAttributes();
//        newStyle.putAll(style);
//        this.buildExtraStyle(list,newStyle);
        return list;
    }
    private void buildDefaultListStyle( List list){
        list.setFont(JFontProviderFactory.defualtFont());
        list.setMarginLeft(20);
        list.setSymbolIndent(15);
        list.setTextAlignment(TextAlignment.JUSTIFIED);
    }
    private void buildExtraStyle(List list, JStyleListAttributes style) {
        if(style.getImage()!=null){
            list.setListSymbol(style.getImage());
        }
    }


    @Override
    public ListItem visitListItem(JQuickPDFParser.ListItemContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        java.util.List<Object> subelem = null;
        System.out.println(ctx.elemValue().getText());
        if (ctx.elemValue() != null) {
            subelem =  visitElemValue(ctx.elemValue());
        }
        ListItem item = new ListItem();
        saveSub(item,subelem);
        buildDefaultListItemStyle(item);
        super.buildStyle(item, style);
        return item;
    }
    private void buildDefaultListItemStyle( ListItem listItem){
        listItem.setFontColor(ReportColor.getThemeColor());
        listItem.setFontSize(11);
    }
    private void saveSub(ListItem listItem,java.util.List<Object> list) {
            list.forEach(e -> {
                if (e instanceof IBlockElement) {
                    listItem.add((IBlockElement) e);
                }
                if (e instanceof String) {
                    Paragraph paragraph = new Paragraph((String) e);
                    paragraph.setFont(JFontProviderFactory.defualtFont());
                    listItem.add(paragraph);
                }

            });
    }

}
