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
import com.github.paohaijiao.combol.JDoubleRadarChartData;
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
import java.util.Arrays;

/**
 * 统一信用报告 —— 风格 06：双维对比雷达图（玫红）
 *
 * <p>以 {@code report/credit_report_06_double_radar.txt} 为模板，把双雷达图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\06_double_radar.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport06DoubleRadarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report06DoubleRadar() throws IOException {
        JDoubleRadarChartData chartData = new JDoubleRadarChartData();
        chartData.setWidth(900);
        chartData.setHeight(500);
        chartData.setTitleText("双维对比雷达图");
        chartData.setSubtitleText("本期与同业均值对比");
        chartData.setLeftTitle("本期评分");
        chartData.setRightTitle("同业均值");
        chartData.setDimensions(Arrays.asList("偿债能力", "盈利能力", "运营能力", "成长能力", "现金流"));
        JDoubleRadarChartData.RadarData leftRadar = new JDoubleRadarChartData.RadarData();
        JDoubleRadarChartData.Series leftSeries = new JDoubleRadarChartData.Series();
        leftSeries.setName("本期");
        leftSeries.setValues(Arrays.asList(88.0, 92.0, 85.0, 95.0, 80.0));
        leftSeries.setColor(new Color(194, 24, 91));
        leftRadar.setSeriesList(Arrays.asList(leftSeries));
        chartData.setLeftRadar(leftRadar);
        JDoubleRadarChartData.RadarData rightRadar = new JDoubleRadarChartData.RadarData();
        JDoubleRadarChartData.Series rightSeries = new JDoubleRadarChartData.Series();
        rightSeries.setName("同业");
        rightSeries.setValues(Arrays.asList(78.0, 80.0, 76.0, 82.0, 74.0));
        rightSeries.setColor(new Color(233, 30, 99));
        rightRadar.setSeriesList(Arrays.asList(rightSeries));
        chartData.setRightRadar(rightRadar);
        chartData.setGridLevels(4);
        chartData.setFillAlpha(70);
        chartData.setLineWidth(2.0f);
        chartData.setShowDataPoints(true);
        chartData.setLegendAtTop(false);
        chartData.setShowLegendSide(true);
        chartData.setFooterText("数据来源：企业信用评估中心");
        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.DoubleRadar, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/06_double_radar.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_06_double_radar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
