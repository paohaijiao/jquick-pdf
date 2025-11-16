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
package com.github.paohaijiao.css.w.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.UnitValue;

/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesWProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {
    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssModel) {
        Style style = new Style();
        if (cssModel.getWhiteSpace() != null) {
            switch (cssModel.getWhiteSpace()) {
                case "nowrap":
                    style.setProperty(Property.WIDTH, UnitValue.createPointValue(10000));
                    break;
                case "pre":
                case "pre-wrap":
                    style.setFontFamily("Courier");
                    break;
            }
        }

        if (cssModel.getWidows() != null) {
            try {
                int widows = Integer.parseInt(cssModel.getWidows());
                if (widows > 1) {
                    style.setProperty(Property.KEEP_TOGETHER, true);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (cssModel.getWidth() != null) {
            if ("auto".equals(cssModel.getWidth())) {
                style.setProperty(Property.WIDTH, null); // null means auto in iText
            } else {
                try {
                    UnitValue width = parseUnitValue(cssModel.getWidth());
                    if (width != null) {
                        style.setProperty(Property.WIDTH, width);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (cssModel.getWordBreak() != null) {
            switch (cssModel.getWordBreak()) {
                case "break-all":
                    style.setProperty(Property.HYPHENATION, new HyphenationConfig("en", null, 1, 1));
                    break;
                case "keep-all":
                    style.setProperty(Property.KEEP_TOGETHER, true);
                    break;
                case "break-word":
                    style.setProperty(Property.HYPHENATION, new HyphenationConfig("en", null, 3, 3));
                    break;
            }
        }

        if (cssModel.getWordSpacing() != null) {
            try {
                UnitValue wordSpacing = parseUnitValue(cssModel.getWordSpacing());
                if (wordSpacing != null) {
                    style.setProperty(Property.CHARACTER_SPACING, wordSpacing.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (cssModel.getWordWrap() != null && "break-word".equals(cssModel.getWordWrap())) {
            style.setProperty(Property.HYPHENATION, new HyphenationConfig("en", null, 3, 3));
        }
        if (cssModel.getWritingMode() != null) {
            switch (cssModel.getWritingMode()) {
                case "vertical-rl":
                case "vertical-lr":
                    style.setProperty(Property.ROTATION_ANGLE, Math.PI / 2);
                    break;
            }
        }
        element.addStyle(style);
    }


}