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
package com.github.paohaijiao.css.j.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;
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
public class JCSSPropertiesJProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {
    /**
     * Converts justify-content CSS property to iText Style
     *
     * @param style The Style object to modify
     * @param css   The justify-content value
     */
    public static void applyJustifyContent(BlockElement<?> element, JCSSPropertiesCoreModel css, Style style) {
        if (css.getJustifyContent() == null) return;
        JustifyContent justifyContent = null;
        switch (css.getJustifyContent()) {
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
     * @param css   The justify-items value
     */
    public static void applyJustifyItems(BlockElement<?> element, JCSSPropertiesCoreModel css, Style style) {
        if (css.getJustifyItems() == null) return;

        //  JustifyItems justifyItems = null;

        switch (css.getJustifyItems()) {
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
     * @param css   The justify-self value
     */
    public static void applyJustifySelf(BlockElement<?> element, JCSSPropertiesCoreModel css, Style style) {
        if (css.getJustifySelf() == null) return;
        switch (css.getJustifySelf()) {
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
     * @param element       The Style object to modify
     * @param cssProperties JCSSPropertiesJModel containing the properties
     */
    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        applyJustifyContent(element, cssProperties, style);
        applyJustifyItems(element, cssProperties, style);
        element.addStyle(style);
    }


}