/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.paohaijiao.config;

/**
 * 水印按页生效规则。
 *
 * <ul>
 *   <li>{@link #ALL} —— 所有页面都加水印（默认）</li>
 *   <li>{@link #SKIP_FIRST} —— 首页（封面）不加水印，其余页加水印</li>
 *   <li>{@link #FIRST_ONLY} —— 仅首页加水印</li>
 *   <li>{@link #ODD_ONLY} —— 仅奇数页加水印（按人类可读页码 1、3、5…）</li>
 *   <li>{@link #EVEN_ONLY} —— 仅偶数页加水印（按人类可读页码 2、4、6…）</li>
 * </ul>
 */
public enum JWatermarkPageRule {

    /** 所有页面都加水印。 */
    ALL,

    /** 首页不加水印，仅正文加水印。 */
    SKIP_FIRST,

    /** 仅首页加水印。 */
    FIRST_ONLY,

    /** 仅奇数页（第 1、3、5…页）加水印。 */
    ODD_ONLY,

    /** 仅偶数页（第 2、4、6…页）加水印。 */
    EVEN_ONLY;

    /**
     * 判断指定页（0-based 页索引）是否需要绘制水印。
     *
     * @param pageIndex 0-based 页索引
     * @return 该页是否需要水印
     */
    public boolean matches(int pageIndex) {
        int humanPage = pageIndex + 1; // 人类可读页码从 1 开始
        switch (this) {
            case SKIP_FIRST:
                return pageIndex > 0;
            case FIRST_ONLY:
                return pageIndex == 0;
            case ODD_ONLY:
                return humanPage % 2 == 1;
            case EVEN_ONLY:
                return humanPage % 2 == 0;
            case ALL:
            default:
                return true;
        }
    }
}
