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
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.bar.JBarChartsRenderer;
import com.github.paohaijiao.calendar.JCalendarChartRenderer;
import com.github.paohaijiao.calendar.JCalendarOption;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.provider.JChartRenderer;
import com.github.paohaijiao.series.JBar;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
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
public class CalendarTest {
    @Test
    public void testBarChar1() throws IOException {
        Map<LocalDate, Integer> data = new HashMap<>();
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        for (int i = 0; i < 365; i++) {
            LocalDate date = startDate.plusDays(i);
            int value = (int) (Math.random() * 15);
            data.put(date, value);
        }
        JOption option=new JOption();
        JCalendarOption calendarOption = new JCalendarOption("2024年活动日历", "类似GitHub贡献图", 2024, data,
                new Color(235, 237, 240),
                new Color(32, 125, 222),
                new Color(232, 235, 240),
                new Color(84, 85, 90),
                20,
                80
        );
        option.setJCalendarOption(calendarOption);
        JChartRenderer renderer = new JCalendarChartRenderer();
        renderer.render(option, "d://test//calendar-2024.svg");
        System.out.println("日历图表已生成: calendar-2024.svg");
        String svgContent = renderer.renderToString(option);
        System.out.println("SVG内容长度: " + svgContent.length());
    }
}
