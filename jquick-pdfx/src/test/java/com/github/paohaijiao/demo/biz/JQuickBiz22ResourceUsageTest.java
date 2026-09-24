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
 * 业务场景 Demo 22：资源使用率监控（运维 / 政务云）
 *
 * <p>业务场景：政务云运维中心对计算节点集群的 CPU / 内存综合使用率进行监控，仪表盘分值
 * 表示当前综合使用率（0~100），用于容量水位预警与扩容决策，主色采用运维深灰蓝 #37474F。</p>
 * <p>实际图表类型：jquick-pdf 无进度图渲染器，<b>实际使用仪表盘 {@link JChartType#Guage} 替代进度图</b>。</p>
 *
 * <p>模板：{@code report/biz/22_resource_usage.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\22_resource_usage.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 400pt（宽 × 高）的方形比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz22ResourceUsageTest {

    public static final String path = JQuickConstant.path;

    @Test
    @SuppressWarnings("deprecation")
    public void biz22ResourceUsage() throws IOException {
        GuageConfig scoreConfig = GuageConfig.builder()
                .score(78)
                .pointerColor(new Color(55, 71, 79))
                .backgroundColor(new Color(236, 239, 241))
                .title("RESOURCE USAGE")
                .build();
        JGuageOption guageOption = JGuageOption.builder().scoreMeter(scoreConfig).build();
        JOption option = new JOption();
        option.setGuageOption(guageOption);

        String svg = JChartRendererFactory.renderChart(JChartType.Guage, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/22_resource_usage.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/22_resource_usage.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
