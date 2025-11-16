package com.github.paohaijiao.example;

import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class AirportGanttChart {

    // 定义航班数据类
    static class Flight {
        String flightNo;
        String gate;
        String type; // "X" or "W"
        int startMinute; // 从21:00开始的分钟数
        int duration; // 持续时间（分钟）
        int row; // 行位置

        public Flight(String flightNo, String gate, String type, int startHour, int startMinute, int duration, int row) {
            this.flightNo = flightNo;
            this.gate = gate;
            this.type = type;
            this.startMinute = (startHour - 21) * 60 + startMinute;
            this.duration = duration;
            this.row = row;
        }
    }

    public static void main(String[] args) {
        try {
            // 创建DOM文档
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            // 创建SVGGraphics2D
            SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

            // 设置SVG尺寸
            int width = 872;
            int height = 282;

            // 绘制甘特图
            drawGanttChart(svgGenerator, width, height);

            // 保存SVG文件
            try (Writer out = new FileWriter("d://test//airport_gantt_fixed.svg")) {
                svgGenerator.stream(out, true);
            }

            System.out.println("修复后的SVG文件已生成: airport_gantt_fixed.svg");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void drawGanttChart(SVGGraphics2D g2d, int width, int height) {
        // 设置背景
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 基本参数
        int startX = 100;
        int startY = 70;
        int chartWidth = 752;
        int chartHeight = 192;
        int rowHeight = 12;
        int totalRows = 16;

        // 创建航班数据
        List<Flight> flights = createFlightData();

        // 绘制标题
        drawTitle(g2d, width);

        // 绘制时间轴
        drawTimeAxis(g2d, startX, startY, chartWidth);

        // 绘制左侧的登机口标签
        drawGateLabels(g2d, startX, startY, rowHeight, totalRows);

        // 绘制甘特图条
        drawGanttBars(g2d, startX, startY, chartWidth, rowHeight, flights);

        // 绘制航班号标签
        drawFlightLabels(g2d, startX, startY, chartWidth, rowHeight, flights);

        // 绘制图例
        drawLegend(g2d, startX, startY + chartHeight + 10);
    }

    private static List<Flight> createFlightData() {
        List<Flight> flights = new ArrayList<>();

        // 添加航班数据 - 根据原始SVG中的数据
        flights.add(new Flight("Y3683", "681", "X", 21, 0, 360, 0));  // 21:00 - 03:00
        flights.add(new Flight("EKXAD", "682I", "W", 21, 0, 360, 1)); // 21:00 - 03:00
        flights.add(new Flight("Y4682", "682O", "W", 21, 0, 360, 2)); // 21:00 - 03:00
        flights.add(new Flight("Y4393", "682", "X", 21, 0, 360, 3));  // 21:00 - 03:00
        flights.add(new Flight("Y2238", "683", "X", 21, 0, 360, 4));  // 21:00 - 03:00
        flights.add(new Flight("Y8192", "684", "W", 21, 0, 240, 5));  // 21:00 - 01:00
        flights.add(new Flight("Y3887", "685", "X", 21, 0, 360, 6));  // 21:00 - 03:00
        flights.add(new Flight("Y3086", "690", "X", 21, 0, 360, 7));  // 21:00 - 03:00
        flights.add(new Flight("Y7421", "691", "X", 21, 0, 120, 8));  // 21:00 - 23:00
        flights.add(new Flight("Y4619", "692", "X", 21, 0, 300, 9));  // 21:00 - 02:00

        return flights;
    }

    private static void drawTitle(SVGGraphics2D g2d, int width) {
        g2d.setColor(new Color(60, 60, 65));
        g2d.setFont(new Font("微软雅黑", Font.BOLD, 18));
        String title = "Gantt of Airport Flight";
        FontMetrics fm = g2d.getFontMetrics();
        int titleWidth = fm.stringWidth(title);
        int titleX = (width - titleWidth) / 2;
        g2d.drawString(title, titleX, 30);
    }

    private static void drawTimeAxis(SVGGraphics2D g2d, int startX, int startY, int chartWidth) {
        g2d.setColor(new Color(146, 154, 186));
        g2d.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        // 时间刻度
        String[] timeLabels = {"21:00", "22:00", "23:00", "00:00", "01:00", "02:00", "03:00"};
        int hourWidth = chartWidth / 6;

        // 绘制时间刻度和标签
        for (int i = 0; i < timeLabels.length; i++) {
            int x = startX + i * hourWidth;

            // 绘制刻度线
            g2d.drawLine(x, startY, x, startY - 5);

            // 绘制时间标签
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(timeLabels[i]);
            g2d.drawString(timeLabels[i], x - textWidth / 2, startY - 8);
        }

        // 绘制时间轴线
        g2d.drawLine(startX, startY, startX + chartWidth, startY);
    }

    private static void drawGateLabels(SVGGraphics2D g2d, int startX, int startY, int rowHeight, int totalRows) {
        g2d.setColor(new Color(54, 140, 108));
        g2d.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        String[] gates = {"681", "682I", "682O", "682", "683", "684", "685", "690",
                "691", "692", "693", "694", "695", "696", "697", "698"};
        String[] types = {"X", "W", "W", "X", "X", "W", "X", "X",
                "X", "X", "W", "W", "X", "X", "X", "W"};

        int labelWidth = 90;
        int cornerRadius = 10;

        for (int i = 0; i < totalRows; i++) {
            int y = startY + i * rowHeight + 1;
            int rectHeight = rowHeight - 2;

            // 绘制圆角矩形背景
            g2d.setColor(new Color(54, 140, 108));
            g2d.fillRoundRect(startX - labelWidth, y, labelWidth - 5, rectHeight, cornerRadius, cornerRadius);

            // 绘制登机口编号（白色文字）
            g2d.setColor(Color.WHITE);
            FontMetrics fm = g2d.getFontMetrics();
            int gateWidth = fm.stringWidth(gates[i]);
            int gateX = startX - labelWidth + (labelWidth - 15 - gateWidth) / 2;
            g2d.drawString(gates[i], gateX, y + rectHeight / 2 + fm.getAscent() / 2 - 2);

            // 绘制类型（黑色文字）
            g2d.setColor(Color.BLACK);
            int typeX = startX - 20;
            g2d.drawString(types[i], typeX, y + rectHeight / 2 + fm.getAscent() / 2 - 2);
        }
    }

    private static void drawGanttBars(SVGGraphics2D g2d, int startX, int startY, int chartWidth, int rowHeight, List<Flight> flights) {
        int totalMinutes = 360; // 6小时 = 360分钟
        double minuteWidth = (double) chartWidth / totalMinutes;

        for (Flight flight : flights) {
            int y = startY + flight.row * rowHeight + 1;
            int barHeight = rowHeight - 2;
            int barX = startX + (int)(flight.startMinute * minuteWidth);
            int barWidth = (int)(flight.duration * minuteWidth);

            // 绘制蓝色背景条
            g2d.setColor(new Color(80, 112, 221));
            g2d.fillRect(barX, y, barWidth, barHeight);

            // 绘制黄色前景条（表示实际使用时间）
            int actualWidth = Math.min(barWidth, (int)(flight.duration * minuteWidth * 0.7));
            g2d.setColor(new Color(221, 179, 11));
            g2d.fillRect(barX, y, actualWidth, barHeight);

            // 绘制边框
            g2d.setColor(new Color(60, 60, 65));
            g2d.drawRect(barX, y, barWidth, barHeight);
        }
    }

    private static void drawFlightLabels(SVGGraphics2D g2d, int startX, int startY, int chartWidth, int rowHeight, List<Flight> flights) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        int totalMinutes = 360;
        double minuteWidth = (double) chartWidth / totalMinutes;

        for (Flight flight : flights) {
            int y = startY + flight.row * rowHeight + 1;
            int barHeight = rowHeight - 2;
            int barX = startX + (int)(flight.startMinute * minuteWidth);
            int barWidth = (int)(flight.duration * minuteWidth);

            // 只在条形图足够宽时显示航班号
            if (barWidth > 50) {
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(flight.flightNo);
                int textX = barX + (barWidth - textWidth) / 2;
                int textY = y + barHeight / 2 + fm.getAscent() / 2 - 2;

                g2d.drawString(flight.flightNo, textX, textY);
            }
        }
    }

    private static void drawLegend(SVGGraphics2D g2d, int x, int y) {
        g2d.setColor(new Color(224, 228, 242));
        g2d.fillRect(x, y, 195, 20);
        g2d.setColor(new Color(130, 146, 204));
        g2d.drawRect(x, y, 195, 20);

        // 图例内容
        g2d.setColor(new Color(60, 60, 65));
        g2d.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        g2d.drawString("计划时间 vs 实际使用时间", x + 10, y + 14);

        // 颜色示例
        g2d.setColor(new Color(80, 112, 221));
        g2d.fillRect(x + 160, y + 5, 15, 10);
        g2d.setColor(new Color(221, 179, 11));
        g2d.fillRect(x + 160, y + 5, 8, 10);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + 160, y + 5, 15, 10);
    }
}