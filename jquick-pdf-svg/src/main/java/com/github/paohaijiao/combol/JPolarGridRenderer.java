package com.github.paohaijiao.combol;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Path2D;

/**
 * 极坐标图形渲染器 - 使用SVGGraphics2D绘制
 */
public class JPolarGridRenderer extends JAbstractChartRenderer {


    @Override
    protected int getDefaultWidth() {
        return 500;
    }

    @Override
    protected int getDefaultHeight() {
        return 500;
    }

    @Override
    protected void drawChart(SVGGraphics2D svg, JOption option, int width, int height) {
        // 设置抗锯齿
        svg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int centerX = width / 2;   // 250
        int centerY = height / 2;  // 250
        svg.setPaint(Color.WHITE);     //绘制白色背景
        svg.fillRect(0, 0, width, height);
        svg.setStroke(new BasicStroke(1.0f));        // 绘制径向网格线（同心圆）
        svg.setPaint(new Color(221, 221, 221));
        int[] radii = {50, 100, 150, 200};
        for (int r : radii) {
            svg.drawOval(centerX - r, centerY - r, 2 * r, 2 * r);
        }
        svg.setStroke(new BasicStroke(0.8f));  // 绘制角度网格线（放射线）
        svg.setPaint(new Color(238, 238, 238));
        int maxRadius = 220;
        int[][] anglePoints = {
                {0, maxRadius}, {maxRadius, 0}, {0, -maxRadius}, {-maxRadius, 0},  // 0,90,180,270
                {110, 191}, {191, 110}, {-110, 191}, {-191, 110},  // 30,60,120,150度
                {110, -191}, {191, -110}, {-110, -191}, {-191, -110}  // 210,240,300,330度
        };
        for (int[] p : anglePoints) {
            svg.drawLine(centerX, centerY, centerX + p[0], centerY + p[1]);
            svg.drawLine(centerX, centerY, centerX - p[0], centerY - p[1]);
        }
       //绘制主轴（加粗）
        svg.setStroke(new BasicStroke(1.2f));
        svg.setPaint(new Color(170, 170, 170));
        svg.drawLine(centerX, centerY - 210, centerX, centerY + 210);
        svg.drawLine(centerX - 210, centerY, centerX + 210, centerY);
        //绘制数据曲线1（外圈玫瑰线）
        int[][] curve1Points = {
                {0, 0}, {50, 0}, {97, 26}, {87, 50}, {50, 87}, {0, 100},
                {-50, 87}, {-87, 50}, {-100, 0}, {-87, -50}, {-50, -87}, {0, -100},
                {50, -87}, {87, -50}, {100, 0}, {87, 50}, {50, 87}, {0, 100},
                {-50, 87}, {-87, 50}
        };
        drawPolyline(svg, centerX, centerY, curve1Points, new Color(255, 159, 67), 1.8f, 0.8f, new float[]{4, 3});
        //绘制数据曲线2（内圈玫瑰线）
        int[][] curve2Points = {
                {0, 0}, {30, 0}, {52, 15}, {45, 30}, {26, 45}, {0, 52},
                {-26, 45}, {-45, 30}, {-52, 0}, {-45, -30}, {-26, -45}, {0, -52},
                {26, -45}, {45, -30}, {52, 0}, {45, 30}, {26, 45}
        };
        drawPolyline(svg, centerX, centerY, curve2Points, new Color(255, 159, 67), 1.8f, 0.6f, null);
        //绘制扇形区域
        drawSector(svg, centerX, centerY, 200, 45, 135, new Color(255, 107, 107), 0.1f);
        //绘制数据点
        svg.setPaint(new Color(255, 107, 107));
        int[][] markers = {
                {100, 0}, {87, 50}, {50, 87}, {0, 100},
                {-50, 87}, {-87, 50}, {-100, 0}
        };
        for (int[] m : markers) {
            svg.fillOval(centerX + m[0] - 3, centerY + m[1] - 3, 7, 7);
        }
        //绘制中心点
        svg.setPaint(new Color(255, 107, 107));
        svg.fillOval(centerX - 4, centerY - 4, 8, 8);
        svg.setPaint(Color.WHITE);
        svg.fillOval(centerX - 2, centerY - 2, 4, 4);
        //绘制角度标签
        svg.setFont(new Font("monospace", Font.PLAIN, 10));
        svg.setPaint(new Color(102, 102, 102));
        String[] angleLabels = {"0°", "30°", "60°", "90°", "120°", "150°",
                "180°", "210°", "240°", "270°", "300°", "330°"};
        int[][] labelPos = {
                {210, 4}, {178, -111}, {99, -190}, {-8, -219},
                {-118, -190}, {-197, -111}, {-226, 4}, {-197, 103},
                {-118, 182}, {-11, 211}, {96, 182}, {175, 103}
        };
        for (int i = 0; i < angleLabels.length; i++) {
            int x = centerX + labelPos[i][0];
            int y = centerY + labelPos[i][1];
            drawCenteredText(svg, angleLabels[i], x, y);
        }
        //绘制径向标签
        svg.setPaint(new Color(153, 153, 153));
        int[] radialPos = {55, 105, 155, 205};
        for (int i = 0; i < radii.length; i++) {
            String text = "r=" + radii[i];
            int x = centerX + radialPos[i];
            int y = centerY + 4;
            svg.drawString(text, x, y);
        }
        // 绘制图例和标题
        svg.setFont(new Font("monospace", Font.BOLD, 12));
        svg.setPaint(new Color(119, 119, 119));
        svg.drawString("极坐标图", centerX - 230, centerY - 230);
        svg.setStroke(new BasicStroke(1.8f));
        svg.setPaint(new Color(255, 159, 67));
        svg.drawLine(centerX - 230, centerY - 220, centerX - 200, centerY - 220);
        svg.setFont(new Font("monospace", Font.PLAIN, 9));
        svg.setPaint(new Color(119, 119, 119));
        svg.drawString("r = a·sin(4θ)", centerX - 195, centerY - 217);
        svg.setPaint(new Color(255, 107, 107));
        svg.fillOval(centerX - 215, centerY - 205, 7, 7);
        svg.drawString("数据点", centerX - 195, centerY - 199);
    }

