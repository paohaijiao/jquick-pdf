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

package com.paohaijiao.data.code;

/**
 * @author martin
 */
public enum JSeriesType {
    line,//折线图.........................
    lines,//线图,用于带有起点和终点信息的线数据的绘制，主要用于地图上的航线，路线的可视化+++++++++++++++++++++
    bar,//柱状图
    scatter,//散点图
    effectScatter,//带有涟漪效果的散点图++++++++++++++++++++++++-
    //k,//K线图，3.0会转换为下面的candlestick----------------------------
    candlestick,//K线图，k会自动转换这个新的++++++++++++++++++++
    pie,//饼图
    radar,//雷达图
    //chord,//和弦图
    //force,//力导向布局图,ECharts 2.x 中 force 类型的图表不再在 ECharts 3 中提供支持，转为统一使用 graph
    // 去展现关系数据。如果要使用力引导布局，可以将 layout 配置项设为'force'。--------------------
    graph,//关系图,用于展现节点以及节点之间的关系数据++++++++++++++++++++++++++
    map,//地图
    funnel,//漏斗图
    gauge,//仪表盘
    //island,//孤岛
    //eventRiver,//事件河流图
    //venn,//韦恩图
    treemap,//矩形树图
    //wordCloud,//词云
    //tree,//树图
    heatmap,//热力图
    boxplot,//中文可以称为『箱形图』、『盒须图』、『盒式图』、『盒状图』、『箱线图』++++++++++++++++++++++++
    parallel,//平行坐标系++++++++++++++++++++++
    sankey,//桑基图,是一种特殊的流图, 它主要用来表示原材料、能量等如何从初始形式经过中间过程的加工、转化到达最终形式++++++++++++++
}
