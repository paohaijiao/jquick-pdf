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

import com.paohaijiao.data.code.JLineType;
import com.paohaijiao.data.style.JLineStyle;

import java.util.HashMap;
import java.util.Map;

/**
 * 辅助线标志，上图icon左数1/2/3，分别是启用，删除上一条，删除全部，可设置更多属性
 *
 * @author martin
 */
public class JMark extends JFeature {
    /**
     * 构造函数
     */
    public JMark() {
        this.show(true);
        Map title = new HashMap<String, String>();
        title.put("mark", "辅助线开关");
        title.put("markUndo", "删除辅助线");
        title.put("markClear", "清空辅助线");
        this.title(title);
        this.lineStyle(new JLineStyle());
        this.lineStyle().width(2);
        this.lineStyle().color("#1e90ff");
        this.lineStyle().type(JLineType.dashed);
    }
}
