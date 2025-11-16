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
package com.github.paohaijiao.css.o.model;

import com.github.paohaijiao.css.n.model.JCSSPropertiesNModel;

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
public class JCSSPropertiesOModel extends JCSSPropertiesNModel {

    public static final String OBJECT_FIT = "object-fit";
    public static final String OBJECT_POSITION = "object-position";
    public static final String OFFSET = "offset";
    public static final String OFFSET_ANCHOR = "offset-anchor";
    public static final String OFFSET_DISTANCE = "offset-distance";
    public static final String OFFSET_PATH = "offset-path";
    public static final String OFFSET_POSITION = "offset-position";
    public static final String OFFSET_ROTATE = "offset-rotate";

    public static final String OPACITY = "opacity";
    public static final String ORDER = "order";
    public static final String ORPHANS = "orphans";

    public static final String OUTLINE = "outline";
    public static final String OUTLINE_COLOR = "outline-color";
    public static final String OUTLINE_OFFSET = "outline-offset";
    public static final String OUTLINE_STYLE = "outline-style";
    public static final String OUTLINE_WIDTH = "outline-width";

    public static final String OVERFLOW = "overflow";
    public static final String OVERFLOW_ANCHOR = "overflow-anchor";
    public static final String OVERFLOW_WRAP = "overflow-wrap";
    public static final String OVERFLOW_X = "overflow-x";
    public static final String OVERFLOW_Y = "overflow-y";

    public static final String OVERSCROLL_BEHAVIOR = "overscroll-behavior";
    public static final String OVERSCROLL_BEHAVIOR_BLOCK = "overscroll-behavior-block";
    public static final String OVERSCROLL_BEHAVIOR_INLINE = "overscroll-behavior-inline";
    public static final String OVERSCROLL_BEHAVIOR_X = "overscroll-behavior-x";

    public static final String OVERSCROLL_BEHAVIOR_Y = "overscroll-behavior-y";
    private static final List<String> VALID_OBJECT_FIT_VALUES = Arrays.asList(
            "fill", "contain", "cover", "none", "scale-down"
    );

    private static final List<String> VALID_OUTLINE_STYLES = Arrays.asList(
            "none", "hidden", "dotted", "dashed", "solid", "double",
            "groove", "ridge", "inset", "outset"
    );

    private static final List<String> VALID_OVERFLOW_VALUES = Arrays.asList(
            "visible", "hidden", "clip", "scroll", "auto"
    );

    private static final List<String> VALID_OVERFLOW_WRAP_VALUES = Arrays.asList(
            "normal", "break-word", "anywhere"
    );

    private static final List<String> VALID_OVERSCROLL_VALUES = Arrays.asList(
            "auto", "contain", "none"
    );

    public String getObjectFit() {
        return get(OBJECT_FIT);
    }

    public void setObjectFit(String value) {
        if (VALID_OBJECT_FIT_VALUES.contains(value)) {
            put(OBJECT_FIT, value);
        } else {
            throw new IllegalArgumentException("Invalid object-fit value: " + value);
        }
    }

    public String getObjectPosition() {
        return get(OBJECT_POSITION);
    }

    public void setObjectPosition(String value) {
        put(OBJECT_POSITION, value);
    }

    public String getOffset() {
        return get(OFFSET);
    }

    public void setOffset(String value) {
        put(OFFSET, value);
    }

    public String getOffsetAnchor() {
        return get(OFFSET_ANCHOR);
    }

    public void setOffsetAnchor(String value) {
        put(OFFSET_ANCHOR, value);
    }

    public String getOffsetDistance() {
        return get(OFFSET_DISTANCE);
    }

    public void setOffsetDistance(String value) {
        put(OFFSET_DISTANCE, value);
    }

    public String getOffsetPath() {
        return get(OFFSET_PATH);
    }

    public void setOffsetPath(String value) {
        put(OFFSET_PATH, value);
    }

    public String getOffsetPosition() {
        return get(OFFSET_POSITION);
    }

    public void setOffsetPosition(String value) {
        put(OFFSET_POSITION, value);
    }

    public String getOffsetRotate() {
        return get(OFFSET_ROTATE);
    }

    public void setOffsetRotate(String value) {
        put(OFFSET_ROTATE, value);
    }

    public String getOpacity() {
        return get(OPACITY);
    }

    public void setOpacity(String value) {
        if (isValidNumber(value, 0, 1)) {
            put(OPACITY, value);
        } else {
            throw new IllegalArgumentException("Opacity must be between 0 and 1");
        }
    }

    public String getOrder() {
        return get(ORDER);
    }

    public void setOrder(String value) {
        put(ORDER, value);
    }

    public String getOrphans() {
        return get(ORPHANS);
    }

    public void setOrphans(String value) {
        if (isValidPositiveInteger(value)) {
            put(ORPHANS, value);
        } else {
            throw new IllegalArgumentException("Orphans must be a positive integer");
        }
    }

    public String getOutline() {
        return get(OUTLINE);
    }

    public void setOutline(String value) {
        put(OUTLINE, value);
    }

