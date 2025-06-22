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
package com.github.paohaijiao.constant;

/**
 * packageName com.github.paohaijiao.constant
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXConstant
 * @date 2025/6/22
 * @description
 */
public class JPdfXConstant {

    public static final String PDF_TYPE_SIMPLE = "SIMPLE";
    public static final String PDF_TYPE_TABLE = "TABLE";
    public static final String PDF_TYPE_REPORT = "REPORT";
    public static final String PDF_TYPE_INVOICE = "INVOICE";
    public static final String PDF_TYPE_CHART = "CHART";

    public static final String PAGE_SIZE_A4 = "A4";
    public static final String PAGE_SIZE_A3 = "A3";
    public static final String PAGE_SIZE_LETTER = "LETTER";
    public static final String PAGE_SIZE_LEGAL = "LEGAL";

    public static final String ORIENTATION_PORTRAIT = "PORTRAIT";
    public static final String ORIENTATION_LANDSCAPE = "LANDSCAPE";

    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_JUSTIFIED = 3;

    public static final int BORDER_NONE = 0;
    public static final int BORDER_TOP = 1;
    public static final int BORDER_BOTTOM = 2;
    public static final int BORDER_LEFT = 4;
    public static final int BORDER_RIGHT = 8;
    public static final int BORDER_ALL = 15;

    public static final int[] COLOR_BLACK = {0, 0, 0};
    public static final int[] COLOR_WHITE = {255, 255, 255};
    public static final int[] COLOR_RED = {255, 0, 0};
    public static final int[] COLOR_GREEN = {0, 255, 0};
    public static final int[] COLOR_BLUE = {0, 0, 255};
    public static final int[] COLOR_GRAY = {200, 200, 200};

    public static final String DEFAULT_FILENAME_PREFIX = "generated_";

    public static final String TEMP_FILE_SUFFIX = "_temp";
}
