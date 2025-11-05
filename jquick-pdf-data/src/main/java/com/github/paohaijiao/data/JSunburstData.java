package com.github.paohaijiao.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class JSunburstData {

    String name;

    double value; // 相对于父节点的比例 (0-1)

    List<JSunburstData> children;

    public JSunburstData(String name, double value) {
        this.name = name;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(JSunburstData child) {
        this.children.add(child);
    }
}
