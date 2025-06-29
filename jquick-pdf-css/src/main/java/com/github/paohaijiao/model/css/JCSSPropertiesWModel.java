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
package com.github.paohaijiao.model.css;

import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.model.css
 *
 * @author Martin
 * @version 1.0.0
 * @className CSSPropertiesBModel
 * @date 2025/6/29
 * @description
 */
public class JCSSPropertiesWModel extends JCSSPropertiesVModel {
    public static final String WHITE_SPACE = "white-space";
    public static final String WIDOWS = "widows";
    public static final String WIDTH = "width";
    public static final String WORD_BREAK = "word-break";
    public static final String WORD_SPACING = "word-spacing";
    public static final String WORD_WRAP = "word-wrap";
    public static final String WRITING_MODE = "writing-mode";

    // Valid values lists
    private static final List<String> VALID_WHITE_SPACE_VALUES = Arrays.asList(
            "normal", "nowrap", "pre", "pre-wrap", "pre-line", "break-spaces", "initial", "inherit"
    );

    private static final List<String> VALID_WORD_BREAK_VALUES = Arrays.asList(
            "normal", "break-all", "keep-all", "break-word", "initial", "inherit"
    );

    private static final List<String> VALID_WORD_WRAP_VALUES = Arrays.asList(
            "normal", "break-word", "initial", "inherit"
    );

    private static final List<String> VALID_WRITING_MODE_VALUES = Arrays.asList(
            "horizontal-tb", "vertical-rl", "vertical-lr", "sideways-rl", "sideways-lr", "initial", "inherit"
    );


    /**
     * Controls text wrapping like a strict English teacher
     */
    public void setTextWrappingDiscipline(String value) {
        if (VALID_WHITE_SPACE_VALUES.contains(value)) {
            put(WHITE_SPACE, value);
        } else {
            throw new IllegalArgumentException("Invalid white-space value: " + value);
        }
    }

    /**
     * Sets minimum widow lines (prevents lonely lines at top of page)
     */
    public void preventLonelyWidows(int minLines) {
        put(WIDOWS, String.valueOf(minLines));
    }

    /**
     * Makes elements grow horizontally like a bodybuilder
     */
    public void pumpUpWidth(String size) {
        put(WIDTH, size);
    }

    /**
     * Breaks words like a karate master
     */
    public void wordBreakingChop(String mode) {
        if (VALID_WORD_BREAK_VALUES.contains(mode)) {
            put(WORD_BREAK, mode);
        } else {
            throw new IllegalArgumentException("Invalid word-break value: " + mode);
        }
    }

    /**
     * Adjusts space between words like a typography DJ
     */
    public void wordSpacingDJ(String spacing) {
        put(WORD_SPACING, spacing);
    }

    /**
     * Wraps words like a gift shop attendant
     */
    public void giftWrapWords(String mode) {
        if (VALID_WORD_WRAP_VALUES.contains(mode)) {
            put(WORD_WRAP, mode);
        } else {
            throw new IllegalArgumentException("Invalid word-wrap value: " + mode);
        }
    }

    /**
     * Changes writing direction like an ambidextrous calligrapher
     */
    public void ambidextrousWriting(String mode) {
        if (VALID_WRITING_MODE_VALUES.contains(mode)) {
            put(WRITING_MODE, mode);
        } else {
            throw new IllegalArgumentException("Invalid writing-mode value: " + mode);
        }
    }


    public void setWhiteSpace(String value) {
        if (VALID_WHITE_SPACE_VALUES.contains(value)) {
            put(WHITE_SPACE, value);
        } else {
            throw new IllegalArgumentException("Invalid white-space value: " + value);
        }
    }

    public void setWidows(int value) {
        if (value >= 1) {
            put(WIDOWS, String.valueOf(value));
        } else {
            throw new IllegalArgumentException("Widows value must be positive");
        }
    }

    public void setWidth(String value) {
        if (isValidSizeValue(value)) {
            put(WIDTH, value);
        } else {
            throw new IllegalArgumentException("Invalid width value: " + value);
        }
    }

    public void setWordBreak(String value) {
        if (VALID_WORD_BREAK_VALUES.contains(value)) {
            put(WORD_BREAK, value);
        } else {
            throw new IllegalArgumentException("Invalid word-break value: " + value);
        }
    }

    public void setWordSpacing(String value) {
        put(WORD_SPACING, value);
    }

    public void setWordWrap(String value) {
        if (VALID_WORD_WRAP_VALUES.contains(value)) {
            put(WORD_WRAP, value);
        } else {
            throw new IllegalArgumentException("Invalid word-wrap value: " + value);
        }
    }

    public void setWritingMode(String value) {
        if (VALID_WRITING_MODE_VALUES.contains(value)) {
            put(WRITING_MODE, value);
        } else {
            throw new IllegalArgumentException("Invalid writing-mode value: " + value);
        }
    }


    public String getWhiteSpace() {
        return get(WHITE_SPACE);
    }

    public String getWidows() {
        return get(WIDOWS);
    }

    public String getWidth() {
        return get(WIDTH);
    }

    public String getWordBreak() {
        return get(WORD_BREAK);
    }

    public String getWordSpacing() {
        return get(WORD_SPACING);
    }

    public String getWordWrap() {
        return get(WORD_WRAP);
    }

    public String getWritingMode() {
        return get(WRITING_MODE);
    }

    private boolean isValidSizeValue(String value) {
        return value != null && (value.equals("auto") ||
                value.matches("^\\d+(\\.\\d+)?(px|em|rem|%|vw|vh|vmin|vmax|cm|mm|in|pt|pc)$"));
    }

}