    /**
     * 绘制折线
     */
    private void drawPolyline(SVGGraphics2D svg, int cx, int cy, int[][] points, Color color, float width, float opacity, float[] dash) {
        if (points.length < 2) return;

        // 设置透明度
        if (opacity < 1.0f) {
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 * opacity));
        }
        svg.setPaint(color);
        // 设置虚线样式
        if (dash != null) {
            svg.setStroke(new BasicStroke(width, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL, 0, dash, 0));
        } else {
            svg.setStroke(new BasicStroke(width));
        }
        // 构建路径
        Path2D path = new Path2D.Double();
        path.moveTo(cx + points[0][0], cy + points[0][1]);
        for (int i = 1; i < points.length; i++) {
            path.lineTo(cx + points[i][0], cy + points[i][1]);
        }
        svg.draw(path);
    }

    /**
     * 绘制扇形
     */
    private void drawSector(SVGGraphics2D svg, int cx, int cy, int radius, int startAngle, int endAngle, Color color, float opacity) {
        double startRad = Math.toRadians(startAngle);
        double endRad = Math.toRadians(endAngle);
        int startX = cx + (int) (radius * Math.cos(startRad));
        int startY = cy + (int) (radius * Math.sin(startRad));
        int endX = cx + (int) (radius * Math.cos(endRad));
        int endY = cy + (int) (radius * Math.sin(endRad));
        // 构建扇形路径
        Path2D path = new Path2D.Double();
        path.moveTo(cx, cy);
        path.lineTo(startX, startY);

        // 绘制圆弧
        int angleSpan = endAngle - startAngle;
        boolean largeArc = angleSpan > 180;
        path.append(new java.awt.geom.Arc2D.Double(cx - radius, cy - radius, 2 * radius, 2 * radius, startAngle, angleSpan, java.awt.geom.Arc2D.OPEN), true);
        path.lineTo(cx, cy);
        // 填充扇形
        Color fillColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 * opacity));
        svg.setPaint(fillColor);
        svg.fill(path);
        // 绘制扇形边框
        svg.setStroke(new BasicStroke(2.0f));
        svg.setPaint(color);
        svg.draw(path);
    }

    /**
     * 绘制居中文本
     */
    private void drawCenteredText(SVGGraphics2D svg, String text, int x, int y) {
        FontMetrics fm = svg.getFontMetrics();
        int textX = x - fm.stringWidth(text) / 2;
        int textY = y + fm.getHeight() / 3;
        svg.drawString(text, textX, textY);
    }
}