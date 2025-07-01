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

import com.github.paohaijiao.model.css.JCSSPropertiesMModel;
import com.itextpdf.layout.properties.*;

import javax.swing.text.Style;
import java.util.HashMap;
import java.util.Map;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesMProvider extends JCSSPropertiesBaseProvider {
    private static final Map<String, BlendMode> BLEND_MODE_MAP = new HashMap<>();

    static {
        BLEND_MODE_MAP.put("normal", BlendMode.NORMAL);
        BLEND_MODE_MAP.put("multiply", BlendMode.MULTIPLY);
        BLEND_MODE_MAP.put("screen", BlendMode.SCREEN);
        BLEND_MODE_MAP.put("overlay", BlendMode.OVERLAY);
        BLEND_MODE_MAP.put("darken", BlendMode.DARKEN);
        BLEND_MODE_MAP.put("lighten", BlendMode.LIGHTEN);
        BLEND_MODE_MAP.put("color-dodge", BlendMode.COLOR_DODGE);
        BLEND_MODE_MAP.put("color-burn", BlendMode.COLOR_BURN);
        BLEND_MODE_MAP.put("hard-light", BlendMode.HARD_LIGHT);
        BLEND_MODE_MAP.put("soft-light", BlendMode.SOFT_LIGHT);
        BLEND_MODE_MAP.put("difference", BlendMode.DIFFERENCE);
        BLEND_MODE_MAP.put("exclusion", BlendMode.EXCLUSION);
        BLEND_MODE_MAP.put("hue", BlendMode.HUE);
        BLEND_MODE_MAP.put("saturation", BlendMode.SATURATION);
        BLEND_MODE_MAP.put("color", BlendMode.COLOR);
        BLEND_MODE_MAP.put("luminosity", BlendMode.LUMINOSITY);
    }

    public static void applyMStyles(Style style, JCSSPropertiesMModel css) {
        applyMarginProperties(style, css);
        applyMaskProperties(style, css);
        applySizeProperties(style, css);
        applyBlendMode(style, css);
    }

    private static void applyMarginProperties(Style style, JCSSPropertiesMModel css) {
        if (css.getMargin() != null) {
            // style.setMargin(parseUnitValue(css.getMargin()));
        }
        if (css.getMarginTop() != null) {
            //  style.setMarginTop(parseUnitValue(css.getMarginTop()));
        }
        if (css.getMarginRight() != null) {
            //   style.setMarginRight(parseUnitValue(css.getMarginRight()));
        }
        if (css.getMarginBottom() != null) {
            //  style.setMarginBottom(parseUnitValue(css.getMarginBottom()));
        }
        if (css.getMarginLeft() != null) {
            // style.setMarginLeft(parseUnitValue(css.getMarginLeft()));
        }

        // Block and inline margins (convert to top/bottom/left/right as needed)
        if (css.getMarginBlockStart() != null) {
            //  style.setMarginTop(parseUnitValue(css.getMarginBlockStart()));
        }
        if (css.getMarginBlockEnd() != null) {
            // style.setMarginBottom(parseUnitValue(css.getMarginBlockEnd()));
        }
        if (css.getMarginInlineStart() != null) {
            //  style.setMarginLeft(parseUnitValue(css.getMarginInlineStart()));
        }
        if (css.getMarginInlineEnd() != null) {
            // style.setMarginRight(parseUnitValue(css.getMarginInlineEnd()));
        }
    }

    private static void applyMaskProperties(Style style, JCSSPropertiesMModel css) {
        // iText has limited support for masking, but we can implement some features
        if (css.getMaskImage() != null) {
            // Create a background image with masking properties
            BackgroundImage bgImage = new BackgroundImage.Builder()
//                    .setImage(css.getMaskImage())
                    // .setBackgroundRepeat(parseBackgroundRepeat(css.getMaskRepeat()))
                    //  .setBackgroundPosition(parseBackgroundPosition(css.getMaskPosition()))
                    //  .setBackgroundSize(parseBackgroundSize(css.getMaskSize()))
                    .build();
            //   style.setBackgroundImage(bgImage);
        }
    }

    private static void applySizeProperties(Style style, JCSSPropertiesMModel css) {
        if (css.getMaxWidth() != null) {
            //  style.setMaxWidth(parseUnitValue(css.getMaxWidth()));
        }
        if (css.getMaxHeight() != null) {
            //    style.setMaxHeight(parseUnitValue(css.getMaxHeight()));
        }
        if (css.getMinWidth() != null) {
            //   style.setMinWidth(parseUnitValue(css.getMinWidth()));
        }
        if (css.getMinHeight() != null) {
            //   style.setMinHeight(parseUnitValue(css.getMinHeight()));
        }

        // Block and inline sizes (map to width/height as needed)
        if (css.getMaxBlockSize() != null) {
            //  style.setMaxHeight(parseUnitValue(css.getMaxBlockSize()));
        }
        if (css.getMinBlockSize() != null) {
            //  style.setMinHeight(parseUnitValue(css.getMinBlockSize()));
        }
        if (css.getMaxInlineSize() != null) {
            //  style.setMaxWidth(parseUnitValue(css.getMaxInlineSize()));
        }
        if (css.getMinInlineSize() != null) {
            // style.setMinWidth(parseUnitValue(css.getMinInlineSize()));
        }
    }

    private static void applyBlendMode(Style style, JCSSPropertiesMModel css) {
        if (css.getMixBlendMode() != null) {
            BlendMode blendMode = BLEND_MODE_MAP.get(css.getMixBlendMode().toLowerCase());
            if (blendMode != null) {
                //    style.setBlendMode(blendMode);
            }
        }
    }


    private static BackgroundRepeat parseBackgroundRepeat(String value) {
        if (value == null) return null;

        switch (value) {
            case "repeat":
                return new BackgroundRepeat(BackgroundRepeat.BackgroundRepeatValue.REPEAT);
            case "repeat-x":
                //   return new BackgroundRepeat(BackgroundRepeat.BackgroundRepeatValue.REPEAT_X);
            case "repeat-y":
                //    return new BackgroundRepeat(BackgroundRepeat.BackgroundRepeatValue.REPEAT_Y);
            case "no-repeat":
                return new BackgroundRepeat(BackgroundRepeat.BackgroundRepeatValue.NO_REPEAT);
            case "space":
                return new BackgroundRepeat(BackgroundRepeat.BackgroundRepeatValue.SPACE);
            case "round":
                return new BackgroundRepeat(BackgroundRepeat.BackgroundRepeatValue.ROUND);
            default:
                return null;
        }
    }

    private static BackgroundPosition parseBackgroundPosition(String value) {
        if (value == null) return null;

        // Simple implementation - can be enhanced for more complex cases
        if (value.contains(" ")) {
            String[] parts = value.split(" ");
            if (parts.length == 2) {
//                return new BackgroundPosition()
//                        .setPositionX(parsePositionValue(parts[0]))
//                        .setPositionY(parsePositionValue(parts[1]));
            }
        }
        return null;
        //return new BackgroundPosition().setPositionX(parsePositionValue(value));
    }

//    private static BackgroundPosition.PositionValue parsePositionValue(String value) {
//        if (value.equals("left") || value.equals("top")) {
//            return BackgroundPosition.PositionValue.LEFT_OR_TOP;
//        } else if (value.equals("right") || value.equals("bottom")) {
//            return BackgroundPosition.PositionValue.RIGHT_OR_BOTTOM;
//        } else if (value.equals("center")) {
//            return BackgroundPosition.PositionValue.CENTER;
//        } else {
//            // Try to parse as percentage or length
//            if (value.endsWith("%")) {
//                float percent = Float.parseFloat(value.substring(0, value.length() - 1));
//                return new BackgroundPosition.PositionValue(percent / 100);
//            } else if (value.endsWith("px")) {
//                float px = Float.parseFloat(value.substring(0, value.length() - 2));
//                // Convert pixels to points (1px = 0.75pt in CSS)
//                return new BackgroundPosition.PositionValue(px * 0.75f);
//            } else {
//                return BackgroundPosition.PositionValue.CENTER;
//            }
//        }
//    }

//    private static BackgroundSize parseBackgroundSize(String value) {
//        if (value == null) return null;
//
//        if (value.equals("cover")) {
//            return BackgroundSize.COVER;
//        } else if (value.equals("contain")) {
//            return BackgroundSize.CONTAIN;
//        } else if (value.contains(" ")) {
//            String[] parts = value.split(" ");
//            if (parts.length == 2) {
//                return new BackgroundSize()
//                        .setBackgroundSizeToValues(
//                                parseBackgroundSizeValue(parts[0]),
//                                parseBackgroundSizeValue(parts[1])
//                        );
//            }
//        }
//        return new BackgroundSize().setBackgroundSizeToValues(parseBackgroundSizeValue(value));
//    }

    private static UnitValue parseBackgroundSizeValue(String value) {
        if (value.equals("auto")) {
            return null; // auto is the default
        } else if (value.endsWith("%")) {
            float percent = Float.parseFloat(value.substring(0, value.length() - 1));
            return UnitValue.createPercentValue(percent);
        } else if (value.endsWith("px")) {
            float px = Float.parseFloat(value.substring(0, value.length() - 2));
            return UnitValue.createPointValue(px);
        } else {
            try {
                float val = Float.parseFloat(value);
                return UnitValue.createPointValue(val);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}