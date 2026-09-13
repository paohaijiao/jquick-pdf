package com.github.paohaijiao.factory;

import com.github.paohaijiao.font.JFontProvider;
import com.github.paohaijiao.font.impl.JDefaultFontProvider;
import com.github.paohaijiao.font.impl.JFileFontProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class JFontProviderFactory {

    public static final String DEFAULT_FONT = "simhei";

    private static final Map<String, JFontProvider> FONT_PROVIDERS =
            new ConcurrentHashMap<String, JFontProvider>();

    static {
        FONT_PROVIDERS.put(DEFAULT_FONT,
                new JDefaultFontProvider("fonts/simhei.ttf"));
    }

    private JFontProviderFactory() {
    }

    public static JFontProvider defaultFont() {
        return getFont(DEFAULT_FONT);
    }

    public static JFontProvider getFont(String name) {
        JFontProvider provider = FONT_PROVIDERS.get(name);
        if (provider == null) {
            throw new IllegalArgumentException("未注册字体: " + name);
        }
        return provider;
    }

    public static void registerFileFont(String name, String fontPath) {
        FONT_PROVIDERS.put(name, new JFileFontProvider(fontPath));
    }
}
