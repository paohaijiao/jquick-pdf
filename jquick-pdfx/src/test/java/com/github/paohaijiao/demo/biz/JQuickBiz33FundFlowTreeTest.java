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
import com.github.paohaijiao.combol.JTreeChartData;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务场景 Demo 33：资金流向树图表（集团资金管理）
 *
 * <p>业务场景：集团资金管理中心展示「集团总部 → 各业务板块 → 各项目公司」三级资金流向层级结构，
 * 用于投融资决策委员会分析资金沉淀与周转效率。</p>
 * <p>实际图表类型：{@link JChartType#Tree} 树图（层级水平布局，展示资金归集与下拨的父子层级）。</p>
 *
 * <p>模板：{@code report/biz/33_fund_flow_tree.txt}；树图以 SVG 文本形式绑定到模板的 {@code ${svg}} 占位符，
 * 输出到 {@code D:\test\biz\33_fund_flow_tree.pdf}。</p>
 *
 * <p>图表尺寸说明：树图渲染器画布固定 1000pt × 800pt（宽 × 高），节点总数控制在 12 个（1 个一级 + 3 个二级 + 8 个三级）
 * 以避免拥挤；模板中 {@code <svg>} 只声明宽度、不声明高度，宽度被页面自动收窄时高度按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz33FundFlowTreeTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz33FundFlowTree() throws IOException {
        JTreeChartData data = new JTreeChartData();
        data.setTitleText("集团资金流向结构图");
        data.setSubtitleText("集团总部 → 业务板块 → 项目公司 · 2025年上半年（单位：亿元）");
        data.setFooterText("数据来源：集团资金管理中心资金池台账 · 统计截止2025年6月30日");
        // 三级节点尺寸：一级为集团、二级为板块、三级为项目公司
        data.setRootNodeWidth(240);
        data.setRootNodeHeight(60);
        data.setLevelNodeWidth(190);
        data.setLevelNodeHeight(54);
        data.setLeafNodeWidth(110);
        data.setLeafNodeHeight(48);
        data.setHorizontalSpacing(70);
        data.setVerticalSpacing(60);
        List<JTreeChartData.LegendItem> legends = new ArrayList<>();
        legends.add(new JTreeChartData.LegendItem("一级节点（集团）", new Color(26, 82, 118)));
        legends.add(new JTreeChartData.LegendItem("二级节点（板块）", new Color(44, 127, 184)));
        legends.add(new JTreeChartData.LegendItem("三级节点（项目公司）", Color.WHITE));
        data.setLegendList(legends);

        JTreeChartData.TreeNode root = new JTreeChartData.TreeNode("root", "集团总部（资金池）", "Group Treasury Pool");
        root.setColor(new Color(0, 105, 92));
        // 二级节点一：基础设施建设板块
        JTreeChartData.TreeNode infra = new JTreeChartData.TreeNode("infra", "基础设施建设板块", "Infrastructure");
        infra.addChild(new JTreeChartData.TreeNode("road", "路桥项目公司", "Road & Bridge"));
        infra.addChild(new JTreeChartData.TreeNode("municipal", "市政工程项目公司", "Municipal Works"));
        infra.addChild(new JTreeChartData.TreeNode("rail", "轨道交通项目公司", "Rail Transit"));
        // 二级节点二：产业投资板块
        JTreeChartData.TreeNode industry = new JTreeChartData.TreeNode("industry", "产业投资板块", "Industry Investment");
        industry.addChild(new JTreeChartData.TreeNode("energy", "新能源项目公司", "New Energy"));
        industry.addChild(new JTreeChartData.TreeNode("manufacture", "智能制造项目公司", "Smart Manufacturing"));
        industry.addChild(new JTreeChartData.TreeNode("material", "新材料项目公司", "New Material"));
        // 二级节点三：金融服务板块
        JTreeChartData.TreeNode finance = new JTreeChartData.TreeNode("finance", "金融服务板块", "Financial Services");
        finance.addChild(new JTreeChartData.TreeNode("leasing", "融资租赁公司", "Financial Leasing"));
        finance.addChild(new JTreeChartData.TreeNode("supply", "供应链金融公司", "Supply Chain Finance"));

        root.addChild(infra);
        root.addChild(industry);
        root.addChild(finance);
        data.setRootNode(root);

        JOption option = new JOption();
        option.setData(data);
        String svg = JChartRendererFactory.renderChart(JChartType.Tree, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/33_fund_flow_tree.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/33_fund_flow_tree.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
