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
package com.github.paohaijiao.css.a.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.Property;

/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesAProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {

    public  void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        if (cssProperties.getAccentColor() != null) {
            //style.setProperty(Property.ACCENT_COLOR, cssProperties.getAccentColor());
            style.setFontColor(ColorConstants.BLUE);  // 使用预定义颜色
            // style.setFontColor(new DeviceRgb(0, 0, 255));  // 使用RGB值
        }

        if (cssProperties.getAlignContent() != null) {
            style.setProperty(Property.ALIGN_CONTENT, cssProperties.getAlignContent());
        }

        if (cssProperties.getAlignItems() != null) {
            style.setProperty(Property.ALIGN_ITEMS, cssProperties.getAlignItems());
        }

        if (cssProperties.getAlignSelf() != null) {
            style.setProperty(Property.ALIGN_SELF, cssProperties.getAlignSelf());
        }

        if (cssProperties.getAll() != null) {
            style.setFont(null);
            style.setFontSize(0);
            style.setFontColor(ColorConstants.BLACK);
            style.setBold();
            style.setItalic();
            style.setBackgroundColor(null);
            style.setBorder(null);
            style.setPadding(0);
            style.setMargin(0);
        }

        if (cssProperties.getAnimation() != null) {
//            style.setProperty(Property.ANIMATION, cssProperties.getAnimation());
            if ("fade-in".equals(cssProperties.getAnimationName())) {
                style.setOpacity(0.7f);
            } else if ("blink".equals(cssProperties.getAnimationName())) {
                style.setFontColor(ColorConstants.RED);
                style.setBackgroundColor(ColorConstants.YELLOW);
            } else {
                style.setBackgroundColor(new DeviceRgb(255, 255, 200));
                style.setBorder(new SolidBorder(ColorConstants.YELLOW, 1));
            }
        }

        if (cssProperties.getAnimationDelay() != null) {
            //style.setProperty(Property.ANIMATION_DELAY, cssProperties.getAnimationDelay());
            style.setOpacity(0.8f).setBackgroundColor(new DeviceRgb(240, 240, 240));
        }

        if (cssProperties.getAnimationDirection() != null) {
            switch (cssProperties.getAnimationDirection()) {
                case "normal":
                    style.setBorderLeft(new SolidBorder(ColorConstants.BLUE, 2));
                    break;
                case "reverse":
                    style.setBorderRight(new SolidBorder(ColorConstants.BLUE, 2));
                    break;
                case "alternate":
                    style.setBorderTop(new DashedBorder(ColorConstants.BLUE, 1));
                    style.setBorderBottom(new DashedBorder(ColorConstants.BLUE, 1));
                    break;
                //style.setProperty(Property.ANIMATION_DIRECTION, cssProperties.getAnimationDirection());
            }
        }
        if (cssProperties.getAnimationDuration() != null) {
            String duration = cssProperties.getAnimationDuration();
            int intensity = (int) Math.min(255 * Float.parseFloat(duration) / 2000, 200);
            style.setBackgroundColor(new DeviceRgb(255, 255, 255 - intensity));
            //style.setProperty(Property.ANIMATION_DURATION, cssProperties.getAnimationDuration());
        }

        if (cssProperties.getAnimationFillMode() != null) {
            // style.setProperty(Property.ANIMATION_FILL_MODE, cssProperties.getAnimationFillMode());
            switch (cssProperties.getAnimationFillMode()) {
                case "forwards":
                    style.setBackgroundColor(new DeviceRgb(240, 248, 255)) // 淡蓝色背景
                            .setBorder(new SolidBorder(ColorConstants.BLUE, 1));
                    break;
                case "backwards":
                    style.setOpacity(0.7f)
                            .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                    break;
                case "both":
                    style.setBackgroundColor(new DeviceRgb(255, 250, 240))
                            .setBorder(new DottedBorder(1));
                    break;
                default: // none
            }

            if (cssProperties.getAnimationIterationCount() != null) {
                //style.setProperty(Property.ANIMATION_ITERATION_COUNT, cssProperties.getAnimationIterationCount());
                String countText = cssProperties.getAnimationIterationCount().equals("infinite")
                        ? "∞"
                        : cssProperties.getAnimationIterationCount() + "time";
                Paragraph paragraph = new Paragraph("↻ " + countText)
                        .setFontSize(7)
                        .setFontColor(new DeviceRgb(100, 100, 100))
                        .setMarginLeft(5);
                addElement(element, paragraph);
            }

            if (cssProperties.getAnimationName() != null) {
                // style.setProperty(Property.ANIMATION_NAME, cssProperties.getAnimationName());
                Text animationLabel = new Text("[" + cssProperties.getAnimationName() + "]")
                        .setFontSize(7)
                        .setFontColor(new DeviceRgb(100, 100, 100));
                addElement(element, animationLabel);
            }

            if (cssProperties.getAnimationPlayState() != null) {
                // style.setProperty(Property.ANIMATION_PLAY_STATE, cssProperties.getAnimationPlayState());
                String icon = "";
                switch (cssProperties.getAnimationPlayState()) {
                    case "running":
                        icon = "▶";
                        break;
                    case "paused":
                        icon = "⏸";
                        break;
                    default:
                        icon = "⏹";
                }
                Paragraph paragraph = new Paragraph(icon + " " + cssProperties.getAnimationPlayState())
                        .setFontSize(7)
                        .setFontColor(new DeviceRgb(100, 100, 100))
                        .setMarginLeft(5);
                addElement(element, paragraph);
            }

            if (cssProperties.getAnimationTimingFunction() != null) {
                // style.setProperty(Property.ANIMATION_TIMING_FUNCTION, cssProperties.getAnimationTimingFunction());
                Text timingLabel = new Text("⏱ " + cssProperties.getAnimationTimingFunction())
                        .setFontSize(7)
                        .setFontColor(new DeviceRgb(120, 120, 120));
                addElement(element, timingLabel);
            }

            if (cssProperties.getAspectRatio() != null) {
                //style.setProperty(Property.ASPECT_RATIO, cssProperties.getAspectRatio());
                String[] ratio = cssProperties.getAspectRatio().split("/");
                float widthRatio = Float.parseFloat(ratio[0]);
                float heightRatio = Float.parseFloat(ratio[1]);
                element.setWidth(200);
                element.setHeight(200 * heightRatio / widthRatio);
            }
            element.addStyle(style);

        }
    }
}