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
package com.github.paohaijiao.funnel;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JFunnelOption {

    private Title title;

    private Funnel funnel;

    private List<Series> series;

    private Legend legend;

    private Color[] colors;

    public JFunnelOption() {
    }

    // 创建默认漏斗图配置
    public static JFunnelOption createDefaultFunnel() {
        return new JFunnelOption()
                .title(new Title().text("Funnel").subtext("Conversion Funnel"))
                .funnel(new Funnel().width(700).topY(60).bottomY(222).gap(0))
                .series(Collections.singletonList(
                        new Series().name("funnel").type("funnel")
                                .data(Arrays.asList(
                                        new DataItem("Show", 100),
                                        new DataItem("Click", 80),
                                        new DataItem("Visit", 60),
                                        new DataItem("Inquiry", 40),
                                        new DataItem("Order", 20)
                                ))
                ))
                .legend(new Legend().show(true).top("bottom"))
                .colors(
                        new Color(12, 168, 223),
                        new Color(255, 153, 77),
                        new Color(80, 112, 221),
                        new Color(182, 214, 52),
                        new Color(80, 83, 114)
                );
    }

    public JFunnelOption title(Title title) {
        this.title = title;
        return this;
    }

    public JFunnelOption funnel(Funnel funnel) {
        this.funnel = funnel;
        return this;
    }

    public JFunnelOption series(List<Series> series) {
        this.series = series;
        return this;
    }

    public JFunnelOption series(Series... series) {
        this.series = Arrays.asList(series);
        return this;
    }

    public JFunnelOption legend(Legend legend) {
        this.legend = legend;
        return this;
    }

    public JFunnelOption colors(Color... colors) {
        this.colors = colors;
        return this;
    }

    public Title title() {
        return title;
    }

    public Funnel funnel() {
        return funnel;
    }

    public List<Series> series() {
        return series;
    }

    public Legend legend() {
        return legend;
    }

    public Color[] colors() {
        return colors;
    }
}