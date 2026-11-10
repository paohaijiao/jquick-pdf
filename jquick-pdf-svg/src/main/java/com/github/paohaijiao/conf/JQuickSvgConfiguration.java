package com.github.paohaijiao.conf;

import java.util.Collections;
import java.util.List;

public class JQuickSvgConfiguration {

    private final List<String> scanPath = new java.util.concurrent.CopyOnWriteArrayList<>();

    private JQuickSvgConfiguration() {
    }

    private static class Holder {
        private static final JQuickSvgConfiguration INSTANCE = new JQuickSvgConfiguration();
    }

    public static JQuickSvgConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    public List<String> getScanPath() {
        return Collections.unmodifiableList(scanPath);
    }

    public void setScanPath(List<String> scanPath) {
        this.scanPath.clear();
        if (scanPath != null) {
            this.scanPath.addAll(scanPath);
        }
    }

    public JQuickSvgConfiguration addScanPath(String path) {
        if (path != null && !path.isEmpty()) {
            this.scanPath.add(path);
        }
        return this;
    }

    public void clearScanPath() {
        this.scanPath.clear();
    }
}
