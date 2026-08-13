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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

/**
 * 分数仪表盘渲染器。
 * <p>
 * 现代卡片式风格：浅色画布 + 白色圆角卡片 + 渐变数值弧 + 圆角指针 + 状态徽章。
 * 只负责美化绘制，不修改任何数据模型（GuageConfig / JGuageOption）。
 */
public class JGuageRenderer extends JAbstractChartRenderer {

    private static final int DEFAULT_WIDTH = 872;

    private static final int DEFAULT_HEIGHT = 282;

    private static final Color CANVAS_BG = new Color(243, 246, 252);

    private static final Color CARD_FILL = new Color(255, 255, 255);

    private static final Color CARD_BORDER = new Color(225, 233, 244);

    private static final Color CARD_SHADOW = new Color(31, 41, 55, 18);

    private static final Color TITLE_COLOR = new Color(30, 41, 59);

    private static final Color SUBTITLE_COLOR = new Color(148, 163, 184);

    private static final Color SCORE_COLOR = new Color(30, 41, 59);

    private static final Color TRACK_COLOR = new Color(232, 238, 248);

    private static final Color TRACK_EDGE = new Color(217, 226, 240);

    private static final Color TICK_MAJOR = new Color(138, 147, 166);

    private static final Color TICK_MID = new Color(163, 172, 192);

    private static final Color TICK_MINOR = new Color(204, 212, 228);

    private static final Color TICK_LABEL = new Color(91, 100, 120);

    private static final Color HUB_FILL = Color.WHITE;

    private static final Color STATUS_GOOD = new Color(34, 197, 94);

    private static final Color STATUS_GREAT = new Color(59, 130, 246);

    private static final Color STATUS_FAIR = new Color(245, 158, 11);

    private static final Color STATUS_LOW = new Color(239, 68, 68);

    private static final int CARD_X = 256;

    private static final int CARD_Y = 50;

    private static final int CARD_W = 360;

    private static final int CARD_H = 205;

    private static final int CENTER_X = CARD_X + CARD_W / 2;

    private static final int CENTER_Y = 225;

    private static final int RADIUS = 138;

    private static final float TRACK_WIDTH = 15f;

    @Override
    protected int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    protected int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    @Override
    protected void drawChart(SVGGraphics2D g2d, JOption option, int width, int height) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        JGuageOption guageOption = option.getGuageOption();
        GuageConfig config = guageOption != null ? guageOption.scoreMeter() : null;
        if (config == null) {
            config = GuageConfig.builder().build();
        }
        int score = Math.max(0, Math.min(100, config.score()));

        g2d.setColor(CANVAS_BG);
        g2d.fillRect(0, 0, width, height);

