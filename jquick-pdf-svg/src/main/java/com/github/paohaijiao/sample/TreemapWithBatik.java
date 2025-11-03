package com.github.paohaijiao.sample;

import com.github.paohaijiao.treemap.TreemapNode;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreemapWithBatik {
    private static final Map<String, Color> DEPARTMENT_COLORS = new HashMap<>();
    private static final Map<String, Color> CATEGORY_COLORS = new HashMap<>();

    static {
        DEPARTMENT_COLORS.put("技术部", new Color(33, 150, 243));    // 鲜明蓝（ECharts常用主色）
        DEPARTMENT_COLORS.put("财务部", new Color(244, 67, 54));     // 鲜艳红（高对比）
        DEPARTMENT_COLORS.put("销售部", new Color(76, 175, 80));     // 明亮绿（与蓝/红强区分）
        DEPARTMENT_COLORS.put("人力资源", new Color(255, 152, 0));   // 橙色（填补色相空白）
        DEPARTMENT_COLORS.put("市场营销", new Color(156, 39, 176));  // 深紫（与其他主色无重叠）

        CATEGORY_COLORS.put("软件开发", new Color(33, 150, 243));    // 主色
        CATEGORY_COLORS.put("基础设施", new Color(21, 101, 192));    // 深蓝（亮度-30）
        CATEGORY_COLORS.put("数据科学", new Color(66, 165, 245));    // 中蓝（亮度-10）
        CATEGORY_COLORS.put("质量保证", new Color(100, 181, 246));   // 浅蓝（亮度+10）
        CATEGORY_COLORS.put("前端开发", new Color(33, 150, 243));    // 继承主色
        CATEGORY_COLORS.put("后端开发", new Color(21, 101, 192));    // 深蓝
        CATEGORY_COLORS.put("移动开发", new Color(66, 165, 245));    // 中蓝
        CATEGORY_COLORS.put("React项目", new Color(33, 150, 243));   // 继承主色
        CATEGORY_COLORS.put("Vue项目", new Color(48, 164, 255));     // 微调浅蓝（亮度+5）
        CATEGORY_COLORS.put("Angular项目", new Color(144, 202, 249));// 更浅蓝（亮度+25）
        CATEGORY_COLORS.put("Java服务", new Color(21, 101, 192));    // 深蓝
        CATEGORY_COLORS.put("Python服务", new Color(13, 71, 161));    // 最深蓝（亮度-50）
        CATEGORY_COLORS.put("Node.js服务", new Color(66, 165, 245));  // 中蓝

        CATEGORY_COLORS.put("会计核算", new Color(244, 67, 54));     // 主色
        CATEGORY_COLORS.put("预算管理", new Color(211, 47, 47));     // 深红（亮度-15）
        CATEGORY_COLORS.put("税务管理", new Color(255, 87, 34));     // 浅红（偏橙红，亮度+10）
        CATEGORY_COLORS.put("内部审计", new Color(255, 112, 67));    // 更浅红（亮度+25）
        CATEGORY_COLORS.put("应收账款", new Color(244, 67, 54));     // 继承主色
        CATEGORY_COLORS.put("应付账款", new Color(229, 57, 53));     // 微调深红（亮度-5）
        CATEGORY_COLORS.put("总账管理", new Color(255, 105, 97));    // 微调浅红（亮度+15）

        CATEGORY_COLORS.put("国内销售", new Color(76, 175, 80));     // 主色
        CATEGORY_COLORS.put("国际销售", new Color(56, 142, 60));     // 深绿（亮度-20）
        CATEGORY_COLORS.put("线上销售", new Color(102, 189, 106));   // 浅绿（亮度+10）
        CATEGORY_COLORS.put("华北区域", new Color(76, 175, 80));     // 继承主色
        CATEGORY_COLORS.put("华南区域", new Color(67, 160, 71));     // 微调深绿（亮度-10）
        CATEGORY_COLORS.put("华东区域", new Color(129, 199, 132));   // 微调浅绿（亮度+20）

        CATEGORY_COLORS.put("招聘", new Color(255, 152, 0));         // 主色
        CATEGORY_COLORS.put("培训发展", new Color(245, 124, 0));     // 深橙（亮度-15）
        CATEGORY_COLORS.put("薪酬福利", new Color(255, 179, 0));     // 浅橙（亮度+15）
        CATEGORY_COLORS.put("员工关系", new Color(255, 204, 0));     // 更浅橙（亮度+30）

        CATEGORY_COLORS.put("数字营销", new Color(156, 39, 176));    // 主色
        CATEGORY_COLORS.put("品牌管理", new Color(123, 31, 162));    // 深紫（亮度-20）
        CATEGORY_COLORS.put("公共关系", new Color(177, 70, 194));    // 浅紫（亮度+10）
        CATEGORY_COLORS.put("社交媒体", new Color(156, 39, 176));    // 继承主色
        CATEGORY_COLORS.put("SEO优化", new Color(142, 36, 166));     // 微调深紫（亮度-10）
        CATEGORY_COLORS.put("邮件营销", new Color(194, 87, 212));    // 微调浅紫（亮度+20）
    }

    public static TreemapNode createTestData() {
        TreemapNode root = new TreemapNode("公司业务", 1000);
        TreemapNode tech = new TreemapNode("技术部", 350);
        TreemapNode finance = new TreemapNode("财务部", 280);
        TreemapNode sales = new TreemapNode("销售部", 220);
        TreemapNode hr = new TreemapNode("人力资源", 100);
        TreemapNode marketing = new TreemapNode("市场营销", 50);
        root.addChild(tech);
        root.addChild(finance);
        root.addChild(sales);
        root.addChild(hr);
        root.addChild(marketing);

        TreemapNode software = new TreemapNode("软件开发", 150);
        TreemapNode infrastructure = new TreemapNode("基础设施", 100);
        TreemapNode dataScience = new TreemapNode("数据科学", 60);
        TreemapNode qa = new TreemapNode("质量保证", 40);
        tech.addChild(software);
        tech.addChild(infrastructure);
        tech.addChild(dataScience);
        tech.addChild(qa);

        TreemapNode frontend = new TreemapNode("前端开发", 70);
        TreemapNode backend = new TreemapNode("后端开发", 60);
        TreemapNode mobile = new TreemapNode("移动开发", 20);
        software.addChild(frontend);
        software.addChild(backend);
        software.addChild(mobile);

        TreemapNode react = new TreemapNode("React项目", 30);
        TreemapNode vue = new TreemapNode("Vue项目", 25);
        TreemapNode angular = new TreemapNode("Angular项目", 15);
        frontend.addChild(react);
        frontend.addChild(vue);
        frontend.addChild(angular);

        TreemapNode java = new TreemapNode("Java服务", 30);
        TreemapNode python = new TreemapNode("Python服务", 20);
        TreemapNode nodejs = new TreemapNode("Node.js服务", 10);
        backend.addChild(java);
        backend.addChild(python);
        backend.addChild(nodejs);

        TreemapNode accounting = new TreemapNode("会计核算", 120);
        TreemapNode budgeting = new TreemapNode("预算管理", 80);
        TreemapNode tax = new TreemapNode("税务管理", 50);
        TreemapNode audit = new TreemapNode("内部审计", 30);
        finance.addChild(accounting);
        finance.addChild(budgeting);
        finance.addChild(tax);
        finance.addChild(audit);

        TreemapNode ar = new TreemapNode("应收账款", 50);
        TreemapNode ap = new TreemapNode("应付账款", 40);
        TreemapNode gl = new TreemapNode("总账管理", 30);
        accounting.addChild(ar);
        accounting.addChild(ap);
        accounting.addChild(gl);

        TreemapNode domestic = new TreemapNode("国内销售", 120);
        TreemapNode international = new TreemapNode("国际销售", 70);
        TreemapNode online = new TreemapNode("线上销售", 30);
        sales.addChild(domestic);
        sales.addChild(international);
        sales.addChild(online);

        TreemapNode north = new TreemapNode("华北区域", 50);
        TreemapNode south = new TreemapNode("华南区域", 40);
        TreemapNode east = new TreemapNode("华东区域", 30);
        domestic.addChild(north);
        domestic.addChild(south);
        domestic.addChild(east);

        TreemapNode recruitment = new TreemapNode("招聘", 40);
        TreemapNode training = new TreemapNode("培训发展", 30);
        TreemapNode compensation = new TreemapNode("薪酬福利", 20);
        TreemapNode employee = new TreemapNode("员工关系", 10);
        hr.addChild(recruitment);
        hr.addChild(training);
        hr.addChild(compensation);
        hr.addChild(employee);

        // 市场营销子类（逻辑不变）
        TreemapNode digital = new TreemapNode("数字营销", 25);
        TreemapNode brand = new TreemapNode("品牌管理", 15);
        TreemapNode pr = new TreemapNode("公共关系", 10);
        marketing.addChild(digital);
        marketing.addChild(brand);
        marketing.addChild(pr);

        TreemapNode social = new TreemapNode("社交媒体", 12);
        TreemapNode seo = new TreemapNode("SEO优化", 8);
        TreemapNode email = new TreemapNode("邮件营销", 5);
        digital.addChild(social);
        digital.addChild(seo);
        digital.addChild(email);

        return root;
    }

    public static void calculateLayout(List<TreemapNode> nodes, double x, double y, double width, double height, boolean horizontal) {
        if (nodes.isEmpty()) return;
        double total = nodes.stream().mapToDouble(n -> n.getValue()).sum();
        if (total == 0) return;

        if (horizontal) {
            double currentX = x;
            for (TreemapNode node : nodes) {
                double nodeWidth = width * (node.getValue() / total);
                if (nodeWidth < 1) nodeWidth = 1;
                node.setRect(new Rectangle2D.Double(currentX, y, nodeWidth, height));
                currentX += nodeWidth;
                if (node.hasChildren()) {
                    calculateLayout(node.getChildren(), currentX - nodeWidth, y, nodeWidth, height, !horizontal);
                }
            }
        } else {
            double currentY = y;
            for (TreemapNode node : nodes) {
                double nodeHeight = height * (node.getValue() / total);
                if (nodeHeight < 1) nodeHeight = 1;
                node.setRect(new Rectangle2D.Double(x, currentY, width, nodeHeight));
                currentY += nodeHeight;
                if (node.hasChildren()) {
                    calculateLayout(node.getChildren(), x, currentY - nodeHeight, width, nodeHeight, !horizontal);
                }
            }
        }
    }

    public static void generateSVG(TreemapNode root, String filename) throws Exception {
        Document document = SVGDOMImplementation.getDOMImplementation().createDocument(
                SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        svgGenerator.setColor(new Color(250, 250, 250));
        svgGenerator.fillRect(0, 0, 1200, 800);

        svgGenerator.setColor(new Color(33, 33, 33));
        svgGenerator.setFont(new Font("Microsoft YaHei", Font.BOLD, 24));
        svgGenerator.drawString("公司业务分布矩形树图（ECharts风格）", 420, 30);
        drawTreemap(svgGenerator, root);
        addLegend(svgGenerator);
        try (Writer out = new FileWriter(filename)) {
            svgGenerator.stream(out, true);
        }
    }

    private static void drawTreemap(SVGGraphics2D svgGenerator, TreemapNode node) {
        if (node.getRect() != null) {
            Color color = getColorForCategory(node.getName());
            svgGenerator.setColor(color);
            svgGenerator.fill(node.getRect());

            svgGenerator.setColor(new Color(220, 220, 220));
            svgGenerator.setStroke(new BasicStroke(0.5f));
            svgGenerator.draw(node.getRect());

            Rectangle2D rect = node.getRect();
            if (rect.getWidth() > 50 && rect.getHeight() > 25) {
                Color textColor = getContrastColor(color);
                svgGenerator.setColor(textColor);
                svgGenerator.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
                String label = node.getName();

                if (rect.getWidth() > 80 && rect.getHeight() > 35) {
                    label += " (" + node.getValue() + ")";
                }
                double centerX = rect.getX() + rect.getWidth() / 2;
                double centerY = rect.getY() + rect.getHeight() / 2;
                int textWidth = label.length() * 6;
                if (textWidth < rect.getWidth() - 10) {
                    svgGenerator.drawString(label, (float)(centerX - textWidth / 2), (float)(centerY + 4));
                } else if (rect.getWidth() > 30 && label.length() > 8) {
                    label = label.substring(0, 6) + "...";
                    textWidth = label.length() * 6;
                    if (textWidth < rect.getWidth() - 5) {
                        svgGenerator.drawString(label, (float)(centerX - textWidth / 2), (float)(centerY + 4));
                    }
                }
            }
        }
        for (TreemapNode child : node.getChildren()) {
            drawTreemap(svgGenerator, child);
        }
    }

    private static Color getColorForCategory(String categoryName) {
        Color color = CATEGORY_COLORS.get(categoryName);
        if (color != null) return color;
        color = DEPARTMENT_COLORS.get(categoryName);
        if (color != null) return color;
        return generateSimilarColor(categoryName);
    }

    private static Color generateSimilarColor(String categoryName) {
        String department = inferDepartment(categoryName);
        if (department != null && DEPARTMENT_COLORS.containsKey(department)) {
            Color baseColor = DEPARTMENT_COLORS.get(department);
            return adjustColorBrightness(baseColor, categoryName.hashCode() % 40 - 20);
        }
        return adjustColorBrightness(DEPARTMENT_COLORS.get("技术部"), categoryName.hashCode() % 30 - 15);
    }

    private static String inferDepartment(String categoryName) {
        if (categoryName.contains("开发") || categoryName.contains("项目") || categoryName.contains("服务")) {
            return "技术部";
        } else if (categoryName.contains("会计") || categoryName.contains("预算") || categoryName.contains("税务") || categoryName.contains("审计")) {
            return "财务部";
        } else if (categoryName.contains("销售") || categoryName.contains("区域")) {
            return "销售部";
        } else if (categoryName.contains("招聘") || categoryName.contains("培训") || categoryName.contains("薪酬") || categoryName.contains("员工")) {
            return "人力资源";
        } else if (categoryName.contains("营销") || categoryName.contains("品牌") || categoryName.contains("公关")) {
            return "市场营销";
        }
        return null;
    }

    private static Color adjustColorBrightness(Color color, int delta) {
        int r = Math.max(0, Math.min(255, color.getRed() + delta));
        int g = Math.max(0, Math.min(255, color.getGreen() + delta));
        int b = Math.max(0, Math.min(255, color.getBlue() + delta));
        return new Color(r, g, b);
    }

    private static Color getContrastColor(Color backgroundColor) {
        double brightness = (backgroundColor.getRed() * 299 +
                backgroundColor.getGreen() * 587 +
                backgroundColor.getBlue() * 114) / 1000.0;
        return brightness > 130 ? Color.BLACK : Color.WHITE;
    }

    private static void addLegend(SVGGraphics2D svgGenerator) {
        int startX = 50;
        int startY = 730;
        int boxSize = 15;
        int spacing = 20;
        svgGenerator.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        String[] departments = {"技术部", "财务部", "销售部", "人力资源", "市场营销"};
        for (int i = 0; i < departments.length; i++) {
            Color color = DEPARTMENT_COLORS.get(departments[i]);
            svgGenerator.setColor(color);
            svgGenerator.fillRect(startX, startY, boxSize, boxSize);
            svgGenerator.setColor(Color.BLACK);
            svgGenerator.drawRect(startX, startY, boxSize, boxSize);
            svgGenerator.drawString(departments[i], startX + boxSize + 5, startY + 12);
            startX += 120;
        }

        svgGenerator.setColor(new Color(80, 80, 80));
        svgGenerator.drawString("注：同色系矩形为同一部门子分类（ECharts高对比风格）", 50, 760);
    }

    public static void main(String[] args) {
        try {
            TreemapNode root = createTestData();
            calculateLayout(root.getChildren(), 50, 50, 1100, 700, true);
            generateSVG(root, "d://test//treemap_echarts_style.svg");
            System.out.println("ECharts风格矩形树图生成成功！");
            System.out.println("文件保存位置: d://test//treemap_echarts_style.svg");
            System.out.println("颜色说明（高对比度主色）：");
            System.out.println("  技术部 - 鲜明蓝 | 财务部 - 鲜艳红 | 销售部 - 明亮绿");
            System.out.println("  人力资源 - 橙色 | 市场营销 - 深紫");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("生成失败：请检查目录是否存在，或依赖包是否完整（Batik+TreemapNode）");
        }
    }
}