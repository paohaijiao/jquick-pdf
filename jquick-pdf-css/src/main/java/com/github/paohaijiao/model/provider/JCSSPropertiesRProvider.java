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

import com.github.paohaijiao.model.css.JCSSPropertiesRModel;
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
public class JCSSPropertiesRProvider extends JCSSPropertiesBaseProvider {
    public static Style applyResize(Style style, String value) {
        // This would be a no-op in PDF rendering, but we can store it for potential HTML export
        //  style.setProperty(Property.RESIZE, value);
        return style;
    }

    public static Style applyRight(Style style, String value) {
        if ("auto".equalsIgnoreCase(value)) {
            // style.setRight(UnitValue.createPointValue(0)); // auto equivalent
        } else if (value.endsWith("%")) {
            float percent = Float.parseFloat(value.substring(0, value.length() - 1));
            //  style.setRight(UnitValue.createPercentValue(percent));
        } else if (value.endsWith("px")) {
            float points = Float.parseFloat(value.substring(0, value.length() - 2));
            //   style.setRight(UnitValue.createPointValue(points));
        } else if ("initial".equalsIgnoreCase(value) || "inherit".equalsIgnoreCase(value)) {
            // iText doesn't support these keywords directly, treat as auto
            //  style.setRight(UnitValue.createPointValue(0));
        }
        return style;
    }

    public static Style applyRotate(Style style, String value) {
        if ("none".equalsIgnoreCase(value)) {
            style.setRotationAngle(0);
        } else if ("x".equalsIgnoreCase(value)) {
            // Rotate around X-axis (not directly supported, would need 3D)
            // Fallback to simple rotation
            style.setRotationAngle(90);
        } else if ("y".equalsIgnoreCase(value)) {
            // Rotate around Y-axis (not directly supported)
            style.setRotationAngle(180);
        } else if ("z".equalsIgnoreCase(value)) {
            // Rotate around Z-axis (default rotation)
            style.setRotationAngle(45); // arbitrary default angle
        } else if (value.endsWith("deg")) {
            float degrees = Float.parseFloat(value.substring(0, value.length() - 3));
            style.setRotationAngle(degrees);
        } else if (value.endsWith("rad")) {
            float radians = Float.parseFloat(value.substring(0, value.length() - 3));
            style.setRotationAngle((float) Math.toDegrees(radians));
        }
        return style;
    }

    public static Style applyRowGap(Style style, String value) {
        if ("normal".equalsIgnoreCase(value)) {
            // Default gap
            // style.setProperty(Property.ROW_GAP, UnitValue.createPointValue(4)); // typical default
        } else if (value.endsWith("px")) {
            float points = Float.parseFloat(value.substring(0, value.length() - 2));
            // style.setProperty(Property.ROW_GAP, UnitValue.createPointValue(points));
        } else if (value.endsWith("em")) {
            // Assuming 1em = 12pt for this example
            float ems = Float.parseFloat(value.substring(0, value.length() - 2));
            //   style.setProperty(Property.ROW_GAP, UnitValue.createPointValue(ems * 12));
        } else if ("initial".equalsIgnoreCase(value) || "inherit".equalsIgnoreCase(value)) {
            //  style.setProperty(Property.ROW_GAP, UnitValue.createPointValue(4));
        }
        return style;
    }

    public static Style applyAllProperties(Style style, JCSSPropertiesRModel model) {
        if (model.getResize() != null) {
            applyResize(style, model.getResize());
        }
        if (model.getRight() != null) {
            applyRight(style, model.getRight());
        }
        if (model.getRotate() != null) {
            applyRotate(style, model.getRotate());
        }
        if (model.getRowGap() != null) {
            applyRowGap(style, model.getRowGap());
        }
        return style;
    }
}