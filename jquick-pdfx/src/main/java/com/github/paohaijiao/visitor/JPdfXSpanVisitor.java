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
import com.github.paohaijiao.model.paragraph.JParagraphModel;
import com.github.paohaijiao.model.style.JStyleModel;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.sample.ReportColor;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
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
public class JPdfXSpanVisitor extends JPdfXLayOutVisitor {

    @Override
    public Text visitSpan(JQuickPDFParser.SpanContext ctx) {
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
        if(ctx.lbr()!=null){
            value="\n"+value;
        }
        if(ctx.rbr()!=null){
            value=value+"\n";
        }
        Text text=new Text(value);
        text.setFontSize(22);
        text.setFontColor(ReportColor.getThemeColor());
        //super.buildStyle(text, style);
        return text;
    }



}
