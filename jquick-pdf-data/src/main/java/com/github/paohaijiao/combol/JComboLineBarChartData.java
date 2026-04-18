package com.github.paohaijiao.combol;
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

import com.github.paohaijiao.graph.JGraphData;
import lombok.Setter;

import java.awt.*;
import java.util.List;

/**
 * 复合图表配置类 - 支持自适应布局
 * 包含条形图和折线图的所有配置信息
 */
@Setter
public class JComboLineBarChartData implements JGraphData {

    private int width = 800;

    private int height = 600;
    /**
     *边距配置
     */
    private int topMargin = 80;

    private int bottomMargin = 80;

    private int leftMargin = 70;

    private int rightMargin = 80;
    /**
     * 动态边距开关
     */
    private boolean autoAdjustMargins = true;

    /**
     * 最小边距
     */
    private int minTopMargin = 60;

    private int minBottomMargin = 70;

    private int minLeftMargin = 60;

    private int minRightMargin = 70;

    /**
     * 条形图动态尺寸配置
     */
    private int minBarWidth = 20;           // 最小条形宽度

    private int maxBarWidth = 50;            // 最大条形宽度

    private int minBarGap = 8;               // 最小条形间距

    private int maxBarGap = 30;              // 最大条形间距

    /**
     * 颜色配置
     */
    private Color backgroundColor = Color.WHITE;

    private Color chartAreaColor = new Color(248, 249, 250);

    private Color barColor = new Color(52, 152, 219);

    private Color lineColor = new Color(231, 76, 60);

    private final Color gridColor = new Color(224, 224, 224);

    private final Color axisColor = Color.BLACK;

    private final Color textColor = new Color(44, 62, 80);

    private final Color footerColor = new Color(149, 165, 166);

    /**
     * 字体配置
     */
    private Font titleFont = new Font("Microsoft YaHei", Font.BOLD, 18);

    private final Font subtitleFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font axisFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private final Font legendFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private final Font dataLabelFont = new Font("Microsoft YaHei", Font.BOLD, 10);

    private final Font axisTitleFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private final Font footerFont = new Font("Microsoft YaHei", Font.PLAIN, 10);

    /**
     * 条形图配置
     */
    private final int barCornerRadius = 4;
    /**
     * 是否显示条形图标签
     */
    private boolean showBarLabels = true;

    /**
     * 折线图配置
     */
    private final int pointRadius = 5;

    private final int innerPointRadius = 2;

    private final float lineStrokeWidth = 3.0f;

    private final int lineStrokeCap = BasicStroke.CAP_ROUND;

    private final int lineStrokeJoin = BasicStroke.JOIN_ROUND;

    private boolean showLineLabels = true;    // 是否显示折线图标签

    /**
     * 网格配置
     */
    private final int gridCount = 5;

    private final float gridStrokeWidth = 1.0f;

    /**
     * 标签偏移配置
     */
    private final int barLabelOffset = 5;

    private final int pointLabelOffsetUp = 5;

    private final int pointLabelOffsetDown = 12;

    private final int xAxisLabelOffset = 25;

    private final int legendItemWidth = 16;

    private final int legendItemHeight = 16;

    private final int legendSpacing = 22;

    private final int footerOffset = 15;

    /**
     * 坐标轴标题偏移配置
     */
    private final int leftAxisTitleOffset = 40;

    private final int rightAxisTitleOffset = 35;

    /**
     * 数据配置
     */
    private List<Double> barData;

    private List<Double> lineData;

    private List<String> xAxisLabels;

    private double maxBarValue = 100;

    private double maxLineValue = 100;

    private boolean autoCalculateMax = true;

    /**
     * 动态计算的值（运行时计算）
     */
    private transient int actualBarWidth;

    private transient int actualBarGap;

    private transient int actualStep;

    private transient int actualBarStartX;

    private transient int actualPointStartX;

    private transient int actualLeftMargin;

    private transient int actualRightMargin;

    /**
     * 文本配置
     */
    private String leftAxisTitle = "" ;

    private String rightAxisTitle = "" ;

    private String barLegendText = "";

    private String lineLegendText = "";

    private String footerText = "";

    /**
     * 标题配置
     */
    private String titleText = "";

    private String subtitleText = "";

    private final int titleOffset = 40;

    private final int subtitleOffset = 60;

    /**
     * 构造器
     */
    private JComboLineBarChartData() {
    }

