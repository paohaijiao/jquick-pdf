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

import com.paohaijiao.data.code.JEasing;
import com.paohaijiao.data.data.JPointData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Data接口 - 添加数据
 *
 * @author martin
 */
@Getter
@Setter
public abstract class JAbstractData<T> implements JData<T>, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 标线图形数据
     *
     * @see JPointData
     */
    protected List data;
    /**
     * 是否可点击，默认开启
     */
    private Boolean clickable;
    /**
     * 非数值显示（如仅用于显示标注标线时），可以通过hoverable:false关闭区域悬浮高亮
     *
     * @since 2.2.0
     */
    private Boolean hoverable;

    /**
     * 是否开启动画，默认开启。
     */
    private Boolean animation;
    /**
     * 初始动画的时长。
     */
    private Integer animationDuration;
    /**
     * 初始动画的缓动效果
     */
    private Object animationEasing;
    /**
     * 数据更新动画的时长。
     */
    private Integer animationDurationUpdate;
    /**
     * 数据更新动画的缓动效果
     */
    private Object animationEasingUpdate;

    public Boolean animation() {
        return this.animation;
    }

    public T animation(Boolean animation) {
        this.animation = animation;
        return (T) this;
    }

    public T animationEasing(JEasing animationEasing) {
        this.animationEasing = animationEasing;
        return (T) this;
    }

    public Integer animationDuration() {
        return this.animationDuration;
    }

    public T animationDuration(Integer animationDuration) {
        this.animationDuration = animationDuration;
        return (T) this;
    }

    public Object animationEasing() {
        return this.animationEasing;
    }

    public T animationEasing(Object animationEasing) {
        this.animationEasing = animationEasing;
        return (T) this;
    }

    public Integer animationDurationUpdate() {
        return this.animationDurationUpdate;
    }

    public T animationDurationUpdate(Integer animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return (T) this;
    }

    public Object animationEasingUpdate() {
        return this.animationEasingUpdate;
    }

    public T animationEasingUpdate(Object animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return (T) this;
    }

    public T animationEasingUpdate(JEasing animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return (T) this;
    }

    /**
     * 获取data值
     */
    public List data() {
        if (this.data == null) {
            this.data = new ArrayList();
        }
        return this.data;
    }

    /**
     * 添加元素
     *
     * @param values
     * @return
     */
    @Override
    public T data(Object... values) {
        if (values == null || values.length == 0) {
            return (T) this;
        }
        for (Object value : values) {
            this.data().add(value);
        }
        return (T) this;
    }


    /**
     * 获取clickable值
     */
    public Boolean clickable() {
        return this.clickable;
    }

    /**
     * 设置clickable值
     *
     * @param clickable
     */
    public T clickable(Boolean clickable) {
        this.clickable = clickable;
        return (T) this;
    }
    /**
     * 获取hoverable值
     */
    public Boolean hoverable() {
        return this.hoverable;
    }

    /**
     * 设置hoverable值
     *
     * @param hoverable
     */
    public T hoverable(Boolean hoverable) {
        this.hoverable = hoverable;
        return (T) this;
    }
}
