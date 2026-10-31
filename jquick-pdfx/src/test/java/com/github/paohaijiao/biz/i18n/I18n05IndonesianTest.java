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
 * 国际化多语言渲染 · 序号 05 —— 印尼语 Demo
 *
 * <p>印尼语使用拉丁字母，默认 Noto Sans 即可完整覆盖；主题色「巴迪·靛紫」#6B2F6B。</p>
 *
 * <p>模板：i18n/indonesian.txt，输出：D://test//i18n//indonesian.pdf</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n05IndonesianTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void i18n05() throws IOException {
        JPdfConfig config = new JPdfConfig();
        // 4.6.0 起目录（TOC）默认开启且固定用 SimHei 度量标题，非 CJK 标题会缺字形，这里关闭
        config.getCatalogConfig().setEnabled(false);
        // 手动注册外部字体：Noto Sans（印尼语）
        config.getFontConfig().registerFont("Noto Sans", "fonts/NotoSans-Regular.ttf");
        // 注册内置 CJK 字体，供模板中中文混排元素的 fontFamilyNames 回退链命中
        config.getFontConfig().registerFont("SimHei", "fonts/simhei.ttf");
        JFontSpec defaultFont = new JFontSpec("Noto Sans", "fonts/NotoSans-Regular.ttf");
        config.getFontConfig().setDefaultFont(defaultFont);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("reportDate", "2026-10-31");
        byte[] pdf = factory.executeResource("i18n/indonesian.txt");

        File dir = new File(path + "i18n");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(path + "i18n/indonesian.pdf");
        out.write(pdf);
        out.close();
    }
}
