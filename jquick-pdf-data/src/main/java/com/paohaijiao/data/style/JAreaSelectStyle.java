package com.paohaijiao.data.style;

/**
 * 在坐标轴上可以进行框选，这里是一些框选的设置
 *
 * @author martin
 */
public class JAreaSelectStyle {
    private Integer width;
    private Integer borderWidth;
    private String borderColor;
    private String color;
    private Double opacity;

    public Integer width() {
        return this.width;
    }

    public JAreaSelectStyle width(Integer width) {
        this.width = width;
        return this;
    }

    public Integer borderWidth() {
        return this.borderWidth;
    }

    public JAreaSelectStyle borderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public String borderColor() {
        return this.borderColor;
    }

    public JAreaSelectStyle borderColor(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public String color() {
        return this.color;
    }

    public JAreaSelectStyle color(String color) {
        this.color = color;
        return this;
    }

    public Double opacity() {
        return this.opacity;
    }

    public JAreaSelectStyle opacity(Double opacity) {
        this.opacity = opacity;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getOpacity() {
        return opacity;
    }

    public void setOpacity(Double opacity) {
        this.opacity = opacity;
    }
}
