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
package com.github.paohaijiao.model.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesEModel;
import com.github.paohaijiao.model.css.JCSSPropertiesFModel;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.UnitValue;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesFProvider extends JCSSPropertiesBaseProvider {

    public static Style convertToStyle(JCSSPropertiesFModel cssModel) {
        Style style = new Style();

        // Filter property
        if (cssModel.getFilter() != null) {
            // iText doesn't directly support CSS filters, you might need custom handling
        }

        // Flex properties
        if (cssModel.getFlex() != null) {
            style.setProperty(Property.FLEX_GROW, Float.parseFloat(cssModel.getFlex()));
            style.setProperty(Property.FLEX_SHRINK, 1f);
            style.setProperty(Property.FLEX_BASIS, UnitValue.createPointValue(0));
        }

        if (cssModel.getFlexBasis() != null) {
            //style.setProperty(Property.FLEX_BASIS, UnitValue.parseUnitValue(cssModel.getFlexBasis()));
        }

        if (cssModel.getFlexDirection() != null) {
//            style.setProperty(Property.FLEX_DIRECTION,
//                    FlexDirection.valueOf(cssModel.getFlexDirection().toUpperCase()));
        }

        if (cssModel.getFlexFlow() != null) {
            // flex-flow is shorthand for flex-direction and flex-wrap
            String[] parts = cssModel.getFlexFlow().split(" ");
            if (parts.length > 0) {
//                style.setProperty(Property.FLEX_DIRECTION,
//                        FlexDirection.valueOf(parts[0].toUpperCase()));
            }
            if (parts.length > 1) {
//                style.setProperty(Property.FLEX_WRAP,
//                        FlexWrap.valueOf(parts[1].toUpperCase()));
            }
        }

        if (cssModel.getFlexGrow() != null) {
            style.setProperty(Property.FLEX_GROW, Float.parseFloat(cssModel.getFlexGrow()));
        }

        if (cssModel.getFlexShrink() != null) {
            style.setProperty(Property.FLEX_SHRINK, Float.parseFloat(cssModel.getFlexShrink()));
        }

        if (cssModel.getFlexWrap() != null) {
//            style.setProperty(Property.FLEX_WRAP,
//                    FlexWrap.valueOf(cssModel.getFlexWrap().toUpperCase()));
        }

        // Float property
        if (cssModel.getFloat() != null) {
            // iText's float property is different from CSS float
            // You might need custom positioning logic here
        }

        // Font properties
        if (cssModel.getFont() != null) {
            // font is shorthand for multiple properties
            // Would need to parse the shorthand notation
        }

        if (cssModel.getFontFamily() != null) {
            style.setFontFamily(cssModel.getFontFamily());
        }

        if (cssModel.getFontFeatureSettings() != null) {
            // iText doesn't directly support font-feature-settings
        }

        if (cssModel.getFontKerning() != null) {
            // iText doesn't directly support font-kerning
        }

        if (cssModel.getFontSize() != null) {
          //  style.setFontSize(UnitValue.parseUnitValue(cssModel.getFontSize()));
        }

        if (cssModel.getFontSizeAdjust() != null) {
            // iText doesn't directly support font-size-adjust
        }

        if (cssModel.getFontStretch() != null) {
            // iText doesn't directly support font-stretch
        }

        if (cssModel.getFontStyle() != null) {
          //  style.setFontStyle(FontStyle.valueOf(cssModel.getFontStyle().toUpperCase()));
        }

        if (cssModel.getFontVariant() != null) {
            // iText doesn't directly support font-variant
        }

        if (cssModel.getFontVariantCaps() != null) {
            // iText doesn't directly support font-variant-caps
        }

        if (cssModel.getFontWeight() != null) {
//            style.setBold(cssModel.getFontWeight().equalsIgnoreCase("bold") ||
//                    cssModel.getFontWeight().equals("700"));
        }

        // Font face and palette values would need to be handled at document level
        // rather than as style properties

        return style;
    }

}