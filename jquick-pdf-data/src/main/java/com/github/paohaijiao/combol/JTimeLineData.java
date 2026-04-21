package com.github.paohaijiao.combol;

import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;

import java.awt.*;
import java.util.List;

/**
 * 奇偶排列流程图数据配置类
 */
@Data
public  class JTimeLineData implements JGraphData {
    /**
     * 配色方案
     */
    public static final Color COLOR_PRIMARY = new Color(31, 78, 121);

    public static final Color COLOR_PRIMARY_LIGHT = new Color(79, 129, 189);

    public static final Color COLOR_ACCENT = new Color(68, 114, 196);

    public static final Color COLOR_HIGHLIGHT = new Color(237, 125, 49);

    public static final Color COLOR_SUCCESS = new Color(112, 173, 71);

    public static final Color COLOR_DARK = new Color(51, 51, 51);

    public static final Color COLOR_MEDIUM = new Color(102, 102, 102);

    public static final Color COLOR_BORDER = new Color(200, 200, 200);

    public static final Color COLOR_BG = new Color(248, 249, 250);

    public static final Color COLOR_LIGHT = new Color(153, 153, 153);
    /**
     * 矩形框背景
     */
    public static final Color COLOR_BOX_BG = new Color(255, 255, 255);

    public static final Color COLOR_BOX_SHADOW = new Color(0, 0, 0, 20);
    // 画布尺寸
    private int width = 1300;

    private int height = 1200;

    // 标题配置 - 左上对齐
    private String mainTitle = "";

    private String subtitle = "";

    private Font mainTitleFont = new Font("Microsoft YaHei", Font.BOLD, 18);

    private Font subtitleFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private int titleY = 35;

    private int leftMargin = 30;
    // 矩形框配置
    private int boxWidth = 200;

    private int boxHeight = 90;

    private int boxTitlePaddingTop = 28;

    private int boxDescPaddingTop = 50;

    private int boxDescLineHeight = 22;
    // 圆圈节点配置
    private int circleRadius = 16;

    private int boxCircleGap = 30;
    // 间距配置
    private int startX = 80;

    private int endX = 80;

    // 边界安全边距
    private int minTopMargin = 100;

    private int minBottomMargin = 80;
    // 节点数据
    private java.util.List<FlowNode> nodes;
    // 颜色配置
    private Color defaultBoxColor = COLOR_PRIMARY;

    private Color defaultNodeColor = COLOR_PRIMARY;

    private Color backgroundColor = COLOR_BG;

    // 字体配置
    private Font boxTitleFont = new Font("Microsoft YaHei", Font.BOLD, 14);

    private Font boxDescFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Font circleNumberFont = new Font("Microsoft YaHei", Font.BOLD, 11);

    private Font footerFont = new Font("Microsoft YaHei", Font.PLAIN, 10);
    // 底部配置
    private String footerText;

    /**
     * 流程节点数据类
     */
    @Data
    public static class FlowNode {

        private String title;

        private String description;

        private Color boxColor;

        private Color nodeColor;

        public FlowNode(String title, String description) {
            this.title = title;
            this.description = description;
        }
        public FlowNode(String title, String description, Color boxColor) {
            this.title = title;
            this.description = description;
            this.boxColor = boxColor;
        }
        public FlowNode(String title, String description, Color boxColor, Color nodeColor) {
            this.title = title;
            this.description = description;
            this.boxColor = boxColor;
            this.nodeColor = nodeColor;
        }
    }
}
