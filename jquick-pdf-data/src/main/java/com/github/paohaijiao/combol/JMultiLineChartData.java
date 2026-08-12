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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 多折线图数据配置类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JMultiLineChartData implements JGraphData {

    /**
     * 基础配置
     */
    private int width = 800;

    private int height = 600;

    /**
     * 数据
     */
    private List<String> xAxisLabels;

    private List<LineData> lineDataList;

    /**
     * 标题配置
     */
    private String titleText;

    private String subtitleText;

    /**
     * 坐标轴配置
     */
    private String yAxisTitle;

    private int gridCount = 5;

    private boolean rotateXAxisLabels = false;

    /**
     * 颜色配置
     */
    private Color chartAreaColor = new Color(248, 249, 250);

    private Color axisColor = Color.BLACK;

    private Color gridColor = new Color(200, 200, 200);

    private Color textColor = Color.BLACK;

    private Color footerColor = new Color(128, 128, 128);


    /**
     * 字体配置
     */
    private Font titleFont = new Font("Microsoft YaHei", Font.BOLD, 18);

    private Font subtitleFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font axisFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font axisTitleFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font legendFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font dataLabelFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Font footerFont = new Font("Microsoft YaHei", Font.ITALIC, 10);


    /**
     * 数据点配置
     */
    private int pointRadius = 5;

    private int innerPointRadius = 2;

    private boolean showInnerPoint = true;

    private boolean showDataLabels = false;

    /**
     * 底部说明配置
     */
    private String footerText;


    /**
     * 格式化配置
     */
    private boolean valueWithPercent = false;

    /**
     * 内部状态
     */
    private double maxValue;

    private boolean autoCalculateMax = true;


    /**
     * 更新最大值
     */
    public void updateMaxValues() {
        if (!autoCalculateMax) {
            return;
        }
        double max = 0;
        if (lineDataList != null) {
            for (LineData lineData : lineDataList) {
                if (lineData.getValues() != null) {
                    for (Double value : lineData.getValues()) {
                        if (value != null && value > max) {
                            max = value;
                        }
                    }
                }
            }
        }
        // 添加10%的顶部空间
        this.maxValue = max * 1.1;
        if (this.maxValue <= 0) {
            this.maxValue = 100;
        }
    }

    /**
     * 获取数据点数量
     */
    public int getDataCount() {
        return xAxisLabels != null ? xAxisLabels.size() : 0;
    }

    /**
     * 折线数据内部类
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LineData {

        private String name;

        private String legendText;

        private List<Double> values;

        @Builder.Default
        private Color lineColor = Color.BLUE;

        @Builder.Default
        private float lineWidth = 2.0f;

        /**
         * 获取图例文字
         */
        public String getLegendText() {
            return legendText != null ? legendText : (name != null ? name : "");
        }
    }

    /**
     * 预定义颜色方案
     */
    public static class ColorScheme {
        public static final Color[] DEFAULT_COLORS = {
                new Color(66, 133, 244),
                new Color(234, 67, 53),
                new Color(52, 168, 83),
                new Color(251, 188, 5),
                new Color(104, 58, 183),
                new Color(255, 87, 34),
                new Color(0, 172, 193),
                new Color(233, 30, 99)
        };

        /**
         * 为折线数据自动分配颜色
         */
        public static void assignColors(List<LineData> lineDataList) {
            for (int i = 0; i < lineDataList.size(); i++) {
                LineData lineData = lineDataList.get(i);
                if (lineData.getLineColor() == null || lineData.getLineColor().equals(Color.BLUE)) {
                    lineData.setLineColor(DEFAULT_COLORS[i % DEFAULT_COLORS.length]);
                }
            }
        }
    }

    public static class JMultiLineChartDataBuilder {
        private final List<LineData> lineDataList = new ArrayList<>();

        public JMultiLineChartDataBuilder addLine(String name, List<Double> values) {
            return addLine(name, name, values, null, 2.0f);
        }

        public JMultiLineChartDataBuilder addLine(String name, String legendText, List<Double> values) {
            return addLine(name, legendText, values, null, 2.0f);
        }

        public JMultiLineChartDataBuilder addLine(String name, List<Double> values, Color color) {
            return addLine(name, name, values, color, 2.0f);
        }

        public JMultiLineChartDataBuilder addLine(String name, String legendText, List<Double> values, Color color, float lineWidth) {
            lineDataList.add(LineData.builder()
                    .name(name)
                    .legendText(legendText)
                    .values(values)
                    .lineColor(color != null ? color : ColorScheme.DEFAULT_COLORS[lineDataList.size() % ColorScheme.DEFAULT_COLORS.length])
                    .lineWidth(lineWidth)
                    .build());
            return this;
        }

        public JMultiLineChartData build() {
            JMultiLineChartData data = new JMultiLineChartData();
            data.setLineDataList(lineDataList);
            return data;
        }
    }
}
