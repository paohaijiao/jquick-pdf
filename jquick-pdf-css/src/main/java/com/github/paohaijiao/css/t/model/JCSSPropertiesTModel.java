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
package com.github.paohaijiao.css.t.model;

import com.github.paohaijiao.css.s.model.JCSSPropertiesSModel;

/**
 * packageName com.github.paohaijiao.model.css
 *
 * @author Martin
 * @version 1.0.0
 * @className CSSPropertiesBModel
 * @date 2025/6/29
 * @description
 */
public class JCSSPropertiesTModel extends JCSSPropertiesSModel {
    public static final String TAB_SIZE = "tab-size";
    public static final String TABLE_LAYOUT = "table-layout";
    public static final String TEXT_ALIGN = "text-align";
    public static final String TABLE_ALIGN = "table-align";
    public static final String TEXT_ALIGN_LAST = "text-align-last";
    public static final String TEXT_DECORATION = "text-decoration";
    public static final String TEXT_DECORATION_COLOR = "text-decoration-color";
    public static final String TEXT_DECORATION_LINE = "text-decoration-line";
    public static final String TEXT_DECORATION_STYLE = "text-decoration-style";
    public static final String TEXT_DECORATION_THICKNESS = "text-decoration-thickness";
    public static final String TEXT_EMPHASIS = "text-emphasis";
    public static final String TEXT_EMPHASIS_COLOR = "text-emphasis-color";
    public static final String TEXT_EMPHASIS_POSITION = "text-emphasis-position";
    public static final String TEXT_EMPHASIS_STYLE = "text-emphasis-style";
    public static final String TEXT_INDENT = "text-indent";
    public static final String TEXT_JUSTIFY = "text-justify";
    public static final String TEXT_ORIENTATION = "text-orientation";
    public static final String TEXT_OVERFLOW = "text-overflow";
    public static final String TEXT_SHADOW = "text-shadow";
    public static final String TEXT_TRANSFORM = "text-transform";
    public static final String TEXT_UNDERLINE_OFFSET = "text-underline-offset";
    public static final String TEXT_UNDERLINE_POSITION = "text-underline-position";
    public static final String TOP = "top";
    public static final String TRANSFORM = "transform";
    public static final String TRANSFORM_ORIGIN = "transform-origin";
    public static final String TRANSFORM_STYLE = "transform-style";
    public static final String TRANSITION = "transition";
    public static final String TRANSITION_DELAY = "transition-delay";
    public static final String TRANSITION_DURATION = "transition-duration";
    public static final String TRANSITION_PROPERTY = "transition-property";
    public static final String TRANSITION_TIMING_FUNCTION = "transition-timing-function";
    public static final String TRANSLATE = "translate";

    /**
     * Makes your tabs as big or small as you fancy
     */
    public void makeTabsSexy(int size) {
        put(TAB_SIZE, size + "px");
    }

    /**
     * Who needs flexible tables? Go fixed or go home!
     */
    public void lockTableLayout(boolean fixed) {
        put(TABLE_LAYOUT, fixed ? "fixed" : "auto");
    }

    /**
     * Align your text like a boss (left, right, center, justify)
     */
    public void bossTextAround(String alignment) {
        put(TEXT_ALIGN, alignment);
    }

    /**
     * The rebel that aligns the last line differently
     */
    public void rebelLastLineAlign(String alignment) {
        put(TEXT_ALIGN_LAST, alignment);
    }

    /**
     * Pimp your text with decorations
     */
    public void pimpMyText(String decoration) {
        put(TEXT_DECORATION, decoration);
    }

    /**
     * Color your underlines like a rainbow
     */
    public void rainbowUnderline(String color) {
        put(TEXT_DECORATION_COLOR, color);
    }

    /**
     * Choose your weapon: underline, overline, line-through
     */
    public void textDecorationArmory(String lines) {
        put(TEXT_DECORATION_LINE, lines);
    }

    /**
     * Make your lines dashed, dotted, or wavy
     */
    public void lineStyleSwag(String style) {
        put(TEXT_DECORATION_STYLE, style);
    }

    /**
     * Thicc or thin? Your underline, your choice
     */
    public void thiccUnderline(String thickness) {
        put(TEXT_DECORATION_THICKNESS, thickness);
    }

    /**
     * Add some oomph to your text
     */
    public void addTextOomph(String emphasis) {
        put(TEXT_EMPHASIS, emphasis);
    }

    /**
     * Colorful emphasis for extra drama
     */
    public void dramaticTextColor(String color) {
        put(TEXT_EMPHASIS_COLOR, color);
    }