    public String getOutlineColor() {
        return get(OUTLINE_COLOR);
    }

    public void setOutlineColor(String value) {
        put(OUTLINE_COLOR, value);
    }

    public String getOutlineOffset() {
        return get(OUTLINE_OFFSET);
    }

    public void setOutlineOffset(String value) {
        put(OUTLINE_OFFSET, value);
    }

    public String getOutlineStyle() {
        return get(OUTLINE_STYLE);
    }

    public void setOutlineStyle(String value) {
        if (VALID_OUTLINE_STYLES.contains(value)) {
            put(OUTLINE_STYLE, value);
        } else {
            throw new IllegalArgumentException("Invalid outline-style value: " + value);
        }
    }

    public String getOutlineWidth() {
        return get(OUTLINE_WIDTH);
    }

    public void setOutlineWidth(String value) {
        put(OUTLINE_WIDTH, value);
    }

    public String getOverflow() {
        return get(OVERFLOW);
    }

    // Overflow Properties
    public void setOverflow(String value) {
        if (VALID_OVERFLOW_VALUES.contains(value)) {
            put(OVERFLOW, value);
        } else {
            throw new IllegalArgumentException("Invalid overflow value: " + value);
        }
    }

    public String getOverflowAnchor() {
        return get(OVERFLOW_ANCHOR);
    }

    public void setOverflowAnchor(String value) {
        put(OVERFLOW_ANCHOR, value);
    }

    public String getOverflowWrap() {
        return get(OVERFLOW_WRAP);
    }

    public void setOverflowWrap(String value) {
        if (VALID_OVERFLOW_WRAP_VALUES.contains(value)) {
            put(OVERFLOW_WRAP, value);
        } else {
            throw new IllegalArgumentException("Invalid overflow-wrap value: " + value);
        }
    }

    public String getOverflowX() {
        return get(OVERFLOW_X);
    }

    public void setOverflowX(String value) {
        if (VALID_OVERFLOW_VALUES.contains(value)) {
            put(OVERFLOW_X, value);
        } else {
            throw new IllegalArgumentException("Invalid overflow-x value: " + value);
        }
    }

    public String getOverflowY() {
        return get(OVERFLOW_Y);
    }

    public void setOverflowY(String value) {
        if (VALID_OVERFLOW_VALUES.contains(value)) {
            put(OVERFLOW_Y, value);
        } else {
            throw new IllegalArgumentException("Invalid overflow-y value: " + value);
        }
    }

    public String getOverscrollBehavior() {
        return get(OVERSCROLL_BEHAVIOR);
    }

    // Overscroll Properties
    public void setOverscrollBehavior(String value) {
        if (VALID_OVERSCROLL_VALUES.contains(value)) {
            put(OVERSCROLL_BEHAVIOR, value);
        } else {
            throw new IllegalArgumentException("Invalid overscroll-behavior value: " + value);
        }
    }

    public String getOverscrollBehaviorBlock() {
        return get(OVERSCROLL_BEHAVIOR_BLOCK);
    }

    public void setOverscrollBehaviorBlock(String value) {
        if (VALID_OVERSCROLL_VALUES.contains(value)) {
            put(OVERSCROLL_BEHAVIOR_BLOCK, value);
        } else {
            throw new IllegalArgumentException("Invalid overscroll-behavior-block value: " + value);
        }
    }

    public String getOverscrollBehaviorInline() {
        return get(OVERSCROLL_BEHAVIOR_INLINE);
    }

    public void setOverscrollBehaviorInline(String value) {
        if (VALID_OVERSCROLL_VALUES.contains(value)) {
            put(OVERSCROLL_BEHAVIOR_INLINE, value);
        } else {
            throw new IllegalArgumentException("Invalid overscroll-behavior-inline value: " + value);
        }
    }

    public String getOverscrollBehaviorX() {
        return get(OVERSCROLL_BEHAVIOR_X);
    }

    public void setOverscrollBehaviorX(String value) {
        if (VALID_OVERSCROLL_VALUES.contains(value)) {
            put(OVERSCROLL_BEHAVIOR_X, value);
        } else {
            throw new IllegalArgumentException("Invalid overscroll-behavior-x value: " + value);
        }
    }

    private boolean isValidNumber(String value, double min, double max) {
        try {
            double num = Double.parseDouble(value);
            return num >= min && num <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidPositiveInteger(String value) {
        try {
            int num = Integer.parseInt(value);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getOverscrollBehaviorY() {
        return get(OVERSCROLL_BEHAVIOR_Y);
    }

    /**
     * Sets overscroll behavior for y-axis
     *
     * @param value Allowed values: auto | contain | none
     * @throws IllegalArgumentException if value is invalid
     */
    public void setOverscrollBehaviorY(String value) {
        if (VALID_OVERSCROLL_VALUES.contains(value)) {
            put(OVERSCROLL_BEHAVIOR_Y, value);
        } else {
            throw new IllegalArgumentException("Invalid overscroll-behavior-y value: " + value);
        }
    }

}
