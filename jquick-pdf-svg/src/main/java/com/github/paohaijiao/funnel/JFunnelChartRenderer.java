package com.github.paohaijiao.funnel;/*
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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public class JFunnelChartRenderer extends JAbstractChartRenderer {

    private static final Color[] DEFAULT_COLORS = {
            new Color(12, 168, 223),
            new Color(255, 153, 77),
            new Color(80, 112, 221),
            new Color(182, 214, 52),
            new Color(80, 83, 114)
    };

    private static final Color TEXT_COLOR_DARK = new Color(51, 51, 51);

    private static final Color TEXT_COLOR_LIGHT = new Color(238, 238, 238);

    private static final Color LEGEND_TEXT_COLOR = new Color(84, 85, 90);

    @Override
    protected int getDefaultWidth() {
        return 872;
    }

    @Override
    protected int getDefaultHeight() {
        return 282;
    }

    @Override
    protected void drawChart(SVGGraphics2D g2d, JOption joption, int width, int height) {
        JFunnelOption option = joption.getFunnelOption();
        drawTitle(g2d, joption, width);
        drawTriangleFunnel(g2d, option, width, height);
        drawLegend(g2d, option, width, height);
    }

    private void drawTriangleFunnel(SVGGraphics2D g2d, JFunnelOption option, int width, int height) {
        List<Series> seriesList = option.series();
        if (seriesList == null || seriesList.isEmpty()) {
            return;
        }
        Series funnelSeries = seriesList.get(0);
        List<Object> data = funnelSeries.data();
        if (data == null || data.isEmpty()) {
            return;
        }
        Funnel funnelConfig = option.funnel() != null ? option.funnel() : new Funnel();
        int segmentCount = data.size();
        float centerX = width / 2f;
        float topY = funnelConfig.topY();
        float bottomY = funnelConfig.bottomY();
        float baseWidth = funnelConfig.width();
        float gap = funnelConfig.gap();
        float totalHeight = bottomY - topY;
        float segmentHeight = (totalHeight - gap * (segmentCount - 1)) / segmentCount;
        Color[] colors = getColors(option, segmentCount);
        for (int i = 0; i < segmentCount; i++) {
            float segmentTopY = topY + (segmentHeight + gap) * i;
            float segmentBottomY = segmentTopY + segmentHeight;
            float topWidth = baseWidth * (1 - (float) i / segmentCount);
            float bottomWidth = baseWidth * (1 - (float) (i + 1) / segmentCount);
            String label = getLabel(data.get(i));
            drawTriangleSegment(g2d, centerX, topWidth, bottomWidth, segmentTopY, segmentBottomY, colors[i], label, funnelConfig);
        }
    }

    private void drawTriangleSegment(SVGGraphics2D g2d, float centerX, float topWidth, float bottomWidth, float topY, float bottomY, Color color, String label, Funnel funnelConfig) {
        float topLeftX = centerX - topWidth / 2;
        float topRightX = centerX + topWidth / 2;
        float bottomLeftX = centerX - bottomWidth / 2;
        float bottomRightX = centerX + bottomWidth / 2;
        GeneralPath path = new GeneralPath();
        path.moveTo(topLeftX, topY);
        path.lineTo(topRightX, topY);
        path.lineTo(bottomRightX, bottomY);
        path.lineTo(bottomLeftX, bottomY);
        path.closePath();
        g2d.setColor(color);
        g2d.fill(path);
        g2d.setColor(funnelConfig.borderColor());
        g2d.setStroke(new BasicStroke(2f));
        g2d.draw(path);
        if (funnelConfig.showLabel()) {
            drawSegmentText(g2d, label, centerX, topY, bottomY, color, funnelConfig);
        }
    }

    private void drawSegmentText(SVGGraphics2D g2d, String label, float centerX, float topY, float bottomY, Color backgroundColor, Funnel funnelConfig) {
        float textY = (topY + bottomY) / 2;
        g2d.setFont(LABEL_FONT);
        Color textColor = funnelConfig.labelColor();
        if (textColor == null) {
            textColor = shouldUseLightText(backgroundColor) ? TEXT_COLOR_LIGHT : TEXT_COLOR_DARK;
        }
        if (textColor.equals(TEXT_COLOR_LIGHT)) {
            g2d.setColor(textColor);
            drawTextWithStroke(g2d, label, centerX, textY, Color.BLACK);
        } else {
            g2d.setColor(textColor);
            float textWidth = getStringWidth(g2d, label);
            g2d.drawString(label, centerX - textWidth / 2, textY);
        }
    }

    private void drawTextWithStroke(SVGGraphics2D g2d, String text, float centerX, float y, Color strokeColor) {
        Color originalColor = g2d.getColor();
        float textWidth = getStringWidth(g2d, text);
        float textX = centerX - textWidth / 2;
        g2d.setColor(strokeColor);
        g2d.setStroke(new BasicStroke(1f));
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    g2d.drawString(text, textX + dx, y + dy);
                }
            }
        }
        g2d.setColor(originalColor);
        g2d.drawString(text, textX, y);
    }

    private void drawLegend(SVGGraphics2D g2d, JFunnelOption option, int width, int height) {
        Legend legendConfig = option.legend();
        if (legendConfig == null || !legendConfig.show()) {
            return;
        }
        List<Series> seriesList = option.series();
        if (seriesList == null || seriesList.isEmpty()) {
            return;
        }
        Series funnelSeries = seriesList.get(0);
        List<Object> data = funnelSeries.data();
        if (data == null || data.isEmpty()) {
            return;
        }

        int itemCount = data.size();
        Color[] colors = getColors(option, itemCount);
        float startX = calculateLegendStartX(legendConfig, width, itemCount);
        float y = calculateLegendY(legendConfig, height);
        g2d.setFont(LABEL_FONT);
        for (int i = 0; i < itemCount; i++) {
            String label = getLabel(data.get(i));
            drawLegendItem(g2d, startX, y, colors[i], label);
            startX += 80; // 图例间距
        }
    }

    private float calculateLegendStartX(Legend legend, int width, int itemCount) {
        String position = legend.left();
        switch (position) {
            case "left":
                return 20f;
            case "right":
                return width - (itemCount * 80f) - 20f;
            case "center":
            default:
                return width / 2f - (itemCount * 80f) / 2f;
        }
    }

    private float calculateLegendY(Legend legend, int height) {
        String position = legend.top();
        switch (position) {
            case "top":
                return 20f;
            case "middle":
                return height / 2f;
            case "bottom":
            default:
                return height - 20f;
        }
    }

    private void drawLegendItem(SVGGraphics2D g2d, float x, float y, Color color, String label) {
        g2d.setColor(color);
        g2d.fillRoundRect((int) x, (int) y, 25, 14, 7, 7);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawRoundRect((int) x, (int) y, 25, 14, 7, 7);
        g2d.setColor(LEGEND_TEXT_COLOR);
        g2d.drawString(label, x + 30, y + 7);
    }

    private Color[] getColors(JFunnelOption option, int count) {
        Color[] configuredColors = option.colors();
        if (configuredColors != null && configuredColors.length >= count) {
            return configuredColors;
        }
        Color[] colors = new Color[count];
        for (int i = 0; i < count; i++) {
            if (i < DEFAULT_COLORS.length) {
                colors[i] = DEFAULT_COLORS[i];
            } else {
                colors[i] = generateColor(i);
            }
        }
        return colors;
    }

    private String getLabel(Object dataItem) {
        if (dataItem instanceof DataItem) {
            return ((DataItem) dataItem).name();
        } else if (dataItem instanceof String) {
            return (String) dataItem;
        } else if (dataItem instanceof Number) {
            return String.valueOf(dataItem);
        } else {
            return dataItem != null ? dataItem.toString() : "";
        }
    }

    private boolean shouldUseLightText(Color backgroundColor) {
        double luminance = (0.299 * backgroundColor.getRed() + 0.587 * backgroundColor.getGreen() + 0.114 * backgroundColor.getBlue()) / 255;
        return luminance < 0.5;
    }

    private Color generateColor(int index) {
        float hue = (index * 137.5f) % 360;
        return Color.getHSBColor(hue / 360, 0.7f, 0.8f);
    }

    private float getStringWidth(SVGGraphics2D g2d, String text) {
        return (float) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
    }
}