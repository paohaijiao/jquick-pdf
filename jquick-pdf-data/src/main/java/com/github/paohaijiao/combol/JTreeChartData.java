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
package com.github.paohaijiao.combol;

/**
 * packageName com.github.paohaijiao.combol
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/21
 */
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
 */

import com.github.paohaijiao.graph.JGraphData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构图数据模型
 */
@Data
public class JTreeChartData implements JGraphData {

    // 画布尺寸
    private int width = 900;

    private int height = 650;

    // 标题相关
    private String titleText;

    private String subtitleText;

    private String footerText;

    // 节点尺寸配置
    private int rootNodeWidth = 210;

    private int rootNodeHeight = 56;

    private int levelNodeWidth = 180;

    private int levelNodeHeight = 52;

    private int leafNodeWidth = 104;

    private int leafNodeHeight = 48;

    // 间距配置
    private int horizontalSpacing = 60;

    private int verticalSpacing = 50;

    // 颜色配置
    private Color backgroundColor = new Color(240, 244, 248);

    private Color textColor = new Color(30, 70, 104);

    // 根节点
    private TreeNode rootNode;

    // 图例列表
    private List<LegendItem> legendList;

    /**
     * 构建示例树结构（组织架构）
     */
    public static JTreeChartData buildSampleOrgChart() {
        JTreeChartData data = new JTreeChartData();
        data.setTitleText("集团组织架构图");
        data.setSubtitleText("Organizational Structure");
        data.setFooterText("组织架构树状图 · 商务版");

        // 构建图例
        List<LegendItem> legends = new ArrayList<>();
        legends.add(new LegendItem("一级节点", new Color(26, 82, 118)));
        legends.add(new LegendItem("二级节点", new Color(44, 127, 184)));
        legends.add(new LegendItem("三级节点", Color.WHITE));
        data.setLegendList(legends);

        // 构建根节点
        TreeNode root = new TreeNode("root", "集团总部 / 核心管理层", "Corporate Headquarters");
        root.setColor(new Color(26, 82, 118));

        // 二级节点：运营管理部
        TreeNode opsNode = new TreeNode("ops", "运营管理部", "Operations Mgmt");
        opsNode.addChild(new TreeNode("supply", "供应链组", "Supply Chain"));
        opsNode.addChild(new TreeNode("process", "流程优化组", "Process Opt."));
        opsNode.addChild(new TreeNode("quality", "质量管控组", "Quality Ctrl"));

        // 二级节点：战略发展中心
        TreeNode strategyNode = new TreeNode("strategy", "战略发展中心", "Strategy Dev");
        strategyNode.addChild(new TreeNode("investment", "投资分析组", "Investment"));
        strategyNode.addChild(new TreeNode("research", "市场研究组", "Market Res."));
        strategyNode.addChild(new TreeNode("planning", "战略规划组", "Strategy Plan"));

        // 二级节点：市场营销部
        TreeNode marketingNode = new TreeNode("marketing", "市场营销部", "Marketing");
        TreeNode brandNode = new TreeNode("brand", "品牌推广组", "Branding");
        brandNode.addChild(new TreeNode("pr", "公关", "PR"));
        brandNode.addChild(new TreeNode("event", "活动", "Event"));
        marketingNode.addChild(brandNode);
        marketingNode.addChild(new TreeNode("digital", "数字营销组", "Digital Mkt"));
        marketingNode.addChild(new TreeNode("sales", "销售组", "Sales"));

        root.addChild(opsNode);
        root.addChild(strategyNode);
        root.addChild(marketingNode);

        data.setRootNode(root);
        return data;
    }

    /**
     * 树节点内部类
     */
    @Data
    public static class TreeNode {

        private String id;

        private String title;

        private String subtitle;

        private Color color;          // 自定义颜色

        private List<TreeNode> children;

        // 布局时计算的坐标
        private transient int x;

        private transient int y;

        private transient int width;

        private transient int height;

        private transient int depth;

        public TreeNode() {
            this.children = new ArrayList<>();
        }

        public TreeNode(String id, String title, String subtitle) {
            this.id = id;
            this.title = title;
            this.subtitle = subtitle;
            this.children = new ArrayList<>();
        }

        public TreeNode(String id, String title, String subtitle, Color color) {
            this(id, title, subtitle);
            this.color = color;
        }

        public void addChild(TreeNode child) {
            if (this.children == null) {
                this.children = new ArrayList<>();
            }
            this.children.add(child);
        }
    }

    /**
     * 图例项
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LegendItem {
        private String text;
        private Color color;
    }
}
