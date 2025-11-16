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

import java.awt.*;

/**
 * packageName com.github.paohaijiao.guage
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/16
 */
public class GuageConfig {

    private final int score;

    private final Color pointerColor;

    private final Color backgroundColor;

    private final Color scaleColor;

    private final Color textColor;

    private final String title;

    private final Font titleFont;

    private final Font scoreFont;

    private GuageConfig(int score, Color pointerColor, Color backgroundColor, Color scaleColor, Color textColor, String title, Font titleFont, Font scoreFont) {
        this.score = score;
        this.pointerColor = pointerColor;
        this.backgroundColor = backgroundColor;
        this.scaleColor = scaleColor;
        this.textColor = textColor;
        this.title = title;
        this.titleFont = titleFont;
        this.scoreFont = scoreFont;
    }

    public static ScoreMeterConfigBuilder builder() {
        return new ScoreMeterConfigBuilder();
    }

    public int score() {
        return score;
    }

    public Color pointerColor() {
        return pointerColor;
    }

    public Color backgroundColor() {
        return backgroundColor;
    }

    public Color scaleColor() {
        return scaleColor;
    }

    public Color textColor() {
        return textColor;
    }

    public String title() {
        return title;
    }

    public Font titleFont() {
        return titleFont;
    }

    public Font scoreFont() {
        return scoreFont;
    }

    public static class ScoreMeterConfigBuilder {

        private int score = 50;

        private Color pointerColor = new Color(80, 112, 221);

        private Color backgroundColor = new Color(232, 235, 240);

        private Color scaleColor = new Color(84, 85, 90);

        private Color textColor = new Color(60, 60, 65);

        private String title = "SCORE";

        private Font titleFont = new Font("Microsoft YaHei", Font.PLAIN, 16);

        private Font scoreFont = new Font("Microsoft YaHei", Font.BOLD, 30);

        public ScoreMeterConfigBuilder score(int score) {
            this.score = score;
            return this;
        }

        public ScoreMeterConfigBuilder pointerColor(Color pointerColor) {
            this.pointerColor = pointerColor;
            return this;
        }

        public ScoreMeterConfigBuilder backgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public ScoreMeterConfigBuilder scaleColor(Color scaleColor) {
            this.scaleColor = scaleColor;
            return this;
        }

        public ScoreMeterConfigBuilder textColor(Color textColor) {
            this.textColor = textColor;
            return this;
        }

        public ScoreMeterConfigBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ScoreMeterConfigBuilder titleFont(Font titleFont) {
            this.titleFont = titleFont;
            return this;
        }

        public ScoreMeterConfigBuilder scoreFont(Font scoreFont) {
            this.scoreFont = scoreFont;
            return this;
        }

        public GuageConfig build() {
            return new GuageConfig(score, pointerColor, backgroundColor, scaleColor, textColor, title, titleFont, scoreFont);
        }
    }
}
