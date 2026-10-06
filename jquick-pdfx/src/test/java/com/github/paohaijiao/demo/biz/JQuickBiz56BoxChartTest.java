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
import com.github.paohaijiao.series.JBoxplot;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务场景 Demo 56：产线质量指标分布盒须图（质量管理）
 *
 * <p>业务场景：质量管理部门对多条产线的关键尺寸进行统计过程控制（SPC），
 * 用盒须图（箱线图）展示各产线数据的最小值、下四分位数（Q1）、中位数、
 * 上四分位数（Q3）、最大值以及离群值，用于评估过程稳定性。</p>
 * <p>实际图表类型：{@link JChartType#BOXPLOT} 盒须图。</p>
 *
 * <p>模板：{@code report/biz/56_boxchart.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\56_boxchart.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt 的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz56BoxChartTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz56BoxChart() throws IOException {
        // 四条产线的关键尺寸样本统计：[min, Q1, median, Q3, max]
        // 产线 A：稳定
        // 产线 B：含离群值（追加第 6 个值为离群点）
        // 产线 C：中位数偏低
        // 产线 D：稳定
        List<Object[]> boxData = new ArrayList<>();
        boxData.add(new Object[]{12.1, 12.4, 12.5, 12.6, 12.9});
        boxData.add(new Object[]{12.0, 12.3, 12.5, 12.7, 13.0, 13.6});
        boxData.add(new Object[]{11.8, 12.0, 12.1, 12.3, 12.6});
        boxData.add(new Object[]{12.2, 12.4, 12.5, 12.6, 12.8});

        JBoxplot boxplot = new JBoxplot("产线关键尺寸分布");
        boxplot.setData(boxData);

        JOption option = new JOption();
        option.title("各产线关键尺寸分布盒须图", "样本量：每条产线 200 件 | 单位：mm");
        option.xAxis(new JCategoryAxis().data("产线 A", "产线 B", "产线 C", "产线 D"));
        option.series(boxplot);

        String svg = JChartRendererFactory.renderChart(JChartType.BOXPLOT, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/56_boxchart.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/56_boxchart.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
