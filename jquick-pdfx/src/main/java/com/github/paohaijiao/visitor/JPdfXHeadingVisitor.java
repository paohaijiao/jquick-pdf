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

import com.github.paohaijiao.model.heading.JHeadingModel;
import com.github.paohaijiao.model.style.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.paohaijiao.javelin.util.JStringUtils;


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
    public Void visitHeading(JQuickPDFParser.HeadingContext ctx) {

        JHeadingModel headingModel = new JHeadingModel();
        String level = ctx.getChild(1).getText();
        headingModel.setLevel(level);
        if(null!=ctx.string()){
            String text = JStringUtils.trim(ctx.string().getText());
            headingModel.setText(text);
        }
        if(null!=ctx.style()){
            JStyleAttributes style = visitStyle(ctx.style());
            headingModel.setStyle(style);
        }
//        Paragraph paragraph = this.buildHeading(headingModel);
//        document.add(paragraph);
        Document document = new Document(this.pdf);
        document.add(new Paragraph(headingModel.getText()));
        document.close();
        return null;
    }

    private Paragraph buildHeading(JHeadingModel data) {
        try {
            Paragraph heading = new Paragraph(data.getText());
//            switch (data.getLevel()) {
//                case "h1":
//                    heading.setFontSize(24);
//                    break;
//                case "h2":
//                    heading.setFontSize(22);
//                    break;
//                case "h3":
//                    heading.setFontSize(20);
//                    break;
//                case "h4":
//                    heading.setFontSize(18);
//                    break;
//                case "h5":
//                    heading.setFontSize(16);
//                    break;
//                case "h6":
//                    heading.setFontSize(14);
//                    break;
//            }
//            JStyleAttributes model = data.getStyle();
//            if (null != model) {
//                if (null != model.getFont()) {
//                    heading.setFont(PdfFontFactory.createFont(model.getFont())); // 字体
//                }
//                if (null != model.getFontSize()) {
//                    heading.setFontSize(model.getFontSize()); // 字号
//                }
//            }

//            heading.setFixedLeading(20); // 行高
//            heading.setFirstLineIndent(20); // 首行缩进
//            heading.setMarginLeft(10); // 左边距
//            heading.setMarginRight(10); // 右边距
//            heading.setTextAlignment(TextAlignment.CENTER); // 对齐方式
//            Style h1Style = new Style();
//            h1Style.setFontSize(24);
//            //  .setFontColor(Color.RED);
//            heading.addStyle(h1Style);
            return heading;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
