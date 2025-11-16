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
import com.github.paohaijiao.funnel.*;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/16
 */
public class FunelTest {
    @Test
    public void testBarChar1() throws IOException {
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

        JFunnelChartRenderer renderer = new JFunnelChartRenderer();
        JOption jOption = new JOption();
        jOption.setFunnelOption(customOption);
        renderer.render(jOption, "d://test/funnel.svg");
    }
}
