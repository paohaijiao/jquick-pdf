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
import com.paohaijiao.data.axis.JValueAxis;
import com.paohaijiao.data.code.JTrigger;
import com.paohaijiao.data.data.JData;
import com.paohaijiao.data.series.JScatter;
import com.paohaijiao.echart.scatter.JScatterChartsRenderer;
import org.junit.Test;

import java.io.IOException;


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
public class ScatterCharTest {
    private static JData[] createScatterData() {
        return new JData[]{
                new JData().value(new Double[]{10.0, 8.04}),
                new JData().value(new Double[]{8.07, 6.95}),
                new JData().value(new Double[]{13.0, 7.58}),
                new JData().value(new Double[]{9.05, 8.81}),
                new JData().value(new Double[]{11.0, 8.33}),
                new JData().value(new Double[]{14.0, 7.66}),
                new JData().value(new Double[]{13.4, 6.81}),
                new JData().value(new Double[]{10.0, 6.33}),
                new JData().value(new Double[]{14.0, 8.96}),
                new JData().value(new Double[]{12.5, 6.82}),
                new JData().value(new Double[]{9.15, 7.2}),
                new JData().value(new Double[]{11.5, 7.2}),
                new JData().value(new Double[]{3.03, 4.23}),
                new JData().value(new Double[]{12.2, 7.83}),
                new JData().value(new Double[]{2.02, 4.47}),
                new JData().value(new Double[]{1.05, 3.33}),
                new JData().value(new Double[]{4.05, 4.96}),
                new JData().value(new Double[]{6.03, 7.24}),
                new JData().value(new Double[]{12.0, 6.26}),
                new JData().value(new Double[]{12.0, 8.84}),
                new JData().value(new Double[]{7.08, 5.82}),
                new JData().value(new Double[]{5.02, 5.68})
        };
    }
    @Test
    public void testBarChar1() throws IOException {
        JOption option = new JOption();
        option.title().text("散点图示例");
        option.tooltip().trigger(JTrigger.axis);
        option.xAxis(new JValueAxis().scale(true));
        option.yAxis(new JValueAxis().scale(true));
        JScatter scatter = new JScatter();
        scatter.symbolSize(20)
                .data(createScatterData());
        option.series(scatter);


        JScatterChartsRenderer renderer=new JScatterChartsRenderer();
        renderer.render(option, "d://test//final-scatter.svg");
        System.out.println("雷达图SVG已生成: radar_chart.svg");
        System.out.println("散点图SVG已生成: final-scatter.svg");
    }

}
