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
package com.github.paohaijiao.treemap;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.series.JTreemap;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.treemap
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/8/7
 */
public class JTreeMapRenderer  extends JAbstractChartRenderer {

    private static final Font TITLE_FONT = new Font("Microsoft YaHei", Font.BOLD, 14);
    private static final Font LABEL_FONT = new Font("Microsoft YaHei", Font.PLAIN, 10);
    private static final int MARGIN = 20;
    private static final Color[] COLOR_PALETTE = {
            new Color(65, 105, 225),   // Royal Blue
            new Color(220, 20, 60),    // Crimson
            new Color(34, 139, 34),     // Forest Green
            new Color(255, 140, 0),     // Dark Orange
            new Color(138, 43, 226),   // Blue Violet
            new Color(0, 139, 139),     // Dark Cyan
            new Color(218, 165, 32),   // Golden Rod
            new Color(148, 0, 211)      // Dark Violet
    };

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        // 绘制背景和标题
        svgGenerator.setPaint(BACKGROUND_COLOR);
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width);

        // 检查数据有效性
        if (option.series() == null || option.series().isEmpty() || !(option.series().get(0) instanceof JTreemap)) {
            return;
        }

        JTreemap treemap = (JTreemap) option.series().get(0);
        List<JData> dataList = treemap.data();
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        // 计算总值
        double totalValue = calculateTotalValue(dataList);
        if (totalValue <= 0) return;

        // 计算绘图区域（留出边距和标题空间）
        int plotWidth = width - 2 * MARGIN;
        int plotHeight = height - 2 * MARGIN - 30;
        int plotX = MARGIN;
        int plotY = MARGIN + 30;

        // 绘制树状图（按类别分组）
        drawGroupedTreemap(svgGenerator, dataList, plotX, plotY, plotWidth, plotHeight, totalValue);
    }

    private double calculateTotalValue(List<JData> dataList) {
        return dataList.stream()
                .filter(data -> data.value() !=null)
                .mapToDouble(data ->(Double)data.value())
                .sum();
    }

    /**
     * 按类别分组的树状图绘制
     */
    private void drawGroupedTreemap(SVGGraphics2D svgGenerator, List<JData> dataList,
                                    int x, int y, int width, int height, double totalValue) {
        // 1. 按类别分组
        Map<String, List<JData>> categoryMap = new HashMap<>();
        for (JData data : dataList) {
                String category = (String) data.getCategory();
                categoryMap.computeIfAbsent(category, k -> new ArrayList<>()).add(data);
        }

        // 2. 计算每个类别的总值
        Map<String, Double> categoryValues = new HashMap<>();
        categoryMap.forEach((category, items) -> {
            double sum = items.stream()
                    .mapToDouble(item -> ((Map<String, Number>) item.value()).get("value").doubleValue())
                    .sum();
            categoryValues.put(category, sum);
        });

        // 3. 绘制分组矩形（使用Squarified算法）
        squarify(svgGenerator, new ArrayList<>(categoryValues.entrySet()), new ArrayList<>(),
                x, y, width, height, totalValue, true, categoryMap);
    }

    /**
     * Squarified Treemap 算法核心实现
     */
    private void squarify(SVGGraphics2D svgGenerator, List<Map.Entry<String, Double>> items,
                          List<Map.Entry<String, Double>> row, int x, int y, int width, int height,
                          double totalValue, boolean isHorizontal, Map<String, List<JData>> categoryMap) {
        if (items.isEmpty()) {
            if (!row.isEmpty()) {
                layoutRow(svgGenerator, row, x, y, width, height, totalValue, isHorizontal, categoryMap);
            }
            return;
        }

        Map.Entry<String, Double> item = items.get(0);
        List<Map.Entry<String, Double>> newRow = new ArrayList<>(row);
        newRow.add(item);

        // 计算当前行的高宽比是否更优
        double rowSum = newRow.stream().mapToDouble(Map.Entry::getValue).sum();
        double ratio = calculateAspectRatio(newRow, rowSum, width, height, isHorizontal);

        if (row.isEmpty() || ratio <= calculateAspectRatio(row, rowSum, width, height, isHorizontal)) {
            items.remove(0);
            squarify(svgGenerator, items, newRow, x, y, width, height, totalValue, isHorizontal, categoryMap);
        } else {
            layoutRow(svgGenerator, row, x, y, width, height, totalValue, isHorizontal, categoryMap);
            int newX = isHorizontal ? x : x + width;
            int newY = isHorizontal ? y + height : y;
            int newWidth = isHorizontal ? width : (int) (width * (1 - rowSum / totalValue));
            int newHeight = isHorizontal ? (int) (height * (1 - rowSum / totalValue)) : height;
            squarify(svgGenerator, items, new ArrayList<>(), newX, newY, newWidth, newHeight,
                    totalValue - rowSum, !isHorizontal, categoryMap);
        }
    }

    private double calculateAspectRatio(List<Map.Entry<String, Double>> row, double rowSum,
                                        int width, int height, boolean isHorizontal) {
        double area = rowSum;
        double length = isHorizontal ? width : height;
        double shortSide = area / length;
        double longSide = length;
        return Math.max(shortSide / longSide, longSide / shortSide);
    }

    private void layoutRow(SVGGraphics2D svgGenerator, List<Map.Entry<String, Double>> row,
                           int x, int y, int width, int height, double totalValue,
                           boolean isHorizontal, Map<String, List<JData>> categoryMap) {
        double rowSum = row.stream().mapToDouble(Map.Entry::getValue).sum();
        double ratio = rowSum / totalValue;

        int rowWidth = isHorizontal ? width : (int) (width * ratio);
        int rowHeight = isHorizontal ? (int) (height * ratio) : height;

        for (Map.Entry<String, Double> entry : row) {
            String category = entry.getKey();
            double value = entry.getValue();
            double itemRatio = value / rowSum;

            int itemWidth = isHorizontal ? (int) (rowWidth * itemRatio) : rowWidth;
            int itemHeight = isHorizontal ? rowHeight : (int) (rowHeight * itemRatio);

            // 绘制类别矩形
            Color color = COLOR_PALETTE[Math.abs(category.hashCode()) % COLOR_PALETTE.length];
            svgGenerator.setPaint(color);
            svgGenerator.fillRect(x, y, itemWidth, itemHeight);
            svgGenerator.setPaint(Color.BLACK);
            svgGenerator.drawRect(x, y, itemWidth, itemHeight);

            // 绘制子项（如果类别有多个数据）
            List<JData> items = categoryMap.get(category);
            if (items.size() > 1) {
                double subTotal = items.stream()
                        .mapToDouble(d -> ((Map<String, Number>) d.value()).get("value").doubleValue())
                        .sum();
                drawSquarifiedTreemap(svgGenerator, items, x, y, itemWidth, itemHeight, subTotal);
            } else {
                // 绘制标签
                String name = (String) ((Map<String, Object>) items.get(0).value()).get("name");
                drawLabel(svgGenerator, name, x, y, itemWidth, itemHeight);
            }

            // 更新位置
            if (isHorizontal) {
                x += itemWidth;
            } else {
                y += itemHeight;
            }
        }
    }

    /**
     * 自适应标签绘制（避免溢出）
     */
    private void drawLabel(SVGGraphics2D svgGenerator, String text, int x, int y, int width, int height) {
        if (width < 30 || height < 15) return; // 空间不足时不绘制

        svgGenerator.setPaint(Color.WHITE);
        FontMetrics metrics = svgGenerator.getFontMetrics();
        int textWidth = metrics.stringWidth(text);

        // 自动调整字体大小
        if (textWidth > width - 10) {
            float newSize = Math.max(8, LABEL_FONT.getSize() * (width - 10) / textWidth);
            svgGenerator.setFont(LABEL_FONT.deriveFont(newSize));
        }

        // 居中绘制
        int textX = x + (width - metrics.stringWidth(text)) / 2;
        int textY = y + (height + metrics.getAscent()) / 2;
        svgGenerator.drawString(text, textX, textY);
        svgGenerator.setFont(LABEL_FONT); // 恢复默认字体
    }

    @Override
    protected int getDefaultWidth() {
        return 800;
    }

    @Override
    protected int getDefaultHeight() {
        return 600;
    }
    /**
     * 绘制Squarified树状图（支持按类别分组）
     * @param svgGenerator SVG绘制器
     * @param dataList     数据列表
     * @param x            起始x坐标
     * @param y            起始y坐标
     * @param width        可用宽度
     * @param height       可用高度
     * @param totalValue   当前层级的总值
     */
    private void drawSquarifiedTreemap(SVGGraphics2D svgGenerator, List<JData> dataList,
                                       int x, int y, int width, int height, double totalValue) {
        // 1. 终止条件：无空间或无数据
        if (width <= 0 || height <= 0 || dataList.isEmpty()) return;

        // 2. 按值从大到小排序
        List<JData> sortedData = new ArrayList<>(dataList);
        sortedData.sort((a, b) -> Double.compare(
                ((Map<String, Number>) b.value()).get("value").doubleValue(),
                ((Map<String, Number>) a.value()).get("value").doubleValue()
        ));

        // 3. 递归划分矩形
        squarify(svgGenerator, sortedData, new ArrayList<>(), x, y, width, height, totalValue);
    }

    /**
     * Squarified算法核心：动态决定横向或纵向划分
     */
    private void squarify(SVGGraphics2D svgGenerator, List<JData> remainingItems,
                          List<JData> currentRow, int x, int y, int width, int height,
                          double remainingTotal) {
        if (remainingItems.isEmpty()) {
            if (!currentRow.isEmpty()) {
                layoutRow(svgGenerator, currentRow, x, y, width, height, remainingTotal);
            }
            return;
        }

        JData item = remainingItems.get(0);
        List<JData> newRow = new ArrayList<>(currentRow);
        newRow.add(item);

        // 计算当前行的高宽比是否更优
        double rowSum = calculateRowSum(newRow);
        double newRatio = calculateAspectRatio(newRow, rowSum, width, height);

        if (currentRow.isEmpty() || newRatio <= calculateAspectRatio(currentRow, rowSum, width, height)) {
            remainingItems.remove(0);
            squarify(svgGenerator, remainingItems, newRow, x, y, width, height, remainingTotal);
        } else {
            layoutRow(svgGenerator, currentRow, x, y, width, height, remainingTotal);
            double rowRatio = rowSum / remainingTotal;
            int newX, newY, newWidth, newHeight;
            if (width > height) { // 横向划分
                newX = x + (int) (width * rowRatio);
                newY = y;
                newWidth = (int) (width * (1 - rowRatio));
                newHeight = height;
            } else { // 纵向划分
                newX = x;
                newY = y + (int) (height * rowRatio);
                newWidth = width;
                newHeight = (int) (height * (1 - rowRatio));
            }
            squarify(svgGenerator, remainingItems, new ArrayList<>(), newX, newY, newWidth, newHeight,
                    remainingTotal - rowSum);
        }
    }

    /**
     * 计算当前行的总值
     */
    private double calculateRowSum(List<JData> row) {
        return row.stream()
                .mapToDouble(d -> ((Map<String, Number>) d.value()).get("value").doubleValue())
                .sum();
    }

    /**
     * 计算当前行的高宽比（越小越接近正方形）
     */
    private double calculateAspectRatio(List<JData> row, double rowSum, int width, int height) {
        boolean isHorizontal = width > height;
        double length = isHorizontal ? width : height;
        double area = rowSum;
        double shortSide = area / length;
        double longSide = length;
        return Math.max(shortSide / longSide, longSide / shortSide);
    }

    /**
     * 绘制当前行的所有矩形
     */
    private void layoutRow(SVGGraphics2D svgGenerator, List<JData> row,
                           int x, int y, int width, int height, double rowTotal) {
        boolean isHorizontal = width > height;
        double accumulated = 0;

        for (JData data : row) {
            Map<String, Object> node = (Map<String, Object>) data.value();
            double value = ((Number) node.get("value")).doubleValue();
            String name = (String) node.get("name");
            String category = (String) node.getOrDefault("category", "default");

            // 计算当前矩形的尺寸
            double ratio = value / rowTotal;
            int rectWidth = isHorizontal ? (int) (width * ratio) : width;
            int rectHeight = isHorizontal ? height : (int) (height * ratio);

            // 分配颜色（按类别）
            Color color = COLOR_PALETTE[Math.abs(category.hashCode()) % COLOR_PALETTE.length];
            svgGenerator.setPaint(color);
            svgGenerator.fillRect(x, y, rectWidth, rectHeight);
            svgGenerator.setPaint(Color.BLACK);
            svgGenerator.drawRect(x, y, rectWidth, rectHeight);

            // 绘制标签（自适应大小）
            if (rectWidth > 30 && rectHeight > 15) {
                drawLabel(svgGenerator, name, x, y, rectWidth, rectHeight);
            }

            // 更新位置
            if (isHorizontal) {
                x += rectWidth;
            } else {
                y += rectHeight;
            }
            accumulated += value;
        }
    }

}
