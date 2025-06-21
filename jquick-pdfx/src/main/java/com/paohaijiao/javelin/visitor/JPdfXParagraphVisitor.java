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
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.paohaijiao.javelin.model.paragraph.JParagraphModel;
import com.paohaijiao.javelin.model.style.JStyleDataModel;
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
public class JPdfXParagraphVisitor extends JPdfXLayOutVisitor {
    @Override
    public Paragraph visitParagraph(JQuickPDFParser.ParagraphContext ctx) {
        this.cleanTemp();
        String text = ctx.string().getText().replaceAll("^\"|\"$", "");
        if(null!=ctx.paragraphStyle()){
            visitParagraphStyle(ctx.paragraphStyle());
        }
        JParagraphModel model=new JParagraphModel();
        model.setText(text);
        model.setStyle(style);
        model.setStyle(style);
//        model.setAlign(align);
//        model.setSpacing(spacingModel);
        Paragraph paragraph=this.buildParagraph(model);
        return paragraph;
    }
    @Override
    public JStyleDataModel visitParagraphStyle(JQuickPDFParser.ParagraphStyleContext ctx) {
        for(JQuickPDFParser.ParagraphStyleTypeContext context:ctx.paragraphStyleType()){
            if(null!=context.textStyle() ){
                visit(context.textStyle());
            }
            if(null!=context.alignment() ){
                visit(context.alignment());
            }
            if(null!=context.spacing() ){
                visit(context.spacing());
            }
        }
        return  null;
    }


    private Paragraph buildParagraph(JParagraphModel data)  {
        try{
            Paragraph paragraph = new Paragraph(data.getText());
            JStyleModel model=data.getStyle();
            if(null!=model){
                if(null!=model.getFont()){
                    paragraph.setFont(PdfFontFactory.createFont(model.getFont())); // 字体
                }
                if(null!=model.getSize()){
                    paragraph.setFontSize(model.getSize()); // 字号
                }
                if(null!=model.getColor()){
                    //paragraph.setFontColor(Color.); // 颜色
                }
                if(null!=model.getBold()&&model.getBold()){
                    paragraph.setBold(); // 加粗
                }
                if(null!=model.getItalic()&&model.getItalic()){
                    paragraph.setItalic(); // 加粗
                }
                if(null!=model.getUnderline()&&model.getUnderline()){
                    paragraph.setUnderline(); // 加粗
                }
                if(null!=model.getUnderline()&&model.getUnderline()){
                    paragraph.setUnderline();
                }
            }

            paragraph.setFixedLeading(20); // 行高
            paragraph.setFirstLineIndent(20); // 首行缩进
            paragraph.setMarginLeft(10); // 左边距
            paragraph.setMarginRight(10); // 右边距
            paragraph.setTextAlignment(TextAlignment.CENTER); // 对齐方式
            return paragraph;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }




}
