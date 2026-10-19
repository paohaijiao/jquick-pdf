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
package com.github.paohaijiao.biz.supply;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 180：采购订单 / 送货单 / 入库验收单（supply 领域第 1 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/supply/180_purchase_order_receipt.txt} 中。</p>
 *
 * <p>视觉主题「绯红·斑马·票式」：主色 #822C19、辅色 #279173、浅底 #F7F1F0；
 * 版式为 ticket 抬头 + grid2 信息区 + striped 明细表 + right 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>采购订单 / 送货单 / 入库验收单：订单号与供应商、送货与收货仓库信息，</p>
 * <p>逐项物料（编码、订单数、实收数、单价）与金额合计、数量差异核对与三方签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/180_purchase_order_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz180PurchaseOrderReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "180_purchase_order_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/supply/180_purchase_order_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 8;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "伺服电机 SVM-750W", "MAT-001188", "12", "12", "1860.00", "合格"},
                {"2", "精密减速机 RV-40E", "MAT-001192", "12", "12", "1240.00", "合格"},
                {"3", "运动控制卡 MC-8020", "MAT-001203", "6", "6", "2350.00", "合格"},
                {"4", "编码器线缆 ENC-5M", "MAT-001210", "24", "24", "86.00", "合格"},
                {"5", "直线导轨 HGR-20", "MAT-001216", "16", "16", "328.00", "合格"},
                {"6", "气动元件套件 PN-50", "MAT-001221", "8", "8", "486.00", "合格"},
                {"7", "安装调试辅料", "MAT-001230", "1", "1", "1216.00", "合格"},
                {"8", "备品备件（免费）", "MAT-001240", "2", "2", "0.00", "合格"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("orderNo", "PO-2026-0921");
        data.put("dateInfo", "2026年09月21日 / 2026年10月08日");
        data.put("buyerName", "××智能装备制造有限公司");
        data.put("deptInfo", "采购部 / 路承志");
        data.put("supplierName", "××精密机械制造有限公司");
        data.put("supplierInfo", "SUP-00218 / 崔行知 025-66××××××");
        data.put("deliveryInfo", "DN-20261008-0033 / 苏A·D72K18");
        data.put("warehouseInfo", "原材料库 / A-01 ~ A-06");
        data.put("payInfo", "月结 60 天 / CNY");
        data.put("contractNo", "SC-2026-0713");
        data.put("checkResult", "数量相符，外观完好，准予入库");
        data.put("checkInfo", "2026年10月08日 / 邵怀信");
        data.put("arriveDate", "2026年10月08日");
        data.put("remark", "本单一式四联：采购联、仓库联、财务联、供应商联；数量或质量有异议的，须在收货后 3 个工作日内书面提出。");
        data.put("buyer", "路承志");
        data.put("keeper", "邵怀信");
        data.put("supplierSign", "崔行知");

        // 明细逐格展开，并累计金额
        BigDecimal itemTotal = BigDecimal.ZERO;
        for (int i = 0; i < ITEM_ROWS; i++) {
            int n = i + 1;
            if (i >= items.length) {
                data.put("itemName" + n, "—");
                data.put("itemSpec" + n, "—");
                data.put("itemUnit" + n, "—");
                data.put("itemQty" + n, "—");
                data.put("itemPrice" + n, "—");
                data.put("itemAmount" + n, "—");
                data.put("itemTax" + n, "—");
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][5]));
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("totalLine", "入库物料金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("qtyLine", "订单数量合计 81 件，实收数量合计 81 件，数量差异 0 件，验收合格率 100%");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××智能装备制造有限公司　采购订单 / 入库验收单　PO-2026-0921");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(32);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE + "（" + pdf.length + " 字节）");
    }

    /** 人民币金额转票据大写（元角分，末尾补「整」）。 */
    private static String toChineseAmount(BigDecimal amount) {
        String[] digits = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] units = {"", "拾", "佰", "仟"};
        String[] sections = {"", "万", "亿", "万亿"};
        long cents = amount.setScale(2, BigDecimal.ROUND_HALF_UP)
                .movePointRight(2).longValue();
        if (cents == 0) {
            return "零元整";
        }
        long yuan = cents / 100;
        int jiao = (int) (cents % 100 / 10);
        int fen = (int) (cents % 10);
        StringBuilder sb = new StringBuilder();
        if (yuan > 0) {
            sb.append(toChineseSection(yuan, digits, units, sections)).append("元");
        }
        if (jiao == 0 && fen == 0) {
            sb.append("整");
        } else {
            if (jiao > 0) {
                sb.append(digits[jiao]).append("角");
            } else if (yuan > 0) {
                sb.append("零");
            }
            if (fen > 0) {
                sb.append(digits[fen]).append("分");
            }
        }
        return sb.toString();
    }

    /** 整数部分按四位分节转大写。 */
    private static String toChineseSection(long value, String[] digits, String[] units, String[] sections) {
        StringBuilder sb = new StringBuilder();
        int sectionIndex = 0;
        boolean needZero = false;
        while (value > 0) {
            int section = (int) (value % 10000);
            StringBuilder part = new StringBuilder();
            int unitIndex = 0;
            boolean zeroInSection = false;
            int temp = section;
            while (temp > 0) {
                int digit = temp % 10;
                if (digit == 0) {
                    zeroInSection = true;
                } else {
                    if (zeroInSection || (needZero && part.length() == 0 && sb.length() > 0)) {
                        part.insert(0, digits[0]);
                    }
                    part.insert(0, units[unitIndex]);
                    part.insert(0, digits[digit]);
                    zeroInSection = false;
                    needZero = false;
                }
                unitIndex++;
                temp /= 10;
            }
            if (part.length() > 0) {
                part.append(sections[sectionIndex]);
            }
            sb.insert(0, part);
            needZero = section < 1000 && section > 0;
            value /= 10000;
            sectionIndex++;
        }
        return sb.toString();
    }
}
