package com.github.paohaijiao.model;

public class JStyleListAttributes extends JStyleBlockAttributes {

    public static final String symbol = "symbol";

    public static final String image = "image";

    public String getSymbol(){
        return get(JStyleListAttributes.symbol);
    }

    public String getImage(){
        return get(JStyleListAttributes.image);
    }

    public void setSymbol(String symbol){
        put(JStyleListAttributes.symbol, symbol);
    }

    public void setImage(String image){
        put(JStyleListAttributes.image, image);
    }
}
