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
public class JCSSPropertiesRModel extends JCSSPropertiesQModel {

    public static final String RESIZE = "resize";
    public static final String RIGHT = "right";
    public static final String ROTATE = "rotate";
    public static final String ROW_GAP = "row-gap";

    private static final List<String> VALID_RESIZE_VALUES = Arrays.asList(
            "none", "both", "horizontal", "vertical", "block", "inline", "initial", "inherit"
    );

    private static final List<String> VALID_ROTATE_VALUES = Arrays.asList(
            "none", "x", "y", "z", "initial", "inherit"
    );


    /**
     * Sets whether an element is resizable and in which directions
     *
     * @param value Allowed values: none | both | horizontal | vertical | block | inline | initial | inherit
     * @throws IllegalArgumentException if value is invalid
     */
    public void setResize(String value) {
        if (VALID_RESIZE_VALUES.contains(value)) {
            put(RESIZE, value);
        } else {
            throw new IllegalArgumentException("Invalid resize value: " + value);
        }
    }

    /**
     * Gets the current resize value
     *
     * @return The current resize value
     */
    public String getResize() {
        return get(RESIZE);
    }

    /**
     * Sets the right offset for positioned elements
     *
     * @param value Length (e.g., "10px", "50%"), auto, initial, or inherit
     */
    public void setRight(String value) {
        put(RIGHT, value);
    }

    /**
     * Gets the current right value
     *
     * @return The current right value
     */
    public String getRight() {
        return get(RIGHT);
    }


    /**
     * Sets the rotation of an element
     *
     * @param value Allowed values: none | x | y | z | initial | inherit | angle (e.g., "45deg", "1rad")
     * @throws IllegalArgumentException if value is invalid
     */
    public void setRotate(String value) {
        if (VALID_ROTATE_VALUES.contains(value) || isValidAngleValue(value)) {
            put(ROTATE, value);
        } else {
            throw new IllegalArgumentException("Invalid rotate value: " + value);
        }
    }

    /**
     * Gets the current rotate value
     *
     * @return The current rotate value
     */
    public String getRotate() {
        return get(ROTATE);
    }

    // Helper method to validate angle values
    private boolean isValidAngleValue(String value) {
        return value != null && (value.endsWith("deg") || value.endsWith("rad") || value.endsWith("grad") || value.endsWith("turn"));
    }


    /**
     * Sets the size of the gap between rows in a grid or flex layout
     *
     * @param value Length (e.g., "10px", "1em"), normal, initial, or inherit
     */
    public void setRowGap(String value) {
        put(ROW_GAP, value);
    }

    /**
     * Gets the current row-gap value
     *
     * @return The current row-gap value
     */
    public String getRowGap() {
        return get(ROW_GAP);
    }

}
