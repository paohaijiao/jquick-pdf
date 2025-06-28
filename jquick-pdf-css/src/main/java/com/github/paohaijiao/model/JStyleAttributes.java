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
import com.github.paohaijiao.model.font.JStyleFontModel;

import java.util.HashMap;

/**
 * packageName com.github.paohaijiao.model
 *
 * @author Martin
 * @version 1.0.0
 * @className HtmlAttributes
 * @date 2025/6/22
 * @description
 */
public class JStyleAttributes extends JStyleFontModel {
    public static final String ID = "id";
    public static final String CLASS = "class";
    public static final String STYLE = "style";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String COLOR = "color";
    public static final String BACKGROUND_COLOR = "background-color";

    public static final String MARGIN = "margin";
    public static final String PADDING = "padding";
    public static final String BORDER = "border";
    public static final String TEXT_ALIGN = "text-align";
    public static final String DISPLAY = "display";
    public static final String POSITION = "position";
    public static final String HREF = "href";
    public static final String SRC = "src";
    public static final String ALT = "alt";
    public static final String TITLE = "title";
    public static final String TARGET = "target";
    public static final String TYPE = "type";
    public static final String VALUE = "value";
    public static final String PLACEHOLDER = "placeholder";
    public static final String DISABLED = "disabled";
    public static final String READONLY = "readonly";
    public static final String CHECKED = "checked";
    public static final String SELECTED = "selected";
    public static final String MAX_LENGTH = "maxlength";
    public static final String TAB_INDEX = "tabindex";
    public static final String AUTO_FOCUS = "autofocus";
    public static final String REQUIRED = "required";
    public void setId(String id) {
        put(ID, id);
    }
    public String getId() {
        return get(ID);
    }
    public void setClass(String className) {
        put(CLASS, className);
    }
    public String getHtmlClass() {
        return get(CLASS);
    }
    public void setStyle(String style) {
        put(STYLE, style);
    }
    public String getStyle() {
        return get(STYLE);
    }
    public void setWidth(String width) {
        put(WIDTH, width);
    }

    public String getWidth() {
        return get(WIDTH);
    }

    public void setHeight(String height) {
        put(HEIGHT, height);
    }


    public String getHeight() {
        return get(HEIGHT);
    }


    public void setColor(String color) {
        put(COLOR, color);
    }


    public String getColor() {
        return get(COLOR);
    }


    public void setBackgroundColor(String backgroundColor) {
        put(BACKGROUND_COLOR, backgroundColor);
    }


    public String getBackgroundColor() {
        return get(BACKGROUND_COLOR);
    }


    public void setFontSize(String fontSize) {
        put(FONT_SIZE, fontSize);
    }

    public Float getFontSize() {
        String fontSize= get(FONT_SIZE);
        if(null==fontSize){
            return 12f;
        }else{
            return Float.valueOf(fontSize);
        }

    }



    public void setMargin(String margin) {
        put(MARGIN, margin);
    }

    public String getMargin() {
        return get(MARGIN);
    }

    public void setPadding(String padding) {
        put(PADDING, padding);
    }

    public String getPadding() {
        return get(PADDING);
    }

    public void setBorder(String border) {
        put(BORDER, border);
    }

    public String getBorder() {
        return get(BORDER);
    }

    public void setTextAlign(String textAlign) {
        put(TEXT_ALIGN, textAlign);
    }

    public String getTextAlign() {
        return get(TEXT_ALIGN);
    }

    public void setHref(String href) {
        put(HREF, href);
    }


    public String getHref() {
        return get(HREF);
    }

    public void setSrc(String src) {
        put(SRC, src);
    }

    public String getSrc() {
        return get(SRC);
    }

    public void setAlt(String alt) {
        put(ALT, alt);
    }

    public String getAlt() {
        return get(ALT);
    }

    public void setTitle(String title) {
        put(TITLE, title);
    }

    public String getTitle() {
        return get(TITLE);
    }

    public void setDisabled(boolean disabled) {
        if (disabled) {
            put(DISABLED, "disabled");
        } else {
            remove(DISABLED);
        }
    }

    public boolean isDisabled() {
        return containsKey(DISABLED);
    }

    public void setReadonly(boolean readonly) {
        if (readonly) {
            put(READONLY, "readonly");
        } else {
            remove(READONLY);
        }
    }


    public boolean isReadonly() {
        return containsKey(READONLY);
    }

    public void setChecked(boolean checked) {
        if (checked) {
            put(CHECKED, "checked");
        } else {
            remove(CHECKED);
        }
    }


    public boolean isChecked() {
        return containsKey(CHECKED);
    }


    public void setSelected(boolean selected) {
        if (selected) {
            put(SELECTED, "selected");
        } else {
            remove(SELECTED);
        }
    }


    public boolean isSelected() {
        return containsKey(SELECTED);
    }

    public String toHtmlString() {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : entrySet()) {
            sb.append(entry.getKey())
                    .append("=\"")
                    .append(entry.getValue())
                    .append("\" ");
        }
        return sb.toString().trim();
    }

    public void addCss(String property, String value) {
        String currentStyle = getStyle();
        if (currentStyle == null) {
            currentStyle = "";
        }

        if (!currentStyle.isEmpty() && !currentStyle.endsWith(";")) {
            currentStyle += ";";
        }

        currentStyle += property + ":" + value + ";";
        setStyle(currentStyle);
    }

    public void removeCss(String property) {
        String currentStyle = getStyle();
        if (currentStyle == null || currentStyle.isEmpty()) {
            return;
        }

        String[] styles = currentStyle.split(";");
        StringBuilder newStyle = new StringBuilder();

        for (String style : styles) {
            if (!style.trim().isEmpty() && !style.trim().startsWith(property + ":")) {
                newStyle.append(style.trim()).append(";");
            }
        }

        setStyle(newStyle.toString());
    }
}
