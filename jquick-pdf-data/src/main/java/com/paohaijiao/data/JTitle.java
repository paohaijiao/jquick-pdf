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

import com.paohaijiao.data.code.JX;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * @author martin
 */
@Getter
@Setter
public class JTitle extends JBasic<JTitle> implements JComponent {
    /**
     * 主标题文本，'\n'指定换行
     */
    private String text;
    /**
     * 主标题文本超链接
     */
    private String link;
    /**
     * 指定窗口打开主标题超链接，支持'self' | 'blank'，不指定等同为'blank'（新窗口）
     */
    private String target;
    /**
     * 副标题文本，'\n'指定换行
     */
    private String subtext;
    /**
     * 副标题文本超链接
     */
    private String sublink;
    /**
     * 指定窗口打开副标题超链接，支持'self' | 'blank'，不指定等同为'blank'（新窗口）
     */
    private String subtarget;
    /**
     * 水平对齐方式，默认根据x设置自动调整，可选为： left' | 'right' | 'center
     *
     * @see JX
     */
    private JX textAlign;
    /**
     * 主标题文本样式（详见textStyle）
     *
     * @see JTextStyle
     */
    private JTextStyle textStyle;
    /**
     * 默认值{color: '#aaa'}，副标题文本样式
     *
     * @see JTextStyle
     */
    private JTextStyle subtextStyle;
    /**
     * 属性offsetCenter用于详情定位，数组为横纵相对仪表盘圆心坐标偏移，支持百分比（相对外半径）
     */
    private Object offsetCenter;

    /**
     * 设置textStyle值
     *
     * @param textStyle
     */
    public JTitle textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    /**
     * 设置subtextStyle值
     *
     * @param subtextStyle
     */
    public JTitle subtextStyle(JTextStyle subtextStyle) {
        this.subtextStyle = subtextStyle;
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
    public JTitle text(String text) {
        this.text = text;
        return this;
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
    public JTitle link(String link) {
        this.link = link;
        return this;
    }

    /**
     * 获取target值
     */
    public String target() {
        return this.target;
    }

    /**
     * 设置target值
     *
     * @param target
     */
    public JTitle target(String target) {
        this.target = target;
        return this;
    }

    /**
     * 获取subtext值
     */
    public String subtext() {
        return this.subtext;
    }

    /**
     * 设置subtext值
     *
     * @param subtext
     */
    public JTitle subtext(String subtext) {
        this.subtext = subtext;
        return this;
    }

    /**
     * 获取sublink值
     */
    public String sublink() {
        return this.sublink;
    }

    /**
     * 设置sublink值
     *
     * @param sublink
     */
    public JTitle sublink(String sublink) {
        this.sublink = sublink;
        return this;
    }

    /**
     * 获取subtarget值
     */
    public String subtarget() {
        return this.subtarget;
    }

    /**
     * 设置subtarget值
     *
     * @param subtarget
     */
    public JTitle subtarget(String subtarget) {
        this.subtarget = subtarget;
        return this;
    }

    /**
     * 获取textAlign值
     */
    public JX textAlign() {
        return this.textAlign;
    }

    /**
     * 设置textAlign值
     *
     * @param textAlign
     */
    public JTitle textAlign(JX textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    /**
     * 主标题文本样式（详见textStyle）
     *
     * @see JTextStyle
     */
    public JTextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        return this.textStyle;
    }

    /**
     * 默认值{color: '#aaa'}，副标题文本样式
     *
     * @see JTextStyle
     */
    public JTextStyle subtextStyle() {
        if (this.subtextStyle == null) {
            this.subtextStyle = new JTextStyle();
        }
        return this.subtextStyle;
    }

    /**
     * 获取offsetCenter值
     */
    public Object offsetCenter() {
        return this.offsetCenter;
    }

    /**
     * 设置offsetCenter值
     *
     * @param offsetCenter
     */
    public JTitle offsetCenter(Object offsetCenter) {
        this.offsetCenter = offsetCenter;
        return this;
    }
}
