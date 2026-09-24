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
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.funnel.*;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * 业务场景 Demo 40：流量转化漏斗（政务服务平台 / 线上办件）
 *
 * <p>业务场景：政务服务平台统计线上办件的全流程转化，从访问到实名认证、表单填写、
 * 材料上传直至提交成功，用于识别流程堵点、优化办事体验，主色采用沉稳青绿 #006064。</p>
 * <p>实际图表类型：{@link JChartType#Funnel} 漏斗图（展示办件流程各环节的逐级递减）。</p>
 *
 * <p>模板：{@code report/biz/40_traffic_funnel.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\40_traffic_funnel.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 400pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz40TrafficFunnelTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz40TrafficFunnel() throws IOException {
        JFunnelOption funnelOption = JFunnelOption.createDefaultFunnel();
        funnelOption = funnelOption
                .title(new Title().text("线上办件流程转化漏斗").subtext("2024年政务服务平台办件全流程（单位：次）"))
                .funnel(new Funnel()
                        .width(600)
                        .topY(80)
                        .bottomY(200)
                        .gap(2)
                        .borderColor(Color.GRAY)
                ).series(Collections.singletonList(
                        new Series()
                                .name("办件环节")
                                .type("funnel")
                                .data(Arrays.asList(
                                        new DataItem("访问页面", 128000),
                                        new DataItem("实名认证", 76200),
                                        new DataItem("表单填写", 51800),
                                        new DataItem("材料上传", 39600),
                                        new DataItem("提交成功", 31400)
                                ))))
                // 与主色 #006064 同色系的低饱和青绿渐变，依次变浅
                .colors(
                        new Color(0, 96, 100),
                        new Color(0, 121, 122),
                        new Color(38, 145, 143),
                        new Color(84, 168, 165),
                        new Color(128, 190, 186)
                );
        JOption option = new JOption();
        option.setFunnelOption(funnelOption);

        String svg = JChartRendererFactory.renderChart(JChartType.Funnel, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/40_traffic_funnel.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/40_traffic_funnel.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
