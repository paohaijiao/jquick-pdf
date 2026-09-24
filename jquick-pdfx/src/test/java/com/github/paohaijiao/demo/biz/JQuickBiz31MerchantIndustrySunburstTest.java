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
import com.github.paohaijiao.data.JSunburstData;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 业务场景 Demo 31：商户行业分布（收单/商户运营平台）
 *
 * <p>业务场景：收单业务按行业大类汇总存量商户，并下钻至细分子行业，用于商户经营结构分析与
 * 行业拓展策略制定。</p>
 * <p>实际图表类型：{@link JChartType#SUNBURST} 旭日图。选题原拟用环形图，但 Demo 20 已使用
 * {@link JChartType#Circle} 环形图，为避免图表类型重复，本 Demo 改用旭日图——旭日图本质上是
 * 分层展开的同心环，既能呈现一级行业的占比，又能通过第二层环直观展示子行业构成。</p>
 *
 * <p>模板：{@code report/biz/31_merchant_industry_sunburst.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\31_merchant_industry_sunburst.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 640pt × 460pt（宽 × 高）的方形比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz31MerchantIndustrySunburstTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz31MerchantIndustrySunburst() throws IOException {
        // 根节点：商户总览；value 为该层相对父节点的比例（根节点固定为 1.0）
        JSunburstData root = new JSunburstData("商户总览", 1.0);
        // 一级行业 5 个，比例之和为 1.0；每个一级行业再下钻 2 个细分子行业（比例之和为 1.0）
        JSunburstData retail = new JSunburstData("批发零售", 0.34);
        retail.addChild(new JSunburstData("食品饮料", 0.55));
        retail.addChild(new JSunburstData("日用百货", 0.45));
        root.addChild(retail);

        JSunburstData catering = new JSunburstData("住宿餐饮", 0.22);
        catering.addChild(new JSunburstData("正餐服务", 0.62));
        catering.addChild(new JSunburstData("快餐小吃", 0.38));
        root.addChild(catering);

        JSunburstData infoTech = new JSunburstData("信息技术", 0.18);
        infoTech.addChild(new JSunburstData("软件开发", 0.58));
        infoTech.addChild(new JSunburstData("数据服务", 0.42));
        root.addChild(infoTech);

        JSunburstData manufacturing = new JSunburstData("制造业", 0.15);
        manufacturing.addChild(new JSunburstData("装备制造", 0.66));
        manufacturing.addChild(new JSunburstData("消费品制造", 0.34));
        root.addChild(manufacturing);

        JSunburstData others = new JSunburstData("其他", 0.11);
        others.addChild(new JSunburstData("居民服务", 0.52));
        others.addChild(new JSunburstData("文化体育", 0.48));
        root.addChild(others);

        JOption option = new JOption();
        option.setSunburstData(root);
        option.title("商户行业分布");
        String svg = JChartRendererFactory.renderChart(JChartType.SUNBURST, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/31_merchant_industry_sunburst.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/31_merchant_industry_sunburst.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
