import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GeoJSON 渲染器（不使用 JTS，纯 Jackson 解析）
 */
public class GeoJsonRendererSimple {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final float lineWidth = 0.005f;
    private String backgroundColor ="#4CAF50";
    public static void main(String[] args) {
        try {
            GeoJsonRendererSimple renderer = new GeoJsonRendererSimple();
            String geoJsonContent = readFile("d://sample//test.geojson");
            String svgContent = renderer.renderGeoJsonToSvg(geoJsonContent, 1200, 800, 0.0);
            saveToFile("d://test//output.svg", svgContent);
            System.out.println("SVG 文件已保存: output.svg");
            renderer.convertSvgToPng("d://test//output.svg", "d://test//output.png", 1200, 800);
            System.out.println("PNG 文件已保存: output.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 工具方法：读取文件
     */
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    /**
     * 工具方法：保存文件
     */
    private static void saveToFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }

    /**
     * 将 GeoJSON 渲染为 SVG 字符串
     */
    public String renderGeoJsonToSvg(String geoJsonString, int width, int height, double padding) throws Exception {
        JsonNode root = objectMapper.readTree(geoJsonString);
        JsonNode features = root.get("features");
        List<GeometryData> geometries = new ArrayList<>();
        for (JsonNode feature : features) {
            JsonNode geometry = feature.get("geometry");
            JsonNode properties = feature.get("properties");
            String type = geometry.get("type").asText();
            String color = backgroundColor; // 默认颜色
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
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        svgGenerator.setSVGCanvasSize(new Dimension(width, height));
        svgGenerator.setColor(Color.WHITE);
        svgGenerator.fillRect(0, 0, width, height);
        Bounds bounds = calculateBounds(geometries, padding);
        System.out.printf("地图边界: minX=%.6f, minY=%.6f, maxX=%.6f, maxY=%.6f%n", bounds.minX, bounds.minY, bounds.maxX, bounds.maxY);
        setupViewport(svgGenerator, bounds, width, height);
        for (GeometryData geom : geometries) {
            drawGeometry(svgGenerator, geom);
        }
        drawTitle(svgGenerator, width, height, "河北省行政区划图");
        Writer writer = new StringWriter();
        svgGenerator.stream(writer, true);
        svgGenerator.dispose();
        return writer.toString();
    }

    /**
     * 计算所有几何对象的边界
     */
    private Bounds calculateBounds(List<GeometryData> geometries, double padding) {
        if (geometries.isEmpty()) {
            return new Bounds(110, 35, 120, 45); // 河北省大致范围
        }
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        for (GeometryData geom : geometries) {
            Bounds geomBounds = getGeometryBounds(geom);
            minX = Math.min(minX, geomBounds.minX);
            minY = Math.min(minY, geomBounds.minY);
            maxX = Math.max(maxX, geomBounds.maxX);
            maxY = Math.max(maxY, geomBounds.maxY);
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
        Bounds bounds = new Bounds(Double.MAX_VALUE, Double.MAX_VALUE,
                Double.MIN_VALUE, Double.MIN_VALUE);

        switch (geom.type) {
            case "Polygon":
                processPolygonCoordinates(geom.coordinates.get(0), bounds);
                break;
            case "MultiPolygon":
                for (JsonNode polygon : geom.coordinates) {
                    processPolygonCoordinates(polygon.get(0), bounds);
                }
                break;
            case "LineString":
                processLineCoordinates(geom.coordinates, bounds);
                break;
            case "Point":
                processPointCoordinates(geom.coordinates, bounds);
                break;
        }

        return bounds;
    }

    /**
     * 设置视图变换
     */
    private void setupViewport(SVGGraphics2D g2d, Bounds bounds, int width, int height) {
        double scaleX = width / (bounds.maxX - bounds.minX);
        double scaleY = height / (bounds.maxY - bounds.minY);
        double scale = Math.min(scaleX, scaleY);
        double scaledWidth = (bounds.maxX - bounds.minX) * scale;
        double scaledHeight = (bounds.maxY - bounds.minY) * scale;
        double offsetX = (width - scaledWidth) / 2;
        double offsetY = (height - scaledHeight) / 2;
        AffineTransform transform = new AffineTransform();
        transform.translate(offsetX, offsetY);
        transform.scale(scale, scale);
        transform.translate(-bounds.minX, -bounds.minY);
        AffineTransform flipTransform = new AffineTransform();
        flipTransform.translate(0, height);
        flipTransform.scale(1, -1);
        flipTransform.concatenate(transform);
        g2d.setTransform(flipTransform);
    }

    /**
     * 绘制几何对象
     */
    private void drawGeometry(SVGGraphics2D g2d, GeometryData geom) {
        Color color = parseColor(geom.color);
        switch (geom.type) {
            case "Polygon":
                drawPolygon(g2d, geom.coordinates.get(0), color, geom.name);
                break;
            case "MultiPolygon":
                drawMultiPolygon(g2d, geom.coordinates, color, geom.name);
                break;
            case "LineString":
                drawLineString(g2d, geom.coordinates, color, geom.name);
                break;
            case "Point":
                drawPoint(g2d, geom.coordinates, color, geom.name);
                break;
        }
    }

    /**
     * 绘制多边形
     */
    private void drawPolygon(SVGGraphics2D g2d, JsonNode coordinates, Color fillColor, String name) {
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
        g2d.setStroke(new BasicStroke(lineWidth));
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
    private void drawMultiPolygon(SVGGraphics2D g2d, JsonNode coordinates, Color fillColor, String name) {
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
            drawPolygon(g2d, polygon.get(0), fillColor, null);
        }
        if (name != null && !name.isEmpty()) {
            drawLabel(g2d, name, centerX, centerY, Color.BLACK);
        }
        g2d.setTransform(originalTransform);
    }

    /**
     * 绘制线
     */
    private void drawLineString(SVGGraphics2D g2d, JsonNode coordinates, Color color, String name) {
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
        g2d.setStroke(new BasicStroke(lineWidth));
        g2d.draw(path);
    }

    /**
     * 绘制点
     */
    private void drawPoint(SVGGraphics2D g2d, JsonNode coordinates, Color color, String name) {
        double x = coordinates.get(0).asDouble();
        double y = coordinates.get(1).asDouble();
        double radius = 0.2; // 小点
        g2d.setColor(color);
        g2d.fillOval(
                (int) (x - radius),
                (int) (y - radius),
                (int) (radius * 2),
                (int) (radius * 2)
        );

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(lineWidth));
        g2d.drawOval(
                (int) (x - radius),
                (int) (y - radius),
                (int) (radius * 2),
                (int) (radius * 2)
        );
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
        g2d.fillRect((int) destPoint.getX() - textWidth / 2 - 2,
                (int) destPoint.getY() - fm.getHeight() / 2,
                textWidth + 4,
                fm.getHeight());

        g2d.setColor(color);
        g2d.drawString(text,
                (float) destPoint.getX() - textWidth / 2,
                (float) destPoint.getY() + fm.getHeight() / 4);
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
        return new Color(
                (int) (Math.random() * 200) + 55,
                (int) (Math.random() * 200) + 55,
                (int) (Math.random() * 200) + 55
        );
    }

    /**
     * 坐标处理方法
     */
    private void processPolygonCoordinates(JsonNode coordinates, Bounds bounds) {
        for (JsonNode coord : coordinates) {
            double x = coord.get(0).asDouble();
            double y = coord.get(1).asDouble();
            bounds.minX = Math.min(bounds.minX, x);
            bounds.minY = Math.min(bounds.minY, y);
            bounds.maxX = Math.max(bounds.maxX, x);
            bounds.maxY = Math.max(bounds.maxY, y);
        }
    }

    private void processLineCoordinates(JsonNode coordinates, Bounds bounds) {
        for (JsonNode coord : coordinates) {
            double x = coord.get(0).asDouble();
            double y = coord.get(1).asDouble();
            bounds.minX = Math.min(bounds.minX, x);
            bounds.minY = Math.min(bounds.minY, y);
            bounds.maxX = Math.max(bounds.maxX, x);
            bounds.maxY = Math.max(bounds.maxY, y);
        }
    }

    private void processPointCoordinates(JsonNode coordinates, Bounds bounds) {
        double x = coordinates.get(0).asDouble();
        double y = coordinates.get(1).asDouble();
        bounds.minX = Math.min(bounds.minX, x);
        bounds.minY = Math.min(bounds.minY, y);
        bounds.maxX = Math.max(bounds.maxX, x);
        bounds.maxY = Math.max(bounds.maxY, y);
    }

    /**
     * 将 SVG 转换为 PNG
     */
    public void convertSvgToPng(String svgFilePath, String pngFilePath, int width, int height) throws Exception {
        File svgFile = new File(svgFilePath);
        File pngFile = new File(pngFilePath);

        PNGTranscoder transcoder = new PNGTranscoder();
        transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, (float) width);
        transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, (float) height);

        try (FileInputStream fis = new FileInputStream(svgFile);
             FileOutputStream fos = new FileOutputStream(pngFile)) {

            TranscoderInput input = new TranscoderInput(fis);
            TranscoderOutput output = new TranscoderOutput(fos);
            transcoder.transcode(input, output);
        }
    }

    /**
     * 辅助类：几何数据
     */
    private static class GeometryData {
        String type;
        String color;
        String name;
        JsonNode coordinates;

        GeometryData(String type, String color, String name, JsonNode coordinates) {
            this.type = type;
            this.color = color;
            this.name = name;
            this.coordinates = coordinates;
        }
    }

    /**
     * 辅助类：边界
     */
    private static class Bounds {
        double minX, minY, maxX, maxY;

        Bounds(double minX, double minY, double maxX, double maxY) {
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }
    }
}