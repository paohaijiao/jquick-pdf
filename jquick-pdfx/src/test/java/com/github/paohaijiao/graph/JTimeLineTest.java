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
package com.github.paohaijiao.graph;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.combol.JTimeLineData;
import com.github.paohaijiao.combol.area.JAreaChartData;
import com.github.paohaijiao.combol.area.JSeriesData;
import com.github.paohaijiao.combol.area.JTheme;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JTimeLineTest {

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.TimeLine);
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
        graphContainer.setOption(option);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        JReader fileReader = new JReSourceFileReader("sample/svg2.txt");
        JAdaptor context = new JAdaptor(fileReader);
        JQuickPdfXExecutor executor = new JQuickPdfXExecutor(config);
        executor.execute(context.getRuleContent());
    }
}
