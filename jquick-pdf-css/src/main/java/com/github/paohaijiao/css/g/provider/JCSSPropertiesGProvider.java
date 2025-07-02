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
package com.github.paohaijiao.css.g.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.properties.UnitValue;

import java.util.Arrays;
import java.util.List;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesGProvider  extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {
    private static final List<String> VALID_GRID_AUTO_FLOW_VALUES = Arrays.asList(
            "row", "column", "dense", "row dense", "column dense", "inherit", "initial", "unset"
    );
    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();
        convertToStyle(element,cssProperties,style);
        element.addStyle(style);
    }
    /**
     * Converts JCSSPropertiesGModel properties to iText Style
     *
     * @param cssModel The model containing CSS grid properties
     * @return iText Style with all applicable grid properties set
     */
    public static void convertToStyle(BlockElement<?> element, JCSSPropertiesCoreModel cssModel,Style style) {
        if (cssModel.getGap() != null) {
            setGap(style, cssModel.getGap());
        }
        if (cssModel.getGrid() != null) {
            setGrid(style, cssModel.getGrid());
        }

        if (cssModel.getGridArea() != null) {
            //   style.setProperty(Property.GRID_AREA, cssModel.getGridArea());
        }

        if (cssModel.getGridAutoColumns() != null) {
            //     style.setProperty(Property.GRID_AUTO_COLUMNS,
            //           GridValue.createGridValue(cssModel.getGridAutoColumns()));
        }

        if (cssModel.getGridAutoFlow() != null) {
            if (VALID_GRID_AUTO_FLOW_VALUES.contains(cssModel.getGridAutoFlow())) {
                //     style.setProperty(Property.GRID_AUTO_FLOW, cssModel.getGridAutoFlow());
            }
        }

        if (cssModel.getGridAutoRows() != null) {
            //   style.setProperty(Property.GRID_AUTO_ROWS,
            //           GridValue.createGridValue(cssModel.getGridAutoRows()));
        }

        if (cssModel.getGridColumn() != null) {
            //   style.setProperty(Property.GRID_COLUMN, cssModel.getGridColumn());
        }

        if (cssModel.getGridColumnEnd() != null) {
            //    style.setProperty(Property.GRID_COLUMN_END, cssModel.getGridColumnEnd());
        }

        if (cssModel.getGridColumnStart() != null) {
            //   style.setProperty(Property.GRID_COLUMN_START, cssModel.getGridColumnStart());
        }

        if (cssModel.getGridRow() != null) {
            //   style.setProperty(Property.GRID_ROW, cssModel.getGridRow());
        }

        if (cssModel.getGridRowEnd() != null) {
            //   style.setProperty(Property.GRID_ROW_END, cssModel.getGridRowEnd());
        }

        if (cssModel.getGridRowStart() != null) {
            //    style.setProperty(Property.GRID_ROW_START, cssModel.getGridRowStart());
        }

        if (cssModel.getGridTemplate() != null) {
            setGridTemplate(style, cssModel.getGridTemplate());
        }

        if (cssModel.getGridTemplateAreas() != null) {
            //  style.setProperty(Property.GRID_TEMPLATE_AREAS, cssModel.getGridTemplateAreas());
        }

        if (cssModel.getGridTemplateColumns() != null) {
            //    style.setProperty(Property.GRID_TEMPLATE_COLUMNS,
            //           GridValue.createGridValue(cssModel.getGridTemplateColumns()));
        }

        if (cssModel.getGridTemplateRows() != null) {
            //  style.setProperty(Property.GRID_TEMPLATE_ROWS,
            //          GridValue.createGridValue(cssModel.getGridTemplateRows()));
        }
    }

    private static void setGap(Style style, String gapValue) {
        String[] parts = gapValue.split(" ");
        if (parts.length == 1) {
            UnitValue gap = parseUnitValue(parts[0]);
//            style.setProperty(Property.ROW_GAP, gap);
//            style.setProperty(Property.COLUMN_GAP, gap);
        } else if (parts.length == 2) {
            //  style.setProperty(Property.ROW_GAP, parseUnitValue(parts[0]));
            //  style.setProperty(Property.COLUMN_GAP, parseUnitValue(parts[1]));
        }
    }

    private static void setGrid(Style style, String gridValue) {
        // This is a simplified implementation - iText doesn't have direct grid shorthand support
        // You might need to parse the value into template rows/columns
        String[] parts = gridValue.split("/");
        if (parts.length == 2) {
//            style.setProperty(Property.GRID_TEMPLATE_ROWS,
//                    GridValue.createGridValue(parts[0].trim()));
//            style.setProperty(Property.GRID_TEMPLATE_COLUMNS,
//                    GridValue.createGridValue(parts[1].trim()));
        }
    }

    private static void setGridTemplate(Style style, String templateValue) {
        // Similar to grid shorthand
        String[] parts = templateValue.split("/");
        if (parts.length == 2) {
//            style.setProperty(Property.GRID_TEMPLATE_ROWS,
//                    GridValue.createGridValue(parts[0].trim()));
//            style.setProperty(Property.GRID_TEMPLATE_COLUMNS,
//                    GridValue.createGridValue(parts[1].trim()));
        }
    }



}