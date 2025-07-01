///*
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
// */
//package com.github.paohaijiao.model.provider;
//
//import com.github.paohaijiao.model.css.JCSSPropertiesGModel;
//import com.itextpdf.layout.Style;
//import com.itextpdf.layout.properties.FlexDirection;
//import com.itextpdf.layout.properties.FlexWrap;
//import com.itextpdf.layout.properties.FontStyle;
//import com.itextpdf.layout.properties.Property;
//import com.itextpdf.layout.properties.UnitValue;
//import com.itextpdf.layout.properties.TextAlignment;
//import com.itextpdf.layout.properties.VerticalAlignment;
//import com.itextpdf.io.font.PdfEncodings;
//import com.itextpdf.io.font.constants.StandardFonts;
//import com.itextpdf.kernel.font.PdfFont;
//import com.itextpdf.kernel.font.PdfFontFactory;
//
//
///**
// * packageName com.github.paohaijiao.model.provider
// *
// * @author Martin
// * @version 1.0.0
// * @className JCSSPropertiesAProvider
// * @date 2025/7/1
// * @description
// */
//public class JCSSPropertiesGProvider extends JCSSPropertiesBaseProvider {
//    public static Style convertToStyle(JCSSPropertiesGModel cssModel) {
//        Style style = new Style();
//
//        // Filter property (not directly supported in iText)
//        if (cssModel.getFilter() != null) {
//            // Custom implementation would be needed for filter effects
//        }
//
//        // Flex properties
//        handleFlexProperties(style, cssModel);
//
//        // Float property (iText handles this differently than CSS)
//        if (cssModel.getFloat() != null) {
//            // iText doesn't have direct CSS float equivalent
//            // Could use setRelativePosition() for similar effects
//        }
//
//        // Font properties
//        handleFontProperties(style, cssModel);
//
//        // Font face rules need to be handled at document level
//        if (cssModel.getFontFaceRule() != null) {
//            // This would require registering the font with PdfDocument
//            // Not something we can do in a Style object
//        }
//
//        if (cssModel.getFontPaletteValuesRule() != null) {
//            // Font palette values not supported in iText
//        }
//
//        return style;
//    }
//
//    private static void handleFlexProperties(Style style, JCSSPropertiesGModel cssModel) {
//        // Flex shorthand property
//        if (cssModel.getFlex() != null) {
//            String[] flexValues = cssModel.getFlex().split("\\s+");
//            if (flexValues.length >= 1) {
//                style.setProperty(Property.FLEX_GROW, Float.parseFloat(flexValues[0]));
//            }
//            if (flexValues.length >= 2) {
//                style.setProperty(Property.FLEX_SHRINK, Float.parseFloat(flexValues[1]));
//            }
//            if (flexValues.length >= 3) {
//                style.setProperty(Property.FLEX_BASIS, UnitValue.parseUnitValue(flexValues[2]));
//            }
//        }
//
//        // Individual flex properties
//        if (cssModel.getFlexGrow() != null) {
//            style.setProperty(Property.FLEX_GROW, Float.parseFloat(cssModel.getFlexGrow()));
//        }
//
//        if (cssModel.getFlexShrink() != null) {
//            style.setProperty(Property.FLEX_SHRINK, Float.parseFloat(cssModel.getFlexShrink()));
//        }
//
//        if (cssModel.getFlexBasis() != null) {
//        }
//
//        if (cssModel.getFlexDirection() != null) {
//
//        }
//
//        if (cssModel.getFlexWrap() != null) {
//
//        }
//
//        // Flex-flow shorthand (combines flex-direction and flex-wrap)
//        if (cssModel.getFlexFlow() != null) {
//        }
//    }
//
//
//
//
//
//}