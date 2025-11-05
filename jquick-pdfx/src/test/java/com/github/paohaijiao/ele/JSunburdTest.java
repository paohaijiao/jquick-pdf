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
import com.github.paohaijiao.data.JSunburstData;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.io.IOException;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JSunburdTest {
    private static JSunburstData createTestData() { // 第一层：主分类
        JSunburstData root = new JSunburstData("总数据", 1.0);
        JSunburstData main1 = new JSunburstData("电子产品", 0.4);
        JSunburstData main2 = new JSunburstData("服装", 0.3);
        JSunburstData main3 = new JSunburstData("食品", 0.3);
        // 第二层：子分类
        JSunburstData main1Sub1 = new JSunburstData("手机", 0.6);
        JSunburstData main1Sub2 = new JSunburstData("电脑", 0.4);
        JSunburstData main2Sub1 = new JSunburstData("男装", 0.5);
        JSunburstData main2Sub2 = new JSunburstData("女装", 0.5);
        JSunburstData main3Sub1 = new JSunburstData("生鲜", 0.4);
        JSunburstData main3Sub2 = new JSunburstData("零食", 0.6);
        // 第三层：孙分类
        main1Sub1.addChild(new JSunburstData("智能手机", 0.7));
        main1Sub1.addChild(new JSunburstData("功能手机", 0.3));
        main1Sub2.addChild(new JSunburstData("笔记本电脑", 0.6));
        main1Sub2.addChild(new JSunburstData("台式电脑", 0.4));
        main2Sub1.addChild(new JSunburstData("衬衫", 0.4));
        main2Sub1.addChild(new JSunburstData("裤子", 0.6));
        main3Sub2.addChild(new JSunburstData("膨化食品", 0.5));
        main3Sub2.addChild(new JSunburstData("糖果", 0.5));
        // 构建树结构
        main1.addChild(main1Sub1);
        main1.addChild(main1Sub2);

        main2.addChild(main2Sub1);
        main2.addChild(main2Sub2);

        main3.addChild(main3Sub1);
        main3.addChild(main3Sub2);

        root.addChild(main1);
        root.addChild(main2);
        root.addChild(main3);

        return root;
    }

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer=new JGraphContainer();
        JSunburstData root = createTestData();
        JOption option = new JOption();
        option.setSunburstData(root);
        option.title("公司业务分布矩形树图（JTreemapRenderer）");
        graphContainer.setType(JChartType.SUNBURST);
        graphContainer.setOption(option);
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
