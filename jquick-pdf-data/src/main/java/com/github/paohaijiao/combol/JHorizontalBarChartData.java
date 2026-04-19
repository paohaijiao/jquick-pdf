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

/**
 * packageName com.github.paohaijiao.data
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/19
 */

import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 横向条形图数据模型
 */
@Data
public class JHorizontalBarChartData implements JGraphData {

    /**
     * 条形图数据项
     */
    @Data
    public static class BarData {

        private String legendText;           // 图例文本

        private List<Double> values;         // 数值列表，长度与Y轴标签数一致

        private Color barColor;              // 条形颜色

        private Color labelColor;            // 数据标签颜色（可选）

        public BarData(String legendText, List<Double> values, Color barColor) {
            this.legendText = legendText;
            this.values = values;
            this.barColor = barColor;
        }

        public BarData(String legendText, List<Double> values, Color barColor, Color labelColor) {
            this.legendText = legendText;
            this.values = values;
            this.barColor = barColor;
            this.labelColor = labelColor;
        }
    }

    // 画布尺寸
    private int width = 800;

    private int height = 500;
    // 标题相关
    private String titleText;

    private String subtitleText;
    // 坐标轴标题
    private String xAxisTitle;   // X轴标题（数值轴）

    private String yAxisTitle;   // Y轴标题（类别轴）
    // Y轴标签（类别名称）
    private List<String> yAxisLabels = new ArrayList<>();
    // 条形数据列表（支持多组并列）
    private List<BarData> barDataList = new ArrayList<>();
    // 最大值（自动计算）
    private double maxValue = 0;
    // 显示数据标签
    private boolean showDataLabels = true;
    // 数值是否带百分号
    private boolean valueWithPercent = false;
    // 网格线数量
    private int gridCount = 5;
    // 间距比例
    private double groupSpacingRatio = 0.2;   // 组间距比例

    private double barSpacingRatio = 0.3;     // 组内条形间距比例
    // 底部说明文本
    private String footerText;
    public static final Color COLOR_A = new Color(84, 112, 198);  // #5470c6

    public static final Color COLOR_B = new Color(250, 200, 88);  // #fac858

    public static final Color COLOR_C = new Color(238, 102, 102); // #ee6666

    public static final Color COLOR_BACKGROUND = new Color(249, 249, 249); // #f9f9f9

    public static final Color COLOR_GRID = new Color(221, 221, 221); // #ddd

    public static final Color COLOR_AXIS = new Color(102, 102, 102); // #666

    public static final Color COLOR_TEXT = new Color(51, 51, 51);    // #333

    public static final Color COLOR_LABEL = new Color(85, 85, 85);   // #555

    public static final Color COLOR_YAXIS_TEXT = new Color(136, 136, 136); // #888
    // 颜色配置
    private Color backgroundColor = COLOR_BACKGROUND;

    private Color gridColor = COLOR_GRID;

    private Color axisColor = COLOR_AXIS;

    private Color textColor =COLOR_TEXT;

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
        for (BarData barData : barDataList) {
            for (Double value : barData.getValues()) {
                if (value != null && value > maxValue) {
                    maxValue = value;
                }
            }
        }
        // 添加一些顶部留白
        if (maxValue > 0) {
            maxValue = maxValue * 1.05;
        }
    }

    /**
     * 获取数据条数（Y轴标签数量）
     */
    public int getDataCount() {
        return yAxisLabels != null ? yAxisLabels.size() : 0;
    }

    /**
     * 添加条形数据
     */
    public void addBarData(BarData barData) {
        this.barDataList.add(barData);
    }

    /**
     * 添加Y轴标签
     */
    public void addYAxisLabel(String label) {
        this.yAxisLabels.add(label);
    }
}
