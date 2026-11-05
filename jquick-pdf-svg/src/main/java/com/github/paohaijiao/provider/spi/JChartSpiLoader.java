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
package com.github.paohaijiao.provider.spi;

import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.spi.JQuickServiceLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * packageName com.github.paohaijiao.provider.spi
 *
 * @author Martin
 * @version 1.0.0
 * @className JChartSpiLoader
 * @date 2026/11/5
 * @description SPI loader for chart renderers.
 * <p>
 * {@link JQuickServiceLoader} is kept as a compatibility fallback. The primary path is
 * {@link JQuickSpi} annotation scanning: every JAbstractChartRenderer implementation on the
 * classpath is inspected, its name, order and chartType are read, and a JChartType to renderer
 * mapping is built in ascending order.
 * <p>
 * No META-INF/services file is needed and the JDK ServiceLoader is not used directly.
 * Only renderer types are cached. Each creation returns a new instance.
 */
public final class JChartSpiLoader {

    private static final String SCAN_PACKAGE = "com.github.paohaijiao";

    private static final String SCAN_PATH = SCAN_PACKAGE.replace('.', '/');

    /**
     * Extra packages to scan, comma separated,
     * for example -Djchart.spi.packages=com.example.chart
     */
    private static final String SCAN_PACKAGES_PROPERTY = "jchart.spi.packages";

    private static final String CLASS_SUFFIX = ".class";

    private static volatile List<Class<? extends JAbstractChartRenderer>> rendererCache;

    private JChartSpiLoader() {
    }

    /**
     * Returns every {@link JQuickSpi} annotated renderer type, sorted by order.
     * The scan result is cached after the first call.
     *
     * @return renderer types, never null
     */
    public static List<Class<? extends JAbstractChartRenderer>> scanRenderers() {
        List<Class<? extends JAbstractChartRenderer>> cached = rendererCache;
        if (cached != null) {
            return cached;
        }
        synchronized (JChartSpiLoader.class) {
            if (rendererCache == null) {
                rendererCache = doScanRenderers();
            }
            return rendererCache;
        }
    }

    private static List<Class<? extends JAbstractChartRenderer>> doScanRenderers() {
        List<Class<? extends JAbstractChartRenderer>> renderers = new ArrayList<>();
        for (String className : scanClassNames()) {
            Class<? extends JAbstractChartRenderer> renderer = resolveRenderer(className);
            if (renderer != null) {
                renderers.add(renderer);
            }
        }
        if (renderers.isEmpty()) {
            renderers.addAll(loadCompatibleServices());
        }
        renderers.sort(new Comparator<Class<? extends JAbstractChartRenderer>>() {
            @Override
            public int compare(Class<? extends JAbstractChartRenderer> left, Class<? extends JAbstractChartRenderer> right) {
                int result = Integer.compare(orderOf(left), orderOf(right));
                return result != 0 ? result : left.getName().compareTo(right.getName());
            }
        });
        return Collections.unmodifiableList(renderers);
    }

    /**
     * Builds the string key to renderer type mapping.
     * <p>
     * Every renderer is registered under its {@link JQuickSpi#name()} and
     * {@link JQuickSpi#type()}, plus the code and constant name of its {@link JChartType}
     * when the enum is bound. The string key is what a third-party plugin uses, so no enum
     * constant has to be added to the jar.
     * If several renderers share a key, the one with the smallest order wins.
     *
     * @return immutable mapping, never null
     */
    public static Map<String, Class<? extends JAbstractChartRenderer>> loadRendererMapping() {
        Map<String, Class<? extends JAbstractChartRenderer>> mapping = new LinkedHashMap<>();
        for (Class<? extends JAbstractChartRenderer> renderer : scanRenderers()) {
            JQuickSpi spi = renderer.getAnnotation(JQuickSpi.class);
            if (spi == null) {
                continue;
            }
            for (String key : keysOf(spi)) {
                register(mapping, key, renderer);
            }
        }
        return Collections.unmodifiableMap(mapping);
    }

