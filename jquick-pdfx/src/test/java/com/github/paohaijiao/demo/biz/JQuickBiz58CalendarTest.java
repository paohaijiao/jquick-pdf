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
package com.github.paohaijiao.demo.biz;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.calendar.JCalendarOption;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 业务场景 Demo 58：销售出单日历热力图（销售运营）
 *
 * <p>业务场景：销售运营部门以日历热力图形式展示全年每日订单金额，颜色越深代表
 * 当日订单金额越高，便于识别销售高峰与低谷。</p>
 * <p>实际图表类型：{@link JChartType#Calendar} 日历热力图。</p>
 *
 * <p>模板：{@code report/biz/58_calendar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\58_calendar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz58CalendarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz58Calendar() throws IOException {
        // 构造 2024 年全年每日订单金额数据
        Map<LocalDate, Integer> data = new HashMap<>();
        Random random = new Random(88);
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 12, 31);
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            int base = 30 + random.nextInt(120);
            // 季度末冲刺：3/6/9/12 月最后 5 天金额放大
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();
            if ((month == 3 || month == 6 || month == 9 || month == 12) && day >= 26) {
                base += 100 + random.nextInt(80);
            }
            // 节假日低谷：春节（2 月 10-17）、国庆（10 月 1-7）
            if ((month == 2 && day >= 10 && day <= 17) || (month == 10 && day >= 1 && day <= 7)) {
                base = Math.max(5, base / 3);
            }
            data.put(date, base);
        }

        JCalendarOption calendarOption = new JCalendarOption(
                "2024 年销售出单日历热力图", "颜色越深代表当日订单金额越高（单位：万元）",
                2024, data,
                new Color(225, 215, 240),  // 起始色（浅紫）
                new Color(74, 20, 140),   // 结束色（深紫）
                new Color(220, 220, 220), // 网格色
                new Color(51, 51, 51),    // 文字色
                14, 6                     // cellSize, margin
        );

        JOption option = new JOption();
        option.setJCalendarOption(calendarOption);

        String svg = JChartRendererFactory.renderChart(JChartType.Calendar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/58_calendar.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/58_calendar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
