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
import com.itextpdf.kernel.pdf.tagutils.AccessibilityProperties;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.UnitValue;

/**
 * packageName com.github.paohaijiao.render.impl
 *
 * @author Martin
 * @version 1.0.0
 * @className JImageRenderer
 * @date 2025/6/27
 * @description
 */
public class JImageRenderer extends JBaseRenderer {
    @Override
    public void applyStyles(IElement element, JStyleAttributes styles) {
        //    super.applyCommonStyles(element, styles);
        Image image = (Image) element;
        AccessibilityProperties accessibilityProperties = image.getAccessibilityProperties();
        if (styles.getAlt() != null) {
            accessibilityProperties.setAlternateDescription(styles.getAlt());
        }
        if (styles.getTitle() != null) {
            accessibilityProperties.setActualText(styles.getTitle());
        }
        if (styles.getWidth() != null) {
            image.setWidth(UnitValue.createPointValue(
                    Float.parseFloat(styles.getWidth().replace("px", ""))
            ));
        }
        if (styles.getHeight() != null) {
            image.setHeight(UnitValue.createPointValue(
                    Float.parseFloat(styles.getHeight().replace("px", ""))
            ));
        }
        if (styles.get("opacity") != null) {
            try {
                float opacity = Float.parseFloat(styles.get("opacity"));
                image.setOpacity(opacity);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