    /**
     * Position your emphasis like a pro
     */
    public void emphasisFengShui(String position) {
        put(TEXT_EMPHASIS_POSITION, position);
    }

    /**
     * Fancy emphasis styles for attention seekers
     */
    public void attentionSeekerStyle(String style) {
        put(TEXT_EMPHASIS_STYLE, style);
    }

    /**
     * Indent your text like a proper gentleman
     */
    public void gentlemanIndent(String indent) {
        put(TEXT_INDENT, indent);
    }

    /**
     * Justify your text existence
     */
    public void justifyMyExistence(String justification) {
        put(TEXT_JUSTIFY, justification);
    }

    /**
     * Flip your text sideways for fun
     */
    public void sidewaysText(String orientation) {
        put(TEXT_ORIENTATION, orientation);
    }

    /**
     * When your text can't contain itself
     */
    public void handleOverflowingEmotions(String overflow) {
        put(TEXT_OVERFLOW, overflow);
    }

    /**
     * Give your text some sweet shadow action
     */
    public void textShadowNinja(String shadow) {
        put(TEXT_SHADOW, shadow);
    }

    /**
     * Transform your text like a superhero
     */
    public void textTransformationSequence(String transform) {
        put(TEXT_TRANSFORM, transform);
    }

    /**
     * Nudge your underline up or down
     */
    public void underlineTweaker(String offset) {
        put(TEXT_UNDERLINE_OFFSET, offset);
    }

    /**
     * Precise underline positioning for perfectionists
     */
    public void underlineOCD(String position) {
        put(TEXT_UNDERLINE_POSITION, position);
    }

    /**
     * Stick it to the top!
     */
    public void stickToTheTop(String value) {
        put(TOP, value);
    }

    /**
     * Transform elements like a Decepticon
     */
    public void transformAndRollOut(String transform) {
        put(TRANSFORM, transform);
    }

    /**
     * Control where the magic transformation happens
     */
    public void transformationOriginStory(String origin) {
        put(TRANSFORM_ORIGIN, origin);
    }

    /**
     * Flat or 3D? Choose your transformation style
     */
    public void chooseYourDimension(String style) {
        put(TRANSFORM_STYLE, style);
    }

    /**
     * Smooth transitions for smooth operators
     */
    public void smoothOperator(String transition) {
        put(TRANSITION, transition);
    }

    /**
     * Make them wait for it...
     */
    public void anticipationBuilder(String delay) {
        put(TRANSITION_DELAY, delay);
    }


    public String getTabSize() {
        return get(TAB_SIZE);
    }

    public String getTableLayout() {
        return get(TABLE_LAYOUT);
    }

    public String getTextAlign() {
        return get(TEXT_ALIGN);
    }

    /**
     * Sets how long the transition party lasts
     *
     * @param duration Time string (e.g., "0.3s", "500ms")
     */
    public void setTransitionPartyDuration(String duration) {
        put(TRANSITION_DURATION, duration);
    }

    /**
     * Choose which properties get to dance during transition
     *
     * @param properties CSS properties to animate (e.g., "all", "opacity, transform")
     */
    public void inviteToTransitionParty(String properties) {
        put(TRANSITION_PROPERTY, properties);
    }

    /**
     * Sets the dance rhythm for transitions
     *
     * @param timing Timing function (e.g., "ease", "linear", "cubic-bezier(0.1, 0.7, 1.0, 0.1)")
     */
    public void setTransitionDanceMoves(String timing) {
        put(TRANSITION_TIMING_FUNCTION, timing);
    }


    /**
     * Slides elements like they're on ice
     *
     * @param values Translation values (e.g., "100px", "50% 100px", "10px 20px 30px")
     */
    public void iceSkateSlide(String values) {
        put(TRANSLATE, values);
    }


    public String getTransitionDuration() {
        return get(TRANSITION_DURATION);
    }

    public String getTransitionProperty() {
        return get(TRANSITION_PROPERTY);
    }

    public String getTransitionTimingFunction() {
        return get(TRANSITION_TIMING_FUNCTION);
    }

    public String getTranslate() {
        return get(TRANSLATE);
    }


    private boolean isValidTimingFunction(String value) {
        return value.matches("^ease|ease-in|ease-out|ease-in-out|linear|step-start|step-end|steps\\(\\d+.*\\)|cubic-bezier\\(.*\\)$");
    }

    public String getTableAlignment() {
        return get(TABLE_ALIGN);
    }

    public void setTableAlignment(String align) {
        this.put(TABLE_ALIGN, align);
    }
}
