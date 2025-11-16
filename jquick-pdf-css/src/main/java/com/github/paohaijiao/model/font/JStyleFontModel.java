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
package com.github.paohaijiao.model.font;

import java.util.HashMap;

/**
 * packageName com.github.paohaijiao.model.font
 *
 * @author Martin
 * @version 1.0.0
 * @className JStyleFontModel
 * @date 2025/6/29
 * @description
 */
public class JStyleFontModel extends HashMap<String, String> {
    public static final String FONT_SIZE = "font-size";
    public static final String FONT_FAMILY = "font-family";
    public static final String FONT_STYLE = "font-style";
    public static final String FONT_WEIGHT = "font-weight";

    public static final String FONT_VARIANT = "font-variant";
    public static final String LINE_HEIGHT = "line-height";
    public static final String LETTER_SPACING = "letter-spacing";
    public static final String TEXT_DECORATION = "text-decoration";
    public static final String TEXT_TRANSFORM = "text-transform";
    public static final String COLOR = "color";
    public static final String TEXT_ALIGN = "text-align";
    public static final String TEXT_SHADOW = "text-shadow";

    public String getFontWeight() {
        return get(FONT_WEIGHT);
    }

    public void setFontWeight(String fontWeight) {
        put(FONT_WEIGHT, fontWeight);
    }

    public String getFontStyle() {
        return get(FONT_STYLE);
    }

    public void setFontStyle(String fontStyle) {
        put(FONT_STYLE, fontStyle);
    }

    public String getFontFamily() {
        return get(FONT_FAMILY);
    }

    public void setFontFamily(String fontFamily) {
        put(FONT_FAMILY, fontFamily);
    }

    public String getFontVariant() {
        return get(FONT_VARIANT);
    }

    public void setFontVariant(String fontVariant) {
        put(FONT_VARIANT, fontVariant);
    }

    public String getLineHeight() {
        return get(LINE_HEIGHT);
    }

    public void setLineHeight(String lineHeight) {
        put(LINE_HEIGHT, lineHeight);
    }

    public String getLetterSpacing() {
        return get(LETTER_SPACING);
    }

    public void setLetterSpacing(String letterSpacing) {
        put(LETTER_SPACING, letterSpacing);
    }

    public String getTextDecoration() {
        return get(TEXT_DECORATION);
    }

    public void setTextDecoration(String textDecoration) {
        put(TEXT_DECORATION, textDecoration);
    }

    public String getTextTransform() {
        return get(TEXT_TRANSFORM);
    }

    public void setTextTransform(String textTransform) {
        put(TEXT_TRANSFORM, textTransform);
    }

    public String getColor() {
        return get(COLOR);
    }

    public void setColor(String color) {
        put(COLOR, color);
    }

    public String getTextAlign() {
        return get(TEXT_ALIGN);
    }

    public void setTextAlign(String textAlign) {
        put(TEXT_ALIGN, textAlign);
    }

    public String getTextShadow() {
        return get(TEXT_SHADOW);
    }

    public void setTextShadow(String textShadow) {
        put(TEXT_SHADOW, textShadow);
    }
}
