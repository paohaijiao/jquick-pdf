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
package com.github.paohaijiao.gantt;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.gant.JGanttOption;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;


public class JGanttChartRenderer extends JAbstractChartRenderer {

    private static final Color DEFAULT_BACKGROUND = new Color(255, 255, 255);

    private static final Color DEFAULT_AXIS = new Color(146, 154, 186);

    private static final Color DEFAULT_GATE = new Color(54, 140, 108);

    private static final Color DEFAULT_PLANNED = new Color(80, 112, 221);

    private static final Color DEFAULT_ACTUAL = new Color(221, 179, 11);

    private static final Color TITLE_COLOR = new Color(31, 41, 55);

    private static final Color SUBTITLE_COLOR = new Color(107, 114, 128);

    private static final Color TIME_TEXT_COLOR = new Color(96, 106, 145);

    private static final Color STRIPE_COLOR = new Color(248, 250, 254);

    private static final Color GRID_COLOR = new Color(232, 238, 249);

    private static final int START_X = 112;

    private static final int HEADER_HEIGHT = 62;

    private static final int LEGEND_SPACE = 28;

    private static final int TOTAL_MINUTES = 360;

    private static final int DEFAULT_START_HOUR = 21;

    private static final String[] DEFAULT_TIME_LABELS = {"21:00", "22:00", "23:00", "00:00", "01:00", "02:00", "03:00"};

    @Override
    protected int getDefaultWidth() {
        return 872;
    }

    @Override
    protected int getDefaultHeight() {
        return 282;
    }

    @Override
    protected void drawChart(SVGGraphics2D g2d, JOption option, int width, int height) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        JGanttOption ganttOption = option.getGanttOption();
        JGanttOption.ChartStyle cs = ganttOption != null ? ganttOption.getChartStyle() : null;

