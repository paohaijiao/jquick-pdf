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
package com.github.paohaijiao.gant;

import java.awt.*;
import java.util.List;

public class JGanttOption {

    private Title title;

    private List<FlightData> flightData;

    private ChartStyle chartStyle;

    private TimeRange timeRange;

    public JGanttOption() {
    }

    public JGanttOption(Title title, List<FlightData> flightData, ChartStyle chartStyle, TimeRange timeRange) {
        this.title = title;
        this.flightData = flightData;
        this.chartStyle = chartStyle;
        this.timeRange = timeRange;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public List<FlightData> getFlightData() {
        return flightData;
    }

    public void setFlightData(List<FlightData> flightData) {
        this.flightData = flightData;
    }

    public ChartStyle getChartStyle() {
        return chartStyle;
    }

    public void setChartStyle(ChartStyle chartStyle) {
        this.chartStyle = chartStyle;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
    }

    public static class Title {
        private String text;
        private String subtext;

        public Title() {
        }

        public Title(String text, String subtext) {
            this.text = text;
            this.subtext = subtext;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getSubtext() {
            return subtext;
        }

        public void setSubtext(String subtext) {
            this.subtext = subtext;
        }
    }

    public static class FlightData {

        private String flightNo;

        private String gate;

        private String type;

        private int startHour;

        private int startMinute;

        private int duration;

        private int row;

        private double actualUsageRatio;

        public FlightData() {
        }

        public FlightData(String flightNo, String gate, String type, int startHour, int startMinute, int duration, int row, double actualUsageRatio) {
            this.flightNo = flightNo;
            this.gate = gate;
            this.type = type;
            this.startHour = startHour;
            this.startMinute = startMinute;
            this.duration = duration;
            this.row = row;
            this.actualUsageRatio = actualUsageRatio;
        }

        public String getFlightNo() {
            return flightNo;
        }

        public void setFlightNo(String flightNo) {
            this.flightNo = flightNo;
        }

        public String getGate() {
            return gate;
        }

        public void setGate(String gate) {
            this.gate = gate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getStartHour() {
            return startHour;
        }

        public void setStartHour(int startHour) {
            this.startHour = startHour;
        }

        public int getStartMinute() {
            return startMinute;
        }

        public void setStartMinute(int startMinute) {
            this.startMinute = startMinute;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public double getActualUsageRatio() {
            return actualUsageRatio;
        }

        public void setActualUsageRatio(double actualUsageRatio) {
            this.actualUsageRatio = actualUsageRatio;
        }
    }

    public static class ChartStyle {

        private Color backgroundColor;

        private Color axisColor;

        private Color gateLabelColor;

        private Color plannedTimeColor;

        private Color actualTimeColor;

        private Font titleFont;

        private Font labelFont;

        private int width;

        private int height;

        public ChartStyle() {
        }

        public ChartStyle(Color backgroundColor, Color axisColor, Color gateLabelColor, Color plannedTimeColor, Color actualTimeColor, Font titleFont, Font labelFont, int width, int height) {
            this.backgroundColor = backgroundColor;
            this.axisColor = axisColor;
            this.gateLabelColor = gateLabelColor;
            this.plannedTimeColor = plannedTimeColor;
            this.actualTimeColor = actualTimeColor;
            this.titleFont = titleFont;
            this.labelFont = labelFont;
            this.width = width;
            this.height = height;
        }

        public Color getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        public Color getAxisColor() {
            return axisColor;
        }

        public void setAxisColor(Color axisColor) {
            this.axisColor = axisColor;
        }

        public Color getGateLabelColor() {
            return gateLabelColor;
        }

        public void setGateLabelColor(Color gateLabelColor) {
            this.gateLabelColor = gateLabelColor;
        }

        public Color getPlannedTimeColor() {
            return plannedTimeColor;
        }

        public void setPlannedTimeColor(Color plannedTimeColor) {
            this.plannedTimeColor = plannedTimeColor;
        }

        public Color getActualTimeColor() {
            return actualTimeColor;
        }

        public void setActualTimeColor(Color actualTimeColor) {
            this.actualTimeColor = actualTimeColor;
        }

        public Font getTitleFont() {
            return titleFont;
        }

        public void setTitleFont(Font titleFont) {
            this.titleFont = titleFont;
        }

        public Font getLabelFont() {
            return labelFont;
        }

        public void setLabelFont(Font labelFont) {
            this.labelFont = labelFont;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class TimeRange {

        private int startHour;

        private int endHour;

        private String[] timeLabels;

        public TimeRange() {
        }

        public TimeRange(int startHour, int endHour, String[] timeLabels) {
            this.startHour = startHour;
            this.endHour = endHour;
            this.timeLabels = timeLabels;
        }

        // Getters and Setters
        public int getStartHour() {
            return startHour;
        }

        public void setStartHour(int startHour) {
            this.startHour = startHour;
        }

        public int getEndHour() {
            return endHour;
        }

        public void setEndHour(int endHour) {
            this.endHour = endHour;
        }

        public String[] getTimeLabels() {
            return timeLabels;
        }

        public void setTimeLabels(String[] timeLabels) {
            this.timeLabels = timeLabels;
        }
    }
}
