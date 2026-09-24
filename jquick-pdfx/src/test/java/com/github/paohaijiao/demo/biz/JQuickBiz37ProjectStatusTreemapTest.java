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
import com.github.paohaijiao.treemap.JTreeMapNode;
import com.github.paohaijiao.treemap.TreeMapOption;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 业务场景 Demo 37：项目状态分布（政府投资项目监管平台）
 *
 * <p>业务场景：项目监管平台按"在建 / 已完工 / 前期 / 暂停"四大状态对政府投资项目分组，
 * 以矩形面积表示项目投资额，用于把握投资进度与资金沉淀情况。</p>
 * <p>实际图表类型：{@link JChartType#TREEMAP} 矩形树图（矩形面积正比于投资额，同色系为一组状态）。</p>
 *
 * <p>模板：{@code report/biz/37_project_status_treemap.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\37_project_status_treemap.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 420pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz37ProjectStatusTreemapTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz37ProjectStatusTreemap() throws IOException {
        // 根节点 value 为四大状态投资额之和（单位：亿元），子节点面积即代表各自投资额
        JTreeMapNode root = new JTreeMapNode("项目总览", 1000);

        JTreeMapNode building = new JTreeMapNode("在建", 420);
        building.addChild(new JTreeMapNode("市政工程", 240));
        building.addChild(new JTreeMapNode("交通工程", 180));
        root.addChild(building);

        JTreeMapNode completed = new JTreeMapNode("已完工", 300);
        completed.addChild(new JTreeMapNode("房建工程", 190));
        completed.addChild(new JTreeMapNode("水利工程", 110));
        root.addChild(completed);

        JTreeMapNode preparatory = new JTreeMapNode("前期", 180);
        preparatory.addChild(new JTreeMapNode("规划设计", 110));
        preparatory.addChild(new JTreeMapNode("征地拆迁", 70));
        root.addChild(preparatory);

        JTreeMapNode suspended = new JTreeMapNode("暂停", 100);
        suspended.addChild(new JTreeMapNode("资金待落实", 60));
        suspended.addChild(new JTreeMapNode("手续待补办", 40));
        root.addChild(suspended);

        TreeMapOption treemapOption = new TreeMapOption();
        treemapOption.setRoot(root);
        JOption option = new JOption();
        option.setTreemapOption(treemapOption);
        option.title("政府投资项目状态分布");
        String svg = JChartRendererFactory.renderChart(JChartType.TREEMAP, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/37_project_status_treemap.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/37_project_status_treemap.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
