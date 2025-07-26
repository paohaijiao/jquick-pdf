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
package com.github.paohaijiao.series.other;

import com.github.paohaijiao.code.JLayout;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 力引导布局相关的配置项
 *
 * @author martin
 */
@Getter
@Setter
public class JForce implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object initLayout;
    private Integer repulsion;
    private Integer gravity;
    private Integer edgeLength;
    private Boolean layoutAnimation;

    public Object initLayout() {
        return this.initLayout;
    }

    public JForce initLayout(Object initLayout) {
        this.initLayout = initLayout;
        return this;
    }

    public JForce initLayout(String initLayout) {
        this.initLayout = initLayout;
        return this;
    }

    public JForce initLayout(JLayout initLayout) {
        this.initLayout = initLayout;
        return this;
    }

    public Integer repulsion() {
        return this.repulsion;
    }

    public JForce repulsion(Integer repulsion) {
        this.repulsion = repulsion;
        return this;
    }

    public Integer gravity() {
        return this.gravity;
    }

    public JForce gravity(Integer gravity) {
        this.gravity = gravity;
        return this;
    }

    public Integer edgeLength() {
        return this.edgeLength;
    }

    public JForce edgeLength(Integer edgeLength) {
        this.edgeLength = edgeLength;
        return this;
    }

    public Boolean layoutAnimation() {
        return this.layoutAnimation;
    }

    public JForce layoutAnimation(Boolean layoutAnimation) {
        this.layoutAnimation = layoutAnimation;
        return this;
    }

    public Object getInitLayout() {
        return initLayout;
    }

    public void setInitLayout(Object initLayout) {
        this.initLayout = initLayout;
    }

    public Integer getRepulsion() {
        return repulsion;
    }

    public void setRepulsion(Integer repulsion) {
        this.repulsion = repulsion;
    }

    public Integer getGravity() {
        return gravity;
    }

    public void setGravity(Integer gravity) {
        this.gravity = gravity;
    }

    public Integer getEdgeLength() {
        return edgeLength;
    }

    public void setEdgeLength(Integer edgeLength) {
        this.edgeLength = edgeLength;
    }

    public Boolean getLayoutAnimation() {
        return layoutAnimation;
    }

    public void setLayoutAnimation(Boolean layoutAnimation) {
        this.layoutAnimation = layoutAnimation;
    }
}
