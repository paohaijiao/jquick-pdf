package com.github.paohaijiao.bubble;

import com.github.paohaijiao.axis.JAxis;
import com.github.paohaijiao.code.JAxisType;

/**
 * 数值轴实现
 */
public class ValueAxis extends JAxis<ValueAxis> {

    public ValueAxis() {
        super();
        this.type(JAxisType.value);
    }

    public ValueAxis(String name) {
        super();
        this.type(JAxisType.value);
        this.name(name);
    }
}
