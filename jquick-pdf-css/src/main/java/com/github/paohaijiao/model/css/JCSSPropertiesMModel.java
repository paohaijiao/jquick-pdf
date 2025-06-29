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
public class JCSSPropertiesMModel extends JCSSPropertiesLModel {
    public static final String MARGIN = "margin";
    public static final String MARGIN_BLOCK = "margin-block";
    public static final String MARGIN_BLOCK_END = "margin-block-end";
    public static final String MARGIN_BLOCK_START = "margin-block-start";
    public static final String MARGIN_BOTTOM = "margin-bottom";
    public static final String MARGIN_INLINE = "margin-inline";
    public static final String MARGIN_INLINE_END = "margin-inline-end";
    public static final String MARGIN_INLINE_START = "margin-inline-start";
    public static final String MARGIN_LEFT = "margin-left";
    public static final String MARGIN_RIGHT = "margin-right";
    public static final String MARGIN_TOP = "margin-top";

    public static final String MARKER = "marker";
    public static final String MARKER_END = "marker-end";
    public static final String MARKER_MID = "marker-mid";
    public static final String MARKER_START = "marker-start";

    public static final String MASK = "mask";
    public static final String MASK_CLIP = "mask-clip";
    public static final String MASK_COMPOSITE = "mask-composite";
    public static final String MASK_IMAGE = "mask-image";
    public static final String MASK_MODE = "mask-mode";
    public static final String MASK_ORIGIN = "mask-origin";
    public static final String MASK_POSITION = "mask-position";
    public static final String MASK_REPEAT = "mask-repeat";
    public static final String MASK_SIZE = "mask-size";
    public static final String MASK_TYPE = "mask-type";
    public static final String MAX_BLOCK_SIZE = "max-block-size";
    public static final String MAX_HEIGHT = "max-height";
    public static final String MAX_INLINE_SIZE = "max-inline-size";
    public static final String MAX_WIDTH = "max-width";
    public static final String MEDIA = "@media";
    public static final String MIN_BLOCK_SIZE = "min-block-size";
    public static final String MIN_INLINE_SIZE = "min-inline-size";
    public static final String MIN_HEIGHT = "min-height";
    public static final String MIN_WIDTH = "min-width";
    public static final String MIX_BLEND_MODE = "mix-blend-mode";

    private static final List<String> VALID_MASK_MODES = Arrays.asList(
            "alpha", "luminance", "match-source"
    );

    private static final List<String> VALID_MASK_COMPOSITES = Arrays.asList(
            "add", "subtract", "intersect", "exclude"
    );

    private static final List<String> VALID_MASK_ORIGINS = Arrays.asList(
            "content-box", "padding-box", "border-box", "margin-box",
            "fill-box", "stroke-box", "view-box"
    );

    private static final List<String> VALID_MASK_REPEATS = Arrays.asList(
            "repeat-x", "repeat-y", "repeat", "space", "round", "no-repeat"
    );

    public void setMargin(String value) {
        put(MARGIN, value);
    }

    public String getMargin() {
        return get(MARGIN);
    }

    public void setMarginBlock(String value) {
        put(MARGIN_BLOCK, value);
    }

    public String getMarginBlock() {
        return get(MARGIN_BLOCK);
    }

    public void setMarginBlockEnd(String value) {
        put(MARGIN_BLOCK_END, value);
    }

    public String getMarginBlockEnd() {
        return get(MARGIN_BLOCK_END);
    }

    public void setMarginBlockStart(String value) {
        put(MARGIN_BLOCK_START, value);
    }

    public String getMarginBlockStart() {
        return get(MARGIN_BLOCK_START);
    }

    public void setMarginBottom(String value) {
        put(MARGIN_BOTTOM, value);
    }

    public String getMarginBottom() {
        return get(MARGIN_BOTTOM);
    }

    public void setMarginInline(String value) {
        put(MARGIN_INLINE, value);
    }

    public String getMarginInline() {
        return get(MARGIN_INLINE);
    }

    public void setMarginInlineEnd(String value) {
        put(MARGIN_INLINE_END, value);
    }

