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
 * 统一信用报告 —— 风格 12：旭日图（琥珀）
 *
 * <p>以 {@code report/credit_report_12_sunburst.txt} 为模板，把旭日图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\12_sunburst.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport12SunburstTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report12Sunburst() throws IOException {
        JSunburstData root = new JSunburstData("业务总览", 1.0);
        JSunburstData product = new JSunburstData("软件产品", 0.40);
        JSunburstData service = new JSunburstData("技术服务", 0.25);
        JSunburstData cloud = new JSunburstData("云服务", 0.20);
        JSunburstData other = new JSunburstData("其他业务", 0.15);
        product.addChild(new JSunburstData("企业管理软件", 0.6));
        product.addChild(new JSunburstData("行业解决方案", 0.4));
        service.addChild(new JSunburstData("实施服务", 0.5));
        service.addChild(new JSunburstData("运维服务", 0.5));
        cloud.addChild(new JSunburstData("公有云", 0.7));
        cloud.addChild(new JSunburstData("私有云", 0.3));
        other.addChild(new JSunburstData("培训", 0.5));
        other.addChild(new JSunburstData("硬件", 0.5));
        root.addChild(product);
        root.addChild(service);
        root.addChild(cloud);
        root.addChild(other);
        JOption option = new JOption();
        option.setSunburstData(root);
        option.title("业务结构旭日图");

        String svg = JChartRendererFactory.renderChart(JChartType.SUNBURST, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/12_sunburst.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_12_sunburst.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
