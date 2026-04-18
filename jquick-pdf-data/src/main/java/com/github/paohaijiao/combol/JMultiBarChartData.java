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
import java.util.List;

/**
 * 多重条形图数据模型
 */
@Data
public class JMultiBarChartData implements JGraphData {
    /**
     * 基础尺寸
     */
    private int width = 800;

    private int height = 600;

    private Color backgroundColor;

    private Color yAxisTextColor;

    private Color labelColor;

    private String xAxisTitle;

    /**
     * 标题相关
     */
    private String titleText;

    private String subtitleText;

    private Font titleFont = new Font("Microsoft YaHei", Font.BOLD, 18);

    private Font subtitleFont = new Font("Microsoft YaHei", Font.PLAIN, 13);

    /**
     * 坐标轴相关
     */
    private String yAxisTitle;

    private Font axisTitleFont = new Font("Microsoft YaHei", Font.PLAIN, 13);

    private Font axisFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Color axisColor = Color.BLACK;

    private Color textColor = Color.BLACK;

    private Color gridColor = new Color(200, 200, 200);

    private Color chartAreaColor = new Color(250, 250, 250);

    private int gridCount = 5;

    /**
     * 数据相关
     */
    private List<String> xAxisLabels;

    private List<BarData> barDataList;

    private double maxValue;

    private double minValue;

    /**
     * 条形样式
     */
    private boolean showBarBorder = false;

    private Color barBorderColor = Color.GRAY;

    private float barBorderWidth = 0.5f;

    /**
     * 间距比例（相对于组内可用宽度）
     */
    private double groupSpacingRatio = 0.2;   // 组间间距比例

    private double barSpacingRatio = 0.2;     // 组内柱子间距比例

    /**
     * 数据标签
     */
    private boolean showDataLabels = true;

    private Font dataLabelFont = new Font("Microsoft YaHei", Font.PLAIN, 10);

    private Color dataLabelInsideColor = Color.WHITE;

    // 图例相关
    private Font legendFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    // 底部说明
    private String footerText;

    private Font footerFont = new Font("Microsoft YaHei", Font.PLAIN, 10);

    private Color footerColor = new Color(150, 150, 150);

    // 数值格式化
    private boolean valueWithPercent = false;

    private boolean rotateXAxisLabels = false;

    /**
     * 更新最大最小值
     */
    public void updateMaxValues() {
        if (barDataList == null || barDataList.isEmpty()) {
            this.maxValue = 100;
            this.minValue = 0;
            return;
        }
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (BarData barData : barDataList) {
            for (Double value : barData.getValues()) {
                if (value > max) max = value;
                if (value < min) min = value;
            }
        }
        this.maxValue = max * 1.1; // 留出10%顶部空间
        this.minValue = Math.max(0, min * 0.9);
    }

    /**
     * 获取数据点数量
     */
    public int getDataCount() {
        return xAxisLabels != null ? xAxisLabels.size() : 0;
    }

    /**
     * 单条数据系列
     */
    @Data
    public static class BarData {
        private String legendText;      // 图例文字
        private Color barColor;         // 柱子颜色
        private Color labelColor;       // 数据标签颜色（可选，默认使用barColor）
        private List<Double> values;    // 数据值列表，长度与xAxisLabels一致
    }
}
