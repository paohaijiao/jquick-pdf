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

import com.github.paohaijiao.graph.JGraphData;

import java.util.List;

/**
 * 图表数据配置类
 */
@lombok.Data
public class JLineScatterChartData implements JGraphData {

    private int width = 800;

    private int height = 500;

    private String titleText;

    private String subtitleText;

    private String footerText;

    private List<String> categories;           // X轴类别（如月份）

    private List<Double> lineValues;           // 折线数据

    private List<Double> scatterValues;        // 散点数据

    private String lineSeriesName = "计划销售额";

    private String scatterSeriesName = "实际完成额";

    private double maxValue;                    // Y轴最大值

    private int gridCount = 5;                  // 网格线数量

    private boolean showDataLabels = true;      // 是否显示数据标签

    private boolean valueWithPercent = false;   // 是否百分比格式
}
