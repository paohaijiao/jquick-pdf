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
package com.github.paohaijiao.biz.bank;

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
 * 单据 140：银行转账回单 / 现金缴款单（bank 领域第 1 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/140_bank_transfer_receipt.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·斑马·公文」：主色 #346E9A、辅色 #91272A、浅底 #F1F4F6；
 * 版式为 doc 抬头 + grid2 信息区 + striped 明细表 + split 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>银行转账回单 / 现金缴款单：付款人与收款人账户信息、交易渠道、</p>
 * <p>逐笔交易明细（金额、手续费、时间、状态）与交易金额合计、银行业务章占位。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/140_bank_transfer_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz140BankTransferReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "140_bank_transfer_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/bank/140_bank_transfer_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "跨行转账", "设备采购款 PO-2026-0812", "43000.00", "12.00", "09:42:18", "成功"},
                {"2", "跨行转账", "模具开发进度款", "18000.00", "8.00", "09:44:05", "成功"},
                {"3", "同城转账", "运输费结算", "3200.00", "0.00", "09:46:33", "成功"},
                {"4", "现金缴款", "营业款现金存入", "26800.00", "0.00", "14:12:07", "成功"},
                {"5", "现金缴款", "零星收入存入", "5300.00", "0.00", "14:15:22", "成功"},
                {"6", "转账退回", "收款账号有误退回", "-3200.00", "0.00", "16:03:41", "已冲正"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("serialNo", "ICBC26010120009318846");
        data.put("tradeDate", "2026年10月18日");
        data.put("currency", "CNY / 人民币");
        data.put("payerName", "××国际贸易有限公司");
        data.put("payerAccount", "4301 0123 4567 8901");
        data.put("payerBank", "中国工商银行××支行");
        data.put("payeeName", "××精密机械制造有限公司");
        data.put("payeeAccount", "1259 8877 6655 4433");
        data.put("payeeBank", "招商银行××分行");
        data.put("channel", "企业网银 / 跨行转账");
        data.put("remark", "本回单为银行电子回单，不作为收款方发货依据；交易数据以银行核心系统记录为准。");
        data.put("operator", "沈亦舟");
        data.put("reviewer", "柳文渊");
        data.put("bankSign", "中国工商银行××支行");

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
            itemTotal = itemTotal.add(new BigDecimal(items[i][3]));
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("feeTotal", new BigDecimal("20.00").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "交易金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("中国工商银行××支行　银行转账回单　ICBC26010120009318846");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(32);
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
