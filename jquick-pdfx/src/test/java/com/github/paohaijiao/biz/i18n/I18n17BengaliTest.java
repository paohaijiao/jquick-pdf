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
 * 国际化多语言渲染 · 序号 17 —— 孟加拉语 Demo
 *
 * <p>孟加拉文字体选型为 Noto Sans Bengali（覆盖全部孟加拉字母、连字与元音符号），主题色「恒河朱砂·莲花式」#B0322F。</p>
 *
 * <p>模板：i18n/bengali.txt，输出：D://test//i18n//bengali.pdf</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n17BengaliTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void i18n17() throws IOException {
        JPdfConfig config = new JPdfConfig();
        // 4.6.0 起目录（TOC）默认开启且固定用 SimHei 度量标题，非 CJK 标题会缺字形，这里关闭
        config.getCatalogConfig().setEnabled(false);
        // 手动注册外部字体：Noto Sans Bengali（孟加拉文，含连字与元音符号）
        config.getFontConfig().registerFont("Noto Sans Bengali", "fonts/NotoSansBengali.ttf");
        // 注册内置 CJK 字体，供模板中中文混排元素的 fontFamilyNames 回退链命中
        config.getFontConfig().registerFont("SimHei", "fonts/simhei.ttf");
        JFontSpec defaultFont = new JFontSpec("Noto Sans Bengali", "fonts/NotoSansBengali.ttf");
        config.getFontConfig().setDefaultFont(defaultFont);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("reportDate", "2026-10-31");
        byte[] pdf = factory.executeResource("i18n/bengali.txt");

        File dir = new File(path + "i18n");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(path + "i18n/bengali.pdf");
        out.write(pdf);
        out.close();
    }
}
