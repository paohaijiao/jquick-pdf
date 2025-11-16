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
package com.github.paohaijiao.css.u.provider;

import com.github.paohaijiao.css.u.model.JCSSPropertiesUModel;
import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.properties.BaseDirection;
import com.itextpdf.layout.properties.Property;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesUProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {

    private static final AtomicInteger PROPERTY_ID_GENERATOR = new AtomicInteger(10000 + 1);

    private static final int USER_SELECT_PROPERTY = PROPERTY_ID_GENERATOR.getAndIncrement();

    public static Style convertToStyle(JCSSPropertiesUModel cssModel, Style style) {
        if (cssModel.getUnicodeBidi() != null) {
            setUnicodeBidi(style, cssModel.getUnicodeBidi());
        }
        if (cssModel.getUserSelect() != null) {
            setUserSelectMetadata(style, cssModel.getUserSelect());
        }
        return style;
    }

    private static void setUnicodeBidi(Style style, String value) {
        switch (value.toLowerCase()) {
            case "embed":
            case "bidi-override":
            case "isolate":
            case "isolate-override":
                style.setProperty(Property.BASE_DIRECTION, BaseDirection.RIGHT_TO_LEFT);
                break;
            case "normal":
            case "plaintext":
            default:
                style.setProperty(Property.BASE_DIRECTION, BaseDirection.LEFT_TO_RIGHT);
                break;
        }
    }

    private static void setUserSelectMetadata(Style style, String value) {
        style.setProperty(USER_SELECT_PROPERTY, value);
    }

    public static String getUserSelectValue(Style style) {
        return (String) style.getProperty(USER_SELECT_PROPERTY);
    }

    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        convertToStyle(cssProperties, style);
        element.addStyle(style);
    }


}