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

public class QuickStartPdf {
    public static void main(String[] args) throws Exception {
        String template = ""
                + "<pdf><body>"
                + "<h1 style=\"fontSize:24;textAlignment:center;fontColor:#1f4e79\">'订单确认单'</h1>"
                + "<p>'客户：'${customer}</p>"
                + "<p>'订单号：'${orderNo}</p>"
                + "<table style=\"width:520px;border:solid 1px #999\">"
                + "<tr><th>'商品'</th><th>'数量'</th><th>'金额'</th></tr>"
                + "<tr><td>'Java 技术书'</td><td>'2'</td><td>'98.00'</td></tr>"
                + "</table></body></pdf>";

        byte[] pdf = new JQuickPdfFactory()
                .bind("customer", "张三")
                .bind("orderNo", "NO-2026001")
                .executeContent(template);
        Files.write(Paths.get("d://test//quick-start.pdf"), pdf);
    }
}