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

package com.paohaijiao.data.series;

import com.paohaijiao.data.code.JLayout;
import com.paohaijiao.data.code.JRoam;
import com.paohaijiao.data.code.JSeriesType;
import com.paohaijiao.data.code.JSymbol;
import com.paohaijiao.data.series.force.JCategory;
import com.paohaijiao.data.series.force.JLink;
import com.paohaijiao.data.series.force.JNode;
import com.paohaijiao.data.series.other.JForce;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 关系图
 *
 * @author martin
 */
@Getter
@Setter
public class JGraph extends JSeries<JGraph> {
    /**
     * 图的布局
     */
    private JLayout layout;
    /**
     * 力引导布局相关的配置项
     */
    private JForce force;
    /**
     * 是否开启滚轮缩放和拖拽漫游，默认为false（关闭），其他有效输入为true（开启），'scale'（仅开启滚轮缩放），'move'（仅开启拖拽漫游）
     */
    private Object roam;
    /**
     * 鼠标漫游缩放时节点的相应缩放比例，当设为0时节点不随着鼠标的缩放而缩放
     */
    private Double nodeScaleRatio;
    /**
     * 力导向图中节点的分类
     */
    private List<JCategory> categories;
    /**
     * 力导向图的顶点数据
     */
    private List nodes;
    /**
     * 力导向图的边数据
     */
    private List<JLink> links;
    /**
     * 力导向图的边数据
     */
    private List<JLink> edges;
    /**
     * 布局中心，可以是绝对值或者相对百分比
     */
    private Object center;
    /**
     * 布局大小，可以是绝对值或者相对百分比
     */
    private Object size;
    /**
     * 防止节点和节点，节点和边之间的重叠
     */
    private Boolean preventOverlap;
    /**
     * 布局冷却因子，值越小结束时间越短，值越大时间越长但是结果也越收敛
     */
    private Object coolDown;
    /**
     * 是否根据屏幕比例拉伸
     */
    private Boolean ratioScaling;
    /**
     * 顶点数据映射成圆半径后的最小半径
     */
    private Integer minRadius;
    /**
     * 顶点数据映射成圆半径后的最大半径
     */
    private Integer maxRadius;
    /**
     * 力导向图的边两端图形样式，可指定为'arrow', 详见symbolList
     */
    private Object linkSymbol;
    /**
     * 力导向图的边两端图形大小
     */
    private Integer linkSymbolSize;
    /**
     * 布局缩放系数，并不完全精确, 效果跟布局大小类似
     */
    private Double scaling;
    /**
     * 向心力系数，系数越大则节点越往中心靠拢
     */
    private Double gravity;
    /**
     * 节点是否能被拖拽
     */
    private Boolean draggable;
    /**
     * 在 500+ 顶点的图上建议设置 large 为 true, 会使用 Barnes-Hut simulation, 同时开启 useWorker 并且把 steps 值调大
     */
    private Boolean large;
    /**
     * 是否在浏览器支持 web worker 的时候把布局计算放入 web worker 中
     */
    private Boolean useWorker;
    /**
     * 每一帧布局计算的迭代次数，因为每一帧绘制的时间经常会比布局时间长很多，所以在使用 web worker 的时候可以把 steps 调大来平衡两者的时间从而达到效率最优化
     */
    private Integer steps;

