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
package com.github.paohaijiao.demo;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.combol.JPolarChartData;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.plugin.radialTree.JRadialTreeChartData;
import com.github.paohaijiao.plugin.themeRiver.JThemeRiverChartData;
import com.github.paohaijiao.series.JBar;
import org.junit.Test;

import java.awt.Color;
import java.io.IOException;

/**
 * packageName com.github.paohaijiao.demo
 *
 * @author Martin
 * @version 1.0.0
 * @classname JGallery520ImagesTest
 * @date 2026/11/8
 * @description Generates the five gallery previews added in 5.2.0: polar, progress, sankey,
 * theme river and radial tree. Every file lands in D:/test/gallery520 and is copied into the
 * repository images/ folder afterwards.
 */
public class JGallery520ImagesTest {

    private static final String OUTPUT_DIR = "d://test/gallery520/";

    @Test
    public void testPolar() throws IOException {
        JPolarChartData data = JPolarChartData.sample();
        JOption option = new JOption();
        option.setData(data);
        JChartRendererFactory.renderChart(JChartType.Polar, option, OUTPUT_DIR + "polar.svg");
    }

    @Test
    public void testProgress() throws IOException {
        JOption option = new JOption();
        option.title().text("关键指标完成度");
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("研发效能", "交付准时率", "客户满意度", "风险整改率");
        option.xAxis(xAxis);
        JBar bar = new JBar();
        bar.name("完成度").data(96, 88, 72, 61);
        option.series(bar);
        JChartRendererFactory.renderChart(JChartType.Progress, option, OUTPUT_DIR + "progress.svg");
    }

    @Test
    public void testSankey() {
        JOption option = new JOption();
        option.title().text("资金流向桑基图");
        JChartRendererFactory.renderChart("sankey", option, OUTPUT_DIR + "sankey.svg");
    }

    @Test
    public void testThemeRiver() throws IOException {
        JThemeRiverChartData data = new JThemeRiverChartData();
        data.setTitleText("流量来源主题河流图");
        data.setSubtitleText("数据由 JThemeRiverChartData 传入 · 每个时间点按总量居中堆叠");
        data.setValueUnit("次");
        data.timeLabels("07-08", "07-09", "07-10", "07-11", "07-12", "07-13", "07-14");
        data.addSeries("自然搜索", 420d, 510d, 470d, 530d, 590d, 620d, 660d);
        data.addSeries("付费推广", 260d, 300d, 280d, 310d, 340d, 330d, 360d);
        data.addSeries("社交媒体", new Color(0x3E8E7E), 180d, 220d, 260d, 300d, 320d, 360d, 400d);
        data.addSeries("邮件营销", 90d, 110d, 120d, 140d, 150d, 160d, 170d);
        data.addSeries("直接访问", 300d, 320d, 340d, 330d, 360d, 380d, 410d);
        JOption option = new JOption();
        option.setData(data);
        JChartRendererFactory.renderChart(JChartType.ThemeRiver, option, OUTPUT_DIR + "theme_river.svg");
    }

    @Test
    public void testRadialTree() throws IOException {
        JRadialTreeChartData.RadialNode root = new JRadialTreeChartData.RadialNode("集团总部")
                .addChild(new JRadialTreeChartData.RadialNode("产品中心", 40)
                        .addChild(new JRadialTreeChartData.RadialNode("研发一部", 18))
                        .addChild(new JRadialTreeChartData.RadialNode("研发二部", 12))
                        .addChild(new JRadialTreeChartData.RadialNode("产品设计部", 10)))
                .addChild(new JRadialTreeChartData.RadialNode("市场中心", 30)
                        .addChild(new JRadialTreeChartData.RadialNode("华东大区", 12))
                        .addChild(new JRadialTreeChartData.RadialNode("华南大区", 10))
                        .addChild(new JRadialTreeChartData.RadialNode("华北大区", 8)))
                .addChild(new JRadialTreeChartData.RadialNode("交付中心", 30)
                        .addChild(new JRadialTreeChartData.RadialNode("实施一部", 14))
                        .addChild(new JRadialTreeChartData.RadialNode("运维服务部", 10))
                        .addChild(new JRadialTreeChartData.RadialNode("客户成功部", 6)));
        JRadialTreeChartData data = new JRadialTreeChartData();
        data.setRoot(root);
        data.setTitleText("组织架构径向树");
        data.setSubtitleText("数据由 JRadialTreeChartData 传入 · 角度按叶子数量分配");
        JOption option = new JOption();
        option.setData(data);
        JChartRendererFactory.renderChart(JChartType.RadialTree, option, OUTPUT_DIR + "radial_tree.svg");
    }
}
