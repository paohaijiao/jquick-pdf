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
import com.github.paohaijiao.combol.JCircleChartData;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JCircleTest {

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.Circle);
        JCircleChartData chartData = new JCircleChartData();
        chartData.setWidth(500);
        chartData.setHeight(400);
        chartData.setTitleText("2024年度销售分布");
        chartData.setSubtitleText("按产品类别统计");
        chartData.setCenterTitle("总销售额");
        chartData.setCenterUnit("万");
        chartData.setFooterText("数据基于2024年度销售报告");
        List<JCircleChartData.SectorData> sectors = new ArrayList<>();
        sectors.add(new JCircleChartData.SectorData("产品A", 4480, new Color(46, 125, 100)));
        sectors.add(new JCircleChartData.SectorData("产品B", 3584, new Color(74, 144, 196)));
        sectors.add(new JCircleChartData.SectorData("产品C", 2816, new Color(91, 108, 142)));
        sectors.add(new JCircleChartData.SectorData("产品D", 1920, new Color(154, 172, 184)));
        chartData.setSectorDataList(sectors);
        JOption option = new JOption();
        option.setData(chartData);
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
