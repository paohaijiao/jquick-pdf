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
 * 业务场景 Demo 23：企业客户风险等级雷达评估（金融/信用平台）
 *
 * <p>业务场景：金融/信用平台对某企业客户做风险等级多维评估，从偿债能力、盈利能力、
 * 经营稳定性、行业景气度、担保充足度、历史履约六个维度打分（满分100），并与上期评分对比。</p>
 * <p>实际图表类型：{@link JChartType#RADAR} 雷达图（双系列：本期评分 / 上期评分）。</p>
 *
 * <p>模板：{@code report/biz/23_risk_radar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\23_risk_radar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz23RiskRadarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz23RiskRadar() throws IOException {
        JOption option = new JOption();
        option.title().text("企业客户风险等级多维评估").subtext("各维度评分（满分100，分值越高越稳健）");
        option.tooltip().trigger(JTrigger.item);
        JRadar radar = new JRadar();
        radar.indicator(
                new JRadar.Indicator().name("偿债能力").max(100),
                new JRadar.Indicator().name("盈利能力").max(100),
                new JRadar.Indicator().name("经营稳定性").max(100),
                new JRadar.Indicator().name("行业景气度").max(100),
                new JRadar.Indicator().name("担保充足度").max(100),
                new JRadar.Indicator().name("历史履约").max(100)
        );
        option.radar(radar);
        // 双系列对比：本期评分与上期评分
        JRadarSeries current = new JRadarSeries();
        current.name("本期评分").type(JSeriesType.radar).data(86, 78, 88, 72, 90, 93);
        JRadarSeries last = new JRadarSeries();
        last.name("上期评分").type(JSeriesType.radar).data(80, 83, 82, 77, 86, 89);
        option.series(current, last);

        String svg = JChartRendererFactory.renderChart(JChartType.RADAR, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/23_risk_radar.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/23_risk_radar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
