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
package com.github.paohaijiao.css.i.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;
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
public class JCSSPropertiesIProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {

    public static Style convertToStyle(BlockElement<?> element, JCSSPropertiesCoreModel css, Style style) {
        // Image rendering
        if (css.getImageRendering() != null) {
            // iText doesn't have direct support for image-rendering, but we can set as property
            // style.setProperty(Property.IMAGE_RENDERING, css.getImageRendering());
        }

        // Initial letter (not directly supported in iText)
        if (css.getInitialLetter() != null) {
            // Would need custom implementation for drop caps
        }

        // Inline size (similar to width for horizontal writing)
        if (css.getInlineSize() != null) {
            if (css.getInlineSize().equals("auto")) {
                //  style.setWidth(UnitValue.AUTO);
            } else if (css.getInlineSize().equals("min-content")) {
                // Approximate min-content
                //  style.setWidth(UnitValue.createPointValue(0));
            } else if (css.getInlineSize().equals("max-content")) {
                // Approximate max-content
                // style.setWidth(UnitValue.createPercentValue(100));
            } else if (css.getInlineSize().equals("fit-content")) {
                // Approximate fit-content
                //   style.setWidth(UnitValue.createPercentValue(100));
            } else {
                // Parse length or percentage
                //style.setWidth(UnitValue.parseUnitValue(css.getInlineSize()));
            }
        }

        // Inset (similar to padding/margin)
        if (css.getInset() != null) {
            String[] parts = css.getInset().split(" ");
            switch (parts.length) {
                case 1:
//                    UnitValue value = parseInsetValue(parts[0]);
//                    style.setPadding(value);
                    break;
                case 2:
//                    UnitValue vert = parseInsetValue(parts[0]);
//                    UnitValue horiz = parseInsetValue(parts[1]);
//                    style.setPadding(vert, horiz, vert, horiz);
                    break;
                case 3:
//                    UnitValue top = parseInsetValue(parts[0]);
//                    UnitValue rightLeft = parseInsetValue(parts[1]);
//                    UnitValue bottom = parseInsetValue(parts[2]);
//                    style.setPadding(top, rightLeft, bottom, rightLeft);
                    break;
                case 4:
//                    style.setPadding(
//                            parseInsetValue(parts[0]), // top
//                            parseInsetValue(parts[1]), // right
//                            parseInsetValue(parts[2]), // bottom
//                            parseInsetValue(parts[3])  // left
//                    );
                    break;
            }
        }

        // Inset block (similar to padding/margin block)
        if (css.getInsetBlock() != null) {
            String[] parts = css.getInsetBlock().split(" ");
            if (parts.length == 1) {
                UnitValue value = parseInsetValue(parts[0]);
//                style.setPaddingTop(value);
//                style.setPaddingBottom(value);
            } else if (parts.length == 2) {
//                style.setPaddingTop(parseInsetValue(parts[0]));
//                style.setPaddingBottom(parseInsetValue(parts[1]));
            }
        }

        // Inset block start/end
        if (css.getInsetBlockStart() != null) {
            //style.setPaddingTop(parseInsetValue(css.getInsetBlockStart()));
        }
        if (css.getInsetBlockEnd() != null) {
            //style.setPaddingBottom(parseInsetValue(css.getInsetBlockEnd()));
        }

        // Inset inline (similar to padding/margin inline)
        if (css.getInsetInline() != null) {
            String[] parts = css.getInsetInline().split(" ");
            if (parts.length == 1) {
                UnitValue value = parseInsetValue(parts[0]);
//                style.setPaddingLeft(value);
//                style.setPaddingRight(value);
            } else if (parts.length == 2) {
//                style.setPaddingLeft(parseInsetValue(parts[0]));
//                style.setPaddingRight(parseInsetValue(parts[1]));
            }
        }

        // Inset inline start/end
        if (css.getInsetInlineStart() != null) {
            // Depends on text direction (LTR/RTL)
            // Assuming LTR for this example
            //style.setPaddingLeft(parseInsetValue(css.getInsetInlineStart()));
        }
        if (css.getInsetInlineEnd() != null) {
            // Assuming LTR for this example
            //   style.setPaddingRight(parseInsetValue(css.getInsetInlineEnd()));
        }

        // Isolation (not directly supported in iText)
        if (css.getIsolation() != null) {
            // Could be implemented using layers or grouping if needed
        }

        // @import rules would need to be handled at document level, not style level
        // So we skip getImportRule() for style conversion

        return style;
    }

    private static UnitValue parseInsetValue(String value) {
//        if ("auto".equals(value)) {
//            return UnitValue.AUTO;
//        }
//        return UnitValue.parseUnitValue(value);
        return null;
    }

    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        convertToStyle(element, cssProperties, style);
        element.addStyle(style);
    }

}