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
package com.github.paohaijiao.biz.i18n;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.font.JFontSpec;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 国际化多语言渲染 · 序号 06 —— 日语 Demo
 *
 * <p>日文字体选型为 Noto Sans JP（覆盖平假名、片假名与汉字字形），
 * 模板中 fontFamilyNames 声明回退链 Noto Sans JP,Helvetica。
 * 字体文件位于 jquick-pdf-font 模块 resources/fonts 下，
 * 通过 classpath 注册。</p>
 *
 * <p>模板：i18n/japanese.txt，输出：D:\test\i18n\japanese.pdf</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n06JapaneseTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void japanese() throws IOException {
        JPdfConfig config = new JPdfConfig();
        // 手动注册外部字体：Noto Sans JP（假名 + 汉字字形，官方静态 Regular OTF）
        config.getFontConfig().registerFont("Noto Sans JP", "fonts/NotoSansJP-Regular.ttf");
        // 注册内置 CJK 字体，供模板中中文混排元素的 fontFamilyNames 回退链命中
        config.getFontConfig().registerFont("SimHei", "fonts/simhei.ttf");
        JFontSpec japaneseFont = new JFontSpec("Noto Sans JP", "fonts/NotoSansJP-Regular.ttf");
        config.getFontConfig().setDefaultFont(japaneseFont);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("reportDate", "2026-10-24");
        byte[] pdf = factory.executeResource("i18n/japanese.txt");

        File dir = new File(path + "i18n");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(path + "i18n/japanese.pdf");
        out.write(pdf);
        out.close();
    }
}
