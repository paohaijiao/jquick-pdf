package com.github.paohaijiao.bubble;

/**
 * 气泡图数据点
 */
public class BubbleDataPoint {
    private Object x;        // X轴值（日期、类别等）
    private Number y;        // Y轴值（AQI数值）
    private Number size;     // 气泡大小（PM2.5浓度）
    private Object category; // 分类（AQI等级）
    private String name;     // 数据点名称

    public BubbleDataPoint() {}

    public BubbleDataPoint(Object x, Number y, Number size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public BubbleDataPoint(Object x, Number y, Number size, Object category) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.category = category;
    }

    public BubbleDataPoint(Object x, Number y, Number size, Object category, String name) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.category = category;
        this.name = name;
    }

    // Getters and Setters
    public Object getX() { return x; }
    public void setX(Object x) { this.x = x; }

    public Number getY() { return y; }
    public void setY(Number y) { this.y = y; }

    public Number getSize() { return size; }
    public void setSize(Number size) { this.size = size; }

    public Object getCategory() { return category; }
    public void setCategory(Object category) { this.category = category; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
