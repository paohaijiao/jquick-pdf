package com.github.paohaijiao.enums;

import com.itextpdf.layout.borders.*;
import lombok.Getter;

@Getter
public enum JBorder {

    solid("solid", SolidBorder.class),
    //dashed("dashed", BeveledBorder.class),
    //border3d("border3d", Border3D.class),
    dashedBorder("dashed",  DashedBorder.class),
    dottedBorder("dotted", DottedBorder.class),
    doubleBorder("double", DoubleBorder.class),
    fixedDashedBorder("fixedDashed", FixedDashedBorder.class),
    grooveBorder("groove", GrooveBorder.class),
    insetBorder("inset", InsetBorder.class),
    outsetBorder("outset", OutsetBorder.class),
    ridgeBorder("ridge", RidgeBorder.class),
    roundDotsBorder("roundDots", RoundDotsBorder.class),
    solidBorder("solid", SolidBorder.class);
    //no("no", UnderlineBorder.class);

    private String code;

    private Class clazz;

    private JBorder(String code, Class clazz) {
        this.code = code;
        this.clazz = clazz;
    }
    public static JBorder codeOf(String code) {
        for (JBorder type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
