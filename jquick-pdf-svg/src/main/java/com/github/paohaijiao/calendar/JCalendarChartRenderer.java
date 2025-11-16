package com.github.paohaijiao.calendar;/*
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
import java.awt.geom.AffineTransform;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

/**
 * 日历图表渲染器（类似GitHub贡献图）
 */
public class JCalendarChartRenderer extends JAbstractChartRenderer {

    private static final Color DEFAULT_GRID_COLOR = new Color(232, 235, 240);
    private static final Color DEFAULT_TEXT_COLOR = new Color(84, 85, 90);
    private static final Color DEFAULT_WEEKEND_COLOR = new Color(248, 249, 250);
    private static final Font MONTH_FONT = new Font("Microsoft YaHei", Font.PLAIN, 12);
    private static final Font WEEKDAY_FONT = new Font("Microsoft YaHei", Font.PLAIN, 12);
    private static final Font YEAR_FONT = new Font("sans-serif", Font.BOLD, 20);

    @Override
    protected int getDefaultWidth() {
        return 1200;
    }
    @Override
    protected int getDefaultHeight() {
        return 300;
    }
    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        JCalendarOption calendarOption = option.getJCalendarOption();
        drawTitle(svgGenerator, option, width);
        drawCalendarGrid(svgGenerator, calendarOption, width, height);
        drawDataCells(svgGenerator, calendarOption, width, height);
        drawMonthLabels(svgGenerator, calendarOption, width, height);
        drawWeekdayLabels(svgGenerator, calendarOption, width, height);
        drawYearLabel(svgGenerator, calendarOption, width, height);
    }

    private void drawCalendarGrid(SVGGraphics2D g2d, JCalendarOption option, int width, int height) {
        int cellSize = option.cellSize();
        int margin = option.margin();
        Color gridColor = option.gridColor();
        g2d.setColor(gridColor);
        g2d.setStroke(new BasicStroke(1));
        int cols = 53;
        int rows = 7;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = margin + col * cellSize;
                int y = margin + row * cellSize;
                if (row == 0 || row == 6) {
                    g2d.setColor(DEFAULT_WEEKEND_COLOR);
                    g2d.fillRect(x, y, cellSize, cellSize);
                }
                g2d.setColor(gridColor);
                g2d.drawRect(x, y, cellSize, cellSize);
            }
        }
    }

    private void drawDataCells(SVGGraphics2D g2d, JCalendarOption option, int width, int height) {
        int cellSize = option.cellSize();
        int margin = option.margin();
        Map<LocalDate, Integer> data = option.data();
        Color startColor = option.startColor();
        Color endColor = option.endColor();
        if (data == null || data.isEmpty()) {
            return;
        }
        LocalDate firstDay = LocalDate.of(option.year(), Month.JANUARY, 1);
        DayOfWeek firstDayOfWeek = firstDay.getDayOfWeek();
        int daysToSubtract = firstDayOfWeek.getValue() % 7;
        LocalDate startDate = firstDay.minusDays(daysToSubtract);
        for (int week = 0; week < 53; week++) {
            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
                LocalDate currentDate = startDate.plusDays(week * 7 + dayOfWeek);
                if (currentDate.getYear() != option.year()) {
                    continue;
                }
                if (data.containsKey(currentDate)) {
                    int value = data.get(currentDate);
                    Color cellColor = calculateColor(value, startColor, endColor);
                    int x = margin + week * cellSize + 1;
                    int y = margin + dayOfWeek * cellSize + 1;
                    g2d.setColor(cellColor);
                    g2d.fillRect(x, y, cellSize - 2, cellSize - 2);
                }
            }
        }
    }

    private Color calculateColor(int value, Color startColor, Color endColor) {
        if (value <= 0) {
            return new Color(235, 237, 240);
        }
        float ratio = Math.min(value / 10.0f, 1.0f);
        int red = (int) (startColor.getRed() + (endColor.getRed() - startColor.getRed()) * ratio);
        int green = (int) (startColor.getGreen() + (endColor.getGreen() - startColor.getGreen()) * ratio);
        int blue = (int) (startColor.getBlue() + (endColor.getBlue() - startColor.getBlue()) * ratio);
        return new Color(Math.max(0, Math.min(255, red)), Math.max(0, Math.min(255, green)), Math.max(0, Math.min(255, blue)));
    }

    private void drawMonthLabels(SVGGraphics2D g2d, JCalendarOption option, int width, int height) {
        int cellSize = option.cellSize();
        int margin = option.margin();
        Color textColor = option.textColor();
        g2d.setColor(textColor);
        g2d.setFont(MONTH_FONT);
        String[] months = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        int[] monthPositions = {4, 8, 13, 17, 22, 26, 30, 35, 39, 44, 48, 52};
        for (int i = 0; i < months.length; i++) {
            int x = margin + monthPositions[i] * cellSize;
            drawCenteredText(g2d, months[i], x, margin - 20);
        }
    }

    private void drawWeekdayLabels(SVGGraphics2D g2d, JCalendarOption option, int width, int height) {
        int cellSize = option.cellSize();
        int margin = option.margin();
        Color textColor = option.textColor();
        g2d.setColor(textColor);
        g2d.setFont(WEEKDAY_FONT);
        String[] weekdays = {"日", "一", "二", "三", "四", "五", "六"};
        for (int i = 0; i < weekdays.length; i++) {
            int y = margin + i * cellSize + cellSize / 2;
            drawRightAlignedText(g2d, weekdays[i], margin - 10, y);
        }
    }

    private void drawYearLabel(SVGGraphics2D g2d, JCalendarOption option, int width, int height) {
        int margin = option.margin();
        g2d.setColor(new Color(134, 135, 140));
        g2d.setFont(YEAR_FONT);
        AffineTransform originalTransform = g2d.getTransform();
        g2d.rotate(-Math.PI / 2, margin - 40, margin + 70);
        drawCenteredText(g2d, String.valueOf(option.year()), margin - 40, margin + 70);
        g2d.setTransform(originalTransform);
    }

    private void drawCenteredText(SVGGraphics2D g2d, String text, int x, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g2d.drawString(text, x - textWidth / 2, y + textHeight / 2);
    }

    private void drawRightAlignedText(SVGGraphics2D g2d, String text, int x, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g2d.drawString(text, x - textWidth, y + textHeight / 2);
    }
}