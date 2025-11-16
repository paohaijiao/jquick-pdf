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
package com.github.paohaijiao.css.g.model;

import com.github.paohaijiao.css.f.model.JCSSPropertiesFModel;

import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.model.css
 *
 * @author Martin
 * @version 1.0.0
 * @className CSSPropertiesBModel
 * @date 2025/6/29
 * @description
 */
public class JCSSPropertiesGModel extends JCSSPropertiesFModel {
    public static final String GAP = "gap";
    public static final String GRID = "grid";
    public static final String GRID_AREA = "grid-area";
    public static final String GRID_AUTO_COLUMNS = "grid-auto-columns";
    public static final String GRID_AUTO_FLOW = "grid-auto-flow";
    public static final String GRID_AUTO_ROWS = "grid-auto-rows";
    public static final String GRID_COLUMN = "grid-column";
    public static final String GRID_COLUMN_END = "grid-column-end";
    public static final String GRID_COLUMN_START = "grid-column-start";
    public static final String GRID_ROW = "grid-row";
    public static final String GRID_ROW_END = "grid-row-end";
    public static final String GRID_ROW_START = "grid-row-start";
    public static final String GRID_TEMPLATE = "grid-template";
    public static final String GRID_TEMPLATE_AREAS = "grid-template-areas";
    public static final String GRID_TEMPLATE_COLUMNS = "grid-template-columns";
    public static final String GRID_TEMPLATE_ROWS = "grid-template-rows";

    private static final List<String> VALID_GRID_AUTO_FLOW_VALUES = Arrays.asList(
            "row", "column", "dense", "row dense", "column dense", "inherit", "initial", "unset"
    );

    public String getGap() {
        return get(GAP);
    }

    /**
     * Sets the gap between grid items (shorthand for row-gap and column-gap)
     *
     * @param value The gap value (e.g., "10px", "1em 2rem")
     */
    public void setGap(String value) {
        put(GAP, value);
    }

    public String getGrid() {
        return get(GRID);
    }

    /**
     * Sets the grid shorthand property
     *
     * @param value The grid value (e.g., "100px 1fr / 50px 1fr")
     */
    public void setGrid(String value) {
        put(GRID, value);
    }

    public String getGridArea() {
        return get(GRID_AREA);
    }

    /**
     * Sets the grid-area property
     *
     * @param value The grid-area value (e.g., "1 / 1 / 3 / 3")
     */
    public void setGridArea(String value) {
        put(GRID_AREA, value);
    }

    public String getGridAutoColumns() {
        return get(GRID_AUTO_COLUMNS);
    }

    /**
     * Sets the grid-auto-columns property
     *
     * @param value The grid-auto-columns value (e.g., "min-content", "1fr")
     */
    public void setGridAutoColumns(String value) {
        put(GRID_AUTO_COLUMNS, value);
    }

    public String getGridAutoFlow() {
        return get(GRID_AUTO_FLOW);
    }

    /**
     * Sets the grid-auto-flow property
     *
     * @param value Allowed values: row | column | dense | row dense | column dense
     */
    public void setGridAutoFlow(String value) {
        if (VALID_GRID_AUTO_FLOW_VALUES.contains(value)) {
            put(GRID_AUTO_FLOW, value);
        } else {
            throw new IllegalArgumentException("Invalid grid-auto-flow value: " + value);
        }
    }

    public String getGridAutoRows() {
        return get(GRID_AUTO_ROWS);
    }

    /**
     * Sets the grid-auto-rows property
     *
     * @param value The grid-auto-rows value (e.g., "min-content", "1fr")
     */
    public void setGridAutoRows(String value) {
        put(GRID_AUTO_ROWS, value);
    }

    public String getGridColumn() {
        return get(GRID_COLUMN);
    }

    /**
     * Sets the grid-column shorthand property
     *
     * @param value The grid-column value (e.g., "1 / 3")
     */
    public void setGridColumn(String value) {
        put(GRID_COLUMN, value);
    }

    public String getGridColumnEnd() {
        return get(GRID_COLUMN_END);
    }

    /**
     * Sets the grid-column-end property
     *
     * @param value The grid-column-end value (e.g., "2", "span 2")
     */
    public void setGridColumnEnd(String value) {
        put(GRID_COLUMN_END, value);
    }

    public String getGridColumnStart() {
        return get(GRID_COLUMN_START);
    }

    /**
     * Sets the grid-column-start property
     *
     * @param value The grid-column-start value (e.g., "1", "span 2")
     */
    public void setGridColumnStart(String value) {
        put(GRID_COLUMN_START, value);
    }

    public String getGridRow() {
        return get(GRID_ROW);
    }

    /**
     * Sets the grid-row shorthand property
     *
     * @param value The grid-row value (e.g., "1 / 3")
     */
    public void setGridRow(String value) {
        put(GRID_ROW, value);
    }

    public String getGridRowEnd() {
        return get(GRID_ROW_END);
    }

    /**
     * Sets the grid-row-end property
     *
     * @param value The grid-row-end value (e.g., "2", "span 2")
     */
    public void setGridRowEnd(String value) {
        put(GRID_ROW_END, value);
    }

    public String getGridRowStart() {
        return get(GRID_ROW_START);
    }

    /**
     * Sets the grid-row-start property
     *
     * @param value The grid-row-start value (e.g., "1", "span 2")
     */
    public void setGridRowStart(String value) {
        put(GRID_ROW_START, value);
    }

    public String getGridTemplate() {
        return get(GRID_TEMPLATE);
    }

    /**
     * Sets the grid-template shorthand property
     *
     * @param value The grid-template value (e.g., "100px 1fr / 50px 1fr")
     */
    public void setGridTemplate(String value) {
        put(GRID_TEMPLATE, value);
    }

    public String getGridTemplateAreas() {
        return get(GRID_TEMPLATE_AREAS);
    }

    /**
     * Sets the grid-template-areas property
     *
     * @param value The grid-template-areas value (e.g., "'header header' 'sidebar main'")
     */
    public void setGridTemplateAreas(String value) {
        put(GRID_TEMPLATE_AREAS, value);
    }

    public String getGridTemplateColumns() {
        return get(GRID_TEMPLATE_COLUMNS);
    }

    /**
     * Sets the grid-template-columns property
     *
     * @param value The grid-template-columns value (e.g., "100px 1fr", "repeat(3, 1fr)")
     */
    public void setGridTemplateColumns(String value) {
        put(GRID_TEMPLATE_COLUMNS, value);
    }

    public String getGridTemplateRows() {
        return get(GRID_TEMPLATE_ROWS);
    }

    /**
     * Sets the grid-template-rows property
     *
     * @param value The grid-template-rows value (e.g., "50px 1fr", "repeat(2, min-content)")
     */
    public void setGridTemplateRows(String value) {
        put(GRID_TEMPLATE_ROWS, value);
    }
}
