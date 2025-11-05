package com.github.paohaijiao.bubble;


import com.github.paohaijiao.axis.JAxis;
import com.github.paohaijiao.code.JAxisType;

/**
 * 类目轴实现
 */
public class CategoryAxis extends JAxis<CategoryAxis> {

    public CategoryAxis() {
        super();
        this.type(JAxisType.category);
    }

    public CategoryAxis(String name) {
        super();
        this.type(JAxisType.category);
        this.name(name);
    }
}
