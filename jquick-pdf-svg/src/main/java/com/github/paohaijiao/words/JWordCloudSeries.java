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
package com.github.paohaijiao.words;

import com.github.paohaijiao.code.JSeriesType;
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.series.JSeries;
import com.github.paohaijiao.style.JItemStyle;

import java.util.List;
/**
 * packageName com.github.paohaijiao.words
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/8/7
 */
public class JWordCloudSeries extends JSeries<JWordCloudSeries> {
    private List<JData> data;
    private Integer minFontSize = 12;
    private Integer maxFontSize = 72;
    private Integer gridSize = 8;
    private Integer rotationStep = 45;
    private Integer rotationRange = 90;
    private Boolean drawOutOfBound = false;
    private JItemStyle textStyle;
    public JWordCloudSeries() {
        this.type(JSeriesType.wordCloud);
    }

    public JWordCloudSeries(String name) {
        super(name);
        this.type(JSeriesType.wordCloud);
    }

    public List<JData> data() {
        return data;
    }

    public JWordCloudSeries data(List<JData> data) {
        this.data = data;
        return this;
    }

    public Integer minFontSize() {
        return minFontSize;
    }

    public JWordCloudSeries minFontSize(Integer minFontSize) {
        this.minFontSize = minFontSize;
        return this;
    }

    public Integer maxFontSize() {
        return maxFontSize;
    }

    public JWordCloudSeries maxFontSize(Integer maxFontSize) {
        this.maxFontSize = maxFontSize;
        return this;
    }

    public Integer gridSize() {
        return gridSize;
    }

    public JWordCloudSeries gridSize(Integer gridSize) {
        this.gridSize = gridSize;
        return this;
    }

    public Integer rotationStep() {
        return rotationStep;
    }

    public JWordCloudSeries rotationStep(Integer rotationStep) {
        this.rotationStep = rotationStep;
        return this;
    }

    public Integer rotationRange() {
        return rotationRange;
    }

    public JWordCloudSeries rotationRange(Integer rotationRange) {
        this.rotationRange = rotationRange;
        return this;
    }

    public Boolean drawOutOfBound() {
        return drawOutOfBound;
    }

    public JWordCloudSeries drawOutOfBound(Boolean drawOutOfBound) {
        this.drawOutOfBound = drawOutOfBound;
        return this;
    }

    public JItemStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JItemStyle();
        }
        return textStyle;
    }
    public JWordCloudSeries textStyle(JItemStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }


}
