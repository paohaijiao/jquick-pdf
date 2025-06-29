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
public class JCSSPropertiesUModel extends JCSSPropertiesTModel {
    public static final String UNICODE_BIDI = "unicode-bidi";
    public static final String USER_SELECT = "user-select";

    // Valid values lists
    private static final List<String> VALID_UNICODE_BIDI_VALUES = Arrays.asList(
            "normal", "embed", "isolate", "bidi-override", "isolate-override", "plaintext", "initial", "inherit"
    );

    private static final List<String> VALID_USER_SELECT_VALUES = Arrays.asList(
            "auto", "none", "text", "contain", "all", "initial", "inherit"
    );


    /**
     * Makes your text go both ways like a bilingual chameleon
     *
     * @param mode Allowed values: normal | embed | isolate | bidi-override | etc.
     */
    public void setBilingualChameleonMode(String mode) {
        if (VALID_UNICODE_BIDI_VALUES.contains(mode)) {
            put(UNICODE_BIDI, mode);
        } else {
            throw new IllegalArgumentException("Invalid unicode-bidi value: " + mode);
        }
    }

    /**
     * Locks down text selection like Fort Knox or sets it free
     *
     * @param selection Allowed values: auto | none | text | contain | all
     */
    public void setTextSelectionFortKnox(String selection) {
        if (VALID_USER_SELECT_VALUES.contains(selection)) {
            put(USER_SELECT, selection);
        } else {
            throw new IllegalArgumentException("Invalid user-select value: " + selection);
        }
    }


    /**
     * Sets the unicode-bidi property for text direction handling
     *
     * @param value Allowed values: normal | embed | isolate | bidi-override | etc.
     */
    public void setUnicodeBidi(String value) {
        if (VALID_UNICODE_BIDI_VALUES.contains(value)) {
            put(UNICODE_BIDI, value);
        } else {
            throw new IllegalArgumentException("Invalid unicode-bidi value: " + value);
        }
    }

    /**
     * Controls text selection behavior
     *
     * @param value Allowed values: auto | none | text | contain | all
     */
    public void setUserSelect(String value) {
        if (VALID_USER_SELECT_VALUES.contains(value)) {
            put(USER_SELECT, value);
        } else {
            throw new IllegalArgumentException("Invalid user-select value: " + value);
        }
    }


    public String getUnicodeBidi() {
        return get(UNICODE_BIDI);
    }

    public String getUserSelect() {
        return get(USER_SELECT);
    }
}
