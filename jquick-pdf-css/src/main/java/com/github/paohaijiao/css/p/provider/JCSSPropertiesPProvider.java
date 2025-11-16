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
package com.github.paohaijiao.css.p.provider;

import com.github.paohaijiao.css.p.model.JCSSPropertiesPModel;
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
public class JCSSPropertiesPProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {
    private static void applyPaddingProperties(Style style, JCSSPropertiesPModel cssModel) {
        String padding = cssModel.getPadding();
        if (padding != null) {
            String[] values = padding.split("\\s+");
            switch (values.length) {
                case 1:
                    float singleValue = parseFloatValue(values[0]);
                    style.setPadding(singleValue);
                    break;
                case 2:
                    float vertical = parseFloatValue(values[0]);
                    float horizontal = parseFloatValue(values[1]);
                    style.setPaddingTop(vertical);
                    style.setPaddingBottom(vertical);
                    style.setPaddingLeft(horizontal);
                    style.setPaddingRight(horizontal);
                    break;
                case 3:
                    float top = parseFloatValue(values[0]);
                    float hor = parseFloatValue(values[1]);
                    float bottom = parseFloatValue(values[2]);
                    style.setPaddingTop(top);
                    style.setPaddingBottom(bottom);
                    style.setPaddingLeft(hor);
                    style.setPaddingRight(hor);
                    break;
                case 4:
                    style.setPaddingTop(parseFloatValue(values[0]));
                    style.setPaddingRight(parseFloatValue(values[1]));
                    style.setPaddingBottom(parseFloatValue(values[2]));
                    style.setPaddingLeft(parseFloatValue(values[3]));
                    break;
            }
        }

        if (cssModel.getPaddingTop() != null) {
            style.setPaddingTop(parseFloatValue(cssModel.getPaddingTop()));
        }
        if (cssModel.getPaddingRight() != null) {
            style.setPaddingRight(parseFloatValue(cssModel.getPaddingRight()));
        }
        if (cssModel.getPaddingBottom() != null) {
            style.setPaddingBottom(parseFloatValue(cssModel.getPaddingBottom()));
        }
        if (cssModel.getPaddingLeft() != null) {
            style.setPaddingLeft(parseFloatValue(cssModel.getPaddingLeft()));
        }

        if (cssModel.getPaddingBlock() != null) {
            String[] blockValues = cssModel.getPaddingBlock().split("\\s+");
            if (blockValues.length > 0) {
                style.setPaddingTop(parseFloatValue(blockValues[0]));
                if (blockValues.length > 1) {
                    style.setPaddingBottom(parseFloatValue(blockValues[1]));
                } else {
                    style.setPaddingBottom(parseFloatValue(blockValues[0]));
                }
            }
        }

        if (cssModel.getPaddingInline() != null) {
            String[] inlineValues = cssModel.getPaddingInline().split("\\s+");
            if (inlineValues.length > 0) {
                style.setPaddingLeft(parseFloatValue(inlineValues[0]));
                if (inlineValues.length > 1) {
                    style.setPaddingRight(parseFloatValue(inlineValues[1]));
                } else {
                    style.setPaddingRight(parseFloatValue(inlineValues[0]));
                }
            }
        }

        if (cssModel.getPaddingBlockStart() != null) {
            style.setPaddingTop(parseFloatValue(cssModel.getPaddingBlockStart()));
        }
        if (cssModel.getPaddingBlockEnd() != null) {
            style.setPaddingBottom(parseFloatValue(cssModel.getPaddingBlockEnd()));
        }
        if (cssModel.getPaddingInlineStart() != null) {
            style.setPaddingLeft(parseFloatValue(cssModel.getPaddingInlineStart()));
        }
        if (cssModel.getPaddingInlineEnd() != null) {
            style.setPaddingRight(parseFloatValue(cssModel.getPaddingInlineEnd()));
        }
    }

    private static void applyPageProperties(Style style, JCSSPropertiesPModel cssModel) {
        // Page break properties
        if (cssModel.getPageBreakAfter() != null) {
            switch (cssModel.getPageBreakAfter()) {
                case "always":
                    //  style.set(Property.PAGE_BREAK_AFTER, com.itextpdf.layout.properties.PageBreakType.ALWAYS);
                    break;
                case "avoid":
                    //  style.set(Property.PAGE_BREAK_AFTER, com.itextpdf.layout.properties.PageBreakType.AVOID);
                    break;
                case "left":
                    // style.set(Property.PAGE_BREAK_AFTER, com.itextpdf.layout.properties.PageBreakType.LEFT);
                    break;
                case "right":
                    // style.set(Property.PAGE_BREAK_AFTER, com.itextpdf.layout.properties.PageBreakType.RIGHT);
                    break;
                default: // "auto" or others
                    // style.set(Property.PAGE_BREAK_AFTER, com.itextpdf.layout.properties.PageBreakType.AUTO);
            }
        }

        if (cssModel.getPageBreakBefore() != null) {
            switch (cssModel.getPageBreakBefore()) {
                case "always":
                    //  style.set(Property.PAGE_BREAK_BEFORE, com.itextpdf.layout.properties.PageBreakType.ALWAYS);
                    break;
                case "avoid":
                    //   style.set(Property.PAGE_BREAK_BEFORE, com.itextpdf.layout.properties.PageBreakType.AVOID);
                    break;
                case "left":
                    // style.set(Property.PAGE_BREAK_BEFORE, com.itextpdf.layout.properties.PageBreakType.LEFT);
                    break;
                case "right":
                    //  style.set(Property.PAGE_BREAK_BEFORE, com.itextpdf.layout.properties.PageBreakType.RIGHT);
                    break;
                default: // "auto" or others
                    // style.set(Property.PAGE_BREAK_BEFORE, com.itextpdf.layout.properties.PageBreakType.AUTO);
            }
        }

        if (cssModel.getPageBreakInside() != null) {
            switch (cssModel.getPageBreakInside()) {
                case "avoid":
                    //  style.set(Property.PAGE_BREAK_INSIDE, com.itextpdf.layout.properties.PageBreakType.AVOID);
                    break;
                default: // "auto" or others
                    //   style.set(Property.PAGE_BREAK_INSIDE, com.itextpdf.layout.properties.PageBreakType.AUTO);
            }
        }
    }

