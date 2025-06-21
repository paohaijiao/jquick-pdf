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

import com.paohaijiao.data.code.JCalculation;
import com.paohaijiao.data.code.JSelectedMode;
import com.paohaijiao.data.code.JSeriesType;
import lombok.Getter;
import lombok.Setter;

/**
 * 地图
 *
 * @author martin
 */
@Getter
@Setter
public class JMap extends JSeries<JMap> {
    /**
     * 选中模式，默认关闭，可选single，multiple
     *
     * @see JSelectedMode
     */
    private Object selectedMode;
    /**
     * 地图类型，支持world，china及全国34个省市自治区。省市自治区的mapType直接使用简体中文
     * 支持子区域模式，通过主地图类型扩展出所包含的子区域地图，格式为'主地图类型|子区域名称'，如
     * 'world|Brazil'，'china|广东'
     */
    private String mapType;
    /**
     * 地图位置设置，默认只适应上下左右居中可配x，y，width，height，任意参数为空都将根据其他参数自适应
     */
    private JMapLocation mapLocation;
    /**
     * 地图数值计算方式，默认为加和，可选为：'sum'（总数） | 'average'（均值）
     *
     * @see JCalculation
     */
    private JCalculation mapValueCalculation;
    /**
     * 地图数值计算结果小数精度，mapValueCalculation为average时有效，默认为取整，需要小数精度时设置大于0的整数
     */
    private Integer mapValuePrecision;
    /**
     * 显示图例颜色标识（系列标识的小圆点），存在legend时生效
     */
    private Boolean showLegendSymbol;
    /**
     * 是否启用值域漫游组件（dataRange）hover时的联动响应
     */
    private Boolean dataRangeHoverLink;
    /**
     * 是否开启滚轮缩放和拖拽漫游
     */
    private Boolean roam;
    /**
     * 滚轮缩放的极限控制，可指定{max:number, min:number}，其中max为放大系数，有效值应大于1，min为缩小系数，有效值应小于1
     */
    private JScaleLimit scaleLimit;
    /**
     * 自定义地区的名称映射，如{'China' : '中国'}
     */
    private Object nameMap;
    /**
     * 地区的名称文本位置修正，数值单位为px，正值为左下偏移，负值为右上偏移，如{'China' : [10, -10]}
     */
    private Object textFixed;
    /**
     * 通过绝对经纬度指定地区的名称文本位置，如{'Islands':[113.95, 22.26]}，香港离岛区名称显示定位到东经113.95，北纬22.26上
     */
    private JGeoCoord geoCoord;

    /**
     * 构造函数
     */
    public JMap() {
        this.type(JSeriesType.map);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JMap(String name) {
        super(name);
        this.type(JSeriesType.map);
    }

    /**
     * 设置mapLocation值
     *
     * @param mapLocation
     */
    public JMap mapLocation(JMapLocation mapLocation) {
        this.mapLocation = mapLocation;
        return this;
    }

    /**
     * 获取selectedMode值
     */
    public Object selectedMode() {
        return this.selectedMode;
    }

    /**
     * 设置selectedMode值
     *
     * @param selectedMode
     */
    public JMap selectedMode(Object selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    /**
     * 设置selectedMode值
     *
     * @param selectedMode
     */
    public JMap selectedMode(JSelectedMode selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    /**
     * 获取mapType值
     */
    public String mapType() {
        return this.mapType;
    }

    /**
     * 设置mapType值
     *
     * @param mapType
     */
    public JMap mapType(String mapType) {
        this.mapType = mapType;
        return this;
    }

    /**
     * 地图位置设置，默认只适应上下左右居中可配x，y，width，height，任意参数为空都将根据其他参数自适应
     */
    public JMapLocation mapLocation() {
        if (this.mapLocation == null) {
            this.mapLocation = new JMapLocation();
        }
        return this.mapLocation;
    }

    /**
     * 获取mapValueCalculation值
     */
    public JCalculation mapValueCalculation() {
        return this.mapValueCalculation;
    }

    /**
     * 设置mapValueCalculation值
     *
     * @param mapValueCalculation
     */
    public JMap mapValueCalculation(JCalculation mapValueCalculation) {
        this.mapValueCalculation = mapValueCalculation;
        return this;
    }

    /**
     * 获取mapValuePrecision值
     */
    public Integer mapValuePrecision() {
        return this.mapValuePrecision;
    }

    /**
     * 设置mapValuePrecision值
     *
     * @param mapValuePrecision
     */
    public JMap mapValuePrecision(Integer mapValuePrecision) {
        this.mapValuePrecision = mapValuePrecision;
        return this;
    }

    /**
     * 获取showLegendSymbol值
     */
    public Boolean showLegendSymbol() {
        return this.showLegendSymbol;
    }

    /**
     * 设置showLegendSymbol值
     *
     * @param showLegendSymbol
     */
    public JMap showLegendSymbol(Boolean showLegendSymbol) {
        this.showLegendSymbol = showLegendSymbol;
        return this;
    }

    /**
     * 获取dataRangeHoverLink值
     */
    public Boolean dataRangeHoverLink() {
        return this.dataRangeHoverLink;
    }

    /**
     * 设置dataRangeHoverLink值
     *
     * @param dataRangeHoverLink
     */
    public JMap dataRangeHoverLink(Boolean dataRangeHoverLink) {
        this.dataRangeHoverLink = dataRangeHoverLink;
        return this;
    }

    /**
     * 获取roam值
     */
    public Boolean roam() {
        return this.roam;
    }

    /**
     * 设置roam值
     *
     * @param roam
     */
    public JMap roam(Boolean roam) {
        this.roam = roam;
        return this;
    }

    /**
     * 获取scaleLimit值
     */
    public JScaleLimit scaleLimit() {
        if (this.scaleLimit == null) {
            this.scaleLimit = new JScaleLimit();
        }
        return this.scaleLimit;
    }

    /**
     * 设置scaleLimit值
     *
     * @param scaleLimit
     */
    public JMap scaleLimit(JScaleLimit scaleLimit) {
        this.scaleLimit = scaleLimit;
        return this;
    }

    /**
     * 获取nameMap值
     */
    public Object nameMap() {
        return this.nameMap;
    }

    /**
     * 设置nameMap值
     *
     * @param nameMap
     */
    public JMap nameMap(Object nameMap) {
        this.nameMap = nameMap;
        return this;
    }

    /**
     * 获取textFixed值
     */
    public Object textFixed() {
        return this.textFixed;
    }

    /**
     * 设置textFixed值
     *
     * @param textFixed
     */
    public JMap textFixed(Object textFixed) {
        this.textFixed = textFixed;
        return this;
    }

    /**
     * 获取geoCoord值
     */
    public JGeoCoord geoCoord() {
        if (this.geoCoord == null) {
            this.geoCoord = new JGeoCoord();
        }
        return this.geoCoord;
    }

    /**
     * 设置name,x,y值
     *
     * @param name
     * @param x
     * @param y
     */
    public JMap geoCoord(String name, String x, String y) {
        this.geoCoord().put(name, x, y);
        return this;
    }
}
