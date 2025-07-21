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
import com.itextpdf.html2pdf.attach.impl.layout.form.element.TextArea;
import com.itextpdf.layout.element.Paragraph;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXDivVisitor
 * @date 2025/6/15
 * @description
 */
public class JPdfXTextAreaVisitor extends JPdfXTabVisitor {

    @Override
    public TextArea visitTextArea(JQuickPDFParser.TextAreaContext ctx) {
    JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        String value = "";
        if (null != ctx.value()) {
            value =ctx.value().getText();
        }
        TextArea textArea = new TextArea(JStringUtils.trim(value));
        Paragraph paragraph = new Paragraph();
        paragraph.add(JStringUtils.trim(value));
        paragraph.setFont(JFontProviderFactory.defualtFont());
        textArea.setPlaceholder(paragraph);
        textArea.setFont(JFontProviderFactory.defualtFont());
        super.buildStyle(textArea, style);
        return textArea;
   }


}
