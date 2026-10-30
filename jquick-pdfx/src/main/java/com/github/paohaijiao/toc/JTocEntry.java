/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.paohaijiao.toc;

import lombok.Data;

/**
 * 一条目录条目：标题 + 所在正文页码 + 层级。
 *
 * <p>由 h1~h6 标题渲染时登记（默认只收 h1/h2/h3），页码为正文 1-based 页序；
 * 前置目录页插入后由目录构建器换算为实际页对象。</p>
 */
@Data
public class JTocEntry {

    /** 目录标题（标题元素文本）。 */
    private final String title;

    /** 登记时的正文页码（1-based，不含目录页）。 */
    private final int pageNumber;

    /** 层级：1 = h1，2 = h2，3 = h3。 */
    private final int level;

    public JTocEntry(String title, int pageNumber, int level) {
        this.title = title == null ? "" : title;
        this.pageNumber = Math.max(1, pageNumber);
        this.level = Math.max(1, Math.min(6, level));
    }
}
