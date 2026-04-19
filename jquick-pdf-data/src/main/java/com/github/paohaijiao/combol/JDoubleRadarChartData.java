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
package com.github.paohaijiao.combol;

import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;
import java.awt.*;
import java.util.List;

@Data
public class JDoubleRadarChartData implements JGraphData {

    /**
     * 画布配置
     */
    private int width = 1000;

    private int height = 600;

    private Color backgroundColor = Color.WHITE;

    /**
     * 标题配置
     */
    private String titleText;

    private String subtitleText;

    private String leftTitle = "左侧雷达图";

    private String rightTitle = "右侧雷达图";

    private Font titleFont = new Font("Microsoft YaHei", Font.BOLD, 20);

    private Font subtitleFont = new Font("Microsoft YaHei", Font.PLAIN, 13);

    private Color textColor = new Color(51, 51, 51);

    /**
     * 坐标轴配置
     */
    private Color axisColor = new Color(68, 68, 68);

    private Color gridColor = new Color(200, 220, 224);

    private Color labelColor = new Color(102, 102, 102);

    private Font axisFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font axisTitleFont = new Font("Microsoft YaHei", Font.BOLD, 14);

    /**
     * 图例配置
     */
    private Font legendFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private boolean legendAtTop = false;

    private boolean showLegendSide = true;

    /**
     * 底部说明配置
     */
    private String footerText;

    private Font footerFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Color footerColor = new Color(153, 153, 153);

    /**
     * 雷达图配置
     */
    private List<String> dimensions;

    private int gridLevels = 4;

    private int fillAlpha = 70;

    private float lineWidth = 2.0f;

    private boolean showDataPoints = true;

    private boolean valueWithPercent = false;

    /**
     * 左右雷达图数据
     */
    private RadarData leftRadar;

    private RadarData rightRadar;

    private double maxValue;

    public void updateMaxValues() {
        double leftMax = 0;
        double rightMax = 0;
        if (leftRadar != null && leftRadar.getSeriesList() != null) {
            for (Series series : leftRadar.getSeriesList()) {
                if (series.getValues() != null) {
                    for (Double value : series.getValues()) {
                        if (value != null && value > leftMax) {
                            leftMax = value;
                        }
                    }
                }
            }
        }
        if (rightRadar != null && rightRadar.getSeriesList() != null) {
            for (Series series : rightRadar.getSeriesList()) {
                if (series.getValues() != null) {
                    for (Double value : series.getValues()) {
                        if (value != null && value > rightMax) {
                            rightMax = value;
                        }
                    }
                }
            }
        }
        this.maxValue = Math.max(leftMax, rightMax);
        if (this.maxValue <= 0) {
            this.maxValue = 100;
        }
        if (leftRadar != null) {
            leftRadar.setMaxValue(this.maxValue);
        }
        if (rightRadar != null) {
            rightRadar.setMaxValue(this.maxValue);
        }
    }

    @Data
    public static class RadarData {

        private List<Series> seriesList;

        private double maxValue;
    }

    @Data
    public static class Series {

        private String name;

        private List<Double> values;

        private Color color;
    }
}