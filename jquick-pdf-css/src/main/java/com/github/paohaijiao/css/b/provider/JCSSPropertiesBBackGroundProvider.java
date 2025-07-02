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
package com.github.paohaijiao.css.b.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.layout.Style;

/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesBBackGroundProvider extends JCSSPropertiesBaseProvider {

    public void buildBackgroundStyle(Style style, JCSSPropertiesCoreModel cssProperties) {
        if (cssProperties.getBackgroundColor() != null) {
            Color backgroundColor = WebColors.getRGBColor(cssProperties.getBackgroundColor());
            style.setBackgroundColor(backgroundColor);
        }
        if (cssProperties.getBackgroundImage() != null) {
        }
        if (cssProperties.getBackgroundBlendMode() != null) {
        }
        if (cssProperties.getBackgroundClip() != null) {
        }
        if (cssProperties.getBackgroundColor() != null) {
        }
        if (cssProperties.getBackgroundOrigin() != null) {
        }
        if (cssProperties.getBackgroundPosition() != null) {
        }
        if (cssProperties.getBackgroundPositionX() != null) {
        }
        if (cssProperties.getBackgroundPositionY() != null) {
        }
        if (cssProperties.getBackgroundRepeat() != null) {
        }
        if (cssProperties.getBackgroundSize() != null) {
        }
    }
}