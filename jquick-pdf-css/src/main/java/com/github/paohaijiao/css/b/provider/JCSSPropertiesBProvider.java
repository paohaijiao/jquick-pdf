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

import com.github.paohaijiao.css.b.model.JCSSPropertiesBModel;
import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.kernel.pdf.xobject.PdfXObject;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.properties.BackgroundImage;
import com.itextpdf.layout.properties.BackgroundPosition;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.UnitValue;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesBProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {
    public static final String VISIBLE = "visible";
    public static final String HIDDEN = "hidden";
    public static final String INHERIT = "inherit";
    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        if (cssProperties.getBackdropFilter() != null) {
            buildBackdropFilter(style,cssProperties);
        }
        if (cssProperties.getBackdropFilter() != null) {
            buildBackfaceVisibility(style,cssProperties);
        }
        applyProperties(element,cssProperties);
        if (cssProperties.getBlockSize() != null) {
        }
        if (cssProperties.getBottom() != null) {

        }
        if (cssProperties.getBoxDecorationBreak() != null) {

        }
        if (cssProperties.getBoxReflect() != null) {

        }
        if (cssProperties.getBoxShadow() != null) {

        }
        if (cssProperties.getBoxSizing() != null) {

        }
        if (cssProperties.getBreakBefore() != null) {

        }
        if (cssProperties.getBreakAfter() != null) {

        }
        if (cssProperties.getBreakInside() != null) {

        }
        element.addStyle(style);
    }
    public void buildBackdropFilter(Style style,JCSSPropertiesCoreModel cssProperties){
        String filter = cssProperties.getBackdropFilter();
        if (filter.contains("blur")) {
            Matcher matcher = Pattern.compile("blur\\((\\d+)px\\)").matcher(filter);
            if (matcher.find()) {
                int blurRadius = Integer.parseInt(matcher.group(1));
                style.setOpacity(0.9f - (blurRadius * 0.02f));
            }
        } else if (filter.contains("brightness")) {
            Matcher matcher = Pattern.compile("blur\\((\\d+)px\\)").matcher(filter);
            if (matcher.find()) {
                int blurRadius = Integer.parseInt(matcher.group(1));
                style.setOpacity(0.9f - (blurRadius * 0.02f));
            }
        }
    }
    public void buildBackfaceVisibility(Style style, JCSSPropertiesCoreModel cssProperties) {
    }


    public static void applyProperties(BlockElement<?> element, JCSSPropertiesBModel cssProperties) throws MalformedURLException {
        Style style = new Style();
        if (element == null || cssProperties == null) {
            return;
        }
        if (cssProperties.getBackgroundColor() != null) {
            style.setBackgroundColor(parseColor(cssProperties.getBackgroundColor()));
        }

        if (cssProperties.getBackgroundImage() != null) {
            PdfXObject pdfXObject = new PdfImageXObject(ImageDataFactory.create(cssProperties.getBackgroundImage()));
            BackgroundImage backgroundImage = new BackgroundImage.Builder()
                    .setImage(pdfXObject)
                    //   .setBackgroundRepeat(parseBackgroundRepeat(cssProperties.getBackgroundRepeat()))
                    .setBackgroundPosition(parseBackgroundPosition(cssProperties.getBackgroundPosition()))
                    //  .setBackgroundSize(parseBackgroundSize(cssProperties.getBackgroundSize()))
                    .build();
            style.setBackgroundImage(backgroundImage);
        }

        if (cssProperties.getBackgroundAttachment() != null) {
        }

        if (cssProperties.getBackgroundBlendMode() != null) {
        }

        if (cssProperties.getBackgroundClip() != null) {
        }

        if (cssProperties.getBackgroundOrigin() != null) {
        }
//        applyBorderProperties(element, style);
//        applyBoxModelProperties(element, style);
//        applyOtherProperties(element, style);
    }

    public static PdfImageXObject createPdfXObject(PdfDocument pdfDoc, String imagePath) throws MalformedURLException {
        return new PdfImageXObject(ImageDataFactory.create(imagePath));
    }

    private static void applyBorderProperties(BlockElement<?> element, Style style, JCSSPropertiesBModel cssModel) {
        if (cssModel.getBorder() != null) {
            Border border = parseBorder(cssModel.getBorder());
            style.setBorder(border);
        }

        if (cssModel.getBorderTop() != null) {
            style.setBorderTop(parseBorder(cssModel.getBorderTop()));
        }
        if (cssModel.getBorderRight() != null) {
            style.setBorderRight(parseBorder(cssModel.getBorderRight()));
        }
        if (cssModel.getBorderBottom() != null) {
            style.setBorderBottom(parseBorder(cssModel.getBorderBottom()));
        }
        if (cssModel.getBorderLeft() != null) {
            style.setBorderLeft(parseBorder(cssModel.getBorderLeft()));
        }

        if (cssModel.getBorderRadius() != null) {
            BorderRadius borderRadius = parseBorderRadius(cssModel.getBorderRadius());
            style.setBorderRadius(borderRadius);
        }

        if (cssModel.getBorderTopLeftRadius() != null) {
            float radiusValue = Float.parseFloat(cssModel.getBorderTopLeftRadius());
            style.setBorderTopLeftRadius(new BorderRadius(UnitValue.createPointValue(radiusValue)));
        }
        if (cssModel.getBorderTopRightRadius() != null) {
            float radiusValue = Float.parseFloat(cssModel.getBorderTopRightRadius());
            style.setBorderTopRightRadius(new BorderRadius(UnitValue.createPointValue(radiusValue)));
        }
        if (cssModel.getBorderBottomRightRadius() != null) {
            float radiusValue = Float.parseFloat(cssModel.getBorderBottomRightRadius());
            style.setBorderBottomRightRadius(new BorderRadius(UnitValue.createPointValue(radiusValue)));
        }
        if (cssModel.getBorderBottomLeftRadius() != null) {
            float radiusValue = Float.parseFloat(cssModel.getBorderBottomLeftRadius());
            style.setBorderBottomLeftRadius(new BorderRadius(UnitValue.createPointValue(radiusValue)));
        }
    }

    private static void applyBoxModelProperties(BlockElement<?> element, Style style, JCSSPropertiesBModel cssModel) {
        if (cssModel.getBoxSizing() != null) {
            // style.setBoxSizing(BoxSizingPropertyValue.valueOf(cssModel.getBoxSizing().toUpperCase()));
        }

        if (cssModel.getBoxShadow() != null) {
        }
    }

    private static void applyOtherProperties(JCSSPropertiesBModel cssModel, Style style) {

    }


    private static BorderRadius parseBorderRadius(String borderRadiusValue) {
        // Implement parsing of border-radius property
        return new BorderRadius(UnitValue.createPointValue(0));
    }


    private static BackgroundPosition parseBackgroundPosition(String positionValue) {
        return null;
        // return new BackgroundPosition().setPositionX(50).setPositionY(50).setUnitX(BackgroundPosition.Unit.PERCENTAGE).setUnitY(BackgroundPosition.Unit.PERCENTAGE);
    }


//    private static BackgroundSize parseBackgroundSize(String sizeValue) {
//        if ("cover".equals(sizeValue)) {
//            return BackgroundSize.COVER;
//        } else if ("contain".equals(sizeValue)) {
//            return BackgroundSize.CONTAIN;
//        }
//        // Handle specific sizes
//        return BackgroundSize.AUTO;
//    }
}