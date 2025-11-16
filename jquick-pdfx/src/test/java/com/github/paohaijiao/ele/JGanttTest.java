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
package com.github.paohaijiao.ele;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.gant.JGanttOption;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JGanttTest {


    private JOption createData(){
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
            JOption jOption = new JOption();
            jOption.setGanttOption(option);
            return jOption;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer=new JGraphContainer();
        graphContainer.setType(JChartType.Gantt);
        graphContainer.setOption(createData());
        JGraphConfig graphConfig=new JGraphConfig();
        graphConfig.put("svg",graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        JReader fileReader = new JReSourceFileReader("sample/svg2.txt");
        JAdaptor context = new JAdaptor(fileReader);
        JQuickPdfXExecutor executor = new JQuickPdfXExecutor(config);
        executor.execute(context.getRuleContent());
    }
}
