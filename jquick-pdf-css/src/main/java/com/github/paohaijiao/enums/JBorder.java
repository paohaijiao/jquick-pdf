//package com.github.paohaijiao.enums;
//
//import com.itextpdf.layout.borders.Border;
//import com.itextpdf.layout.properties.FontKerning;
//import lombok.Getter;
//
//@Getter
//public enum JBorder {
//
//    solid("solid", Border.SOLID),
//    dashed("dashed", Border.DASHED),
//    no("no", Border.DOTTED),
//    no("no", Border.DOUBLE),
//    no("no", Border.ROUND_DOTS),
//    no("no", Border._3D_GROOVE),
//    no("no", Border._3D_INSET),
//    no("no", Border._3D_OUTSET),
//    no("no", Border._3D_RIDGE),
//    no("no", Border.DASHED_FIXED),
//    no("no", Border.ARC_RIGHT_DEGREE),
//    no("no", Border.ARC_TOP_DEGREE),
//    no("no", Border.ARC_LEFT_DEGREE),
//    no("no", Border.ARC_BOTTOM_DEGREE),
//    no("no", Border.ARC_QUARTER_CLOCKWISE_EXTENT),
//
//    private String code;
//
//    private Border type;
//
//    private JBorder(String code, Border type) {
//        this.code = code;
//        this.type = type;
//    }
//    public static JBorder codeOf(String code) {
//        for (JBorder type : values()) {
//            if (type.code.equals(code)) {
//                return type;
//            }
//        }
//        return null;
//    }
//}
