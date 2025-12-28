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
import com.github.paohaijiao.gant.JGanttOption;
import com.github.paohaijiao.gantt.JGanttChartRenderer;
import com.github.paohaijiao.provider.JChartRenderer;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/16
 */
public class JGanttTest {
    @Test
    public void testBarChar1() throws IOException, ParserConfigurationException {
        try {
            JGanttOption option = new JGanttOption();
            option.setTitle(new JGanttOption.Title("Gantt of Airport Flight", "航班调度甘特图"));
            option.setFlightData(Arrays.asList(
                    new JGanttOption.FlightData("Y3683", "681", "X", 21, 0, 360, 0, 0.7),
                    new JGanttOption.FlightData("EKXAD", "682I", "W", 21, 0, 360, 1, 0.7),
                    new JGanttOption.FlightData("Y4682", "682O", "W", 21, 0, 360, 2, 0.7),
                    new JGanttOption.FlightData("Y4393", "682", "X", 21, 0, 360, 3, 0.7),
                    new JGanttOption.FlightData("Y2238", "683", "X", 21, 0, 360, 4, 0.7),
                    new JGanttOption.FlightData("Y8192", "684", "W", 21, 0, 240, 5, 0.7),
                    new JGanttOption.FlightData("Y3887", "685", "X", 21, 0, 360, 6, 0.7),
                    new JGanttOption.FlightData("Y3086", "690", "X", 21, 0, 360, 7, 0.7),
                    new JGanttOption.FlightData("Y7421", "691", "X", 21, 0, 120, 8, 0.7),
                    new JGanttOption.FlightData("Y4619", "692", "X", 21, 0, 300, 9, 0.7)
            ));
            option.setChartStyle(new JGanttOption.ChartStyle(
                    Color.WHITE,
                    new Color(146, 154, 186),
                    new Color(54, 140, 108),
                    new Color(80, 112, 221),
                    new Color(221, 179, 11),
                    new Font("微软雅黑", Font.BOLD, 18),
                    new Font("微软雅黑", Font.PLAIN, 12),
                    872,
                    282
            ));
            option.setTimeRange(new JGanttOption.TimeRange(21, 3, new String[]{"21:00", "22:00", "23:00", "00:00", "01:00", "02:00", "03:00"}));
            JChartRenderer renderer = new JGanttChartRenderer();
            JOption jOption = new JOption();
            jOption.setGanttOption(option);
            renderer.render(jOption, "d://test//gantt.svg");
            System.out.println("工程化后的SVG文件已生成: airport_gantt_engineered.svg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
