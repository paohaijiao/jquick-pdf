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
import com.github.paohaijiao.combol.JHorizontalMultiBarChartData;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 业务场景 Demo 35：人员在岗率对比（政务/人事管理）
 *
 * <p>业务场景：某执法系统各下属单位上报当日应到人数与实到人数，人事部门据此统计在岗率，用于日常勤务调度与值守保障。</p>
 * <p>实际图表类型：{@link JChartType#MutipleHorizontalBar} 多重横向条形图，每个单位并列「应到人数 / 实到人数」两个系列。</p>
 *
 * <p>模板：{@code report/biz/35_staff_attendance.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\35_staff_attendance.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>} 只声明宽度、不声明高度，
 * 宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz35StaffAttendanceTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz35StaffAttendance() throws IOException {
        JHorizontalMultiBarChartData chartData = new JHorizontalMultiBarChartData();
        chartData.setTitleText("各下属单位人员在岗率对比");
        chartData.setSubtitleText("应到人数与实到人数（2024年9月，单位：人）");
        chartData.setXAxisTitle("在岗率（%）");
        chartData.setValueWithPercent(false);
        chartData.setShowDataLabels(true);
        chartData.setLegendAtTop(true);
        chartData.setGroupSpacingRatio(0.15);
        chartData.setBarSpacingRatio(0.2);
        chartData.addCategory("第一分局");
        chartData.addCategory("第二分局");
        chartData.addCategory("第三分局");
        chartData.addCategory("第四分局");
        chartData.addCategory("第五分局");
        // 两个系列分别表示应到人数与实到人数，颜色沿用政务系统低饱和主色
        chartData.addSeries("应到人数", Arrays.asList(186.0, 152.0, 134.0, 118.0, 96.0), new Color(0, 105, 92));
        chartData.addSeries("实到人数", Arrays.asList(178.0, 141.0, 129.0, 106.0, 91.0), new Color(77, 150, 140));
        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.MutipleHorizontalBar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/35_staff_attendance.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/35_staff_attendance.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
