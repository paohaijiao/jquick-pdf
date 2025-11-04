
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

import com.github.paohaijiao.JLabel;
import com.github.paohaijiao.JOption;
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.JTooltip;
import com.github.paohaijiao.series.JSeries;
import com.github.paohaijiao.series.SunburstSeries;
import com.github.paohaijiao.style.JItemStyle;
import com.github.paohaijiao.style.JTextStyle;
import com.github.paohaijiao.style.itemstyle.JEmphasis;
import com.github.paohaijiao.style.itemstyle.JNormal;
import com.github.paohaijiao.sunburst.JSunburstChart;
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
public class SunBirdTest {


    private static Map<String, Object> createCategory(String name, double value, Map<String, Object>... children) {
        Map<String, Object> category = new java.util.HashMap<>();
        category.put("name", name);
        category.put("value", value);
        category.put("children", java.util.Arrays.asList(children));
        return category;
    }

    private static Map<String, Object> createSubCategory(String name, double value) {
        Map<String, Object> subCategory = new java.util.HashMap<>();
        subCategory.put("name", name);
        subCategory.put("value", value);
        return subCategory;
    }

    @Test
    public void testBarChar1() throws IOException {
        // 创建 JOption 配置
        JOption option = new JOption();

        // 设置标题
        JTitle title = new JTitle();
        title.setText("咖啡风味分析");
        option.setTitle(title);

        // 使用 ECharts 风格的配色
        List<Object> colors = java.util.Arrays.asList(
                "#c23531", "#2f4554", "#61a0a8", "#d48265", "#91c7ae",
                "#749f83", "#ca8622", "#bda29a", "#6e7074", "#546570"
        );
        option.setColor(colors);

        // 创建多层数据
        List<Map<String, Object>> seriesData = new ArrayList<>();

        seriesData.add(createCategory("花香", 10,
                createSubCategory("茉莉", 4),
                createSubCategory("玫瑰", 3),
                createSubCategory("薰衣草", 3)
        ));

        seriesData.add(createCategory("果香", 14,
                createSubCategory("浆果", 5),
                createSubCategory("柑橘", 4),
                createSubCategory("热带水果", 5)
        ));

        seriesData.add(createCategory("坚果", 8,
                createSubCategory("杏仁", 3),
                createSubCategory("榛子", 3),
                createSubCategory("核桃", 2)
        ));

        seriesData.add(createCategory("巧克力", 9,
                createSubCategory("黑巧克力", 4),
                createSubCategory("牛奶巧克力", 3),
                createSubCategory("可可", 2)
        ));

        // 创建系列
        SunburstSeries series = new SunburstSeries();
        series.setData(seriesData);

        List<JSeries> seriesList = new ArrayList<>();
        seriesList.add(series);
        option.setSeries(seriesList);

        // 创建并渲染图表
        JSunburstChart chart = new JSunburstChart();
        chart.render(option, "d://test//sunburst-demo.svg");
    }
}
