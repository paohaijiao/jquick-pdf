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
package com.github.paohaijiao.builder;

import com.github.paohaijiao.model.JStyleCssModel;

/**
 * packageName com.github.paohaijiao.css
 *
 * @author Martin
 * @version 1.0.0
 * @className JElementStyleBuilder
 * @date 2025/6/24
 * @description
 */
public class JElementStyleBuilder {
    private JStyleCssModel style = new JStyleCssModel();

    public static JElementStyleBuilder create() {
        return new JElementStyleBuilder();
    }

    public JElementStyleBuilder withFont(String family, String size, String weight, String style) {
        this.style.setFontFamily(family);
        this.style.setFontSize(size);
        this.style.setFontWeight(weight);
        this.style.setFontStyle(style);
        return this;
    }

    public JElementStyleBuilder withColor(String color) {
        this.style.setColor(color);
        return this;
    }

    public JElementStyleBuilder withBackground(String color) {
        this.style.setBackgroundColor(color);
        return this;
    }

    public JElementStyleBuilder withMargin(String margin) {
        this.style.setMargin(margin);
        return this;
    }

    public JElementStyleBuilder withMargin(String top, String right, String bottom, String left) {
        this.style.setMarginTop(top);
        this.style.setMarginRight(right);
        this.style.setMarginBottom(bottom);
        this.style.setMarginLeft(left);
        return this;
    }

    public JElementStyleBuilder withPadding(String padding) {
        this.style.setPadding(padding);
        return this;
    }

    public JElementStyleBuilder withBorder(String border) {
        this.style.setBorder(border);
        return this;
    }

    public JElementStyleBuilder withTextAlign(String align) {
        this.style.setTextAlign(align);
        return this;
    }

    public JStyleCssModel build() {
        return this.style;
    }
}
