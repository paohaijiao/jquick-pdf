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
package com.github.paohaijiao.css.p.model;

import com.github.paohaijiao.css.o.model.JCSSPropertiesOModel;

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
public class JCSSPropertiesPModel extends JCSSPropertiesOModel {
    public static final String PADDING = "padding";
    public static final String PADDING_BLOCK = "padding-block";
    public static final String PADDING_BLOCK_END = "padding-block-end";
    public static final String PADDING_BLOCK_START = "padding-block-start";
    public static final String PADDING_BOTTOM = "padding-bottom";
    public static final String PADDING_INLINE = "padding-inline";
    public static final String PADDING_INLINE_END = "padding-inline-end";
    public static final String PADDING_INLINE_START = "padding-inline-start";
    public static final String PADDING_LEFT = "padding-left";
    public static final String PADDING_RIGHT = "padding-right";
    public static final String PADDING_TOP = "padding-top";

    public static final String PAGE = "@page";
    public static final String PAGE_BREAK_AFTER = "page-break-after";
    public static final String PAGE_BREAK_BEFORE = "page-break-before";
    public static final String PAGE_BREAK_INSIDE = "page-break-inside";
    public static final String PAINT_ORDER = "paint-order";
    public static final String PERSPECTIVE = "perspective";
    public static final String PERSPECTIVE_ORIGIN = "perspective-origin";
    public static final String PLACE_CONTENT = "place-content";
    public static final String PLACE_ITEMS = "place-items";
    public static final String PLACE_SELF = "place-self";
    public static final String POINTER_EVENTS = "pointer-events";
    public static final String POSITION = "position";
    public static final String PROPERTY = "@property";
    private static final List<String> VALID_PAGE_BREAK_VALUES = Arrays.asList(
            "auto", "always", "avoid", "left", "right"
    );

    private static final List<String> VALID_PAINT_ORDER_VALUES = Arrays.asList(
            "normal", "fill", "stroke", "markers"
    );

    private static final List<String> VALID_POSITION_VALUES = Arrays.asList(
            "static", "relative", "absolute", "fixed", "sticky"
    );

    private static final List<String> VALID_POINTER_EVENTS_VALUES = Arrays.asList(
            "auto", "none", "visiblePainted", "visibleFill", "visibleStroke",
            "visible", "painted", "fill", "stroke", "all"
    );

    /**
     * Sets padding shorthand property (1-4 values)
     *
     * @param value Padding values (e.g., "10px", "10px 20px", "10px 20px 30px 40px")
     */
    public void setPadding(String value) {
        put(PADDING, value);
    }

    public String getPadding() {
        return get(PADDING);
    }

    /**
     * Sets padding in block dimension (vertical in horizontal writing modes)
     *
     * @param value 1-2 values (e.g., "10px", "10px 20px")
     */
    public void setPaddingBlock(String value) {
        put(PADDING_BLOCK, value);
    }

    public String getPaddingBlock() {
        return get(PADDING_BLOCK);
    }

    /**
     * Sets padding at end of block dimension
     *
     * @param value Padding value (e.g., "10px")
     */
    public void setPaddingBlockEnd(String value) {
        put(PADDING_BLOCK_END, value);
    }

    public String getPaddingBlockEnd() {
        return get(PADDING_BLOCK_END);
    }

    /**
     * Sets padding at start of block dimension
     *
     * @param value Padding value (e.g., "10px")
     */
    public void setPaddingBlockStart(String value) {
        put(PADDING_BLOCK_START, value);
    }

    public String getPaddingBlockStart() {
        return get(PADDING_BLOCK_START);
    }

    /**
     * Sets bottom padding
     *
     * @param value Padding value (e.g., "10px")
     */
    public void setPaddingBottom(String value) {
        put(PADDING_BOTTOM, value);
    }

    public String getPaddingBottom() {
        return get(PADDING_BOTTOM);
    }

    /**
     * Sets padding in inline dimension (horizontal in horizontal writing modes)
     *
     * @param value 1-2 values (e.g., "10px", "10px 20px")
     */
    public void setPaddingInline(String value) {
        put(PADDING_INLINE, value);
    }

    public String getPaddingInline() {
        return get(PADDING_INLINE);
    }

    /**
     * Sets padding at end of inline dimension
     *
     * @param value Padding value (e.g., "10px")
     */
    public void setPaddingInlineEnd(String value) {
        put(PADDING_INLINE_END, value);
    }

    public String getPaddingInlineEnd() {
        return get(PADDING_INLINE_END);
    }

    /**
     * Sets padding at start of inline dimension
     *
     * @param value Padding value (e.g., "10px")
     */
    public void setPaddingInlineStart(String value) {
        put(PADDING_INLINE_START, value);
    }

    public String getPaddingInlineStart() {
        return get(PADDING_INLINE_START);
    }

    /**
     * Sets left padding
     *
     * @param value Padding value (e.g., "10px")
     */
    public void setPaddingLeft(String value) {
        put(PADDING_LEFT, value);
    }

    public String getPaddingLeft() {
        return get(PADDING_LEFT);
    }

    /**
     * Sets right padding
     *
     * @param value Padding value (e.g., "10px")
     */
    public void setPaddingRight(String value) {
        put(PADDING_RIGHT, value);
    }

    public String getPaddingRight() {
        return get(PADDING_RIGHT);
    }

    /**
     * Sets top padding
     *
     * @param value Padding value (e.g., "10px")
     */
    public void setPaddingTop(String value) {
        put(PADDING_TOP, value);
    }

    public String getPaddingTop() {
        return get(PADDING_TOP);
    }


