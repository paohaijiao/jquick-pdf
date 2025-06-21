package com.paohaijiao.data.axis;

import com.paohaijiao.data.code.JAxisType;
import com.paohaijiao.data.code.JNameLocation;
import com.paohaijiao.data.style.JAreaSelectStyle;
import com.paohaijiao.data.style.JNameTextStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * 平行坐标系
 *
 * @author martin
 */
@Getter
@Setter
public class JParallelAxis extends JAxis<JParallelAxis> {
    /**
     * 坐标轴的维度号
     */
    private Integer dim;
    /**
     * 用于定义『坐标轴』对应到哪个『坐标系』中
     */
    private Integer parallelIndex;
    /**
     * 坐标轴的分割段数，需要注意的是这个分割段数只是个预估值，最后实际显示的段数会在这个基础上根据分割后坐标轴刻度显示的易读程度作调整
     */
    private Integer splitNumber;
    /**
     * 坐标轴名称显示位置
     */
    private JNameLocation nameLocation;
    /**
     * 在坐标轴上可以进行框选，这里是一些框选的设置
     */
    private JAreaSelectStyle areaSelectStyle;
    /**
     * 坐标轴名称的文字样式
     */
    private JNameTextStyle nameTextStyle;

    /**
     * 构造函数
     */
    public JParallelAxis() {
        this.type(JAxisType.value);
    }

    public Integer dim() {
        return this.dim;
    }

    public JParallelAxis dim(Integer dim) {
        this.dim = dim;
        return this;
    }

    public Integer parallelIndex() {
        return this.parallelIndex;
    }

    public JParallelAxis parallelIndex(Integer parallelIndex) {
        this.parallelIndex = parallelIndex;
        return this;
    }

    public Integer splitNumber() {
        return this.splitNumber;
    }

    public JParallelAxis splitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public JNameLocation nameLocation() {
        return this.nameLocation;
    }

    public JParallelAxis nameLocation(JNameLocation nameLocation) {
        this.nameLocation = nameLocation;
        return this;
    }
    public JAreaSelectStyle areaSelectStyle() {
        if (this.areaSelectStyle == null) {
            this.areaSelectStyle = new JAreaSelectStyle();
        }
        return this.areaSelectStyle;
    }

    public JParallelAxis areaSelectStyle(JAreaSelectStyle areaSelectStyle) {
        this.areaSelectStyle = areaSelectStyle;
        return this;
    }

    public JNameTextStyle nameTextStyle() {
        if (this.nameTextStyle == null) {
            this.nameTextStyle = new JNameTextStyle();
        }
        return this.nameTextStyle;
    }

    public JParallelAxis nameTextStyle(JNameTextStyle nameTextStyle) {
        this.nameTextStyle = nameTextStyle;
        return this;
    }
}
