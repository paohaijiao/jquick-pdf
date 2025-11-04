///*
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
// */
//
//
//
//import com.github.paohaijiao.JLegend;
//import com.github.paohaijiao.JOption;
//import com.github.paohaijiao.JTitle;
//import com.github.paohaijiao.series.JTreemap;
//import com.github.paohaijiao.series.other.JLevel;
//import com.github.paohaijiao.style.JBreadcrumb;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Treemap 图表测试
// */
//public class TreemapTest {
//    public static void main(String[] args) {
//        try {
//            // 创建 Treemap 系列
//            JTreemap treemap = new JTreemap("公司业务分布");
//
//            // 设置中心位置和大小
//            treemap.center("50%", "50%");
//            treemap.size("80%", "80%");
//
//            // 标签配置
////            Map<String, Object> label = new HashMap<>();
////            label.put("show", true);
////            label.put("showValue", true);
////            label.put("fontSize", 11);
////            JItemStyle itemStyle = new JItemStyle();
////            itemStyle.setfin
////            treemap.setLabel(label);
//
//            // 面包屑配置
//            JBreadcrumb breadcrumb = new JBreadcrumb();
//            breadcrumb.setShow(true);
//            treemap.setBreadcrumb(breadcrumb);
//
//            // 层级配置
//            JLevel level1 = new JLevel();
//            level1.setColor(new Object[]{"#5470c6", "#91cc75", "#fac858"});
//
//            JLevel level2 = new JLevel();
//            level2.setColor(new Object[]{"#ee6666", "#73c0de", "#3ba272"});
//
//            treemap.setLevels(new JLevel[]{level1, level2});
//
//            // 可见性配置
//            treemap.setVisibleMin(10.0);
//            treemap.setChildrenVisibleMin(100.0);
//
//            // 数据
//            treemap.setData(Arrays.asList(
//                    createDataItem("技术部", 350, null, "#5470c6"),
//                    createDataItem("财务部", 280, null, "#91cc75"),
//                    createDataItem("销售部", 220, null, "#fac858"),
//                    createDataItem("人力资源", 100, null, "#ee6666"),
//                    createDataItem("市场营销", 50, null, "#73c0de"),
//
//                    createDataItem("软件开发", 150, "技术部", null),
//                    createDataItem("基础设施", 100, "技术部", null),
//                    createDataItem("数据科学", 60, "技术部", null),
//                    createDataItem("质量保证", 40, "技术部", null),
//
//                    createDataItem("前端开发", 70, "软件开发", null),
//                    createDataItem("后端开发", 60, "软件开发", null),
//                    createDataItem("移动开发", 20, "软件开发", null)
//            ));
//
//            // 创建 JOption
//            JOption option = new JOption();
//            JTitle title=new JTitle();
//            title.setText("公司业务分布矩形树图");
//            title.setSubtext("数据来源：公司年度报告");
//            option.setTitle(title);
//            option.setColor(Arrays.asList("#5470c6", "#91cc75", "#fac858", "#ee6666", "#73c0de"));
//            option.setBackgroundColor("#fafafa");
//            option.setLegend(new JLegend(true));
//            option.setSeries(Arrays.asList(treemap));
//
//            // 创建渲染器并生成图表
//            JTreemapChartRenderer renderer = new JTreemapChartRenderer();
//            renderer.render(option, "d://test//treemap_jtreemap.svg");
//
//            System.out.println("基于 JTreemap 的矩形树图生成成功！");
//            System.out.println("文件保存位置: d://test//treemap_jtreemap.svg");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static Map<String, Object> createDataItem(String name, Number value, String parent, String color) {
//        Map<String, Object> item = new HashMap<>();
//        item.put("name", name);
//        item.put("value", value);
//        if (parent != null) {
//            item.put("parent", parent);
//        }
//        if (color != null) {
//            Map<String, Object> itemStyle = new HashMap<>();
//            itemStyle.put("color", color);
//            item.put("itemStyle", itemStyle);
//        }
//        return item;
//    }
//}
