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
import com.github.paohaijiao.util.JStringUtils;
import com.github.paohaijiao.visitor.element.JQuickCheckBoxElementRender;

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
    public JQuickCheckBoxElementRender visitCheckbox(JQuickPDFParser.CheckboxContext ctx) {
        JStyleAttributes style;
        String value = "";
        boolean checked = false;
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        if (ctx.checkboxStatus() != null) {
            checked = true;
        }
        if (ctx.value() != null) {
            Object val = visitValue(ctx.value());
            if (val != null) {
                value = val.toString();
            }
        }
        JQuickCheckBoxElementRender checkBoxElement = new JQuickCheckBoxElementRender(checked, JStringUtils.trim(value), style);
        super.buildStyle(checkBoxElement, style);
        return checkBoxElement;
    }
}
