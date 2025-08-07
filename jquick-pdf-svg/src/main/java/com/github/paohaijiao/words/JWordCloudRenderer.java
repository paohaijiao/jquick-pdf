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
package com.github.paohaijiao.words;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * packageName com.github.paohaijiao.words
 * @author Martin
 * @version 1.0.0
 * @since 2025/8/7
 */
public class JWordCloudRenderer extends JAbstractChartRenderer {
    private static final Color[] WORD_COLORS = {
            new Color(65, 105, 225),
            new Color(220, 20, 60),
            new Color(34, 139, 34),
            new Color(218, 165, 32),
            new Color(148, 0, 211),
            new Color(255, 140, 0)
    };
    private static final int MARGIN = 20;
    private static final Random random = new Random();

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        svgGenerator.setPaint(BACKGROUND_COLOR);
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width);
        if (option.series() == null || option.series().isEmpty()) {
            return;
        }
        JWordCloudSeries wordCloudSeries = null;
        for (Object series : option.series()) {
            if (series instanceof JWordCloudSeries) {
                wordCloudSeries = (JWordCloudSeries) series;
                break;
            }
        }
        if (wordCloudSeries == null) {
            return;
        }
        List<JData> dataList = wordCloudSeries.data();
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        double[] weightRange = calculateWeightRange(dataList);
        double minWeight = weightRange[0];
        double maxWeight = weightRange[1];
        drawWordCloud(svgGenerator, dataList, width, height, minWeight, maxWeight,
                wordCloudSeries.minFontSize(), wordCloudSeries.maxFontSize(),
                wordCloudSeries.gridSize(), wordCloudSeries.rotationStep(),
                wordCloudSeries.rotationRange());
    }

    private double[] calculateWeightRange(List<JData> dataList) {
        double minWeight = Double.MAX_VALUE;
        double maxWeight = Double.MIN_VALUE;
        for (JData data : dataList) {
            double weight = ((Number) data.value()).doubleValue();
            minWeight = Math.min(minWeight, weight);
            maxWeight = Math.max(maxWeight, weight);
        }
        return new double[]{minWeight, maxWeight};
    }

    private void drawWordCloud(SVGGraphics2D svgGenerator, List<JData> dataList,
                               int width, int height, double minWeight, double maxWeight,
                               int minFontSize, int maxFontSize, int gridSize,
                               int rotationStep, int rotationRange) {
        int availableWidth = width - 2 * MARGIN;
        int availableHeight = height - 2 * MARGIN;
        dataList.sort((a, b) -> Double.compare(
                ((Number) b.value()).doubleValue(),
                ((Number) a.value()).doubleValue()
        ));
        WordCloudGrid grid = new WordCloudGrid(availableWidth, availableHeight, gridSize);
        for (JData data : dataList) {
            String word = data.name();
            double weight = ((Number) data.value()).doubleValue();
            int fontSize = calculateFontSize(weight, minWeight, maxWeight, minFontSize, maxFontSize);
            Font wordFont = new Font("Microsoft YaHei", Font.BOLD, fontSize);
            svgGenerator.setFont(wordFont);
            Color wordColor = WORD_COLORS[random.nextInt(WORD_COLORS.length)];
            svgGenerator.setPaint(wordColor);
            FontMetrics metrics = svgGenerator.getFontMetrics();
            int wordWidth = metrics.stringWidth(word);
            int wordHeight = metrics.getHeight();
            int rotation = rotationRange > 0 ?
                    (random.nextInt(rotationRange / rotationStep) * rotationStep - rotationRange / 2) : 0;
            Point position = grid.findPosition(wordWidth, wordHeight);
            if (position != null) {
                svgGenerator.translate(position.x + wordWidth/2, position.y + wordHeight/2);
                svgGenerator.rotate(Math.toRadians(rotation));
                svgGenerator.drawString(word, -wordWidth/2, wordHeight/2);
                svgGenerator.rotate(-Math.toRadians(rotation));
                svgGenerator.translate(-(position.x + wordWidth/2), -(position.y + wordHeight/2));
            }
        }
    }

    private int calculateFontSize(double weight, double minWeight, double maxWeight,
                                  int minFontSize, int maxFontSize) {
        if (maxWeight == minWeight) {
            return (minFontSize + maxFontSize) / 2;
        }
        double ratio = (weight - minWeight) / (maxWeight - minWeight);
        return minFontSize + (int) (ratio * (maxFontSize - minFontSize));
    }

    @Override
    protected int getDefaultWidth() {
        return 800;
    }

    @Override
    protected int getDefaultHeight() {
        return 600;
    }

    private static class WordCloudGrid {
        private final boolean[][] grid;
        private final int gridSize;
        private final int cols;
        private final int rows;

        public WordCloudGrid(int width, int height, int gridSize) {
            this.gridSize = gridSize;
            this.cols = width / gridSize;
            this.rows = height / gridSize;
            this.grid = new boolean[cols][rows];
        }

        public Point findPosition(int width, int height) {
            int requiredCols = (int) Math.ceil(width / (double) gridSize);
            int requiredRows = (int) Math.ceil(height / (double) gridSize);
            int centerX = cols / 2;
            int centerY = rows / 2;
            for (int radius = 0; radius < Math.max(cols, rows); radius++) {
                for (int x = Math.max(0, centerX - radius); x <= Math.min(cols - requiredCols, centerX + radius); x++) {
                    for (int y = Math.max(0, centerY - radius); y <= Math.min(rows - requiredRows, centerY + radius); y++) {
                        if (isAreaAvailable(x, y, requiredCols, requiredRows)) {
                            markAreaUsed(x, y, requiredCols, requiredRows);
                            return new Point(x * gridSize, y * gridSize);
                        }
                    }
                }
            }
            return null;
        }
        private boolean isAreaAvailable(int startX, int startY, int width, int height) {
            if (startX + width > cols || startY + height > rows) {
                return false;
            }

            for (int x = startX; x < startX + width; x++) {
                for (int y = startY; y < startY + height; y++) {
                    if (grid[x][y]) {
                        return false;
                    }
                }
            }
            return true;
        }
        private void markAreaUsed(int startX, int startY, int width, int height) {
            for (int x = startX; x < startX + width; x++) {
                for (int y = startY; y < startY + height; y++) {
                    grid[x][y] = true;
                }
            }
        }
    }
}
