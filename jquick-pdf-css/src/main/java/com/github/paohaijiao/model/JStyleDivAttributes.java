package com.github.paohaijiao.model;


public class JStyleDivAttributes extends JStyleBlockAttributes {


    public static final String fillArea = "fillArea";

    public static final String fillAreaOnSplit = "fillAreaOnSplit";



    public void setFillArea(String fillArea) {
        put(JStyleDivAttributes.fillArea, fillArea);
    }

    public void setFillAreaOnSplit(String fillAreaOnSplit) {
        put(JStyleDivAttributes.fillAreaOnSplit, fillAreaOnSplit);
    }

    public String getFillArea() {
        return get(JStyleDivAttributes.fillArea);
    }
    public String getFillAreaOnSplit() {
        return get(JStyleDivAttributes.fillAreaOnSplit);
    }
}
