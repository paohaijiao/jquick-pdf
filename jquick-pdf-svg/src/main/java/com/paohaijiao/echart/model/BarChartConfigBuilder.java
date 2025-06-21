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
package com.paohaijiao.echart.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName BarChartConfigBuilder
 * @Description: Description
 * @Author: gou
 * @CreateDate: 2025/6/12 8:43
 * @UpdateUser: UpdateUser
 * @UpdateDate: 2025/6/12 8:43
 * @UpdateRemark:
 * @Version: 1.0
 */
public class BarChartConfigBuilder extends EChartConfigBuilder{
    public static Map<String, Object> buildBarChart(
            String title,
            List<String> xAxisData,
            List<SeriesData> seriesList) {

        return new EChartConfig()
                .add("title", mapOf("text", title))
                .add("tooltip", mapOf())
                .add("legend", mapOf(
                        "data", seriesList.stream()
                                .map(SeriesData::getName)
                                .collect(Collectors.toList())
                ))
              .add("xAxis", mapOf(
                                "type", "category",
                                "data", xAxisData
                        ))
                        .add("yAxis", mapOf("type", "value"))
                        .add("series", seriesList.stream()
                                .map(series -> mapOf(
                                        "name", series.getName(),
                                        "type", "bar",
                                        "data", series.getData()
                                ))
                                .collect(Collectors.toList())
                        )
                        .build();
    }

    public static class SeriesData {
        private String name;
        private List<Number> data;

        public SeriesData(String name, List<Number> data) {
            this.name = name;
            this.data = data;
        }

        // getters
        public String getName() { return name; }
        public List<Number> getData() { return data; }
    }
    public static void main(String[] args) {
        List<String> categories = Arrays.asList("衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子");
        List<SeriesData> series = Arrays.asList(
                new SeriesData("2022", Arrays.asList(15, 25, 36, 10, 10, 20)),
                new SeriesData("2023", Arrays.asList(20, 30, 25, 15, 12, 25))
        );

        Map<String, Object> config = buildBarChart("年度销售对比", categories, series);
        System.out.println(config);
    }
}
