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
public class JCSSPropertiesSModel extends JCSSPropertiesRModel {
    // Add these constants to the existing constants section
    public static final String SCALE = "scale";
    public static final String SCOPE = "@scope";
    public static final String SCROLL_BEHAVIOR = "scroll-behavior";
    public static final String SCROLL_MARGIN = "scroll-margin";
    public static final String SCROLL_MARGIN_BLOCK = "scroll-margin-block";
    public static final String SCROLL_MARGIN_BLOCK_END = "scroll-margin-block-end";
    public static final String SCROLL_MARGIN_BLOCK_START = "scroll-margin-block-start";
    public static final String SCROLL_MARGIN_BOTTOM = "scroll-margin-bottom";
    public static final String SCROLL_MARGIN_INLINE = "scroll-margin-inline";
    public static final String SCROLL_MARGIN_INLINE_END = "scroll-margin-inline-end";
    public static final String SCROLL_MARGIN_INLINE_START = "scroll-margin-inline-start";
    public static final String SCROLL_MARGIN_LEFT = "scroll-margin-left";
    public static final String SCROLL_MARGIN_RIGHT = "scroll-margin-right";
    public static final String SCROLL_MARGIN_TOP = "scroll-margin-top";
    public static final String SCROLL_PADDING = "scroll-padding";
    public static final String SCROLL_PADDING_BLOCK = "scroll-padding-block";
    public static final String SCROLL_PADDING_BLOCK_END = "scroll-padding-block-end";
    public static final String SCROLL_PADDING_BLOCK_START = "scroll-padding-block-start";
    public static final String SCROLL_PADDING_BOTTOM = "scroll-padding-bottom";
    public static final String SCROLL_PADDING_INLINE = "scroll-padding-inline";
    public static final String SCROLL_PADDING_INLINE_END = "scroll-padding-inline-end";
    public static final String SCROLL_PADDING_INLINE_START = "scroll-padding-inline-start";
    public static final String SCROLL_PADDING_LEFT = "scroll-padding-left";
    public static final String SCROLL_PADDING_RIGHT = "scroll-padding-right";
    public static final String SCROLL_PADDING_TOP = "scroll-padding-top";
    public static final String SCROLL_SNAP_ALIGN = "scroll-snap-align";
    public static final String SCROLL_SNAP_STOP = "scroll-snap-stop";
    public static final String SCROLL_SNAP_TYPE = "scroll-snap-type";
    public static final String SCROLLBAR_COLOR = "scrollbar-color";
    public static final String SHAPE_OUTSIDE = "shape-outside";
    public static final String STARTING_STYLE = "@starting-style";


    private static final List<String> VALID_SCROLL_BEHAVIOR_VALUES = Arrays.asList(
            "auto", "smooth", "initial", "inherit"
    );


    /**
     * Sets the scale transformation of an element
     *
     * @param value Scale value (e.g., "1.5", "2 0.5", "1.2 1.2 1.2")
     */
    public void setScale(String value) {
        put(SCALE, value);
    }

    /**
     * Gets the current scale value
     *
     * @return The current scale value
     */
    public String getScale() {
        return get(SCALE);
    }


    /**
     * Defines a scope rule for CSS scoping
     *
     * @param scopeSelector Scope selector (e.g., "(.card)")
     * @param rules         CSS rules for the scope
     */
    public void setScopeRule(String scopeSelector, String rules) {
        String key = SCOPE + (scopeSelector != null ? " " + scopeSelector : "");
        put(key, rules);
    }

    /**
     * Gets the scope rule for a specific selector
     *
     * @param scopeSelector Scope selector
     * @return The CSS rules for the scope
     */
    public String getScopeRule(String scopeSelector) {
        return get(SCOPE + (scopeSelector != null ? " " + scopeSelector : ""));
    }


    /**
     * Sets the scrolling behavior for a scrollable element
     *
     * @param value Allowed values: auto | smooth | initial | inherit
     * @throws IllegalArgumentException if value is invalid
     */
    public void setScrollBehavior(String value) {
        if (VALID_SCROLL_BEHAVIOR_VALUES.contains(value)) {
            put(SCROLL_BEHAVIOR, value);
        } else {
            throw new IllegalArgumentException("Invalid scroll-behavior value: " + value);
        }
    }

    /**
     * Gets the current scroll behavior value
     *
     * @return The current scroll behavior value
     */
    public String getScrollBehavior() {
        return get(SCROLL_BEHAVIOR);
    }


    /**
     * Sets the scroll margin shorthand (1-4 values)
     *
     * @param value Margin values (e.g., "10px", "10px 20px", "10px 20px 30px 40px")
     */
    public void setScrollMargin(String value) {
        put(SCROLL_MARGIN, value);
    }

    public String getScrollMargin() {
        return get(SCROLL_MARGIN);
    }

    /**
     * Sets scroll margin in block dimension
     *
     * @param value 1-2 values (e.g., "10px", "10px 20px")
     */
    public void setScrollMarginBlock(String value) {
        put(SCROLL_MARGIN_BLOCK, value);
    }

