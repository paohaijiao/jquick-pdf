package com.github.paohaijiao.bubble;

import com.github.paohaijiao.code.JSeriesType;
import com.github.paohaijiao.series.JSeries;

public class ScatterSeries extends JSeries<ScatterSeries> {

    public ScatterSeries() {
        super();
        this.type(JSeriesType.scatter);
    }

    public ScatterSeries(String name) {
        super(name);
        this.type(JSeriesType.scatter);
    }
}
