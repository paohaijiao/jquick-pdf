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
package com.github.paohaijiao.css.h.model;

import com.github.paohaijiao.css.g.model.JCSSPropertiesGModel;

import java.util.Arrays;

/**
 * packageName com.github.paohaijiao.model.css
 *
 * @author Martin
 * @version 1.0.0
 * @className CSSPropertiesBModel
 * @date 2025/6/29
 * @description
 */
public class JCSSPropertiesHModel extends JCSSPropertiesGModel {
    public static final String HANGING_PUNCTUATION = "hanging-punctuation";
    public static final String HEIGHT = "height";
    public static final String HYPHENS = "hyphens";
    public static final String HYPHENATE_CHARACTER = "hyphenate-character";

    /**
     * @param value none | first | last | allow-end | force-end
     */
    public void setHangingPunctuation(String value) {
        if (isValidHangingPunctuation(value)) {
            put(HANGING_PUNCTUATION, value);
        } else {
            throw new IllegalArgumentException("Invalid hanging-punctuation value: " + value);
        }
    }

    public String getHangingPunctuation() {
        return get(HANGING_PUNCTUATION);
    }

    /**
     * @param value auto | <length> | <percentage> | min-content | max-content | fit-content
     */
    public void setHeight(String value) {
        put(HEIGHT, value);
    }

    public String getHeight() {
        return get(HEIGHT);
    }

    public void setHyphens(String value) {
        if (Arrays.asList("none", "manual", "auto").contains(value)) {
            put(HYPHENS, value);
        } else {
            throw new IllegalArgumentException("Invalid hyphens value: " + value);
        }
    }

    public String getHyphens() {
        return get(HYPHENS);
    }


    public void setHyphenateCharacter(String value) {
        if ("auto".equals(value) || (value != null && value.length() == 1)) {
            put(HYPHENATE_CHARACTER, value);
        } else {
            throw new IllegalArgumentException("hyphenate-character must be a single character or 'auto'");
        }
    }

    public String getHyphenateCharacter() {
        return get(HYPHENATE_CHARACTER);
    }

    private boolean isValidHangingPunctuation(String value) {
        return Arrays.asList("none", "first", "last", "allow-end", "force-end")
                .contains(value);
    }

}
