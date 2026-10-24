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
package com.github.paohaijiao.font;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 家族名到字体文件的注册表：模板里 {@code fontFamilyNames} 回退链中的
 * 自定义家族名（非 PDF 标准 14 字体）在这里按名字查找并解析为可嵌入字体。
 * 渲染入口 {@code config.fontConfig().registerFont(family, file)} 注册。
 */
public final class JFontRegistry {

    private static final Map<String, JFontSpec> FONTS = new ConcurrentHashMap<String, JFontSpec>();

    private JFontRegistry() {
    }

    public static void register(String familyName, String file) {
        if (familyName == null || familyName.trim().isEmpty()) {
            return;
        }
        FONTS.put(normalize(familyName), new JFontSpec(familyName.trim(), file));
    }

    public static JFontSpec find(String familyName) {
        if (familyName == null) {
            return null;
        }
        return FONTS.get(normalize(familyName));
    }

    private static String normalize(String name) {
        return name.trim().toLowerCase(Locale.ROOT);
    }
}
