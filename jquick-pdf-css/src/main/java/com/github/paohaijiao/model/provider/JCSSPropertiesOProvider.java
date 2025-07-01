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
package com.github.paohaijiao.model.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesOModel;

import javax.swing.text.Style;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesOProvider extends JCSSPropertiesBaseProvider {
    public static void applyProperties(JCSSPropertiesOModel cssProps, Style style) {
        applyObjectProperties(cssProps, style);
        applyOpacityAndOrder(cssProps, style);
        applyOutlineProperties(cssProps, style);
        applyOverflowProperties(cssProps, style);
    }

    private static void applyObjectProperties(JCSSPropertiesOModel cssProps, Style style) {
        if (cssProps.getObjectFit() != null) {
            String objectFit = cssProps.getObjectFit();
        }

        if (cssProps.getObjectPosition() != null) {
        }

        if (cssProps.getOffset() != null) {
        }
    }

    private static void applyOpacityAndOrder(JCSSPropertiesOModel cssProps, Style style) {
        if (cssProps.getOpacity() != null) {
            try {
                float opacity = Float.parseFloat(cssProps.getOpacity());
                //style.setOpacity(opacity);
            } catch (NumberFormatException e) {
                // Handle invalid opacity
            }
        }

        if (cssProps.getOrder() != null) {
        }

        if (cssProps.getOrphans() != null) {
            try {
                int orphans = Integer.parseInt(cssProps.getOrphans());
                //tyle.setProperty(Property.ORPHANS_CONTROL, orphans);
            } catch (NumberFormatException e) {
                // Handle invalid value
            }
        }
    }

    private static void applyOutlineProperties(JCSSPropertiesOModel cssProps, Style style) {
        // Outline shorthand
        if (cssProps.getOutline() != null) {
        }

        // Individual outline properties
        if (cssProps.getOutlineColor() != null) {
            //style.setProperty(Property.OUTLINE_COLOR, parseColor(cssProps.getOutlineColor()));
        }

        if (cssProps.getOutlineStyle() != null) {
            // Map CSS outline style to iText border style
            String outlineStyle = cssProps.getOutlineStyle();
            switch (outlineStyle) {
                case "none":
                case "hidden":
//                    style.setProperty(Property.OUTLINE, null);
                    break;
                case "dotted":
//                    style.setProperty(Property.OUTLINE_STYLE, BorderBorderStyle.DOTTED);
//                    break;
                case "dashed":
                    // style.setProperty(Property.OUTLINE_STYLE, BorderBorderStyle.DASHED);
                    break;
                case "solid":
                    //      style.setProperty(Property.OUTLINE_STYLE, BorderBorderStyle.SOLID);
                    break;
                // Handle other styles...
            }
        }

        if (cssProps.getOutlineWidth() != null) {
            //style.setProperty(Property.OUTLINE_WIDTH, UnitValue.createPointValue(parseLength(cssProps.getOutlineWidth())));
        }

        if (cssProps.getOutlineOffset() != null) {
        }
    }

    private static void applyOverflowProperties(JCSSPropertiesOModel cssProps, Style style) {
        if (cssProps.getOverflow() != null) {
            String overflow = cssProps.getOverflow();
            if ("hidden".equals(overflow) || "clip".equals(overflow)) {
                // style.setProperty(Property.OVERFLOW_HIDDEN, true);
            }
        }

        if (cssProps.getOverflowX() != null || cssProps.getOverflowY() != null) {
            String overflow = cssProps.getOverflowX() != null ? cssProps.getOverflowX() : cssProps.getOverflowY();
            if ("hidden".equals(overflow) || "clip".equals(overflow)) {
                //  style.setProperty(Property.OVERFLOW_HIDDEN, true);
            }
        }

        if (cssProps.getOverflowWrap() != null) {
            String overflowWrap = cssProps.getOverflowWrap();
            if ("break-word".equals(overflowWrap)) {
                //  style.setProperty(Property.BREAK_WORDS, true);
            }
        }
    }


    private static float parseLength(String lengthValue) {
        // Implement CSS length parsing (px, pt, em, etc.)
        try {
            return Float.parseFloat(lengthValue.replaceAll("[^0-9.]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}