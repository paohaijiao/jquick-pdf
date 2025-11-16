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

import java.awt.*;

public class Title {
    private String text;
    private String subtext;
    private String left = "center";
    private String top = "top";
    private Color textColor;
    private Color subtextColor;

    public Title() {
    }

    public Title text(String text) {
        this.text = text;
        return this;
    }

    public Title subtext(String subtext) {
        this.subtext = subtext;
        return this;
    }

    public Title left(String left) {
        this.left = left;
        return this;
    }

    public Title top(String top) {
        this.top = top;
        return this;
    }

    public Title textColor(Color textColor) {
        this.textColor = textColor;
        return this;
    }

    public Title subtextColor(Color subtextColor) {
        this.subtextColor = subtextColor;
        return this;
    }

    // Getters
    public String text() {
        return text;
    }

    public String subtext() {
        return subtext;
    }

    public String left() {
        return left;
    }

    public String top() {
        return top;
    }

    public Color textColor() {
        return textColor;
    }

    public Color subtextColor() {
        return subtextColor;
    }
}
