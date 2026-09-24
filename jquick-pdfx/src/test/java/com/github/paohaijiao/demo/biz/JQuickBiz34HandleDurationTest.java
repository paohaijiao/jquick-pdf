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
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.series.JBar;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 业务场景 Demo 34：业务办理时长分布（政务窗口服务）
 *
 * <p>业务场景：政务窗口按办理时长区间统计业务办件量，用于分析窗口服务效率与长尾事项。</p>
 * <p>实际图表类型：{@link JChartType#BAR} 柱状图。<b>jquick-pdf 无直方图（Histogram）渲染器，
 * 实际使用柱状图 BAR 替代直方图</b>：预先将时长分箱，以「时长区间」为类目、「办件量」为柱高呈现分布形态。</p>
 *
 * <p>模板：{@code report/biz/34_handle_duration.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\34_handle_duration.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz34HandleDurationTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz34HandleDuration() throws IOException {
        JOption option = new JOption();
        option.title().text("业务办理时长分布").subtext("按办理时长区间统计办件量（单位：件）");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        // 时长分箱作为类目，用柱状图替代直方图呈现分布
        xAxis.data("0-15分钟", "15-30分钟", "30-45分钟", "45-60分钟", "60-90分钟", "90-120分钟", "120分钟以上");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        JBar count = new JBar();
        count.name("办件量").data(4120, 8360, 6240, 3180, 1520, 640, 210);
        option.series(count);

        String svg = JChartRendererFactory.renderChart(JChartType.BAR, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/34_handle_duration.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/34_handle_duration.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