    /**
     * Resolves a renderer class by type key, for example bar, BAR, candlestick or sankey.
     * The lookup falls back to the lower-case form of the key.
     *
     * @param type type key
     * @return renderer type, or null when nothing is registered
     */
    public static Class<? extends JAbstractChartRenderer> resolveRendererType(String type) {
        if (type == null) {
            return null;
        }
        Map<String, Class<? extends JAbstractChartRenderer>> mapping = loadRendererMapping();
        Class<? extends JAbstractChartRenderer> renderer = mapping.get(type);
        if (renderer != null) {
            return renderer;
        }
        String key = type.trim();
        renderer = mapping.get(key);
        if (renderer != null) {
            return renderer;
        }
        return mapping.get(key.toLowerCase(Locale.ROOT));
    }

    private static List<String> keysOf(JQuickSpi spi) {
        Set<String> keys = new LinkedHashSet<>();
        addKey(keys, spi.name());
        addKey(keys, spi.type());
        JChartType chartType = spi.chartType();
        if (chartType != null && chartType != JChartType.NONE) {
            addKey(keys, chartType.getCode());
            addKey(keys, chartType.name());
        }
        return new ArrayList<>(keys);
    }

    private static void addKey(Set<String> keys, String key) {
        if (key != null && !key.trim().isEmpty()) {
            keys.add(key.trim());
        }
    }

    private static void register(Map<String, Class<? extends JAbstractChartRenderer>> mapping,
                                 String key,
                                 Class<? extends JAbstractChartRenderer> renderer) {
        Class<? extends JAbstractChartRenderer> registered = mapping.get(key);
        if (registered == null || orderOf(renderer) < orderOf(registered)) {
            mapping.put(key, renderer);
        }
    }

    /**
     * Builds the JChartType to renderer type mapping, kept for callers of the enum API.
     * A type whose renderer is missing from the string mapping is simply absent.
     *
     * @return immutable mapping, never null
     */
    public static Map<JChartType, Class<? extends JAbstractChartRenderer>> loadTypeMapping() {
        Map<JChartType, Class<? extends JAbstractChartRenderer>> mapping = new EnumMap<>(JChartType.class);
        for (JChartType chartType : JChartType.values()) {
            if (chartType == JChartType.NONE) {
                continue;
            }
            Class<? extends JAbstractChartRenderer> renderer = resolveRendererType(chartType.getCode());
            if (renderer != null) {
                mapping.put(chartType, renderer);
            }
        }
        return Collections.unmodifiableMap(mapping);
    }

    /**
     * Resolves a chart type by renderer alias, that is {@link JQuickSpi#name()}.
     * Returns null for plugin renderers that only declare a string type.
     *
     * @param name alias or code
     * @return matching chart type, or null
     */
    public static JChartType resolveType(String name) {
        if (name == null) {
            return null;
        }
        for (Class<? extends JAbstractChartRenderer> renderer : scanRenderers()) {
            JQuickSpi spi = renderer.getAnnotation(JQuickSpi.class);
            if (spi != null && name.equals(spi.name()) && spi.chartType() != JChartType.NONE) {
                return spi.chartType();
            }
        }
        return JChartType.codeOf(name);
    }