        Color backgroundColor = colorOr(cs != null ? cs.getBackgroundColor() : null, DEFAULT_BACKGROUND);
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, width, height);

        List<JGanttOption.FlightData> flights = ganttOption != null ? ganttOption.getFlightData() : null;

        int totalRows = computeTotalRows(flights);
        int startY = HEADER_HEIGHT;
        int chartWidth = width - START_X - 20;
        int available = height - HEADER_HEIGHT - LEGEND_SPACE;
        int rowHeight = Math.max(8, available / totalRows);
        int chartHeight = rowHeight * totalRows;

        drawHeader(g2d, width, cs, ganttOption);
        drawRowStripes(g2d, START_X, startY, chartWidth, chartHeight, rowHeight, totalRows, cs);
        drawTimeAxis(g2d, START_X, startY, chartWidth, chartHeight, cs, ganttOption);
        drawGateLabels(g2d, START_X, startY, rowHeight, totalRows, cs, flights);
        drawGanttBars(g2d, START_X, startY, chartWidth, rowHeight, cs, ganttOption, flights);
        drawLegend(g2d, START_X, startY + chartHeight + 5, cs, ganttOption);
    }

    private void drawHeader(SVGGraphics2D g2d, int width, JGanttOption.ChartStyle cs, JGanttOption ganttOption) {
        JGanttOption.Title title = ganttOption != null ? ganttOption.getTitle() : null;
        if (title == null) {
            return;
        }
        if (title.getText() != null) {
            g2d.setFont(titleFont(cs));
            g2d.setColor(TITLE_COLOR);
            String text = title.getText();
            g2d.drawString(text, (width - g2d.getFontMetrics().stringWidth(text)) / 2, 28);
        }
        if (title.getSubtext() != null) {
            g2d.setFont(labelFont(cs));
            g2d.setColor(SUBTITLE_COLOR);
            String subtext = title.getSubtext();
            g2d.drawString(subtext, (width - g2d.getFontMetrics().stringWidth(subtext)) / 2, 48);
        }
        Color planned = colorOr(cs != null ? cs.getPlannedTimeColor() : null, DEFAULT_PLANNED);
        g2d.setColor(planned);
        g2d.fillRoundRect((width - 40) / 2, 52, 40, 3, 2, 2);
    }

    private void drawRowStripes(SVGGraphics2D g2d, int startX, int startY, int chartWidth, int chartHeight, int rowHeight, int totalRows, JGanttOption.ChartStyle cs) {
        for (int i = 0; i < totalRows; i++) {
            int y = startY + i * rowHeight;
            if (i % 2 == 0) {
                g2d.setColor(STRIPE_COLOR);
                g2d.fillRect(startX, y, chartWidth, rowHeight);
            }
        }
        g2d.setColor(GRID_COLOR);
        for (int i = 1; i <= totalRows; i++) {
            int y = startY + i * rowHeight;
            g2d.drawLine(startX, y, startX + chartWidth, y);
        }
        Color axisColor = colorOr(cs != null ? cs.getAxisColor() : null, DEFAULT_AXIS);
        g2d.setColor(axisColor);
        g2d.drawLine(startX, startY, startX, startY + chartHeight);
        g2d.drawLine(startX + chartWidth, startY, startX + chartWidth, startY + chartHeight);
        g2d.drawLine(startX, startY + chartHeight, startX + chartWidth, startY + chartHeight);
    }

    private void drawTimeAxis(SVGGraphics2D g2d, int startX, int startY, int chartWidth, int chartHeight, JGanttOption.ChartStyle cs, JGanttOption ganttOption) {
        String[] timeLabels = DEFAULT_TIME_LABELS;
        if (ganttOption != null && ganttOption.getTimeRange() != null && ganttOption.getTimeRange().getTimeLabels() != null) {
            timeLabels = ganttOption.getTimeRange().getTimeLabels();
        }
        int hourWidth = chartWidth / (timeLabels.length - 1);
        g2d.setColor(GRID_COLOR);
        for (int i = 0; i < timeLabels.length; i++) {
            int x = startX + i * hourWidth;
            g2d.drawLine(x, startY + 1, x, startY + chartHeight - 1);
        }

        Color axisColor = colorOr(cs != null ? cs.getAxisColor() : null, DEFAULT_AXIS);
        g2d.setFont(labelFont(cs));

        g2d.setColor(axisColor);
        g2d.setStroke(new BasicStroke(1.2f));
        g2d.drawLine(startX, startY, startX + chartWidth, startY);
        g2d.setStroke(new BasicStroke(1f));

        for (int i = 0; i < timeLabels.length; i++) {
            int x = startX + i * hourWidth;
            g2d.setColor(axisColor);
            g2d.drawLine(x, startY, x, startY - 5);
            g2d.setColor(TIME_TEXT_COLOR);
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(timeLabels[i], x - fm.stringWidth(timeLabels[i]) / 2, startY - 8);
        }
    }

    private void drawGateLabels(SVGGraphics2D g2d, int startX, int startY, int rowHeight, int totalRows, JGanttOption.ChartStyle cs, List<JGanttOption.FlightData> flights) {
        String[] gates = new String[totalRows];
        String[] types = new String[totalRows];
        if (flights != null) {
            for (JGanttOption.FlightData f : flights) {
                if (f.getRow() >= 0 && f.getRow() < totalRows) {
                    gates[f.getRow()] = f.getGate();
                    types[f.getRow()] = f.getType();
                }
            }
        }

        Color gateColor = colorOr(cs != null ? cs.getGateLabelColor() : null, DEFAULT_GATE);
        int chipX = 10;
        int chipWidth = startX - chipX - 18;
        int badgeSize = 14;
        int badgeX = startX - badgeSize;
        for (int i = 0; i < totalRows; i++) {
            int y = startY + i * rowHeight + 2;
            int rectHeight = rowHeight - 4;
            g2d.setColor(gateColor);
            g2d.fillRoundRect(chipX, y, chipWidth, rectHeight, 8, 8);
            g2d.setColor(lighten(gateColor, 0.35f));
            g2d.drawRoundRect(chipX, y, chipWidth, rectHeight, 8, 8);
            if (gates[i] != null) {
                g2d.setFont(labelFont(cs).deriveFont(Font.BOLD));
                g2d.setColor(Color.WHITE);
                FontMetrics fm = g2d.getFontMetrics();
                String gate = gates[i];
                g2d.drawString(gate, chipX + (chipWidth - fm.stringWidth(gate)) / 2,
                        y + rectHeight / 2 + fm.getAscent() / 2 - 1);
            }
            if (types[i] != null && !types[i].isEmpty()) {
                boolean wide = "W".equalsIgnoreCase(types[i]);
                g2d.setColor(wide ? new Color(255, 244, 214) : new Color(226, 237, 255));
                g2d.fillRoundRect(badgeX, y + (rectHeight - badgeSize) / 2, badgeSize, badgeSize, 4, 4);
                g2d.setColor(wide ? new Color(196, 138, 20) : new Color(52, 94, 196));
                g2d.setFont(labelFont(cs).deriveFont(Font.BOLD, 10f));
                FontMetrics fm = g2d.getFontMetrics();
                String type = types[i];
                g2d.drawString(type, badgeX + (badgeSize - fm.stringWidth(type)) / 2, y + rectHeight / 2 + fm.getAscent() / 2 - 1);
            }
        }
    }

    private void drawGanttBars(SVGGraphics2D g2d, int startX, int startY, int chartWidth, int rowHeight, JGanttOption.ChartStyle cs, JGanttOption ganttOption, List<JGanttOption.FlightData> flights) {
        if (flights == null || flights.isEmpty()) {
            return;
        }
        double minuteWidth = (double) chartWidth / TOTAL_MINUTES;
        int startHour = ganttOption != null && ganttOption.getTimeRange() != null ? ganttOption.getTimeRange().getStartHour() : DEFAULT_START_HOUR;
        Color planned = colorOr(cs != null ? cs.getPlannedTimeColor() : null, DEFAULT_PLANNED);
        Color actual = colorOr(cs != null ? cs.getActualTimeColor() : null, DEFAULT_ACTUAL);

        for (JGanttOption.FlightData flight : flights) {
            int y = startY + flight.getRow() * rowHeight + 2;
            int barHeight = rowHeight - 4;
            int startMinute = (flight.getStartHour() - startHour) * 60 + flight.getStartMinute();
            double x = startX + startMinute * minuteWidth;
            double width = flight.getDuration() * minuteWidth;
            if (width <= 0) {
                continue;
            }
            int radius = Math.min(barHeight / 2, 7);
            g2d.setColor(withAlpha(planned, 45));
            g2d.fill(new RoundRectangle2D.Double(x, y, width, barHeight, radius, radius));
            g2d.setColor(withAlpha(planned, 170));
            g2d.draw(new RoundRectangle2D.Double(x, y, width, barHeight, radius, radius));
            double ratio = flight.getActualUsageRatio() > 0 ? flight.getActualUsageRatio() : 0.7;
            double actualWidth = width * ratio;
            if (actualWidth >= 3) {
                RoundRectangle2D actualShape = new RoundRectangle2D.Double(x, y, actualWidth, barHeight, radius, radius);
                g2d.setPaint(new GradientPaint((float) x, 0f, lighten(actual, 0.25f), (float) (x + actualWidth), 0f, actual));
                g2d.fill(actualShape);
                g2d.setColor(darken(actual, 0.12f));
                g2d.draw(actualShape);
                g2d.setColor(withAlpha(Color.WHITE, 200));
                g2d.drawLine((int) (x + actualWidth), y + 1, (int) (x + actualWidth), y + barHeight - 1);
            }
            g2d.setFont(labelFont(cs).deriveFont(Font.BOLD, 11f));
            String no = flight.getFlightNo();
            FontMetrics fm = g2d.getFontMetrics();
            int tw = fm.stringWidth(no);
            int textY = y + barHeight / 2 + fm.getAscent() / 2 - 1;
            if (actualWidth - 10 > tw) {
                g2d.setColor(Color.WHITE);
                g2d.drawString(no, (float) (x + (actualWidth - tw) / 2), textY);
            } else if (width - 10 > tw) {
                g2d.setColor(darken(planned, 0.15f));
                g2d.drawString(no, (float) (x + width + 6), textY);
            }
        }
    }

    private void drawLegend(SVGGraphics2D g2d, int x, int y, JGanttOption.ChartStyle cs, JGanttOption ganttOption) {
        int w = 330;
        int h = 22;
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(x, y, w, h, 6, 6);
        g2d.setColor(new Color(226, 232, 240));
        g2d.drawRoundRect(x, y, w, h, 6, 6);

        Color planned = colorOr(cs != null ? cs.getPlannedTimeColor() : null, DEFAULT_PLANNED);
        Color actual = colorOr(cs != null ? cs.getActualTimeColor() : null, DEFAULT_ACTUAL);
        g2d.setFont(labelFont(cs));
        FontMetrics fm = g2d.getFontMetrics();
        int textY = y + h / 2 + fm.getAscent() / 2 - 1;
        int swatchY = y + 6;

        g2d.setColor(withAlpha(planned, 60));
        g2d.fillRoundRect(x + 12, swatchY, 18, 10, 3, 3);
        g2d.setColor(planned);
        g2d.drawRoundRect(x + 12, swatchY, 18, 10, 3, 3);
        g2d.setColor(TIME_TEXT_COLOR);
        g2d.drawString("计划时间", x + 38, textY);

        g2d.setColor(actual);
        g2d.fillRoundRect(x + 130, swatchY, 18, 10, 3, 3);
        g2d.setColor(TIME_TEXT_COLOR);
        g2d.drawString("实际使用", x + 156, textY);
    }


    private static int computeTotalRows(List<JGanttOption.FlightData> flights) {
        if (flights == null || flights.isEmpty()) {
            return 10;
        }
        int max = 0;
        for (JGanttOption.FlightData f : flights) {
            if (f.getRow() > max) {
                max = f.getRow();
            }
        }
        return max + 1;
    }

    private static Font titleFont(JGanttOption.ChartStyle cs) {
        return cs != null && cs.getTitleFont() != null ? cs.getTitleFont() : new Font("Microsoft YaHei", Font.BOLD, 18);
    }

    private static Font labelFont(JGanttOption.ChartStyle cs) {
        return cs != null && cs.getLabelFont() != null ? cs.getLabelFont() : new Font("Microsoft YaHei", Font.PLAIN, 12);
    }

    private static Color colorOr(Color value, Color def) {
        return value != null ? value : def;
    }

    private static Color withAlpha(Color c, int alpha) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
    }

    private static Color lighten(Color c, float f) {
        return new Color(
                Math.min(255, (int) (c.getRed() + (255 - c.getRed()) * f)),
                Math.min(255, (int) (c.getGreen() + (255 - c.getGreen()) * f)),
                Math.min(255, (int) (c.getBlue() + (255 - c.getBlue()) * f)));
    }

    private static Color darken(Color c, float f) {
        return new Color(
                Math.max(0, (int) (c.getRed() * (1 - f))),
                Math.max(0, (int) (c.getGreen() * (1 - f))),
                Math.max(0, (int) (c.getBlue() * (1 - f))));
    }
}
