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
package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MavenVerify {
    public static void main(String[] args) throws Exception {
        String template = "<pdf><body>"
                + "<h1 style=\"fontSize:22;textAlignment:center\">'Maven 环境验证'</h1>"
                + "<p>'当前用户：'${user}</p>"
                + "<p>'环境状态：'${status}</p>"
                + "</body></pdf>";
        byte[] pdf = new JQuickPdfFactory()
                .bind("user", "demo")
                .bind("status", "依赖、解析和输出正常")
                .executeContent(template);
        Files.write(Paths.get("d://test//maven-check.pdf"), pdf);
    }
}
