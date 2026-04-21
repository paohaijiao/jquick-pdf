package com.github.paohaijiao;

import com.github.paohaijiao.combol.*;
import org.junit.Test;

import java.awt.*;
import java.util.List;


public class JMilestoneGraphTest {

    @Test
    public void testStandardFlow() throws Exception {
        JTimeLineData data = new JTimeLineData();
        List<JTimeLineData.FlowNode> nodes = new java.util.ArrayList<>();
        nodes.add(new JTimeLineData.FlowNode("项目启动", "•团队组建与立项|•市场调研完成|•战略规划制定", new Color(31, 78, 121), new Color(31, 78, 121)));
        nodes.add(new JTimeLineData.FlowNode("1000", "500", new Color(68, 114, 196), new Color(68, 114, 196)));
        nodes.add(new JTimeLineData.FlowNode("500", "里程碑达成", new Color(112, 173, 71), new Color(112, 173, 71)));
        nodes.add(new JTimeLineData.FlowNode("150%", "100%增长", new Color(237, 125, 49), new Color(237, 125, 49)));
        nodes.add(new JTimeLineData.FlowNode("100%", "50%完成率", new Color(79, 129, 189), new Color(79, 129, 189)));
        data.setNodes(nodes);
        data.setMainTitle("MILESTONE TIMELINE");
        data.setSubtitle("2021-2023 关键里程碑节点");
        data.setFooterText("数据来源：年度报告 | 更新日期：2024年1月");
        data.setHeight(1300);
        data.setBoxWidth(200);
        data.setBoxHeight(90);
        data.setStartX(100);
        data.setEndX(100);

        JOption option = new JOption();
        option.setData(data);

        JBusinessAlternateFlowRenderer renderer = new JBusinessAlternateFlowRenderer();
        renderer.render(option, "d://test//alternate_flow_1.svg");

    }
}
