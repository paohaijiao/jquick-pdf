package com.github.paohaijiao.treemap;
import com.github.paohaijiao.model.TreemapNode;
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
        DEPARTMENT_COLORS.put("技术部", new Color(102, 194, 165));   // 蓝绿色
        DEPARTMENT_COLORS.put("财务部", new Color(252, 141, 98));    // 橙红色
        DEPARTMENT_COLORS.put("销售部", new Color(141, 160, 203));   // 淡紫色
        DEPARTMENT_COLORS.put("人力资源", new Color(231, 138, 195));  // 粉红色
        DEPARTMENT_COLORS.put("市场营销", new Color(166, 216, 84));   // 亮绿色

        // 技术部子分类颜色（蓝绿色系）
        CATEGORY_COLORS.put("软件开发", new Color(102, 194, 165));     // 主色
        CATEGORY_COLORS.put("基础设施", new Color(77, 175, 148));      // 深一点
        CATEGORY_COLORS.put("数据科学", new Color(127, 213, 182));     // 浅一点
        CATEGORY_COLORS.put("质量保证", new Color(153, 216, 201));     // 更浅

        CATEGORY_COLORS.put("前端开发", new Color(102, 194, 165));     // 继承父类
        CATEGORY_COLORS.put("后端开发", new Color(77, 175, 148));      // 深一点
        CATEGORY_COLORS.put("移动开发", new Color(127, 213, 182));     // 浅一点

        CATEGORY_COLORS.put("React项目", new Color(102, 194, 165));   // 继承
        CATEGORY_COLORS.put("Vue项目", new Color(87, 184, 157));      // 微调
        CATEGORY_COLORS.put("Angular项目", new Color(117, 204, 173)); // 微调

        CATEGORY_COLORS.put("Java服务", new Color(77, 175, 148));     // 继承
        CATEGORY_COLORS.put("Python服务", new Color(67, 165, 138));   // 更深
        CATEGORY_COLORS.put("Node.js服务", new Color(92, 185, 158));  // 中间

        // 财务部子分类颜色（橙红色系）
        CATEGORY_COLORS.put("会计核算", new Color(252, 141, 98));      // 主色
        CATEGORY_COLORS.put("预算管理", new Color(242, 121, 78));      // 深一点
        CATEGORY_COLORS.put("税务管理", new Color(255, 161, 118));     // 浅一点
        CATEGORY_COLORS.put("内部审计", new Color(255, 181, 148));     // 更浅

        CATEGORY_COLORS.put("应收账款", new Color(252, 141, 98));      // 继承
        CATEGORY_COLORS.put("应付账款", new Color(237, 126, 83));      // 微调
        CATEGORY_COLORS.put("总账管理", new Color(247, 156, 113));     // 微调

        // 销售部子分类颜色（淡紫色系）
        CATEGORY_COLORS.put("国内销售", new Color(141, 160, 203));     // 主色
        CATEGORY_COLORS.put("国际销售", new Color(121, 140, 183));     // 深一点
        CATEGORY_COLORS.put("线上销售", new Color(161, 180, 223));     // 浅一点

        CATEGORY_COLORS.put("华北区域", new Color(141, 160, 203));     // 继承
        CATEGORY_COLORS.put("华南区域", new Color(131, 150, 193));     // 微调
        CATEGORY_COLORS.put("华东区域", new Color(151, 170, 213));     // 微调

        // 人力资源子分类颜色（粉红色系）
        CATEGORY_COLORS.put("招聘", new Color(231, 138, 195));         // 主色
        CATEGORY_COLORS.put("培训发展", new Color(221, 128, 185));     // 深一点
        CATEGORY_COLORS.put("薪酬福利", new Color(241, 148, 205));     // 浅一点
        CATEGORY_COLORS.put("员工关系", new Color(251, 168, 225));     // 更浅

        // 市场营销子分类颜色（亮绿色系）
        CATEGORY_COLORS.put("数字营销", new Color(166, 216, 84));      // 主色
        CATEGORY_COLORS.put("品牌管理", new Color(146, 196, 64));      // 深一点
        CATEGORY_COLORS.put("公共关系", new Color(186, 236, 104));     // 浅一点

        CATEGORY_COLORS.put("社交媒体", new Color(166, 216, 84));      // 继承
        CATEGORY_COLORS.put("SEO优化", new Color(156, 206, 74));       // 微调
        CATEGORY_COLORS.put("邮件营销", new Color(176, 226, 94));      // 微调
    }

    public static TreemapNode createTestData() {
        // 根节点
        TreemapNode root = new TreemapNode("公司业务", 1000);

        // 第一层级 - 主要业务部门
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

        // 技术部子类
        TreemapNode software = new TreemapNode("软件开发", 150);
        TreemapNode infrastructure = new TreemapNode("基础设施", 100);
        TreemapNode dataScience = new TreemapNode("数据科学", 60);
        TreemapNode qa = new TreemapNode("质量保证", 40);

        tech.addChild(software);
        tech.addChild(infrastructure);
        tech.addChild(dataScience);
        tech.addChild(qa);

        // 软件开发子类
        TreemapNode frontend = new TreemapNode("前端开发", 70);
        TreemapNode backend = new TreemapNode("后端开发", 60);
        TreemapNode mobile = new TreemapNode("移动开发", 20);

        software.addChild(frontend);
        software.addChild(backend);
        software.addChild(mobile);

        // 前端开发子类
        TreemapNode react = new TreemapNode("React项目", 30);
        TreemapNode vue = new TreemapNode("Vue项目", 25);
        TreemapNode angular = new TreemapNode("Angular项目", 15);

        frontend.addChild(react);
        frontend.addChild(vue);
        frontend.addChild(angular);

        // 后端开发子类
        TreemapNode java = new TreemapNode("Java服务", 30);
        TreemapNode python = new TreemapNode("Python服务", 20);
        TreemapNode nodejs = new TreemapNode("Node.js服务", 10);

        backend.addChild(java);
        backend.addChild(python);
        backend.addChild(nodejs);

        // 财务部子类
        TreemapNode accounting = new TreemapNode("会计核算", 120);
        TreemapNode budgeting = new TreemapNode("预算管理", 80);
        TreemapNode tax = new TreemapNode("税务管理", 50);
        TreemapNode audit = new TreemapNode("内部审计", 30);

        finance.addChild(accounting);
        finance.addChild(budgeting);
        finance.addChild(tax);
        finance.addChild(audit);

        // 会计核算子类
        TreemapNode ar = new TreemapNode("应收账款", 50);
        TreemapNode ap = new TreemapNode("应付账款", 40);
        TreemapNode gl = new TreemapNode("总账管理", 30);

        accounting.addChild(ar);
        accounting.addChild(ap);
        accounting.addChild(gl);

        // 销售部子类
        TreemapNode domestic = new TreemapNode("国内销售", 120);
        TreemapNode international = new TreemapNode("国际销售", 70);
        TreemapNode online = new TreemapNode("线上销售", 30);

        sales.addChild(domestic);
        sales.addChild(international);
        sales.addChild(online);

        // 国内销售子类
        TreemapNode north = new TreemapNode("华北区域", 50);
        TreemapNode south = new TreemapNode("华南区域", 40);
        TreemapNode east = new TreemapNode("华东区域", 30);

        domestic.addChild(north);
        domestic.addChild(south);
        domestic.addChild(east);

        // 人力资源子类
        TreemapNode recruitment = new TreemapNode("招聘", 40);
        TreemapNode training = new TreemapNode("培训发展", 30);
        TreemapNode compensation = new TreemapNode("薪酬福利", 20);
        TreemapNode employee = new TreemapNode("员工关系", 10);

        hr.addChild(recruitment);
        hr.addChild(training);
        hr.addChild(compensation);
        hr.addChild(employee);

        // 市场营销子类
        TreemapNode digital = new TreemapNode("数字营销", 25);
        TreemapNode brand = new TreemapNode("品牌管理", 15);
        TreemapNode pr = new TreemapNode("公共关系", 10);

        marketing.addChild(digital);
        marketing.addChild(brand);
        marketing.addChild(pr);

        // 数字营销子类
        TreemapNode social = new TreemapNode("社交媒体", 12);
        TreemapNode seo = new TreemapNode("SEO优化", 8);
        TreemapNode email = new TreemapNode("邮件营销", 5);

        digital.addChild(social);
        digital.addChild(seo);
        digital.addChild(email);

        return root;
    }

    // 计算矩形树图布局
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

    // 生成SVG
    public static void generateSVG(TreemapNode root, String filename) throws Exception {
        Document document = SVGDOMImplementation.getDOMImplementation().createDocument(
                SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null);

        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // 设置背景
        svgGenerator.setColor(new Color(245, 245, 245));
        svgGenerator.fillRect(0, 0, 1200, 800);

        // 添加标题
        svgGenerator.setColor(new Color(51, 51, 51));
        svgGenerator.setFont(new Font("Microsoft YaHei", Font.BOLD, 24));
        svgGenerator.drawString("公司业务分布矩形树图", 450, 30);

        // 绘制矩形树图
        drawTreemap(svgGenerator, root);

        // 添加图例
        addLegend(svgGenerator);

        // 输出SVG文件
        try (Writer out = new FileWriter(filename)) {
            svgGenerator.stream(out, true);
        }
    }

    // 递归绘制矩形
    private static void drawTreemap(SVGGraphics2D svgGenerator, TreemapNode node) {
        if (node.getRect() != null) {
            // 使用分类颜色映射
            Color color = getColorForCategory(node.getName());
            svgGenerator.setColor(color);
            svgGenerator.fill(node.getRect());

            // 绘制边框
            svgGenerator.setColor(new Color(200, 200, 200));
            svgGenerator.setStroke(new BasicStroke(0.8f));
            svgGenerator.draw(node.getRect());

            // 添加文本标签
            Rectangle2D rect = node.getRect();
            if (rect.getWidth() > 50 && rect.getHeight() > 25) {
                // 根据背景色亮度调整文字颜色
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
                } else if (rect.getWidth() > 30) {
                    if (label.length() > 8) {
                        label = label.substring(0, 6) + "...";
                        textWidth = label.length() * 6;
                        if (textWidth < rect.getWidth() - 5) {
                            svgGenerator.drawString(label, (float)(centerX - textWidth / 2), (float)(centerY + 4));
                        }
                    }
                }
            }
        }

        for (TreemapNode child : node.getChildren()) {
            drawTreemap(svgGenerator, child);
        }
    }

    // 根据分类名称获取颜色
    private static Color getColorForCategory(String categoryName) {
        // 首先检查是否有预定义的颜色
        Color color = CATEGORY_COLORS.get(categoryName);
        if (color != null) {
            return color;
        }

        // 如果没有预定义，检查是否是主要部门
        color = DEPARTMENT_COLORS.get(categoryName);
        if (color != null) {
            return color;
        }

        // 如果都没有，根据名称生成相似颜色（基于哈希）
        return generateSimilarColor(categoryName);
    }

    // 生成相似颜色（基于分类名称的哈希）
    private static Color generateSimilarColor(String categoryName) {
        // 尝试从名称中推断所属部门
        String department = inferDepartment(categoryName);
        if (department != null && DEPARTMENT_COLORS.containsKey(department)) {
            Color baseColor = DEPARTMENT_COLORS.get(department);
            return adjustColorBrightness(baseColor, categoryName.hashCode() % 40 - 20);
        }

        // 默认使用技术部的颜色
        return adjustColorBrightness(DEPARTMENT_COLORS.get("技术部"), categoryName.hashCode() % 30 - 15);
    }

    // 根据名称推断所属部门
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

    // 调整颜色亮度
    private static Color adjustColorBrightness(Color color, int delta) {
        int r = Math.max(0, Math.min(255, color.getRed() + delta));
        int g = Math.max(0, Math.min(255, color.getGreen() + delta));
        int b = Math.max(0, Math.min(255, color.getBlue() + delta));
        return new Color(r, g, b);
    }

    // 获取对比色（根据背景色亮度决定文字颜色）
    private static Color getContrastColor(Color backgroundColor) {
        // 计算亮度（YIQ公式）
        double brightness = (backgroundColor.getRed() * 299 +
                backgroundColor.getGreen() * 587 +
                backgroundColor.getBlue() * 114) / 1000.0;
        return brightness > 128 ? Color.BLACK : Color.WHITE;
    }

    // 添加图例
    private static void addLegend(SVGGraphics2D svgGenerator) {
        int startX = 50;
        int startY = 730;
        int boxSize = 15;
        int spacing = 20;

        svgGenerator.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

        // 主要部门图例
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

        // 图例说明
        svgGenerator.setColor(new Color(100, 100, 100));
        svgGenerator.drawString("注：相同颜色的矩形属于同一业务分类", 50, 760);
    }

    public static void main(String[] args) {
        try {
            TreemapNode root = createTestData();
            calculateLayout(root.getChildren(), 50, 50, 1100, 700, true);
            generateSVG(root, "d://test//treemap_color_coded.svg");
            System.out.println("颜色编码的SVG矩形树图生成成功！");
            System.out.println("文件保存位置: d://test//treemap_color_coded.svg");
            System.out.println("颜色说明：");
            System.out.println("  技术部 - 蓝绿色系");
            System.out.println("  财务部 - 橙红色系");
            System.out.println("  销售部 - 淡紫色系");
            System.out.println("  人力资源 - 粉红色系");
            System.out.println("  市场营销 - 亮绿色系");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
