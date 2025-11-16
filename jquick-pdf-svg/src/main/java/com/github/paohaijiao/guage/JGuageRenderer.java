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
package com.github.paohaijiao.guage;
import com.github.paohaijiao.JOption;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

/**
 * 分数仪表盘渲染器
 */
public class JGuageRenderer extends JAbstractChartRenderer {

    private static final int DEFAULT_WIDTH = 872;

    private static final int DEFAULT_HEIGHT = 282;

    @Override
    protected int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    protected int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        GuageConfig config = option.getGuageOption().scoreMeter();
        if (config == null) {
            throw new IllegalArgumentException("ScoreMeterConfig is required");
        }
        svgGenerator.setColor(Color.WHITE);
        svgGenerator.fillRect(0, 0, width, height);
        drawMainArc(svgGenerator, config);
        drawScaleMarks(svgGenerator);
        drawPointer(svgGenerator, config);
        drawText(svgGenerator, config);
    }

    private void drawMainArc(SVGGraphics2D g2d, GuageConfig config) {
        Arc2D outerArc = new Arc2D.Double(255.4735, 110.0265, 361.053, 211.5, 0, 180, Arc2D.OPEN);
        g2d.setColor(config.backgroundColor());
        g2d.fill(outerArc);
        Arc2D innerArc = new Arc2D.Double(270.5445, 120.0715, 330.911, 191.41, 0, 180, Arc2D.OPEN);
        g2d.setColor(Color.WHITE);
        g2d.fill(innerArc);
    }

    private void drawScaleMarks(SVGGraphics2D g2d) {
        Color thinLineColor = new Color(109, 110, 115);
        Color thickLineColor = new Color(84, 85, 90);
        Font font = new Font("Microsoft YaHei", Font.PLAIN, 12);
        g2d.setFont(font);
        int centerX = 436;
        int centerY = 215;
        int outerRadius = 105;
        int innerRadius = 95;
        int labelRadius = 120;
        for (int i = 0; i <= 10; i++) {
            int value = i * 10;
            double angle = 180 - (value * 1.8);
            double radian = Math.toRadians(angle);
            int x1 = centerX + (int) (innerRadius * Math.cos(radian));
            int y1 = centerY - (int) (innerRadius * Math.sin(radian));
            int x2 = centerX + (int) (outerRadius * Math.cos(radian));
            int y2 = centerY - (int) (outerRadius * Math.sin(radian));
            g2d.setColor(thickLineColor);
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
            int labelX = centerX + (int) (labelRadius * Math.cos(radian));
            int labelY = centerY - (int) (labelRadius * Math.sin(radian));
            String label = String.valueOf(value);
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(label);
            g2d.setColor(thickLineColor);
            if (value == 0) {
                g2d.drawString(label, labelX, labelY - 5);
            } else if (value == 50) {
                g2d.drawString(label, labelX - textWidth/2, labelY + 15);
            } else if (value == 100) {
                g2d.drawString(label, labelX - textWidth, labelY - 5);
            } else if (value < 50) {
                g2d.drawString(label, labelX, labelY);
            } else {
                g2d.drawString(label, labelX - textWidth, labelY);
            }
        }
        g2d.setColor(thinLineColor);
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i <= 100; i++) {
            if (i % 10 == 0) continue;
            double angle = 180 - (i * 1.8);
            double radian = Math.toRadians(angle);
            int minorInnerRadius = innerRadius + 5;
            int minorOuterRadius = outerRadius - 5;
            int x1 = centerX + (int) (minorInnerRadius * Math.cos(radian));
            int y1 = centerY - (int) (minorInnerRadius * Math.sin(radian));
            int x2 = centerX + (int) (minorOuterRadius * Math.cos(radian));
            int y2 = centerY - (int) (minorOuterRadius * Math.sin(radian));
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }

    private void drawPointer(SVGGraphics2D g2d, GuageConfig config) {
        int centerX = 436;
        int centerY = 215;
        double angle = 180 - (config.score() * 1.8);
        double radian = Math.toRadians(angle);
        int pointerLength = 80;
        double tipX = centerX + pointerLength * Math.cos(radian);
        double tipY = centerY - pointerLength * Math.sin(radian);
        g2d.setColor(config.pointerColor());
        g2d.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(new Line2D.Double(centerX, centerY, tipX, tipY));
    }

    private void drawText(SVGGraphics2D g2d, GuageConfig config) {
        g2d.setColor(config.textColor());
        g2d.setFont(config.titleFont());
        String title = config.title();
        FontMetrics titleFm = g2d.getFontMetrics();
        int titleWidth = titleFm.stringWidth(title);
        g2d.drawString(title, 436 - titleWidth/2, 175);
        g2d.setFont(config.scoreFont());
        String score = String.valueOf(config.score());
        FontMetrics scoreFm = g2d.getFontMetrics();
        int scoreWidth = scoreFm.stringWidth(score);
        g2d.drawString(score, 436 - scoreWidth/2, 205);
    }
}
