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
import com.paohaijiao.data.axis.JValueAxis;
import com.paohaijiao.data.code.JTrigger;
import com.paohaijiao.data.series.JLine;
import com.paohaijiao.echart.line.JLineChartsRenderer;
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
public class LineCharTest {
    @Test
    public void testBarChar1() throws IOException {
        JOption option = new JOption();
        option.title().text("销售数据折线图");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("1月", "2月", "3月", "4月", "5月", "6月", "7月");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        JLine line = new JLine();
        line.name("销售额").data(120, 132, 101, 134, 90, 230, 210);
        option.series(line);
        JLineChartsRenderer renderer=new JLineChartsRenderer();
        renderer.render(option, "D://test//line_chart.svg");
    }

}
