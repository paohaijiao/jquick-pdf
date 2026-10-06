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
package com.github.paohaijiao.demo.biz;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.treemap.JTreeMapNode;
import com.github.paohaijiao.treemap.TreeMapMapping;
import com.github.paohaijiao.treemap.TreeMapOption;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务场景 Demo 68：集团各板块营收占比矩形树图（战略规划）
 *
 * <p>业务场景：战略规划部门以矩形树图展示集团各业务板块及子业务线的营收占比，
 * 矩形面积代表营收规模，颜色区分不同板块。</p>
 * <p>实际图表类型：{@link JChartType#TREEMAP} 矩形树图。</p>
 *
 * <p>模板：{@code report/biz/68_treemap.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\68_treemap.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt 的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz68TreeMapTest {

    public static final String path = JQuickConstant.path;

    private static final Map<String, Color> DEPARTMENT_COLORS = new HashMap<>();
    private static final Map<String, Color> CATEGORY_COLORS = new HashMap<>();

    static {
        DEPARTMENT_COLORS.put("主营业务", new Color(26, 35, 126));
        DEPARTMENT_COLORS.put("创新业务", new Color(40, 53, 147));
        DEPARTMENT_COLORS.put("支撑服务", new Color(57, 73, 171));
        DEPARTMENT_COLORS.put("其他业务", new Color(92, 107, 192));

        CATEGORY_COLORS.put("软件产品", new Color(26, 35, 126));
        CATEGORY_COLORS.put("硬件产品", new Color(30, 45, 140));
        CATEGORY_COLORS.put("解决方案", new Color(35, 55, 155));
        CATEGORY_COLORS.put("云服务", new Color(40, 53, 147));
        CATEGORY_COLORS.put("AI 应用", new Color(45, 62, 160));
        CATEGORY_COLORS.put("数据服务", new Color(50, 70, 175));
        CATEGORY_COLORS.put("技术服务", new Color(57, 73, 171));
        CATEGORY_COLORS.put("运维服务", new Color(65, 82, 180));
        CATEGORY_COLORS.put("投资收益", new Color(92, 107, 192));
        CATEGORY_COLORS.put("租金物业", new Color(110, 125, 200));
    }

    @Test
    public void biz68TreeMap() throws IOException {
        JTreeMapNode root = new JTreeMapNode("集团总营收", 10000);

        // 主营业务板块
        JTreeMapNode main = new JTreeMapNode("主营业务", 5500);
        main.addChild(new JTreeMapNode("软件产品", 2500));
        main.addChild(new JTreeMapNode("硬件产品", 1800));
        main.addChild(new JTreeMapNode("解决方案", 1200));

        // 创新业务板块
        JTreeMapNode innovation = new JTreeMapNode("创新业务", 2200);
        innovation.addChild(new JTreeMapNode("云服务", 1000));
        innovation.addChild(new JTreeMapNode("AI 应用", 700));
        innovation.addChild(new JTreeMapNode("数据服务", 500));

        // 支撑服务板块
        JTreeMapNode support = new JTreeMapNode("支撑服务", 1800);
        support.addChild(new JTreeMapNode("技术服务", 1000));
        support.addChild(new JTreeMapNode("运维服务", 800));

        // 其他业务板块
        JTreeMapNode other = new JTreeMapNode("其他业务", 500);
        other.addChild(new JTreeMapNode("投资收益", 300));
        other.addChild(new JTreeMapNode("租金物业", 200));

        root.addChild(main);
        root.addChild(innovation);
        root.addChild(support);
        root.addChild(other);

        TreeMapOption treemapOption = new TreeMapOption();
        treemapOption.setRoot(root);
        treemapOption.setDepartmentColors(DEPARTMENT_COLORS);
        treemapOption.setCategoryColors(CATEGORY_COLORS);
        // 部门匹配规则：按子节点名称关键字匹配到所属板块
        treemapOption.getDepartmentRules().add(new TreeMapMapping("软件", "主营业务"));
        treemapOption.getDepartmentRules().add(new TreeMapMapping("硬件", "主营业务"));
        treemapOption.getDepartmentRules().add(new TreeMapMapping("解决方案", "主营业务"));
        treemapOption.getDepartmentRules().add(new TreeMapMapping("云服务", "创新业务"));
        treemapOption.getDepartmentRules().add(new TreeMapMapping("AI", "创新业务"));
        treemapOption.getDepartmentRules().add(new TreeMapMapping("数据", "创新业务"));
        treemapOption.getDepartmentRules().add(new TreeMapMapping("技术", "支撑服务"));
        treemapOption.getDepartmentRules().add(new TreeMapMapping("运维", "支撑服务"));
        treemapOption.getDepartmentRules().add(new TreeMapMapping("投资", "其他业务"));
        treemapOption.getDepartmentRules().add(new TreeMapMapping("租金", "其他业务"));

        JOption option = new JOption();
        option.setTreemapOption(treemapOption);
        option.title("集团各板块营收占比矩形树图", "面积代表营收规模（单位：万元）");

        String svg = JChartRendererFactory.renderChart(JChartType.TREEMAP, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/68_treemap.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/68_treemap.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
