
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
import com.paohaijiao.echart.sunburst.JSunburstChart;
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
public class SunBirdTest {

    @Test
    public void testBarChar1() throws IOException {
        JOption option = new JOption();
        option.title().text("Sunburst Chart");
        JSunburstChart chart = new JSunburstChart();
        // 渲染到文件
        chart.render(option, "d://test//sunburst_chart.svg");

    }

}
