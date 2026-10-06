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
 * 回执单 Demo 54：业务受理回执单（政务窗口场景）
 *
 * <p>业务场景：政务综合窗口受理企业数据资产登记申请后，向申请人出具回执，
 * 载明受理信息、窗口已办事项确认、办理须知，并保留可裁剪的客户留存联与签收位。</p>
 *
 * <p>版式：细边框标题区（编号左、日期右）+ 四列受理信息表（仅下边框）+
 * 灰底通栏小节标题 + 两列 checkbox 确认事项 + 有序办理须知 + 裁剪线分隔客户留存联 +
 * 双栏签字区 + 联次注记。</p>
 *
 * <p>本模板为纯版式 Demo，不含图表，因此不需要绑定 {@code svg} 变量；
 * 模板：{@code report/biz/54_acknowledgement_receipt.txt}，输出：{@code D:\test\biz\54_acknowledgement_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz54AcknowledgementReceiptTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz54AcknowledgementReceipt() throws IOException {
        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/54_acknowledgement_receipt.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（标题区两端对齐、两列确认事项、双栏签字区）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        byte[] bytes = factory.executeResource("report/biz/54_acknowledgement_receipt.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
