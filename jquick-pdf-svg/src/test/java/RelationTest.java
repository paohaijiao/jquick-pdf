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
import com.paohaijiao.data.JOption;
import com.paohaijiao.data.code.JLayout;
import com.paohaijiao.data.json.JGsonOption;
import com.paohaijiao.data.series.JGraph;
import com.paohaijiao.data.series.force.JCategory;
import com.paohaijiao.data.series.force.JLink;
import com.paohaijiao.data.series.force.JNode;
import com.paohaijiao.echart.radar.JRadarChartsRenderer;
import com.paohaijiao.echart.relation.JRelationChartRenderer;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;

public class RelationTest {
    @Test
    public void testBarChar1() throws IOException, ParserConfigurationException, TransformerException {

        JGsonOption option = new JGsonOption();
        option.title("Relationship Chart Test");

        // 创建图系列
        JGraph graph = new JGraph();
        graph.name("关系图");
        graph.layout(JLayout.force); // 使用力导向布局
        graph.force().repulsion(100); // 设置排斥力
        graph.draggable(true); // 节点可拖动

        // 添加节点 - 修正了ID问题
        List<JNode> nodes = new ArrayList<>();
        nodes.add(new JNode("1", "Node A").symbolSize(30).category(0));//id 1
        nodes.add(new JNode("2", "Node B").symbolSize(25).category(1));
        nodes.add(new JNode("3", "Node C").symbolSize(20).category(2));
        nodes.add(new JNode("4", "Node D").symbolSize(15).category(0));
        nodes.add(new JNode("5", "Node E").symbolSize(35).category(1));
        nodes.add(new JNode("6", "Node F").symbolSize(20).category(3));
        nodes.add(new JNode("7", "Node G").symbolSize(25).category(2));
        nodes.add(new JNode("8", "Node H").symbolSize(15).category(4));
        nodes.add(new JNode("9", "Node I").symbolSize(30).category(3));
        nodes.add(new JNode("10", "Node J").symbolSize(20).category(0));
        graph.setData(nodes);

        // 添加连接
        List<JLink> links = new ArrayList<>();
        links.add(new JLink("1", "2"));
        links.add(new JLink("1", "3"));
        links.add(new JLink("2", "4"));
        links.add(new JLink("3", "5"));
        links.add(new JLink("4", "6"));
        links.add(new JLink("5", "7"));
        links.add(new JLink("6", "8"));
        links.add(new JLink("7", "9"));
        links.add(new JLink("8", "10"));
        links.add(new JLink("9", "1"));
        links.add(new JLink("10", "2"));
        links.add(new JLink("3", "6"));
        links.add(new JLink("4", "7"));
        links.add(new JLink("5", "8"));
        graph.setLinks(links);

        // 添加类别
        List<JCategory> categories = new ArrayList<>();
        categories.add(new JCategory().name("Category 1"));
        categories.add(new JCategory().name("Category 2"));
        categories.add(new JCategory().name("Category 3"));
        categories.add(new JCategory().name("Category 4"));
        categories.add(new JCategory().name("Category 5"));
        graph.setCategories(categories);

        option.series(graph);
        option.legend().data("Category 1", "Category 2", "Category 3", "Category 4", "Category 5");

        JRelationChartRenderer renderer=new JRelationChartRenderer();
        renderer.render(option, "d://test//relation_chart.svg");
        System.out.println("雷达图SVG已生成: radar_chart.svg");


    }
}
