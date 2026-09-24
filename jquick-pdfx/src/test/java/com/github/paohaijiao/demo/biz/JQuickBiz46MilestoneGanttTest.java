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
import com.github.paohaijiao.gant.JGanttOption;
import org.junit.Test;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 业务场景 Demo 46：重点项目里程碑进度甘特图（项目监管平台）
 *
 * <p>业务场景：市重点项目建设监管平台展示城东新区综合交通枢纽项目从可研批复到竣工验收的里程碑排期与进度偏差。</p>
 * <p>实际图表类型：{@link JChartType#Gantt} 甘特图（横向宽图，横轴按周，纵轴为里程碑行）。</p>
 *
 * <p>模板：{@code report/biz/46_milestone_gantt.txt}；甘特图以 SVG 文本形式绑定到模板的 {@code ${svg}} 占位符，
 * 输出到 {@code D:\test\biz\46_milestone_gantt.pdf}。</p>
 *
 * <p>图表尺寸说明：甘特图渲染器画布固定 872pt × 282pt（横向宽图）；横轴时间刻度共 7 个（第1周—第7周），
 * 每个刻度间隔 60 分钟单位即代表 1 周，任务条按「起始周 + 持续周数」换算。模板中 {@code <svg>} 只声明宽度、
 * 不声明高度，宽度被页面自动收窄时高度按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz46MilestoneGanttTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz46MilestoneGantt() throws IOException {
        JGanttOption option = new JGanttOption();
        option.setTitle(new JGanttOption.Title("Gantt of Key Project Milestones", "重点项目里程碑进度甘特图"));
        // FlightData 参数：任务编号、任务名称（行标签）、类型标记（X/W）、起始周、起始分钟、持续分钟、行序号、实际完成比例
        option.setFlightData(Arrays.asList(
                new JGanttOption.FlightData("可研批复", "可研", "X", 1, 0, 60, 0, 1.00),
                new JGanttOption.FlightData("初步设计", "初设", "W", 2, 0, 60, 1, 0.92),
                new JGanttOption.FlightData("施工图审查", "图审", "X", 3, 0, 60, 2, 0.80),
                new JGanttOption.FlightData("招投标", "招标", "W", 3, 0, 60, 3, 0.65),
                new JGanttOption.FlightData("开工建设", "开工", "X", 4, 0, 60, 4, 0.55),
                new JGanttOption.FlightData("主体封顶", "封顶", "X", 5, 0, 60, 5, 0.38),
                new JGanttOption.FlightData("竣工验收", "验收", "W", 6, 0, 60, 6, 0.20)
        ));
        // ChartStyle 参数：背景色、坐标轴色、行标签色、计划条色、实际条色、标题字体、标签字体、宽、高
        option.setChartStyle(new JGanttOption.ChartStyle(
                Color.WHITE,
                new Color(176, 190, 197),
                new Color(55, 71, 79),
                new Color(120, 144, 156),
                new Color(224, 164, 64),
                new Font("微软雅黑", Font.BOLD, 18),
                new Font("微软雅黑", Font.PLAIN, 12),
                872,
                282
        ));
        // TimeRange 参数：起始周、结束周、时间刻度标签（7 个刻度 = 6 个区间，每区间代表 1 周）
        option.setTimeRange(new JGanttOption.TimeRange(1, 7,
                new String[]{"第1周", "第2周", "第3周", "第4周", "第5周", "第6周", "第7周"}));

        JOption jOption = new JOption();
        jOption.setGanttOption(option);
        String svg = JChartRendererFactory.renderChart(JChartType.Gantt, jOption);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/46_milestone_gantt.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/46_milestone_gantt.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
