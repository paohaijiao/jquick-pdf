package com.github.paohaijiao.combol;

import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;

import java.awt.*;
import java.util.List;

/**
 * 环形图数据配置
 */
@Data
public class JCircleChartData implements JGraphData {

    /**
     * 基础尺寸
     */
    private int width = 500;

    private int height = 400;
    /**
     * 环形图数据
     */
    private List<SectorData> sectorDataList;
    /**
     * 起始角度（0表示从12点钟方向开始）
     */
    private float startAngle = -90f;
    /**
     * 中心文字配置
     */
    private boolean showCenterText = true;

    private String centerTitle = "总计";

    private String centerValue;

    private String centerUnit;

    /**
     * 标题配置
     */
    private String titleText;

    private String subtitleText;
    /**
     * 底部说明
     */
    private String footerText;
    /**
     * 颜色配置
     */
    private Color backgroundColor = Color.WHITE;

    private Color centerColor = new Color(255, 255, 255);

    private Color textColor = new Color(51, 51, 51);

    private Color textSecondaryColor = new Color(120, 120, 120);

    private Color footerColor = new Color(150, 150, 150);
    /**
     * 字体配置
     */
    private Font titleFont = new Font("Microsoft YaHei", Font.BOLD, 18);

    private Font subtitleFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font centerTitleFont = new Font("Microsoft YaHei", Font.PLAIN, 13);

    private Font centerValueFont = new Font("Microsoft YaHei", Font.BOLD, 24);

    private Font centerUnitFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font legendFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font legendPercentFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Font footerFont = new Font("Microsoft YaHei", Font.PLAIN, 10);

    /**
     * 颜色配置
     */
    private Color centerTitleColor = new Color(100, 100, 100);

    private Color centerValueColor = new Color(51, 51, 51);

    private Color centerUnitColor = new Color(150, 150, 150);

    /**
     * 验证数据并计算百分比
     */
    public void validateAndCalculate() {
        JAssert.notNull(sectorDataList, "sector data list require not null");
        JAssert.isTrue(!sectorDataList.isEmpty(), "sector data list cannot be empty");
        // 计算总和
        double sum = 0;
        for (SectorData sector : sectorDataList) {
            JAssert.notNull(sector.getValue(), "sector value require not null");
            sum += sector.getValue();
        }
        // 计算百分比
        for (SectorData sector : sectorDataList) {
            double percentage = (sector.getValue() / sum) * 100;
            sector.setPercentage(percentage);
            // 自动生成图例文字
            if (sector.getLegendText() == null) {
                sector.setLegendText(sector.getName());
            }
            // 自动生成颜色（如果没有设置）
            if (sector.getSectorColor() == null) {
                sector.setSectorColor(getDefaultColor(sectorDataList.indexOf(sector)));
            }
        }
        // 设置中心值
        if (centerValue == null) {
            centerValue = formatValue(sum);
        }
    }

    /**
     * 格式化数值
     */
    private String formatValue(double value) {
        if (value >= 10000) {
            return String.format("%.1fK", value / 1000);
        }
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.format("%.1f", value);
    }

    /**
     * 获取默认颜色（商务风格配色）
     */
    private Color getDefaultColor(int index) {
        Color[] defaultColors = {
                new Color(46, 125, 100),   // 墨绿

                new Color(74, 144, 196),   // 商务蓝

                new Color(91, 108, 142),   // 深灰蓝

                new Color(154, 172, 184),  // 浅灰蓝

                new Color(240, 147, 85),   // 橙

                new Color(126, 87, 138)    // 紫
        };
        return defaultColors[index % defaultColors.length];
    }

    /**
     * 扇形数据类
     */
    @Data
    public static class SectorData {

        private String name;           // 名称

        private double value;          // 数值

        private double percentage;     // 百分比（自动计算）

        private Color sectorColor;     // 扇形颜色

        private String legendText;     // 图例文字

        public SectorData(String name, double value) {
            this.name = name;
            this.value = value;
        }

        public SectorData(String name, double value, Color color) {
            this.name = name;
            this.value = value;
            this.sectorColor = color;
        }
    }
}