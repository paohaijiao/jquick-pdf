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
package com.github.paohaijiao.css.i.model;

import com.github.paohaijiao.css.h.model.JCSSPropertiesHModel;

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
public class JCSSPropertiesIModel extends JCSSPropertiesHModel {

    public static final String IMAGE_RENDERING = "image-rendering";
    public static final String INITIAL_LETTER = "initial-letter";
    public static final String INLINE_SIZE = "inline-size";
    public static final String INSET = "inset";
    public static final String INSET_BLOCK = "inset-block";
    public static final String INSET_BLOCK_END = "inset-block-end";
    public static final String INSET_BLOCK_START = "inset-block-start";
    public static final String INSET_INLINE = "inset-inline";
    public static final String INSET_INLINE_END = "inset-inline-end";
    public static final String INSET_INLINE_START = "inset-inline-start";
    public static final String ISOLATION = "isolation";
    public static final String IMPORT = "@import";

    private static final List<String> VALID_IMAGE_RENDERING =
            Arrays.asList("auto", "smooth", "high-quality", "crisp-edges", "pixelated");
    private static final List<String> VALID_ISOLATION =
            Arrays.asList("auto", "isolate");

    public String getImageRendering() {
        return get(IMAGE_RENDERING);
    }

    /**
     * @param value auto | smooth | high-quality | crisp-edges | pixelated
     */
    public void setImageRendering(String value) {
        if (VALID_IMAGE_RENDERING.contains(value)) {
            put(IMAGE_RENDERING, value);
        } else {
            throw new IllegalArgumentException("Invalid image-rendering value: " + value);
        }
    }

    public String getInitialLetter() {
        return get(INITIAL_LETTER);
    }

    /**
     * @param value：<number> <integer>? 如 "3 2"
     */
    public void setInitialLetter(String value) {
        put(INITIAL_LETTER, value);
    }

    public String getInlineSize() {
        return get(INLINE_SIZE);
    }

    /**
     * @param value ：auto | <length> | <percentage> | min-content | max-content | fit-content
     */
    public void setInlineSize(String value) {
        put(INLINE_SIZE, value);
    }

    public String getInset() {
        return get(INSET);
    }

    /**
     * @param value ：<length> | <percentage> | auto
     *              ：inset: 10px;
     *              inset: 10px 20px;
     *              inset: 10px 20px 30px;
     *              inset: 10px 20px 30px 40px;
     */
    public void setInset(String value) {
        put(INSET, value);
    }

    public String getInsetBlock() {
        return get(INSET_BLOCK);
    }

    public void setInsetBlock(String value) {
        put(INSET_BLOCK, value);
    }

    public String getInsetBlockEnd() {
        return get(INSET_BLOCK_END);
    }

    /**
     * @param value ：<length> | <percentage> | auto
     */
    public void setInsetBlockEnd(String value) {
        put(INSET_BLOCK_END, value);
    }

    public String getInsetBlockStart() {
        return get(INSET_BLOCK_START);
    }

    /**
     * @param value ：<length> | <percentage> | auto
     */
    public void setInsetBlockStart(String value) {
        put(INSET_BLOCK_START, value);
    }

    public String getInsetInline() {
        return get(INSET_INLINE);
    }

    /**
     * @param value ：<length> | <percentage> | auto
     *              1-2 ：inset-inline: 10px;
     *              inset-inline: 10px 20px;
     */
    public void setInsetInline(String value) {
        put(INSET_INLINE, value);
    }

    public String getInsetInlineEnd() {
        return get(INSET_INLINE_END);
    }

    /**
     * @param value ：<length> | <percentage> | auto
     *              LTR ， RTL
     */
    public void setInsetInlineEnd(String value) {
        put(INSET_INLINE_END, value);
    }

    public String getInsetInlineStart() {
        return get(INSET_INLINE_START);
    }

    /**
     * @param value ：<length> | <percentage> | auto
     *              LTR ，RTL
     */
    public void setInsetInlineStart(String value) {
        put(INSET_INLINE_START, value);
    }

    public String getIsolation() {
        return get(ISOLATION);
    }

    /**
     * @param value ：auto | isolate
     */
    public void setIsolation(String value) {
        if (VALID_ISOLATION.contains(value)) {
            put(ISOLATION, value);
        } else {
            throw new IllegalArgumentException("Invalid isolation value: " + value);
        }
    }

    public String getImportRule() {
        return get(IMPORT);
    }

    /**
     * @param rule ：@import "url" [media-query];
     */
    public void setImportRule(String rule) {
        if (rule != null && rule.startsWith("@import")) {
            put(IMPORT, rule);
        } else {
            throw new IllegalArgumentException("Invalid @import rule format");
        }
    }

}
