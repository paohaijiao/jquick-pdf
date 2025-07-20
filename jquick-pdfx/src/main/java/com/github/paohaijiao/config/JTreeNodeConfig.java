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
package com.github.paohaijiao.config;

import com.github.paohaijiao.extension.tree.TreeNode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.config
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
@Data
public class JTreeNodeConfig<T extends TreeNode> extends HashMap<String, T> {

    @Override
    public T put(String key, T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        return super.put(key, value);
    }

    /**
     * Copies all the mappings from the specified map to this config.
     *
     * @param m mappings to be stored in this config
     * @throws IllegalArgumentException if any value in the map is null
     */
    @Override
    public void putAll(Map<? extends String, ? extends T> m) {
        for (Entry<? extends String, ? extends T> entry : m.entrySet()) {
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("Value cannot be null");
            }
        }
        super.putAll(m);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this config contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if no mapping exists
     */
    @Override
    public T get(Object key) {
        return super.get(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or throws an exception if the key is not found.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped
     * @throws IllegalArgumentException if the key is not found
     */
    public T getRequired(String key) {
        T value = get(key);
        if (value == null) {
            throw new IllegalArgumentException("No value found for key: " + key);
        }
        return value;
    }

    /**
     * Returns true if this config contains a mapping for the specified key.
     *
     * @param key the key whose presence in this config is to be tested
     * @return true if this config contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(key);
    }

    /**
     * Removes the mapping for the specified key from this config if present.
     *
     * @param key the key whose mapping is to be removed from the config
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
    @Override
    public T remove(Object key) {
        return super.remove(key);
    }

    /**
     * Removes all the mappings from this config.
     */
    @Override
    public void clear() {
        super.clear();
    }

    /**
     * Returns the number of key-value mappings in this config.
     *
     * @return the number of key-value mappings in this config
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * Returns true if this config contains no key-value mappings.
     *
     * @return true if this config contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    public T drawTree(String key) {
        if (containsKey(key)) {
            throw new IllegalArgumentException("tree " + key + " not exists");
        } else {
            T t = this.get(key);
            if (null == t) {
                throw new IllegalArgumentException("no tree found for key: " + key);
            }
            return t;
        }
    }

}
