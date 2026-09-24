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
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.guage.GuageConfig;
import com.github.paohaijiao.guage.JGuageOption;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 业务场景 Demo 44：错误率监控仪表盘（系统运维 / 接口 SLA）
 *
 * <p>业务场景：核心业务系统对接口可用性进行 SLA 监控，仪表盘分值表示近 30 天接口健康度
 * 评分（0~100，由可用率与错误率综合折算），用于服务等级考核与故障复盘，主色采用深紫 #4A148C。</p>
 * <p>实际图表类型：{@link JChartType#Guage} 仪表盘（用于展示 SLA 健康度评分）。</p>
 *
 * <p>模板：{@code report/biz/44_error_rate.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\44_error_rate.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 400pt（宽 × 高）的方形比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz44ErrorRateTest {

    public static final String path = JQuickConstant.path;

    @Test
    @SuppressWarnings("deprecation")
    public void biz44ErrorRate() throws IOException {
        GuageConfig scoreConfig = GuageConfig.builder()
                .score(93)
                .pointerColor(new Color(74, 20, 140))
                .backgroundColor(new Color(243, 240, 247))
                .title("SLA HEALTH")
                .build();
        JGuageOption guageOption = JGuageOption.builder().scoreMeter(scoreConfig).build();
        JOption option = new JOption();
        option.setGuageOption(guageOption);

        String svg = JChartRendererFactory.renderChart(JChartType.Guage, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/44_error_rate.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/44_error_rate.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
