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
package com.github.paohaijiao.matrix;

/**
 * packageName com.github.paohaijiao.matrix
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/16
 */
public class JOptionCollectionMatrixBuilder {

    private JCorrelationMatrixOption.Title title;

    private JCorrelationMatrixOption.Grid grid;

    private JCorrelationMatrixOption.Legend legend;

    private JCorrelationMatrixOption.XAxis xAxis;

    private JCorrelationMatrixOption.YAxis yAxis;

    private JCorrelationMatrixOption.Dataset dataset;

    private JCorrelationMatrixOption.Tooltip tooltip;

    public JOptionCollectionMatrixBuilder() {}

    public JOptionCollectionMatrixBuilder title(JCorrelationMatrixOption.Title title) {
        this.title = title;
        return this;
    }

    public JOptionCollectionMatrixBuilder title(String text) {
        this.title = new JCorrelationMatrixOption.Title(text, null, null);
        return this;
    }

    public JOptionCollectionMatrixBuilder title(String text, String subtext) {
        this.title = new JCorrelationMatrixOption.Title(text, subtext, null);
        return this;
    }

    public JOptionCollectionMatrixBuilder grid(JCorrelationMatrixOption.Grid grid) {
        this.grid = grid;
        return this;
    }

    public JOptionCollectionMatrixBuilder grid(int left, int right, int top, int bottom) {
        this.grid = new JCorrelationMatrixOption.Grid(left, right, top, bottom, true);
        return this;
    }

    public JOptionCollectionMatrixBuilder legend(JCorrelationMatrixOption.Legend legend) {
        this.legend = legend;
        return this;
    }

    public JOptionCollectionMatrixBuilder legend(String[] data) {
        this.legend = new JCorrelationMatrixOption.Legend(data, "top", "horizontal");
        return this;
    }

    public JOptionCollectionMatrixBuilder xAxis(JCorrelationMatrixOption.XAxis xAxis) {
        this.xAxis = xAxis;
        return this;
    }

    public JOptionCollectionMatrixBuilder xAxis(String[] data) {
        this.xAxis = new JCorrelationMatrixOption.XAxis("category", data, null, true);
        return this;
    }

    public JOptionCollectionMatrixBuilder yAxis(JCorrelationMatrixOption.YAxis yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    public JOptionCollectionMatrixBuilder yAxis(String name) {
        this.yAxis = new JCorrelationMatrixOption.YAxis("value", name);
        return this;
    }

    public JOptionCollectionMatrixBuilder dataset(JCorrelationMatrixOption.Dataset dataset) {
        this.dataset = dataset;
        return this;
    }

    public JOptionCollectionMatrixBuilder dataset(double[][] sourceArray) {
        this.dataset = new JCorrelationMatrixOption.Dataset(null, sourceArray, null);
        return this;
    }

    public JOptionCollectionMatrixBuilder tooltip(JCorrelationMatrixOption.Tooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public JCorrelationMatrixOption build() {
        return new JCorrelationMatrixOption(title, grid, legend, xAxis, yAxis, dataset, tooltip);
    }
}