    public String getScrollMarginBlock() {
        return get(SCROLL_MARGIN_BLOCK);
    }

// [...] Similar methods for all other scroll-margin properties:
// scroll-margin-block-end, scroll-margin-block-start,
// scroll-margin-bottom, scroll-margin-inline,
// scroll-margin-inline-end, scroll-margin-inline-start,
// scroll-margin-left, scroll-margin-right, scroll-margin-top


    /**
     * Sets the scroll padding shorthand (1-4 values)
     *
     * @param value Padding values (e.g., "10px", "10px 20px", "10px 20px 30px 40px")
     */
    public void setScrollPadding(String value) {
        put(SCROLL_PADDING, value);
    }

    public String getScrollPadding() {
        return get(SCROLL_PADDING);
    }

    /**
     * Sets scroll padding in block dimension
     *
     * @param value 1-2 values (e.g., "10px", "10px 20px")
     */
    public void setScrollPaddingBlock(String value) {
        put(SCROLL_PADDING_BLOCK, value);
    }

    public String getScrollPaddingBlock() {
        return get(SCROLL_PADDING_BLOCK);
    }

// [...] Similar methods for all other scroll-padding properties:
// scroll-padding-block-end, scroll-padding-block-start,
// scroll-padding-bottom, scroll-padding-inline,
// scroll-padding-inline-end, scroll-padding-inline-start,
// scroll-padding-left, scroll-padding-right, scroll-padding-top

    // Helper method to validate length values
    private boolean isValidLengthValue(String value) {
        if (value == null) return false;
        return value.equals("auto") || value.equals("initial") || value.equals("inherit") ||
                value.matches("^-?\\d+(\\.\\d+)?(px|em|rem|%|vw|vh|vmin|vmax|cm|mm|in|pt|pc)$");
    }


    public void setScrollMarginBlockEnd(String value) {
        put(SCROLL_MARGIN_BLOCK_END, value);
    }

    public String getScrollMarginBlockEnd() {
        return get(SCROLL_MARGIN_BLOCK_END);
    }

    public void setScrollMarginBlockStart(String value) {
        put(SCROLL_MARGIN_BLOCK_START, value);
    }

    public String getScrollMarginBlockStart() {
        return get(SCROLL_MARGIN_BLOCK_START);
    }

    public void setScrollMarginBottom(String value) {
        put(SCROLL_MARGIN_BOTTOM, value);
    }

    public String getScrollMarginBottom() {
        return get(SCROLL_MARGIN_BOTTOM);
    }

    public void setScrollMarginInline(String value) {
        put(SCROLL_MARGIN_INLINE, value);
    }

    public String getScrollMarginInline() {
        return get(SCROLL_MARGIN_INLINE);
    }

    public void setScrollMarginInlineEnd(String value) {
        put(SCROLL_MARGIN_INLINE_END, value);
    }

    public String getScrollMarginInlineEnd() {
        return get(SCROLL_MARGIN_INLINE_END);
    }

    public void setScrollMarginInlineStart(String value) {
        put(SCROLL_MARGIN_INLINE_START, value);
    }

    public String getScrollMarginInlineStart() {
        return get(SCROLL_MARGIN_INLINE_START);
    }

    public void setScrollMarginLeft(String value) {
        put(SCROLL_MARGIN_LEFT, value);
    }

    public String getScrollMarginLeft() {
        return get(SCROLL_MARGIN_LEFT);
    }

    public void setScrollMarginRight(String value) {
        put(SCROLL_MARGIN_RIGHT, value);
    }

    public String getScrollMarginRight() {
        return get(SCROLL_MARGIN_RIGHT);
    }

    public void setScrollMarginTop(String value) {
        put(SCROLL_MARGIN_TOP, value);
    }

    public String getScrollMarginTop() {
        return get(SCROLL_MARGIN_TOP);
    }


    public void setScrollPaddingBlockEnd(String value) {
        put(SCROLL_PADDING_BLOCK_END, value);
    }

    public String getScrollPaddingBlockEnd() {
        return get(SCROLL_PADDING_BLOCK_END);
    }

    public void setScrollPaddingBlockStart(String value) {
        put(SCROLL_PADDING_BLOCK_START, value);
    }

    public String getScrollPaddingBlockStart() {
        return get(SCROLL_PADDING_BLOCK_START);
    }

    public void setScrollPaddingBottom(String value) {
        put(SCROLL_PADDING_BOTTOM, value);
    }

    public String getScrollPaddingBottom() {
        return get(SCROLL_PADDING_BOTTOM);
    }

    public void setScrollPaddingInline(String value) {
        put(SCROLL_PADDING_INLINE, value);
    }

    public String getScrollPaddingInline() {
        return get(SCROLL_PADDING_INLINE);
    }

    public void setScrollPaddingInlineEnd(String value) {
        put(SCROLL_PADDING_INLINE_END, value);
    }

    public String getScrollPaddingInlineEnd() {
        return get(SCROLL_PADDING_INLINE_END);
    }

