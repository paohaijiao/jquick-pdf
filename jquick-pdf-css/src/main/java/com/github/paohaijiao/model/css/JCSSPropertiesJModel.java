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
public class JCSSPropertiesJModel extends JCSSPropertiesIModel {

    public static final String JUSTIFY_CONTENT = "justify-content";
    public static final String JUSTIFY_ITEMS = "justify-items";
    public static final String JUSTIFY_SELF = "justify-self";


    private static final List<String> VALID_JUSTIFY_CONTENT = Arrays.asList(
            "normal", "flex-start", "flex-end", "center", "space-between",
            "space-around", "space-evenly", "stretch", "start", "end",
            "left", "right", "safe", "unsafe"
    );

    private static final List<String> VALID_JUSTIFY_ITEMS = Arrays.asList(
            "normal", "stretch", "flex-start", "flex-end", "center",
            "start", "end", "self-start", "self-end", "left", "right",
            "baseline", "first baseline", "last baseline", "safe", "unsafe"
    );

    private static final List<String> VALID_JUSTIFY_SELF = Arrays.asList(
            "auto", "normal", "stretch", "flex-start", "flex-end", "center",
            "start", "end", "self-start", "self-end", "left", "right",
            "baseline", "first baseline", "last baseline", "safe", "unsafe"
    );


    /**
     * Sets main axis alignment (for Flexbox/Grid)
     *
     * @param value Allowed values: flex-start | flex-end | center | space-between | space-around | space-evenly
     * @throws IllegalArgumentException if value is invalid
     */
    public void setJustifyContent(String value) {
        if (isValidJustifyValue(value, VALID_JUSTIFY_CONTENT)) {
            put(JUSTIFY_CONTENT, value);
        } else {
            throw new IllegalArgumentException("Invalid justify-content value: " + value);
        }
    }

    public String getJustifyContent() {
        return get(JUSTIFY_CONTENT);
    }

    /**
     * Sets default inline-axis alignment for all items (mainly for Grid)
     *
     * @param value Allowed values: start | end | center | stretch | baseline
     * @throws IllegalArgumentException if value is invalid
     */
    public void setJustifyItems(String value) {
        if (isValidJustifyValue(value, VALID_JUSTIFY_ITEMS)) {
            put(JUSTIFY_ITEMS, value);
        } else {
            throw new IllegalArgumentException("Invalid justify-items value: " + value);
        }
    }

    public String getJustifyItems() {
        return get(JUSTIFY_ITEMS);
    }

    /**
     * Sets inline-axis alignment for individual items (overrides justify-items)
     *
     * @param value Allowed values: auto | start | end | center | stretch | baseline
     * @throws IllegalArgumentException if value is invalid
     */
    public void setJustifySelf(String value) {
        if (isValidJustifyValue(value, VALID_JUSTIFY_SELF)) {
            put(JUSTIFY_SELF, value);
        } else {
            throw new IllegalArgumentException("Invalid justify-self value: " + value);
        }
    }

    public String getJustifySelf() {
        return get(JUSTIFY_SELF);
    }


    private boolean isValidJustifyValue(String value, List<String> validValues) {
        if (value == null) return false;
        String normalizedValue = value.replaceAll("^safe\\s+|^unsafe\\s+", "");
        return validValues.contains(normalizedValue) || validValues.contains(value);
    }
}
