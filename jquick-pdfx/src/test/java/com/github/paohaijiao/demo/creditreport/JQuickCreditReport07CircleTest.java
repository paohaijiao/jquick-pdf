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
import com.github.paohaijiao.combol.JCircleChartData;
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
import java.util.List;

/**
 * 统一信用报告 —— 风格 07：环形图（深绿）
 *
 * <p>以 {@code report/credit_report_07_circle.txt} 为模板，把环形图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\07_circle.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport07CircleTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report07Circle() throws IOException {
        JCircleChartData chartData = new JCircleChartData();
        chartData.setWidth(500);
        chartData.setHeight(400);
        chartData.setTitleText("营收结构环形图");
        chartData.setSubtitleText("2023年度按业务线统计");
        chartData.setCenterTitle("总营收");
        chartData.setCenterUnit("万元");
        chartData.setFooterText("数据来源：企业信用评估中心");
        List<JCircleChartData.SectorData> sectors = new ArrayList<>();
        sectors.add(new JCircleChartData.SectorData("软件产品", 10270, new Color(46, 125, 50)));
        sectors.add(new JCircleChartData.SectorData("技术服务", 6420, new Color(67, 160, 71)));
        sectors.add(new JCircleChartData.SectorData("云服务", 5136, new Color(129, 199, 132)));
        sectors.add(new JCircleChartData.SectorData("其他", 3854, new Color(165, 214, 167)));
        chartData.setSectorDataList(sectors);
        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.Circle, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/07_circle.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_07_circle.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
