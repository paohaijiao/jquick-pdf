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
public class JCorrelationMatrixOption {

    private Title title;

    private Grid grid;

    private Legend legend;

    private XAxis xAxis;

    private YAxis yAxis;

    private Dataset dataset;

    private Tooltip tooltip;

    public JCorrelationMatrixOption() {}

    public JCorrelationMatrixOption(Title title, Grid grid, Legend legend, XAxis xAxis, YAxis yAxis, Dataset dataset, Tooltip tooltip) {
        this.title = title;
        this.grid = grid;
        this.legend = legend;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.dataset = dataset;
        this.tooltip = tooltip;
    }

    public Title title() {
        return title;
    }

    public JCorrelationMatrixOption title(Title title) {
        this.title = title;
        return this;
    }

    public Grid grid() {
        return grid;
    }

    public JCorrelationMatrixOption grid(Grid grid) {
        this.grid = grid;
        return this;
    }

    public Legend legend() {
        return legend;
    }

    public JCorrelationMatrixOption legend(Legend legend) {
        this.legend = legend;
        return this;
    }

    public XAxis xAxis() {
        return xAxis;
    }

    public JCorrelationMatrixOption xAxis(XAxis xAxis) {
        this.xAxis = xAxis;
        return this;
    }

    public YAxis yAxis() {
        return yAxis;
    }

    public JCorrelationMatrixOption yAxis(YAxis yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    public Dataset dataset() {
        return dataset;
    }

    public JCorrelationMatrixOption dataset(Dataset dataset) {
        this.dataset = dataset;
        return this;
    }

    public Tooltip tooltip() {
        return tooltip;
    }

    public JCorrelationMatrixOption tooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public static JOptionCollectionMatrixBuilder builder() {
        return new JOptionCollectionMatrixBuilder();
    }

    /**
     * 标题配置
     */
    public static class Title {

        private String text;

        private String subtext;

        private String textStyle;

        public Title() {}

        public Title(String text, String subtext, String textStyle) {
            this.text = text;
            this.subtext = subtext;
            this.textStyle = textStyle;
        }

        public String text() {
            return text;
        }

        public Title text(String text) {
            this.text = text;
            return this;
        }

        public String subtext() {
            return subtext;
        }

        public Title subtext(String subtext) {
            this.subtext = subtext;
            return this;
        }

        public String textStyle() {
            return textStyle;
        }

        public Title textStyle(String textStyle) {
            this.textStyle = textStyle;
            return this;
        }
    }

    /**
     * 网格配置
     */
    public static class Grid {

        private int left;

        private int right;

        private int top;

        private int bottom;

        private boolean containLabel;

        public Grid() {}

        public Grid(int left, int right, int top, int bottom, boolean containLabel) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
            this.containLabel = containLabel;
        }

        public int left() {
            return left;
        }

        public Grid left(int left) {
            this.left = left;
            return this;
        }

        public int right() {
            return right;
        }

        public Grid right(int right) {
            this.right = right;
            return this;
        }

        public int top() {
            return top;
        }

        public Grid top(int top) {
            this.top = top;
            return this;
        }

        public int bottom() {
            return bottom;
        }

        public Grid bottom(int bottom) {
            this.bottom = bottom;
            return this;
        }

        public boolean containLabel() {
            return containLabel;
        }

        public Grid containLabel(boolean containLabel) {
            this.containLabel = containLabel;
            return this;
        }
    }

    /**
     * 图例配置
     */
    public static class Legend {
        private String[] data;
        private String position;
        private String orient;

        public Legend() {}

        public Legend(String[] data, String position, String orient) {
            this.data = data;
            this.position = position;
            this.orient = orient;
        }

        public String[] data() {
            return data;
        }

        public Legend data(String[] data) {
            this.data = data;
            return this;
        }

        public String position() {
            return position;
        }

        public Legend position(String position) {
            this.position = position;
            return this;
        }

        public String orient() {
            return orient;
        }

        public Legend orient(String orient) {
            this.orient = orient;
            return this;
        }
    }

    /**
     * X轴配置
     */
    public static class XAxis {
        private String type;

        private String[] data;

        private String name;

        private boolean boundaryGap;

        public XAxis() {}

        public XAxis(String type, String[] data, String name, boolean boundaryGap) {
            this.type = type;
            this.data = data;
            this.name = name;
            this.boundaryGap = boundaryGap;
        }

        public String type() {
            return type;
        }

        public XAxis type(String type) {
            this.type = type;
            return this;
        }

        public String[] data() {
            return data;
        }

        public XAxis data(String[] data) {
            this.data = data;
            return this;
        }

        public String name() {
            return name;
        }

        public XAxis name(String name) {
            this.name = name;
            return this;
        }

        public boolean boundaryGap() {
            return boundaryGap;
        }

        public XAxis boundaryGap(boolean boundaryGap) {
            this.boundaryGap = boundaryGap;
            return this;
        }
    }

    /**
     * Y轴配置
     */
    public static class YAxis {
        private String type;
        private String name;

        public YAxis() {}

        public YAxis(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String type() {
            return type;
        }

        public YAxis type(String type) {
            this.type = type;
            return this;
        }

        public String name() {
            return name;
        }

        public YAxis name(String name) {
            this.name = name;
            return this;
        }
    }

    /**
     * 数据集配置
     */
    public static class Dataset {

        private String source;

        private double[][] sourceArray;

        private String[] dimensions;

        public Dataset() {}

        public Dataset(String source, double[][] sourceArray, String[] dimensions) {
            this.source = source;
            this.sourceArray = sourceArray;
            this.dimensions = dimensions;
        }

        public String source() {
            return source;
        }

        public Dataset source(String source) {
            this.source = source;
            return this;
        }

        public double[][] sourceArray() {
            return sourceArray;
        }

        public Dataset sourceArray(double[][] sourceArray) {
            this.sourceArray = sourceArray;
            return this;
        }

        public String[] dimensions() {
            return dimensions;
        }

        public Dataset dimensions(String[] dimensions) {
            this.dimensions = dimensions;
            return this;
        }
    }

    /**
     * 提示框配置
     */
    public static class Tooltip {
        private String trigger;
        private String formatter;

        public Tooltip() {}

        public Tooltip(String trigger, String formatter) {
            this.trigger = trigger;
            this.formatter = formatter;
        }

        public String trigger() {
            return trigger;
        }

        public Tooltip trigger(String trigger) {
            this.trigger = trigger;
            return this;
        }

        public String formatter() {
            return formatter;
        }

        public Tooltip formatter(String formatter) {
            this.formatter = formatter;
            return this;
        }
    }
}
