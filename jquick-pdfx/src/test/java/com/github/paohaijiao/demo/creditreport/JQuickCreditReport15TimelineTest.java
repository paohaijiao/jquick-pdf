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
import com.github.paohaijiao.combol.JTimeLineData;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 统一信用报告 —— 风格 15：时间线（棕褐）
 *
 * <p>以 {@code report/credit_report_15_timeline.txt} 为模板，把时间线渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\15_timeline.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport15TimelineTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report15Timeline() throws IOException {
        JTimeLineData data = new JTimeLineData();
        List<JTimeLineData.FlowNode> nodes = new ArrayList<>();
        nodes.add(new JTimeLineData.FlowNode("2018年", "企业成立|注册资本1000万元", new Color(78, 52, 46), new Color(78, 52, 46)));
        nodes.add(new JTimeLineData.FlowNode("2020年", "首轮融资|营收突破8000万元", new Color(109, 76, 65), new Color(109, 76, 65)));
        nodes.add(new JTimeLineData.FlowNode("2021年", "高新技术企业认定|研发投入占比12%", new Color(141, 110, 99), new Color(141, 110, 99)));
        nodes.add(new JTimeLineData.FlowNode("2022年", "专精特新小巨人|客户续约率92%", new Color(161, 136, 127), new Color(161, 136, 127)));
        nodes.add(new JTimeLineData.FlowNode("2023年", "信用评级AAA|营收25680万元", new Color(188, 170, 164), new Color(188, 170, 164)));
        data.setNodes(nodes);
        data.setMainTitle("CREDIT MILESTONE TIMELINE");
        data.setSubtitle("2018-2023 信用建设关键里程碑");
        data.setFooterText("数据来源：企业信用评估中心 | 更新日期：2024年1月");
        data.setHeight(1300);
        data.setBoxWidth(200);
        data.setBoxHeight(90);
        data.setStartX(100);
        data.setEndX(100);
        JOption option = new JOption();
        option.setData(data);

        String svg = JChartRendererFactory.renderChart(JChartType.TimeLine, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/15_timeline.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_15_timeline.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
