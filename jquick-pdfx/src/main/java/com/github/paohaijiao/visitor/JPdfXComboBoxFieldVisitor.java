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
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.layout.form.element.ComboBoxField;
import com.itextpdf.layout.element.*;

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
public class JPdfXComboBoxFieldVisitor extends JPdfXCheckBoxVisitor {

    @Override
    public ComboBoxField visitComboBoxField(JQuickPDFParser.ComboBoxFieldContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        String text = "";
        Object value = null;
        if (ctx.elemValue() != null) {
            value =visitElemValue(ctx.elemValue());
            if (null != value && value instanceof String) {
                text = (String) value;
            }
        }
        ComboBoxField comboBoxField = new ComboBoxField(text);
        saveSub(comboBoxField,value);
        super.buildStyle(comboBoxField, style);
        return comboBoxField;
    }
    private void saveSub(ComboBoxField comboBoxField,Object object) {
        if(null!=object&&object instanceof java.util.List) {
            java.util.List<Object> list=(java.util.List<Object>) object;
            list.forEach(e -> {
                if (e instanceof IBlockElement) {
                    comboBoxField.addOption((IBlockElement) e);
                }
            });
        }
    }
}
