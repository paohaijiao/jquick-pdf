package com.github.paohaijiao.sample;

public enum CatalogType
{

    /**
     * 目录类别 1-需要注意 2-正常项目
     */
    ATTENTION(1), NORMAL(2);
    private Integer val;

    CatalogType(Integer val) {
        this.val = val;
    }

    public Integer val() {
        return val;
    }
}
