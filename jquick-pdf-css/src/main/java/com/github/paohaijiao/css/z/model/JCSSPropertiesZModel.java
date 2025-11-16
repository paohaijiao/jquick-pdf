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
package com.github.paohaijiao.css.z.model;

import com.github.paohaijiao.css.w.model.JCSSPropertiesWModel;

/**
 * packageName com.github.paohaijiao.model.css
 *
 * @author Martin
 * @version 1.0.0
 * @className CSSPropertiesBModel
 * @date 2025/6/29
 * @description
 */
public class JCSSPropertiesZModel extends JCSSPropertiesWModel {
    public static final String Z_INDEX = "z-index";
    public static final String ZOOM = "zoom";


    /**
     * Stacks elements like a poker dealer ("raise you to 9999!")
     *
     * @param value Integer value or "auto"
     */
    public void stackLikeAPokerDealer(String value) {
        if (value.equals("auto") || value.matches("^-?\\d+$")) {
            put(Z_INDEX, value);
        } else {
            throw new IllegalArgumentException("Invalid z-index value: " + value);
        }
    }

    /**
     * Zooms in/out like a paparazzi camera
     *
     * @param value Zoom factor (1.0 = normal) or percentage
     */
    public void paparazziZoom(String value) {
        if (value.equals("normal") || value.equals("reset") ||
                value.matches("^\\d+(\\.\\d+)?$") ||
                value.matches("^\\d+(\\.\\d+)?%$")) {
            put(ZOOM, value);
        } else {
            throw new IllegalArgumentException("Invalid zoom value: " + value);
        }
    }

    public String getZIndex() {
        return get(Z_INDEX);
    }

    /**
     * Sets z-index stacking order
     *
     * @param value Integer or "auto"
     */
    public void setZIndex(String value) {
        if (value.equals("auto") || value.matches("^-?\\d+$")) {
            put(Z_INDEX, value);
        } else {
            throw new IllegalArgumentException("Invalid z-index value: " + value);
        }
    }

    public String getZoom() {
        return get(ZOOM);
    }

    /**
     * Sets zoom magnification
     *
     * @param value Number (1.0 = normal), percentage, or "normal"/"reset"
     */
    public void setZoom(String value) {
        if (value.equals("normal") || value.equals("reset") ||
                value.matches("^\\d+(\\.\\d+)?$") ||
                value.matches("^\\d+(\\.\\d+)?%$")) {
            put(ZOOM, value);
        } else {
            throw new IllegalArgumentException("Invalid zoom value: " + value);
        }
    }

    /**
     * Convenience zoom method with float parameter
     */
    public void setZoom(float factor) {
        put(ZOOM, String.valueOf(factor));
    }

    /**
     * Convenience method for common z-index use case
     */
    public void bringToFront() {
        put(Z_INDEX, "9999");
    }

    /**
     * Convenience method for common z-index use case
     */
    public void sendToBack() {
        put(Z_INDEX, "-1");
    }

}
