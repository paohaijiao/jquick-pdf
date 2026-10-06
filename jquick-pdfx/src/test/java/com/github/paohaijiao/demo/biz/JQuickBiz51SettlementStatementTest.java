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
package com.github.paohaijiao.demo.biz;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 结算单 Demo 51：工程材料结算单（工程财务场景）
 *
 * <p>业务场景：工程项目的甲乙双方按供货期结算货款，需在单据上同时体现双方主体信息、
 * 费用明细、金额大写小写、收款账户与盖章位，作为付款依据归档。</p>
 *
 * <p>版式：居中标题 + 上下细线；甲乙双方并列双盒（盒头深色条）；费用明细五列表且
 * 合计金额直接落在末行高亮单元格；大写/小写金额条；收款账户与结算说明左右并排；
 * 底部「左侧备注 + 右侧盖章框（内容垂直居中）」。</p>
 *
 * <p>本模板为纯版式 Demo，不含图表，因此不需要绑定 {@code svg} 变量；
 * 模板：{@code report/biz/51_settlement_statement.txt}，输出：{@code D:\test\biz\51_settlement_statement.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz51SettlementStatementTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz51SettlementStatement() throws IOException {
        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/51_settlement_statement.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（双方双盒并排、金额条两端对齐、盖章区）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        byte[] bytes = factory.executeResource("report/biz/51_settlement_statement.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
