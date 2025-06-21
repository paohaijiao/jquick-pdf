package com.paohaijiao.data.series.other;

import com.paohaijiao.data.code.JBrushType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 涟漪特效相关配置
 *
 * @author martin
 */
@Getter
@Setter
public class JRippleEffect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 动画的时间
     */
    private Integer period;
    /**
     * 动画中波纹的最大缩放比例
     */
    private Double scale;
    /**
     * 波纹的填充方式，可选 'stroke' 和 'fill'
     */
    private JBrushType brushType;

    public Integer period() {
        return this.period;
    }

    public JRippleEffect period(Integer period) {
        this.period = period;
        return this;
    }

    public Double scale() {
        return this.scale;
    }

    public JRippleEffect scale(Double scale) {
        this.scale = scale;
        return this;
    }

    public JBrushType brushType() {
        return this.brushType;
    }

    public JRippleEffect brushType(JBrushType brushType) {
        this.brushType = brushType;
        return this;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public JBrushType getBrushType() {
        return brushType;
    }

    public void setBrushType(JBrushType brushType) {
        this.brushType = brushType;
    }
}
