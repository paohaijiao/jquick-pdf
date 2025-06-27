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
package com.github.paohaijiao.util;

import com.github.paohaijiao.model.JStyleCssModel;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.layout.IPropertyContainer;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.properties.Background;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
/**
 * packageName com.github.paohaijiao.css
 *
 * @author Martin
 * @version 1.0.0
 * @className ITextStyleUtils
 * @date 2025/6/24
 * @description
 */
public class ITextStyleUtils {
    private static final Map<String, Integer> FONT_WEIGHT_MAP = new HashMap<>();
    // 字体样式映射
    private static final Map<String, Integer> FONT_STYLE_MAP = new HashMap<>();
    // 文本对齐映射
    private static final Map<String, TextAlignment> TEXT_ALIGN_MAP = new HashMap<>();
    // 垂直对齐映射
    private static final Map<String, VerticalAlignment> VERTICAL_ALIGN_MAP = new HashMap<>();

    static {
        // 初始化字体权重映射
        FONT_WEIGHT_MAP.put("normal", 400);
        FONT_WEIGHT_MAP.put("bold", 700);
        FONT_WEIGHT_MAP.put("bolder", 900);
        FONT_WEIGHT_MAP.put("lighter", 300);
        for (int i = 1; i <= 9; i++) {
            FONT_WEIGHT_MAP.put(Integer.toString(i * 100), i * 100);
        }

        // 初始化字体样式映射
        FONT_STYLE_MAP.put("normal", 0);
        FONT_STYLE_MAP.put("italic", 1);
        FONT_STYLE_MAP.put("oblique", 2);

        // 初始化文本对齐映射
        TEXT_ALIGN_MAP.put("left", TextAlignment.LEFT);
        TEXT_ALIGN_MAP.put("right", TextAlignment.RIGHT);
        TEXT_ALIGN_MAP.put("center", TextAlignment.CENTER);
      //  TEXT_ALIGN_MAP.put("justify", TextAlignment.JUSTIFY);

        // 初始化垂直对齐映射
        VERTICAL_ALIGN_MAP.put("top", VerticalAlignment.TOP);
        VERTICAL_ALIGN_MAP.put("middle", VerticalAlignment.MIDDLE);
        VERTICAL_ALIGN_MAP.put("bottom", VerticalAlignment.BOTTOM);
    }

    /**
     * 应用样式到iText元素
     */
    public static void applyStyle(IPropertyContainer element, JStyleCssModel style) {
        if (style == null || element == null) {
            return;
        }
        applyFontStyles(element, style);
        applyBackground(element, style);
        applyBorders(element, style);
        applySpacing(element, style);
        applyTextStyles(element, style);
        applyLayoutStyles(element, style);
    }

    private static void applyFontStyles(IPropertyContainer element, JStyleCssModel style) {
        if (style.getFontFamily() != null) {
            element.setProperty(Property.FONT, style.getFontFamily());
        }
        if (style.getFontSize() != null) {
            UnitValue fontSize = parseUnitValue(style.getFontSize());
            if (fontSize != null) {
                element.setProperty(Property.FONT_SIZE, fontSize);
            }
        }
        if (style.getFontWeight() != null) {
            Integer weight = FONT_WEIGHT_MAP.get(style.getFontWeight().toLowerCase());
            if (weight != null) {
                element.setProperty(Property.FONT_WEIGHT, weight);
            }
        }
        if (style.getFontStyle() != null) {
            Integer fontStyle = FONT_STYLE_MAP.get(style.getFontStyle().toLowerCase());
            if (fontStyle != null) {
                element.setProperty(Property.FONT_STYLE, fontStyle);
            }
        }
        if (style.getColor() != null) {
            Color color = parseColor(style.getColor());
            if (color != null) {
                element.setProperty(Property.FONT_COLOR, color);
            }
        }
    }

    private static void applyBackground(IPropertyContainer element, JStyleCssModel style) {
        if (style.getBackgroundColor() != null) {
            Color color = parseColor(style.getBackgroundColor());
            if (color != null) {
                element.setProperty(Property.BACKGROUND, new Background(color));
            }
        }
    }