        drawHeader(g2d, config, width);
        drawCard(g2d);
        drawTrack(g2d);
        drawValueArc(g2d, config, score);
        drawTicks(g2d);
        drawNeedle(g2d, config, score);
        drawCenterText(g2d, config, score);
        drawStatusBadge(g2d, score);
    }

    private void drawHeader(SVGGraphics2D g2d, GuageConfig config, int width) {
        Font base = config.titleFont() != null ? config.titleFont() : new Font("Microsoft YaHei", Font.PLAIN, 16);
        g2d.setFont(base.deriveFont(Font.BOLD, Math.max(18, base.getSize())));
        g2d.setColor(TITLE_COLOR);
        String title = config.title() != null ? config.title() : "SCORE";
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(title, (width - fm.stringWidth(title)) / 2, 30);
        g2d.setColor(withAlpha(TICK_MAJOR, 160));
        g2d.fillRoundRect((width - 36) / 2, 40, 36, 3, 2, 2);
    }

    private void drawCard(SVGGraphics2D g2d) {
        g2d.setColor(CARD_SHADOW);
        g2d.fill(new RoundRectangle2D.Double(CARD_X + 3, CARD_Y + 4, CARD_W, CARD_H, 16, 16));
        RoundRectangle2D card = new RoundRectangle2D.Double(CARD_X, CARD_Y, CARD_W, CARD_H, 16, 16);
        g2d.setColor(CARD_FILL);
        g2d.fill(card);
        g2d.setColor(CARD_BORDER);
        g2d.draw(card);
    }

    private void drawTrack(SVGGraphics2D g2d) {
        g2d.setColor(TRACK_EDGE);
        g2d.setStroke(new BasicStroke(1.2f));
        g2d.draw(new Arc2D.Double(CENTER_X - RADIUS - 12, CENTER_Y - RADIUS - 12, (RADIUS + 12) * 2, (RADIUS + 12) * 2, 0, 180, Arc2D.OPEN));
        g2d.setColor(TRACK_COLOR);
        g2d.setStroke(new BasicStroke(TRACK_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(new Arc2D.Double(CENTER_X - RADIUS, CENTER_Y - RADIUS, RADIUS * 2, RADIUS * 2, 0, 180, Arc2D.OPEN));
    }

    private void drawValueArc(SVGGraphics2D g2d, GuageConfig config, int score) {
        if (score <= 0) {
            return;
        }
        Color pointerColor = config.pointerColor() != null ? config.pointerColor() : new Color(80, 112, 221);
        Arc2D valueArc = new Arc2D.Double(CENTER_X - RADIUS, CENTER_Y - RADIUS, RADIUS * 2, RADIUS * 2, 180, -score * 1.8, Arc2D.OPEN);
        g2d.setPaint(new GradientPaint(CENTER_X - RADIUS, 0, lighten(pointerColor, 0.45f), CENTER_X + RADIUS, 0, pointerColor));
        g2d.setStroke(new BasicStroke(TRACK_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(valueArc);
    }

    private void drawTicks(SVGGraphics2D g2d) {
        g2d.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        Font labelFont = new Font("Microsoft YaHei", Font.PLAIN, 12);
        g2d.setFont(labelFont);
        for (int v = 0; v <= 100; v += 10) {
            double angle = Math.toRadians(180 - v * 1.8);
            int x1 = CENTER_X + (int) ((RADIUS - 4) * Math.cos(angle));
            int y1 = CENTER_Y - (int) ((RADIUS - 4) * Math.sin(angle));
            int x2 = CENTER_X + (int) ((RADIUS + 8) * Math.cos(angle));
            int y2 = CENTER_Y - (int) ((RADIUS + 8) * Math.sin(angle));
            g2d.setColor(v % 20 == 0 ? TICK_MAJOR : TICK_MID);
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
            if (v % 20 == 0) {
                g2d.setColor(TICK_LABEL);
                FontMetrics fm = g2d.getFontMetrics();
                String label = String.valueOf(v);
                int labelR = RADIUS + 26;
                int lx = CENTER_X + (int) (labelR * Math.cos(angle));
                int ly = CENTER_Y - (int) (labelR * Math.sin(angle));
                int tw = fm.stringWidth(label);
                int tx = lx - tw / 2;
                int ty = ly + fm.getAscent() / 2 - 2;
                if (v == 0) {
                    tx = lx - tw / 2 + 4;
                    ty = ly + fm.getAscent() / 2 + 2;
                } else if (v == 100) {
                    tx = lx - tw / 2 - 4;
                    ty = ly + fm.getAscent() / 2 + 2;
                }
                g2d.drawString(label, tx, ty);
            }
        }
        g2d.setColor(TICK_MINOR);
        g2d.setStroke(new BasicStroke(1f));
        for (int v = 0; v <= 100; v += 2) {
            if (v % 10 == 0) {
                continue;
            }
            double angle = Math.toRadians(180 - v * 1.8);
            int x1 = CENTER_X + (int) ((RADIUS + 1) * Math.cos(angle));
            int y1 = CENTER_Y - (int) ((RADIUS + 1) * Math.sin(angle));
            int x2 = CENTER_X + (int) ((RADIUS + 5) * Math.cos(angle));
            int y2 = CENTER_Y - (int) ((RADIUS + 5) * Math.sin(angle));
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }

    private void drawNeedle(SVGGraphics2D g2d, GuageConfig config, int score) {
        Color needleColor = config.pointerColor() != null ? config.pointerColor() : new Color(80, 112, 221);
        double angle = Math.toRadians(180 - score * 1.8);
        int length = RADIUS - 30;
        int tipX = CENTER_X + (int) (length * Math.cos(angle));
        int tipY = CENTER_Y - (int) (length * Math.sin(angle));
        int tailLen = 18;
        int tailX = CENTER_X - (int) (tailLen * Math.cos(angle));
        int tailY = CENTER_Y + (int) (tailLen * Math.sin(angle));
        g2d.setColor(needleColor);
        g2d.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(new Line2D.Double(CENTER_X, CENTER_Y, tipX, tipY));
        g2d.draw(new Line2D.Double(CENTER_X, CENTER_Y, tailX, tailY));
        g2d.setColor(HUB_FILL);
        g2d.fill(new Ellipse2D.Double(CENTER_X - 8, CENTER_Y - 8, 16, 16));
        g2d.setColor(needleColor);
        g2d.setStroke(new BasicStroke(2.5f));
        g2d.draw(new Ellipse2D.Double(CENTER_X - 8, CENTER_Y - 8, 16, 16));
        g2d.fill(new Ellipse2D.Double(CENTER_X - 3, CENTER_Y - 3, 6, 6));
    }

    private void drawCenterText(SVGGraphics2D g2d, GuageConfig config, int score) {
        Font scoreFont = config.scoreFont() != null ? config.scoreFont() : new Font("Microsoft YaHei", Font.BOLD, 30);
        g2d.setFont(scoreFont);
        g2d.setColor(SCORE_COLOR);
        String scoreText = String.valueOf(score);
        FontMetrics fm = g2d.getFontMetrics();
        int baselineY = CENTER_Y - 18;
        int x = CENTER_X - fm.stringWidth(scoreText) / 2;
        g2d.drawString(scoreText, x, baselineY);
        g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        g2d.setColor(SUBTITLE_COLOR);
        String unit = "/ 100";
        g2d.drawString(unit, CENTER_X + fm.stringWidth(scoreText) / 2 + 4, baselineY - 6);
    }

    private void drawStatusBadge(SVGGraphics2D g2d, int score) {
        Color badgeColor;
        String label;
        if (score >= 80) {
            badgeColor = STATUS_GOOD;
            label = "EXCELLENT";
        } else if (score >= 60) {
            badgeColor = STATUS_GREAT;
            label = "GOOD";
        } else if (score >= 40) {
            badgeColor = STATUS_FAIR;
            label = "FAIR";
        } else {
            badgeColor = STATUS_LOW;
            label = "LOW";
        }
        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 11));
        FontMetrics fm = g2d.getFontMetrics();
        int tw = fm.stringWidth(label);
        int w = tw + 22;
        int h = 20;
        int x = CENTER_X - w / 2;
        int y = CENTER_Y - 66;
        g2d.setColor(withAlpha(badgeColor, 32));
        g2d.fill(new RoundRectangle2D.Double(x, y, w, h, h, h));
        g2d.setColor(badgeColor);
        g2d.draw(new RoundRectangle2D.Double(x, y, w, h, h, h));
        g2d.drawString(label, CENTER_X - tw / 2, y + h / 2 + fm.getAscent() / 2 - 1);
    }


    private static Color lighten(Color c, float f) {
        return new Color(
                Math.min(255, (int) (c.getRed() + (255 - c.getRed()) * f)),
                Math.min(255, (int) (c.getGreen() + (255 - c.getGreen()) * f)),
                Math.min(255, (int) (c.getBlue() + (255 - c.getBlue()) * f)));
    }

    private static Color withAlpha(Color c, int alpha) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
    }
}