    private static void applyVisualProperties(Style style, JCSSPropertiesPModel cssModel) {
        // Paint order - iText doesn't have direct support, but we can set rendering mode
        if (cssModel.getPaintOrder() != null) {
            // This is a simplified mapping as iText doesn't have exact equivalent
            if ("stroke".equals(cssModel.getPaintOrder())) {
                // style.setStrokeFirst(true);
            } else if ("fill".equals(cssModel.getPaintOrder())) {
                //  style.setStrokeFirst(false);
            }
        }

        // Perspective - iText doesn't support 3D transformations directly
        // We can only approximate with 2D transformations if needed

        // Place properties (shorthands for alignment)
        if (cssModel.getPlaceContent() != null) {
            String[] placeValues = cssModel.getPlaceContent().split("\\s+");
            if (placeValues.length > 0) {
                //   style.setProperty(Property.ALIGN_CONTENT, convertAlignment(placeValues[0]));
                if (placeValues.length > 1) {
                    //      style.setProperty(Property.JUSTIFY_CONTENT, convertAlignment(placeValues[1]));
                }
            }
        }

        if (cssModel.getPlaceItems() != null) {
            String[] placeValues = cssModel.getPlaceItems().split("\\s+");
            if (placeValues.length > 0) {
                //   style.setProperty(Property.ALIGN_ITEMS, convertAlignment(placeValues[0]));
                if (placeValues.length > 1) {
                    //        style.setProperty(Property.JUSTIFY_ITEMS, convertAlignment(placeValues[1]));
                }
            }
        }

        if (cssModel.getPlaceSelf() != null) {
            String[] placeValues = cssModel.getPlaceSelf().split("\\s+");
            if (placeValues.length > 0) {
                //     style.setProperty(Property.ALIGN_SELF, convertAlignment(placeValues[0]));
                if (placeValues.length > 1) {
                    ///      style.setProperty(Property.JUSTIFY_SELF, convertAlignment(placeValues[1]));
                }
            }
        }

        // Pointer events - iText doesn't have interactive elements in the same way as web
        // So this property would have no effect in PDF rendering

        // Position property
        if (cssModel.getPosition() != null) {
            switch (cssModel.getPosition()) {
                case "absolute":
//                    style.setProperty(Property.POSITION, com.itextpdf.layout.properties.PositionProperty.ABSOLUTE);
                    break;
                case "fixed":
                    //         style.setProperty(Property.POSITION, com.itextpdf.layout.properties.PositionProperty.FIXED);
                    break;
                case "relative":
//                    style.setProperty(Property.POSITION, com.itextpdf.layout.properties.PositionProperty.RELATIVE);
                    break;
                case "sticky":
                    // iText doesn't support sticky positioning, fall back to relative
                    //   style.setProperty(Property.POSITION, com.itextpdf.layout.properties.PositionProperty.RELATIVE);
                    break;
                default: // "static" or others
                    // style.setProperty(Property.POSITION, PositionProperty.STATIC);
            }
        }
    }

    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssModel) {
        Style style = new Style();
        applyPaddingProperties(style, cssModel);
        applyPageProperties(style, cssModel);
        applyVisualProperties(style, cssModel);
        element.addStyle(style);
    }


//    private static com.itextpdf.layout.properties.Alignment convertAlignment(String cssValue) {
//        switch (cssValue.toLowerCase()) {
//            case "start":
//            case "flex-start":
//                return com.itextpdf.layout.properties.Alignment.START;
//            case "end":
//            case "flex-end":
//                return com.itextpdf.layout.properties.Alignment.END;
//            case "center":
//                return com.itextpdf.layout.properties.Alignment.CENTER;
//            case "stretch":
//                return com.itextpdf.layout.properties.Alignment.STRETCH;
//            case "space-between":
//                return com.itextpdf.layout.properties.Alignment.SPACE_BETWEEN;
//            case "space-around":
//                return com.itextpdf.layout.properties.Alignment.SPACE_AROUND;
//            case "baseline":
//                return com.itextpdf.layout.properties.Alignment.BASELINE;
//            default:
//                return com.itextpdf.layout.properties.Alignment.LEFT;
//        }
// }
}