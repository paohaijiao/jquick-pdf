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
import com.github.paohaijiao.combol.JLineScatterChartData;
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
import java.util.List;

/**
 * 业务场景 Demo 61：城市综合发展评估折线雷达图（发改研究）
 *
 * <p>业务场景：发改研究院以折线展示季度 GDP 走势，以雷达散点展示创新、协调、
 * 绿色、开放、共享五维发展指数得分，综合评估城市发展质量。</p>
 * <p>实际图表类型：{@link JChartType#LineRadar} 折线雷达混合图。</p>
 *
 * <p>模板：{@code report/biz/61_lineRadar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\61_lineRadar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz61LineRadarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz61LineRadar() throws IOException {
        List<String> categories = Arrays.asList("创新", "协调", "绿色", "开放", "共享", "安全", "包容", "高效");
        // 折线：各季度 GDP 增速（%）
        List<Double> lineValues = Arrays.asList(5.2, 5.5, 5.8, 5.6, 6.0, 6.2, 5.9, 6.3);
        // 雷达散点：各维度发展指数得分（满分 100）
        List<Double> scatterValues = Arrays.asList(78.0, 72.0, 88.0, 65.0, 80.0, 70.0, 76.0, 74.0);

        JLineScatterChartData data = new JLineScatterChartData();
        data.setTitleText("城市综合发展评估折线雷达图");
        data.setSubtitleText("折线：季度 GDP 增速（%）　雷达散点：八维发展指数得分");
        data.setFooterText("数据来源：统计公报与专项评估");
        data.setCategories(categories);
        data.setLineValues(lineValues);
        data.setScatterValues(scatterValues);
        data.setLineSeriesName("GDP 增速");
        data.setScatterSeriesName("发展指数");
        data.setMaxValue(100);
        data.setGridCount(5);
        data.setShowDataLabels(true);

        JOption option = new JOption();
        option.setData(data);

        String svg = JChartRendererFactory.renderChart(JChartType.LineRadar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/61_lineRadar.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/61_lineRadar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
