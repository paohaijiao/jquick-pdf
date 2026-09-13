/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.config;

import lombok.Data;

/**
 * 布局配置：控制 HTML 语义的 flex 行布局行为。
 */
@Data
public class JLayoutConfig {

    /**
     * 是否启用 flex 行布局（{@code display:flex}）。默认关闭，关闭时所有 div 均按块级元素垂直堆叠，
     * 既有模板的渲染效果保持不变。
     * <p>
     * 开启后，声明了 {@code display:flex} 的 div 会作为 flex 容器，其子元素尽量排在同一行：
     * 宽度足够时并排，宽度不足时按比例收缩（等价于 HTML 默认的 {@code flex-wrap:nowrap}）；
     * 若显式声明 {@code flex-wrap:wrap}，则一行放不下时自动换行。
     * <p>
     * 使用示例：
     * <pre>
     * JPdfConfig config = new JPdfConfig();
     * config.getLayoutConfig().setFlexLayout(true);
     * </pre>
     */
    private boolean flexLayout = false;
}
