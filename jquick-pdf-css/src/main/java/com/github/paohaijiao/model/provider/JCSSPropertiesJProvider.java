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

import com.github.paohaijiao.model.css.JCSSPropertiesJModel;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.properties.JustifyContent;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesJProvider extends JCSSPropertiesBaseProvider {

    /**
     * Converts justify-content CSS property to iText Style
     *
     * @param style The Style object to modify
     * @param value The justify-content value
     */
    public static void applyJustifyContent(Style style, String value) {
        if (value == null) return;

        JustifyContent justifyContent = null;
        switch (value) {
            case "flex-start":
                justifyContent = JustifyContent.FLEX_START;
                break;
            case "flex-end":
                justifyContent = JustifyContent.FLEX_END;
                break;
            case "center":
                justifyContent = JustifyContent.CENTER;
                break;
            case "space-between":
                //    justifyContent = JustifyContent.SPACE_BETWEEN;
                break;
            case "space-around":
                //    justifyContent = JustifyContent.SPACE_AROUND;
                break;
            case "space-evenly":
                //     justifyContent = JustifyContent.SPACE_EVENLY;
                break;
            case "start":
            case "left":
                justifyContent = JustifyContent.START;
                break;
            case "end":
            case "right":
                justifyContent = JustifyContent.END;
                break;
            case "normal":
            case "stretch":
            default:
                // iText doesn't have direct equivalents for all CSS values
                // Use default or closest match
                break;
        }

        if (justifyContent != null) {
            //style.setProperty(Property.JUSTIFY_CONTENT, justifyContent);
        }
    }

    /**
     * Converts justify-items CSS property to iText Style
     *
     * @param style The Style object to modify
     * @param value The justify-items value
     */
    public static void applyJustifyItems(Style style, String value) {
        if (value == null) return;

        //  JustifyItems justifyItems = null;

        switch (value) {
            case "flex-start":
            case "start":
            case "self-start":
            case "left":
                //       justifyItems = JustifyItems.START;
                break;
            case "flex-end":
            case "end":
            case "self-end":
            case "right":
                //     justifyItems = JustifyItems.END;
                break;
            case "center":
                //    justifyItems = JustifyItems.CENTER;
                break;
            case "stretch":
                //    justifyItems = JustifyItems.STRETCH;
                break;
            case "baseline":
            case "first baseline":
            case "last baseline":
                //   justifyItems = JustifyItems.BASELINE;
                break;
            case "normal":
            default:
                // iText doesn't have direct equivalents for all CSS values
                break;
        }

//        if (justifyItems != null) {
//            style.setProperty(Property.JUSTIFY_ITEMS, justifyItems);
//        }
    }

    /**
     * Converts justify-self CSS property to iText Style
     *
     * @param style The Style object to modify
     * @param value The justify-self value
     */
    public static void applyJustifySelf(Style style, String value) {
        if (value == null) return;

        // JustifySelf justifySelf = null;

        switch (value) {
            case "flex-start":
            case "start":
            case "self-start":
            case "left":
                //      justifySelf = JustifySelf.START;
                break;
            case "flex-end":
            case "end":
            case "self-end":
            case "right":
                //     justifySelf = JustifySelf.END;
                break;
            case "center":
                //     justifySelf = JustifySelf.CENTER;
                break;
            case "stretch":
                //    justifySelf = JustifySelf.STRETCH;
                break;
            case "baseline":
            case "first baseline":
            case "last baseline":
                //   justifySelf = JustifySelf.BASELINE;
                break;
            case "auto":
                //    justifySelf = JustifySelf.AUTO;
                break;
            case "normal":
            default:
                // iText doesn't have direct equivalents for all CSS values
                break;
        }

//        if (justifySelf != null) {
//            style.setProperty(Property.JUSTIFY_SELF, justifySelf);
//        }
    }

    /**
     * Applies all justify properties from JCSSPropertiesJModel to an iText Style
     *
     * @param style The Style object to modify
     * @param model The JCSSPropertiesJModel containing the properties
     */
    public static void applyAllJustifyProperties(Style style, JCSSPropertiesJModel model) {
        if (style == null || model == null) return;

        if (model.getJustifyContent() != null) {
            applyJustifyContent(style, model.getJustifyContent());
        }

        if (model.getJustifyItems() != null) {
            applyJustifyItems(style, model.getJustifyItems());
        }

        if (model.getJustifySelf() != null) {
            applyJustifySelf(style, model.getJustifySelf());
        }
    }

}