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
 * 国际化多语言渲染 · 序号 13 —— 藏文 Demo
 *
 * <p>藏文使用藏文区段字符，字体选型为 Noto Serif Tibetan（notofonts 官方
 * 藏文发布包，Sans 版未单独发布，Serif 版覆盖完整藏文字形与叠字丁）。
 * 字体文件位于 jquick-pdf-font 模块 resources/fonts 下，通过 classpath 注册。</p>
 *
 * <p>模板：i18n/tibetan.txt，输出：D:\test\i18n\tibetan.pdf</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n13TibetanTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void tibetan() throws IOException {
        JPdfConfig config = new JPdfConfig();
        // 手动注册外部字体：Noto Serif Tibetan（藏文，含叠加字丁字形）
        config.getFontConfig().registerFont("Noto Serif Tibetan", "fonts/NotoSerifTibetan-Regular.ttf");
        // 注册内置 CJK 字体，供模板中中文混排元素的 fontFamilyNames 回退链命中
        config.getFontConfig().registerFont("SimHei", "fonts/simhei.ttf");
        JFontSpec tibetanFont = new JFontSpec("Noto Serif Tibetan", "fonts/NotoSerifTibetan-Regular.ttf");
        config.getFontConfig().setDefaultFont(tibetanFont);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("reportDate", "2026-10-24");
        byte[] pdf = factory.executeResource("i18n/tibetan.txt");

        File dir = new File(path + "i18n");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(path + "i18n/tibetan.pdf");
        out.write(pdf);
        out.close();
    }
}
