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
package com.github.paohaijiao.css.a.model;

import java.util.HashMap;

/**
 * packageName com.github.paohaijiao.model.css
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAModel
 * @date 2025/6/29
 * @description
 */
public class JCSSPropertiesAModel extends HashMap<String, String> {
    public static final String ACCENT_COLOR = "accent-color";
    public static final String ALIGN_CONTENT = "align-content";
    public static final String ALIGN_ITEMS = "align-items";
    public static final String ALIGN_SELF = "align-self";
    public static final String ALL = "all";
    public static final String ANIMATION = "animation";
    public static final String ANIMATION_DELAY = "animation-delay";
    public static final String ANIMATION_DIRECTION = "animation-direction";
    public static final String ANIMATION_DURATION = "animation-duration";
    public static final String ANIMATION_FILL_MODE = "animation-fill-mode";
    public static final String ANIMATION_ITERATION_COUNT = "animation-iteration-count";
    public static final String ANIMATION_NAME = "animation-name";
    public static final String ANIMATION_PLAY_STATE = "animation-play-state";
    public static final String ANIMATION_TIMING_FUNCTION = "animation-timing-function";
    public static final String ASPECT_RATIO = "aspect-ratio";

    public void setAccentColor(String accentColor) {
        put(ACCENT_COLOR, accentColor);
    }

    public String getAccentColor() {
        return get(ACCENT_COLOR);
    }

    public void setAlignContent(String alignContent) {
        put(ALIGN_CONTENT, alignContent);
    }

    public String getAlignContent() {
        return get(ALIGN_CONTENT);
    }

    public void setAlignItems(String alignItems) {
        put(ALIGN_ITEMS, alignItems);
    }

    public String getAlignItems() {
        return get(ALIGN_ITEMS);
    }

    public void setAlignSelf(String alignSelf) {
        put(ALIGN_SELF, alignSelf);
    }

    public String getAlignSelf() {
        return get(ALIGN_SELF);
    }

    public void setAll(String all) {
        put(ALL, all);
    }

    public String getAll() {
        return get(ALL);
    }

    public void setAnimation(String animation) {
        put(ANIMATION, animation);
    }

    public String getAnimation() {
        return get(ANIMATION);
    }

    public void setAnimationDelay(String animationDelay) {
        put(ANIMATION_DELAY, animationDelay);
    }

    public String getAnimationDelay() {
        return get(ANIMATION_DELAY);
    }

    public void setAnimationDirection(String animationDirection) {
        put(ANIMATION_DIRECTION, animationDirection);
    }

    public String getAnimationDirection() {
        return get(ANIMATION_DIRECTION);
    }

    public void setAnimationDuration(String animationDuration) {
        put(ANIMATION_DURATION, animationDuration);
    }

    public String getAnimationDuration() {
        return get(ANIMATION_DURATION);
    }

    public void setAnimationFillMode(String animationFillMode) {
        put(ANIMATION_FILL_MODE, animationFillMode);
    }

    public String getAnimationFillMode() {
        return get(ANIMATION_FILL_MODE);
    }

    public void setAnimationIterationCount(String animationIterationCount) {
        put(ANIMATION_ITERATION_COUNT, animationIterationCount);
    }

    public String getAnimationIterationCount() {
        return get(ANIMATION_ITERATION_COUNT);
    }

    public void setAnimationName(String animationName) {
        put(ANIMATION_NAME, animationName);
    }

    public String getAnimationName() {
        return get(ANIMATION_NAME);
    }

    public void setAnimationPlayState(String animationPlayState) {
        put(ANIMATION_PLAY_STATE, animationPlayState);
    }

    public String getAnimationPlayState() {
        return get(ANIMATION_PLAY_STATE);
    }

    public void setAnimationTimingFunction(String animationTimingFunction) {
        put(ANIMATION_TIMING_FUNCTION, animationTimingFunction);
    }

    public String getAnimationTimingFunction() {
        return get(ANIMATION_TIMING_FUNCTION);
    }

    public void setAspectRatio(String aspectRatio) {
        put(ASPECT_RATIO, aspectRatio);
    }

    public String getAspectRatio() {
        return get(ASPECT_RATIO);
    }
}