    private static void applyBorders(IPropertyContainer element, JStyleCssModel style) {
        if (style.getBorder() != null) {
            Border border = parseBorder(style.getBorder());
            if (border != null) {
                element.setProperty(Property.BORDER, border);
            }
        } else {
            if (style.getBorderTop() != null) {
                Border border = parseBorder(style.getBorderTop());
                if (border != null) {
                    element.setProperty(Property.BORDER_TOP, border);
                }
            }
            if (style.getBorderRight() != null) {
                Border border = parseBorder(style.getBorderRight());
                if (border != null) {
                    element.setProperty(Property.BORDER_RIGHT, border);
                }
            }
            if (style.getBorderBottom() != null) {
                Border border = parseBorder(style.getBorderBottom());
                if (border != null) {
                    element.setProperty(Property.BORDER_BOTTOM, border);
                }
            }
            if (style.getBorderLeft() != null) {
                Border border = parseBorder(style.getBorderLeft());
                if (border != null) {
                    element.setProperty(Property.BORDER_LEFT, border);
                }
            }
        }
    }

    private static void applySpacing(IPropertyContainer element, JStyleCssModel style) {
        if (style.getMargin() != null) {
            UnitValue margin = parseUnitValue(style.getMargin());
            if (margin != null) {
             //   element.setProperty(Property.MARGIN, margin);
            }
        } else {
            if (style.getMarginTop() != null) {
                UnitValue margin = parseUnitValue(style.getMarginTop());
                if (margin != null) {
                    element.setProperty(Property.MARGIN_TOP, margin);
                }
            }
            if (style.getMarginRight() != null) {
                UnitValue margin = parseUnitValue(style.getMarginRight());
                if (margin != null) {
                    element.setProperty(Property.MARGIN_RIGHT, margin);
                }
            }
            if (style.getMarginBottom() != null) {
                UnitValue margin = parseUnitValue(style.getMarginBottom());
                if (margin != null) {
                    element.setProperty(Property.MARGIN_BOTTOM, margin);
                }
            }
            if (style.getMarginLeft() != null) {
                UnitValue margin = parseUnitValue(style.getMarginLeft());
                if (margin != null) {
                    element.setProperty(Property.MARGIN_LEFT, margin);
                }
            }
        }

        if (style.getPadding() != null) {
            UnitValue padding = parseUnitValue(style.getPadding());
            if (padding != null) {
              //  element.setProperty(Property.PADDING, padding);
            }
        } else {
            if (style.getPaddingTop() != null) {
                UnitValue padding = parseUnitValue(style.getPaddingTop());
                if (padding != null) {
                    element.setProperty(Property.PADDING_TOP, padding);
                }
            }
            if (style.getPaddingRight() != null) {
                UnitValue padding = parseUnitValue(style.getPaddingRight());
                if (padding != null) {
                    element.setProperty(Property.PADDING_RIGHT, padding);
                }
            }
            if (style.getPaddingBottom() != null) {
                UnitValue padding = parseUnitValue(style.getPaddingBottom());
                if (padding != null) {
                    element.setProperty(Property.PADDING_BOTTOM, padding);
                }
            }
            if (style.getPaddingLeft() != null) {
                UnitValue padding = parseUnitValue(style.getPaddingLeft());
                if (padding != null) {
                    element.setProperty(Property.PADDING_LEFT, padding);
                }
            }
        }
    }

    private static void applyTextStyles(IPropertyContainer element, JStyleCssModel style) {
        if (style.getTextAlign() != null) {
            TextAlignment alignment = TEXT_ALIGN_MAP.get(style.getTextAlign().toLowerCase());
            if (alignment != null) {
                //element.setProperty(Property.TEXT_ALIGN, alignment);
            }
        }

        if (style.getVerticalAlign() != null) {
            VerticalAlignment alignment = VERTICAL_ALIGN_MAP.get(style.getVerticalAlign().toLowerCase());
            if (alignment != null) {
                element.setProperty(Property.VERTICAL_ALIGNMENT, alignment);
            }
        }

        if (style.getLineHeight() != null) {
            UnitValue lineHeight = parseUnitValue(style.getLineHeight());
            if (lineHeight != null) {
                element.setProperty(Property.LINE_HEIGHT, lineHeight);
            }
        }

        if (style.getTextDecoration() != null) {
            if (style.getTextDecoration().contains("underline")) {
                element.setProperty(Property.UNDERLINE, true);
            }
            if (style.getTextDecoration().contains("line-through")) {
                //element.setProperty(Property.STRIKE_THROUGH, true);
            }
        }
    }

