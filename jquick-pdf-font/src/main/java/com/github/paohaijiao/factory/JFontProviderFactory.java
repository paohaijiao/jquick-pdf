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
package com.github.paohaijiao.factory;

import com.github.paohaijiao.enums.JPdfEncoding;
import com.github.paohaijiao.font.JFontProvider;
import com.github.paohaijiao.font.impl.JDefaultFontProvider;
import com.itextpdf.kernel.font.PdfFont;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.factory
 *
 * @author Martin
 * @version 1.0.0
 * @className JFontProviderFactory
 * @date 2025/6/28
 * @description
 */
public class JFontProviderFactory {

    public static final String DEFAULT_FONT = "simhei";


    private static final Map<String, JFontProvider> fontProviders = new HashMap<>();

    static{
        fontProviders.put(DEFAULT_FONT,new JDefaultFontProvider(DEFAULT_FONT,"fonts/simhei.ttf", JPdfEncoding.IDENTITY_H));
    }
    public static PdfFont defualtFont() {
        if(!fontProviders.containsKey(DEFAULT_FONT)){
            throw new IllegalArgumentException("Unknown font provider for font: " + DEFAULT_FONT);
        }
        JFontProvider provider =fontProviders.get(DEFAULT_FONT);
        return provider.getFont();
    }
    public static PdfFont getFont(String font) {
        if(!fontProviders.containsKey(font)){
            throw new IllegalArgumentException("Unknown font provider for font: " + font);
        }
        JFontProvider provider =fontProviders.get(font);
        return provider.getFont();
    }




//
//    public void registerSystemFont(String fontName, String alias) {
//        registerSystemFont(fontName, alias, DEFAULT_ENCODING);
//    }
//
//    public void registerSystemFont(String fontName, String alias, String encoding) {
//        fontProviders.put(alias, new JSystemFontProvider(fontName, encoding));
//    }
//
//    public void registerFileFont(String fontPath, String alias) {
//        registerFileFont(fontPath, alias, DEFAULT_ENCODING);
//    }
//
//    public void registerFileFont(String fontPath, String alias, String encoding) {
//        fontProviders.put(alias, new JFileFontProvider(fontPath, encoding));
//    }
//
//    public void registerResourceFont(String resourcePath, String alias) {
//        registerResourceFont(resourcePath, alias, DEFAULT_ENCODING);
//    }
//
//    public void registerResourceFont(String resourcePath, String alias, String encoding) {
//        fontProviders.put(alias, new JResourceFontProvider(resourcePath, encoding));
//    }
//
//    public JFontProvider getFontProvider(String alias) {
//        JFontProvider provider = fontProviders.get(alias);
//        if (provider == null) {
//            throw new IllegalArgumentException("Font provider not registered for alias: " + alias);
//        }
//        return provider;
//    }
//
//    public boolean isFontRegistered(String alias) {
//        return fontProviders.containsKey(alias);
//    }

}
