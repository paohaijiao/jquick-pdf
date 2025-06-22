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
 * packageName com.github.paohaijiao.config
 *
 * @author Martin
 * @version 1.0.0
 * @className JFontConfig
 * @date 2025/6/22
 * @description
 */
@Data
public class JFontConfig {

    private String defaultChineseFont = "SimSun";

    private String defaultEnglishFont = "Helvetica";

    private float defaultSize = 12;

    private int[] defaultColor = {0, 0, 0};

    private float titleSize = 16;

    private float headerSize = 14;

    private float bodySize = 12;

    private float smallSize = 10;

    private boolean bold = false;

    private boolean italic = false;

    private boolean underline = false;

    private String fontResourcesPath = "classpath:fonts/";
}
