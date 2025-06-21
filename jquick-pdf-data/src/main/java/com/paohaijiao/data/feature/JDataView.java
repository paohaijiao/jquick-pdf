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

/**
 * @author martin
 */
public class JDataView extends JFeature {
    /**
     * 构造函数
     */
    public JDataView() {
        this.show(true);
        this.title("数据视图");
        this.readOnly(false);
        this.lang(new String[]{"数据视图", "关闭", "刷新"});
    }
}
