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

import com.github.paohaijiao.code.JLayout;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.json.JGsonOption;
import com.github.paohaijiao.series.JGraph;
import com.github.paohaijiao.series.force.JCategory;
import com.github.paohaijiao.series.force.JLink;
import com.github.paohaijiao.series.force.JNode;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务场景 Demo 28：部门成本构成与流向图（集团成本管控）
 *
 * <p>业务场景：集团财务共享中心展示「一级成本中心 → 二级责任部门 → 成本归集终点」的成本构成与流向，
 * 用于成本管控专题会定位成本增量的责任归属。</p>
 * <p>实际图表类型：{@link JChartType#RELATION} 关系图。<b>说明：jquick-pdf 暂无桑基图渲染器，
 * 本 Demo 以关系图（节点 + 有向连线）替代桑基图表达成本流向，故模板图注中标注为「桑基图替代视图」。</b></p>
 *
 * <p>模板：{@code report/biz/28_cost_relation.txt}；关系图以 SVG 文本形式绑定到模板的 {@code ${svg}} 占位符，
 * 输出到 {@code D:\test\biz\28_cost_relation.pdf}。</p>
 *
 * <p>图表尺寸说明：关系图按渲染器默认画布 800pt × 600pt（宽 × 高）绘制，共 11 个节点（三列：成本中心 / 责任部门 /
 * 归集终点），每个节点固定写入 x/y 坐标（取值 180~800）以避免力导向随机分布导致的重叠与布局漂移；
 * 模板中 {@code <svg>} 只声明宽度、不声明高度，宽度被页面自动收窄时高度按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz28CostRelationTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz28CostRelation() throws IOException {
        JGsonOption option = new JGsonOption();
        option.title("部门成本构成与流向关系图");

        JGraph graph = new JGraph();
        graph.name("成本流向");
        graph.layout(JLayout.force);
        graph.force().repulsion(100);
        graph.draggable(true);

        // 节点分三列：第一列一级成本中心、第二列二级责任部门、第三列成本归集终点
        List<JNode> nodes = new ArrayList<>();
        nodes.add(position(new JNode("1", "人工成本").symbolSize(46).category(0), 180, 150));
        nodes.add(position(new JNode("2", "采购与外包成本").symbolSize(46).category(0), 180, 350));
        nodes.add(position(new JNode("3", "资产折旧与摊销").symbolSize(40).category(0), 180, 550));
        nodes.add(position(new JNode("4", "运营与能耗费用").symbolSize(40).category(0), 180, 750));
        nodes.add(position(new JNode("5", "研发中心").symbolSize(38).category(1), 480, 150));
        nodes.add(position(new JNode("6", "生产制造部").symbolSize(38).category(1), 480, 350));
        nodes.add(position(new JNode("7", "市场营销部").symbolSize(36).category(1), 480, 550));
        nodes.add(position(new JNode("8", "行政管理部").symbolSize(34).category(1), 480, 750));
        nodes.add(position(new JNode("9", "主营业务成本").symbolSize(44).category(2), 800, 250));
        nodes.add(position(new JNode("10", "期间费用").symbolSize(40).category(2), 800, 500));
        nodes.add(position(new JNode("11", "研发资本化").symbolSize(34).category(2), 800, 750));
        graph.setData(nodes);

        // 成本流向连线：一级成本中心 → 二级责任部门 → 成本归集终点
        List<JLink> links = new ArrayList<>();
        links.add(new JLink("1", "5"));
        links.add(new JLink("1", "6"));
        links.add(new JLink("1", "7"));
        links.add(new JLink("1", "8"));
        links.add(new JLink("2", "5"));
        links.add(new JLink("2", "6"));
        links.add(new JLink("2", "8"));
        links.add(new JLink("3", "6"));
        links.add(new JLink("3", "9"));
        links.add(new JLink("4", "6"));
        links.add(new JLink("4", "8"));
        links.add(new JLink("5", "9"));
        links.add(new JLink("5", "11"));
        links.add(new JLink("6", "9"));
        links.add(new JLink("7", "10"));
        links.add(new JLink("8", "10"));
        graph.setLinks(links);

        List<JCategory> categories = new ArrayList<>();
        categories.add(new JCategory().name("一级成本中心"));
        categories.add(new JCategory().name("二级责任部门"));
        categories.add(new JCategory().name("成本归集终点"));
        graph.setCategories(categories);

        option.series(graph);
        String svg = JChartRendererFactory.renderChart(JChartType.RELATION, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/28_cost_relation.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/28_cost_relation.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    /**
     * 为节点写入固定坐标（渲染器读取 node 的 x/y，缺省时随机分布）
     */
    private JNode position(JNode node, int x, int y) {
        node.put("x", x);
        node.put("y", y);
        return node;
    }
}
