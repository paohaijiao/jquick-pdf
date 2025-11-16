package com.github.paohaijiao.lunar;/*
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
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * 农历日历渲染器
 */
public class JLunarCalendarRenderer extends JAbstractChartRenderer {

    @Override
    protected int getDefaultWidth() {
        return 900;
    }

    @Override
    protected int getDefaultHeight() {
        return 650;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        if (!(option instanceof LunarCalendarOption)) {
            throw new IllegalArgumentException("Option must be LunarCalendarOption");
        }
        LunarCalendarOption lunarOption = (LunarCalendarOption) option;
        LunarCalendarOption.ColorConfig colors = lunarOption.colorConfig();
        if (colors.isUseGradientBackground()) {
            GradientPaint gradient = new GradientPaint(0, 0, colors.getGradientStartColor(), width, height, colors.getGradientEndColor());
            svgGenerator.setPaint(gradient);
            svgGenerator.fillRect(0, 0, width, height);
        } else {
            svgGenerator.setColor(colors.getBackgroundColor());
            svgGenerator.fillRect(0, 0, width, height);
        }
        drawTitle(svgGenerator, option, width);
        drawCalendarContent(svgGenerator, lunarOption, width, height);
    }

    private void drawCalendarContent(SVGGraphics2D g2d, LunarCalendarOption option, int width, int height) {
        LunarCalendarOption.ColorConfig colors = option.colorConfig();
        LunarCalendarOption.CalendarDataConfig dataConfig = option.dataConfig();
        Font chineseFont = new Font("Microsoft YaHei", Font.PLAIN, 14);
        Font titleFont = new Font("Microsoft YaHei", Font.BOLD, 16);
        g2d.setFont(chineseFont);
        int startX = 50;
        int startY = 80;
        int cellSize = 110;
        int cellHeight = 90;
        int rows = dataConfig.getRows();
        int cols = dataConfig.getCols();
        g2d.setColor(colors.getGridColor());
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = startX + col * cellSize;
                int y = startY + row * cellHeight;
                g2d.drawRect(x, y, cellSize, cellHeight);
                LunarCalendarOption.ColorConfig colorConfig = option.getColorConfig();
                if (null == colorConfig.getBackgroundColor()) {
                    Color bgColor = getDayBackgroundColor(row, col, colors);
                    g2d.setColor(bgColor);
                } else {
                    g2d.setColor(colorConfig.getBackgroundColor());
                }

                g2d.fillRect(x + 1, y + 1, cellSize - 2, cellHeight - 2);
            }
        }
        String[] weekDays = dataConfig.getWeekDays();
        g2d.setColor(colors.getTextColor());
        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        for (int col = 0; col < cols; col++) {
            int x = startX + col * cellSize + cellSize / 2;
            int y = startY - 30;
            if (col >= 5 && col < weekDays.length) {
                g2d.setColor(colors.getWeekendColor());
            } else {
                g2d.setColor(colors.getTextColor());
            }
            drawCenteredString(g2d, weekDays[col], x, y);
        }
        List<LunarCalendarOption.DayData> dayDataList = dataConfig.getDayDataList();
        for (LunarCalendarOption.DayData dayData : dayDataList) {
            if (dayData.getRow() < rows && dayData.getCol() < cols) {
                int x = startX + dayData.getCol() * cellSize + cellSize / 2;
                int y = startY + dayData.getRow() * cellHeight;
                if (dayData.getCol() >= 5) {
                    g2d.setColor(colors.getWeekendColor());
                } else {
                    g2d.setColor(colors.getSolarDateColor());
                }
                g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
                drawCenteredString(g2d, String.valueOf(dayData.getSolarDay()), x, y + 25);
                g2d.setColor(colors.getLunarDateColor());
                g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
                drawCenteredString(g2d, dayData.getLunarDay(), x, y + 50);
            }
        }
        List<LunarCalendarOption.SpecialDay> specialDays = dataConfig.getSpecialDays();
        g2d.setColor(colors.getSpecialDayColor());
        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        for (LunarCalendarOption.SpecialDay specialDay : specialDays) {
            if (specialDay.getRow() < rows && specialDay.getCol() < cols) {
                int x = startX + specialDay.getCol() * cellSize + cellSize / 2;
                int y = startY + specialDay.getRow() * cellHeight + 75;
                drawCenteredString(g2d, specialDay.getName(), x, y);
            }
        }
        if (option.title() == null) {
            g2d.setColor(colors.getTitleColor());
            g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
            String monthTitle = option.year() + "年 " + option.month() + "月";
            drawCenteredString(g2d, monthTitle, width / 2, 40);
        }
    }

    private Color getDayBackgroundColor(int row, int col, LunarCalendarOption.ColorConfig colors) {
        if (colors.isUseCustomCellColors() && colors.getCustomCellColors() != null) {
            int colorIndex = col % colors.getCustomCellColors().length;
            return colors.getCustomCellColors()[colorIndex];
        } else if (colors.isUseGradientBackground()) {
            float ratio = (row * 7 + col) / 35.0f;
            return interpolateColor(colors.getGradientStartColor(), colors.getGradientEndColor(), ratio);
        } else {
            float hue = (row * 7 + col) * 0.02f;
            float saturation = 0.7f;
            float brightness = 0.95f;
            return Color.getHSBColor(hue, saturation, brightness);
        }
    }

    private Color interpolateColor(Color start, Color end, float ratio) {
        int r = (int) (start.getRed() + (end.getRed() - start.getRed()) * ratio);
        int g = (int) (start.getGreen() + (end.getGreen() - start.getGreen()) * ratio);
        int b = (int) (start.getBlue() + (end.getBlue() - start.getBlue()) * ratio);
        return new Color(r, g, b);
    }

    private void drawCenteredString(SVGGraphics2D g2d, String text, int x, int y) {
        if (text == null || text.isEmpty()) return;
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle2D bounds = fm.getStringBounds(text, g2d);
        int textX = x - (int) bounds.getWidth() / 2;
        int textY = y - (int) bounds.getHeight() / 2 + fm.getAscent();
        g2d.drawString(text, textX, textY);
    }
}
