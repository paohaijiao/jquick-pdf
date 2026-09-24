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
import com.github.paohaijiao.bubble.CategoryAxis;
import com.github.paohaijiao.bubble.ScatterSeries;
import com.github.paohaijiao.bubble.ValueAxis;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务场景 Demo 45：存量业务分层（资产运营 / 存量客户经营）
 *
 * <p>业务场景：存量业务管理按账龄（月）与综合收益率（%）对存量资产分层，气泡大小表示存量规模，
 * 颜色表示分层（核心 / 成长 / 观察），用于存量资产的结构诊断与经营策略调整。</p>
 * <p>实际图表类型：{@link JChartType#Bubble} 气泡图。选题原拟用散点图，但 Demo 29 已使用
 * {@link JChartType#SCATTER} 散点图，为避免图表类型重复，本 Demo 改用气泡图——气泡图在散点
 * 二维坐标基础上增加了"气泡大小=存量规模"这一维度，能同时表达账龄、收益率与规模三重信息。</p>
 *
 * <p>模板：{@code report/biz/45_stock_bubble.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\45_stock_bubble.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 400pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz45StockBubbleTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz45StockBubble() throws IOException {
        JOption option = new JOption();
        option.title().text("存量业务分层分析").subtext("账龄（月）× 综合收益率（%）× 存量规模");
        // 图例取值必须与数据点 category 完全一致
        option.legend("核心业务", "成长业务", "观察业务");
        option.xAxis(new CategoryAxis().name("账龄（月）"));
        option.yAxis(new ValueAxis().name("综合收益率（%）"));
        ScatterSeries series = new ScatterSeries("存量规模");
        List<Map<String, Object>> seriesData = new ArrayList<>();
        // 核心业务：账龄长、收益率中等、存量规模大
        Map<String, Object> p1 = new HashMap<>();
        p1.put("x", "12");
        p1.put("y", 4.8);
        p1.put("size", 860);
        p1.put("category", "核心业务");
        p1.put("name", "客户A");
        p1.put("color", new Color(40, 53, 147, 180));
        seriesData.add(p1);
        Map<String, Object> p2 = new HashMap<>();
        p2.put("x", "24");
        p2.put("y", 4.5);
        p2.put("size", 1240);
        p2.put("category", "核心业务");
        p2.put("name", "客户B");
        p2.put("color", new Color(40, 53, 147, 180));
        seriesData.add(p2);
        Map<String, Object> p3 = new HashMap<>();
        p3.put("x", "36");
        p3.put("y", 4.2);
        p3.put("size", 980);
        p3.put("category", "核心业务");
        p3.put("name", "客户C");
        p3.put("color", new Color(40, 53, 147, 180));
        seriesData.add(p3);
        // 成长业务：账龄中等、收益率较高、存量规模中等
        Map<String, Object> p4 = new HashMap<>();
        p4.put("x", "6");
        p4.put("y", 6.2);
        p4.put("size", 420);
        p4.put("category", "成长业务");
        p4.put("name", "客户D");
        p4.put("color", new Color(92, 107, 192, 180));
        seriesData.add(p4);
        Map<String, Object> p5 = new HashMap<>();
        p5.put("x", "15");
        p5.put("y", 5.8);
        p5.put("size", 560);
        p5.put("category", "成长业务");
        p5.put("name", "客户E");
        p5.put("color", new Color(92, 107, 192, 180));
        seriesData.add(p5);
        Map<String, Object> p6 = new HashMap<>();
        p6.put("x", "27");
        p6.put("y", 5.4);
        p6.put("size", 640);
        p6.put("category", "成长业务");
        p6.put("name", "客户F");
        p6.put("color", new Color(92, 107, 192, 180));
        seriesData.add(p6);
        // 观察业务：账龄短、收益率高、存量规模小
        Map<String, Object> p7 = new HashMap<>();
        p7.put("x", "3");
        p7.put("y", 7.6);
        p7.put("size", 180);
        p7.put("category", "观察业务");
        p7.put("name", "客户G");
        p7.put("color", new Color(197, 202, 233, 180));
        seriesData.add(p7);
        Map<String, Object> p8 = new HashMap<>();
        p8.put("x", "9");
        p8.put("y", 7.2);
        p8.put("size", 260);
        p8.put("category", "观察业务");
        p8.put("name", "客户H");
        p8.put("color", new Color(197, 202, 233, 180));
        seriesData.add(p8);
        Map<String, Object> p9 = new HashMap<>();
        p9.put("x", "21");
        p9.put("y", 6.9);
        p9.put("size", 210);
        p9.put("category", "观察业务");
        p9.put("name", "客户I");
        p9.put("color", new Color(197, 202, 233, 180));
        seriesData.add(p9);
        series.data(seriesData.toArray());
        option.series(series);

        String svg = JChartRendererFactory.renderChart(JChartType.Bubble, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/45_stock_bubble.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/45_stock_bubble.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
