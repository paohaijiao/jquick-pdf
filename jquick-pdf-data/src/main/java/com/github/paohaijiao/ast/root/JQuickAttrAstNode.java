package com.github.paohaijiao.ast.root;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Raw tag attributes in insertion order, printed as {@code key="value"} separated by
 * spaces. Styles go to {@link JQuickStyleAstNode} instead.
 */
public class JQuickAttrAstNode {

    /**
     * Insertion-ordered entries, keeps output stable.
     */
    private final LinkedHashMap<String, String> entries = new LinkedHashMap<String, String>();

    public JQuickAttrAstNode() {
    }

    /**
     * Copy constructor.
     */
    public JQuickAttrAstNode(JQuickAttrAstNode other) {
        if (other != null) {
            entries.putAll(other.entries);
        }
    }

    /**
     * Adds an attribute and returns this for chaining.
     */
    public JQuickAttrAstNode put(String key, String value) {
        if (key != null && value != null) {
            entries.put(key, value);
        }
        return this;
    }

    /**
     * Removes an attribute and returns this for chaining.
     */
    public JQuickAttrAstNode remove(String key) {
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
     * Prints {@code key="value"} pairs separated by spaces, or an empty string.
     */
    @Override
    public String toString() {
        if (entries.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(entry.getKey()).append("=\"").append(entry.getValue()).append('"');
        }
        return sb.toString();
    }
}
