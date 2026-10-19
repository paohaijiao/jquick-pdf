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
package com.github.paohaijiao.biz.customs;

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
 * 单据 128：商业发票 / 形式发票（COMMERCIAL INVOICE）（customs 领域第 4 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/128_commercial_proforma_invoice.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·密排·公文」：主色 #1C5680、辅色 #91272C、浅底 #F0F4F7；
 * 版式为 doc 抬头 + grid2 信息区 + compact 明细表 + split 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>外贸商业发票 / 形式发票：发票号码与合同号、买卖双方、贸易术语与付款方式、</p>
 * <p>中英对照货物明细与总金额、卖方签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/128_commercial_proforma_invoice.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz128CommercialProformaInvoice {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "128_commercial_proforma_invoice.pdf";
    private static final String TEMPLATE = "demo/biz/customs/128_commercial_proforma_invoice.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "伺服电机 / SERVO MOTOR", "SM-500W", "8 PCS", "224.60", "1796.80"},
                {"2", "精密齿轮箱 / GEAR BOX", "GB-120", "8 PCS", "506.90", "4055.20"},
                {"3", "运动控制卡 / CONTROL CARD", "MC-8020", "6 PCS", "325.40", "1952.40"},
                {"4", "三坐标测量仪 / CMM", "CMM-1210", "1 SET", "29800.00", "29800.00"},
                {"5", "工业阀门组件 / VALVE", "IV-50", "24 SETS", "86.50", "2076.00"},
                {"6", "技术资料 / DOCUMENT", "—", "1 SET", "1200.00", "1200.00"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("invoiceNo", "INV-2026-0918");
        data.put("invoiceDate", "2026年09月18日");
        data.put("contractNo", "SC-2026-0713");
        data.put("sellerName", "×× PRECISION MACHINERY PTE. LTD.");
        data.put("buyerName", "××国际贸易有限公司");
        data.put("tradeTerm", "FOB SINGAPORE");
        data.put("payTerm", "T/T 30% 定金，70% 见提单副本");
        data.put("portInfo", "SINGAPORE / SHANGHAI");
        data.put("remark", "形式发票（PROFORMA INVOICE）仅供买方申请许可证与开立信用证使用，不作为最终结算依据；最终金额以商业发票为准。");
        data.put("preparer", "苏见宁");
        data.put("reviewer", "柳文渊");
        data.put("signer", "×× PRECISION MACHINERY PTE. LTD.");

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
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][5]));
            data.put("itemAmount" + n, items[i][5]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "TOTAL AMOUNT（大写）" + toChineseAmount(itemTotal) + " 美元　　（小写）USD " + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("×× PRECISION MACHINERY PTE. LTD.　COMMERCIAL INVOICE　INV-2026-0918");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(28);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("· %d ·");

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
