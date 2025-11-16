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
package com.github.paohaijiao.bubble;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.axis.JAxis;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.series.JSeries;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AQI气泡图渲染器 - 完全基于JOption配置
 */
public class JBubbleChartRenderer extends JAbstractChartRenderer {


    private static final Color[] DEFAULT_AQI_COLORS = {new Color(102, 194, 165), // 优 - 绿色
            new Color(252, 194, 91),  // 良 - 黄色
            new Color(246, 138, 89),  // 轻度污染 - 橙色
            new Color(232, 96, 85),   // 中度污染 - 红色
            new Color(158, 42, 95)    // 重度污染 - 紫色
    };// 默认颜色配置

    private static final int MARGIN_LEFT = 80;// 默认边距配置
    private static final int MARGIN_RIGHT = 120;
    private static final int MARGIN_TOP = 60;
    private static final int MARGIN_BOTTOM = 80;


    private static final int MIN_BUBBLE_SIZE = 10;  // 默认气泡大小范围
    private static final int MAX_BUBBLE_SIZE = 60;

    @Override
    protected int getDefaultWidth() {
        return 900;
    }

    @Override
    protected int getDefaultHeight() {
        return 600;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawTitle(svgGenerator, option, width);
        int chartWidth = width - MARGIN_LEFT - MARGIN_RIGHT;// 计算图表区域
        int chartHeight = height - MARGIN_TOP - MARGIN_BOTTOM;
        drawAxes(svgGenerator, MARGIN_LEFT, MARGIN_TOP, chartWidth, chartHeight); // 绘制坐标轴
        drawAxisLabels(svgGenerator, option, MARGIN_LEFT, MARGIN_TOP, chartWidth, chartHeight);// 绘制坐标轴标签
        drawBubbles(svgGenerator, option, MARGIN_LEFT, MARGIN_TOP, chartWidth, chartHeight);// 绘制气泡数据
        drawLegend(svgGenerator, option, width - MARGIN_RIGHT + 20, MARGIN_TOP);// 绘制图例
        drawDescriptions(svgGenerator, option, MARGIN_LEFT, height - MARGIN_BOTTOM + 40, chartWidth);// 绘制说明文字
    }

    /**
     * 绘制坐标轴
     */
    private void drawAxes(SVGGraphics2D g2d, int x, int y, int width, int height) {
        g2d.setColor(AXIS_COLOR);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.draw(new Line2D.Double(x, y + height, x + width, y + height)); // X 轴
        g2d.draw(new Line2D.Double(x, y, x, y + height)); // Y 轴
        g2d.setColor(new Color(240, 240, 240));// 绘制网格线
        g2d.setStroke(new BasicStroke(1f));
        int yGridLines = 5; // Y 轴网格线
        for (int i = 0; i <= yGridLines; i++) {
            int yPos = y + height - (i * height / yGridLines);
            g2d.draw(new Line2D.Double(x, yPos, x + width, yPos));
        }
        int xGridLines = 10;// X 轴网格线
        for (int i = 0; i <= xGridLines; i++) {
            int xPos = x + (i * width / xGridLines);
            g2d.draw(new Line2D.Double(xPos, y, xPos, y + height));
        }
    }

