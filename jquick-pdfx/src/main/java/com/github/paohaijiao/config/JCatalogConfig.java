/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.paohaijiao.config;

import lombok.Data;

/**
 * 目录（TOC）配置。
 *
 * <p>开启后：</p>
 * <ol>
 *   <li>模板中的 {@code <h1>}~{@code <h3>} 自动登记为目录条目；</li>
 *   <li>渲染完成后在文档最前生成目录页，条目自带 PDF 内部跳转链接（GoTo）；</li>
 *   <li>同步重建 PDF 书签树（PDDocumentOutline），层级与 h1/h2/h3 一致；</li>
 *   <li>可选页码盖印：目录页罗马数字（I、II…）、正文阿拉伯数字（1、2…）。</li>
 * </ol>
 */
@Data
public class JCatalogConfig {

    /** 是否启用目录采集与生成。 */
    private boolean enabled = false;

    /** 保留字段：目录起始页（历史配置，向后兼容）。 */
    private Integer startPage = 1;

    /** 目录页标题。 */
    private String title = "目  录";

    /** 目录条目是否显示页码（阿拉伯数字，指向正文页码）。 */
    private boolean showPageNumbers = true;

    /**
     * 是否盖印页码：目录页盖罗马数字（I、II…），正文页盖阿拉伯数字（1、2…）。
     * 目录页数计入罗马页码，正文从 1 开始，实现两种页码体系分离。
     */
    private boolean stampPageNumbers = false;

    /** 每页目录最多容纳的条目数（超出自动分页）。 */
    private int entriesPerPage = 25;
}
