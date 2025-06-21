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

/**
 * 事件的详细信息
 *
 */
@Getter
@Setter
public class JDetail implements Serializable {

    private static final long serialVersionUID = 680903438457621422L;

    private String link;
    private String text;
    private String img;

    /**
     * 构造方法
     */
    public JDetail() {
    }

    /**
     * 构造方法
     *
     * @param link
     * @param text
     */
    public JDetail(String link, String text) {
        this.link = link;
        this.text = text;
    }

    /**
     * 构造方法
     *
     * @param link
     * @param text
     * @param img
     */
    public JDetail(String link, String text, String img) {
        this.link = link;
        this.text = text;
        this.img = img;
    }

    /**
     * 获取link值
     */
    public String link() {
        return this.link;
    }

    /**
     * 设置link值
     *
     * @param link
     */
    public JDetail link(String link) {
        this.link = link;
        return this;
    }

    /**
     * 获取text值
     */
    public String text() {
        return this.text;
    }

    /**
     * 设置text值
     *
     * @param text
     */
    public JDetail text(String text) {
        this.text = text;
        return this;
    }

    /**
     * 获取img值
     */
    public String img() {
        return this.img;
    }

    /**
     * 设置img值
     *
     * @param img
     */
    public JDetail img(String img) {
        this.img = img;
        return this;
    }
}
