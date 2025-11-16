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
import com.github.paohaijiao.funnel.*;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JFulnnelTest {
    private JOption createData(){
        JFunnelOption option = JFunnelOption.createDefaultFunnel();
        JFunnelOption customOption = option
                .title(new Title().text("销售漏斗").subtext("2024年数据"))
                .funnel(new Funnel()
                        .width(600)
                        .topY(80)
                        .bottomY(200)
                        .gap(2)
                        .borderColor(Color.GRAY)
                )
                .series(Collections.singletonList(
                        new Series()
                                .name("sales")
                                .type("funnel")
                                .data(Arrays.asList(
                                        new DataItem("展现", 10000),
                                        new DataItem("点击", 5000),
                                        new DataItem("咨询", 2000),
                                        new DataItem("订单", 500)
                                ))
                ))
                .colors(
                        new Color(12, 168, 223),
                        new Color(255, 153, 77),
                        new Color(80, 112, 221),
                        new Color(182, 214, 52)
                );

        JOption jOption = new JOption();
        jOption.setFunnelOption(customOption);
        return jOption;
    }

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer=new JGraphContainer();
        graphContainer.setType(JChartType.Funnel);
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
