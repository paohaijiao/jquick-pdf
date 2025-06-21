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

package com.paohaijiao.data.feature;

import com.paohaijiao.data.code.JMagic;
import com.paohaijiao.data.series.JFunnel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author martin
 */
public class JMagicType extends JFeature {
    private Option option;

    /**
     * 构造函数,参数:magics
     *
     * @param magics
     */
    public JMagicType(JMagic... magics) {
        this.show(true);
        Map title = new HashMap<String, String>();
        title.put("line", "折线图切换");
        title.put("bar", "柱形图切换");
        title.put("stack", "堆积");
        title.put("tiled", "平铺");
        this.title(title);
        if (magics == null || magics.length == 0) {
            this.type(new Object[]{JMagic.bar, JMagic.line, JMagic.stack, JMagic.tiled});
        } else {
            this.type(magics);
        }
    }

    /**
     * 设置Option
     *
     * @param option
     * @return
     */
    public JFeature option(Option option) {
        this.option = option;
        return this;
    }

    /**
     * 获取Option
     *
     * @return
     */
    public Option option() {
        return this.option;
    }

    /**
     * 获取option值
     *
     * @return
     */
    public Option getOption() {
        return option;
    }

    /**
     * 设置option
     *
     * @param option
     */
    public void setOption(Option option) {
        this.option = option;
    }

    /**
     * 内部类 Option
     */
    public static class Option {
        private JFunnel funnel;

        /**
         * 设置funnel值
         *
         * @param funnel
         */
        public Option funnel(JFunnel funnel) {
            this.funnel = funnel;
            return this;
        }

        /**
         * 获取funnel值
         */
        public JFunnel funnel() {
            if (this.funnel == null) {
                this.funnel = new JFunnel();
            }
            return this.funnel;
        }
    }
}
