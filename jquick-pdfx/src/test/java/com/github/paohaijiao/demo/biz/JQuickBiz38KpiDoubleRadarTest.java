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
 * 业务场景 Demo 38：多维度 KPI 双雷达对比（企业绩效管理）
 *
 * <p>业务场景：企业绩效管理部门从营收增长、成本控制、交付及时率、质量合格率、客户满意度五个维度，
 * 对比本期（2024年）与去年同期（2023年）的 KPI 得分，用于年度绩效复盘。</p>
 * <p>实际图表类型：{@link JChartType#DoubleRadar} 双雷达图，左侧为本期、右侧为去年同期，两个雷达并列展示。</p>
 *
 * <p>模板：{@code report/biz/38_kpi_double_radar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\38_kpi_double_radar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>} 只声明宽度、不声明高度，
 * 宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz38KpiDoubleRadarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz38KpiDoubleRadar() throws IOException {
        JDoubleRadarChartData chartData = new JDoubleRadarChartData();
        chartData.setTitleText("多维度 KPI 双雷达对比");
        chartData.setSubtitleText("本期（2024年）与去年同期（2023年）绩效得分对比");
        chartData.setLeftTitle("本期 KPI（2024年）");
        chartData.setRightTitle("去年同期（2023年）");
        chartData.setDimensions(Arrays.asList("营收增长", "成本控制", "交付及时率", "质量合格率", "客户满意度"));
        // 左侧雷达：本期各维度得分
        JDoubleRadarChartData.RadarData leftRadar = new JDoubleRadarChartData.RadarData();
        JDoubleRadarChartData.Series leftSeries = new JDoubleRadarChartData.Series();
        leftSeries.setName("本期");
        leftSeries.setValues(Arrays.asList(88.0, 92.0, 85.0, 95.0, 80.0));
        leftSeries.setColor(new Color(106, 27, 154));
        leftRadar.setSeriesList(Arrays.asList(leftSeries));
        chartData.setLeftRadar(leftRadar);
        // 右侧雷达：去年同期各维度得分
        JDoubleRadarChartData.RadarData rightRadar = new JDoubleRadarChartData.RadarData();
        JDoubleRadarChartData.Series rightSeries = new JDoubleRadarChartData.Series();
        rightSeries.setName("去年同期");
        rightSeries.setValues(Arrays.asList(81.0, 85.0, 79.0, 90.0, 76.0));
        rightSeries.setColor(new Color(156, 39, 176));
        rightRadar.setSeriesList(Arrays.asList(rightSeries));
        chartData.setRightRadar(rightRadar);
        chartData.setGridLevels(4);
        chartData.setFillAlpha(70);
        chartData.setLineWidth(2.0f);
        chartData.setShowDataPoints(true);
        chartData.setLegendAtTop(false);
        chartData.setShowLegendSide(true);
        chartData.setFooterText("数据来源：企业绩效管理系统");
        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.DoubleRadar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/38_kpi_double_radar.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/38_kpi_double_radar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
