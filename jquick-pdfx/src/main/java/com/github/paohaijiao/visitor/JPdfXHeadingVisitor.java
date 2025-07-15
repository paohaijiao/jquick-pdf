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
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.layout.Document;
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
public class JPdfXHeadingVisitor extends JPdfXParagraphVisitor {

    @Override
    public Paragraph visitHeading(JQuickPDFParser.HeadingContext ctx) {
        Integer number = 1;//default h1
        if (ctx.number() != null && !ctx.number().isEmpty()) {
            String numberTxt = ctx.number().get(0).getText();
            number = Integer.parseInt(numberTxt.toString());
        }
        String value = "";
        if (null != ctx.value()) {
            value = (String) visitValue(ctx.value());
        }
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        Integer fontSize = getFontSize("" + number);
        style.put(JStyleAttributes.FONT_SIZE, fontSize + "");
        Paragraph h1 = new Paragraph(value);
        super.buildStyle(h1, style);
//        doc.add(h1);
        return h1;
    }

    private Integer getFontSize(String level) {
        Integer fontSize = 24;
        switch (level) {
            case "h1":
                fontSize = 24;
                break;
            case "h2":
                fontSize = 22;
                break;
            case "h3":
                fontSize = 20;
                break;
            case "h4":
                fontSize = 18;
                break;
            case "h5":
                fontSize = 16;
                break;
            case "h6":
                fontSize = 14;
                break;
        }
        return fontSize;
    }


}
