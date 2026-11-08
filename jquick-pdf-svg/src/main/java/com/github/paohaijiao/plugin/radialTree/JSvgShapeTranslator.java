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
package com.github.paohaijiao.plugin.radialTree;

import org.apache.batik.parser.AWTPathProducer;
import org.apache.batik.parser.AWTTransformProducer;
import org.apache.batik.parser.PathParser;
import org.apache.batik.parser.TransformListParser;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * packageName com.github.paohaijiao.plugin.radialTree
 *
 * @author Martin
 * @version 1.0.0
 * @classname JSvgShapeTranslator
 * @date 2026/11/8
 * @description Replays an SVG document onto a Graphics2D surface.
 * <p>
 * Every SVG element is converted into a java.awt.Shape or a drawString call, then painted
 * through the given SVGGraphics2D. No node is appended to the DOM directly and no transcoder
 * is involved, the output is produced by the normal svgGenerator.stream(...) path.
 * <p>
 * Supported elements: svg, g, path, rect, circle, ellipse, line, polyline, polygon, text.
 * Supported presentation attributes: fill, fill-opacity, stroke, stroke-width, stroke-opacity,
 * opacity, transform, font-family, font-size, font-weight, text-anchor.
 */
public final class JSvgShapeTranslator {

    private static final String DEFAULT_FONT_FAMILY = "Microsoft YaHei";

    private static final float DEFAULT_FONT_SIZE = 12f;

    private static final String[] IGNORED_ELEMENTS = {"defs", "clipPath", "mask", "linearGradient", "radialGradient", "filter", "metadata"};

    private JSvgShapeTranslator() {
    }

    /**
     * Paints the whole document, starting from its root element.
     *
     * @param document     parsed SVG document
     * @param svgGenerator target graphics, never null
     */
    public static void paint(Document document, SVGGraphics2D svgGenerator) {
        if (document == null || svgGenerator == null) {
            return;
        }
        Element root = document.getDocumentElement();
        if (root == null) {
            return;
        }
        paintElement(root, svgGenerator, 1.0d);
    }

    private static void paintElement(Element element, SVGGraphics2D svgGenerator, double inheritedOpacity) {
        String tag = localName(element);
        if (isIgnored(tag)) {
            return;
        }
        double opacity = inheritedOpacity * number(property(element, "opacity"), 1.0d);
        AffineTransform origin = svgGenerator.getTransform();
        boolean transformed = applyTransform(element, svgGenerator);
        try {
            switch (tag) {
                case "svg":
                case "g":
                case "a":
                    paintChildren(element, svgGenerator, opacity);
                    break;
                case "path":
                    paintShape(pathShape(property(element, "d")), element, svgGenerator, opacity);
                    break;
                case "rect":
                    paintShape(rectShape(element), element, svgGenerator, opacity);
                    break;
                case "circle":
                    paintShape(circleShape(element), element, svgGenerator, opacity);
                    break;
                case "ellipse":
                    paintShape(ellipseShape(element), element, svgGenerator, opacity);
                    break;
                case "line":
                    paintShape(lineShape(element), element, svgGenerator, opacity);
                    break;
                case "polyline":
                case "polygon":
                    paintShape(polygonShape(element, "polygon".equals(tag)), element, svgGenerator, opacity);
                    break;
                case "text":
                    paintText(element, svgGenerator, opacity);
                    break;
                default:
                    paintChildren(element, svgGenerator, opacity);
                    break;
            }
        } finally {
            if (transformed) {
                svgGenerator.setTransform(origin);
            }
        }
    }

