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
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.util.JStringUtils;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;

import java.util.Arrays;


/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXHeadingVisitor extends JPdfXParagraphVisitor {

    @Override
    public Paragraph visitHeading(JQuickPDFParser.HeadingContext ctx) {
        Integer number = 1;//default h1
        if (ctx.number() != null && !ctx.number().isEmpty()) {
            String numberTxt = ctx.number().get(0).getText();
            number = Integer.parseInt(numberTxt.toString());
        }
        Object value=null;
        String text="";
        if (null != ctx.elemValue()) {
            value = visitElemValue(ctx.elemValue());
          if (null != value && value instanceof String) {
              text = (String) value;
          }
        }
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        Paragraph paragraph = new Paragraph(JStringUtils.trim(text));
        paragraph.setFont(JFontProviderFactory.defualtFont());
        createHeading(paragraph,number);
        saveSub(paragraph,value);
        super.buildStyle(paragraph, style);
        return paragraph;
    }
    private void saveSub(Paragraph paragraph,Object object) {
        if(null!=object&&object instanceof java.util.List) {
            java.util.List<Object> list=(java.util.List<Object>) object;
            list.forEach(e -> {
                if (e instanceof String) {
                    paragraph.add((String) e);
                }
                if (e instanceof ILeafElement) {
                    paragraph.add((ILeafElement) e);
                }
                if (e instanceof IBlockElement) {
                    paragraph.add((IBlockElement) e);
                }
                if (e instanceof TabStop) {
                    TabStop tabStop = (TabStop) e;
                    paragraph.addTabStops(Arrays.asList(tabStop));
                }
            });
        }
    }
    private static Paragraph createHeading(Paragraph heading, int level)  {
        heading.setFont(JFontProviderFactory.defualtFont());
        switch (level) {
            case 1: // H1
                heading.setFontSize(24);
                heading.setBold();
                heading.setFontColor(ColorConstants.DARK_GRAY);
                heading.setTextAlignment(TextAlignment.LEFT);
                heading.setMarginTop(30);
                heading.setMarginBottom(10);
                break;
            case 2: // H2
                heading.setFontSize(20);
                heading.setBold();
                heading.setFontColor(ColorConstants.DARK_GRAY);
                heading.setTextAlignment(TextAlignment.LEFT);
                heading.setMarginTop(25);
                heading.setMarginBottom(8);
                break;
            case 3: // H3
                heading.setFontSize(16);
                heading.setBold();
                heading.setFontColor(ColorConstants.BLACK);
                heading.setTextAlignment(TextAlignment.LEFT);
                heading.setMarginTop(20);
                heading.setMarginBottom(6);
                break;
            case 4: // H4
                heading.setFontSize(14);
                heading.setBold();
                heading.setFontColor(ColorConstants.BLACK);
                heading.setTextAlignment(TextAlignment.LEFT);
                heading.setMarginTop(15);
                heading.setMarginBottom(5);
                break;
            case 5: // H5
                heading.setFontSize(12);
                heading.setBold();
                heading.setFontColor(ColorConstants.BLACK);
                heading.setTextAlignment(TextAlignment.LEFT);
                heading.setMarginTop(10);
                heading.setMarginBottom(4);
                break;
            case 6: // H6
                heading.setFontSize(10);
                heading.setBold();
                heading.setFontColor(ColorConstants.GRAY);
                heading.setTextAlignment(TextAlignment.LEFT);
                heading.setMarginTop(8);
                heading.setMarginBottom(3);
                break;
            default:
                heading.setFontSize(14);
                heading.setBold();
                heading.setFontColor(ColorConstants.BLACK);
                heading.setTextAlignment(TextAlignment.LEFT);
                heading.setMarginTop(15);
                heading.setMarginBottom(5);
        }
        return heading;
    }


}
