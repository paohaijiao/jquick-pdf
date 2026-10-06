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
 * 业务场景 Demo 66：两家供应商多维度能力双雷达对比（采购管理）
 *
 * <p>业务场景：采购管理部门从质量、价格、交付、服务、技术、响应六个维度
 * 综合打分，以双雷达图并列对比供应商 A 与供应商 B 的能力。</p>
 * <p>实际图表类型：{@link JChartType#DoubleRadar} 双雷达图。</p>
 *
 * <p>模板：{@code report/biz/66_twoRadar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\66_twoRadar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz66TwoRadarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz66TwoRadar() throws IOException {
        JDoubleRadarChartData chartData = new JDoubleRadarChartData();
        chartData.setTitleText("两家供应商多维度能力双雷达对比");
        chartData.setSubtitleText("左：供应商 A　右：供应商 B　维度：质量/价格/交付/服务/技术/响应");
        chartData.setLeftTitle("供应商 A");
        chartData.setRightTitle("供应商 B");
        chartData.setDimensions(Arrays.asList("质量", "价格", "交付", "服务", "技术", "响应"));

        JDoubleRadarChartData.RadarData leftRadar = new JDoubleRadarChartData.RadarData();
        JDoubleRadarChartData.Series leftSeries = new JDoubleRadarChartData.Series();
        leftSeries.setName("供应商 A");
        leftSeries.setValues(Arrays.asList(92.0, 70.0, 85.0, 80.0, 90.0, 75.0));
        leftSeries.setColor(new Color(136, 14, 79));
        leftRadar.setSeriesList(Arrays.asList(leftSeries));
        chartData.setLeftRadar(leftRadar);

        JDoubleRadarChartData.RadarData rightRadar = new JDoubleRadarChartData.RadarData();
        JDoubleRadarChartData.Series rightSeries = new JDoubleRadarChartData.Series();
        rightSeries.setName("供应商 B");
        rightSeries.setValues(Arrays.asList(78.0, 92.0, 70.0, 75.0, 72.0, 88.0));
        rightSeries.setColor(new Color(194, 24, 91));
        rightRadar.setSeriesList(Arrays.asList(rightSeries));
        chartData.setRightRadar(rightRadar);

        chartData.setGridLevels(5);
        chartData.setFillAlpha(70);
        chartData.setLineWidth(2.0f);
        chartData.setShowDataPoints(true);
        chartData.setLegendAtTop(true);
        chartData.setShowLegendSide(false);
        chartData.setFooterText("数据来源：供应商综合评估（满分 100）");

        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.DoubleRadar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/66_twoRadar.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/66_twoRadar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