    private static void paintChildren(Element element, SVGGraphics2D svgGenerator, double opacity) {
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                paintElement((Element) node, svgGenerator, opacity);
            }
        }
    }

    private static void paintShape(Shape shape, Element element, SVGGraphics2D svgGenerator, double opacity) {
        if (shape == null) {
            return;
        }
        String fillValue = property(element, "fill");
        Color fill = color(fillValue, opacity * number(property(element, "fill-opacity"), 1.0d));
        if (fill != null) {
            svgGenerator.setPaint(fill);
            svgGenerator.fill(shape);
        }
        String strokeValue = property(element, "stroke");
        Color stroke = color(strokeValue, opacity * number(property(element, "stroke-opacity"), 1.0d));
        if (stroke != null) {
            float width = (float) number(property(element, "stroke-width"), 1.0d);
            svgGenerator.setPaint(stroke);
            svgGenerator.setStroke(new BasicStroke(Math.max(width, 0.1f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            svgGenerator.draw(shape);
        }
    }

    private static void paintText(Element element, SVGGraphics2D svgGenerator, double opacity) {
        String content = text(element);
        if (content.isEmpty()) {
            return;
        }
        float fontSize = (float) number(property(element, "font-size"), DEFAULT_FONT_SIZE);
        String family = property(element, "font-family");
        if (family == null || family.isEmpty()) {
            family = DEFAULT_FONT_FAMILY;
        }
        family = family.split(",")[0].replace("'", "").replace("\"", "").trim();
        boolean bold = "bold".equalsIgnoreCase(property(element, "font-weight"));
        svgGenerator.setFont(new Font(family, bold ? Font.BOLD : Font.PLAIN, Math.max(1, Math.round(fontSize))));
        Color fill = color(property(element, "fill"), opacity);
        svgGenerator.setPaint(fill == null ? Color.BLACK : fill);
        double x = number(property(element, "x"), 0d);
        double y = number(property(element, "y"), 0d);
        String anchor = property(element, "text-anchor");
        int textWidth = svgGenerator.getFontMetrics().stringWidth(content);
        if ("middle".equalsIgnoreCase(anchor)) {
            x -= textWidth / 2.0d;
        } else if ("end".equalsIgnoreCase(anchor)) {
            x -= textWidth;
        }
        double dy = number(property(element, "dy"), 0d);
        svgGenerator.drawString(content, (float) x, (float) (y + dy));
    }

    private static boolean applyTransform(Element element, SVGGraphics2D svgGenerator) {
        String value = property(element, "transform");
        if (value == null || value.isEmpty()) {
            return false;
        }
        try {
            TransformListParser parser = new TransformListParser();
            AWTTransformProducer producer = new AWTTransformProducer();
            parser.setTransformListHandler(producer);
            parser.parse(value);
            AffineTransform transform = producer.getAffineTransform();
            if (transform == null || transform.isIdentity()) {
                return false;
            }
            svgGenerator.transform(transform);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private static Shape pathShape(String data) {
        if (data == null || data.trim().isEmpty()) {
            return null;
        }
        try {
            PathParser parser = new PathParser();
            AWTPathProducer producer = new AWTPathProducer();
            parser.setPathHandler(producer);
            parser.parse(data);
            return producer.getShape();
        } catch (Exception ignored) {
            return null;
        }
    }

    private static Shape rectShape(Element element) {
        double x = number(property(element, "x"), 0d);
        double y = number(property(element, "y"), 0d);
        double width = number(property(element, "width"), 0d);
        double height = number(property(element, "height"), 0d);
        if (width <= 0 || height <= 0) {
            return null;
        }
        double rx = number(property(element, "rx"), 0d);
        double ry = number(property(element, "ry"), 0d);
        if (rx > 0 || ry > 0) {
            return new RoundRectangle2D.Double(x, y, width, height, rx * 2, Math.max(ry, rx) * 2);
        }
        return new Rectangle2D.Double(x, y, width, height);
    }

    private static Shape circleShape(Element element) {
        double cx = number(property(element, "cx"), 0d);
        double cy = number(property(element, "cy"), 0d);
        double r = number(property(element, "r"), 0d);
        if (r <= 0) {
            return null;
        }
        return new Ellipse2D.Double(cx - r, cy - r, r * 2, r * 2);
    }

    private static Shape ellipseShape(Element element) {
        double cx = number(property(element, "cx"), 0d);
        double cy = number(property(element, "cy"), 0d);
        double rx = number(property(element, "rx"), 0d);
        double ry = number(property(element, "ry"), 0d);
        if (rx <= 0 || ry <= 0) {
            return null;
        }
        return new Ellipse2D.Double(cx - rx, cy - ry, rx * 2, ry * 2);
    }

    private static Shape lineShape(Element element) {
        return new Line2D.Double(
                number(property(element, "x1"), 0d),
                number(property(element, "y1"), 0d),
                number(property(element, "x2"), 0d),
                number(property(element, "y2"), 0d));
    }

    private static Shape polygonShape(Element element, boolean close) {
        String points = property(element, "points");
        if (points == null || points.trim().isEmpty()) {
            return null;
        }
        String[] tokens = points.trim().split("[\\s,]+");
        if (tokens.length < 4) {
            return null;
        }
        Path2D.Double path = new Path2D.Double();
        for (int i = 0; i + 1 < tokens.length; i += 2) {
            double x = number(tokens[i], 0d);
            double y = number(tokens[i + 1], 0d);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        if (close) {
            path.closePath();
        }
        return path;
    }

    private static String text(Element element) {
        String content = element.getTextContent();
        return content == null ? "" : content.replaceAll("\\s+", " ").trim();
    }

    private static String localName(Element element) {
        String name = element.getLocalName();
        if (name == null) {
            name = element.getNodeName();
            int index = name.indexOf(':');
            if (index >= 0) {
                name = name.substring(index + 1);
            }
        }
        return name;
    }

    private static boolean isIgnored(String tag) {
        for (String ignored : IGNORED_ELEMENTS) {
            if (ignored.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reads a presentation attribute, falling back to the inline style declaration.
     *
     * @param element target element
     * @param name    attribute name
     * @return attribute value or null
     */
    private static String property(Element element, String name) {
        String direct = element.getAttribute(name);
        if (direct != null && !direct.trim().isEmpty()) {
            return direct.trim();
        }
        String style = element.getAttribute("style");
        if (style != null && !style.isEmpty()) {
            String[] declarations = style.split(";");
            for (String declaration : declarations) {
                int index = declaration.indexOf(':');
                if (index > 0 && name.equals(declaration.substring(0, index).trim())) {
                    return declaration.substring(index + 1).trim();
                }
            }
        }
        return null;
    }

    private static double number(String value, double fallback) {
        if (value == null || value.trim().isEmpty() || "none".equalsIgnoreCase(value.trim())) {
            return fallback;
        }
        String cleaned = value.trim().replaceAll("(px|pt|em|%)$", "");
        try {
            double result = Double.parseDouble(cleaned);
            if (value.trim().endsWith("%")) {
                return result / 100.0d;
            }
            return result;
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    private static Color color(String value, double alpha) {
        if (value == null || value.isEmpty() || "none".equalsIgnoreCase(value) || "transparent".equalsIgnoreCase(value)) {
            return null;
        }
        Color base = parseColor(value.trim());
        if (base == null) {
            return null;
        }
        int a = (int) Math.round(Math.min(Math.max(alpha, 0d), 1d) * 255);
        return new Color(base.getRed(), base.getGreen(), base.getBlue(), a);
    }

    private static Color parseColor(String value) {
        if (value.startsWith("#")) {
            return parseHex(value);
        }
        if (value.startsWith("rgb")) {
            return parseRgb(value);
        }
        String lower = value.toLowerCase();
        if ("black".equals(lower)) {
            return Color.BLACK;
        }
        if ("white".equals(lower)) {
            return Color.WHITE;
        }
        if ("red".equals(lower)) {
            return Color.RED;
        }
        if ("green".equals(lower)) {
            return Color.GREEN;
        }
        if ("blue".equals(lower)) {
            return Color.BLUE;
        }
        if ("gray".equals(lower) || "grey".equals(lower)) {
            return Color.GRAY;
        }
        try {
            return Color.decode(value);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private static Color parseHex(String value) {
        String hex = value.substring(1);
        try {
            if (hex.length() == 3) {
                int r = Integer.parseInt(hex.substring(0, 1), 16) * 17;
                int g = Integer.parseInt(hex.substring(1, 2), 16) * 17;
                int b = Integer.parseInt(hex.substring(2, 3), 16) * 17;
                return new Color(r, g, b);
            }
            if (hex.length() >= 6) {
                return new Color(Integer.parseInt(hex.substring(0, 6), 16));
            }
        } catch (NumberFormatException ignored) {
            return null;
        }
        return null;
    }

    private static Color parseRgb(String value) {
        int start = value.indexOf('(');
        int end = value.indexOf(')');
        if (start < 0 || end <= start) {
            return null;
        }
        String[] parts = value.substring(start + 1, end).split("[,\\s/]+");
        if (parts.length < 3) {
            return null;
        }
        int r = channel(parts[0]);
        int g = channel(parts[1]);
        int b = channel(parts[2]);
        double alpha = parts.length > 3 ? number(parts[3], 1d) : 1d;
        return new Color(r, g, b, (int) Math.round(Math.min(Math.max(alpha, 0d), 1d) * 255));
    }

    private static int channel(String value) {
        String trimmed = value.trim();
        double parsed;
        if (trimmed.endsWith("%")) {
            parsed = number(trimmed.substring(0, trimmed.length() - 1), 0d) * 255d;
        } else {
            parsed = number(trimmed, 0d);
        }
        return (int) Math.min(Math.max(parsed, 0d), 255d);
    }
}
