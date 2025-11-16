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
package com.github.paohaijiao.funnel;
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

import java.awt.*;

public class Funnel {
    private float width = 700f;
    private float topY = 60f;
    private float bottomY = 222f;
    private float gap = 0f;
    private String sort = "descending"; // descending, ascending, none
    private boolean showLabel = true;
    private Color labelColor;
    private Color borderColor = Color.WHITE;

    public Funnel() {
    }

    public Funnel width(float width) {
        this.width = width;
        return this;
    }

    public Funnel topY(float topY) {
        this.topY = topY;
        return this;
    }

    public Funnel bottomY(float bottomY) {
        this.bottomY = bottomY;
        return this;
    }

    public Funnel gap(float gap) {
        this.gap = gap;
        return this;
    }

    public Funnel sort(String sort) {
        this.sort = sort;
        return this;
    }

    public Funnel showLabel(boolean showLabel) {
        this.showLabel = showLabel;
        return this;
    }

    public Funnel labelColor(Color labelColor) {
        this.labelColor = labelColor;
        return this;
    }

    public Funnel borderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    // Getters
    public float width() {
        return width;
    }

    public float topY() {
        return topY;
    }

    public float bottomY() {
        return bottomY;
    }

    public float gap() {
        return gap;
    }

    public String sort() {
        return sort;
    }

    public boolean showLabel() {
        return showLabel;
    }

    public Color labelColor() {
        return labelColor;
    }

    public Color borderColor() {
        return borderColor;
    }
}
