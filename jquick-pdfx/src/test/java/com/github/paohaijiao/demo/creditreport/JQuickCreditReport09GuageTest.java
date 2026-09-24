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
 * 统一信用报告 —— 风格 09：仪表盘（石墨灰）
 *
 * <p>以 {@code report/credit_report_09_guage.txt} 为模板，把仪表盘渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\09_guage.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport09GuageTest {

    public static final String path = JQuickConstant.path;

    @Test
    @SuppressWarnings("deprecation")
    public void report09Guage() throws IOException {
        GuageConfig scoreConfig = GuageConfig.builder()
                .score(86)
                .pointerColor(new Color(69, 90, 100))
                .backgroundColor(new Color(240, 242, 245))
                .title("CREDIT SCORE")
                .build();
        JGuageOption guageOption = JGuageOption.builder().scoreMeter(scoreConfig).build();
        JOption option = new JOption();
        option.setGuageOption(guageOption);

        String svg = JChartRendererFactory.renderChart(JChartType.Guage, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/09_guage.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_09_guage.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
