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

import com.itextpdf.layout.Style;
import com.itextpdf.layout.properties.Property;

import java.util.Arrays;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesHProvider extends JCSSPropertiesBaseProvider {
    public static Style applyHangingPunctuation(Style style, String value) {
        if (isValidHangingPunctuation(value)) {
            // style.setProperty(Property.HANGING_PUNCTUATION, value);
        } else {
            throw new IllegalArgumentException("Invalid hanging-punctuation value: " + value);
        }
        return style;
    }

    public static Style applyHeight(Style style, String value) {
        // iText handles height values automatically - it accepts length, percentage, and keywords
        style.setProperty(Property.HEIGHT, value);
        return style;
    }

    public static Style applyHyphens(Style style, String value) {
        if (Arrays.asList("none", "manual", "auto").contains(value)) {
            // style.setProperty(Property.HYPHENS, value);
        } else {
            throw new IllegalArgumentException("Invalid hyphens value: " + value);
        }
        return style;
    }

    public static Style applyHyphenateCharacter(Style style, String value) {
        if ("auto".equals(value) || (value != null && value.length() == 1)) {
            //   style.setProperty(Property.HYPHENATE_CHARACTER, value);
        } else {
            throw new IllegalArgumentException("hyphenate-character must be a single character or 'auto'");
        }
        return style;
    }

    private static boolean isValidHangingPunctuation(String value) {
        return Arrays.asList("none", "first", "last", "allow-end", "force-end")
                .contains(value);
    }

    public static Style applyAllProperties(Style style,
                                           String hangingPunctuation,
                                           String height,
                                           String hyphens,
                                           String hyphenateCharacter) {
        if (hangingPunctuation != null) {
            applyHangingPunctuation(style, hangingPunctuation);
        }
        if (height != null) {
            applyHeight(style, height);
        }
        if (hyphens != null) {
            applyHyphens(style, hyphens);
        }
        if (hyphenateCharacter != null) {
            applyHyphenateCharacter(style, hyphenateCharacter);
        }
        return style;
    }

}