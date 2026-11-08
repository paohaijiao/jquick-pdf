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
package com.github.paohaijiao.plugin.sankey;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.provider.spi.JQuickSpi;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;

/**
 * packageName com.github.paohaijiao.plugin.sankey
 *
 * @author Martin
 * @version 1.0.0
 * @className JSankeyChartRenderer
 * @date 2026/11/5
 * @description Sample third-party plugin renderer: sankey chart.
 * <p>
 * The class lives outside the released jar and is not declared in the chart type enum.
 * Only {@code name} and {@code type} are given, so the factory reaches it with
 * {@code renderChart("sankey", option)}. Adding a new chart therefore needs no change to
 * the library, just a jar on the classpath and a scan root that covers its package.
 */
@JQuickSpi(name = "sankey", type = "sankey", order = 200)
public class JSankeyChartRenderer extends JAbstractChartRenderer {

    private static final int CANVAS_WIDTH = 720;

    private static final int CANVAS_HEIGHT = 360;

    private static final String[] LEFT_LABELS = {"研发投入", "市场推广", "运营成本"};

    private static final int[] LEFT_UNITS = {40, 35, 25};

    private static final String[] RIGHT_LABELS = {"营收增长", "成本优化", "风险准备"};

    private static final int[] RIGHT_UNITS = {45, 30, 25};

    private static final int[][] FLOWS = {
            {0, 0, 25},
            {0, 2, 15},
            {1, 0, 20},
            {1, 1, 15},
            {2, 1, 15},
            {2, 2, 10}
    };

    private static final Color[] FLOW_COLORS = {
            new Color(0x90, 0xCA, 0xF9),
            new Color(0xA5, 0xD6, 0xA7),
            new Color(0xFF, 0xE0, 0x82),
            new Color(0xCE, 0x93, 0xD8),
            new Color(0x80, 0xCB, 0xC4),
            new Color(0xFF, 0xAB, 0x91)
    };

    private static final Color NODE_COLOR = new Color(0x34, 0x49, 0x5E);

    private static final Color LABEL_COLOR = new Color(0x2C, 0x3E, 0x50);

    private static final Font LABEL_FONT_PLAIN = new Font("Microsoft YaHei", Font.PLAIN, 13);

    @Override
    protected int getDefaultWidth() {
        return CANVAS_WIDTH;
    }

    @Override
    protected int getDefaultHeight() {
        return CANVAS_HEIGHT;
    }

    @Override
    protected void drawChart(SVGGraphics2D g2d, JOption option, int width, int height) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawTitle(g2d, option, width);
        boolean titled = option.title() != null && option.title().text() != null;
        int top = titled ? 110 : 50;
        int usable = height - top - 40;
        int total = sum(LEFT_UNITS);
        double unit = (double) usable / total;
        int nodeWidth = 14;
        int leftX = 110;
        int rightX = width - 130;
        int[] leftOffsets = offsets(LEFT_UNITS, unit, top);
        int[] rightOffsets = offsets(RIGHT_UNITS, unit, top);
        g2d.setFont(LABEL_FONT_PLAIN);
        g2d.setPaint(LABEL_COLOR);
        for (int i = 0; i < LEFT_LABELS.length; i++) {
            int textY = leftOffsets[i] + (int) (LEFT_UNITS[i] * unit) / 2 + 5;
            g2d.drawString(LEFT_LABELS[i], leftX - 12 - g2d.getFontMetrics().stringWidth(LEFT_LABELS[i]), textY);
        }
        for (int i = 0; i < RIGHT_LABELS.length; i++) {
            int textY = rightOffsets[i] + (int) (RIGHT_UNITS[i] * unit) / 2 + 5;
            g2d.drawString(RIGHT_LABELS[i], rightX + nodeWidth + 12, textY);
        }
        for (int i = 0; i < FLOWS.length; i++) {
            int source = FLOWS[i][0];
            int target = FLOWS[i][1];
            int value = FLOWS[i][2];
            double thickness = value * unit;
            double sourceY = leftOffsets[source] + consumed(FLOWS, i, 0) * unit;
            double targetY = rightOffsets[target] + consumed(FLOWS, i, 1) * unit;
            g2d.setPaint(FLOW_COLORS[i % FLOW_COLORS.length]);
            g2d.fill(flow(leftX + nodeWidth, sourceY, rightX, targetY, thickness));
        }
        g2d.setPaint(NODE_COLOR);
        for (int i = 0; i < LEFT_UNITS.length; i++) {
            g2d.fillRect(leftX, leftOffsets[i], nodeWidth, (int) Math.round(LEFT_UNITS[i] * unit));
        }
        for (int i = 0; i < RIGHT_UNITS.length; i++) {
            g2d.fillRect(rightX, rightOffsets[i], nodeWidth, (int) Math.round(RIGHT_UNITS[i] * unit));
        }
    }

    private Path2D flow(double sourceX, double sourceY, double targetX, double targetY, double thickness) {
        double middleX = (sourceX + targetX) / 2d;
        Path2D.Double path = new Path2D.Double();
        path.moveTo(sourceX, sourceY);
        path.curveTo(middleX, sourceY, middleX, targetY, targetX, targetY);
        path.lineTo(targetX, targetY + thickness);
        path.curveTo(middleX, targetY + thickness, middleX, sourceY + thickness, sourceX, sourceY + thickness);
        path.closePath();
        return path;
    }

    private int sum(int[] values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total == 0 ? 1 : total;
    }

    private int[] offsets(int[] units, double unit, int top) {
        int[] offsets = new int[units.length];
        int current = top;
        for (int i = 0; i < units.length; i++) {
            offsets[i] = current;
            current += (int) Math.round(units[i] * unit);
        }
        return offsets;
    }

    /**
     * Units already taken on the same node before the given flow index,
     * so bands stack instead of overlapping.
     */
    private int consumed(int[][] flows, int index, int side) {
        int total = 0;
        for (int i = 0; i < index; i++) {
            if (flows[i][side] == flows[index][side]) {
                total += flows[i][2];
            }
        }
        return total;
    }
}
