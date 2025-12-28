
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
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.data.JSunburstData;
import com.github.paohaijiao.sunburst.JSunburstChart;
import org.junit.Test;

import java.io.IOException;


/**
 * @ClassName BarCharTest
 * @Description: Description
 * @Author: gou
 * @CreateDate: 2025/6/13 6:52
 * @UpdateUser: UpdateUser
 * @UpdateDate: 2025/6/13 6:52
 * @UpdateRemark:
 * @Version: 1.0
 */
public class SunBirdTest {


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
    public void testBarChar1() throws IOException {
        JOption option = new JOption();
        // 设置标题
        JTitle title = new JTitle();
        title.setText("咖啡风味分析");
        option.setTitle(title);
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
        option.setSunburstData(root);
        JSunburstChart chart = new JSunburstChart();
        chart.render(option, "d://test//sunburst.svg");
    }
//    @Test
//    public void testBarChar2() throws IOException {
//        JOption option = new JOption();
//        option.setTitle(new JTitle("专业旭日图示例", "多层次数据可视化"));
//
//        // 设置背景色
//        option.setBackgroundColor("#f5f5f5");
//
//        // 设置颜色系列
//        List<Object> colors = Arrays.asList(
//                "#FF6B6B", "#FF4757", "#4ECDC4", "#26A69A",
//                "#FFD166", "#FFAB00", "#EF5350", "#D32F2F"
//        );
//        option.setColor(colors);
//
//        // 创建系列数据（这里需要根据实际数据结构来设置）
//        JSeries series = new JSeries();
//        series.setName("旭日图数据");
//        series.setType("sunburst");
//
//        List<JSeries> seriesList = new ArrayList<>();
//        seriesList.add(series);
//        option.setSeries(seriesList);
//
//        // 创建渲染器并生成图表
//        ProfessionalSunburstGenerator generator = new ProfessionalSunburstGenerator();
//
//        // 生成到文件
//        generator.render(option, "d://test//sunburst_chart.svg");
//
//        // 或者生成到字符串
//        String svgContent = generator.renderToString(option);
//        System.out.println("旭日图生成完成！");
//
//    }
}
