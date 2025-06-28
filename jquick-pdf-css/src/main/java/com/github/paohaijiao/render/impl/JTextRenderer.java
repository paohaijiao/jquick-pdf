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
package com.github.paohaijiao.render.impl;
import com.github.paohaijiao.model.JStyleAttributes;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Text;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.properties.TextAlignment;

/**
 * packageName com.github.paohaijiao.render.impl
 *
 * @author Martin
 * @version 1.0.0
 * @className JTextRenderer
 * @date 2025/6/27
 * @description
 */
public class JTextRenderer extends JBaseRenderer {
    @Override
    public void applyStyles(IElement element, JStyleAttributes styles) {
        super.applyCommonStyles(element, styles);
        Text text = (Text) element;
        if (styles.getColor() != null) {
            text.setFontColor(parseColor(styles.getColor()));
        }
        text.setFontSize(styles.getFontSize());
        if (styles.getTextAlign() != null) {
            TextAlignment alignment = TextAlignment.valueOf(
                    styles.getTextAlign().toUpperCase());
            text.setTextAlignment(alignment);
        }
    }
}