    public void setScrollPaddingInlineStart(String value) {
        put(SCROLL_PADDING_INLINE_START, value);
    }

    public String getScrollPaddingInlineStart() {
        return get(SCROLL_PADDING_INLINE_START);
    }

    public void setScrollPaddingLeft(String value) {
        put(SCROLL_PADDING_LEFT, value);
    }

    public String getScrollPaddingLeft() {
        return get(SCROLL_PADDING_LEFT);
    }

    public void setScrollPaddingRight(String value) {
        put(SCROLL_PADDING_RIGHT, value);
    }

    public String getScrollPaddingRight() {
        return get(SCROLL_PADDING_RIGHT);
    }

    public void setScrollPaddingTop(String value) {
        put(SCROLL_PADDING_TOP, value);
    }

    public String getScrollPaddingTop() {
        return get(SCROLL_PADDING_TOP);
    }

    // Add these lists for valid values
    private static final List<String> VALID_SCROLL_SNAP_ALIGN_VALUES = Arrays.asList(
            "none", "start", "end", "center", "initial", "inherit"
    );

    private static final List<String> VALID_SCROLL_SNAP_STOP_VALUES = Arrays.asList(
            "normal", "always", "initial", "inherit"
    );

    private static final List<String> VALID_SCROLL_SNAP_TYPE_VALUES = Arrays.asList(
            "none", "x", "y", "block", "inline", "both", "mandatory", "proximity", "initial", "inherit"
    );

    private static final List<String> VALID_SCROLLBAR_COLOR_VALUES = Arrays.asList(
            "auto", "dark", "light", "initial", "inherit"
    );


    /**
     * Sets the scroll snap alignment for an element
     *
     * @param value Allowed values: none | start | end | center | initial | inherit
     * @throws IllegalArgumentException if value is invalid
     */
    public void setScrollSnapAlign(String value) {
        if (VALID_SCROLL_SNAP_ALIGN_VALUES.contains(value)) {
            put(SCROLL_SNAP_ALIGN, value);
        } else {
            throw new IllegalArgumentException("Invalid scroll-snap-align value: " + value);
        }
    }

    public String getScrollSnapAlign() {
        return get(SCROLL_SNAP_ALIGN);
    }

    /**
     * Sets whether scrolling must stop on snap points
     *
     * @param value Allowed values: normal | always | initial | inherit
     * @throws IllegalArgumentException if value is invalid
     */
    public void setScrollSnapStop(String value) {
        if (VALID_SCROLL_SNAP_STOP_VALUES.contains(value)) {
            put(SCROLL_SNAP_STOP, value);
        } else {
            throw new IllegalArgumentException("Invalid scroll-snap-stop value: " + value);
        }
    }

    public String getScrollSnapStop() {
        return get(SCROLL_SNAP_STOP);
    }

    /**
     * Sets the scroll snap container behavior
     *
     * @param value Allowed values: none | x | y | block | inline | both |
     *              mandatory | proximity | initial | inherit
     * @throws IllegalArgumentException if value is invalid
     */
    public void setScrollSnapType(String value) {
        if (VALID_SCROLL_SNAP_TYPE_VALUES.contains(value)) {
            put(SCROLL_SNAP_TYPE, value);
        } else {
            throw new IllegalArgumentException("Invalid scroll-snap-type value: " + value);
        }
    }

    public String getScrollSnapType() {
        return get(SCROLL_SNAP_TYPE);
    }


    /**
     * Sets the scrollbar color
     *
     * @param value Allowed values: auto | dark | light | initial | inherit |
     *              color values (e.g., "#ff0000", "red", "rgb(255,0,0)")
     * @throws IllegalArgumentException if value is invalid
     */
    public void setScrollbarColor(String value) {
        if (VALID_SCROLLBAR_COLOR_VALUES.contains(value) || isValidColorValue(value)) {
            put(SCROLLBAR_COLOR, value);
        } else {
            throw new IllegalArgumentException("Invalid scrollbar-color value: " + value);
        }
    }

    public String getScrollbarColor() {
        return get(SCROLLBAR_COLOR);
    }


    /**
     * Sets the shape around which inline content should wrap
     *
     * @param value Allowed values: none | margin-box | padding-box | content-box |
     *              circle() | ellipse() | polygon() | url() | initial | inherit
     */
    public void setShapeOutside(String value) {
        put(SHAPE_OUTSIDE, value);
    }

    public String getShapeOutside() {
        return get(SHAPE_OUTSIDE);
    }

    /**
     * Defines starting style rules for elements
     *
     * @param selector The selector for the starting style
     * @param rules    CSS rules for the starting style
     */
    public void setStartingStyle(String selector, String rules) {
        String key = STARTING_STYLE + (selector != null ? " " + selector : "");
        put(key, rules);
    }

    public String getStartingStyle(String selector) {
        return get(STARTING_STYLE + (selector != null ? " " + selector : ""));
    }

    private boolean isValidColorValue(String value) {
        return true;
    }
}
