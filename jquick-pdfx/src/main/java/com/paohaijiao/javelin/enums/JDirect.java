package com.paohaijiao.javelin.enums;

import lombok.Getter;

@Getter
public enum JDirect {
    left("left","left"),
    right("right","right");

    private String code;
    private String name;

    JDirect(String code, String name){
      this.code = code;
      this.name = name;
    }

    public static JDirect codeOf(String code){
        for(JDirect j : JDirect.values()){
            if (j.code.equals(code)){
                return j;
            }
        }
        return null;
    }
}
