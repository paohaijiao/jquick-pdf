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
package com.github.paohaijiao.biz.chart;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.treemap.JTreeMapNode;
import com.github.paohaijiao.treemap.TreeMapOption;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 业务场景 Demo 37：项目状态分布（政府投资项目监管平台）
 *
 * <p>业务场景：项目监管平台按"在建 / 已完工 / 前期 / 暂停"四大状态对政府投资项目分组，
 * 以矩形面积表示项目投资额，用于把握投资进度与资金沉淀情况。</p>
 * <p>实际图表类型：{@link JChartType#TREEMAP} 矩形树图（矩形面积正比于投资额，squarify 交错布局，相近色系为一组状态）。</p>
 *
 * <p>模板：{@code report/biz/37_project_status_treemap.xml}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\37_project_status_treemap.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 420pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz37ProjectStatusTreemapTest {

    public static final String path = JQuickConstant.path;

    private static final Map<String, Color> DEPARTMENT_COLORS = new LinkedHashMap<>();
    private static final Map<String, Color> CATEGORY_COLORS = new LinkedHashMap<>();

    static {
        // 顶层状态分组颜色：在建（蓝）/ 已完工（绿）/ 前期（橙）/ 暂停（灰蓝）
        DEPARTMENT_COLORS.put("在建",   new Color(33, 150, 243));
        DEPARTMENT_COLORS.put("已完工", new Color(76, 175, 80));
        DEPARTMENT_COLORS.put("前期",   new Color(255, 152, 0));
        DEPARTMENT_COLORS.put("暂停",   new Color(96, 125, 139));

        // 在建：蓝-青色系，四个分类由浅蓝过渡到青绿
        CATEGORY_COLORS.put("市政工程", new Color(100, 181, 246));
        CATEGORY_COLORS.put("交通工程", new Color(33, 150, 243));
        CATEGORY_COLORS.put("轨道交通", new Color(13, 100, 196));
        CATEGORY_COLORS.put("水务工程", new Color(0, 172, 193));

        // 已完工：绿色系，绿色过渡到黄绿
        CATEGORY_COLORS.put("房建工程", new Color(102, 187, 106));
        CATEGORY_COLORS.put("水利工程", new Color(56, 142, 60));
        CATEGORY_COLORS.put("市政配套", new Color(139, 195, 74));
        CATEGORY_COLORS.put("园林绿化", new Color(174, 213, 92));

        // 前期：橙黄色系，橙红过渡到亮黄
        CATEGORY_COLORS.put("规划设计", new Color(255, 167, 38));
        CATEGORY_COLORS.put("征地拆迁", new Color(230, 126, 0));
        CATEGORY_COLORS.put("可研审批", new Color(255, 202, 40));
        CATEGORY_COLORS.put("环评公示", new Color(251, 140, 0));

        // 暂停：灰蓝色系，由浅灰蓝过渡到深灰蓝
        CATEGORY_COLORS.put("资金待落实", new Color(144, 164, 174));
        CATEGORY_COLORS.put("手续待补办", new Color(120, 144, 156));
        CATEGORY_COLORS.put("方案调整",   new Color(84, 110, 122));
        CATEGORY_COLORS.put("征地争议",   new Color(55, 71, 79));
    }

    @Test
    public void biz37ProjectStatusTreemap() throws IOException {
        // 根节点 value 为四大状态投资额之和（单位：亿元），子节点面积即代表各自投资额
        JTreeMapNode root = new JTreeMapNode("项目总览", 1000);

        JTreeMapNode building = new JTreeMapNode("在建", 420);
        building.addChild(new JTreeMapNode("市政工程", 170));
        building.addChild(new JTreeMapNode("交通工程", 120));
        building.addChild(new JTreeMapNode("轨道交通", 80));
        building.addChild(new JTreeMapNode("水务工程", 50));
        root.addChild(building);

        JTreeMapNode completed = new JTreeMapNode("已完工", 300);
        completed.addChild(new JTreeMapNode("房建工程", 130));
        completed.addChild(new JTreeMapNode("水利工程", 80));
        completed.addChild(new JTreeMapNode("市政配套", 55));
        completed.addChild(new JTreeMapNode("园林绿化", 35));
        root.addChild(completed);

        JTreeMapNode preparatory = new JTreeMapNode("前期", 180);
        preparatory.addChild(new JTreeMapNode("规划设计", 70));
        preparatory.addChild(new JTreeMapNode("征地拆迁", 55));
        preparatory.addChild(new JTreeMapNode("可研审批", 35));
        preparatory.addChild(new JTreeMapNode("环评公示", 20));
        root.addChild(preparatory);

        JTreeMapNode suspended = new JTreeMapNode("暂停", 100);
        suspended.addChild(new JTreeMapNode("资金待落实", 40));
        suspended.addChild(new JTreeMapNode("手续待补办", 30));
        suspended.addChild(new JTreeMapNode("方案调整", 18));
        suspended.addChild(new JTreeMapNode("征地争议", 12));
        root.addChild(suspended);

        TreeMapOption treemapOption = new TreeMapOption();
        treemapOption.setRoot(root);
        treemapOption.setDepartmentColors(DEPARTMENT_COLORS);
        treemapOption.setCategoryColors(CATEGORY_COLORS);
        // 每个节点（含根节点）的颜色均可在两张颜色表中按名称精确命中，
        // 不再依赖部门关键词推断规则，避免出现随机灰调的兜底色
        treemapOption.setLegendNote("注：相近色系矩形为同一状态下的项目分类");
        JOption option = new JOption();
        option.setTreemapOption(treemapOption);
        option.title("政府投资项目状态分布");
        String svg = JChartRendererFactory.renderChart(JChartType.TREEMAP, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/37_project_status_treemap.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/37_project_status_treemap.xml");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
