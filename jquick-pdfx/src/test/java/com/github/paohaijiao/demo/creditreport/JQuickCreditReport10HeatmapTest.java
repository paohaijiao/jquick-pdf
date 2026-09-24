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
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.series.JHeatmap;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 统一信用报告 —— 风格 10：热力图（深紫）
 *
 * <p>以 {@code report/credit_report_10_heatmap.txt} 为模板，把热力图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\10_heatmap.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport10HeatmapTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report10Heatmap() throws IOException {
        JOption option = new JOption();
        option.title("月度经营热力图", "各业务线月度营收（万元）");
        option.xAxis(new JCategoryAxis().data("1月", "2月", "3月", "4月", "5月", "6月"));
        option.yAxis(new JCategoryAxis().data("软件产品", "技术服务", "云服务", "其他业务"));
        JHeatmap heatmap = new JHeatmap();
        heatmap.data(
                new Object[]{0, 0, 860}, new Object[]{0, 1, 1020}, new Object[]{0, 2, 1180},
                new Object[]{0, 3, 1340}, new Object[]{0, 4, 1520}, new Object[]{0, 5, 1680},
                new Object[]{1, 0, 520}, new Object[]{1, 1, 640}, new Object[]{1, 2, 780},
                new Object[]{1, 3, 890}, new Object[]{1, 4, 1010}, new Object[]{1, 5, 1160},
                new Object[]{2, 0, 380}, new Object[]{2, 1, 460}, new Object[]{2, 2, 540},
                new Object[]{2, 3, 620}, new Object[]{2, 4, 710}, new Object[]{2, 5, 820},
                new Object[]{3, 0, 260}, new Object[]{3, 1, 310}, new Object[]{3, 2, 360},
                new Object[]{3, 3, 420}, new Object[]{3, 4, 470}, new Object[]{3, 5, 540}
        );
        option.series(heatmap);

        String svg = JChartRendererFactory.renderChart(JChartType.HEATMAP, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/10_heatmap.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_10_heatmap.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
