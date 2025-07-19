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

import com.github.paohaijiao.enums.JHtmlPageBreakTypeEnums;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreak;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreakType;
import com.itextpdf.layout.element.*;
import org.apache.commons.lang3.StringUtils;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXDivVisitor
 * @date 2025/6/15
 * @description
 */
public class JPdfXHtmlPageBreakVisitor extends JPdfXSvgVisitor {
    @Override
    public HtmlPageBreak visitHtmlPageBreak(JQuickPDFParser.HtmlPageBreakContext ctx) {
        HtmlPageBreak htmlPageBreak=new HtmlPageBreak(HtmlPageBreakType.ALWAYS);
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        String value = null;
        if (null != ctx.IDENTIFIER()) {
            value =ctx.IDENTIFIER().getText();
        }
        if(StringUtils.isNotEmpty(value)){
            JHtmlPageBreakTypeEnums enums=JHtmlPageBreakTypeEnums.codeOf(value);
            if(enums!=null){
                htmlPageBreak=new HtmlPageBreak(enums.getType());
            }
        }
        super.buildStyle(htmlPageBreak, style);
        return htmlPageBreak;
    }

}