    private static void applyLayoutStyles(IPropertyContainer element, JStyleCssModel style) {
        if (style.getWidth() != null) {
            UnitValue width = parseUnitValue(style.getWidth());
            if (width != null) {
                element.setProperty(Property.WIDTH, width);
            }
        }
        if (style.getHeight() != null) {
            UnitValue height = parseUnitValue(style.getHeight());
            if (height != null) {
                element.setProperty(Property.HEIGHT, height);
            }
        }
        if (style.getMinHeight() != null) {
            UnitValue minHeight = parseUnitValue(style.getMinHeight());
            if (minHeight != null) {
                element.setProperty(Property.MIN_HEIGHT, minHeight);
            }
        }

        if (style.getDisplay() != null) {
            if ("none".equalsIgnoreCase(style.getDisplay())) {
                //element.setProperty(Property.VISIBILITY, false);
            }
        }

        if (style.getPosition() != null) {
            if ("absolute".equalsIgnoreCase(style.getPosition())) {
         //       element.setProperty(Property.POSITION, com.itextpdf.layout.properties.Property.PositionProperty.ABSOLUTE);
            } else if ("fixed".equalsIgnoreCase(style.getPosition())) {
        //        element.setProperty(Property.POSITION, com.itextpdf.layout.properties.Property.PositionProperty.FIXED);
            }
        }
    }

    /**
     * 解析CSS单位值
     */
    private static UnitValue parseUnitValue(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        value = value.trim().toLowerCase();

        try {
            if (value.endsWith("px")) {
                float pxValue = Float.parseFloat(value.substring(0, value.length() - 2));
                return UnitValue.createPointValue(pxValue);
            } else if (value.endsWith("pt")) {
                float ptValue = Float.parseFloat(value.substring(0, value.length() - 2));
                return UnitValue.createPointValue(ptValue);
            } else if (value.endsWith("%")) {
                float percentValue = Float.parseFloat(value.substring(0, value.length() - 1));
                return UnitValue.createPercentValue(percentValue);
            } else if (value.equals("auto")) {
                return null; // iText-core中auto通常表示为null
            } else {
                float floatValue = Float.parseFloat(value);
                return UnitValue.createPointValue(floatValue);
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 解析颜色值
     */
    private static Color parseColor(String color) {
        if (color == null || color.isEmpty()) {
            return null;
        }

        try {
            return WebColors.getRGBColor(color);
        } catch (Exception e) {
            // 如果WebColors无法解析，尝试其他格式
            if (color.startsWith("#")) {
                try {
                    return new DeviceRgb(
                            Integer.parseInt(color.substring(1, 3), 16),
                            Integer.parseInt(color.substring(3, 5), 16),
                            Integer.parseInt(color.substring(5, 7), 16)
                    );
                } catch (Exception ex) {
                    return null;
                }
            } else if (color.startsWith("rgb(")) {
                try {
                    String[] parts = color.substring(4, color.length() - 1).split(",");
                    return new DeviceRgb(
                            Integer.parseInt(parts[0].trim()),
                            Integer.parseInt(parts[1].trim()),
                            Integer.parseInt(parts[2].trim())
                    );
                } catch (Exception ex) {
                    return null;
                }
            }
            return null;
        }
    }

    /**
     * 解析边框
     */
    private static Border parseBorder(String border) {
        if (border == null || border.isEmpty()) {
            return null;
        }

        String[] parts = border.split("\\s+");
        if (parts.length >= 3) {
            try {
                float width = Float.parseFloat(parts[0].replace("px", ""));
                Color color = parseColor(parts[2]);
                if (color != null) {
                    return new SolidBorder(color, width);
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 合并两个样式
     */
    public static JStyleCssModel mergeStyles(JStyleCssModel base, JStyleCssModel override) {
        if (base == null) return override;
        if (override == null) return base;

        JStyleCssModel result = new JStyleCssModel();
        Field[] fields = JStyleCssModel.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object overrideValue = field.get(override);
                if (overrideValue != null) {
                    field.set(result, overrideValue);
                } else {
                    Object baseValue = field.get(base);
                    field.set(result, baseValue);
                }
            } catch (IllegalAccessException e) {
            }
        }

        return result;
    }
}
