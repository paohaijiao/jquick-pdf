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
import com.paohaijiao.data.JOption;
import com.paohaijiao.data.JRadar;
import com.paohaijiao.data.code.JSeriesType;
import com.paohaijiao.data.code.JTrigger;
import com.paohaijiao.data.series.JRadarSeries;
import com.paohaijiao.data.style.JTextStyle;
import com.paohaijiao.echart.pie.JPieChartsRenderer;
import com.paohaijiao.echart.radar.JRadarChartsRenderer;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class RadarCharTest {
    @Test
    public void testBarChar1() throws IOException, ParserConfigurationException {
        // 创建图表选项
        JOption option = new JOption();
        option.title().text("雷达图示例")
                .subtext("预算 vs 开销对比")
                .left("center")
                .textStyle(new JTextStyle().color("#333"));

        // 设置提示框
        option.tooltip().trigger(JTrigger.item);

        // 设置雷达图指标
        JRadar radar = new JRadar();
        radar.indicator(
                new JRadar.Indicator().name("销售").max(6500),
                new JRadar.Indicator().name("管理").max(16000),
                new JRadar.Indicator().name("信息技术").max(30000),
                new JRadar.Indicator().name("客服").max(38000),
                new JRadar.Indicator().name("研发").max(52000),
                new JRadar.Indicator().name("市场").max(25000)
        );
        option.radar(radar);

        // 添加雷达图系列数据
        JRadarSeries budgetSeries = new JRadarSeries();
        budgetSeries.name("预算")
                .type(JSeriesType.radar)
                .data(4300, 10000, 28000, 35000, 50000, 19000);

        JRadarSeries actualSeries = new JRadarSeries();
        actualSeries.name("实际开销")
                .type(JSeriesType.radar)
                .data(5000, 14000, 28000, 31000, 42000, 21000);

        option.series(budgetSeries, actualSeries);
        System.out.println(option);

        JRadarChartsRenderer renderer=new JRadarChartsRenderer();
        renderer.render(option, "d://test//radar_chart.svg");
        System.out.println("雷达图SVG已生成: radar_chart.svg");
    }
}
