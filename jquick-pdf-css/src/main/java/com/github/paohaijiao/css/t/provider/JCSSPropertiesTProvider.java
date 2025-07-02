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
package com.github.paohaijiao.css.t.provider;

import com.github.paohaijiao.css.s.model.JCSSPropertiesSModel;
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
public class JCSSPropertiesTProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {
    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        applyProperties(cssProperties, style);
        applyScrollMarginProperties(cssProperties, style);
        element.addStyle(style);
    }
    public static Style applyProperties(JCSSPropertiesSModel cssModel, Style style) {
        if (cssModel == null || style == null) {
            return style;
        }

        // Scale property
        if (cssModel.getScale() != null) {
            String[] scaleValues = cssModel.getScale().split(" ");
            if (scaleValues.length >= 1) {
                float scaleX = Float.parseFloat(scaleValues[0]);
                float scaleY = scaleValues.length >= 2 ? Float.parseFloat(scaleValues[1]) : scaleX;
//                style.setProperty(Property.TRANSFORM, new com.itextpdf.kernel.pdf.canvas.PdfCanvas.TransformationMatrix()
//                        .scale(scaleX, scaleY));
            }
        }

        // Scroll behavior - not directly supported in iText, but we can add as custom property
        if (cssModel.getScrollBehavior() != null) {
            //  style.setProperty(Property.SCROLL_BEHAVIOR, cssModel.getScrollBehavior());
        }
        applyScrollMarginProperties(cssModel, style);
        applyScrollPaddingProperties(cssModel, style);
        applyScrollSnapProperties(cssModel, style);

        if (cssModel.getScrollbarColor() != null) {
            // style.setProperty(Property.SCROLLBAR_COLOR, cssModel.getScrollbarColor());
        }

        // Shape outside - not directly supported in iText
        if (cssModel.getShapeOutside() != null) {
            //  style.setProperty(Property.SHAPE_OUTSIDE, cssModel.getShapeOutside());
        }

        return style;
    }

    private static void applyScrollMarginProperties(JCSSPropertiesSModel cssModel, Style style) {
        // Shorthand scroll-margin
        if (cssModel.getScrollMargin() != null) {
            String[] margins = cssModel.getScrollMargin().split(" ");
            switch (margins.length) {
                case 1:
                    UnitValue margin = parseUnitValue(margins[0]);
//                    style.setProperty(Property.SCROLL_MARGIN_TOP, margin);
//                    style.setProperty(Property.SCROLL_MARGIN_RIGHT, margin);
//                    style.setProperty(Property.SCROLL_MARGIN_BOTTOM, margin);
//                    style.setProperty(Property.SCROLL_MARGIN_LEFT, margin);
                    break;
                case 2:
                    UnitValue vertical = parseUnitValue(margins[0]);
                    UnitValue horizontal = parseUnitValue(margins[1]);
//                    style.setProperty(Property.SCROLL_MARGIN_TOP, vertical);
//                    style.setProperty(Property.SCROLL_MARGIN_BOTTOM, vertical);
//                    style.setProperty(Property.SCROLL_MARGIN_LEFT, horizontal);
//                    style.setProperty(Property.SCROLL_MARGIN_RIGHT, horizontal);
                    break;
                case 3:
//                    style.setProperty(Property.SCROLL_MARGIN_TOP, parseUnitValue(margins[0]));
//                    style.setProperty(Property.SCROLL_MARGIN_LEFT, parseUnitValue(margins[1]));
//                    style.setProperty(Property.SCROLL_MARGIN_RIGHT, parseUnitValue(margins[1]));
//                    style.setProperty(Property.SCROLL_MARGIN_BOTTOM, parseUnitValue(margins[2]));
                    break;
                case 4:
//                    style.setProperty(Property.SCROLL_MARGIN_TOP, parseUnitValue(margins[0]));
//                    style.setProperty(Property.SCROLL_MARGIN_RIGHT, parseUnitValue(margins[1]));
//                    style.setProperty(Property.SCROLL_MARGIN_BOTTOM, parseUnitValue(margins[2]));
//                    style.setProperty(Property.SCROLL_MARGIN_LEFT, parseUnitValue(margins[3]));
                    break;
            }
        }

        // Individual scroll margin properties
        if (cssModel.getScrollMarginTop() != null) {
            //  style.setProperty(Property.SCROLL_MARGIN_TOP, parseUnitValue(cssModel.getScrollMarginTop()));
        }
        if (cssModel.getScrollMarginRight() != null) {
            //   style.setProperty(Property.SCROLL_MARGIN_RIGHT, parseUnitValue(cssModel.getScrollMarginRight()));
        }
        if (cssModel.getScrollMarginBottom() != null) {
            //    style.setProperty(Property.SCROLL_MARGIN_BOTTOM, parseUnitValue(cssModel.getScrollMarginBottom()));
        }
        if (cssModel.getScrollMarginLeft() != null) {
            //    style.setProperty(Property.SCROLL_MARGIN_LEFT, parseUnitValue(cssModel.getScrollMarginLeft()));
        }
        if (cssModel.getScrollMarginBlock() != null) {
            String[] blockMargins = cssModel.getScrollMarginBlock().split(" ");
            //    style.setProperty(Property.SCROLL_MARGIN_BLOCK_START, parseUnitValue(blockMargins[0]));
            if (blockMargins.length > 1) {
                //      style.setProperty(Property.SCROLL_MARGIN_BLOCK_END, parseUnitValue(blockMargins[1]));
            } else {
                //    style.setProperty(Property.SCROLL_MARGIN_BLOCK_END, parseUnitValue(blockMargins[0]));
            }
        }
        if (cssModel.getScrollMarginBlockStart() != null) {
            //  style.setProperty(Property.SCROLL_MARGIN_BLOCK_START, parseUnitValue(cssModel.getScrollMarginBlockStart()));
        }
        if (cssModel.getScrollMarginBlockEnd() != null) {
            // style.setProperty(Property.SCROLL_MARGIN_BLOCK_END, parseUnitValue(cssModel.getScrollMarginBlockEnd()));
        }
        if (cssModel.getScrollMarginInline() != null) {
            String[] inlineMargins = cssModel.getScrollMarginInline().split(" ");
            // style.setProperty(Property.SCROLL_MARGIN_INLINE_START, parseUnitValue(inlineMargins[0]));
            if (inlineMargins.length > 1) {
                //  style.setProperty(Property.SCROLL_MARGIN_INLINE_END, parseUnitValue(inlineMargins[1]));
            } else {
                //  style.setProperty(Property.SCROLL_MARGIN_INLINE_END, parseUnitValue(inlineMargins[0]));
            }
        }
        if (cssModel.getScrollMarginInlineStart() != null) {
            //  style.setProperty(Property.SCROLL_MARGIN_INLINE_START, parseUnitValue(cssModel.getScrollMarginInlineStart()));
        }
        if (cssModel.getScrollMarginInlineEnd() != null) {
            //  style.setProperty(Property.SCROLL_MARGIN_INLINE_END, parseUnitValue(cssModel.getScrollMarginInlineEnd()));
        }
    }

    private static void applyScrollPaddingProperties(JCSSPropertiesSModel cssModel, Style style) {
        // Shorthand scroll-padding
        if (cssModel.getScrollPadding() != null) {
            String[] paddings = cssModel.getScrollPadding().split(" ");
            switch (paddings.length) {
                case 1:
                    UnitValue padding = parseUnitValue(paddings[0]);
//                    style.setProperty(Property.SCROLL_PADDING_TOP, padding);
//                    style.setProperty(Property.SCROLL_PADDING_RIGHT, padding);
//                    style.setProperty(Property.SCROLL_PADDING_BOTTOM, padding);
//                    style.setProperty(Property.SCROLL_PADDING_LEFT, padding);
                    break;
                case 2:
                    UnitValue vertical = parseUnitValue(paddings[0]);
                    UnitValue horizontal = parseUnitValue(paddings[1]);
//                    style.setProperty(Property.SCROLL_PADDING_TOP, vertical);
//                    style.setProperty(Property.SCROLL_PADDING_BOTTOM, vertical);
//                    style.setProperty(Property.SCROLL_PADDING_LEFT, horizontal);
//                    style.setProperty(Property.SCROLL_PADDING_RIGHT, horizontal);
                    break;
                case 3:
//                    style.setProperty(Property.SCROLL_PADDING_TOP, parseUnitValue(paddings[0]));
//                    style.setProperty(Property.SCROLL_PADDING_LEFT, parseUnitValue(paddings[1]));
//                    style.setProperty(Property.SCROLL_PADDING_RIGHT, parseUnitValue(paddings[1]));
//                    style.setProperty(Property.SCROLL_PADDING_BOTTOM, parseUnitValue(paddings[2]));
                    break;
                case 4:
//                    style.setProperty(Property.SCROLL_PADDING_TOP, parseUnitValue(paddings[0]));
//                    style.setProperty(Property.SCROLL_PADDING_RIGHT, parseUnitValue(paddings[1]));
//                    style.setProperty(Property.SCROLL_PADDING_BOTTOM, parseUnitValue(paddings[2]));
//                    style.setProperty(Property.SCROLL_PADDING_LEFT, parseUnitValue(paddings[3]));
                    break;
            }
        }

        // Individual scroll padding properties
        if (cssModel.getScrollPaddingTop() != null) {
            //   style.setProperty(Property.SCROLL_PADDING_TOP, parseUnitValue(cssModel.getScrollPaddingTop()));
        }
        if (cssModel.getScrollPaddingRight() != null) {
            //     style.setProperty(Property.SCROLL_PADDING_RIGHT, parseUnitValue(cssModel.getScrollPaddingRight()));
        }
        if (cssModel.getScrollPaddingBottom() != null) {
            //    style.setProperty(Property.SCROLL_PADDING_BOTTOM, parseUnitValue(cssModel.getScrollPaddingBottom()));
        }
        if (cssModel.getScrollPaddingLeft() != null) {
            //   style.setProperty(Property.SCROLL_PADDING_LEFT, parseUnitValue(cssModel.getScrollPaddingLeft()));
        }
        if (cssModel.getScrollPaddingBlock() != null) {
            String[] blockPaddings = cssModel.getScrollPaddingBlock().split(" ");
            //  style.setProperty(Property.SCROLL_PADDING_BLOCK_START, parseUnitValue(blockPaddings[0]));
            if (blockPaddings.length > 1) {
                //       style.setProperty(Property.SCROLL_PADDING_BLOCK_END, parseUnitValue(blockPaddings[1]));
            } else {
                //       style.setProperty(Property.SCROLL_PADDING_BLOCK_END, parseUnitValue(blockPaddings[0]));
            }
        }
        if (cssModel.getScrollPaddingBlockStart() != null) {
            //   style.setProperty(Property.SCROLL_PADDING_BLOCK_START, parseUnitValue(cssModel.getScrollPaddingBlockStart()));
        }
        if (cssModel.getScrollPaddingBlockEnd() != null) {
            //   style.setProperty(Property.SCROLL_PADDING_BLOCK_END, parseUnitValue(cssModel.getScrollPaddingBlockEnd()));
        }
        if (cssModel.getScrollPaddingInline() != null) {
            String[] inlinePaddings = cssModel.getScrollPaddingInline().split(" ");
            //    style.setProperty(Property.SCROLL_PADDING_INLINE_START, parseUnitValue(inlinePaddings[0]));
            if (inlinePaddings.length > 1) {
                //  style.setProperty(Property.SCROLL_PADDING_INLINE_END, parseUnitValue(inlinePaddings[1]));
            } else {
                //   style.setProperty(Property.SCROLL_PADDING_INLINE_END, parseUnitValue(inlinePaddings[0]));
            }
        }
        if (cssModel.getScrollPaddingInlineStart() != null) {
            //    style.setProperty(Property.SCROLL_PADDING_INLINE_START, parseUnitValue(cssModel.getScrollPaddingInlineStart()));
        }
        if (cssModel.getScrollPaddingInlineEnd() != null) {
            //  style.setProperty(Property.SCROLL_PADDING_INLINE_END, parseUnitValue(cssModel.getScrollPaddingInlineEnd()));
        }
    }

    private static void applyScrollSnapProperties(JCSSPropertiesSModel cssModel, Style style) {
        if (cssModel.getScrollSnapAlign() != null) {
            //  style.setProperty(Property.SCROLL_SNAP_ALIGN, cssModel.getScrollSnapAlign());
        }
        if (cssModel.getScrollSnapStop() != null) {
            //    style.setProperty(Property.SCROLL_SNAP_STOP, cssModel.getScrollSnapStop());
        }
        if (cssModel.getScrollSnapType() != null) {
            //  style.setProperty(Property.SCROLL_SNAP_TYPE, cssModel.getScrollSnapType());
        }
    }



}