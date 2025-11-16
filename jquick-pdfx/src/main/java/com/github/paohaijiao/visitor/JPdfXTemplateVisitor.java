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

import com.github.paohaijiao.config.JTemplateConfig;
import com.github.paohaijiao.exception.JAssert;
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
 * @className JPdfXDivVisitor
 * @date 2025/6/15
 * @description
 */
public class JPdfXTemplateVisitor extends JPdfXTextAreaVisitor {

    @Override
    public JHtmlRenderModel visitTemplate(JQuickPDFParser.TemplateContext ctx) {
        String value = "";
        if (ctx.IDENTIFIER() != null) {
            value = ctx.IDENTIFIER().getText();
        }
        JTemplateConfig templateConfig = this.config.getTemplateConfig();
        JAssert.notNull(templateConfig, "template not found");
        String html = templateConfig.get(value);
        List<IElement> iElements = HtmlConverter.convertToElements(html, proper);
        JHtmlRenderModel jHtmlRenderModel = new JHtmlRenderModel();
        jHtmlRenderModel.setList(iElements);
        return jHtmlRenderModel;
    }


}
