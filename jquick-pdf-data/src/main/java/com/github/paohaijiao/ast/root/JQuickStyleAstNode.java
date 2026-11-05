package com.github.paohaijiao.ast.root;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Inline style, printed as {@code key:value; key:value} in insertion order.
 * Sizes, fonts, spacing and colors all go through here.
 */
public class JQuickStyleAstNode {

    /**
     * Insertion-ordered entries, keeps output stable.
     */
    private final LinkedHashMap<String, String> entries = new LinkedHashMap<String, String>();

    public JQuickStyleAstNode() {
    }

    /**
     * Copy constructor.
     */
    public JQuickStyleAstNode(JQuickStyleAstNode other) {
        if (other != null) {
            entries.putAll(other.entries);
        }
    }

    /**
     * Adds a style and returns this for chaining.
     */
    public JQuickStyleAstNode put(String key, String value) {
        if (key != null && value != null) {
            entries.put(key, value);
        }
        return this;
    }

    /**
     * Removes a style and returns this for chaining.
     */
    public JQuickStyleAstNode remove(String key) {
        if (key != null) {
            entries.remove(key);
        }
        return this;
    }

    public String get(String key) {
        return key == null ? null : entries.get(key);
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /**
     * Read-only view for iteration.
     */
    public Map<String, String> getEntries() {
        return Collections.unmodifiableMap(entries);
    }

    /**
     * Prints {@code background:#0F4C81; padding:12px}, or an empty string.
     */
    @Override
    public String toString() {
        if (entries.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append(entry.getKey()).append(':').append(entry.getValue());
        }
        return sb.toString();
    }
}
