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

import lombok.Data;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.treemap
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/8/27
 */
@Data
public class TreemapNode {
    String name;
    double value;
    List<TreemapNode> children;
    Color color;
    Rectangle2D.Double rect;

    public TreemapNode(String name, double value) {
        this.name = name;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(TreemapNode child) {
        children.add(child);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }
}