    public String getMarginInlineEnd() {
        return get(MARGIN_INLINE_END);
    }

    public void setMarginInlineStart(String value) {
        put(MARGIN_INLINE_START, value);
    }

    public String getMarginInlineStart() {
        return get(MARGIN_INLINE_START);
    }

    public void setMarginLeft(String value) {
        put(MARGIN_LEFT, value);
    }

    public String getMarginLeft() {
        return get(MARGIN_LEFT);
    }

    public void setMarginRight(String value) {
        put(MARGIN_RIGHT, value);
    }

    public String getMarginRight() {
        return get(MARGIN_RIGHT);
    }

    public void setMarginTop(String value) {
        put(MARGIN_TOP, value);
    }

    public String getMarginTop() {
        return get(MARGIN_TOP);
    }

    public void setMarker(String value) {
        put(MARKER, value);
    }

    public String getMarker() {
        return get(MARKER);
    }

    public void setMarkerEnd(String value) {
        put(MARKER_END, value);
    }

    public String getMarkerEnd() {
        return get(MARKER_END);
    }

    public void setMarkerMid(String value) {
        put(MARKER_MID, value);
    }

    public String getMarkerMid() {
        return get(MARKER_MID);
    }

    public void setMarkerStart(String value) {
        put(MARKER_START, value);
    }

    public String getMarkerStart() {
        return get(MARKER_START);
    }

    public void setMask(String value) {
        put(MASK, value);
    }

    public String getMask() {
        return get(MASK);
    }

    public void setMaskClip(String value) {
        if (VALID_MASK_ORIGINS.contains(value) || "no-clip".equals(value)) {
            put(MASK_CLIP, value);
        } else {
            throw new IllegalArgumentException("Invalid mask-clip value: " + value);
        }
    }

    public String getMaskClip() {
        return get(MASK_CLIP);
    }

    public void setMaskComposite(String value) {
        if (VALID_MASK_COMPOSITES.contains(value)) {
            put(MASK_COMPOSITE, value);
        } else {
            throw new IllegalArgumentException("Invalid mask-composite value: " + value);
        }
    }

    public String getMaskComposite() {
        return get(MASK_COMPOSITE);
    }

    public void setMaskImage(String value) {
        put(MASK_IMAGE, value);
    }

    public String getMaskImage() {
        return get(MASK_IMAGE);
    }

    public void setMaskMode(String value) {
        if (VALID_MASK_MODES.contains(value)) {
            put(MASK_MODE, value);
        } else {
            throw new IllegalArgumentException("Invalid mask-mode value: " + value);
        }
    }

    public String getMaskMode() {
        return get(MASK_MODE);
    }

    public void setMaskOrigin(String value) {
        if (VALID_MASK_ORIGINS.contains(value)) {
            put(MASK_ORIGIN, value);
        } else {
            throw new IllegalArgumentException("Invalid mask-origin value: " + value);
        }
    }

    public String getMaskOrigin() {
        return get(MASK_ORIGIN);
    }

    public void setMaskPosition(String value) {
        put(MASK_POSITION, value);
    }

    public String getMaskPosition() {
        return get(MASK_POSITION);
    }

    public void setMaskRepeat(String value) {
        if (VALID_MASK_REPEATS.contains(value)) {
            put(MASK_REPEAT, value);
        } else {
            throw new IllegalArgumentException("Invalid mask-repeat value: " + value);
        }
    }

    public String getMaskRepeat() {
        return get(MASK_REPEAT);
    }

    public void setMaskSize(String value) {
        put(MASK_SIZE, value);
    }

    public String getMaskSize() {
        return get(MASK_SIZE);
    }

    private static final List<String> VALID_MASK_TYPES = Arrays.asList(
            "luminance", "alpha"
    );

    private static final List<String> VALID_BLEND_MODES = Arrays.asList(
            "normal", "multiply", "screen", "overlay", "darken", "lighten",
            "color-dodge", "color-burn", "hard-light", "soft-light", "difference",
            "exclusion", "hue", "saturation", "color", "luminosity"
    );


