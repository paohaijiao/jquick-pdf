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
import java.util.List;

public class JGanttChartRenderer extends JAbstractChartRenderer {

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
        JGanttOption ganttOption = option.getGanttOption();
        Color backgroundColor = ganttOption.getChartStyle() != null && ganttOption.getChartStyle().getBackgroundColor() != null ? ganttOption.getChartStyle().getBackgroundColor() : Color.WHITE;
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, width, height);
        int startX = 100;
        int startY = 70;
        int chartWidth = 752;
        int chartHeight = 192;
        int rowHeight = 12;
        int totalRows = 16;
        drawTitle(g2d, option, width);
        drawTimeAxis(g2d, startX, startY, chartWidth, ganttOption);
        drawGateLabels(g2d, startX, startY, rowHeight, totalRows, ganttOption);
        drawGanttBars(g2d, startX, startY, chartWidth, rowHeight, ganttOption);
        drawFlightLabels(g2d, startX, startY, chartWidth, rowHeight, ganttOption);
        drawLegend(g2d, startX, startY + chartHeight + 10, ganttOption);
    }

    private void drawTimeAxis(SVGGraphics2D g2d, int startX, int startY, int chartWidth, JGanttOption option) {
        Color axisColor = option.getChartStyle() != null && option.getChartStyle().getAxisColor() != null ? option.getChartStyle().getAxisColor() : new Color(146, 154, 186);
        g2d.setColor(axisColor);
        Font labelFont = option.getChartStyle() != null && option.getChartStyle().getLabelFont() != null ? option.getChartStyle().getLabelFont() : new Font("微软雅黑", Font.PLAIN, 12);
        g2d.setFont(labelFont);
        String[] timeLabels;
        if (option.getTimeRange() != null && option.getTimeRange().getTimeLabels() != null) {
            timeLabels = option.getTimeRange().getTimeLabels();
        } else {
            timeLabels = new String[]{"21:00", "22:00", "23:00", "00:00", "01:00", "02:00", "03:00"};
        }
        int hourWidth = chartWidth / (timeLabels.length - 1);
        for (int i = 0; i < timeLabels.length; i++) {
            int x = startX + i * hourWidth;
            g2d.drawLine(x, startY, x, startY - 5);
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(timeLabels[i]);
            g2d.drawString(timeLabels[i], x - textWidth / 2, startY - 8);
        }
        g2d.drawLine(startX, startY, startX + chartWidth, startY);
    }

    private void drawGateLabels(SVGGraphics2D g2d, int startX, int startY, int rowHeight, int totalRows, JGanttOption option) {
        Color gateLabelColor = option.getChartStyle() != null && option.getChartStyle().getGateLabelColor() != null ? option.getChartStyle().getGateLabelColor() : new Color(54, 140, 108);
        Font labelFont = option.getChartStyle() != null && option.getChartStyle().getLabelFont() != null ? option.getChartStyle().getLabelFont() : new Font("微软雅黑", Font.PLAIN, 12);
        g2d.setFont(labelFont);
        String[] gates = {"681", "682I", "682O", "682", "683", "684", "685", "690", "691", "692", "693", "694", "695", "696", "697", "698"};
        String[] types = {"X", "W", "W", "X", "X", "W", "X", "X", "X", "X", "W", "W", "X", "X", "X", "W"};
        int labelWidth = 90;
        int cornerRadius = 10;
        for (int i = 0; i < totalRows; i++) {
            int y = startY + i * rowHeight + 1;
            int rectHeight = rowHeight - 2;
            g2d.setColor(gateLabelColor);
            g2d.fillRoundRect(startX - labelWidth, y, labelWidth - 5, rectHeight, cornerRadius, cornerRadius);
            g2d.setColor(Color.WHITE);
            FontMetrics fm = g2d.getFontMetrics();
            int gateWidth = fm.stringWidth(gates[i]);
            int gateX = startX - labelWidth + (labelWidth - 15 - gateWidth) / 2;
            g2d.drawString(gates[i], gateX, y + rectHeight / 2 + fm.getAscent() / 2 - 2);
            g2d.setColor(Color.BLACK);
            int typeX = startX - 20;
            g2d.drawString(types[i], typeX, y + rectHeight / 2 + fm.getAscent() / 2 - 2);
        }
    }

    private void drawGanttBars(SVGGraphics2D g2d, int startX, int startY, int chartWidth, int rowHeight, JGanttOption option) {
        List<JGanttOption.FlightData> flights = option.getFlightData();
        if (flights == null || flights.isEmpty()) {
            return;
        }
        int totalMinutes = 360;
        double minuteWidth = (double) chartWidth / totalMinutes;
        Color plannedTimeColor = option.getChartStyle() != null && option.getChartStyle().getPlannedTimeColor() != null ? option.getChartStyle().getPlannedTimeColor() : new Color(80, 112, 221);
        Color actualTimeColor = option.getChartStyle() != null && option.getChartStyle().getActualTimeColor() != null ? option.getChartStyle().getActualTimeColor() : new Color(221, 179, 11);
        for (JGanttOption.FlightData flight : flights) {
            int y = startY + flight.getRow() * rowHeight + 1;
            int barHeight = rowHeight - 2;
            int startMinute = (flight.getStartHour() - 21) * 60 + flight.getStartMinute();
            int barX = startX + (int) (startMinute * minuteWidth);
            int barWidth = (int) (flight.getDuration() * minuteWidth);
            g2d.setColor(plannedTimeColor);
            g2d.fillRect(barX, y, barWidth, barHeight);
            double actualRatio = flight.getActualUsageRatio() > 0 ? flight.getActualUsageRatio() : 0.7;
            int actualWidth = Math.min(barWidth, (int) (flight.getDuration() * minuteWidth * actualRatio));
            g2d.setColor(actualTimeColor);
            g2d.fillRect(barX, y, actualWidth, barHeight);
            g2d.setColor(new Color(60, 60, 65));
            g2d.drawRect(barX, y, barWidth, barHeight);
        }
    }

    private void drawFlightLabels(SVGGraphics2D g2d, int startX, int startY, int chartWidth, int rowHeight, JGanttOption option) {
        List<JGanttOption.FlightData> flights = option.getFlightData();
        if (flights == null || flights.isEmpty()) {
            return;
        }
        g2d.setColor(Color.WHITE);
        Font labelFont = option.getChartStyle() != null && option.getChartStyle().getLabelFont() != null ? option.getChartStyle().getLabelFont() : new Font("微软雅黑", Font.PLAIN, 12);
        g2d.setFont(labelFont);
        int totalMinutes = 360;
        double minuteWidth = (double) chartWidth / totalMinutes;
        for (JGanttOption.FlightData flight : flights) {
            int y = startY + flight.getRow() * rowHeight + 1;
            int barHeight = rowHeight - 2;
            int startMinute = (flight.getStartHour() - 21) * 60 + flight.getStartMinute();
            int barX = startX + (int) (startMinute * minuteWidth);
            int barWidth = (int) (flight.getDuration() * minuteWidth);
            if (barWidth > 50) {
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(flight.getFlightNo());
                int textX = barX + (barWidth - textWidth) / 2;
                int textY = y + barHeight / 2 + fm.getAscent() / 2 - 2;
                g2d.drawString(flight.getFlightNo(), textX, textY);
            }
        }
    }

    private void drawLegend(SVGGraphics2D g2d, int x, int y, JGanttOption option) {
        g2d.setColor(new Color(224, 228, 242));
        g2d.fillRect(x, y, 195, 20);
        g2d.setColor(new Color(130, 146, 204));
        g2d.drawRect(x, y, 195, 20);
        g2d.setColor(new Color(60, 60, 65));
        Font labelFont = option.getChartStyle() != null && option.getChartStyle().getLabelFont() != null ? option.getChartStyle().getLabelFont() : new Font("微软雅黑", Font.PLAIN, 12);
        g2d.setFont(labelFont);
        g2d.drawString("计划时间 vs 实际使用时间", x + 10, y + 14);
        Color plannedTimeColor = option.getChartStyle() != null && option.getChartStyle().getPlannedTimeColor() != null ? option.getChartStyle().getPlannedTimeColor() : new Color(80, 112, 221);
        Color actualTimeColor = option.getChartStyle() != null && option.getChartStyle().getActualTimeColor() != null ? option.getChartStyle().getActualTimeColor() : new Color(221, 179, 11);
        g2d.setColor(plannedTimeColor);
        g2d.fillRect(x + 160, y + 5, 15, 10);
        g2d.setColor(actualTimeColor);
        g2d.fillRect(x + 160, y + 5, 8, 10);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + 160, y + 5, 15, 10);
    }
}
