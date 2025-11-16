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
package com.github.paohaijiao.css.v.model;

import com.github.paohaijiao.css.u.model.JCSSPropertiesUModel;

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
public class JCSSPropertiesVModel extends JCSSPropertiesUModel {
    public static final String VERTICAL_ALIGN = "vertical-align";
    public static final String VISIBILITY = "visibility";

    private static final List<String> VALID_VERTICAL_ALIGN_VALUES = Arrays.asList(
            "baseline", "sub", "super", "text-top", "text-bottom",
            "middle", "top", "bottom", "initial", "inherit"
    );

    private static final List<String> VALID_VISIBILITY_VALUES = Arrays.asList(
            "visible", "hidden", "collapse", "initial", "inherit"
    );


    /**
     * Aligns elements vertically like a synchronized swim team
     *
     * @param alignment Allowed values: baseline | sub | super | text-top | etc.
     */
    public void setSynchronizedSwimAlignment(String alignment) {
        if (VALID_VERTICAL_ALIGN_VALUES.contains(alignment)) {
            put(VERTICAL_ALIGN, alignment);
        } else if (isValidLengthValue(alignment)) {
            put(VERTICAL_ALIGN, alignment); // Also accepts length/percentage values
        } else {
            throw new IllegalArgumentException("Invalid vertical-align value: " + alignment);
        }
    }

    /**
     * Makes elements play hide-and-seek like a pro
     *
     * @param mode Allowed values: visible | hidden | collapse
     */
    public void setHideAndSeekMaster(String mode) {
        if (VALID_VISIBILITY_VALUES.contains(mode)) {
            put(VISIBILITY, mode);
        } else {
            throw new IllegalArgumentException("Invalid visibility value: " + mode);
        }
    }

    public String getVerticalAlign() {
        return get(VERTICAL_ALIGN);
    }

    /**
     * Sets vertical alignment of an inline or table-cell element
     *
     * @param value Allowed values: baseline | sub | super | length | percentage
     */
    public void setVerticalAlign(String value) {
        if (VALID_VERTICAL_ALIGN_VALUES.contains(value) || isValidLengthValue(value)) {
            put(VERTICAL_ALIGN, value);
        } else {
            throw new IllegalArgumentException("Invalid vertical-align value: " + value);
        }
    }

    public String getVisibility() {
        return get(VISIBILITY);
    }

    /**
     * Sets element visibility
     *
     * @param value Allowed values: visible | hidden | collapse
     */
    public void setVisibility(String value) {
        if (VALID_VISIBILITY_VALUES.contains(value)) {
            put(VISIBILITY, value);
        } else {
            throw new IllegalArgumentException("Invalid visibility value: " + value);
        }
    }

    // Helper method (reused from previous implementations)
    private boolean isValidLengthValue(String value) {
        if (value == null) return false;
        return value.matches("^-?\\d+(\\.\\d+)?(px|em|rem|%|vw|vh|vmin|vmax|cm|mm|in|pt|pc)$");
    }
}
