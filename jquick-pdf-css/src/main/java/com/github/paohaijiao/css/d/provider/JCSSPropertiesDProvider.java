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
package com.github.paohaijiao.css.d.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.properties.Property;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesDProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {
    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        convertToStyle(element,cssProperties,style);
        element.addStyle(style);
    }
    /**
     * Converts JCSSPropertiesDModel properties to iText Style
     *
     * @param model the CSS properties model
     * @return iText Style object with configured properties
     */
    public static Style convertToStyle(BlockElement<?> element, JCSSPropertiesCoreModel model,  Style style) {
        if (model.getDirection() != null) {
            switch (model.getDirection().toLowerCase()) {
                case "ltr":
                    style.setProperty(Property.BASE_DIRECTION, com.itextpdf.layout.properties.BaseDirection.LEFT_TO_RIGHT);
                    break;
                case "rtl":
                    style.setProperty(Property.BASE_DIRECTION, com.itextpdf.layout.properties.BaseDirection.RIGHT_TO_LEFT);
                    break;
                default:
                    // Default is left-to-right
                    style.setProperty(Property.BASE_DIRECTION, com.itextpdf.layout.properties.BaseDirection.LEFT_TO_RIGHT);
            }
        }
        if (model.getDisplay() != null) {
            switch (model.getDisplay().toLowerCase()) {
                case "none":
                    style.setOpacity(0f);
                    break;
                case "block":
                    break;
                case "inline":
                    // For inline elements, you might need to handle differently
                    // This depends on your specific use case
                    break;
                case "flex":
                    // iText 7.2+ supports flex layout
                    // style.setProperty(Property.DISPLAY, com.itextpdf.layout.properties.Display.FLEX);
                    break;
                case "inline-block":
                    // Similar to inline but allows some block properties
                    // Might need custom handling
                    break;
                default:
                    // Default is block
            }
        }

        return style;
    }

}