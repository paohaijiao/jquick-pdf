package com.github.paohaijiao.model;

import lombok.Data;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

@Data
public class TreemapNode {
    String name;
    double value;
    List<TreemapNode> children;
    Color color;
    Rectangle2D.Double rect; // 添加rect属性

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
