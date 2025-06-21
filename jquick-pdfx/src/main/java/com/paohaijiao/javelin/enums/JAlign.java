package com.paohaijiao.javelin.enums;

import com.itextpdf.layout.properties.TextAlignment;
import lombok.Getter;

@Getter
public enum JAlign {
    left("left","left"),
    center("center","center"),
    justify("justify","justify"),
    right("right","right");

    private String code;
    private String name;

    JAlign(String code, String name){
      this.code = code;
      this.name = name;
    }

    public static JAlign codeOf(String code){
        for(JAlign j : JAlign.values()){
            if (j.code.equals(code)){
                return j;
            }
        }
        return null;
    }
    public static TextAlignment textAlignOf(String code){
        if(JAlign.codeOf(code).getCode().equals(JAlign.left.getCode())){
            return TextAlignment.LEFT;
        }
        if(JAlign.codeOf(code).getCode().equals(JAlign.right.getCode())){
            return TextAlignment.RIGHT;
        }
        if(JAlign.codeOf(code).getCode().equals(JAlign.center.getCode())){
            return TextAlignment.CENTER;
        }
        if(JAlign.codeOf(code).getCode().equals(JAlign.justify.getCode())){
            return TextAlignment.JUSTIFIED;
        }
        return  TextAlignment.LEFT;
    }
}