    /**
     * 构造函数
     */
    public JGraph() {
        this.type(JSeriesType.graph);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JGraph(String name) {
        super(name);
        this.type(JSeriesType.graph);
    }

    /**
     * 构造函数
     *
     * @param name
     * @param layout
     */
    public JGraph(String name, JLayout layout) {
        super(name);
        this.type(JSeriesType.graph);
        this.layout = layout;
    }

    public JLayout layout() {
        return this.layout;
    }

    public JGraph layout(JLayout layout) {
        this.layout = layout;
        return this;
    }

    public JForce force() {
        if (this.force == null) {
            this.force = new JForce();
        }
        return this.force;
    }

    public JGraph force(JForce force) {
        this.force = force;
        return this;
    }

    public Double nodeScaleRatio() {
        return this.nodeScaleRatio;
    }

    public JGraph nodeScaleRatio(Double nodeScaleRatio) {
        this.nodeScaleRatio = nodeScaleRatio;
        return this;
    }

    /**
     * 获取coolDown值
     */
    public Object coolDown() {
        return this.coolDown;
    }

    /**
     * 设置coolDown值
     *
     * @param coolDown
     */
    public JGraph coolDown(Object coolDown) {
        this.coolDown = coolDown;
        return this;
    }

    /**
     * 获取ratioScaling值
     */
    public Boolean ratioScaling() {
        return this.ratioScaling;
    }

    /**
     * 设置ratioScaling值
     *
     * @param ratioScaling
     */
    public JGraph ratioScaling(Boolean ratioScaling) {
        this.ratioScaling = ratioScaling;
        return this;
    }


    /**
     * 获取preventOverlap值
     */
    public Boolean preventOverlap() {
        return this.preventOverlap;
    }

    /**
     * 设置preventOverlap值
     *
     * @param preventOverlap
     */
    public JGraph preventOverlap(Boolean preventOverlap) {
        this.preventOverlap = preventOverlap;
        return this;
    }

    /**
     * 设置categories值
     *
     * @param categories
     */
    public JGraph categories(List<JCategory> categories) {
        this.categories = categories;
        return this;
    }

    /**
     * 设置nodes值
     *
     * @param nodes
     */
    public JGraph nodes(List nodes) {
        this.nodes = nodes;
        return this;
    }

    /**
     * 设置links值
     *
     * @param links
     */
    public JGraph links(List<JLink> links) {
        this.links = links;
        return this;
    }

    /**
     * 设置links值
     *
     * @param edges
     */
    public JGraph edges(List<JLink> edges) {
        this.edges = edges;
        return this;
    }

    /**
     * 力导向图中节点的分类
     */
    public List<JCategory> categories() {
        if (this.categories == null) {
            this.categories = new ArrayList<JCategory>();
        }
        return this.categories;
    }

    /**
     * 添加节点分类
     *
     * @param values
     * @return
     */
    public JGraph categories(JCategory... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.categories().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 添加节点分类，使用分类名
     *
     * @param names
     * @return
     */
    public JGraph categories(String... names) {
        if (names == null || names.length == 0) {
            return this;
        }
        for (String name : names) {
            this.categories().add(new JCategory(name));
        }
        return this;
    }

    /**
     * 添加节点分类，使用分类名
     *
     * @param values
     * @return
     */
    public JGraph categories(Object... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        for (Object value : values) {
            if (value instanceof String) {
                this.categories().add(new JCategory((String) value));
            } else if (value instanceof JCategory) {
                this.categories().add((JCategory) value);
            }
            //其他忽略
        }
        return this;
    }

    /**
     * 力导向图的顶点数据
     */
    public List<JNode> nodes() {
        if (this.nodes == null) {
            this.nodes = new ArrayList();
        }
        return this.nodes;
    }

    /**
     * 添加力导向图的顶点数据
     *
     * @param values
     * @return
     */
    public JGraph nodes(JNode... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.nodes().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 力导向图的边数据
     */
    public List<JLink> links() {
        if (this.links == null) {
            this.links = new ArrayList<JLink>();
        }
        return this.links;
    }

    /**
     * 添加力导向图的边数据
     *
     * @param values
     * @return
     */
    public JGraph links(JLink... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.links().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 力导向图的边数据
     */
    public List<JLink> edges() {
        if (this.edges == null) {
            this.edges = new ArrayList<JLink>();
        }
        return this.edges;
    }

    /**
     * 添加力导向图的边数据
     *
     * @param values
     * @return
     */
    public JGraph edges(JLink... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.edges().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 获取center值
     */
    public Object center() {
        return this.center;
    }

    /**
     * 设置center值
     *
     * @param center
     */
    public JGraph center(Object center) {
        this.center = center;
        return this;
    }

    /**
     * 获取size值
     */
    public Object size() {
        return this.size;
    }

    /**
     * 设置size值
     *
     * @param size
     */
    public JGraph size(Object size) {
        this.size = size;
        return this;
    }

    /**
     * 获取minRadius值
     */
    public Integer minRadius() {
        return this.minRadius;
    }

    /**
     * 设置minRadius值
     *
     * @param minRadius
     */
    public JGraph minRadius(Integer minRadius) {
        this.minRadius = minRadius;
        return this;
    }

    /**
     * 获取maxRadius值
     */
    public Integer maxRadius() {
        return this.maxRadius;
    }

    /**
     * 设置maxRadius值
     *
     * @param maxRadius
     */
    public JGraph maxRadius(Integer maxRadius) {
        this.maxRadius = maxRadius;
        return this;
    }

    /**
     * 获取linkSymbol值
     */
    public Object linkSymbol() {
        return this.linkSymbol;
    }

    /**
     * 设置linkSymbol值
     *
     * @param linkSymbol
     */
    public JGraph linkSymbol(JSymbol linkSymbol) {
        this.linkSymbol = linkSymbol;
        return this;
    }

    /**
     * 设置linkSymbol值
     *
     * @param linkSymbol
     */
    public JGraph linkSymbol(String linkSymbol) {
        this.linkSymbol = linkSymbol;
        return this;
    }

    /**
     * 获取linkSymbolSize值
     */
    public Integer linkSymbolSize() {
        return this.linkSymbolSize;
    }

    /**
     * 设置linkSymbolSize值
     *
     * @param linkSymbolSize
     */
    public JGraph linkSymbolSize(Integer linkSymbolSize) {
        this.linkSymbolSize = linkSymbolSize;
        return this;
    }

    /**
     * 获取scaling值
     */
    public Double scaling() {
        return this.scaling;
    }

    /**
     * 设置scaling值
     *
     * @param scaling
     */
    public JGraph scaling(Double scaling) {
        this.scaling = scaling;
        return this;
    }

    /**
     * 获取gravity值
     */
    public Double gravity() {
        return this.gravity;
    }

    /**
     * 设置gravity值
     *
     * @param gravity
     */
    public JGraph gravity(Double gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 获取draggable值
     */
    public Boolean draggable() {
        return this.draggable;
    }

    /**
     * 设置draggable值
     *
     * @param draggable
     */
    public JGraph draggable(Boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    /**
     * 获取large值
     */
    public Boolean large() {
        return this.large;
    }

    /**
     * 设置large值
     *
     * @param large
     */
    public JGraph large(Boolean large) {
        this.large = large;
        return this;
    }

    /**
     * 获取useWorker值
     */
    public Boolean useWorker() {
        return this.useWorker;
    }

    /**
     * 设置useWorker值
     *
     * @param useWorker
     */
    public JGraph useWorker(Boolean useWorker) {
        this.useWorker = useWorker;
        return this;
    }

    /**
     * 获取steps值
     */
    public Integer steps() {
        return this.steps;
    }

    /**
     * 设置steps值
     *
     * @param steps
     */
    public JGraph steps(Integer steps) {
        this.steps = steps;
        return this;
    }

    /**
     * 获取roam值
     */
    public Object roam() {
        return this.roam;
    }

    /**
     * 设置roam值
     *
     * @param roam
     */
    public JGraph roam(Boolean roam) {
        this.roam = roam;
        return this;
    }

    /**
     * 设置roam值
     *
     * @param roam
     */
    public JGraph roam(JRoam roam) {
        this.roam = roam;
        return this;
    }
}
