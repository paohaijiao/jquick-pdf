package com.paohaijiao.data;

import com.paohaijiao.data.code.*;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * 视觉映射组件，用于进行『视觉编码』，也就是将数据映射到视觉元素（视觉通道）
 *
 * @author martin
 */
@Getter
@Setter
public class JVisualMap extends JBasic<JVisualMap> {
    /**
     * 类型
     */
    private JVisualMapType type;
    /**
     * 最小值
     */
    private Integer min;
    /**
     * 最大值
     */
    private Integer max;
    /**
     * 是否启用值域漫游
     */
    private Boolean calculable;
    /**
     * 拖拽时，是否实时更新
     */
    private Boolean realtime;
    /**
     * 是否反转
     */
    private Boolean inverse;
    /**
     * 数据展示的小数精度，默认为0，无小数点
     */
    private Integer precision;
    /**
     * 图形的宽度，即长条的宽度
     */
    private Integer itemWidth;
    /**
     * 图形的高度，即长条的高度
     */
    private Integer itemHeight;
    private JSymbol itemSymbol;
    /**
     * 指定组件中手柄和文字的摆放关系
     */
    private JAlign align;
    /**
     * handle 指『拖拽手柄』。handlePosition 指定了手柄的位置
     */
    private JAlign handlePosition;
    /**
     * 指定用数据的『哪个维度』，映射到视觉元素上
     */
    private Object dimension;
    /**
     * 指定取哪个系列的数据，即哪个系列的 series.data
     */
    private Integer seriesIndex;
    /**
     * 定义 在选中范围中 的视觉元素
     */
    private JVisualMapType inRange;
    /**
     * 定义 在选中范围外 的视觉元素
     */
    private JVisualMapType outOfRange;
    /**
     * 水平（'horizontal'）或者竖直（'vertical'）
     */
    private JOrient orient;
    /**
     * 标签的格式化工具
     */
    private String formatter;
    /**
     * 对于连续型数据，自动平均切分成几段
     */
    private Integer splitNumber;
    private JSelectedMode selectedMode;
    private JTextStyle textStyle;
    private Object[] color;
    private Object[] text;
    private Object[] textGap;
    private Object[] pieces;
    private Object[] categories;

    public JVisualMapType type() {
        return this.type;
    }

    public JVisualMap type(JVisualMapType type) {
        this.type = type;
        return this;
    }

    public Integer min() {
        return this.min;
    }

    public JVisualMap min(Integer min) {
        this.min = min;
        return this;
    }

    public Integer max() {
        return this.max;
    }

    public JVisualMap max(Integer max) {
        this.max = max;
        return this;
    }

    public Boolean calculable() {
        return this.calculable;
    }

    public JVisualMap calculable(Boolean calculable) {
        this.calculable = calculable;
        return this;
    }

    public Boolean realtime() {
        return this.realtime;
    }

    public JVisualMap realtime(Boolean realtime) {
        this.realtime = realtime;
        return this;
    }

    public Boolean inverse() {
        return this.inverse;
    }

    public JVisualMap inverse(Boolean inverse) {
        this.inverse = inverse;
        return this;
    }

    public Integer precision() {
        return this.precision;
    }

    public JVisualMap precision(Integer precision) {
        this.precision = precision;
        return this;
    }

    public Integer itemWidth() {
        return this.itemWidth;
    }

    public JVisualMap itemWidth(Integer itemWidth) {
        this.itemWidth = itemWidth;
        return this;
    }

    public Integer itemHeight() {
        return this.itemHeight;
    }

    public JVisualMap itemHeight(Integer itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    public JSymbol itemSymbol() {
        return this.itemSymbol;
    }

    public JVisualMap itemSymbol(JSymbol itemSymbol) {
        this.itemSymbol = itemSymbol;
        return this;
    }

    public JAlign align() {
        return this.align;
    }

    public JVisualMap align(JAlign align) {
        this.align = align;
        return this;
    }

    public JAlign handlePosition() {
        return this.handlePosition;
    }

    public JVisualMap handlePosition(JAlign handlePosition) {
        this.handlePosition = handlePosition;
        return this;
    }

    public Object dimension() {
        return this.dimension;
    }

    public JVisualMap dimension(Object dimension) {
        this.dimension = dimension;
        return this;
    }

    public Integer seriesIndex() {
        return this.seriesIndex;
    }

    public JVisualMap seriesIndex(Integer seriesIndex) {
        this.seriesIndex = seriesIndex;
        return this;
    }

    public JVisualMapType inRange() {
        return this.inRange;
    }

    public JVisualMap inRange(JVisualMapType inRange) {
        this.inRange = inRange;
        return this;
    }

    public JVisualMapType outOfRange() {
        return this.outOfRange;
    }

    public JVisualMap outOfRange(JVisualMapType outOfRange) {
        this.outOfRange = outOfRange;
        return this;
    }

    public JOrient orient() {
        return this.orient;
    }

    public JVisualMap orient(JOrient orient) {
        this.orient = orient;
        return this;
    }

    public String formatter() {
        return this.formatter;
    }

    public JVisualMap formatter(String formatter) {
        this.formatter = formatter;
        return this;
    }

    public Integer splitNumber() {
        return this.splitNumber;
    }

    public JVisualMap splitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public JSelectedMode selectedMode() {
        return this.selectedMode;
    }

    public JVisualMap selectedMode(JSelectedMode selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    public JTextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        return this.textStyle;
    }

    public JVisualMap textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public JVisualMap color(Object[] color) {
        this.color = color;
        return this;
    }

    public JVisualMap text(Object[] text) {
        this.text = text;
        return this;
    }

    public JVisualMap textGap(Object[] textGap) {
        this.textGap = textGap;
        return this;
    }

    public JVisualMap pieces(Object[] pieces) {
        this.pieces = pieces;
        return this;
    }

    public JVisualMap categories(Object[] categories) {
        this.categories = categories;
        return this;
    }
}
