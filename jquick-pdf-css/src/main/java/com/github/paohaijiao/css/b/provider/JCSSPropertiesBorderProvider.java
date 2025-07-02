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
public class JCSSPropertiesBorderProvider extends JCSSPropertiesBaseProvider {

    public void buildBackgroundStyle(Style style, JCSSPropertiesCoreModel cssProperties) {
        if (cssProperties.getBorder() != null) {
        }
        if (cssProperties.getBorderBlock() != null) {
        }
        if (cssProperties.getBorderBlockColor() != null) {
        }
        if (cssProperties.getBorderBlockEnd() != null) {
        }
        if (cssProperties.getBorderBlockEndColor() != null) {
        }
        if (cssProperties.getBorderBlockEndStyle() != null) {
        }
        if (cssProperties.getBorderBlockEndWidth() != null) {
        }
        if (cssProperties.getBorderBlockStart() != null) {
        }
        if (cssProperties.getBorderBlockStartColor() != null) {
        }
        if (cssProperties.getBorderBlockStartStyle() != null) {
        }
        if (cssProperties.getBorderBlockStartWidth() != null) {
        }
        if (cssProperties.getBorderBottom() != null) {
        }
        if (cssProperties.getBorderBottomColor() != null) {
        }
        if (cssProperties.getBorderBottomLeftRadius() != null) {
        }
        if (cssProperties.getBorderBottomRightRadius() != null) {
        }
        if (cssProperties.getBorderBottomRightRadius() != null) {
        }
        if (cssProperties.getBorderBottomStyle() != null) {
        }
        if (cssProperties.getBorderBottomWidth() != null) {

        }
        if (cssProperties.getBorderCollapse() != null) {

        }
        if (cssProperties.getBorderColor() != null) {

        }
        if (cssProperties.getBorderColor() != null) {

        }
        if (cssProperties.getBorderEndEndRadius() != null) {

        }
        if (cssProperties.getBorderEndStartRadius() != null) {

        }
        if (cssProperties.getBorderImage() != null) {

        }
        if (cssProperties.getBorderImageOutset() != null) {

        }
        if (cssProperties.getBorderImageRepeat() != null) {

        }
        if (cssProperties.getBorderImageSlice() != null) {

        }
        if (cssProperties.getBorderImageSource() != null) {

        }
        if (cssProperties.getBorderImageWidth() != null) {

        }
        if (cssProperties.getBorderInline() != null) {

        }
        if (cssProperties.getBorderInlineColor() != null) {

        }
        if (cssProperties.getBorderInlineEnd() != null) {

        }
        if (cssProperties.getBorderInlineEndColor() != null) {

        }
        if (cssProperties.getBorderInlineEndStyle() != null) {

        }
        if (cssProperties.getBorderInlineEndWidth() != null) {

        }
        if (cssProperties.getBorderInlineStart() != null) {

        }
        if (cssProperties.getBorderInlineStartColor() != null) {

        }
        if (cssProperties.getBorderInlineStartStyle() != null) {

        }
        if (cssProperties.getBorderInlineStartWidth() != null) {

        }
        if (cssProperties.getBorderInlineStyle() != null) {

        }
        if (cssProperties.getBorderInlineWidth() != null) {

        }
        if (cssProperties.getBorderLeft() != null) {

        }
        if (cssProperties.getBorderLeftColor() != null) {

        }
        if (cssProperties.getBorderLeftStyle() != null) {

        }
        if (cssProperties.getBorderLeftWidth() != null) {

        }
        if (cssProperties.getBorderRadius() != null) {

        }
        if (cssProperties.getBorderRight() != null) {

        }
        if (cssProperties.getBorderRightColor() != null) {

        }
        if (cssProperties.getBorderRightStyle() != null) {

        }
        if (cssProperties.getBorderRightWidth() != null) {

        }
        if (cssProperties.getBorderSpacing() != null) {

        }
        if (cssProperties.getBorderStartEndRadius() != null) {

        }
        if (cssProperties.getBorderStartStartRadius() != null) {

        }
        if (cssProperties.getBorderStyle() != null) {

        }
        if (cssProperties.getBorderTop() != null) {

        }
        if (cssProperties.getBorderTopColor() != null) {

        }
        if (cssProperties.getBorderTopStyle() != null) {

        }
        if (cssProperties.getBorderTopWidth() != null) {

        }
        if (cssProperties.getBorderWidth() != null) {

        }
    }
    private float convertToFloat(String value) {
        if (value == null) return 0f;
        try {
            // Remove non-numeric characters (like 'px', 'pt', etc.)
            String numeric = value.replaceAll("[^0-9.]", "");
            return Float.parseFloat(numeric);
        } catch (NumberFormatException e) {
            return 0f;
        }
    }
}