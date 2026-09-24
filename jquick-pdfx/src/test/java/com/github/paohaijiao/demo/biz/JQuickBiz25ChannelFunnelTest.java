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
 * 业务场景 Demo 25：渠道来源漏斗转化（信用平台 / 用户增长）
 *
 * <p>业务场景：信用平台统计广告投放、搜索引擎、渠道合作、老客推荐四类注册渠道的新增注册量，
 * 以漏斗形态对比各渠道获客贡献，用于投放预算分配，主色采用暖调深橙 #BF360C。</p>
 * <p>实际图表类型：{@link JChartType#Funnel} 漏斗图（展示各渠道新增注册量的递减分布）。</p>
 *
 * <p>模板：{@code report/biz/25_channel_funnel.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\25_channel_funnel.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 400pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz25ChannelFunnelTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz25ChannelFunnel() throws IOException {
        JFunnelOption funnelOption = JFunnelOption.createDefaultFunnel();
        funnelOption = funnelOption
                .title(new Title().text("注册渠道转化漏斗").subtext("2024年信用平台新增注册用户（单位：人）"))
                .funnel(new Funnel()
                        .width(600)
                        .topY(80)
                        .bottomY(200)
                        .gap(2)
                        .borderColor(Color.GRAY)
                ).series(Collections.singletonList(
                        new Series()
                                .name("注册渠道")
                                .type("funnel")
                                .data(Arrays.asList(
                                        new DataItem("广告投放", 12000),
                                        new DataItem("搜索引擎", 8600),
                                        new DataItem("渠道合作", 5400),
                                        new DataItem("老客推荐", 3100)
                                ))))
                // 与主色 #BF360C 同色系的低饱和棕色渐变，避免高饱和跳色
                .colors(
                        new Color(140, 62, 44),
                        new Color(166, 84, 58),
                        new Color(191, 108, 82),
                        new Color(214, 141, 117)
                );
        JOption option = new JOption();
        option.setFunnelOption(funnelOption);

        String svg = JChartRendererFactory.renderChart(JChartType.Funnel, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/25_channel_funnel.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/25_channel_funnel.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
