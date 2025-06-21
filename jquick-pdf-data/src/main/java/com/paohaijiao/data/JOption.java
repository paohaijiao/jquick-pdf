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

package com.paohaijiao.data;

import com.paohaijiao.data.axis.JAxis;
import com.paohaijiao.data.code.JEasing;
import com.paohaijiao.data.code.JSymbol;
import com.paohaijiao.data.code.JTrigger;
import com.paohaijiao.data.option.JNoDataLoadingOption;
import com.paohaijiao.data.series.JParallel;
import com.paohaijiao.data.series.JSeries;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Description: Option
 *
 * @author martin
 */
@Getter
@Setter
public class JOption implements Serializable {

    private static final long serialVersionUID = 4664955083296866542L;

    /**
     * 全图默认背景，（详见backgroundColor），默认为无，透明
     */
    private Object backgroundColor;
    /**
     * 数值系列的颜色列表，（详见color），可配数组，eg：['#87cefa', 'rgba(123,123,123,0.5)','...']，当系列数量个数比颜色列表长度大时将循环选取
     */
    private List<Object> color;
    /**
     * 非IE8-支持渲染为图片，（详见renderAsImage）
     * {boolean | string} false，非IE8-支持渲染为图片，可设为true或指定图片格式（png | jpeg），渲染为图片后实例依然可用（如setOption，resize等），但各种交互失效
     */
    private Object renderAsImage;
    /**
     * 是否启用拖拽重计算特性，默认关闭，（详见calculable，相关的还有 calculableColor， calculableHolderColor， nameConnector， valueConnector）
     */
    private Boolean calculable;
    /**
     * 是否启用图表初始化动画，默认开启，建议IE8-关闭，（详见 animation，相关的还有 addDataAnimation， animationThreshold， animationDuration， animationEasing）
     */
    private Boolean animation;
    /**
     * 时间轴（详见timeline），每个图表最多仅有一个时间轴控件
     */
    private JTimeline timeline;
    /**
     * 标题（详见title），每个图表最多仅有一个标题控件
     */
    private JTitle title;
    /**
     * 工具箱（详见toolbox），每个图表最多仅有一个工具箱
     */
    private JToolbox toolbox;
    /**
     * 提示框（详见tooltip），鼠标悬浮交互时的信息提示
     */
    private JTooltip tooltip;
    /**
     * 图例（详见legend），每个图表最多仅有一个图例，混搭图表共享
     */
    private JLegend legend;
    /**
     * 值域选择（详见dataRange）,值域范围
     */
    private JDataRange dataRange;
    /**
     * 数据区域缩放（详见dataZoom）,数据展现范围选择
     */
    private List<JDataZoom> dataZoom;
    /**
     * 缩放漫游组件（详见RoamController）,数据缩放漫游选择
     */
    private JRoamController roamController;
    /**
     * 直角坐标系内绘图网格（详见grid）
     */
    private JGrid grid;
    /**
     * 直角坐标系中横轴数组（详见xAxis），数组中每一项代表一条横轴坐标轴，标准（1.0）中规定最多同时存在2条横轴
     */
    private List<JAxis> xAxis;
    /**
     * 直角坐标系中纵轴数组（详见yAxis），数组中每一项代表一条纵轴坐标轴，标准（1.0）中规定最多同时存在2条纵轴
     */
    private List<JAxis> yAxis;
    /**
     * 驱动图表生成的数据内容（详见series），数组中每一项代表一个系列的特殊选项及数据
     */
    private List<JSeries> series;
    /**
     * 极坐标
     */
    private List<JPolar> polar;
    /**
     * 默认标志图形类型列表，循环使用
     */
    private List<JSymbol> symbolList;
    /**
     * 拖拽重计算提示边框颜色
     */
    private String calculableColor;
    /**
     * 可计算占位提示颜色,默认值 '#ccc'
     */
    private String calculableHolderColo;
    /**
     * 数据合并名字间连接符,默认值'&'
     */
    private String nameConnector;
    /**
     * 数据合并名字与数值间连接符,默认值 ':'
     */
    private String valueConnector;
    /**
     * 是否启用动态数据接口动画效果，默认开启，建议IE8-关闭
     */
    private Boolean addDataAnimation;
    /**
     * 动画元素阀值，产生的图形原素超过2000不出动画，默认开启，建议IE8-关闭
     */
    private Integer animationThreshold;
    /**
     * 进入动画时长，单位ms
     */
    private Integer animationDuration;
    /**
     * 更新动画时长，单位ms
     */
    private Integer animationDurationUpdate;
    /**
     * 主元素的缓动效果
     */
    private JEasing animationEasing;
    /**
     * 数据更新动画的缓动效果
     */
    private Object animationEasingUpdate;
    /**
     * 无数据时载入配置
     */
    private JNoDataLoadingOption noDataLoadingOption;
    /**
     * 当使用timeline时，每一组数据要放到单独的option中
     */
    private List<JOption> options;
    /**
     * 样式
     */
    private JItemStyle itemStyle;
    /**
     * 地理坐标系组件
     */
    private JGeo geo;
    /**
     * 平行坐标系介绍
     */
    private JParallel parallel;
    /**
     * visualMap 是视觉映射组件，用于进行『视觉编码』，也就是将数据映射到视觉元素（视觉通道）
     */
    private List<JVisualMap> visualMap;
    /**
     * 图形元素组件
     */
    private JGraphic graphic;
    /**
     * 雷达图
     */
    private JRadar radar;

