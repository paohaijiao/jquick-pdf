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


import com.github.paohaijiao.JOption;
import com.github.paohaijiao.treemap.JTreeMapNode;
import com.github.paohaijiao.treemap.JTreeMapRenderer;
import com.github.paohaijiao.treemap.TreeMapMapping;
import com.github.paohaijiao.treemap.TreeMapOption;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class JTreemapRendererExample {

    private static final Map<String, Color> DEPARTMENT_COLORS = new HashMap<>();
    private static final Map<String, Color> CATEGORY_COLORS = new HashMap<>();

    static {
        DEPARTMENT_COLORS.put("技术部", new Color(33, 150, 243));
        DEPARTMENT_COLORS.put("财务部", new Color(244, 67, 54));
        DEPARTMENT_COLORS.put("销售部", new Color(76, 175, 80));
        DEPARTMENT_COLORS.put("人力资源", new Color(255, 152, 0));
        DEPARTMENT_COLORS.put("市场营销", new Color(156, 39, 176));
        CATEGORY_COLORS.put("软件开发", new Color(33, 150, 243));
        CATEGORY_COLORS.put("基础设施", new Color(21, 101, 192));
        CATEGORY_COLORS.put("数据科学", new Color(66, 165, 245));
        CATEGORY_COLORS.put("质量保证", new Color(100, 181, 246));
        CATEGORY_COLORS.put("前端开发", new Color(33, 150, 243));
        CATEGORY_COLORS.put("后端开发", new Color(21, 101, 192));
        CATEGORY_COLORS.put("移动开发", new Color(66, 165, 245));
        CATEGORY_COLORS.put("React项目", new Color(33, 150, 243));
        CATEGORY_COLORS.put("Vue项目", new Color(48, 164, 255));
        CATEGORY_COLORS.put("Angular项目", new Color(144, 202, 249));
        CATEGORY_COLORS.put("Java服务", new Color(21, 101, 192));
        CATEGORY_COLORS.put("Python服务", new Color(13, 71, 161));
        CATEGORY_COLORS.put("Node.js服务", new Color(66, 165, 245));
        CATEGORY_COLORS.put("会计核算", new Color(244, 67, 54));
        CATEGORY_COLORS.put("预算管理", new Color(211, 47, 47));
        CATEGORY_COLORS.put("税务管理", new Color(255, 87, 34));
        CATEGORY_COLORS.put("内部审计", new Color(255, 112, 67));
        CATEGORY_COLORS.put("应收账款", new Color(244, 67, 54));
        CATEGORY_COLORS.put("应付账款", new Color(229, 57, 53));
        CATEGORY_COLORS.put("总账管理", new Color(255, 105, 97));
        CATEGORY_COLORS.put("国内销售", new Color(76, 175, 80));
        CATEGORY_COLORS.put("国际销售", new Color(56, 142, 60));
        CATEGORY_COLORS.put("线上销售", new Color(102, 189, 106));
        CATEGORY_COLORS.put("华北区域", new Color(76, 175, 80));
        CATEGORY_COLORS.put("华南区域", new Color(67, 160, 71));
        CATEGORY_COLORS.put("华东区域", new Color(129, 199, 132));
        CATEGORY_COLORS.put("招聘", new Color(255, 152, 0));
        CATEGORY_COLORS.put("培训发展", new Color(245, 124, 0));
        CATEGORY_COLORS.put("薪酬福利", new Color(255, 179, 0));
        CATEGORY_COLORS.put("员工关系", new Color(255, 204, 0));
        CATEGORY_COLORS.put("数字营销", new Color(156, 39, 176));
        CATEGORY_COLORS.put("品牌管理", new Color(123, 31, 162));
        CATEGORY_COLORS.put("公共关系", new Color(177, 70, 194));
        CATEGORY_COLORS.put("社交媒体", new Color(156, 39, 176));
        CATEGORY_COLORS.put("SEO优化", new Color(142, 36, 166));
        CATEGORY_COLORS.put("邮件营销", new Color(194, 87, 212));
    }

    public static JTreeMapNode createTestData() {
        JTreeMapNode root = new JTreeMapNode("公司业务", 1000);
        JTreeMapNode tech = new JTreeMapNode("技术部", 350);
        JTreeMapNode software = new JTreeMapNode("软件开发", 150);
        JTreeMapNode infrastructure = new JTreeMapNode("基础设施", 100);
        JTreeMapNode dataScience = new JTreeMapNode("数据科学", 60);
        JTreeMapNode qa = new JTreeMapNode("质量保证", 40);
        JTreeMapNode frontend = new JTreeMapNode("前端开发", 70);
        JTreeMapNode backend = new JTreeMapNode("后端开发", 60);
        JTreeMapNode mobile = new JTreeMapNode("移动开发", 20);
        JTreeMapNode react = new JTreeMapNode("React项目", 30);
        JTreeMapNode vue = new JTreeMapNode("Vue项目", 25);
        JTreeMapNode angular = new JTreeMapNode("Angular项目", 15);
        JTreeMapNode java = new JTreeMapNode("Java服务", 30);
        JTreeMapNode python = new JTreeMapNode("Python服务", 20);
        JTreeMapNode nodejs = new JTreeMapNode("Node.js服务", 10);
        frontend.addChild(react);
        frontend.addChild(vue);
        frontend.addChild(angular);
        backend.addChild(java);
        backend.addChild(python);
        backend.addChild(nodejs);
        software.addChild(frontend);
        software.addChild(backend);
        software.addChild(mobile);
        tech.addChild(software);
        tech.addChild(infrastructure);
        tech.addChild(dataScience);
        tech.addChild(qa);
        JTreeMapNode finance = new JTreeMapNode("财务部", 280);
        JTreeMapNode accounting = new JTreeMapNode("会计核算", 120);
        JTreeMapNode budgeting = new JTreeMapNode("预算管理", 80);
        JTreeMapNode tax = new JTreeMapNode("税务管理", 50);
        JTreeMapNode audit = new JTreeMapNode("内部审计", 30);

        JTreeMapNode ar = new JTreeMapNode("应收账款", 50);
        JTreeMapNode ap = new JTreeMapNode("应付账款", 40);
        JTreeMapNode gl = new JTreeMapNode("总账管理", 30);

        accounting.addChild(ar);
        accounting.addChild(ap);
        accounting.addChild(gl);

        finance.addChild(accounting);
        finance.addChild(budgeting);
        finance.addChild(tax);
        finance.addChild(audit);

        JTreeMapNode sales = new JTreeMapNode("销售部", 220);
        JTreeMapNode domestic = new JTreeMapNode("国内销售", 120);
        JTreeMapNode international = new JTreeMapNode("国际销售", 70);
        JTreeMapNode online = new JTreeMapNode("线上销售", 30);

        JTreeMapNode north = new JTreeMapNode("华北区域", 50);
        JTreeMapNode south = new JTreeMapNode("华南区域", 40);
        JTreeMapNode east = new JTreeMapNode("华东区域", 30);

        domestic.addChild(north);
        domestic.addChild(south);
        domestic.addChild(east);

        sales.addChild(domestic);
        sales.addChild(international);
        sales.addChild(online);

        // 人力资源
        JTreeMapNode hr = new JTreeMapNode("人力资源", 100);
        JTreeMapNode recruitment = new JTreeMapNode("招聘", 40);
        JTreeMapNode training = new JTreeMapNode("培训发展", 30);
        JTreeMapNode compensation = new JTreeMapNode("薪酬福利", 20);
        JTreeMapNode employee = new JTreeMapNode("员工关系", 10);

        hr.addChild(recruitment);
        hr.addChild(training);
        hr.addChild(compensation);
        hr.addChild(employee);

        // 市场营销
        JTreeMapNode marketing = new JTreeMapNode("市场营销", 50);
        JTreeMapNode digital = new JTreeMapNode("数字营销", 25);
        JTreeMapNode brand = new JTreeMapNode("品牌管理", 15);
        JTreeMapNode pr = new JTreeMapNode("公共关系", 10);

        JTreeMapNode social = new JTreeMapNode("社交媒体", 12);
        JTreeMapNode seo = new JTreeMapNode("SEO优化", 8);
        JTreeMapNode email = new JTreeMapNode("邮件营销", 5);

        digital.addChild(social);
        digital.addChild(seo);
        digital.addChild(email);

        marketing.addChild(digital);
        marketing.addChild(brand);
        marketing.addChild(pr);

        // 添加到根节点
        root.addChild(tech);
        root.addChild(finance);
        root.addChild(sales);
        root.addChild(hr);
        root.addChild(marketing);

        return root;
    }

    public static void main(String[] args) {
        try {
            JTreeMapNode root = createTestData();
            TreeMapOption treemapOption = new TreeMapOption();
            treemapOption.setRoot(root);
            treemapOption.setDepartmentColors(DEPARTMENT_COLORS);
            treemapOption.setCategoryColors(CATEGORY_COLORS);
            treemapOption.getDepartmentRules().add(new TreeMapMapping("开发", "技术部"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("项目", "技术部"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("服务", "技术部"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("会计", "财务部"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("预算", "财务部"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("税务", "财务部"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("审计", "财务部"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("销售", "销售部"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("区域", "销售部"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("招聘", "人力资源"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("培训", "人力资源"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("薪酬", "人力资源"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("员工", "人力资源"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("营销", "市场营销"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("品牌", "市场营销"));
            treemapOption.getDepartmentRules().add(new TreeMapMapping("公关", "市场营销"));
            JOption option = new JOption();
            option.setTreemapOption(treemapOption);
            option.title("公司业务分布矩形树图（JTreemapRenderer）");
            JTreeMapRenderer renderer = new JTreeMapRenderer();
            String outputPath = "d://test//treemap.svg";
            renderer.render(option, outputPath);
            System.out.println("JTreemapRenderer 树形图生成成功！");
            System.out.println("文件保存位置: " + outputPath);
            System.out.println("使用配置：");
            System.out.println("  - 部门颜色映射: " + DEPARTMENT_COLORS.size() + " 个部门");
            System.out.println("  - 分类颜色映射: " + CATEGORY_COLORS.size() + " 个分类");
            System.out.println("  - 部门规则: " + treemapOption.getDepartmentRules().size() + " 条规则");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("生成失败：请检查目录是否存在，或依赖包是否完整");
        }
    }
}

