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
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.combol.JMultiLineChartData;
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
import java.util.List;

/**
 * 业务场景 Demo 43：系统接口调用量趋势（政务云平台运维）
 *
 * <p>业务场景：政务云平台 API 网关按月统计身份核验、数据查询、证照下载、电子签章四类核心接口的
 * 成功调用量，用于平台容量规划与限流策略制定，主色采用深蓝 #0D47A1。</p>
 * <p>实际图表类型：{@link JChartType#MultipleLine} 多重折线图（四条接口调用量共用「万次」量纲，观察月度增长与峰值）。</p>
 *
 * <p>模板：{@code report/biz/43_api_calls_line.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\43_api_calls_line.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz43ApiCallsLineTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz43ApiCallsLine() throws IOException {
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        JMultiLineChartData chartData = new JMultiLineChartData();
        chartData.setXAxisLabels(months);
        chartData.setTitleText("2024年平台接口调用量趋势");
        chartData.setSubtitleText("四类核心接口月度调用量对比");
        chartData.setYAxisTitle("调用量（万次）");
        chartData.setFooterText("数据来源：政务云API网关 | 统计口径：自然月（单位：万次）");
        chartData.setGridCount(6);
        chartData.setShowDataLabels(false);
        chartData.setShowInnerPoint(true);
        chartData.setPointRadius(5);
        chartData.setInnerPointRadius(2);
        chartData.setChartAreaColor(new Color(248, 249, 250));
        chartData.setAxisColor(Color.BLACK);
        chartData.setGridColor(new Color(220, 220, 220));
        chartData.setTextColor(Color.BLACK);
        chartData.setFooterColor(new Color(128, 128, 128));
        chartData.setValueWithPercent(false);
        chartData.setAutoCalculateMax(true);
        chartData.setRotateXAxisLabels(false);
        // 身份核验：平台最大流量入口
        JMultiLineChartData.LineData lineA = new JMultiLineChartData.LineData();
        lineA.setName("身份核验");
        lineA.setLegendText("身份核验（万次）");
        lineA.setValues(Arrays.asList(1280.0, 1245.0, 1360.0, 1420.0, 1385.0, 1450.0, 1520.0, 1495.0, 1560.0, 1620.0, 1685.0, 1740.0));
        lineA.setLineColor(new Color(13, 71, 161));
        lineA.setLineWidth(2.5f);
        // 数据查询：共享交换与数据核验调用
        JMultiLineChartData.LineData lineB = new JMultiLineChartData.LineData();
        lineB.setName("数据查询");
        lineB.setLegendText("数据查询（万次）");
        lineB.setValues(Arrays.asList(860.0, 835.0, 905.0, 960.0, 940.0, 985.0, 1030.0, 1015.0, 1065.0, 1120.0, 1160.0, 1205.0));
        lineB.setLineColor(new Color(21, 101, 192));
        lineB.setLineWidth(2.5f);
        // 证照下载：电子证照库对外服务
        JMultiLineChartData.LineData lineC = new JMultiLineChartData.LineData();
        lineC.setName("证照下载");
        lineC.setLegendText("证照下载（万次）");
        lineC.setValues(Arrays.asList(420.0, 402.0, 445.0, 478.0, 462.0, 495.0, 520.0, 508.0, 536.0, 560.0, 585.0, 612.0));
        lineC.setLineColor(new Color(0, 131, 143));
        lineC.setLineWidth(2.5f);
        // 电子签章：增速最快的新增能力
        JMultiLineChartData.LineData lineD = new JMultiLineChartData.LineData();
        lineD.setName("电子签章");
        lineD.setLegendText("电子签章（万次）");
        lineD.setValues(Arrays.asList(210.0, 226.0, 248.0, 265.0, 288.0, 305.0, 326.0, 348.0, 372.0, 395.0, 418.0, 442.0));
        lineD.setLineColor(new Color(120, 144, 156));
        lineD.setLineWidth(2.5f);
        chartData.setLineDataList(Arrays.asList(lineA, lineB, lineC, lineD));
        // 设置完折线数据后必须刷新 Y 轴最大值
        chartData.updateMaxValues();
        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText("2024年平台接口调用量趋势");
        title.setSubtext("四类核心接口月度调用量对比");
        option.setTitle(title);
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.MultipleLine, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/43_api_calls_line.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/43_api_calls_line.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
