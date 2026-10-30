/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.paohaijiao.toc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 目录条目采集器：标题元素在绘制时把 (标题, 页码, 层级) 登记进来，
 * 渲染结束后由 {@link JTocPageBuilder} 据此生成目录页与书签树。
 */
public class JTocCollector {

    private final List<JTocEntry> entries = new ArrayList<>();

    /**
     * 登记一条目录条目。
     *
     * @param title      标题文本
     * @param pageNumber 正文页码（1-based）
     * @param level      层级（h1 = 1，h2 = 2，h3 = 3）
     */
    public void add(String title, int pageNumber, int level) {
        if (title == null || title.trim().isEmpty()) {
            return;
        }
        String normalized = title.trim();
        // 防御性去重：连续出现的同名同层级条目（预排版重绘导致）只保留一条。
        if (!entries.isEmpty()) {
            JTocEntry last = entries.get(entries.size() - 1);
            if (last.getLevel() == level && last.getTitle().equals(normalized)) {
                return;
            }
        }
        entries.add(new JTocEntry(normalized, pageNumber, level));
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public List<JTocEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }
}
