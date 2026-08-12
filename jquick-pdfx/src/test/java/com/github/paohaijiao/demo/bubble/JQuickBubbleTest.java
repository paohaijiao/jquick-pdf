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
package com.github.paohaijiao.demo.bubble;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.bubble.CategoryAxis;
import com.github.paohaijiao.bubble.ScatterSeries;
import com.github.paohaijiao.bubble.ValueAxis;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import org.junit.Test;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JQuickBubbleTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void bubble() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        JTitle title = new JTitle();
        title.setText("空气质量指数 (AQI) 监测气泡图");
        title.setSubtext("图表说明：本气泡图展示了空气质量指数(AQI)的时间变化趋势。X轴表示日期，Y轴表示AQI数值，气泡大小反映PM2.5浓度，气泡颜色表示AQI等级。");
        JOption option = new JOption()
                .title(title)
                .legend("优", "良", "轻度污染", "中度污染", "重度污染")
                .xAxis(new CategoryAxis().name("日期"))
                .yAxis(new ValueAxis().name("AQI数值"));
        ScatterSeries series = new ScatterSeries("空气质量监测");
        List<Map<String, Object>> seriesData = new ArrayList<>();
        Random random = new Random(42); // 固定种子以便重现
        String[] dates = {"01-01", "01-02", "01-03", "01-04", "01-05", "01-06", "01-07", "01-08", "01-09", "01-10", "01-11", "01-12", "01-13", "01-14", "01-15"};
        for (int i = 0; i < dates.length; i++) {
            int aqi = 20 + random.nextInt(180); // AQI 20-200
            double pm25 = 10 + random.nextDouble() * 150; // PM2.5 10-160
            String category;
            if (aqi <= 50) category = "优";
            else if (aqi <= 100) category = "良";
            else if (aqi <= 150) category = "轻度污染";
            else if (aqi <= 200) category = "中度污染";
            else category = "重度污染";
            String name = String.format("日期:%s, AQI:%d, PM2.5:%.1f", dates[i], aqi, pm25);
            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("x", dates[i]);
            dataPoint.put("y", aqi);
            dataPoint.put("size", pm25);
            dataPoint.put("category", category);
            dataPoint.put("name", name);
            dataPoint.put("color", getBubbleColor(category,aqi));
            seriesData.add(dataPoint);
        }
        series.data(seriesData.toArray());
        option.series(series);
        option.title("公司业务分布矩形树图（JTreemapRenderer）");
        graphContainer.setType(JChartType.Bubble);
        graphContainer.setOption(option);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);

        FileOutputStream fileOutputStream = new FileOutputStream(path + "test.pdf");
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        byte[] bytes = factory.executeResource("sample/svg2.txt");
        fileOutputStream.write(bytes);
    }
    private static  Color getBubbleColor(Object category, double yValue) {
        if (category != null) {
            String categoryStr = category.toString();
            switch (categoryStr) {
                case "优":
                    return new Color(102, 194, 165, 180);
                case "良":
                    return new Color(252, 194, 91, 180);
                case "轻度污染":
                    return new Color(246, 138, 89, 180);
                case "中度污染":
                    return new Color(232, 96, 85, 180);
                case "重度污染":
                    return new Color(158, 42, 95, 180);
            }
        }
        if (yValue <= 50) return new Color(102, 194, 165, 180);
        else if (yValue <= 100) return new Color(252, 194, 91, 180);
        else if (yValue <= 150) return new Color(246, 138, 89, 180);
        else if (yValue <= 200) return new Color(232, 96, 85, 180);
        else return new Color(158, 42, 95, 180);
    }
}

