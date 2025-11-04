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
package com.github.paohaijiao.treemap;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.treemap
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JTreemapNode {

    private String name;

    private double value;

    private List<JTreemapNode> children = new ArrayList<>();

    private Rectangle2D rect;

    private Color color;

    public JTreemapNode(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public void addChild(JTreemapNode child) {
        this.children.add(child);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<JTreemapNode> getChildren() {
        return children;
    }

    public void setChildren(List<JTreemapNode> children) {
        this.children = children;
    }

    public Rectangle2D getRect() {
        return rect;
    }

    public void setRect(Rectangle2D rect) {
        this.rect = rect;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
