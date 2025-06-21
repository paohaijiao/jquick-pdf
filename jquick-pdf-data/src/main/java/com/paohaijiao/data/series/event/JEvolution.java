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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 同一事件的的演化过程，每一个数组项为Object {}，内容如下
 *
 * @author martin
 */
@Getter
@Setter
public class JEvolution implements Serializable {

    private static final long serialVersionUID = -3014023133802074740L;

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private String time;
    private Integer value;
    /**
     * 事件的详细信息
     */
    private JDetail detail;

    /**
     * 构造方法
     */
    public JEvolution() {
    }

    /**
     * 构造方法
     *
     * @param time
     * @param value
     */
    public JEvolution(String time, Integer value) {
        this.time = time;
        this.value = value;
    }

    /**
     * 获取time值
     */
    public String time() {
        return this.time;
    }

    /**
     * 设置time值
     *
     * @param time
     */
    public JEvolution time(String time) {
        this.time = time;
        return this;
    }

    /**
     * 设置time值，默认yyyy-MM-dd,其他情况建议使用字符串类型的时间
     *
     * @param time
     */
    public JEvolution time(Date time) {
        this.time = FORMAT.format(time);
        return this;
    }

    /**
     * 获取value值
     */
    public Integer value() {
        return this.value;
    }

    /**
     * 设置value值
     *
     * @param value
     */
    public JEvolution value(Integer value) {
        this.value = value;
        return this;
    }

    /**
     * 获取detail值
     */
    public JDetail detail() {
        return this.detail;
    }

    /**
     * 设置detail值
     *
     * @param detail
     */
    public JEvolution detail(JDetail detail) {
        this.detail = detail;
        return this;
    }

    /**
     * 设置detail值
     *
     * @param link
     * @param text
     */
    public JEvolution detail(String link, String text) {
        this.detail = new JDetail(link, text);
        return this;
    }

    /**
     * 设置detail值
     *
     * @param link
     * @param text
     * @param img
     */
    public JEvolution detail(String link, String text, String img) {
        this.detail = new JDetail(link, text, img);
        return this;
    }
}
