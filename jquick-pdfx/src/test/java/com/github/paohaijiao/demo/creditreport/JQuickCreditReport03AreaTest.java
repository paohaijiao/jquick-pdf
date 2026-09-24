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
import com.github.paohaijiao.combol.area.JAreaChartData;
import com.github.paohaijiao.combol.area.JSeriesData;
import com.github.paohaijiao.combol.area.JTheme;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 统一信用报告 —— 风格 03：区域图（靛蓝）
 *
 * <p>以 {@code report/credit_report_03_area.txt} 为模板，把区域图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\03_area.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport03AreaTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report03Area() throws IOException {
        JAreaChartData data = new JAreaChartData();
        data.setTitle("信用评级趋势");
        data.setSubtitle("2020-2023年度（单位：万元）");
        data.setXAxisTitle("年度");
        data.setYAxisTitle("金额（万元）");
        data.setLegendText("营业收入");
        data.setShowDataLabels(true);
        data.setSeriesList(Arrays.asList(new JSeriesData("营业收入", Arrays.asList(8560.0, 12340.0, 18920.0, 25680.0))));
        data.setXAxisLabels(Arrays.asList("2020年", "2021年", "2022年", "2023年"));
        data.setTheme(JTheme.DEFAULT);
        JOption option = new JOption();
        option.setData(data);

        String svg = JChartRendererFactory.renderChart(JChartType.AREA, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/03_area.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_03_area.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
