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
import com.github.paohaijiao.JRadar;
import com.github.paohaijiao.code.JSeriesType;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.series.JRadarSeries;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 统一信用报告 —— 风格 05：雷达图（紫色）
 *
 * <p>以 {@code report/credit_report_05_radar.txt} 为模板，把雷达图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\05_radar.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport05RadarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report05Radar() throws IOException {
        JOption option = new JOption();
        option.title().text("信用评级多维评估").subtext("各维度得分（满分100）");
        option.tooltip().trigger(JTrigger.item);
        JRadar radar = new JRadar();
        radar.indicator(
                new JRadar.Indicator().name("偿债能力").max(100),
                new JRadar.Indicator().name("盈利能力").max(100),
                new JRadar.Indicator().name("运营能力").max(100),
                new JRadar.Indicator().name("成长能力").max(100),
                new JRadar.Indicator().name("现金流").max(100),
                new JRadar.Indicator().name("信用历史").max(100)
        );
        option.radar(radar);
        JRadarSeries current = new JRadarSeries();
        current.name("本期评分").type(JSeriesType.radar).data(88, 92, 85, 95, 80, 90);
        JRadarSeries last = new JRadarSeries();
        last.name("上期评分").type(JSeriesType.radar).data(82, 85, 80, 88, 76, 86);
        option.series(current, last);

        String svg = JChartRendererFactory.renderChart(JChartType.RADAR, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/05_radar.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_05_radar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
