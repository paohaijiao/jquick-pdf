
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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.bubble.CategoryAxis;
import com.github.paohaijiao.bubble.JBubbleChartRenderer;
import com.github.paohaijiao.bubble.ScatterSeries;
import com.github.paohaijiao.bubble.ValueAxis;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @ClassName BarCharTest
 * @Description: Description
 * @Author: gou
 * @CreateDate: 2025/6/13 6:52
 * @UpdateUser: UpdateUser
 * @UpdateDate: 2025/6/13 6:52
 * @UpdateRemark:
 * @Version: 1.0
 */
public class BubbleTest {


    @Test
    public void testBarChar1() throws IOException {
        JTitle title = new JTitle();
        title.setText("空气质量指数 (AQI) 监测气泡图");
        title.setSubtext("图表说明：本气泡图展示了空气质量指数(AQI)的时间变化趋势。X轴表示日期，Y轴表示AQI数值，气泡大小反映PM2.5浓度，气泡颜色表示AQI等级。");
//        title.set
        JOption option = new JOption()
                .title(title)
                .legend("优", "良", "轻度污染", "中度污染", "重度污染")
                .xAxis(new CategoryAxis().name("日期"))
                .yAxis(new ValueAxis().name("AQI数值"));
        ScatterSeries series = new ScatterSeries("空气质量监测");
        List<Map<String, Object>> seriesData = BubbleDataCreator.createAQISampleData();
        series.data(seriesData.toArray());
        option.series(series);
        JBubbleChartRenderer renderer = new JBubbleChartRenderer();
        renderer.render(option, "aqi_bubble_chart.svg");
        System.out.println("AQI气泡图已生成: aqi_bubble_chart.svg");
        renderer.render(option, "d://test//bubble-demo.svg");
    }
}
