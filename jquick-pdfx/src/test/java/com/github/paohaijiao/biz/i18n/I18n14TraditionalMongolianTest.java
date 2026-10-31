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
 * 国际化多语言渲染 · 序号 14 —— 传统蒙古文 Demo
 *
 * <p>传统蒙古文使用蒙古文区段字符，官方竖排字体选型为 Noto Sans Mongolian
 * （notofonts 官方发布为 TTF；竖排书写由渲染层按 -90° 旋转完成，
 * 模板中对应 angleInRadians:-1.5708）。字体文件位于 jquick-pdf-font
 * 模块 resources/fonts 下，通过 classpath 注册。</p>
 *
 * <p>模板：i18n/traditional_mongolian.txt，输出：D:\test\i18n\traditional_mongolian.pdf</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n14TraditionalMongolianTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void traditionalMongolian() throws IOException {
        JPdfConfig config = new JPdfConfig();
        // 手动注册外部字体：Noto Sans Mongolian（传统蒙古文/满文共用，竖排字形）
        config.getFontConfig().registerFont("Noto Sans Mongolian", "fonts/NotoSansMongolian-Regular.ttf");
        // 注册内置 CJK 字体，供模板中中文混排元素的 fontFamilyNames 回退链命中
        config.getFontConfig().registerFont("SimHei", "fonts/simhei.ttf");
        JFontSpec mongolianFont = new JFontSpec("Noto Sans Mongolian", "fonts/NotoSansMongolian-Regular.ttf");
        config.getFontConfig().setDefaultFont(mongolianFont);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("reportDate", "2026-10-24");
        byte[] pdf = factory.executeResource("i18n/traditional_mongolian.txt");

        File dir = new File(path + "i18n");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(path + "i18n/traditional_mongolian.pdf");
        out.write(pdf);
        out.close();
    }
}
