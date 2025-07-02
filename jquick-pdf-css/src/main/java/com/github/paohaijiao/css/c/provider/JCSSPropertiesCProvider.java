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
package com.github.paohaijiao.css.c.provider;

import com.github.paohaijiao.css.c.model.JCSSPropertiesCModel;
import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesCProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {

    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssModel) {
        Style style=new Style();
        if (cssModel == null) {
            return ;
        }
        applyBasicProperties(element,cssModel, style);
        applyColumnProperties(element,cssModel, style);
        applyContentProperties(element,cssModel, style);
        applyOtherProperties(element,cssModel, style);
        element.addStyle(style);
    }

    private static void applyBasicProperties(BlockElement<?> element, JCSSPropertiesCModel cssModel, Style style) {
        if (cssModel.getColor() != null) {
            Color textColor = parseColor(cssModel.getColor());
            if (textColor != null) {
                style.setFontColor(textColor);
            }
        }

        if (cssModel.getCaretColor() != null) {

        }

        if (cssModel.getClear() != null) {
        }

        if (cssModel.getClip() != null || cssModel.getClipPath() != null) {
        }
    }

    private static void applyColumnProperties(BlockElement<?> element, JCSSPropertiesCModel cssModel, Style style) {
        if (cssModel.getColumnCount() != null || cssModel.getColumnWidth() != null || cssModel.getColumns() != null) {
        }

        if (cssModel.getColumnGap() != null) {
            //style.setProperty(Property.COLUMN_GAP, gap);
        }

        if (cssModel.getColumnRule() != null ||
                (cssModel.getColumnRuleColor() != null && cssModel.getColumnRuleWidth() != null)) {
            //style.setProperty(Property.COLUMN_RULE, rule);
        }
    }

    private static void applyContentProperties(BlockElement<?> element, JCSSPropertiesCModel cssModel, Style style) {
        if (cssModel.getContent() != null) {
        }

        if (cssModel.getCounterIncrement() != null ||
                cssModel.getCounterReset() != null ||
                cssModel.getCounterSet() != null) {
        }
    }

    private static void applyOtherProperties(BlockElement<?> element, JCSSPropertiesCModel cssModel, Style style) {
        if (cssModel.getCursor() != null) {
        }
        if (cssModel.getCharsetRule() != null ||
                cssModel.getContainerRule() != null ||
                cssModel.getCounterStyleRule() != null) {

        }
    }



}