package com.github.paohaijiao.model;


public class JStyleDivAttributes extends JStyleBlockAttributes {


    public static final String fillArea = "fillArea";

    public static final String fillAreaOnSplit = "fillAreaOnSplit";



    public JStyleDivAttributes setFillArea(Boolean fillArea) {
        put(JStyleDivAttributes.fillArea, fillArea);
        return this;
    }

    public JStyleDivAttributes setFillAreaOnSplit(Boolean fillAreaOnSplit) {
        put(JStyleDivAttributes.fillAreaOnSplit, fillAreaOnSplit);
        return this;
    }
}
