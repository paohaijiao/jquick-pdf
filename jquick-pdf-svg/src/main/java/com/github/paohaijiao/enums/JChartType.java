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
package com.github.paohaijiao.enums;

public enum JChartType {
    BAR("bar","条形图"),
    BOXPLOT("boxplot","盒须图"),
    HEATMAP("heatmap","热力图"),
    K("k","k线图"),
    LINE("line","折线图"),
    PIE("pie","饼状图"),
    RADAR("radar","雷达图"),
    RELATION("ration","关系图"),
    SCATTER("scatter","散点图"),
    SUNBURST("sunburst","旭日图"),
    WORDCLOUD("wordCloud","词云"),
    ;
    private String code;

    private String name;

    JChartType(String code,String name) {
        this.code = code;
        this.name = name;
    }
    public JChartType codeOf(String code){
        for (JChartType jChartType : values()) {
            if (jChartType.code.equals(code)) {
                return jChartType;
            }
        }
        return null;
    }
}