    /**
     * Sets mask type for SVG masks
     *
     * @param value Allowed values: luminance | alpha
     * @throws IllegalArgumentException if value is invalid
     */
    public void setMaskType(String value) {
        if (VALID_MASK_TYPES.contains(value)) {
            put(MASK_TYPE, value);
        } else {
            throw new IllegalArgumentException("Invalid mask-type value: " + value);
        }
    }

    public String getMaskType() {
        return get(MASK_TYPE);
    }

    /**
     * Sets maximum size in block dimension
     *
     * @param value Allowed values: none | <length> | <percentage> | min-content |
     *              max-content | fit-content | <function>
     */
    public void setMaxBlockSize(String value) {
        put(MAX_BLOCK_SIZE, value);
    }

    public String getMaxBlockSize() {
        return get(MAX_BLOCK_SIZE);
    }

    /**
     * Sets maximum height
     *
     * @param value Allowed values: none | <length> | <percentage> | min-content |
     *              max-content | fit-content
     */
    public void setMaxHeight(String value) {
        put(MAX_HEIGHT, value);
    }

    public String getMaxHeight() {
        return get(MAX_HEIGHT);
    }

    /**
     * Sets maximum size in inline dimension
     *
     * @param value Allowed values: none | <length> | <percentage> | min-content |
     *              max-content | fit-content | <function>
     */
    public void setMaxInlineSize(String value) {
        put(MAX_INLINE_SIZE, value);
    }

    public String getMaxInlineSize() {
        return get(MAX_INLINE_SIZE);
    }

    /**
     * Sets maximum width
     *
     * @param value Allowed values: none | <length> | <percentage> | min-content |
     *              max-content | fit-content
     */
    public void setMaxWidth(String value) {
        put(MAX_WIDTH, value);
    }

    public String getMaxWidth() {
        return get(MAX_WIDTH);
    }

    /**
     * Adds a media query rule
     *
     * @param query Media query condition (e.g., "(max-width: 600px)")
     * @param rules CSS rules to apply when media query matches
     */
    public void addMediaRule(String query, String rules) {
        String key = MEDIA + " " + query;
        String existing = get(key);
        put(key, existing != null ? existing + rules : rules);
    }

    public String getMediaRules(String query) {
        return get(MEDIA + " " + query);
    }

    /**
     * Sets minimum size in block dimension
     *
     * @param value Allowed values: <length> | <percentage> | min-content |
     *              max-content | fit-content | <function>
     */
    public void setMinBlockSize(String value) {
        put(MIN_BLOCK_SIZE, value);
    }

    public String getMinBlockSize() {
        return get(MIN_BLOCK_SIZE);
    }

    /**
     * Sets minimum size in inline dimension
     *
     * @param value Allowed values: <length> | <percentage> | min-content |
     *              max-content | fit-content | <function>
     */
    public void setMinInlineSize(String value) {
        put(MIN_INLINE_SIZE, value);
    }

    public String getMinInlineSize() {
        return get(MIN_INLINE_SIZE);
    }

    /**
     * Sets minimum height
     *
     * @param value Allowed values: <length> | <percentage> | min-content |
     *              max-content | fit-content
     */
    public void setMinHeight(String value) {
        put(MIN_HEIGHT, value);
    }

    public String getMinHeight() {
        return get(MIN_HEIGHT);
    }

    /**
     * Sets minimum width
     *
     * @param value Allowed values: <length> | <percentage> | min-content |
     *              max-content | fit-content
     */
    public void setMinWidth(String value) {
        put(MIN_WIDTH, value);
    }

    public String getMinWidth() {
        return get(MIN_WIDTH);
    }

    /**
     * Sets blend mode for element
     *
     * @param value Allowed values: normal | multiply | screen | overlay | etc.
     * @throws IllegalArgumentException if value is invalid
     */
    public void setMixBlendMode(String value) {
        if (VALID_BLEND_MODES.contains(value)) {
            put(MIX_BLEND_MODE, value);
        } else {
            throw new IllegalArgumentException("Invalid mix-blend-mode value: " + value);
        }
    }

    public String getMixBlendMode() {
        return get(MIX_BLEND_MODE);
    }


}
