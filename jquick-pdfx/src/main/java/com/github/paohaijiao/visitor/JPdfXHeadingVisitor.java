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
        Paragraph h1 = new Paragraph(text);
        saveSub(h1,value);
        super.buildStyle(h1, style);
        return h1;
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
            });
        }
    }


}
