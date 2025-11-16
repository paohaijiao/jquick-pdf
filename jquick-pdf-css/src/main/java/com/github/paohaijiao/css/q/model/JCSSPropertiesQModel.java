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
package com.github.paohaijiao.css.q.model;

import com.github.paohaijiao.css.p.model.JCSSPropertiesPModel;

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
public class JCSSPropertiesQModel extends JCSSPropertiesPModel {
    public static final String QUOTES = "quotes";

    private static final List<String> VALID_QUOTES_VALUES = Arrays.asList(
            "none", "auto", "initial", "inherit"
    );

    /**
     * Gets the current quotes value
     *
     * @return The current quotes value
     */
    public String getQuotes() {
        return get(QUOTES);
    }

    /**
     * Sets how quotation marks are rendered
     *
     * @param value Allowed values: none | auto | initial | inherit | string pairs
     * @throws IllegalArgumentException if value is invalid
     */
    public void setQuotes(String value) {
        if (VALID_QUOTES_VALUES.contains(value) || isValidQuotesString(value)) {
            put(QUOTES, value);
        } else {
            throw new IllegalArgumentException("Invalid quotes value: " + value);
        }
    }

    /**
     * Helper method to validate quotes string values
     *
     * @param value The value to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidQuotesString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        try {
            String[] parts = value.split(" ");
            if (parts.length % 2 != 0) {
                return false;
            }

            for (String part : parts) {
                if (!part.startsWith("\"") || !part.endsWith("\"")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
