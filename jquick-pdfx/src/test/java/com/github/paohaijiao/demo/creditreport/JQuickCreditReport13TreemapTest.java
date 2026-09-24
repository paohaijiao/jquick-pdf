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
package com.github.paohaijiao.demo.creditreport;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.treemap.JTreeMapNode;
import com.github.paohaijiao.treemap.TreeMapOption;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 统一信用报告 —— 风格 13：矩形树图（蓝灰）
 *
 * <p>以 {@code report/credit_report_13_treemap.txt} 为模板，把矩形树图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\13_treemap.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport13TreemapTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report13Treemap() throws IOException {
        JTreeMapNode root = new JTreeMapNode("业务总览", 1000);
        JTreeMapNode product = new JTreeMapNode("软件产品", 400);
        JTreeMapNode service = new JTreeMapNode("技术服务", 250);
        JTreeMapNode cloud = new JTreeMapNode("云服务", 200);
        JTreeMapNode other = new JTreeMapNode("其他业务", 150);
        product.addChild(new JTreeMapNode("企业管理软件", 240));
        product.addChild(new JTreeMapNode("行业解决方案", 160));
        service.addChild(new JTreeMapNode("实施服务", 130));
        service.addChild(new JTreeMapNode("运维服务", 120));
        cloud.addChild(new JTreeMapNode("公有云", 140));
        cloud.addChild(new JTreeMapNode("私有云", 60));
        other.addChild(new JTreeMapNode("培训", 80));
        other.addChild(new JTreeMapNode("硬件", 70));
        root.addChild(product);
        root.addChild(service);
        root.addChild(cloud);
        root.addChild(other);
        TreeMapOption treemapOption = new TreeMapOption();
        treemapOption.setRoot(root);
        JOption option = new JOption();
        option.setTreemapOption(treemapOption);
        option.title("业务板块矩形树图");

        String svg = JChartRendererFactory.renderChart(JChartType.TREEMAP, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/13_treemap.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_13_treemap.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