    /**
     * 绘制坐标轴标签
     */
    private void drawAxisLabels(SVGGraphics2D g2d, JOption option, int x, int y, int width, int height) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(LABEL_FONT);
        List<String> yAxisLabels = getYAxisLabels(option);// Y 轴标签
        int yLabelsCount = yAxisLabels.size();
        for (int i = 0; i < yLabelsCount; i++) {
            int yPos = y + height - (i * height / (yLabelsCount - 1));
            g2d.drawString(yAxisLabels.get(i), x - 30, yPos + 4);
        }
        List<String> xAxisLabels = getXAxisLabels(option); // X 轴标签
        for (int i = 0; i < xAxisLabels.size(); i++) {
            int xPos = x + (i * width / (xAxisLabels.size() - 1));
            g2d.drawString(xAxisLabels.get(i), xPos - 10, y + height + 20);
        }
        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 14)); // 坐标轴标题
        String yAxisTitle = getYAxisTitle(option);
        String xAxisTitle = getXAxisTitle(option);
        g2d.drawString(yAxisTitle, x - 60, y - 10);
        g2d.drawString(xAxisTitle, x + width / 2 - 20, y + height + 50);
    }

    /**
     * 绘制气泡数据
     */
    private void drawBubbles(SVGGraphics2D g2d, JOption option, int x, int y, int width, int height) {
        List<Map<String, Object>> dataPoints = extractDataPoints(option);
        if (dataPoints.isEmpty()) {
            return;
        }
        double minX = 0;// 获取数据范围
        double maxX = dataPoints.size() - 1;
        double minY = getMinYValue(dataPoints);
        double maxY = getMaxYValue(dataPoints);
        double minSize = getMinSizeValue(dataPoints);
        double maxSize = getMaxSizeValue(dataPoints);

        for (int i = 0; i < dataPoints.size(); i++) {
            Map<String, Object> point = dataPoints.get(i);
            Number yValue = (Number) point.get("y");// 获取数据值
            Number sizeValue = (Number) point.get("size");
            Object category = point.get("category");
            if (yValue == null || sizeValue == null) continue;
            double xPos = x + (i * width / maxX);// 计算气泡位置
            double yPos = y + height - ((yValue.doubleValue() - minY) * height / (maxY - minY));
            double size = calculateBubbleSize(sizeValue.doubleValue(), minSize, maxSize); // 计算气泡大小
            Color bubbleColor = getBubbleColor(category, yValue.doubleValue());// 设置气泡颜色
            g2d.setColor(bubbleColor);// 绘制气泡
            g2d.fill(new Ellipse2D.Double(xPos - size / 2, yPos - size / 2, size, size));
            g2d.setColor(getBubbleBorderColor(bubbleColor));// 绘制气泡边框
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.draw(new Ellipse2D.Double(xPos - size / 2, yPos - size / 2, size, size));
            g2d.setColor(Color.BLACK);// 在气泡中显示数值
            g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
            String sizeText = String.format("%.0f", sizeValue.doubleValue());
            int textWidth = g2d.getFontMetrics().stringWidth(sizeText);
            g2d.drawString(sizeText, (float) (xPos - textWidth / 2), (float) (yPos + 4));
        }
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D g2d, JOption option, int x, int y) {
//        g2d.setColor(Color.BLACK);
//        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
//        g2d.drawString("图例", x, y - 10);
//        String[] levels = {"优", "良", "轻度污染", "中度污染", "重度污染"};
//        g2d.setFont(LABEL_FONT);
//        for (int i = 0; i < levels.length; i++) {
//            g2d.setColor(DEFAULT_AQI_COLORS[i]);
//            g2d.fill(new Rectangle2D.Double(x, y + i * 25, 15, 15));
//            g2d.setColor(Color.BLACK);
//            g2d.drawString(levels[i], x + 20, y + i * 25 + 12);
//        }
//        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
//        g2d.drawString("气泡大小:", x, y + levels.length * 25 + 20);
//        g2d.setFont(LABEL_FONT);
//        g2d.drawString("代表 PM2.5 浓度", x, y + levels.length * 25 + 40);
//        g2d.drawString("气泡越大", x + 10, y + levels.length * 25 + 60);
//        g2d.drawString("PM2.5 浓度越高", x + 10, y + levels.length * 25 + 80);
    }

    /**
     * 绘制说明文字
     */
    private void drawDescriptions(SVGGraphics2D g2d, JOption option, int x, int y, int width) {
        String description = getDescription(option);
        if (description == null || description.trim().isEmpty()) {
            return;
        }
        g2d.setColor(Color.BLACK);
        g2d.setFont(LABEL_FONT);
        FontMetrics fm = g2d.getFontMetrics();
        int lineHeight = fm.getHeight();
        int currentY = y;
        String[] words = description.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            String testLine = line + word + " ";
            if (fm.stringWidth(testLine) < width) {
                line.append(word).append(" ");
            } else {
                g2d.drawString(line.toString(), x, currentY);
                currentY += lineHeight;
                line = new StringBuilder(word + " ");
            }
        }
        g2d.drawString(line.toString(), x, currentY);
    }

    private List<String> getXAxisLabels(JOption option) {
        List<String> labels = new ArrayList<>();
        if (option.xAxis() != null && !option.xAxis().isEmpty()) {
            JAxis xAxis = option.xAxis().get(0);
            if (xAxis.data() != null) {
                for (Object data : xAxis.data()) {
                    labels.add(data.toString());
                }
            }
        }
        if (labels.isEmpty()) {// 如果没有配置标签，使用默认标签
            for (int i = 1; i <= 15; i++) {
                labels.add(String.format("01-%02d", i));
            }
        }
        return labels;
    }

    private List<String> getYAxisLabels(JOption option) {
        List<String> labels = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {// 生成Y轴标签 0-200，间隔40
            labels.add(String.valueOf(i * 40));
        }
        return labels;
    }

    private String getXAxisTitle(JOption option) {
        if (option.xAxis() != null && !option.xAxis().isEmpty()) {
            JAxis xAxis = option.xAxis().get(0);
            return xAxis.name() != null ? xAxis.name() : "日期";
        }
        return "X轴";
    }

    private String getYAxisTitle(JOption option) {
        if (option.yAxis() != null && !option.yAxis().isEmpty()) {
            JAxis yAxis = option.yAxis().get(0);
            return yAxis.name() != null ? yAxis.name() : "AQI数值";
        }
        return "Y轴";
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> extractDataPoints(JOption option) {
        List<Map<String, Object>> dataPoints = new ArrayList<>();
        if (option.series() != null && !option.series().isEmpty()) {
            JSeries series = option.series().get(0);
            if (series.data() != null) {
                for (Object data : series.data()) {
                    if (data instanceof Map) {
                        dataPoints.add((Map<String, Object>) data);
                    } else if (data instanceof BubbleDataPoint) {
                        // 处理 BubbleDataPoint 对象
                        BubbleDataPoint point = (BubbleDataPoint) data;
                        Map<String, Object> map = new HashMap<>();
                        map.put("x", point.getX());
                        map.put("y", point.getY());
                        map.put("size", point.getSize());
                        map.put("category", point.getCategory());
                        map.put("name", point.getName());
                        dataPoints.add(map);
                    }
                }
            }
        }
        return dataPoints;
    }

    private double getMinYValue(List<Map<String, Object>> dataPoints) {
        return dataPoints.stream()
                .map(point -> (Number) point.get("y"))
                .filter(num -> num != null)
                .mapToDouble(Number::doubleValue)
                .min()
                .orElse(0);
    }

    private double getMaxYValue(List<Map<String, Object>> dataPoints) {
        return dataPoints.stream()
                .map(point -> (Number) point.get("y"))
                .filter(num -> num != null)
                .mapToDouble(Number::doubleValue)
                .max()
                .orElse(100);
    }

    private double getMinSizeValue(List<Map<String, Object>> dataPoints) {
        return dataPoints.stream()
                .map(point -> (Number) point.get("size"))
                .filter(num -> num != null)
                .mapToDouble(Number::doubleValue)
                .min()
                .orElse(0);
    }

    private double getMaxSizeValue(List<Map<String, Object>> dataPoints) {
        return dataPoints.stream()
                .map(point -> (Number) point.get("size"))
                .filter(num -> num != null)
                .mapToDouble(Number::doubleValue)
                .max()
                .orElse(100);
    }

    private double calculateBubbleSize(double value, double minValue, double maxValue) {
        double normalized = (value - minValue) / (maxValue - minValue);
        return MIN_BUBBLE_SIZE + normalized * (MAX_BUBBLE_SIZE - MIN_BUBBLE_SIZE);
    }

    private Color getBubbleColor(Object category, double yValue) {
        if (category != null) {
            String categoryStr = category.toString();
            switch (categoryStr) {
                case "优":
                    return new Color(102, 194, 165, 180);
                case "良":
                    return new Color(252, 194, 91, 180);
                case "轻度污染":
                    return new Color(246, 138, 89, 180);
                case "中度污染":
                    return new Color(232, 96, 85, 180);
                case "重度污染":
                    return new Color(158, 42, 95, 180);
            }
        }
        if (yValue <= 50) return new Color(102, 194, 165, 180);
        else if (yValue <= 100) return new Color(252, 194, 91, 180);
        else if (yValue <= 150) return new Color(246, 138, 89, 180);
        else if (yValue <= 200) return new Color(232, 96, 85, 180);
        else return new Color(158, 42, 95, 180);
    }

    private Color getBubbleBorderColor(Color baseColor) {
        return new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 220);
    }

    private String getDescription(JOption option) {
        if (option.title() != null && option.title().subtext() != null) {
            return option.title().subtext();
        }
        return "";
    }
}