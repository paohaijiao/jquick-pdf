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
import com.github.paohaijiao.calendar.JCalendarOption;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JCalendarTest {

    private JOption createData(){
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
        return option;
    }

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer=new JGraphContainer();
        graphContainer.setType(JChartType.Calendar);
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
