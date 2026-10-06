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
 * 单据 Demo 48：物资入库单（仓储管理场景）
 *
 * <p>业务场景：物资储备企业收货入库时填写的三联单据，记录供货单位、采购合同、
 * 入库明细与金额合计，并留出保管员 / 验收人 / 仓库主管 / 财务复核四个签字位。</p>
 *
 * <p>版式：整页深蓝双线外框 + 页头左右分栏（左机构名与单据名 / 右编号信息栏）+
 * 四列表单式信息栏 + 八列入库明细表 + 大字金额合计条 + 左色条验收结论 + 四栏签字区。</p>
 *
 * <p>本模板为纯版式 Demo，不含图表，因此不需要绑定 {@code svg} 变量；
 * 模板：{@code report/biz/48_warehouse_receipt.txt}，输出：{@code D:\test\biz\48_warehouse_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz48WarehouseReceiptTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz48WarehouseReceipt() throws IOException {
        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/48_warehouse_receipt.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（页头双栏、金额条两端对齐、四栏签字区）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        byte[] bytes = factory.executeResource("report/biz/48_warehouse_receipt.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
