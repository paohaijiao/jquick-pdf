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
package com.github.paohaijiao.guage;


/**
 * 图表配置选项
 */
public class JGuageOption {

    private final String title;

    private final String subtext;

    private final Integer width;

    private final Integer height;

    private final GuageConfig scoreMeter;

    private JGuageOption(String title, String subtext, Integer width, Integer height, GuageConfig scoreMeter) {
        this.title = title;
        this.subtext = subtext;
        this.width = width;
        this.height = height;
        this.scoreMeter = scoreMeter;
    }

    public static JOptionBuilder builder() {
        return new JOptionBuilder();
    }

    public String title() {
        return title;
    }

    public String subtext() {
        return subtext;
    }

    public Integer width() {
        return width;
    }

    public Integer height() {
        return height;
    }

    public GuageConfig scoreMeter() {
        return scoreMeter;
    }

    public static class JOptionBuilder {

        private String title;

        private String subtext;

        private Integer width;

        private Integer height;

        private GuageConfig scoreMeter;

        public JOptionBuilder title(String title) {
            this.title = title;
            return this;
        }

        public JOptionBuilder subtext(String subtext) {
            this.subtext = subtext;
            return this;
        }

        public JOptionBuilder width(Integer width) {
            this.width = width;
            return this;
        }

        public JOptionBuilder height(Integer height) {
            this.height = height;
            return this;
        }

        public JOptionBuilder scoreMeter(GuageConfig scoreMeter) {
            this.scoreMeter = scoreMeter;
            return this;
        }

        public JGuageOption build() {
            return new JGuageOption(title, subtext, width, height, scoreMeter);
        }
    }
}
