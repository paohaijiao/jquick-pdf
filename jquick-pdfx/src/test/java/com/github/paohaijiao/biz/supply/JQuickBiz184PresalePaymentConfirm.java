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
 * 单据 184：预售房款收款单 / 尾款结算确认单（supply 领域第 5 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/supply/184_presale_payment_confirm.txt} 中。</p>
 *
 * <p>视觉主题「橄榄·厚框·典式」：主色 #549632、辅色 #3C2791、浅底 #F3F6F1；
 * 版式为 classic 抬头 + grid2 信息区 + solid 明细表 + right 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>预售房款收款单 / 尾款结算确认单：预售许可证与项目房号、房屋总价与面积，</p>
 * <p>逐笔款项（应收、实收、日期、方式）与结算合计、资金监管账户与三方签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/184_presale_payment_confirm.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz184PresalePaymentConfirm {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "184_presale_payment_confirm.pdf";
    private static final String TEMPLATE = "demo/biz/supply/184_presale_payment_confirm.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "定金", "100000.00", "100000.00", "2026-08-12", "银行转账", "已收"},
                {"2", "首付款（一期）", "460000.00", "460000.00", "2026-08-26", "银行转账", "已收"},
                {"3", "首付款（二期）", "300000.00", "300000.00", "2026-09-18", "POS 刷卡", "已收"},
                {"4", "按揭贷款放款", "1700000.00", "1700000.00", "2026-10-12", "银行放款", "已收"},
                {"5", "尾款 / 面积补差", "300000.00", "300000.00", "2026-10-20", "银行转账", "已收"},
                {"6", "维修基金与税费", "0.00", "0.00", "—", "另行缴纳", "待缴"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("receiptNo", "PRE-20261020-007712");
        data.put("receiveDate", "2026年10月20日");
        data.put("devName", "××房地产开发有限公司");
        data.put("presaleNo", "××房预字第〔2026〕088 号");
        data.put("projectInfo", "××花园 3 期 / 8 幢 1 单元 1802 室");
        data.put("buyerInfo", "崔行知 / 3201××××××××××××××");
        data.put("areaInfo", "128.60 ㎡ / 106.42 ㎡");
        data.put("totalPrice", "￥2,860,000.00");
        data.put("downPayment", "￥860,000.00");
        data.put("loanAmount", "￥1,700,000.00");
        data.put("balanceAmount", "￥300,000.00");
        data.put("superviseAccount", "××银行××支行　3201 8877 6655 0012（预售资金监管专户）");
        data.put("payInfo", "POS 刷卡 + 按揭放款 / REC-20261020-0033");
        data.put("settleResult", "房款已全额结清，具备办理不动产登记条件");
        data.put("remark", "预售资金须全额存入监管账户并专项用于本项目建设；房款结清后由开发企业协助办理不动产权证。");
        data.put("devSign", "××房地产开发有限公司");
        data.put("cashier", "沈亦舟");
        data.put("buyerSign", "崔行知");

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
            itemTotal = itemTotal.add(new BigDecimal(items[i][2]));
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("receivedTotal", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "房款应收合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("settleLine", "已收房款合计 ￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "　　欠款 ￥0.00　　房款已全额结清");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××房地产开发有限公司　预售房款收款 / 尾款结算确认单　PRE-20261020-007712");
        config.getHeaderConfig().setFontSize(8f);
        config.getHeaderConfig().setHeight(28);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("— %d —");

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
