
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
import java.util.*;


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
        List<Map<String, Object>> data = new ArrayList<>();
        Random random = new Random(42); // 固定种子以便重现
        String[] dates = {"01-01", "01-02", "01-03", "01-04", "01-05", "01-06", "01-07", "01-08", "01-09", "01-10", "01-11", "01-12", "01-13", "01-14", "01-15"};
        for (int i = 0; i < dates.length; i++) {
            int aqi = 20 + random.nextInt(180); // AQI 20-200
            double pm25 = 10 + random.nextDouble() * 150; // PM2.5 10-160
            String category;
            if (aqi <= 50) category = "优";
            else if (aqi <= 100) category = "良";
            else if (aqi <= 150) category = "轻度污染";
            else if (aqi <= 200) category = "中度污染";
            else category = "重度污染";
            String name = String.format("日期:%s, AQI:%d, PM2.5:%.1f", dates[i], aqi, pm25);
            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("x", dates[i]);
            dataPoint.put("y", aqi);
            dataPoint.put("size", pm25);
            dataPoint.put("category", category);
            dataPoint.put("name", name);
            data.add(dataPoint);
        }
        series.data(data.toArray());
        option.series(series);
        JBubbleChartRenderer renderer = new JBubbleChartRenderer();
        renderer.render(option, "d://test//bubble.svg");
    }
}
