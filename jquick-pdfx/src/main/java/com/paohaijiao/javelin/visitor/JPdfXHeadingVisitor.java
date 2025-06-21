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

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.paohaijiao.javelin.model.heading.JHeadingModel;
import com.paohaijiao.javelin.model.style.JStyleModel;
import com.paohaijiao.javelin.parser.JQuickPDFParser;


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
    public Paragraph  visitHeading(JQuickPDFParser.HeadingContext ctx) {
        this.cleanTemp();
        JHeadingModel headingModel=new JHeadingModel();
        String level = ctx.getChild(1).getText();
        headingModel.setLevel(level);
        String text = ctx.string().getText().replaceAll("\"", "");
        headingModel.setLevel(text);
        for(JQuickPDFParser.TextStyleContext context:ctx.textStyle()){
            visit(context);
        }
        headingModel.setStyle(style);
        Paragraph paragraph=this.buildHeading(headingModel);
        return paragraph;
    }
    private Paragraph buildHeading(JHeadingModel data)  {
        try{
            Paragraph heading = new Paragraph(data.getText());
            switch (data.getLevel()) {
                case "h1": heading.setFontSize(24); break;
                case "h2": heading.setFontSize(22); break;
                case "h3": heading.setFontSize(20); break;
                case "h4": heading.setFontSize(18); break;
                case "h5": heading.setFontSize(16); break;
                case "h6": heading.setFontSize(14); break;
            }
            JStyleModel model=data.getStyle();
            if(null!=model){
                if(null!=model.getFont()){
                    heading.setFont(PdfFontFactory.createFont(model.getFont())); // 字体
                }
                if(null!=model.getSize()){
                    heading.setFontSize(model.getSize()); // 字号
                }
                if(null!=model.getColor()){
                    //paragraph.setFontColor(Color.); // 颜色
                }
                if(null!=model.getBold()&&model.getBold()){
                    heading.setBold(); // 加粗
                }
                if(null!=model.getItalic()&&model.getItalic()){
                    heading.setItalic(); // 加粗
                }
                if(null!=model.getUnderline()&&model.getUnderline()){
                    heading.setUnderline(); // 加粗
                }
                if(null!=model.getUnderline()&&model.getUnderline()){
                    heading.setUnderline();
                }
            }

            heading.setFixedLeading(20); // 行高
            heading.setFirstLineIndent(20); // 首行缩进
            heading.setMarginLeft(10); // 左边距
            heading.setMarginRight(10); // 右边距
            heading.setTextAlignment(TextAlignment.CENTER); // 对齐方式
            Style h1Style = new Style()
                    //.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD))
                    .setFontSize(24);
                  //  .setFontColor(Color.RED);

            heading.addStyle(h1Style);
            return heading;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }




}
