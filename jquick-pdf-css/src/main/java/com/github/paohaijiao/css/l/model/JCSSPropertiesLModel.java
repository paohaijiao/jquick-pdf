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
package com.github.paohaijiao.css.l.model;

import com.github.paohaijiao.css.k.model.JCSSPropertiesKModel;

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
public class JCSSPropertiesLModel extends JCSSPropertiesKModel {

    public static final String LAYER = "@layer";
    public static final String LEFT = "left";
    public static final String LETTER_SPACING = "letter-spacing";
    public static final String LINE_HEIGHT = "line-height";
    public static final String LIST_STYLE = "list-style";
    public static final String LIST_STYLE_IMAGE = "list-style-image";
    public static final String LIST_STYLE_POSITION = "list-style-position";
    public static final String LIST_STYLE_TYPE = "list-style-type";


    private static final List<String> VALID_LIST_POSITIONS = Arrays.asList(
            "inside", "outside"
    );

    private static final List<String> VALID_LIST_TYPES = Arrays.asList(
            "disc", "circle", "square", "decimal", "decimal-leading-zero",
            "lower-roman", "upper-roman", "lower-greek", "lower-latin",
            "upper-latin", "armenian", "georgian", "none"
    );


    /**
     * Defines a cascade layer
     *
     * @param layerName Name of the layer (optional)
     * @param rules     CSS rules to include in the layer (optional)
     */
    public void setLayerRule(String layerName, String rules) {
        String key = LAYER + (layerName != null ? " " + layerName : "");
        put(key, rules != null ? rules : "");
    }

    public String getLayerRule(String layerName) {
        return get(LAYER + (layerName != null ? " " + layerName : ""));
    }

    /**
     * Sets left position property
     *
     * @param value Allowed values: auto | <length> | <percentage>
     */
    public void setLeft(String value) {
        put(LEFT, value);
    }

    public String getLeft() {
        return get(LEFT);
    }

    /**
     * Sets spacing between characters
     *
     * @param value Allowed values: normal | <length>
     */
    public void setLetterSpacing(String value) {
        put(LETTER_SPACING, value);
    }

    public String getLetterSpacing() {
        return get(LETTER_SPACING);
    }

    /**
     * Sets line height
     *
     * @param value Allowed values: normal | <number> | <length> | <percentage>
     */
    public void setLineHeight(String value) {
        put(LINE_HEIGHT, value);
    }

    public String getLineHeight() {
        return get(LINE_HEIGHT);
    }

    /**
     * Shorthand for list style properties
     *
     * @param value Combination of type, position, and image
     */
    public void setListStyle(String value) {
        put(LIST_STYLE, value);
    }

    public String getListStyle() {
        return get(LIST_STYLE);
    }

    /**
     * Sets list item marker image
     *
     * @param value Allowed values: none | <url>
     */
    public void setListStyleImage(String value) {
        put(LIST_STYLE_IMAGE, value);
    }

    public String getListStyleImage() {
        return get(LIST_STYLE_IMAGE);
    }

    /**
     * Sets position of list item markers
     *
     * @param value Allowed values: inside | outside
     * @throws IllegalArgumentException if value is invalid
     */
    public void setListStylePosition(String value) {
        if (VALID_LIST_POSITIONS.contains(value)) {
            put(LIST_STYLE_POSITION, value);
        } else {
            throw new IllegalArgumentException("Invalid list-style-position value: " + value);
        }
    }

    public String getListStylePosition() {
        return get(LIST_STYLE_POSITION);
    }

    /**
     * Sets type of list item markers
     *
     * @param value Allowed values: disc | circle | square | decimal | etc.
     * @throws IllegalArgumentException if value is invalid
     */
    public void setListStyleType(String value) {
        if (VALID_LIST_TYPES.contains(value)) {
            put(LIST_STYLE_TYPE, value);
        } else {
            throw new IllegalArgumentException("Invalid list-style-type value: " + value);
        }
    }

    public String getListStyleType() {
        return get(LIST_STYLE_TYPE);
    }

}
