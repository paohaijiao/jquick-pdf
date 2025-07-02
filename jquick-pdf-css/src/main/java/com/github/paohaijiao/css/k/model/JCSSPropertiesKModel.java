package com.github.paohaijiao.css.k.model;

import com.github.paohaijiao.css.j.model.JCSSPropertiesJModel;

public class JCSSPropertiesKModel extends JCSSPropertiesJModel {
    public static final String KEYFRAMES = "@keyframes";


    /**
     * Defines a keyframes animation rule
     *
     * @param name    Animation name (must be valid CSS identifier)
     * @param content Keyframe steps (e.g., "0% {opacity: 0;} 100% {opacity: 1;}")
     * @throws IllegalArgumentException if name or content is invalid
     */
    public void setKeyframesRule(String name, String content) {
        if (isValidKeyframesName(name) && isValidKeyframesContent(content)) {
            put(KEYFRAMES + " " + name, content);
        } else {
            throw new IllegalArgumentException("Invalid @keyframes declaration");
        }
    }

    public String getKeyframesRule(String name) {
        return get(KEYFRAMES + " " + name);
    }


    private boolean isValidKeyframesName(String name) {
        return name != null && name.matches("^[a-zA-Z][a-zA-Z0-9-_]*$");
    }

    private boolean isValidKeyframesContent(String content) {
        return content != null && content.contains("{") && content.contains("}");
    }
}
