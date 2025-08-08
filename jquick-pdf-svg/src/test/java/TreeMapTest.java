
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
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.style.JItemStyle;
import com.github.paohaijiao.treemap.JTreeMapRenderer;
import com.github.paohaijiao.treemap.JTreeMapSeries;
import com.github.paohaijiao.words.JWordCloudRenderer;
import com.github.paohaijiao.words.JWordCloudSeries;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


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
public class TreeMapTest {
    private static List<JData> createTestData() {
        JData electronics = new JData("电子产品", 12000)
                .children(Arrays.asList(
                        new JData("智能手机", 6500),
                        new JData("笔记本电脑", 3500),
                        new JData("平板电脑", 2000)
                ));
        JData clothing = new JData("服装", 8500)
                .children(Arrays.asList(
                        new JData("男装", 4000)
                                .children(Arrays.asList(
                                        new JData("衬衫", 1500),
                                        new JData("裤子", 1800),
                                        new JData("外套", 700)
                                )),
                        new JData("女装", 4500)
                ));

        JData food = new JData("食品", 7500)
                .children(Arrays.asList(
                        new JData("零食", 3000),
                        new JData("饮料", 2500),
                        new JData("生鲜", 2000)
                ));

        JData furniture = new JData("家具", 5000)
                .children(Arrays.asList(
                        new JData("客厅家具", 2500),
                        new JData("卧室家具", 1500),
                        new JData("办公家具", 1000)
                ));

        JData books = new JData("图书", 3000)
                .children(Arrays.asList(
                        new JData("科技类", 1200),
                        new JData("文学类", 1000),
                        new JData("儿童读物", 800)
                ));

        return Arrays.asList(electronics, clothing, food, furniture, books);
    }
    @Test
    public void testBarChar1() throws IOException {
        List<JData> testData = createTestData();
        JTreeMapSeries series = new JTreeMapSeries("销售数据")
                .data(testData)
                .showValue(true)
                .showPercentage(true);
        JOption option = new JOption()
                .title("2025年产品销售占比")
                .series(series);

        JTreeMapRenderer renderer = new JTreeMapRenderer();
        renderer.render(option, "d://test//treemap.svg");

    }

}
