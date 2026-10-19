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
 * 单据 153：保险保单 / 缴费回执 / 理赔单据（bank 领域第 14 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/153_insurance_policy_claim.txt} 中。</p>
 *
 * <p>视觉主题「绛紫·密排·通栏」：主色 #8329A6、辅色 #769127、浅底 #F5F0F7；
 * 版式为 banner 抬头 + chip 信息区 + compact 明细表 + split 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>保险保单 / 缴费回执 / 理赔单据：保单号与险种、投保人与被保险人、</p>
 * <p>逐项承保标的（保额、保费、损失、赔付）与理赔结论、承保公司签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/153_insurance_policy_claim.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz153InsurancePolicyClaim {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "153_insurance_policy_claim.pdf";
    private static final String TEMPLATE = "demo/biz/bank/153_insurance_policy_claim.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "厂房建筑物（3 号车间）", "8600000.00", "15600.00", "0.00", "0.00", "正常承保"},
                {"2", "机器设备（数控加工中心等）", "12800000.00", "23200.00", "186000.00", "168000.00", "已赔付"},
                {"3", "存货（原材料及成品）", "4200000.00", "7600.00", "0.00", "0.00", "正常承保"},
                {"4", "办公设备及家具", "800000.00", "1450.00", "0.00", "0.00", "正常承保"},
                {"5", "机器损坏险附加条款", "400000.00", "750.00", "0.00", "0.00", "正常承保"},
                {"6", "免赔额 / 免赔率", "—", "0.00", "18000.00", "0.00", "已扣减"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("policyNo", "POL-2026102900881234");
        data.put("issueDate", "2026年10月29日");
        data.put("insurerName", "××财产保险股份有限公司××分公司");
        data.put("policyHolder", "××智能装备制造有限公司");
        data.put("insured", "××智能装备制造有限公司");
        data.put("productName", "财产一切险 + 机器损坏险");
        data.put("insurePeriod", "2026年11月01日 00:00 至 2027年10月31日 24:00");
        data.put("insuredAmount", "￥26,800,000.00");
        data.put("premium", "￥48,600.00");
        data.put("payStatus", "转账 / 已缴费");
        data.put("claimNo", "CLM-20261112-0033");
        data.put("claimResult", "属于保险责任，予以赔付");
        data.put("remark", "本保单缴费回执为保费到账凭证；理赔须在出险后 48 小时内报案，并提供损失清单与事故证明。");
        data.put("policyHolderSign", "××智能装备制造有限公司");
        data.put("operator", "邵怀信");
        data.put("insurerSign", "××财产保险××分公司");

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

        data.put("premiumTotal", new BigDecimal("48600.00").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "赔付金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××财产保险××分公司　保险保单 / 理赔单　POL-2026102900881234");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(28);
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
