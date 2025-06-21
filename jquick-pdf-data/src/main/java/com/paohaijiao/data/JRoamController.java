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

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 缩放漫游组件，仅对地图有效
 *
 * @author martin
 */
@Getter
@Setter
public class JRoamController extends JBasic<JRoamController> implements JComponent {
    private String fillerColor;
    private String handleColor;
    private Integer step;
    private Map<String, Boolean> mapTypeControl;

    /**
     * 获取fillerColor值
     */
    public String fillerColor() {
        return this.fillerColor;
    }

    /**
     * 设置fillerColor值
     *
     * @param fillerColor
     */
    public JRoamController fillerColor(String fillerColor) {
        this.fillerColor = fillerColor;
        return this;
    }

    /**
     * 获取handleColor值
     */
    public String handleColor() {
        return this.handleColor;
    }

    /**
     * 设置handleColor值
     *
     * @param handleColor
     */
    public JRoamController handleColor(String handleColor) {
        this.handleColor = handleColor;
        return this;
    }

    /**
     * 获取step值
     */
    public Integer step() {
        return this.step;
    }

    /**
     * 设置step值
     *
     * @param step
     */
    public JRoamController step(Integer step) {
        this.step = step;
        return this;
    }

    /**
     * 获取mapTypeControl值
     */
    public Map<String, Boolean> mapTypeControl() {
        return this.mapTypeControl;
    }

    /**
     * 设置mapTypeControl值
     *
     * @param key   地名
     * @param value true|false
     */
    public JRoamController mapTypeControl(String key, Boolean value) {
        if (this.mapTypeControl == null) {
            this.mapTypeControl = new HashMap<String, Boolean>();
        }
        this.mapTypeControl.put(key, value);
        return this;
    }
}
