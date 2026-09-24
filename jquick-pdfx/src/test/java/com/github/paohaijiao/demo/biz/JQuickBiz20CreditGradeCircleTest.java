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
import com.github.paohaijiao.combol.JCircleChartData;
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
import java.util.List;

/**
 * 业务场景 Demo 20：企业信用等级分布（信用平台）
 *
 * <p>业务场景：信用信息平台按季度汇总入库企业的信用等级（AAA / AA / A / BBB / BB 及以下），
 * 用于区域信用状况概览与授信政策制定。</p>
 * <p>实际图表类型：{@link JChartType#Circle} 环形图（中心显示企业总数，外环按等级占比展开）。</p>
 *
 * <p>模板：{@code report/biz/20_credit_grade_circle.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\20_credit_grade_circle.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 500pt × 400pt（宽 × 高）的方形比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz20CreditGradeCircleTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz20CreditGradeCircle() throws IOException {
        JCircleChartData chartData = new JCircleChartData();
        chartData.setTitleText("企业信用等级分布");
        chartData.setSubtitleText("统计口径：入库企业信用评级（单位：户）");
        chartData.setCenterTitle("企业总数");
        chartData.setCenterUnit("户");
        chartData.setFooterText("数据来源：信用信息平台");
        // 五个信用等级扇形，颜色沿同一主色由深至浅，保持政务/企业系统低饱和风格
        List<JCircleChartData.SectorData> sectors = new ArrayList<>();
        sectors.add(new JCircleChartData.SectorData("AAA", 1280, new Color(31, 78, 121)));
        sectors.add(new JCircleChartData.SectorData("AA", 2460, new Color(62, 107, 157)));
        sectors.add(new JCircleChartData.SectorData("A", 3180, new Color(120, 152, 187)));
        sectors.add(new JCircleChartData.SectorData("BBB", 1520, new Color(168, 190, 214)));
        sectors.add(new JCircleChartData.SectorData("BB及以下", 640, new Color(205, 219, 234)));
        chartData.setSectorDataList(sectors);

        JOption option = new JOption();
        option.setData(chartData);
        String svg = JChartRendererFactory.renderChart(JChartType.Circle, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/20_credit_grade_circle.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/20_credit_grade_circle.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