    private static Class<? extends JAbstractChartRenderer> resolveRenderer(String className) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className, false, classLoader());
        } catch (Throwable e) {
            return null;
        }
        if (!JAbstractChartRenderer.class.isAssignableFrom(clazz)) {
            return null;
        }
        if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
            return null;
        }
        if (!clazz.isAnnotationPresent(JQuickSpi.class)) {
            return null;
        }
        return clazz.asSubclass(JAbstractChartRenderer.class);
    }

    /**
     * Fallback for the legacy JQuickServiceLoader registration, used only when the
     * annotation scan returns nothing. Providers on this path still need {@link JQuickSpi}
     * to supply the chart type binding.
     */
    @SuppressWarnings("unchecked")
    private static List<Class<? extends JAbstractChartRenderer>> loadCompatibleServices() {
        List<Class<? extends JAbstractChartRenderer>> renderers = new ArrayList<>();
        try {
            List<JAbstractChartRenderer> services = JQuickServiceLoader.loadAvailableServices(JAbstractChartRenderer.class);
            for (JAbstractChartRenderer service : services) {
                if (service.getClass().isAnnotationPresent(JQuickSpi.class)) {
                    renderers.add((Class<? extends JAbstractChartRenderer>) service.getClass());
                }
            }
        } catch (Throwable ignored) {
            return Collections.emptyList();
        }
        return renderers;
    }

    private static int orderOf(Class<? extends JAbstractChartRenderer> renderer) {
        JQuickSpi spi = renderer.getAnnotation(JQuickSpi.class);
        return spi == null ? 0 : spi.order();
    }

    private static ClassLoader classLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader != null ? classLoader : JChartSpiLoader.class.getClassLoader();
    }

    private static Set<String> scanClassNames() {
        Set<String> classNames = new LinkedHashSet<>();
        for (String packageName : scanPackages()) {
            String packagePath = packageName.replace('.', '/');
            Enumeration<URL> resources;
            try {
                resources = classLoader().getResources(packagePath);
            } catch (IOException e) {
                continue;
            }
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                try {
                    if ("file".equals(url.getProtocol())) {
                        scanDirectory(new File(decode(url.getPath())), packageName, classNames);
                    } else if ("jar".equals(url.getProtocol())) {
                        scanJar(url, packagePath, classNames);
                    }
                } catch (Throwable ignored) {
                    // skip unreadable classpath entries and keep the rest
                }
            }
        }
        scanCodeSource(classNames);
        return classNames;
    }

    private static List<String> scanPackages() {
        Set<String> packages = new LinkedHashSet<>();
        packages.add(SCAN_PACKAGE);
        String configured = System.getProperty(SCAN_PACKAGES_PROPERTY);
        if (configured != null) {
            for (String item : configured.split(",")) {
                String packageName = item.trim();
                if (!packageName.isEmpty()) {
                    packages.add(packageName);
                }
            }
        }
        return new ArrayList<>(packages);
    }

    /**
     * Some build outputs omit directory entries, so getResources may not return the package
     * path. In that case the code source of JAbstractChartRenderer is scanned as a fallback.
     */
    private static void scanCodeSource(Set<String> classNames) {
        try {
            CodeSource codeSource = JAbstractChartRenderer.class.getProtectionDomain().getCodeSource();
            if (codeSource == null || codeSource.getLocation() == null) {
                return;
            }
            URL location = codeSource.getLocation();
            if (!"file".equals(location.getProtocol())) {
                return;
            }
            File file = new File(decode(location.getPath()));
            if (file.isDirectory()) {
                scanDirectory(new File(file, SCAN_PATH), SCAN_PACKAGE, classNames);
            } else if (file.getName().endsWith(".jar")) {
                try (JarFile jarFile = new JarFile(file)) {
                    scanJarFile(jarFile, SCAN_PATH, classNames);
                }
            }
        } catch (Throwable ignored) {
            // keep the current result when the fallback scan fails
        }
    }

    private static void scanDirectory(File directory, String packageName, Set<String> classNames) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + "." + file.getName(), classNames);
            } else if (file.getName().endsWith(CLASS_SUFFIX) && file.getName().indexOf('$') < 0) {
                classNames.add(packageName + "." + file.getName().substring(0, file.getName().length() - CLASS_SUFFIX.length()));
            }
        }
    }

    private static void scanJar(URL url, String packagePath, Set<String> classNames) throws IOException {
        JarURLConnection connection = (JarURLConnection) url.openConnection();
        connection.setUseCaches(false);
        try (JarFile jarFile = connection.getJarFile()) {
            scanJarFile(jarFile, packagePath, classNames);
        }
    }

    private static void scanJarFile(JarFile jarFile, String packagePath, Set<String> classNames) {
        Enumeration<JarEntry> entries = jarFile.entries();
        String prefix = packagePath + "/";
        while (entries.hasMoreElements()) {
            String entryName = entries.nextElement().getName();
            if (!entryName.startsWith(prefix) || !entryName.endsWith(CLASS_SUFFIX) || entryName.indexOf('$') >= 0) {
                continue;
            }
            String className = entryName.substring(0, entryName.length() - CLASS_SUFFIX.length()).replace('/', '.');
            classNames.add(className);
        }
    }

    private static String decode(String path) {
        try {
            return URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {
            return path;
        }
    }
}
