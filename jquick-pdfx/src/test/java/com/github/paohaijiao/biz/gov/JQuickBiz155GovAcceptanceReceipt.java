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
package com.github.paohaijiao.biz.gov;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 155：政务服务事项受理回执单（gov 领域第 1 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/155_gov_acceptance_receipt.txt} 中。</p>
 *
 * <p>视觉主题「青瓷·斑马·竖栏」：主色 #1BA072、辅色 #91277E、浅底 #F0F7F5；
 * 版式为 side 抬头 + kv 信息区 + striped 明细表 + right 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>政务服务事项受理回执单：受理编号与事项编码、申请人信息、办理类型与承诺时限，</p>
 * <p>申请材料清单与核验结果、办件进度提示与受理人 / 申请人签收栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/155_gov_acceptance_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz155GovAcceptanceReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "155_gov_acceptance_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/gov/155_gov_acceptance_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "政务服务事项申请表", "原件", "1", "窗口提交", "齐全"},
                {"2", "营业执照副本复印件", "复印件盖章", "1", "窗口提交", "齐全"},
                {"3", "项目可行性说明", "原件", "1", "窗口提交", "齐全"},
                {"4", "法人代表身份证明", "复印件", "1", "窗口提交", "齐全"},
                {"5", "土地 / 场地使用证明", "复印件", "1", "窗口提交", "齐全"},
                {"6", "授权委托书（代办时）", "原件", "1", "窗口提交", "齐全"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("acceptNo", "GS-20261018-03316");
        data.put("acceptDate", "2026年10月18日");
        data.put("orgName", "××市政务服务中心 ××局窗口");
        data.put("matterName", "企业投资项目备案");
        data.put("matterCode", "3201-GS-BM-0088");
        data.put("applicant", "××新能源科技股份有限公司");
        data.put("creditCode", "91440300MA5F3LQ28T");
        data.put("contactInfo", "崔行知 / 025-88××××××");
        data.put("handleType", "即办件 / 承诺件（承诺 5 个工作日）");
        data.put("acceptWay", "窗口受理（线上预约）");
        data.put("promiseDate", "2026年10月25日");
        data.put("deliverWay", "窗口自取 / 邮寄送达");
        data.put("progressNote", "本事项为承诺件，承诺办结时限 5 个工作日；申请人可凭受理编号在政务服务网或自助查询机查询办理进度。");
        data.put("remark", "本回执仅为受理凭证，不作为审批结论；补齐补正材料所需时间不计入承诺办结时限。");
        data.put("acceptor", "苏见宁");
        data.put("applicantSign", "崔行知");
        data.put("windowHead", "闻仲平");

        // 明细逐格展开
        for (int i = 0; i < ITEM_ROWS; i++) {
            int n = i + 1;
            if (i >= items.length) {
                data.put("itemName" + n, "—");
                data.put("itemSpec" + n, "—");
                data.put("itemUnit" + n, "—");
                data.put("itemQty" + n, "—");
                data.put("itemPrice" + n, "—");
                data.put("itemAmount" + n, "—");
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
        }

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市政务服务中心　政务事项受理回执单　GS-20261018-03316");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(30);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("· %d ·");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE + "（" + pdf.length + " 字节）");
    }
}
