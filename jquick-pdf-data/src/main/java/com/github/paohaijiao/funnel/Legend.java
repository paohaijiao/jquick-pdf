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
package com.github.paohaijiao.funnel;

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

public class Legend {
    private boolean show = true;
    private String left = "center";
    private String top = "bottom";
    private String orient = "horizontal"; // horizontal, vertical

    public Legend() {
    }

    public Legend show(boolean show) {
        this.show = show;
        return this;
    }

    public Legend left(String left) {
        this.left = left;
        return this;
    }

    public Legend top(String top) {
        this.top = top;
        return this;
    }

    public Legend orient(String orient) {
        this.orient = orient;
        return this;
    }

    public boolean show() {
        return show;
    }

    public String left() {
        return left;
    }

    public String top() {
        return top;
    }

    public String orient() {
        return orient;
    }
}