    public List<JVisualMap> visualMap() {
        if (this.visualMap == null) {
            this.visualMap = new ArrayList<JVisualMap>();
        }
        return this.visualMap;
    }

    public JVisualMap visualMapNew() {
        JVisualMap v = new JVisualMap();
        this.visualMap().add(v);
        return v;
    }

    public JOption visualMap(List<JVisualMap> visualMap) {
        this.visualMap = visualMap;
        return this;
    }

    public List<JVisualMap> getVisualMap() {
        return visualMap;
    }

    public void setVisualMap(List<JVisualMap> visualMap) {
        this.visualMap = visualMap;
    }

    public Object animationEasingUpdate() {
        return this.animationEasingUpdate;
    }

    public JOption animationEasingUpdate(Object animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public JOption animationEasingUpdate(JEasing animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Object getAnimationEasingUpdate() {
        return animationEasingUpdate;
    }

    public void setAnimationEasingUpdate(Object animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
    }

    public JParallel parallel() {
        if (this.parallel == null) {
            this.parallel = new JParallel();
        }
        return this.parallel;
    }

    public JOption parallel(JParallel parallel) {
        this.parallel = parallel;
        return this;
    }

    public JParallel getParallel() {
        return parallel;
    }

    public void setParallel(JParallel parallel) {
        this.parallel = parallel;
    }

    public JGeo geo() {
        if (this.geo == null) {
            this.geo = new JGeo();
        }
        return this.geo;
    }

    public JOption geo(JGeo geo) {
        this.geo = geo;
        return this;
    }


    public JGeo getGeo() {
        return geo;
    }

    public void setGeo(JGeo geo) {
        this.geo = geo;
    }

    /**
     * 获取symbolList值
     */
    public List<JSymbol> symbolList() {
        return this.symbolList;
    }

    /**
     * 设置symbolList值
     *
     * @param symbolList
     */
    public JOption symbolList(List<JSymbol> symbolList) {
        if (this.symbolList == null) {
            this.symbolList = new LinkedList<JSymbol>();
        }
        this.symbolList = symbolList;
        return this;
    }

    /**
     * 设置symbolList值
     *
     * @param symbolList
     */
    public JOption symbolList(JSymbol... symbolList) {
        if (symbolList == null || symbolList.length == 0) {
            return this;
        }
        this.symbolList().addAll(Arrays.asList(symbolList));
        return this;
    }

    /**
     * 获取calculableColor值
     */
    public String calculableColor() {
        return this.calculableColor;
    }

    /**
     * 设置calculableColor值
     *
     * @param calculableColor
     */
    public JOption calculableColor(String calculableColor) {
        this.calculableColor = calculableColor;
        return this;
    }

    /**
     * 获取calculableHolderColo值
     */
    public String calculableHolderColo() {
        return this.calculableHolderColo;
    }

    /**
     * 设置calculableHolderColo值
     *
     * @param calculableHolderColo
     */
    public JOption calculableHolderColo(String calculableHolderColo) {
        this.calculableHolderColo = calculableHolderColo;
        return this;
    }

    /**
     * 获取nameConnector值
     */
    public String nameConnector() {
        return this.nameConnector;
    }

    /**
     * 设置nameConnector值
     *
     * @param nameConnector
     */
    public JOption nameConnector(String nameConnector) {
        this.nameConnector = nameConnector;
        return this;
    }

    /**
     * 获取valueConnector值
     */
    public String valueConnector() {
        return this.valueConnector;
    }

    /**
     * 设置valueConnector值
     *
     * @param valueConnector
     */
    public JOption valueConnector(String valueConnector) {
        this.valueConnector = valueConnector;
        return this;
    }

    /**
     * 获取addDataAnimation值
     */
    public Boolean addDataAnimation() {
        return this.addDataAnimation;
    }

    /**
     * 设置addDataAnimation值
     *
     * @param addDataAnimation
     */
    public JOption addDataAnimation(Boolean addDataAnimation) {
        this.addDataAnimation = addDataAnimation;
        return this;
    }

    /**
     * 获取animationThreshold值
     */
    public Integer animationThreshold() {
        return this.animationThreshold;
    }

    /**
     * 设置animationThreshold值
     *
     * @param animationThreshold
     */
    public JOption animationThreshold(Integer animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    /**
     * 获取animationDuration值
     */
    public Integer animationDuration() {
        return this.animationDuration;
    }

    /**
     * 设置animationDuration值
     *
     * @param animationDuration
     */
    public JOption animationDuration(Integer animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    /**
     * 获取animationDurationUpdate值
     */
    public Integer animationDurationUpdate() {
        return this.animationDurationUpdate;
    }

    /**
     * 设置animationDurationUpdate值
     *
     * @param animationDurationUpdate
     */
    public JOption animationDurationUpdate(Integer animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    /**
     * 获取animationEasing值
     */
    public JEasing animationEasing() {
        return this.animationEasing;
    }

    /**
     * 设置animationEasing值
     *
     * @param animationEasing
     */
    public JOption animationEasing(JEasing animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    /**
     * 获取noDataLoadingOption值
     */
    public JNoDataLoadingOption noDataLoadingOption() {
        if (this.noDataLoadingOption == null) {
            this.noDataLoadingOption = new JNoDataLoadingOption();
        }
        return this.noDataLoadingOption;
    }

    /**
     * 设置noDataLoadingOption值
     *
     * @param noDataLoadingOption
     */
    public JOption noDataLoadingOption(JNoDataLoadingOption noDataLoadingOption) {
        this.noDataLoadingOption = noDataLoadingOption;
        return this;
    }

    /**
     * 获取itemStyle值
     */
    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }

    /**
     * 设置itemStyle值
     *
     * @param itemStyle
     */
    public JOption itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    /**
     * 获取polar值
     */
    public List<JPolar> polar() {
        if (this.polar == null) {
            this.polar = new ArrayList<JPolar>();
        }
        return this.polar;
    }

    /**
     * 设置polar值
     *
     * @param polar
     */
    public JOption polar(List<JPolar> polar) {
        this.polar = polar;
        return this;
    }

    /**
     * 设置values值
     *
     * @param values
     */
    public JOption polar(JPolar... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.polar().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 设置timeline值
     *
     * @param timeline
     */
    public JOption timeline(JTimeline timeline) {
        this.timeline = timeline;
        return this;
    }

    /**
     * 设置title值
     *
     * @param title
     */
    public JOption title(JTitle title) {
        this.title = title;
        return this;
    }

    /**
     * 标题
     *
     * @param text
     * @return
     */
    public JOption title(String text) {
        this.title().text(text);
        return this;
    }

    /**
     * 标题和副标题
     *
     * @param text
     * @param subtext
     * @return
     */
    public JOption title(String text, String subtext) {
        this.title().text(text).subtext(subtext);
        return this;
    }

    /**
     * 设置toolbox值
     *
     * @param toolbox
     */
    public JOption toolbox(JToolbox toolbox) {
        this.toolbox = toolbox;
        return this;
    }

    /**
     * 设置tooltip值
     *
     * @param tooltip
     */
    public JOption tooltip(JTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    /**
     * 设置trigger值
     *
     * @param trigger
     */
    public JOption tooltip(JTrigger trigger) {
        this.tooltip().trigger(trigger);
        return this;
    }

    /**
     * 设置legend值
     *
     * @param legend
     */
    public JOption legend(JLegend legend) {
        this.legend = legend;
        return this;
    }

    /**
     * 设置dataRange值
     *
     * @param dataRange
     */
    public JOption dataRange(JDataRange dataRange) {
        this.dataRange = dataRange;
        return this;
    }

    /**
     * 设置dataZoom值
     *
     * @param dataZoom
     */
    public JOption dataZoom(List<JDataZoom> dataZoom) {
        this.dataZoom = dataZoom;
        return this;
    }

    /**
     * 设置dataZoom值
     *
     * @param dataZoom
     */
    public JOption dataZoom(JDataZoom... dataZoom) {
        if (dataZoom == null || dataZoom.length == 0) {
            return this;
        }
        this.dataZoom().addAll(Arrays.asList(dataZoom));
        return this;
    }

    /**
     * 设置grid值
     *
     * @param grid
     */
    public JOption grid(JGrid grid) {
        this.grid = grid;
        return this;
    }

    /**
     * 设置xAxis值
     *
     * @param xAxis
     */
    public JOption xAxis(List<JAxis> xAxis) {
        this.xAxis = xAxis;
        return this;
    }

    /**
     * 设置yAxis值
     *
     * @param yAxis
     */
    public JOption yAxis(List<JAxis> yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    /**
     * 设置series值
     *
     * @param series
     */
    public JOption series(List<JSeries> series) {
        this.series = series;
        return this;
    }

    /**
     * 设置options值
     *
     * @param options
     */
    public JOption options(List<JOption> options) {
        this.options = options;
        return this;
    }

    /**
     * 获取backgroundColor值
     */
    public Object backgroundColor() {
        return this.backgroundColor;
    }

    /**
     * 设置backgroundColor值
     *
     * @param backgroundColor
     */
    public JOption backgroundColor(Object backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * 获取color值
     */
    public List<Object> color() {
        if (this.color == null) {
            this.color = new ArrayList<Object>();
        }
        return this.color;
    }

    /**
     * 设置color值
     *
     * @param colors
     */
    public JOption color(Object... colors) {
        if (colors == null || colors.length == 0) {
            return this;
        }
        this.color().addAll(Arrays.asList(colors));
        return this;
    }

    /**
     * 获取renderAsImage值
     */
    public Object renderAsImage() {
        return this.renderAsImage;
    }

    /**
     * 设置renderAsImage值
     *
     * @param renderAsImage
     */
    public JOption renderAsImage(Object renderAsImage) {
        this.renderAsImage = renderAsImage;
        return this;
    }

    /**
     * 获取calculable值
     */
    public Boolean calculable() {
        return this.calculable;
    }

    /**
     * 设置calculable值
     *
     * @param calculable
     */
    public JOption calculable(Boolean calculable) {
        this.calculable = calculable;
        return this;
    }

    /**
     * 获取animation值
     */
    public Boolean animation() {
        return this.animation;
    }

    /**
     * 设置animation值
     *
     * @param animation
     */
    public JOption animation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    /**
     * 时间轴（详见timeline），每个图表最多仅有一个时间轴控件
     */
    public JTimeline timeline() {
        if (this.timeline == null) {
            this.timeline = new JTimeline();
        }
        return this.timeline;
    }

    /**
     * 标题（详见title），每个图表最多仅有一个标题控件
     */
    public JTitle title() {
        if (this.title == null) {
            this.title = new JTitle();
        }
        return this.title;
    }

    /**
     * 工具箱（详见toolbox），每个图表最多仅有一个工具箱
     */
    public JToolbox toolbox() {
        if (this.toolbox == null) {
            this.toolbox = new JToolbox();
        }
        return this.toolbox;
    }

    /**
     * 提示框（详见tooltip），鼠标悬浮交互时的信息提示
     */
    public JTooltip tooltip() {
        if (this.tooltip == null) {
            this.tooltip = new JTooltip();
        }
        return this.tooltip;
    }

    /**
     * 图例（详见legend），每个图表最多仅有一个图例，混搭图表共享
     */
    public JLegend legend() {
        if (this.legend == null) {
            this.legend = new JLegend();
        }
        return this.legend;
    }

    /**
     * 添加图例（详见legend），每个图表最多仅有一个图例，混搭图表共享
     *
     * @param values
     * @return
     */
    public JOption legend(Object... values) {
        this.legend().data(values);
        return this;
    }

    /**
     * 值域选择（详见dataRange）,值域范围
     */
    public JDataRange dataRange() {
        if (this.dataRange == null) {
            this.dataRange = new JDataRange();
        }
        return this.dataRange;
    }

    /**
     * 数据区域缩放（详见dataZoom）,数据展现范围选择
     */
    public JDataZoom dataZoomNew() {
        JDataZoom dataZoom = new JDataZoom();
        this.dataZoom().add(dataZoom);
        return dataZoom;
    }

    /**
     * 数据区域缩放（详见dataZoom）,数据展现范围选择
     */
    public List<JDataZoom> dataZoom() {
        if (this.dataZoom == null) {
            this.dataZoom = new ArrayList<JDataZoom>();
        }
        return this.dataZoom;
    }

    /**
     * 数据缩放漫游选择（详见roamController）,数据缩放漫游选择
     */
    public JRoamController roamController() {
        if (this.roamController == null) {
            this.roamController = new JRoamController();
        }
        return this.roamController;
    }

    /**
     * 直角坐标系内绘图网格（详见grid）
     */
    public JGrid grid() {
        if (this.grid == null) {
            this.grid = new JGrid();
        }
        return this.grid;
    }

    /**
     * 直角坐标系中横轴数组（详见xAxis），数组中每一项代表一条横轴坐标轴，标准（1.0）中规定最多同时存在2条横轴
     */
    public List<JAxis> xAxis() {
        if (this.xAxis == null) {
            this.xAxis = new ArrayList<JAxis>();
        }
        return this.xAxis;
    }

    /**
     * 添加x轴
     *
     * @param values
     * @return
     */
    public JOption xAxis(JAxis... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        if (this.xAxis().size() == 2) {
            throw new RuntimeException("xAxis已经存在2个，无法继续添加!");
        }
        if (this.xAxis().size() + values.length > 2) {
            throw new RuntimeException("添加的xAxis超出了最大允许的范围:2!");
        }
        this.xAxis().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 直角坐标系中横轴数组（详见xAxis），数组中每一项代表一条横轴坐标轴，标准（1.0）中规定最多同时存在2条横轴
     */
    public List<JAxis> yAxis() {
        if (this.yAxis == null) {
            this.yAxis = new ArrayList<JAxis>();
        }
        return this.yAxis;
    }

    /**
     * 添加y轴
     *
     * @param values
     * @return
     */
    public JOption yAxis(JAxis... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        if (this.yAxis().size() == 2) {
            throw new RuntimeException("yAxis已经存在2个，无法继续添加!");
        }
        if (this.yAxis().size() + values.length > 2) {
            throw new RuntimeException("添加的yAxis超出了最大允许的范围:2!");
        }
        this.yAxis().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 驱动图表生成的数据内容（详见series），数组中每一项代表一个系列的特殊选项及数据
     */
    public List<JSeries> series() {
        if (this.series == null) {
            this.series = new ArrayList<JSeries>();
        }
        return this.series;
    }

    /**
     * 添加数据
     *
     * @param values
     * @return
     */
    public JOption series(JSeries... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.series().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 当使用timeline时，每一组数据要放到单独的option中
     */
    public List<JOption> options() {
        if (this.options == null) {
            this.options = new ArrayList<JOption>();
        }
        return this.options;
    }

    /**
     * 添加Option数据
     *
     * @param values
     * @return
     */
    public JOption options(JOption... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.options().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 添加图形元素组件
     *
     * @param graphic
     * @return
     */
    public JOption graphic(JGraphic graphic) {
        this.graphic = graphic;
        return this;

    }

    /**
     * 雷达图
     *
     * @param radar
     * @return
     */
    public JOption radar(JRadar radar) {
        this.radar = radar;
        return this;
    }

    /**
     * 获取xAxis值
     */
    public List<JAxis> getxAxis() {
        return xAxis;
    }

    /**
     * 设置xAxis值
     *
     * @param xAxis
     */
    public void setxAxis(List<JAxis> xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * 获取yAxis值
     */
    public List<JAxis> getyAxis() {
        return yAxis;
    }

    /**
     * 设置yAxis值
     *
     * @param yAxis
     */
    public void setyAxis(List<JAxis> yAxis) {
        this.yAxis = yAxis;
    }

}
