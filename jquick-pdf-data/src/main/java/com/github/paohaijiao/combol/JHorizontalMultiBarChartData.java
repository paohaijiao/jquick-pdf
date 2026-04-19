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
package com.github.paohaijiao.combol;


import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 横向多条形图数据模型
 * 支持多组系列数据并列显示（横向分组条形图）
 */
@Data
public class JHorizontalMultiBarChartData implements JGraphData {

    public static final Color COLOR_BACKGROUND = new Color(249, 249, 249); // #f9f9f9

    public static final Color COLOR_GRID = new Color(221, 221, 221);       // #ddd

    public static final Color COLOR_AXIS = new Color(102, 102, 102);       // #666

    public static final Color COLOR_TEXT = new Color(51, 51, 51);          // #333

    public static final Color COLOR_LABEL = new Color(85, 85, 85);         // #555

    public static final Color COLOR_YAXIS_TEXT = new Color(136, 136, 136); // #888

    // 画布尺寸
    private int width = 800;

    private int height = 500;

    // 标题相关
    private String titleText;

    private String subtitleText;
    // 坐标轴标题
    private String xAxisTitle;   // X轴标题（数值轴）

    private String yAxisTitle;   // Y轴标题（类别轴）
    // Y轴类别标签
    private List<String> categories = new ArrayList<>();
    // 系列数据列表（多组并列）
    private List<Series> seriesList = new ArrayList<>();
    // 最大值（自动计算）
    private double maxValue = 0;
    // 显示数据标签
    private boolean showDataLabels = true;
    // 数值是否带百分号
    private boolean valueWithPercent = false;
    // 网格线数量
    private int gridCount = 5;
    // 间距比例
    private double groupSpacingRatio = 0.2;   // 类别组间距比例

    private double barSpacingRatio = 0.3;     // 组内条形间距比例
    // 图例位置（true=顶部，false=底部）
    private boolean legendAtTop = true;
    // 底部说明文本
    private String footerText;
    // 颜色配置
    private Color backgroundColor = COLOR_BACKGROUND;

    private Color gridColor = COLOR_GRID;

    private Color axisColor = COLOR_AXIS;

    private Color textColor = COLOR_TEXT;

    private Color labelColor = COLOR_LABEL;

    private Color yAxisTextColor = COLOR_YAXIS_TEXT;

    private Color footerColor = COLOR_YAXIS_TEXT;
    // 字体配置
    private Font titleFont = new Font("Microsoft YaHei", Font.BOLD, 18);

    private Font subtitleFont = new Font("Microsoft YaHei", Font.PLAIN, 13);

    private Font axisFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font axisTitleFont = new Font("Microsoft YaHei", Font.PLAIN, 13);

    private Font legendFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font dataLabelFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Font footerFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    /**
     * 更新最大值
     */
    public void updateMaxValues() {
        maxValue = 0;
        for (Series series : seriesList) {
            if (series.getValues() != null) {
                for (Double value : series.getValues()) {
                    if (value != null && value > maxValue) {
                        maxValue = value;
                    }
                }
            }
        }
        // 添加一些顶部留白（10%）
        if (maxValue > 0) {
            maxValue = maxValue * 1.1;
        }
        // 如果是百分比，确保最大值不超过100
        if (valueWithPercent && maxValue > 100) {
            maxValue = 100;
        }
    }

    /**
     * 获取类别数量
     */
    public int getCategoryCount() {
        return categories != null ? categories.size() : 0;
    }

    /**
     * 获取系列数量
     */
    public int getSeriesCount() {
        return seriesList != null ? seriesList.size() : 0;
    }

    /**
     * 添加类别
     */
    public void addCategory(String category) {
        this.categories.add(category);
    }

    /**
     * 添加系列
     */
    public void addSeries(Series series) {
        this.seriesList.add(series);
    }

    /**
     * 批量添加系列
     */
    public void addSeries(String name, List<Double> values) {
        this.seriesList.add(new Series(name, values));
    }

    /**
     * 批量添加系列（带颜色）
     */
    public void addSeries(String name, List<Double> values, Color color) {
        this.seriesList.add(new Series(name, values, color));
    }

    /**
     * 系列数据（一组条形）
     */
    @Data
    public static class Series {

        private String name;                    // 系列名称（图例显示）

        private List<Double> values;            // 数值列表，长度与类别数一致

        private Color color;                    // 条形颜色（可选）

        private Color labelColor;               // 数据标签颜色（可选）

        public Series(String name, List<Double> values) {
            this.name = name;
            this.values = values;
        }

        public Series(String name, List<Double> values, Color color) {
            this.name = name;
            this.values = values;
            this.color = color;
        }

        public Series(String name, List<Double> values, Color color, Color labelColor) {
            this.name = name;
            this.values = values;
            this.color = color;
            this.labelColor = labelColor;
        }
    }
}
