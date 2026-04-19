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
package com.github.paohaijiao.combol.area;

import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;

import java.awt.*;
import java.util.List;

/**
 * 面积图配置数据结构
 */
@Data
public class JAreaChartData implements JGraphData {
    /**
     * 基础配置
     */
    private int width = 800;

    private int height = 500;

    private String title;

    private String subtitle;

    /**
     * 数据
     */
    private List<JSeriesData> seriesList;

    private List<String> xAxisLabels;
    /**
     * 主题配置
     */
    private JTheme theme = JTheme.DEFAULT;

    /**
     * 自定义颜色（优先级高于主题）
     */
    private Color areaStartColor;

    private Color areaEndColor;

    private Color lineColor;

    private Color backgroundColor;

    private Color gridColor;

    private Color axisColor;

    private Color textColor;

    private Color axisTextColor;

    /**
     * 轴标题
     */
    private String xAxisTitle;

    private String yAxisTitle;

    /**
     * 图例配置
     */
    private boolean showLegend = true;

    private String legendText;

    /**
     * 数据标签配置
     */
    private boolean showDataLabels = false;

    // 其他配置
    private int gridCount = 6;

    private double yAxisMin = 0;  // 0表示自动计算

    private double yAxisMax = 0;  // 0表示自动计算

    private boolean smoothLine = false;  // 是否平滑曲线

    // 字体配置
    private JFontConfig fonts = new JFontConfig();

    /**
     * 获取实际使用的颜色（自定义优先，否则使用主题）
     */
    public Color getEffectiveAreaStartColor() {
        if (areaStartColor != null) return areaStartColor;
        return theme.getAreaStartColor();
    }

    public Color getEffectiveAreaEndColor() {
        if (areaEndColor != null) return areaEndColor;
        return theme.getAreaEndColor();
    }

    public Color getEffectiveLineColor() {
        if (lineColor != null) return lineColor;
        return theme.getLineColor();
    }

    public Color getEffectiveBackgroundColor() {
        if (backgroundColor != null) return backgroundColor;
        return theme.getBackgroundColor();
    }

    public Color getEffectiveGridColor() {
        if (gridColor != null) return gridColor;
        return theme.getGridColor();
    }

    public Color getEffectiveAxisColor() {
        if (axisColor != null) return axisColor;
        return theme.getAxisColor();
    }

    public Color getEffectiveTextColor() {
        if (textColor != null) return textColor;
        return theme.getTextColor();
    }

    public Color getEffectiveAxisTextColor() {
        if (axisTextColor != null) return axisTextColor;
        return theme.getAxisTextColor();
    }

    /**
     * 计算Y轴范围
     */
    public double[] getYAxisRange() {
        if (yAxisMin != 0 || yAxisMax != 0) {
            return new double[]{yAxisMin, yAxisMax};
        }
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (JSeriesData series : seriesList) {
            for (double val : series.getValues()) {
                min = Math.min(min, val);
                max = Math.max(max, val);
            }
        }
        // 添加10%的边距
        double margin = (max - min) * 0.1;
        min = Math.max(0, min - margin);
        max = max + margin;
        return new double[]{min, max};
    }
}



