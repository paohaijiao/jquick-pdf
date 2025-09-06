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
import com.github.paohaijiao.factory.JFontProviderFactory;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.model.JStyleListAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.sample.ReportColor;
import com.github.paohaijiao.util.JStringUtils;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.Leading;
import com.itextpdf.layout.properties.ListNumberingType;
import com.itextpdf.layout.properties.Property;
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
        Object value = null;
        if (ctx.elemValue() != null) {
            value = (String) visitElemValue(ctx.elemValue());
        }
        String text = "";
        if(null!=value&&value instanceof String){
            text= JStringUtils.trim(value.toString());
        }
        ListItem item = new ListItem(text);
        saveSub(item,value);
        buildDefaultListItemStyle(item);
        super.buildStyle(item, style);
        return item;
    }
    private void buildDefaultListItemStyle( ListItem listItem){
        listItem.setFontColor(ReportColor.getThemeColor());
        listItem.setFontSize(11);
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
