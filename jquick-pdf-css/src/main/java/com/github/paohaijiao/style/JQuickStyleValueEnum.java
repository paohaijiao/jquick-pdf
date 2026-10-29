package com.github.paohaijiao.style;

/**
 * 样式取值枚举：集中定义所有样式属性的可选值（如 display:flex、justify-content:space-between、
 * font-weight:bold 等），避免渲染代码里散落魔法字符串。
 * 统一通过 {@link #match(String)} 做忽略大小写的取值比较，
 * 通过 {@link #getValue()} 取原始值用于写入默认样式。
 */
public enum JQuickStyleValueEnum {

    // display
    flex("flex"),
    inlineFlex("inline-flex"),
    block("block"),
    inline("inline"),

    // flex-direction
    row("row"),
    rowReverse("row-reverse"),
    column("column"),
    columnReverse("column-reverse"),

    // flex-wrap
    nowrap("nowrap"),
    wrap("wrap"),
    wrapReverse("wrap-reverse"),

    // justify-content
    flexStart("flex-start"),
    flexEnd("flex-end"),
    start("start"),
    end("end"),
    left("left"),
    right("right"),
    center("center"),
    spaceBetween("space-between"),
    spaceAround("space-around"),

    // vertical-align / text-align
    top("top"),
    middle("middle"),
    bottom("bottom"),
    justify("justify"),

    // font-weight / font-style / text-decoration
    bold("bold"),
    normal("normal"),
    italic("italic"),
    underline("underline"),
    lineThrough("line-through"),

    // border / list-style
    solid("solid"),
    dashed("dashed"),
    dotted("dotted"),
    none("none"),

    // 通用
    auto("auto");

    private final String value;

    JQuickStyleValueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /** 忽略大小写比较声明值是否等于该取值（等价于原先散落各处的 {@code "flex".equalsIgnoreCase(v)}）。 */
    public boolean match(String declared) {
        return value.equalsIgnoreCase(declared);
    }
}
