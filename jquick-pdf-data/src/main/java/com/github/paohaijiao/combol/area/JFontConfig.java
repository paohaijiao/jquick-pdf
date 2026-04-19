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
package com.github.paohaijiao.combol.area;

import lombok.Data;

/**
 * packageName com.github.paohaijiao.combol
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/19
 */
@Data
public class JFontConfig {

    private java.awt.Font titleFont = new java.awt.Font("Microsoft YaHei", java.awt.Font.BOLD, 18);

    private java.awt.Font subtitleFont = new java.awt.Font("Microsoft YaHei", java.awt.Font.PLAIN, 14);

    private java.awt.Font axisFont = new java.awt.Font("Microsoft YaHei", java.awt.Font.PLAIN, 12);

    private java.awt.Font legendFont = new java.awt.Font("Microsoft YaHei", java.awt.Font.PLAIN, 12);

    private java.awt.Font dataLabelFont = new java.awt.Font("Microsoft YaHei", java.awt.Font.PLAIN, 11);

}
