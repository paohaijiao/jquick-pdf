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

public class JStyleBlockAttributes extends JStyleElementPropertyAttributes {


    public static final String marginLeft = "marginLeft";

    public static final String marginRight = "marginRight";

    public static final String marginTop = "marginTop";

    public static final String marginBottom = "marginBottom";

    public static final String commonMargin = "commonMargin";

    public static final String margins = "margins";

    public static final String paddingLeft = "paddingLeft";

    public static final String paddingRight = "paddingRight";

    public static final String paddingTop = "paddingTop";

    public static final String paddingBottom = "paddingBottom";

    public static final String commonPadding = "commonPadding";

    public static final String paddings = "paddings";

    public static final String verticalAlignment = "verticalAlignment";

    public static final String spacingRatio = "spacingRatio";

    public static final String keepTogether = "keepTogether";

    public static final String keepWithNext = "keepWithNext";

    public static final String angleInRadians = "angleInRadians";

    public static final String width = "width";

    public static final String height = "height";

    public static final String maxHeight = "maxHeight";

    public static final String minHeight = "minHeight";

    public static final String minWidth = "minWidth";

    public static final String maxWidth = "maxWidth";

    public String getMarginLeft() {
        return get(JStyleBlockAttributes.marginLeft);
    }

    public void setMarginLeft(String marginLeft) {
        put(JStyleBlockAttributes.marginLeft, marginLeft);
    }

    public String getMarginRight() {
        return get(JStyleBlockAttributes.marginRight);
    }

    public void setMarginRight(String marginRight) {
        put(JStyleBlockAttributes.marginRight, marginRight);
    }

    public String getMarginTop() {
        return get(JStyleBlockAttributes.marginTop);
    }

    public void setMarginTop(String marginTop) {
        put(JStyleBlockAttributes.marginTop, marginTop);
    }

    public String getMarginBottom() {
        return get(JStyleBlockAttributes.marginBottom);
    }

    public void setMarginBottom(String marginBottom) {
        put(JStyleBlockAttributes.marginBottom, marginBottom);
    }

    public String getCommonMargin() {
        return get(JStyleBlockAttributes.commonMargin);
    }

    public void setCommonMargin(String commonMargin) {
        put(JStyleBlockAttributes.commonMargin, commonMargin);
    }

    public String getMargins() {
        return get(JStyleBlockAttributes.margins);
    }

    public void setMargins(String margins) {
        put(JStyleBlockAttributes.margins, margins);
    }

    public String getPaddingLeft() {
        return get(JStyleBlockAttributes.paddingLeft);
    }

    public void setPaddingLeft(String paddingLeft) {
        put(JStyleBlockAttributes.paddingLeft, paddingLeft);
    }

    public String getPaddingRight() {
        return get(JStyleBlockAttributes.paddingRight);
    }

    public void setPaddingRight(String paddingRight) {
        put(JStyleBlockAttributes.paddingRight, paddingRight);
    }

    public String getPaddingTop() {
        return get(JStyleBlockAttributes.paddingTop);
    }

    public void setPaddingTop(String paddingTop) {
        put(JStyleBlockAttributes.paddingTop, paddingTop);
    }

    public String getPaddingBottom() {
        return get(JStyleBlockAttributes.paddingBottom);
    }

    public void setPaddingBottom(String paddingBottom) {
        put(JStyleBlockAttributes.paddingBottom, paddingBottom);
    }

    public String getCommonPadding() {
        return get(JStyleBlockAttributes.commonPadding);
    }

    public void setCommonPadding(String commonPadding) {
        put(JStyleBlockAttributes.commonPadding, commonPadding);
    }

    public String getPaddings() {
        return get(JStyleBlockAttributes.paddings);
    }

    public void setPaddings(String paddings) {
        put(JStyleBlockAttributes.paddings, paddings);
    }

    public String getVerticalAlignment() {
        return get(JStyleBlockAttributes.verticalAlignment);
    }

    public void setVerticalAlignment(String verticalAlignment) {
        put(JStyleBlockAttributes.verticalAlignment, verticalAlignment);
    }

    public String getSpacingRatio() {
        return get(JStyleBlockAttributes.spacingRatio);
    }

    public void setSpacingRatio(String spacingRatio) {
        put(JStyleBlockAttributes.spacingRatio, spacingRatio);

    }

    public String getKeepTogether() {
        return (String) get(JStyleBlockAttributes.keepTogether);
    }

    public JStyleBlockAttributes setKeepTogether(String keepTogether) {
        put(JStyleBlockAttributes.keepTogether, keepTogether);
        return this;
    }

    public String getKeepWithNext() {
        return (String) get(JStyleBlockAttributes.keepWithNext);
    }

    public JStyleBlockAttributes setKeepWithNext(String keepWithNext) {
        put(JStyleBlockAttributes.keepWithNext, keepWithNext);
        return this;
    }

    public String getAngleInRadians() {
        return get(JStyleBlockAttributes.angleInRadians);
    }

    public JStyleBlockAttributes setAngleInRadians(String angleInRadians) {
        put(JStyleBlockAttributes.angleInRadians, angleInRadians);
        return this;
    }

    public String getWidth() {
        return get(JStyleBlockAttributes.width);
    }

    public JStyleBlockAttributes setWidth(String width) {
        put(JStyleBlockAttributes.width, width);
        return this;
    }

    public String getHeight() {
        return get(JStyleBlockAttributes.height);
    }

    public JStyleBlockAttributes setHeight(String height) {
        put(JStyleBlockAttributes.height, height);
        return this;
    }

    public String getMaxHeight() {
        return get(JStyleBlockAttributes.maxHeight);
    }

    public JStyleBlockAttributes setMaxHeight(String maxHeight) {
        put(JStyleBlockAttributes.maxHeight, maxHeight);
        return this;
    }

    public String getMinHeight() {
        return get(JStyleBlockAttributes.minHeight);
    }

    public JStyleBlockAttributes setMinHeight(String minHeight) {
        put(JStyleBlockAttributes.minHeight, minHeight);
        return this;
    }

    public String getMinWidth() {
        return get(JStyleBlockAttributes.minWidth);
    }

    public JStyleBlockAttributes setMinWidth(String minWidth) {
        put(JStyleBlockAttributes.minWidth, minWidth);
        return this;
    }

    public String getMaxWidth() {
        return get(JStyleBlockAttributes.maxWidth);
    }

    public JStyleBlockAttributes setMaxWidth(String maxWidth) {
        put(JStyleBlockAttributes.maxWidth, maxWidth);
        return this;
    }
}
