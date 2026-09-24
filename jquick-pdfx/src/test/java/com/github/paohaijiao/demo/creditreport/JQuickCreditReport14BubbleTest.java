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
package com.github.paohaijiao.demo.creditreport;

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

/**
 * 统一信用报告 —— 风格 14：气泡图（浅蓝）
 *
 * <p>以 {@code report/credit_report_14_bubble.txt} 为模板，把气泡图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\14_bubble.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport14BubbleTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report14Bubble() throws IOException {
        JOption option = new JOption();
        option.title().text("风险气泡分布").subtext("气泡大小代表风险敞口，颜色代表风险等级");
        option.legend("低风险", "中风险", "高风险");
        option.xAxis(new CategoryAxis().name("账龄（月）"));
        option.yAxis(new ValueAxis().name("逾期率（%）"));
        ScatterSeries series = new ScatterSeries("风险敞口");
        List<Map<String, Object>> seriesData = new ArrayList<>();
        seriesData.add(bubblePoint("客户A", "3", 1.2, 120, "低风险"));
        seriesData.add(bubblePoint("客户B", "6", 3.5, 260, "中风险"));
        seriesData.add(bubblePoint("客户C", "9", 6.8, 410, "高风险"));
        seriesData.add(bubblePoint("客户D", "4", 2.1, 180, "低风险"));
        seriesData.add(bubblePoint("客户E", "12", 9.4, 560, "高风险"));
        seriesData.add(bubblePoint("客户F", "8", 5.2, 330, "中风险"));
        series.data(seriesData.toArray());
        option.series(series);

        String svg = JChartRendererFactory.renderChart(JChartType.Bubble, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/14_bubble.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_14_bubble.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    private static Map<String, Object> bubblePoint(String name, String x, double y, double size, String category) {
        Map<String, Object> point = new HashMap<>();
        point.put("x", x);
        point.put("y", y);
        point.put("size", size);
        point.put("category", category);
        point.put("name", name);
        point.put("color", bubbleColor(category));
        return point;
    }

    private static Color bubbleColor(String category) {
        if ("低风险".equals(category)) {
            return new Color(2, 136, 209, 180);
        }
        if ("中风险".equals(category)) {
            return new Color(255, 167, 38, 180);
        }
        return new Color(229, 57, 53, 180);
    }
}
