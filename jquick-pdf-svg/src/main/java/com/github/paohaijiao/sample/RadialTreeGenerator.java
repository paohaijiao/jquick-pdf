package com.github.paohaijiao.sample;

import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
public class RadialTreeGenerator {
    static class TreeNode {
        String name;
        List<TreeNode> children = new ArrayList<>();
        int value;
        Point2D position;
        double angle;
        double radius;

        TreeNode(String name) {
            this.name = name;
        }

        TreeNode(String name, int value) {
            this.name = name;
            this.value = value;
        }

        void addChild(TreeNode child) {
            children.add(child);
        }
    }

    // 创建示例树结构
    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode("Root");

        TreeNode tech = new TreeNode("技术");
        tech.addChild(new TreeNode("前端开发", 8));
        tech.addChild(new TreeNode("后端开发", 10));
        tech.addChild(new TreeNode("DevOps", 6));
        tech.addChild(new TreeNode("数据库1", 7));
        tech.addChild(new TreeNode("数据库2", 7));
        tech.addChild(new TreeNode("数据库3", 7));
        tech.addChild(new TreeNode("4", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));
        tech.addChild(new TreeNode("数据库", 7));

        TreeNode design = new TreeNode("设计");
        design.addChild(new TreeNode("UI设计", 9));
        design.addChild(new TreeNode("UX研究", 5));
        design.addChild(new TreeNode("图形设计", 7));

        TreeNode business = new TreeNode("业务");
        business.addChild(new TreeNode("市场分析", 8));
        business.addChild(new TreeNode("产品管理", 9));
        business.addChild(new TreeNode("客户支持", 6));

        root.addChild(tech);
        root.addChild(design);
        root.addChild(business);

        return root;
    }

    // 计算径向布局
    private static void calculateRadialLayout(TreeNode node, Point2D center,
                                              double startAngle, double endAngle,
                                              double radius, int depth) {
        if (node == null) return;

        int childCount = node.children.size();
        if (childCount == 0) return;

        double angleStep = (endAngle - startAngle) / childCount;
        double currentAngle = startAngle;

        for (int i = 0; i < childCount; i++) {
            TreeNode child = node.children.get(i);

            // 计算子节点位置
            double childRadius = radius + 80; // 每层增加80像素
            double x = center.getX() + childRadius * Math.cos(currentAngle);
            double y = center.getY() + childRadius * Math.sin(currentAngle);

            child.position = new Point2D.Double(x, y);
            child.angle = currentAngle;
            child.radius = childRadius;

            // 递归计算子节点的子节点
            double childStartAngle = currentAngle - angleStep / 2;
            double childEndAngle = currentAngle + angleStep / 2;

            calculateRadialLayout(child, center, childStartAngle, childEndAngle, childRadius, depth + 1);

            currentAngle += angleStep;
        }
    }

    // 生成SVG
    public static void generateSVG(String filename) throws Exception {
        // 创建DOM文档
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // 创建SVG图形上下文
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // 设置SVG尺寸
        svgGenerator.setSVGCanvasSize(new Dimension(800, 600));

        // 绘制背景
        svgGenerator.setPaint(new Color(240, 240, 240));
        svgGenerator.fillRect(0, 0, 800, 600);

        // 创建树结构
        TreeNode root = createSampleTree();
        Point2D center = new Point2D.Double(400, 300);
        root.position = center;

        // 计算布局
        calculateRadialLayout(root, center, 0, 2 * Math.PI, 0, 0);

        // 绘制连接线
        drawConnections(svgGenerator, root);

        // 绘制节点
        drawNodes(svgGenerator, root);

        // 输出到文件
        try (Writer out = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8")) {
            svgGenerator.stream(out, true /* useCSS */);
        }
    }

    // 绘制连接线
    private static void drawConnections(SVGGraphics2D svgGenerator, TreeNode node) {
        if (node == null || node.children.isEmpty()) return;

        svgGenerator.setPaint(new Color(150, 150, 150));
        svgGenerator.setStroke(new BasicStroke(1.5f));

        for (TreeNode child : node.children) {
            // 绘制从父节点到子节点的线
            Line2D line = new Line2D.Double(
                    node.position.getX(), node.position.getY(),
                    child.position.getX(), child.position.getY()
            );
            svgGenerator.draw(line);

            // 递归绘制子节点的连接线
            drawConnections(svgGenerator, child);
        }
    }

    // 绘制节点
    private static void drawNodes(SVGGraphics2D svgGenerator, TreeNode node) {
        if (node == null) return;

        // 绘制当前节点
        double radius = node.children.isEmpty() ? 6 : 8;

        // 根据节点类型设置颜色
        if (node.name.equals("Root")) {
            svgGenerator.setPaint(new Color(52, 152, 219)); // 蓝色
        } else if (node.children.isEmpty()) {
            svgGenerator.setPaint(new Color(231, 76, 60)); // 红色
        } else {
            svgGenerator.setPaint(new Color(46, 204, 113)); // 绿色
        }

        Ellipse2D circle = new Ellipse2D.Double(
                node.position.getX() - radius,
                node.position.getY() - radius,
                radius * 2,
                radius * 2
        );
        svgGenerator.fill(circle);

        // 绘制节点边框
        svgGenerator.setPaint(Color.DARK_GRAY);
        svgGenerator.setStroke(new BasicStroke(1.0f));
        svgGenerator.draw(circle);

        // 绘制节点文本
        svgGenerator.setPaint(Color.BLACK);
        Font font = new Font("Arial", Font.PLAIN, 12);
        svgGenerator.setFont(font);
        double textX = node.position.getX();
        double textY = node.position.getY() - radius - 5;
        if (node != null && node.angle != 0) {
            if (node.angle > Math.PI / 2 && node.angle < 3 * Math.PI / 2) {
                textX -= svgGenerator.getFontMetrics().stringWidth(node.name) + 5;
            }
        }

        svgGenerator.drawString(node.name, (float) textX, (float) textY);

        // 递归绘制子节点
        for (TreeNode child : node.children) {
            drawNodes(svgGenerator, child);
        }
    }

    public static void main(String[] args) {
        try {
            generateSVG("d://test//radial_tree.svg");
            System.out.println("SVG文件已生成: radial_tree.svg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
