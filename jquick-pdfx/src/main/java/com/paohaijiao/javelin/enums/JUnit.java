package com.paohaijiao.javelin.enums;

import lombok.Getter;

@Getter
public enum JUnit {
    px("px","px"),
    pt("pt","pt"),
    mm("mm","mm"),
    cm("cm","cm"),
    in("in","in"),
    percent("%","%");

    private String code;
    private String name;

    JUnit(String code, String name){
      this.code = code;
      this.name = name;
    }

    public static JUnit codeOf(String code){
        for(JUnit j : JUnit.values()){
            if (j.code.equals(code)){
                return j;
            }
        }
        return null;
    }
}
