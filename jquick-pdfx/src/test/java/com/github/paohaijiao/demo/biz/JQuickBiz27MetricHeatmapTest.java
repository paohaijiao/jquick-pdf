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
import com.github.paohaijiao.series.JHeatmap;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 业务场景 Demo 27：企业运营业务指标热力矩阵（企业运营）
 *
 * <p>业务场景：企业运营管理系统按"业务线 × 月份"汇总营业收入，通过颜色深浅识别各业务线的
 * 淡旺季规律，为资源投放与营销排期提供依据。</p>
 * <p>实际图表类型：{@link JChartType#HEATMAP} 热力图（矩阵单元格颜色越深表示营收越高）。</p>
 *
 * <p>模板：{@code report/biz/27_metric_heatmap.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\27_metric_heatmap.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 400pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz27MetricHeatmapTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz27MetricHeatmap() throws IOException {
        JOption option = new JOption();
        option.title("业务指标热力矩阵", "各业务线月度营业收入（万元）");
        // xAxis 为列（月份），yAxis 为行（业务线）
        option.xAxis(new JCategoryAxis().data("1月", "2月", "3月", "4月", "5月", "6月"));
        option.yAxis(new JCategoryAxis().data("零售金融", "公司金融", "财富管理", "支付结算"));
        JHeatmap heatmap = new JHeatmap();
        // 每个元素为 Object[]{行索引(yAxis), 列索引(xAxis), 数值}，覆盖 4 行 × 6 列 = 24 个数据点
        heatmap.data(
                new Object[]{0, 0, 1860}, new Object[]{0, 1, 1420}, new Object[]{0, 2, 2140},
                new Object[]{0, 3, 2280}, new Object[]{0, 4, 2360}, new Object[]{0, 5, 2520},
                new Object[]{1, 0, 1240}, new Object[]{1, 1, 980}, new Object[]{1, 2, 1680},
                new Object[]{1, 3, 1820}, new Object[]{1, 4, 1940}, new Object[]{1, 5, 2160},
                new Object[]{2, 0, 860}, new Object[]{2, 1, 1120}, new Object[]{2, 2, 940},
                new Object[]{2, 3, 880}, new Object[]{2, 4, 910}, new Object[]{2, 5, 1020},
                new Object[]{3, 0, 2380}, new Object[]{3, 1, 1960}, new Object[]{3, 2, 2460},
                new Object[]{3, 3, 2580}, new Object[]{3, 4, 2720}, new Object[]{3, 5, 2860}
        );
        option.series(heatmap);

        String svg = JChartRendererFactory.renderChart(JChartType.HEATMAP, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/27_metric_heatmap.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/27_metric_heatmap.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
