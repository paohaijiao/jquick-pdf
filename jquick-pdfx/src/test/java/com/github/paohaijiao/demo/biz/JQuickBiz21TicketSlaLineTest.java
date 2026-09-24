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
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.combol.JMultiLineChartData;
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
import java.util.List;

/**
 * 业务场景 Demo 21：工单处理时效趋势（政务服务热线 / 企业工单系统）
 *
 * <p>业务场景：政务与企业工单系统按月统计受理、处理、办结及超期工单的处理时长，
 * 用于服务质量考核与流程优化，主色采用政务蓝 #2F5597。</p>
 * <p>实际图表类型：{@link JChartType#MultipleLine} 多重折线图（四条时长指标共用「小时」量纲，观察月度收敛趋势）。</p>
 *
 * <p>模板：{@code report/biz/21_ticket_sla_line.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\21_ticket_sla_line.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz21TicketSlaLineTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz21TicketSlaLine() throws IOException {
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        JMultiLineChartData chartData = new JMultiLineChartData();
        chartData.setXAxisLabels(months);
        chartData.setTitleText("2024年工单处理时效趋势");
        chartData.setSubtitleText("受理 / 处理 / 办结 / 超期时长月度对比");
        chartData.setYAxisTitle("时长（小时）");
        chartData.setFooterText("数据来源：工单管理系统 | 统计口径：自然月（单位：小时）");
        chartData.setGridCount(6);
        chartData.setShowDataLabels(false);
        chartData.setShowInnerPoint(true);
        chartData.setPointRadius(5);
        chartData.setInnerPointRadius(2);
        chartData.setChartAreaColor(new Color(248, 249, 250));
        chartData.setAxisColor(Color.BLACK);
        chartData.setGridColor(new Color(220, 220, 220));
        chartData.setTextColor(Color.BLACK);
        chartData.setFooterColor(new Color(128, 128, 128));
        chartData.setValueWithPercent(false);
        chartData.setAutoCalculateMax(true);
        chartData.setRotateXAxisLabels(false);
        // 平均受理时长：前端智能分派与自助填报推动逐月下降
        JMultiLineChartData.LineData lineA = new JMultiLineChartData.LineData();
        lineA.setName("平均受理时长");
        lineA.setLegendText("平均受理时长（小时）");
        lineA.setValues(Arrays.asList(2.4, 2.1, 1.9, 1.8, 1.7, 1.6, 1.6, 1.5, 1.4, 1.3, 1.2, 1.1));
        lineA.setLineColor(new Color(47, 85, 151));
        lineA.setLineWidth(2.5f);
        // 平均处理时长：部门流转与协同处置环节耗时
        JMultiLineChartData.LineData lineB = new JMultiLineChartData.LineData();
        lineB.setName("平均处理时长");
        lineB.setLegendText("平均处理时长（小时）");
        lineB.setValues(Arrays.asList(3.6, 3.4, 3.2, 3.0, 2.8, 2.7, 2.6, 2.4, 2.3, 2.1, 2.0, 1.8));
        lineB.setLineColor(new Color(91, 155, 213));
        lineB.setLineWidth(2.5f);
        // 平均办结时长：从受理到办结的端到端耗时（3月、8月出现小幅反弹）
        JMultiLineChartData.LineData lineC = new JMultiLineChartData.LineData();
        lineC.setName("平均办结时长");
        lineC.setLegendText("平均办结时长（小时）");
        lineC.setValues(Arrays.asList(6.2, 5.8, 5.9, 5.5, 5.1, 4.8, 4.6, 4.7, 4.3, 3.9, 3.5, 3.1));
        lineC.setLineColor(new Color(197, 90, 17));
        lineC.setLineWidth(2.5f);
        // 超期工单平均超期时长：长尾工单管控效果
        JMultiLineChartData.LineData lineD = new JMultiLineChartData.LineData();
        lineD.setName("超期工单平均超期时长");
        lineD.setLegendText("超期工单平均超期时长（小时）");
        lineD.setValues(Arrays.asList(9.8, 9.2, 8.6, 8.1, 7.6, 7.2, 6.8, 6.4, 6.0, 5.5, 5.1, 4.6));
        lineD.setLineColor(new Color(127, 127, 127));
        lineD.setLineWidth(2.5f);
        chartData.setLineDataList(Arrays.asList(lineA, lineB, lineC, lineD));
        // 设置完折线数据后必须刷新 Y 轴最大值
        chartData.updateMaxValues();
        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText("2024年工单处理时效趋势");
        title.setSubtext("受理 / 处理 / 办结 / 超期时长月度对比");
        option.setTitle(title);
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.MultipleLine, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/21_ticket_sla_line.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/21_ticket_sla_line.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
