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
package com.github.paohaijiao.factory;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.bar.JBarChartsRenderer;
import com.github.paohaijiao.boxPlot.JBoxPlotChartRenderer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.heatMap.JHeatMapChartRenderer;
import com.github.paohaijiao.k.JKChartsRenderer;
import com.github.paohaijiao.line.JLineChartsRenderer;
import com.github.paohaijiao.pie.JPieChartsRenderer;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.radar.JRadarChartsRenderer;
import com.github.paohaijiao.relation.JRelationChartRenderer;
import com.github.paohaijiao.scatter.JScatterChartsRenderer;
import com.github.paohaijiao.sunburst.JSunburstChart;
import com.github.paohaijiao.words.JWordCloudRenderer;

/**
 * packageName com.github.paohaijiao.factory
 *
 * @author Martin
 * @version 1.0.0
 * @className JChartRendererFactory
 * @date 2025/6/27
 * @description
 */
public class JChartRendererFactory {

    public static JAbstractChartRenderer createRenderer(JChartType type, JOption option) {
        switch (type) {
            case BAR:
                return new JBarChartsRenderer();
            case BOXPLOT:
                return new JBoxPlotChartRenderer();
            case HEATMAP:
                return new JHeatMapChartRenderer();
            case K:
                return new JKChartsRenderer();
            case LINE:
                return new JLineChartsRenderer();
            case PIE:
                return new JPieChartsRenderer();
            case RADAR:
                return new JRadarChartsRenderer();
            case RELATION:
                return new JRelationChartRenderer();
            case SCATTER:
                return new JScatterChartsRenderer();
            case SUNBURST:
                return new JSunburstChart();
            case WORDCLOUD:
                return new JWordCloudRenderer();
            default:
                throw new IllegalArgumentException("Unsupported chart type: " + type);
        }
    }

    public static String renderChart(JChartType type, JOption option) {
        JAbstractChartRenderer renderer = createRenderer(type, option);
        try {
            String content = renderer.renderToString(option);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void renderChart(JChartType type, JOption option, String filePath) {
        JAbstractChartRenderer renderer = createRenderer(type, option);
        try {
            renderer.render(option, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
