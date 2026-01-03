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
package com.github.paohaijiao.geo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.paohaijiao.JOption;
import com.github.paohaijiao.geo.model.Bounds;
import com.github.paohaijiao.geo.model.GeometryData;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

/**
 * GeoJSON 渲染器（基于 JAbstractChartRenderer 设计模式）
 */
public class JGeoJsonRenderer extends JAbstractChartRenderer {

    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 计算所有几何对象的边界
     */
    private Bounds calculateBounds(List<GeometryData> geometries, double padding) {
        if (geometries.isEmpty()) {
            return new Bounds(110, 35, 120, 45);
        }
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        for (GeometryData geom : geometries) {
            Bounds geomBounds = getGeometryBounds(geom);
            minX = Math.min(minX, geomBounds.getMinX());
            minY = Math.min(minY, geomBounds.getMinY());
            maxX = Math.max(maxX, geomBounds.getMaxX());
            maxY = Math.max(maxY, geomBounds.getMaxY());
        }
        double width = maxX - minX;
        double height = maxY - minY;
        minX -= width * padding;
        maxX += width * padding;
        minY -= height * padding;
        maxY += height * padding;
        return new Bounds(minX, minY, maxX, maxY);
    }

    /**
     * 获取单个几何对象的边界
     */
    private Bounds getGeometryBounds(GeometryData geom) {
        Bounds bounds = new Bounds(Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
        switch (geom.getType()) {
            case "Polygon":
                processPolygonCoordinates(geom.getCoordinates().get(0), bounds);
                break;
            case "MultiPolygon":
                for (JsonNode polygon : geom.getCoordinates()) {
                    processPolygonCoordinates(polygon.get(0), bounds);
                }
                break;
            case "LineString":
                processLineCoordinates(geom.getCoordinates(), bounds);
                break;
            case "Point":
                processPointCoordinates(geom.getCoordinates(), bounds);
                break;
        }

        return bounds;
    }

    /**
     * 设置视图变换
     */
    private void setupViewport(SVGGraphics2D g2d, Bounds bounds, int width, int height) {
        double scaleX = width / (bounds.getMaxX() - bounds.getMinX());
        double scaleY = height / (bounds.getMaxY() - bounds.getMinY());
        double scale = Math.min(scaleX, scaleY);
        double scaledWidth = (bounds.getMaxX() - bounds.getMinX()) * scale;
        double scaledHeight = (bounds.getMaxY() - bounds.getMinY()) * scale;
        double offsetX = (width - scaledWidth) / 2;
        double offsetY = (height - scaledHeight) / 2;
        AffineTransform transform = new AffineTransform();
        transform.translate(offsetX, offsetY);
        transform.scale(scale, scale);
        transform.translate(-bounds.getMinX(), -bounds.getMinY());
        AffineTransform flipTransform = new AffineTransform();
        flipTransform.translate(0, height);
        flipTransform.scale(1, -1);
        flipTransform.concatenate(transform);
        g2d.setTransform(flipTransform);
    }

    /**
     * 绘制几何对象
     */
    private void drawGeometry(SVGGraphics2D g2d, JOption option, GeometryData geom) {
        Color color = parseColor(geom.getColor());
        switch (geom.getType()) {
            case "Polygon":
                drawPolygon(g2d, option, geom.getCoordinates().get(0), color, geom.getName());
                break;
            case "MultiPolygon":
                drawMultiPolygon(g2d, option, geom.getCoordinates(), color, geom.getName());
                break;
            case "LineString":
                drawLineString(g2d, option, geom.getCoordinates(), color, geom.getName());
                break;
            case "Point":
                drawPoint(g2d, option, geom.getCoordinates(), color, geom.getName());
                break;
        }
    }

    /**
     * 绘制多边形
     */
    private void drawPolygon(SVGGraphics2D g2d, JOption option, JsonNode coordinates, Color fillColor, String name) {
        Path2D path = new Path2D.Double();
        int size = coordinates.size();
        if (size > 0) {
            double x = coordinates.get(0).get(0).asDouble();
            double y = coordinates.get(0).get(1).asDouble();
            path.moveTo(x, y);
            for (int i = 1; i < size; i++) {
                x = coordinates.get(i).get(0).asDouble();
                y = coordinates.get(i).get(1).asDouble();
                path.lineTo(x, y);
            }
            path.closePath();
        }

        g2d.setColor(fillColor);
        g2d.fill(path);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(option.getGeoOption().getPadding()));
        g2d.draw(path);
        if (name != null && !name.isEmpty() && size > 0) {
            double centerX = 0;
            double centerY = 0;
            for (int i = 0; i < size; i++) {
                centerX += coordinates.get(i).get(0).asDouble();
                centerY += coordinates.get(i).get(1).asDouble();
            }
            centerX /= size;
            centerY /= size;
            drawLabel(g2d, name, centerX, centerY, Color.BLACK);
        }
    }

