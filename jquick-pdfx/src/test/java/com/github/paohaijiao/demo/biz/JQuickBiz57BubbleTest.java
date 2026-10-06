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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.bubble.CategoryAxis;
import com.github.paohaijiao.bubble.ScatterSeries;
import com.github.paohaijiao.bubble.ValueAxis;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 业务场景 Demo 57：城市空气质量监测气泡图（生态环境监测）
 *
 * <p>业务场景：生态环境监测中心对城市空气质量进行逐日监测，以气泡图展示
 * 日期（X 轴）、AQI 数值（Y 轴）、PM2.5 浓度（气泡大小）以及空气质量等级（气泡颜色）。</p>
 * <p>实际图表类型：{@link JChartType#Bubble} 气泡图。</p>
 *
 * <p>模板：{@code report/biz/57_bubble.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\57_bubble.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt 的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz57BubbleTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz57Bubble() throws IOException {
        JOption option = new JOption()
                .title("城市空气质量监测气泡图", "X 轴：日期  Y 轴：AQI  气泡大小：PM2.5 浓度  颜色：等级")
                .legend("优", "良", "轻度污染", "中度污染", "重度污染")
                .xAxis(new CategoryAxis().name("日期"))
                .yAxis(new ValueAxis().name("AQI 数值"));

        ScatterSeries series = new ScatterSeries("空气质量监测");
        List<Map<String, Object>> seriesData = new ArrayList<>();
        Random random = new Random(42);
        String[] dates = {"01-01", "01-02", "01-03", "01-04", "01-05", "01-06", "01-07", "01-08",
                "01-09", "01-10", "01-11", "01-12", "01-13", "01-14", "01-15"};
        for (int i = 0; i < dates.length; i++) {
            int aqi = 20 + random.nextInt(180);
            double pm25 = 10 + random.nextDouble() * 150;
            String category;
            if (aqi <= 50) category = "优";
            else if (aqi <= 100) category = "良";
            else if (aqi <= 150) category = "轻度污染";
            else if (aqi <= 200) category = "中度污染";
            else category = "重度污染";
            Map<String, Object> point = new HashMap<>();
            point.put("x", dates[i]);
            point.put("y", aqi);
            point.put("size", pm25);
            point.put("category", category);
            point.put("name", "日期:" + dates[i] + ", AQI:" + aqi + ", PM2.5:" + String.format("%.1f", pm25));
            point.put("color", getBubbleColor(category));
            seriesData.add(point);
        }
        series.data(seriesData.toArray());
        option.series(series);

        String svg = JChartRendererFactory.renderChart(JChartType.Bubble, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/57_bubble.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/57_bubble.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    private static Color getBubbleColor(String category) {
        switch (category) {
            case "优":
                return new Color(102, 194, 165, 180);
            case "良":
                return new Color(252, 194, 91, 180);
            case "轻度污染":
                return new Color(246, 138, 89, 180);
            case "中度污染":
                return new Color(232, 96, 85, 180);
            default:
                return new Color(158, 42, 95, 180);
        }
    }
}
