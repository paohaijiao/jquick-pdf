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
 * 单据 152：反洗钱交易筛查清单 / 可疑交易报告单（bank 领域第 13 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/152_aml_screening_list.txt} 中。</p>
 *
 * <p>视觉主题「紫棠·疏朗·公文」：主色 #511699、辅色 #919027、浅底 #F3F0F7；
 * 版式为 doc 抬头 + grid2 信息区 + airy 明细表 + card 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>反洗钱交易筛查清单 / 可疑交易报告单：筛查批次与规则集、筛查范围，</p>
 * <p>逐笔可疑交易（行为特征、金额、命中规则、处置）、合计与合规负责人签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/152_aml_screening_list.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz152AmlScreeningList {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "152_aml_screening_list.pdf";
    private static final String TEMPLATE = "demo/biz/bank/152_aml_screening_list.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "××贸易有限公司", "短期内资金分散转入集中转出", "4860000.00", "规则 R-0302", "已上报"},
                {"2", "××网络科技有限公司", "与高风险地区频繁小额往来", "1286000.00", "规则 R-0511", "已上报"},
                {"3", "××物资供应有限公司", "资金快进快出、不留余额", "2340000.00", "规则 R-0408", "持续监测"},
                {"4", "××进出口有限公司", "大额整数交易与经营范围不符", "980000.00", "规则 R-0215", "排除"},
                {"5", "××咨询有限公司", "多账户归集后集中转出", "760000.00", "规则 R-0302", "排除"},
                {"6", "××物流有限公司", "正常运费结算，背景清晰", "318539.05", "规则 R-0101", "排除"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("batchNo", "AML-20261028-0071");
        data.put("screenDate", "2026年10月28日");
        data.put("orgName", "××银行股份有限公司××分行 合规部");
        data.put("ruleSet", "大额交易 + 可疑交易模型 V3.2");
        data.put("scope", "2026年10月01日 — 2026年10月27日 全量对公交易");
        data.put("hitCount", "6");
        data.put("reportCount", "2");
        data.put("excludeCount", "4");
        data.put("riskLevel", "高风险 1 笔 / 中风险 2 笔 / 低风险 3 笔");
        data.put("analyst", "沈亦舟");
        data.put("handleNote", "对已上报的 2 笔交易已按《金融机构大额交易和可疑交易报告管理办法》向中国反洗钱监测分析中心报送；其余交易纳入持续监测名单。");
        data.put("remark", "本清单涉及客户身份资料与交易信息，属反洗钱保密资料，严禁向客户或与履职无关人员泄露。");
        data.put("complianceOfficer", "闻仲平");
        data.put("orgSign", "××银行××分行");

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
            itemTotal = itemTotal.add(new BigDecimal(items[i][3]));
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("totalLine", "命中交易金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××银行××分行　反洗钱可疑交易筛查清单　AML-20261028-0071");
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