    /**
     * 绘制多个多边形（MultiPolygon）
     */
    private void drawMultiPolygon(SVGGraphics2D g2d, JOption option, JsonNode coordinates, Color fillColor, String name) {
        AffineTransform originalTransform = g2d.getTransform();
        double totalCenterX = 0;
        double totalCenterY = 0;
        int totalPoints = 0;
        for (JsonNode polygon : coordinates) {
            JsonNode exteriorRing = polygon.get(0);
            for (JsonNode coord : exteriorRing) {
                totalCenterX += coord.get(0).asDouble();
                totalCenterY += coord.get(1).asDouble();
                totalPoints++;
            }
        }

        double centerX = totalPoints > 0 ? totalCenterX / totalPoints : 0;
        double centerY = totalPoints > 0 ? totalCenterY / totalPoints : 0;
        for (JsonNode polygon : coordinates) {
            drawPolygon(g2d, option, polygon.get(0), fillColor, null);
        }
        if (name != null && !name.isEmpty()) {
            drawLabel(g2d, name, centerX, centerY, Color.BLACK);
        }
        g2d.setTransform(originalTransform);
    }

    /**
     * 绘制线
     */
    private void drawLineString(SVGGraphics2D g2d, JOption option, JsonNode coordinates, Color color, String name) {
        Path2D path = new Path2D.Double();
        int size = coordinates.size();
        if (size > 0) {
            double x = coordinates.get(0).get(0).asDouble();
            double y = coordinates.get(0).get(1).asDouble();
            path.moveTo(x, y);
            for (int i = 1; i < size; i++) {
                x = coordinates.get(i).get(0).asDouble();
                y = coordinates.get(i).get(1).asDouble();
                path.lineTo(x, y);
            }
        }

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(option.getGeoOption().getPadding()));
        g2d.draw(path);
    }

    /**
     * 绘制点
     */
    private void drawPoint(SVGGraphics2D g2d, JOption option, JsonNode coordinates, Color color, String name) {
        double x = coordinates.get(0).asDouble();
        double y = coordinates.get(1).asDouble();
        double radius = 0.2; // 小点
        g2d.setColor(color);
        g2d.fillOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(option.getGeoOption().getPadding()));
        g2d.drawOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }

    /**
     * 绘制文本标签
     */
    private void drawLabel(SVGGraphics2D g2d, String text, double x, double y, Color color) {
        AffineTransform originalTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        java.awt.geom.Point2D srcPoint = new java.awt.geom.Point2D.Double(x, y);
        java.awt.geom.Point2D destPoint = new java.awt.geom.Point2D.Double();
        originalTransform.transform(srcPoint, destPoint);
        g2d.setColor(color);
        g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        g2d.setColor(new Color(255, 255, 255, 200)); // 半透明白色背景
        g2d.fillRect((int) destPoint.getX() - textWidth / 2 - 2, (int) destPoint.getY() - fm.getHeight() / 2, textWidth + 4, fm.getHeight());
        g2d.setColor(color);
        g2d.drawString(text, (float) destPoint.getX() - textWidth / 2, (float) destPoint.getY() + fm.getHeight() / 4);
        g2d.setTransform(originalTransform);
    }

    /**
     * 绘制标题
     */
    private void drawTitle(SVGGraphics2D g2d, int width, int height, String title) {
        AffineTransform originalTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 24));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(title);
        g2d.drawString(title, width / 2 - textWidth / 2, 40);
        g2d.setTransform(originalTransform);
    }

    /**
     * 解析颜色
     */
    private Color parseColor(String colorStr) {
        try {
            if (colorStr.startsWith("#")) {
                return Color.decode(colorStr);
            } else if (colorStr.startsWith("rgb")) {
                String[] parts = colorStr.replaceAll("[rgb()\\s]", "").split(",");
                int r = Integer.parseInt(parts[0]);
                int g = Integer.parseInt(parts[1]);
                int b = Integer.parseInt(parts[2]);
                return new Color(r, g, b);
            }
        } catch (Exception e) {
            System.err.println("解析颜色失败: " + colorStr + ", 使用默认颜色");
        }
        return new Color((int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55);
    }

    /**
     * 坐标处理方法
     */
    private void processPolygonCoordinates(JsonNode coordinates, Bounds bounds) {
        for (JsonNode coord : coordinates) {
            double x = coord.get(0).asDouble();
            double y = coord.get(1).asDouble();
            bounds.setMinX(Math.min(bounds.getMinX(), x));
            bounds.setMinY(Math.min(bounds.getMinY(), y));
            bounds.setMaxX(Math.max(bounds.getMaxX(), x));
            bounds.setMaxY(Math.max(bounds.getMaxY(), y));
        }
    }

    private void processLineCoordinates(JsonNode coordinates, Bounds bounds) {
        for (JsonNode coord : coordinates) {
            double x = coord.get(0).asDouble();
            double y = coord.get(1).asDouble();
            bounds.setMinX(Math.min(bounds.getMinX(), x));
            bounds.setMinY(Math.min(bounds.getMinY(), y));
            bounds.setMaxX(Math.max(bounds.getMaxX(), x));
            bounds.setMaxY(Math.max(bounds.getMaxY(), y));
        }
    }

    private void processPointCoordinates(JsonNode coordinates, Bounds bounds) {
        double x = coordinates.get(0).asDouble();
        double y = coordinates.get(1).asDouble();
        bounds.setMinX(Math.min(bounds.getMinX(), x));
        bounds.setMinY(Math.min(bounds.getMinY(), y));
        bounds.setMaxX(Math.max(bounds.getMaxX(), x));
        bounds.setMaxY(Math.max(bounds.getMaxY(), y));
    }


    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        try {
            JsonNode root = objectMapper.readTree(option.getGeoOption().getGeoJsonContent());
            JsonNode features = root.get("features");
            List<GeometryData> geometries = new ArrayList<>();
            for (JsonNode feature : features) {
                JsonNode geometry = feature.get("geometry");
                JsonNode properties = feature.get("properties");
                String type = geometry.get("type").asText();
                String color = option.getGeoOption().getBackgroundColor();
                if (properties != null && properties.has("color")) {
                    color = properties.get("color").asText();
                }
                String name = "未命名区域"; // 默认名称
                if (properties != null && properties.has("name")) {
                    name = properties.get("name").asText();
                }
                JsonNode coordinates = geometry.get("coordinates");
                geometries.add(new GeometryData(type, color, name, coordinates));
            }
            svgGenerator.setSVGCanvasSize(new Dimension(width, height));
            svgGenerator.setColor(Color.WHITE);
            svgGenerator.fillRect(0, 0, width, height);
            Bounds bounds = calculateBounds(geometries, option.getGeoOption().getPadding());
            System.out.printf("地图边界: minX=%.6f, minY=%.6f, maxX=%.6f, maxY=%.6f%n", bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
            setupViewport(svgGenerator, bounds, width, height);
            for (GeometryData geom : geometries) {
                drawGeometry(svgGenerator, option, geom);
            }
            drawTitle(svgGenerator, width, height, option.getGeoOption().getTitle());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
