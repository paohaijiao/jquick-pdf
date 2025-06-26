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
import com.github.paohaijiao.render.JStyleRenderer;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.properties.*;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
/**
 * packageName com.github.paohaijiao.render.impl
 *
 * @author Martin
 * @version 1.0.0
 * @className JBaseRenderer
 * @date 2025/6/27
 * @description
 */
public abstract  class JBaseRenderer implements JStyleRenderer {

    public abstract void applyStyles(IElement element, JStyleAttributes styles);

    protected void applyCommonStyles(IElement element, JStyleAttributes styles) {
        if (styles.getBackgroundColor() != null) {
            Color bgColor = parseColor(styles.getBackgroundColor());
            ((BlockElement<?>) element).setBackgroundColor(bgColor);
        }

        if (styles.getBorder() != null) {
            ((BlockElement<?>) element).setBorder(parseBorder(styles.getBorder()));
        }

        if (styles.getMargin() != null) {
            float margin = Float.parseFloat(styles.getMargin().replace("px", ""));
            ((BlockElement<?>) element).setMargin(margin);
        }

        if (styles.getPadding() != null) {
            float padding = Float.parseFloat(styles.getPadding().replace("px", ""));
            ((BlockElement<?>) element).setPadding(padding);

        }

        if (element instanceof BlockElement) {
            BlockElement<?> blockElement = (BlockElement<?>) element;
            if (styles.getWidth() != null) {
                blockElement.setWidth(UnitValue.createPointValue(
                        Float.parseFloat(styles.getWidth().replace("px", ""))
                ));
            }
            if (styles.getHeight() != null) {
                blockElement.setHeight(UnitValue.createPointValue(
                        Float.parseFloat(styles.getHeight().replace("px", ""))
                ));
            }
        }
    }

    protected Color parseColor(String colorStr) {
        if (colorStr.startsWith("#")) {
            return new DeviceRgb(
                    Integer.valueOf(colorStr.substring(1, 3), 16),
                    Integer.valueOf(colorStr.substring(3, 5), 16),
                    Integer.valueOf(colorStr.substring(5, 7), 16)
            );
        } else if (colorStr.startsWith("rgb(")) {
            String[] rgb = colorStr.substring(4, colorStr.length() - 1).split(",");
            return new DeviceRgb(
                    Integer.parseInt(rgb[0].trim()),
                    Integer.parseInt(rgb[1].trim()),
                    Integer.parseInt(rgb[2].trim())
            );
        }
        return DeviceRgb.BLACK;
    }

    protected Border parseBorder(String borderStr) {
        String[] parts = borderStr.split(" ");
        float width = Float.parseFloat(parts[0].replace("px", ""));
        return new SolidBorder(parseColor(parts[2]), width);
    }
}
