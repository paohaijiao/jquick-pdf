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
import com.paohaijiao.data.axis.JCategoryAxis;
import com.paohaijiao.data.series.JHeatmap;
import com.paohaijiao.echart.heatMap.JHeatMapChartRenderer;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class HeatMapCharTest {
    @Test
    public void testBarChar1() throws IOException, ParserConfigurationException {
        JOption option = new JOption();
        option.title("2023年月度温度分布热力图");
        option.xAxis(new JCategoryAxis()
                .data("1月", "2月", "3月", "4月", "5月", "6月",
                        "7月", "8月", "9月", "10月", "11月", "12月"));
        option.yAxis(new JCategoryAxis()
                .data("凌晨(0-6)", "早晨(6-9)", "上午(9-12)",
                        "中午(12-14)", "下午(14-18)", "晚上(18-24)"));
        JHeatmap heatmap = new JHeatmap();
        heatmap.data(
                new Object[]{0, 0, -5.2}, new Object[]{0, 1, -3.8}, new Object[]{0, 2, 1.5},
                new Object[]{0, 3, 4.2}, new Object[]{0, 4, 2.8}, new Object[]{0, 5, -2.1},
                new Object[]{1, 0, -3.5}, new Object[]{1, 1, -1.2}, new Object[]{1, 2, 3.0},
                new Object[]{1, 3, 6.5}, new Object[]{1, 4, 4.2}, new Object[]{1, 5, 0.5},
                new Object[]{2, 0, 0.8}, new Object[]{2, 1, 3.5}, new Object[]{2, 2, 8.2},
                new Object[]{2, 3, 12.0}, new Object[]{2, 4, 9.5}, new Object[]{2, 5, 4.2},
                new Object[]{3, 0, 5.2}, new Object[]{3, 1, 8.0}, new Object[]{3, 2, 12.5},
                new Object[]{3, 3, 16.8}, new Object[]{3, 4, 14.2}, new Object[]{3, 5, 9.5},
                new Object[]{4, 0, 10.5}, new Object[]{4, 1, 13.2}, new Object[]{4, 2, 17.8},
                new Object[]{4, 3, 21.5}, new Object[]{4, 4, 19.0}, new Object[]{4, 5, 14.8},
                new Object[]{5, 0, 15.2}, new Object[]{5, 1, 18.5}, new Object[]{5, 2, 22.0},
                new Object[]{5, 3, 26.5}, new Object[]{5, 4, 24.2}, new Object[]{5, 5, 19.8},
                new Object[]{6, 0, 18.5}, new Object[]{6, 1, 22.0}, new Object[]{6, 2, 26.5},
                new Object[]{6, 3, 30.2}, new Object[]{6, 4, 28.5}, new Object[]{6, 5, 23.8},
                new Object[]{7, 0, 17.8}, new Object[]{7, 1, 21.5}, new Object[]{7, 2, 25.2},
                new Object[]{7, 3, 29.0}, new Object[]{7, 4, 27.5}, new Object[]{7, 5, 22.8},
                new Object[]{8, 0, 13.5}, new Object[]{8, 1, 16.2}, new Object[]{8, 2, 20.0},
                new Object[]{8, 3, 24.5}, new Object[]{8, 4, 22.0}, new Object[]{8, 5, 17.5},
                new Object[]{9, 0, 8.2}, new Object[]{9, 1, 11.5}, new Object[]{9, 2, 15.0},
                new Object[]{9, 3, 18.8}, new Object[]{9, 4, 16.5}, new Object[]{9, 5, 12.0},
                new Object[]{10, 0, 2.5}, new Object[]{10, 1, 5.0}, new Object[]{10, 2, 9.2},
                new Object[]{10, 3, 12.5}, new Object[]{10, 4, 10.0}, new Object[]{10, 5, 5.5},
                new Object[]{11, 0, -2.8}, new Object[]{11, 1, -0.5}, new Object[]{11, 2, 3.5},
                new Object[]{11, 3, 6.8}, new Object[]{11, 4, 4.2}, new Object[]{11, 5, 0.0}
        );


        option.series(heatmap);
        JHeatMapChartRenderer renderer=new JHeatMapChartRenderer();
        renderer.render(option,"d://test//heatmap.svg");
    }
}
