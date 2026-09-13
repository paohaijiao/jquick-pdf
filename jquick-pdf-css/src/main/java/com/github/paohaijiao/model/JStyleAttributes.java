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
package com.github.paohaijiao.model;


import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.model
 *
 * <p>标准 CSS/HTML 写法（kebab-case，如 {@code min-height}）与内部驼峰写法
 * （{@code minHeight}）互为别名：写入时统一规范为驼峰键，渲染层只按一种键名读取即可
 * 同时兼容 {@code <div style="min-height:300px">} 与 {@code <div style="minHeight:300px">}。</p>
 *
 * @author Martin
 * @version 1.0.0
 * @className HtmlAttributes
 * @date 2025/6/22
 * @description
 */
public class JStyleAttributes extends HashMap<String, String> {

    /**
     * 少数无法由“连字符转驼峰”推导的别名：CSS 属性名用 {@code align}，
     * 而内部键名用 {@code Alignment}（如 {@code text-align} → {@code textAlignment}）。
     */
    private static final Map<String, String> ALIASES = new HashMap<>();

    static {
        ALIASES.put("text-align", "textAlignment");
        ALIASES.put("vertical-align", "verticalAlignment");
    }

    /**
     * 写入样式声明，并把 {@code min-height} 这类标准写法规范为 {@code minHeight}。
     *
     * @param key   样式名（驼峰写法或标准 kebab-case 写法）
     * @param value 样式值
     * @return 该样式名原有的值（{@code min-height} 与 {@code minHeight} 视为同一个键）
     * @example {@code new JStyleAttributes().put("min-height", "300px");}
     */
    @Override
    public String put(String key, String value) {
        return super.put(canonicalKey(key), value);
    }

    /**
     * 仅当样式尚未声明时写入，键名规则同 {@link #put(String, String)}。
     *
     * @param key   样式名（驼峰写法或标准 kebab-case 写法）
     * @param value 样式值
     * @return 该样式名已有的值；未声明时返回 {@code null}
     * @example {@code attributes.putIfAbsent("min-height", "300px");}
     */
    @Override
    public String putIfAbsent(String key, String value) {
        return super.putIfAbsent(canonicalKey(key), value);
    }

    /** 把 {@code min-height} 转换为 {@code minHeight}；不含连字符的样式名原样返回。 */
    private static String canonicalKey(String key) {
        if (null == key || key.indexOf('-') < 0) {
            return key;
        }
        String alias = ALIASES.get(key);
        if (null != alias) {
            return alias;
        }
        StringBuilder builder = new StringBuilder(key.length());
        boolean upperCaseNext = false;
        for (int i = 0; i < key.length(); i++) {
            char current = key.charAt(i);
            if ('-' == current) {
                upperCaseNext = true;
            } else if (upperCaseNext) {
                builder.append(Character.toUpperCase(current));
                upperCaseNext = false;
            } else {
                builder.append(current);
            }
        }
        return builder.toString();
    }
}
