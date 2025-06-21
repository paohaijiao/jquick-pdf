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

package com.paohaijiao.data.series.event;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 事件列表，每一个数组项为Object {}，内容如下
 *
 */
@Getter
@Setter
public class JEvent implements Serializable {

    private static final long serialVersionUID = 2936961594659788171L;

    private String name;
    private Integer weight;
    private List<JEvolution> evolution;

    /**
     * 构造方法
     */
    public JEvent() {
    }

    /**
     * 构造方法
     *
     * @param name
     */
    public JEvent(String name) {
        this.name = name;
    }

    /**
     * 构造方法
     *
     * @param name
     * @param weight
     */
    public JEvent(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     * 获取name值
     */
    public String name() {
        return this.name;
    }

    /**
     * 设置name值
     *
     * @param name
     */
    public JEvent name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取weight值
     */
    public Integer weight() {
        return this.weight;
    }

    /**
     * 设置weight值
     *
     * @param weight
     */
    public JEvent weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    /**
     * 获取evolution值
     */
    public List<JEvolution> evolution() {
        if (this.evolution == null) {
            this.evolution = new ArrayList<JEvolution>();
        }
        return this.evolution;
    }

    /**
     * 设置evolution值
     *
     * @param evolution
     */
    public JEvent evolution(List<JEvolution> evolution) {
        this.evolution = evolution;
        return this;
    }

    /**
     * 添加evolution值
     *
     * @param values
     * @return
     */
    public JEvent evolution(JEvolution... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.evolution().addAll(Arrays.asList(values));
        return this;
    }

}