    /**
     * Defines page rule for print styling
     *
     * @param pageSelector Page selector (e.g., ":first", ":left")
     * @param rules        CSS rules for the page
     */
    public void setPageRule(String pageSelector, String rules) {
        String key = PAGE + (pageSelector != null ? " " + pageSelector : "");
        put(key, rules);
    }

    public String getPageRule(String pageSelector) {
        return get(PAGE + (pageSelector != null ? " " + pageSelector : ""));
    }

    /**
     * Sets page break behavior after element
     *
     * @param value Allowed values: auto | always | avoid | left | right
     * @throws IllegalArgumentException if value is invalid
     */
    public void setPageBreakAfter(String value) {
        if (VALID_PAGE_BREAK_VALUES.contains(value)) {
            put(PAGE_BREAK_AFTER, value);
        } else {
            throw new IllegalArgumentException("Invalid page-break-after value: " + value);
        }
    }

    public String getPageBreakAfter() {
        return get(PAGE_BREAK_AFTER);
    }

    /**
     * Sets page break behavior before element
     *
     * @param value Allowed values: auto | always | avoid | left | right
     * @throws IllegalArgumentException if value is invalid
     */
    public void setPageBreakBefore(String value) {
        if (VALID_PAGE_BREAK_VALUES.contains(value)) {
            put(PAGE_BREAK_BEFORE, value);
        } else {
            throw new IllegalArgumentException("Invalid page-break-before value: " + value);
        }
    }

    public String getPageBreakBefore() {
        return get(PAGE_BREAK_BEFORE);
    }

    /**
     * Sets page break behavior inside element
     *
     * @param value Allowed values: auto | avoid
     * @throws IllegalArgumentException if value is invalid
     */
    public void setPageBreakInside(String value) {
        if (VALID_PAGE_BREAK_VALUES.contains(value)) {
            put(PAGE_BREAK_INSIDE, value);
        } else {
            throw new IllegalArgumentException("Invalid page-break-inside value: " + value);
        }
    }

    public String getPageBreakInside() {
        return get(PAGE_BREAK_INSIDE);
    }


    /**
     * Sets painting order of fill, stroke, and markers
     *
     * @param value Allowed values: normal | fill | stroke | markers
     * @throws IllegalArgumentException if value is invalid
     */
    public void setPaintOrder(String value) {
        if (VALID_PAINT_ORDER_VALUES.contains(value)) {
            put(PAINT_ORDER, value);
        } else {
            throw new IllegalArgumentException("Invalid paint-order value: " + value);
        }
    }

    public String getPaintOrder() {
        return get(PAINT_ORDER);
    }

    /**
     * Sets 3D perspective distance
     *
     * @param value Perspective value (e.g., "500px", "none")
     */
    public void setPerspective(String value) {
        put(PERSPECTIVE, value);
    }

    public String getPerspective() {
        return get(PERSPECTIVE);
    }

    /**
     * Sets perspective origin
     *
     * @param value Origin position (e.g., "center", "left top", "50% 50%")
     */
    public void setPerspectiveOrigin(String value) {
        put(PERSPECTIVE_ORIGIN, value);
    }

    public String getPerspectiveOrigin() {
        return get(PERSPECTIVE_ORIGIN);
    }

    /**
     * Sets alignment for content in grid/flex containers (shorthand for align-content + justify-content)
     *
     * @param value Alignment values (e.g., "center", "start end", "space-between center")
     */
    public void setPlaceContent(String value) {
        put(PLACE_CONTENT, value);
    }

    public String getPlaceContent() {
        return get(PLACE_CONTENT);
    }

    /**
     * Sets alignment for items in grid/flex containers (shorthand for align-items + justify-items)
     *
     * @param value Alignment values (e.g., "center", "start", "baseline")
     */
    public void setPlaceItems(String value) {
        put(PLACE_ITEMS, value);
    }

    public String getPlaceItems() {
        return get(PLACE_ITEMS);
    }

    /**
     * Sets alignment for individual item in grid/flex container (shorthand for align-self + justify-self)
     *
     * @param value Alignment values (e.g., "center", "start", "stretch")
     */
    public void setPlaceSelf(String value) {
        put(PLACE_SELF, value);
    }

    public String getPlaceSelf() {
        return get(PLACE_SELF);
    }

    /**
     * Sets whether element is pointer event target
     *
     * @param value Allowed values: auto | none | visiblePainted | etc.
     * @throws IllegalArgumentException if value is invalid
     */
    public void setPointerEvents(String value) {
        if (VALID_POINTER_EVENTS_VALUES.contains(value)) {
            put(POINTER_EVENTS, value);
        } else {
            throw new IllegalArgumentException("Invalid pointer-events value: " + value);
        }
    }

    public String getPointerEvents() {
        return get(POINTER_EVENTS);
    }

    /**
     * Sets positioning method
     *
     * @param value Allowed values: static | relative | absolute | fixed | sticky
     * @throws IllegalArgumentException if value is invalid
     */
    public void setPosition(String value) {
        if (VALID_POSITION_VALUES.contains(value)) {
            put(POSITION, value);
        } else {
            throw new IllegalArgumentException("Invalid position value: " + value);
        }
    }

    public String getPosition() {
        return get(POSITION);
    }

    /**
     * Defines a custom CSS property
     *
     * @param name       Property name (must start with --)
     * @param descriptor Property descriptor (e.g., "syntax: '<color>'; inherits: true; initial-value: #fff;")
     */
    public void setCustomProperty(String name, String descriptor) {
        if (name != null && name.startsWith("--")) {
            put(PROPERTY + " " + name, descriptor);
        } else {
            throw new IllegalArgumentException("Custom property name must start with --");
        }
    }

    public String getCustomProperty(String name) {
        return get(PROPERTY + " " + name);
    }

}
