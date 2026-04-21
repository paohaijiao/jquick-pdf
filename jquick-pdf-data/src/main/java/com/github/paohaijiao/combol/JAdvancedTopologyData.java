package com.github.paohaijiao.combol;

import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 网络拓扑图数据类
 */
@Data
public class JAdvancedTopologyData implements JGraphData {
    /**
     * 基础配置
     */
    private int width = 900;

    private int height = 700;

    private String titleText;

    private String subtitleText;

    private String footerText;

    /**
     * 节点和连线数据
     */
    private List<Node> nodes = new ArrayList<>();

    private List<Link> links = new ArrayList<>();

    /**
     * 布局配置
     */
    private boolean autoLayout = true;

    private long layoutIterations = 50;

    private int layoutSeed = 42;

    /**
     * 节点标签偏移量（像素）
     */
    private int nodeLabelOffset = 15;

    /**
     * 是否启用智能标签布局（避免重叠）
     */
    private boolean smartLabelLayout = true;
    /**
     * 样式配置
     */
    private Color backgroundColor = Color.WHITE;

    private Color textColor = Color.BLACK;

    private Color gridColor = new Color(230, 230, 230);

    private Color defaultNodeColor = new Color(66, 133, 244);

    private Color defaultBorderColor = new Color(0, 0, 0);

    private int defaultNodeRadius = 25;

    private NodeShape defaultShape = NodeShape.CIRCLE;

    private int borderWidth = 2;

    /**
     * 连线配置
     */
    private boolean curvedLinks = true;

    private boolean showArrows = true;

    private int arrowSize = 8;

    private boolean showLinkLabels = true;

    /**
     * 动画配置
     */
    private boolean showDataFlow = false;

    private int flowAnimationDuration = 2000;

    private int flowPointSize = 6;

    /**
     * 其他配置
     */
    private boolean showGrid = false;

    private int gridSize = 50;

    private boolean showShadow = true;

    private boolean showIcons = true;

    private boolean showStatus = true;

    private boolean showLegend = true;

    private boolean showLabelBackground = false;

    private Color labelBackgroundColor = new Color(255, 255, 255, 200);

    private Color footerColor = new Color(255, 255, 255, 200);

    private int statusRadius = 6;

    /**
     * 字体配置
     */
    private Font titleFont = new Font("Microsoft YaHei", Font.BOLD, 18);

    private Font subtitleFont = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private Font nodeLabelFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Font linkLabelFont = new Font("Microsoft YaHei", Font.PLAIN, 10);

    private Font legendFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Font footerFont = new Font("Microsoft YaHei", Font.PLAIN, 10);

    /**
     * 节点形状枚举
     */
    public enum NodeShape {
        CIRCLE, RECTANGLE, TRIANGLE, DIAMOND
    }

    /**
     * 节点类
     */
    @Data
    public static class Node {

        private String id;

        private String label;

        private String icon;

        private String status;

        private String legendGroup;

        private Color color;

        private Color borderColor;

        private Integer radius;

        private NodeShape shape;

        private Integer x;

        private Integer y;
    }

    /**
     * 连线类
     */
    @Data
    public static class Link {

        private String sourceId;

        private String targetId;

        private String label;

        private Color lineColor = new Color(100, 100, 100);

        private Color labelColor;

        private Color flowColor;

        private int lineWidth = 2;

        private boolean curved = false;

        private boolean showArrow = false;

        private boolean active = true;
    }
}