    /**
     * Builder模式
     * @return
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 计算自适应布局参数
     * 根据数据量动态计算条形宽度、间距和边距
     */
    public void calculateAdaptiveLayout() {
        int dataCount = barData.size();
        int chartWidth = width - leftMargin - rightMargin;
        // 总宽度 = 数据数量 * (条形宽度 + 间距) - 间距 + 左右额外边距
        if (dataCount <= 0) {
            actualBarWidth = minBarWidth;
            actualBarGap = minBarGap;
        } else {
            int availableWidth = chartWidth - minBarGap;   // 可用宽度（左右各留出半个间距）
            // 计算总宽度占用
            int idealBarWidth = Math.min(maxBarWidth, Math.max(minBarWidth, availableWidth / (dataCount * 2)));
            // 根据条形宽度计算剩余空间用于间距
            int totalBarWidth = idealBarWidth * dataCount;
            int remainingWidth = availableWidth - totalBarWidth;
            if (remainingWidth >= 0) {
                actualBarWidth = idealBarWidth;
                if (dataCount > 1) {
                    actualBarGap = Math.min(maxBarGap, remainingWidth / (dataCount - 1));
                    // 如果间距小于最小值，则减小条形宽度
                    if (actualBarGap < minBarGap) {
                        actualBarGap = minBarGap;
                        int neededWidth = totalBarWidth + actualBarGap * (dataCount - 1);
                        if (neededWidth > availableWidth) {
                            actualBarWidth = (availableWidth - actualBarGap * (dataCount - 1)) / dataCount;
                        }
                    }
                } else {
                    actualBarGap = minBarGap;
                }
            } else {
                // 空间不足，使用最小间距，并压缩条形宽度
                actualBarGap = minBarGap;
                actualBarWidth = (availableWidth - actualBarGap * (dataCount - 1)) / dataCount;
                if (actualBarWidth < minBarWidth) {
                    actualBarWidth = minBarWidth;
                    // 如果还是不够，需要调整边距
                    if (autoAdjustMargins) {
                        int requiredWidth = actualBarWidth * dataCount + actualBarGap * (dataCount - 1);
                        int extraWidth = requiredWidth - availableWidth;
                        // 增加左右边距来容纳内容
                        int additionalMargin = (int) Math.ceil(extraWidth / 2.0);
                        actualLeftMargin = leftMargin + additionalMargin;
                        actualRightMargin = rightMargin + additionalMargin;
                    } else {
                        actualLeftMargin = leftMargin;
                        actualRightMargin = rightMargin;
                    }
                } else {
                    actualLeftMargin = leftMargin;
                    actualRightMargin = rightMargin;
                }
            }
        }

        actualStep = actualBarWidth + actualBarGap;
        actualBarStartX = actualLeftMargin + actualBarGap / 2;
        actualPointStartX = actualBarStartX + actualBarWidth / 2;
        // 确保X轴标签不超出边界
        int minXLabelMargin = 20;
        if (actualPointStartX + (dataCount - 1) * actualStep + 10 > width - minXLabelMargin) {
            // 内容超出，缩小条形宽度
            int overflow = (actualPointStartX + (dataCount - 1) * actualStep + 10) - (width - minXLabelMargin);
            int reduction = (int) Math.ceil((double) overflow / dataCount);
            actualBarWidth = Math.max(minBarWidth, actualBarWidth - reduction);
            actualStep = actualBarWidth + actualBarGap;
            actualPointStartX = actualBarStartX + actualBarWidth / 2;
        }
    }
    /**
     * 更新最大值（如果启用自动计算）
     */
    public void updateMaxValues() {
        if (autoCalculateMax) {
            if (barData != null && !barData.isEmpty()) {
                double max = barData.stream().max(Double::compare).orElse(0.0);
                // 智能计算Y轴最大值：如果最大值小于10，向上取整到2的倍数
                if (max < 10) {
                    maxBarValue = Math.ceil(max / 2) * 2;
                } else {
                    maxBarValue = Math.ceil(max / 10) * 10;
                }
                if (maxBarValue < 1) maxBarValue = 1;
            }
            if (lineData != null && !lineData.isEmpty()) {
                double max = lineData.stream().max(Double::compare).orElse(0.0);
                if (max < 20) {
                    maxLineValue = Math.ceil(max / 5) * 5;
                } else {
                    maxLineValue = Math.ceil(max / 20) * 20;
                }
                if (maxLineValue < 5) maxLineValue = 5;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public int getBottomMargin() {
        return bottomMargin;
    }

    public int getLeftMargin() {
        return actualLeftMargin > 0 ? actualLeftMargin : leftMargin;
    }

    public int getRightMargin() {
        return actualRightMargin > 0 ? actualRightMargin : rightMargin;
    }

    public int getMinTopMargin() {
        return minTopMargin;
    }

    public int getMinBottomMargin() {
        return minBottomMargin;
    }

    public int getMinLeftMargin() {
        return minLeftMargin;
    }

    public int getMinRightMargin() {
        return minRightMargin;
    }

    public boolean isAutoAdjustMargins() {
        return autoAdjustMargins;
    }

    public int getActualBarWidth() {
        return actualBarWidth > 0 ? actualBarWidth : minBarWidth;
    }

    public int getActualBarGap() {
        return actualBarGap > 0 ? actualBarGap : minBarGap;
    }

    public int getActualStep() {
        return actualStep > 0 ? actualStep : (minBarWidth + minBarGap);
    }

    public int getActualBarStartX() {
        return actualBarStartX > 0 ? actualBarStartX : (leftMargin + minBarGap / 2);
    }

    public int getActualPointStartX() {
        return actualPointStartX > 0 ? actualPointStartX : (getActualBarStartX() + getActualBarWidth() / 2);
    }

    public int getMinBarWidth() {
        return minBarWidth;
    }

    public int getMaxBarWidth() {
        return maxBarWidth;
    }

    public int getMinBarGap() {
        return minBarGap;
    }

    public int getMaxBarGap() {
        return maxBarGap;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getChartAreaColor() {
        return chartAreaColor;
    }

    public Color getBarColor() {
        return barColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public Color getGridColor() {
        return gridColor;
    }

    public Color getAxisColor() {
        return axisColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getFooterColor() {
        return footerColor;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public Font getSubtitleFont() {
        return subtitleFont;
    }

    public Font getAxisFont() {
        return axisFont;
    }

    public Font getLegendFont() {
        return legendFont;
    }

    public Font getDataLabelFont() {
        return dataLabelFont;
    }

    public Font getAxisTitleFont() {
        return axisTitleFont;
    }

    public Font getFooterFont() {
        return footerFont;
    }

    public int getBarWidth() {
        return getActualBarWidth();
    }

    public int getBarGap() {
        return getActualBarGap();
    }

    public int getBarCornerRadius() {
        return barCornerRadius;
    }

    public boolean isShowBarLabels() {
        return showBarLabels;
    }

    public int getPointRadius() {
        return pointRadius;
    }

    public int getInnerPointRadius() {
        return innerPointRadius;
    }

    public float getLineStrokeWidth() {
        return lineStrokeWidth;
    }

    public int getLineStrokeCap() {
        return lineStrokeCap;
    }

    public int getLineStrokeJoin() {
        return lineStrokeJoin;
    }

    public boolean isShowLineLabels() {
        return showLineLabels;
    }

    public Stroke getLineStroke() {
        return new BasicStroke(lineStrokeWidth, lineStrokeCap, lineStrokeJoin);
    }

    public int getGridCount() {
        return gridCount;
    }

    public float getGridStrokeWidth() {
        return gridStrokeWidth;
    }

    public int getBarLabelOffset() {
        return barLabelOffset;
    }

    public int getPointLabelOffsetUp() {
        return pointLabelOffsetUp;
    }

    public int getPointLabelOffsetDown() {
        return pointLabelOffsetDown;
    }

    public int getXAxisLabelOffset() {
        return xAxisLabelOffset;
    }

    public int getLegendItemWidth() {
        return legendItemWidth;
    }

    public int getLegendItemHeight() {
        return legendItemHeight;
    }

    public int getLegendSpacing() {
        return legendSpacing;
    }

    public int getFooterOffset() {
        return footerOffset;
    }

    public int getLeftAxisTitleOffset() {
        return leftAxisTitleOffset;
    }

    public int getRightAxisTitleOffset() {
        return rightAxisTitleOffset;
    }

    public List<Double> getBarData() {
        return barData;
    }

    public List<Double> getLineData() {
        return lineData;
    }

    public List<String> getXAxisLabels() {
        return xAxisLabels;
    }

    public int getDataCount() {
        return barData != null ? barData.size() : 0;
    }

    public double getMaxBarValue() {
        return maxBarValue;
    }

    public double getMaxLineValue() {
        return maxLineValue;
    }

    public boolean isAutoCalculateMax() {
        return autoCalculateMax;
    }

    public String getLeftAxisTitle() {
        return leftAxisTitle;
    }

    public String getRightAxisTitle() {
        return rightAxisTitle;
    }

    public String getBarLegendText() {
        return barLegendText;
    }

    public String getLineLegendText() {
        return lineLegendText;
    }

    public String getFooterText() {
        return footerText;
    }

    public String getTitleText() {
        return titleText;
    }

    public String getSubtitleText() {
        return subtitleText;
    }

    public int getTitleOffset() {
        return titleOffset;
    }

    public int getSubtitleOffset() {
        return subtitleOffset;
    }

    /**
     * 获取图表区域宽度
     */
    public int getChartAreaWidth() {
        return width - getLeftMargin() - getRightMargin();
    }

    /**
     * 获取图表区域高度
     */
    public int getChartAreaHeight() {
        return height - topMargin - bottomMargin;
    }

    /**
     * 获取图表区域顶部Y坐标
     */
    public int getChartTop() {
        return topMargin;
    }

    /**
     * 获取图表区域底部Y坐标
     */
    public int getChartBottom() {
        return height - bottomMargin;
    }

    /**
     * 获取图表区域左侧X坐标
     */
    public int getChartLeft() {
        return getLeftMargin();
    }

    /**
     * 获取图表区域右侧X坐标
     */
    public int getChartRight() {
        return width - getRightMargin();
    }

    public static class Builder {

        private final JComboLineBarChartData config = new JComboLineBarChartData();
        // 尺寸配置
        public Builder width(int width) {
            config.width = width;
            return this;
        }


        public Builder height(int height) {
            config.height = height;
            return this;
        }


        public Builder margins(int top, int bottom, int left, int right) {
            config.topMargin = top;
            config.bottomMargin = bottom;
            config.leftMargin = left;
            config.rightMargin = right;
            config.autoAdjustMargins = false;
            return this;
        }

        public Builder autoAdjustMargins(boolean auto) {
            config.autoAdjustMargins = auto;
            return this;
        }
        public Builder minMargins(int top, int bottom, int left, int right) {
            config.minTopMargin = top;
            config.minBottomMargin = bottom;
            config.minLeftMargin = left;
            config.minRightMargin = right;
            return this;
        }
        public Builder barSizeRange(int minWidth, int maxWidth, int minGap, int maxGap) {
            config.minBarWidth = minWidth;
            config.maxBarWidth = maxWidth;
            config.minBarGap = minGap;
            config.maxBarGap = maxGap;
            return this;
        }
        public Builder backgroundColor(Color color) {
            config.backgroundColor = color;
            return this;
        }
        public Builder chartAreaColor(Color color) {
            config.chartAreaColor = color;
            return this;
        }
        public Builder barColor(Color color) {
            config.barColor = color;
            return this;
        }
        public Builder lineColor(Color color) {
            config.lineColor = color;
            return this;
        }
        public Builder titleFont(Font font) {
            config.titleFont = font;
            return this;
        }
        public Builder axisFont(Font font) {
            config.axisFont = font;
            return this;
        }
        public Builder barData(List<Double> data) {
            config.barData = data;
            return this;
        }
        public Builder lineData(List<Double> data) {
            config.lineData = data;
            return this;
        }
        public Builder xAxisLabels(List<String> labels) {
            config.xAxisLabels = labels;
            return this;
        }
        public Builder maxBarValue(double maxValue) {
            config.maxBarValue = maxValue;
            config.autoCalculateMax = false;
            return this;
        }
        public Builder maxLineValue(double maxValue) {
            config.maxLineValue = maxValue;
            config.autoCalculateMax = false;
            return this;
        }
        public Builder autoCalculateMax(boolean auto) {
            config.autoCalculateMax = auto;
            return this;
        }
        public Builder showBarLabels(boolean show) {
            config.showBarLabels = show;
            return this;
        }
        public Builder showLineLabels(boolean show) {
            config.showLineLabels = show;
            return this;
        }
        public Builder leftAxisTitle(String title) {
            config.leftAxisTitle = title;
            return this;
        }
        public Builder rightAxisTitle(String title) {
            config.rightAxisTitle = title;
            return this;
        }
        public Builder barLegendText(String text) {
            config.barLegendText = text;
            return this;
        }
        public Builder lineLegendText(String text) {
            config.lineLegendText = text;
            return this;
        }
        public Builder footerText(String text) {
            config.footerText = text;
            return this;
        }
        public Builder title(String title, String subtitle) {
            config.titleText = title;
            config.subtitleText = subtitle;
            return this;
        }
        public JComboLineBarChartData build() {
            if (config.barData == null || config.barData.isEmpty()) {
                throw new IllegalStateException("Bar data cannot be null or empty");
            }
            if (config.lineData == null || config.lineData.isEmpty()) {
                throw new IllegalStateException("Line data cannot be null or empty");
            }
            if (config.xAxisLabels == null || config.xAxisLabels.isEmpty()) {
                throw new IllegalStateException("X axis labels cannot be null or empty");
            }
            if (config.barData.size() != config.lineData.size() ||
                    config.barData.size() != config.xAxisLabels.size()) {
                throw new IllegalStateException("Bar data, line data and x-axis labels must have the same size");
            }
            return config;
        }
    }
}