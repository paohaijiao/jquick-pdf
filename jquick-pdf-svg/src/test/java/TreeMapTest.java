
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
import com.github.paohaijiao.data.JSeriesData;
import com.github.paohaijiao.series.JTreemap;
import com.github.paohaijiao.style.JItemStyle;
import com.github.paohaijiao.JDiskNode;
import com.github.paohaijiao.sunburst.JSunburstChart;
import com.github.paohaijiao.treemap.JTreeMapRenderer;
import com.github.paohaijiao.treemap.JTreeMapSeries;
import org.junit.Test;

import java.io.IOException;
import java.util.*;


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
    public  JOption createGroupedTreemapOption() {
        JOption option = new JOption();
        option.setTitle(new JTitle().text("公司部门预算（按类别分组）"));

        JTreemap treemap = new JTreemap();
        treemap.setData(Arrays.asList(
                createNode("研发-前端", 2000000, "研发"),
                createNode("研发-后端", 2500000, "研发"),
                createNode("市场-广告", 1500000, "市场"),
                createNode("市场-推广", 1700000, "市场"),
                createNode("销售-国内", 1800000, "销售"),
                createNode("销售-国际", 1000000, "销售"),
                createNode("行政-总务", 800000, "行政"),
                createNode("行政-人事", 600000, "行政")
        ));

        option.setSeries(Collections.singletonList(treemap));
        return option;
    }

    // 示例2：扁平化数据（无分组，适合测试基础布局）
    public  JOption createFlatTreemapOption() {
        JOption option = new JOption();
        option.setTitle(new JTitle().text("2023年产品销售额"));
        JTreemap treemap = new JTreemap();
        treemap.setData(Arrays.asList(
                createNode("智能手机", 4500000),
                createNode("笔记本电脑", 3200000),
                createNode("平板电脑", 2800000),
                createNode("智能手表", 1500000),
                createNode("耳机", 1200000),
                createNode("路由器", 800000)
        ));

        option.setSeries(Collections.singletonList(treemap));
        return option;
    }

    // 示例3：极端数据测试（包含零值和超大值）
    public  JOption createEdgeCaseOption() {
        JOption option = new JOption();
        option.setTitle(new JTitle().text("极端数据测试"));
        JTreemap treemap = new JTreemap();
        treemap.setData(Arrays.asList(
                createNode("超大项", 10000000),
                createNode("零值项", 0),
                createNode("普通项A", 500000),
                createNode("普通项B", 300000),
                createNode("极小项", 1)
        ));

        option.setSeries(Collections.singletonList(treemap));
        return option;
    }

    private  JData createNode(String name, double value, String category) {
        return new JData(name).value(value).category(category);
    }

    // 创建扁平节点（无类别）
    private  JData createNode(String name, double value) {
        return new JData(name).value(value);
    }
    @Test
    public void testBarChar1() throws IOException {
        JOption option = createGroupedTreemapOption();
        option.title().text("Sunburst Chart");
        JTreeMapRenderer chart = new JTreeMapRenderer();
        chart.render(option, "d://test//treemap.svg");

    }



}
