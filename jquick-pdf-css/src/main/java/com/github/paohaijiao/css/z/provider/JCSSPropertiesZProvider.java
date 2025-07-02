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
package com.github.paohaijiao.css.z.provider;

import com.github.paohaijiao.css.z.model.JCSSPropertiesZModel;
import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;
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
public class JCSSPropertiesZProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {


    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        applyZProperties(style, cssProperties);
        element.addStyle(style);
    }
    public Style applyZProperties(Style style, JCSSPropertiesZModel zModel) {
        if (zModel == null) {
            return style;
        }
        String zIndex = zModel.getZIndex();
        if (zIndex != null) {
            if ("9999".equals(zIndex)) {
                style.setProperty(Property.RENDERING_MODE, "last");
            } else if ("-1".equals(zIndex)) {
                style.setProperty(Property.RENDERING_MODE, "first");
            }
        }
        String zoom = zModel.getZoom();
        if (zoom != null) {
            try {
                AffineTransform transform = new AffineTransform();
                if (zoom.endsWith("%")) {
                    float percent = Float.parseFloat(zoom.substring(0, zoom.length() - 1));
                    float scale = percent / 100f;
                    transform.scale(scale, scale);
                } else if ("normal".equals(zoom) || "reset".equals(zoom)) {
                    transform.scale(1, 1);
                } else {
                    float scale = Float.parseFloat(zoom);
                    transform.scale(scale, scale);
                }
                style.setProperty(Property.TRANSFORM, transform);
            } catch (NumberFormatException e) {
                System.err.println("Invalid zoom value: " + zoom);
            }
        }
        return style;
    }

    public Style stackLikeAPokerDealer(Style style, String value) {
        return setZIndex(style, value);
    }

    public Style paparazziZoom(Style style, String value) {
        return setZoom(style, value);
    }
    public Style setZIndex(Style style, String value) {
        if (value.equals("auto") || value.matches("^-?\\d+$")) {
            if ("9999".equals(value)) {
                style.setProperty(Property.RENDERING_MODE, "last");
            } else if ("-1".equals(value)) {
                style.setProperty(Property.RENDERING_MODE, "first");
            }
        }
        return style;
    }
    public Style setZoom(Style style, String value) {
        if (value.equals("normal") || value.equals("reset") ||
                value.matches("^\\d+(\\.\\d+)?$") ||
                value.matches("^\\d+(\\.\\d+)?%$")) {
            try {
                AffineTransform transform = new AffineTransform();
                if (value.endsWith("%")) {
                    float percent = Float.parseFloat(value.substring(0, value.length() - 1));
                    float scale = percent / 100f;
                    transform.scale(scale, scale);
                } else if ("normal".equals(value) || "reset".equals(value)) {
                    transform.scale(1, 1);
                } else {
                    float scale = Float.parseFloat(value);
                    transform.scale(scale, scale);
                }
                style.setProperty(Property.TRANSFORM, transform);
            } catch (NumberFormatException e) {
                System.err.println("Invalid zoom value: " + value);
            }
        }
        return style;
    }

    public Style bringToFront(Style style) {
        return setZIndex(style, "9999");
    }

    public Style sendToBack(Style style) {
        return setZIndex(style, "-1");
    }

    public Style setZoom(Style style, float factor) {
        AffineTransform transform = new AffineTransform();
        transform.scale(factor, factor);
        style.setProperty(Property.TRANSFORM, transform);
        return style;
    }

}