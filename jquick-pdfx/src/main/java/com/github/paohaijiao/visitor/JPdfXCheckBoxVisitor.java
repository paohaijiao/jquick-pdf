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

import com.github.paohaijiao.model.JHtmlRenderModel;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.element.IElement;

import java.util.List;


/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXCheckBoxVisitor extends JPdfXButtonVisitor {

    @Override
    public JHtmlRenderModel visitCheckbox(JQuickPDFParser.CheckboxContext ctx) {
        String text=ctx.getText();
        List<IElement> iElements= HtmlConverter.convertToElements( text,proper);
        JHtmlRenderModel jHtmlRenderModel=new JHtmlRenderModel();
        jHtmlRenderModel.setList(iElements);
        return jHtmlRenderModel;
    }


}
