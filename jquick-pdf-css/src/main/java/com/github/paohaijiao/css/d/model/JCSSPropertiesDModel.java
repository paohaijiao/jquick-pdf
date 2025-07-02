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
package com.github.paohaijiao.css.d.model;

import com.github.paohaijiao.css.c.model.JCSSPropertiesCModel;

/**
 * packageName com.github.paohaijiao.model.css
 *
 * @author Martin
 * @version 1.0.0
 * @className CSSPropertiesBModel
 * @date 2025/6/29
 * @description
 */
public class JCSSPropertiesDModel extends JCSSPropertiesCModel {
    public static final String DIRECTION = "direction";
    public static final String DISPLAY = "display";

    public void setDirection(String direction) {
        put(DIRECTION, direction);
    }

    public String getDirection() {
        return get(DIRECTION);
    }

    public void setDisplay(String display) {
        put(DISPLAY, display);
    }

    public String getDisplay() {
        return get(DISPLAY);
    }
}
