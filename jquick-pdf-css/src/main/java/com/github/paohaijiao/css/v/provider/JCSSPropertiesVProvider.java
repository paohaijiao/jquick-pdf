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
package com.github.paohaijiao.css.v.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesVProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {
    /**
     * Sets vertical-align property in iText Style
     *
     * @param style The iText Style to modify
     * @param value The vertical-align value
     */
    private static void setVerticalAlign(Style style, String value) {
        switch (value.toLowerCase()) {
            case "baseline":
            case "text-bottom":
                style.setVerticalAlignment(VerticalAlignment.BOTTOM);
                break;
            case "text-top":
                style.setVerticalAlignment(VerticalAlignment.TOP);
                break;
            case "middle":
                style.setVerticalAlignment(VerticalAlignment.MIDDLE);
                break;
            case "top":
                style.setVerticalAlignment(VerticalAlignment.TOP);
                break;
            case "bottom":
                style.setVerticalAlignment(VerticalAlignment.BOTTOM);
                break;
            case "sub":
                style.setProperty(Property.MARGIN_BOTTOM, UnitValue.createPointValue(-2));
                break;
            case "super":
                style.setProperty(Property.MARGIN_TOP, UnitValue.createPointValue(-2));
                break;
            default:
                if (isValidLengthValue(value)) {
                    float points = parseLengthValue(value);
                    style.setProperty(Property.MARGIN_TOP, UnitValue.createPointValue(points));
                }
                break;
        }
    }

    /**
     * Sets visibility property in iText Style
     *
     * @param style The iText Style to modify
     * @param value The visibility value
     */
    private static void setVisibility(Style style, String value) {
        switch (value.toLowerCase()) {
            case "hidden":
            case "collapse":
                style.setOpacity(0f);
                break;
            case "visible":
                style.setOpacity(1f);
                break;
        }
    }

    /**
     * Helper method to validate length values
     */
    private static boolean isValidLengthValue(String value) {
        if (value == null) return false;
        return value.matches("^-?\\d+(\\.\\d+)?(px|em|rem|%|vw|vh|vmin|vmax|cm|mm|in|pt|pc)$");
    }

    /**
     * Parses length values into points (iText's default unit)
     *
     * @param value The length value string (e.g., "10px", "2em", "50%")
     * @return The value in points
     */
    private static float parseLengthValue(String value) {
        // This is a simplified parser - you may need to enhance it based on your needs
        if (value.endsWith("px")) {
            return Float.parseFloat(value.substring(0, value.length() - 2));
        } else if (value.endsWith("pt")) {
            return Float.parseFloat(value.substring(0, value.length() - 2)); // points to points
        } else if (value.endsWith("in")) {
            return Float.parseFloat(value.substring(0, value.length() - 2)) * 72; // inches to points
        } else if (value.endsWith("cm")) {
            return Float.parseFloat(value.substring(0, value.length() - 2)) * 28.3465f; // cm to points
        } else if (value.endsWith("mm")) {
            return Float.parseFloat(value.substring(0, value.length() - 2)) * 2.83465f; // mm to points
        } else if (value.endsWith("%")) {
            return Float.parseFloat(value.substring(0, value.length() - 1));
        } else {
            return Float.parseFloat(value);
        }
    }

    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        String verticalAlign = cssProperties.getVerticalAlign();
        if (verticalAlign != null) {
            setVerticalAlign(style, verticalAlign);
        }
        String visibility = cssProperties.getVisibility();
        if (visibility != null) {
            setVisibility(style, visibility);
        }
        element.addStyle(style);
    }


}