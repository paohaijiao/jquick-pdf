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
 * 单据 166：联合奖惩清单 / 失信修复受理单（gov 领域第 12 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/166_credit_repair_receipt.txt} 中。</p>
 *
 * <p>视觉主题「紫棠·素雅·典式」：主色 #5B1EB1、辅色 #918C27、浅底 #F3F0F7；
 * 版式为 classic 抬头 + strip 信息区 + light 明细表 + two 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>联合奖惩清单 / 失信修复受理单：失信行为类别与认定文号、履行情况，</p>
 * <p>逐项联合奖惩措施与执行状态、受理结论与办理时限、受理机关签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/166_credit_repair_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz166CreditRepairReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "166_credit_repair_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/gov/166_credit_repair_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "限制参与政府采购活动", "××市财政局", "惩戒", "已解除", "修复后解除"},
                {"2", "限制取得政府供应土地", "××市自然资源和规划局", "惩戒", "已解除", "修复后解除"},
                {"3", "纳入重点监管对象", "××市市场监督管理局", "惩戒", "已解除", "修复后解除"},
                {"4", "金融机构信贷审慎参考", "××银保监分局", "惩戒", "参考类", "自行判断"},
                {"5", "信用中国网站公示", "××市信用办", "惩戒", "已移除", "公示期满"},
                {"6", "守信激励：绿色通道", "××市政务服务中心", "激励", "待申请", "修复后可申请"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("acceptNo", "CRP-20261029-0077");
        data.put("acceptDate", "2026年10月29日");
        data.put("orgName", "××市社会信用体系建设领导小组办公室");
        data.put("companyName", "××物资供应有限公司");
        data.put("creditCode", "91320500MA1M8T3X7Y");
        data.put("dishonestType", "一般失信行为（行政处罚类）");
        data.put("recordNo", "××市监处罚〔2026〕118 号");
        data.put("recordDate", "2026年04月18日");
        data.put("performInfo", "罚款已缴纳，违法行为已整改完毕");
        data.put("repairType", "信用修复（提前移除公示）");
        data.put("acceptResult", "材料齐全，予以受理");
        data.put("minPublicDate", "2026年10月18日");
        data.put("timeLimit", "信用修复申请自受理之日起 15 个工作日内作出决定；符合条件的，终止公示并移除失信信息。");
        data.put("remark", "申请主体须对提交材料的真实性负责；提供虚假材料的，记入信用记录并从重处理。");
        data.put("acceptor", "苏见宁");
        data.put("reviewer", "柳文渊");
        data.put("companySign", "崔行知");

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
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市信用办　失信修复受理单　CRP-20261029-0077");
        config.getHeaderConfig().setFontSize(8f);
        config.getHeaderConfig().setHeight(34);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("— %d —");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE + "（" + pdf.length + " 字节）");
    }
}
