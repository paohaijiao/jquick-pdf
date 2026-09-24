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
 * 业务场景 Demo 36：告警数量周趋势时间线（应急/运维平台）
 *
 * <p>业务场景：应急指挥平台按周汇总去重后的告警数量，展示第1周至第8周的趋势与周环比，用于运维值班复盘。</p>
 * <p>实际图表类型：{@link JChartType#TimeLine} 时间线，每个节点标注当周告警数量与环比变化，
 * 节点颜色按主色由深到浅依次过渡。</p>
 *
 * <p>模板：{@code report/biz/36_alert_week_timeline.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\36_alert_week_timeline.pdf}。</p>
 *
 * <p>图表尺寸说明：时间线渲染器画布为横向宽图（1300×500 比例）；模板中 {@code <svg>} 只声明宽度、
 * 不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz36AlertWeekTimelineTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz36AlertWeekTimeline() throws IOException {
        JTimeLineData data = new JTimeLineData();
        List<JTimeLineData.FlowNode> nodes = new ArrayList<>();
        // 8 个节点，颜色按 #BF360C 主色系由深到浅过渡；节点文字用「|」分隔成两行
        nodes.add(new JTimeLineData.FlowNode("第1周", "告警 328 起|环比 +6.5%", new Color(191, 54, 12), new Color(191, 54, 12)));
        nodes.add(new JTimeLineData.FlowNode("第2周", "告警 296 起|环比 -9.8%", new Color(198, 76, 40), new Color(198, 76, 40)));
        nodes.add(new JTimeLineData.FlowNode("第3周", "告警 341 起|环比 +15.2%", new Color(216, 112, 74), new Color(216, 112, 74)));
        nodes.add(new JTimeLineData.FlowNode("第4周", "告警 305 起|环比 -10.6%", new Color(224, 140, 108), new Color(224, 140, 108)));
        nodes.add(new JTimeLineData.FlowNode("第5周", "告警 278 起|环比 -8.9%", new Color(232, 168, 142), new Color(232, 168, 142)));
        nodes.add(new JTimeLineData.FlowNode("第6周", "告警 352 起|环比 +26.6%", new Color(240, 196, 176), new Color(240, 196, 176)));
        nodes.add(new JTimeLineData.FlowNode("第7周", "告警 389 起|环比 +10.5%", new Color(246, 214, 200), new Color(246, 214, 200)));
        nodes.add(new JTimeLineData.FlowNode("第8周", "告警 334 起|环比 -14.1%", new Color(250, 228, 218), new Color(250, 228, 218)));
        data.setNodes(nodes);
        data.setMainTitle("ALERT WEEKLY TREND");
        data.setSubtitle("2024年第1-8周告警数量趋势");
        data.setFooterText("数据来源：应急指挥平台 | 统计口径：去重后告警数");
        data.setHeight(1300);
        data.setBoxWidth(200);
        data.setBoxHeight(90);
        data.setStartX(100);
        data.setEndX(100);
        JOption option = new JOption();
        option.setData(data);

        String svg = JChartRendererFactory.renderChart(JChartType.TimeLine, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/36_alert_week_timeline.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/36_alert_week_timeline.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
