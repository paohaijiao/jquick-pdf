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

import com.paohaijiao.data.code.JSeriesType;
import com.paohaijiao.data.series.force.JLink;
import com.paohaijiao.data.series.force.JNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 桑基图
 * 是一种特殊的流图, 它主要用来表示原材料、能量等如何从初始形式经过中间过程的加工、转化到达最终形式。
 *
 * @author martin
 */
@Getter
@Setter
public class JSankey extends JSeries<JSankey> {
    /**
     * 图中每个矩形节点的宽度
     */
    private Integer nodeWidth;
    /**
     * 图中每一列任意两个矩形节点之间的间隔
     */
    private Integer nodeGap;
    /**
     * 布局的迭代次数，用来不断优化图中节点的位置，以减少节点和边之间的相互遮盖
     * 默认布局迭代次数：32
     * 经测试，布局迭代次数不要低于默认值
     */
    private Integer layoutIterations;
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
     * 构造函数
     */
    public JSankey() {
        this.type(JSeriesType.sankey);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JSankey(String name) {
        super(name);
        this.type(JSeriesType.sankey);
    }

    public Integer nodeWidth() {
        return this.nodeWidth;
    }

    public JSankey nodeWidth(Integer nodeWidth) {
        this.nodeWidth = nodeWidth;
        return this;
    }

    public Integer nodeGap() {
        return this.nodeGap;
    }

    public JSankey nodeGap(Integer nodeGap) {
        this.nodeGap = nodeGap;
        return this;
    }

    public Integer layoutIterations() {
        return this.layoutIterations;
    }

    public JSankey layoutIterations(Integer layoutIterations) {
        this.layoutIterations = layoutIterations;
        return this;
    }

    /**
     * 设置nodes值
     *
     * @param nodes
     */
    public JSankey nodes(List nodes) {
        this.nodes = nodes;
        return this;
    }

    /**
     * 设置links值
     *
     * @param links
     */
    public JSankey links(List<JLink> links) {
        this.links = links;
        return this;
    }

    /**
     * 设置links值
     *
     * @param edges
     */
    public JSankey edges(List<JLink> edges) {
        this.edges = edges;
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
    public JSankey nodes(JNode... values) {
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
    public JSankey links(JLink... values) {
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
    public JSankey edges(JLink... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.edges().addAll(Arrays.asList(values));
        return this;
    }
}
