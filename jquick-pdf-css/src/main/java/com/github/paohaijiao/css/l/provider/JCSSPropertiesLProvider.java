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
package com.github.paohaijiao.css.l.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
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
public class JCSSPropertiesLProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {

    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        com.itextpdf.layout.Style style=new com.itextpdf.layout.Style();
        applyProperties(element,cssProperties,style);
        element.addStyle(style);
    }
    public static void applyProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProps, Style style) {
        if (cssProps.getLeft() != null) {
        }

        if (cssProps.getLetterSpacing() != null) {
            if ("normal".equalsIgnoreCase(cssProps.getLetterSpacing())) {
                //   style.setCharacterSpacing(0);
            } else {
                try {
                    float spacing = Float.parseFloat(cssProps.getLetterSpacing().replaceAll("[^0-9.-]", ""));
                    //   style.setCharacterSpacing(spacing);
                } catch (NumberFormatException e) {
                    // Handle invalid value
                }
            }
        }

        // line-height
        if (cssProps.getLineHeight() != null) {
            if ("normal".equalsIgnoreCase(cssProps.getLineHeight())) {
                //  style.setLineHeight(1.2f); // Default normal line height
            } else {
                try {
                    float lineHeight = Float.parseFloat(cssProps.getLineHeight().replaceAll("[^0-9.-]", ""));
                    // style.setLineHeight(lineHeight);
                } catch (NumberFormatException e) {
                    // Handle invalid value
                }
            }
        }

        if (cssProps.getListStyle() != null) {
            String[] parts = cssProps.getListStyle().split(" ");
            for (String part : parts) {
//                if (JCSSPropertiesLModel.VALID_LIST_TYPES.contains(part)) {
//                    style.setListSymbol(part); // iText will handle standard types
//                } else if (JCSSPropertiesLModel.VALID_LIST_POSITIONS.contains(part)) {
//                    style.setProperty(Property.LIST_SYMBOL_POSITION,
//                            "inside".equals(part) ? ListSymbolPosition.INSIDE : ListSymbolPosition.OUTSIDE);
//                } else if (part.startsWith("url(")) {
//                    // Handle list-style-image (would need image handling)
//                }
            }
        }

        // list-style-image
        if (cssProps.getListStyleImage() != null && !"none".equalsIgnoreCase(cssProps.getListStyleImage())) {
            // iText doesn't directly support list images, would need custom implementation
            // This would involve creating a custom ListSymbol implementation
        }

        // list-style-position
        if (cssProps.getListStylePosition() != null) {
//            style.setProperty(Property.LIST_SYMBOL_POSITION,
//                    "inside".equals(cssProps.getListStylePosition()) ?
//                            ListSymbolPosition.INSIDE : ListSymbolPosition.OUTSIDE);
        }

        if (cssProps.getListStyleType() != null) {
            //  style.setListSymbol(cssProps.getListStyleType());
            // iText supports: DISC, CIRCLE, SQUARE, DECIMAL,
            // LOWER_LATIN, UPPER_LATIN, LOWER_ROMAN, UPPER_ROMAN, etc.
        }
    }


}