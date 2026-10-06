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
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.series.JCandlestick;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 业务场景 Demo 60：股票日 K 线图（投资研究）
 *
 * <p>业务场景：投资研究部门以 K 线（蜡烛图）展示某股票 2024 年 12 月的日交易数据，
 * 实体为开收盘价区间，上下影线为最高与最低价，用于技术分析。</p>
 * <p>实际图表类型：{@link JChartType#K} K 线图。</p>
 *
 * <p>模板：{@code report/biz/60_k_chart.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\60_k_chart.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt 的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz60KChartTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz60KChart() throws IOException {
        // 2024 年 12 月交易日（共 22 个交易日，此处简化取前 15 个）
        String[] dates = {"12-02", "12-03", "12-04", "12-05", "12-06", "12-09", "12-10",
                "12-11", "12-12", "12-13", "12-16", "12-17", "12-18", "12-19", "12-20"};
        // 每组数据：[open, close, min, max]
        double[][] kData = {
                {32.10, 32.85, 31.90, 33.00},
                {32.80, 33.40, 32.50, 33.60},
                {33.30, 32.90, 32.60, 33.50},
                {32.95, 33.80, 32.80, 34.00},
                {33.70, 34.20, 33.50, 34.50},
                {34.10, 33.60, 33.20, 34.30},
                {33.55, 34.10, 33.30, 34.40},
                {34.00, 34.60, 33.80, 34.80},
                {34.50, 35.20, 34.30, 35.50},
                {35.10, 34.70, 34.40, 35.30},
                {34.65, 35.00, 34.20, 35.20},
                {34.95, 35.40, 34.80, 35.70},
                {35.30, 34.80, 34.50, 35.60},
                {34.75, 35.10, 34.40, 35.30},
                {35.00, 35.60, 34.90, 35.90}
        };

        JCandlestick candlestick = new JCandlestick("日 K 线");
        for (double[] d : kData) {
            candlestick.data(d[0], d[1], d[2], d[3]);
        }

        JOption option = new JOption();
        option.title("某股票 2024 年 12 月日 K 线图", "单位：元 | 数据来源：行情系统");
        option.xAxis(new JCategoryAxis().data((Object[]) dates));
        option.series(candlestick);

        String svg = JChartRendererFactory.renderChart(JChartType.K, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/60_k_chart.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/60_k_chart.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
