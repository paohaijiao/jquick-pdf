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
 * 单据 150：企业征信查询报告 / 信用报告（bank 领域第 11 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/150_credit_report.txt} 中。</p>
 *
 * <p>视觉主题「紫棠·斑马·票式」：主色 #52239F、辅色 #918727、浅底 #F3F0F7；
 * 版式为 ticket 抬头 + strip 信息区 + striped 明细表 + three 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>企业征信查询报告 / 信用报告：被查询主体与查询原因、信用等级，</p>
 * <p>逐笔信贷记录（授信金额、余额、还款状态、逾期次数）与公共记录、征信中心签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/150_credit_report.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz150CreditReport {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "150_credit_report.pdf";
    private static final String TEMPLATE = "demo/biz/bank/150_credit_report.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "中国建设银行××支行", "流动资金贷款", "3000000.00", "1300000.00", "正常", "0 次"},
                {"2", "中国银行××分行", "银行承兑汇票", "5000000.00", "1186000.00", "正常", "0 次"},
                {"3", "招商银行××分行", "国内信用证", "2000000.00", "0.00", "已结清", "0 次"},
                {"4", "××融资租赁有限公司", "融资租赁", "1600000.00", "860000.00", "正常", "0 次"},
                {"5", "××小额贷款公司", "信用贷款", "800000.00", "0.00", "已结清", "0 次"},
                {"6", "××银行××分行", "并购贷款", "12000000.00", "9600000.00", "关注", "1 次"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("reportNo", "PBO-20261026-1193");
        data.put("reportDate", "2026年10月26日");
        data.put("orgName", "中国人民银行征信中心 ××分中心");
        data.put("companyName", "××智能装备制造有限公司");
        data.put("creditCode", "91320100MA1X7K38XG");
        data.put("queryReason", "授信审批（贷前调查）");
        data.put("operatorName", "崔行知　机构码 3301××××");
        data.put("creditScore", "BBB+ / 信用良好");
        data.put("validUntil", "自出具之日起 30 日");
        data.put("summary", "无不良信用记录，无被执行信息");
        data.put("remark", "本报告仅供被查询主体授权的机构在授信审批中使用，不得用于其他用途或对外披露。");
        data.put("reviewer", "柳文渊");
        data.put("orgSign", "中国人民银行征信中心");

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
            itemTotal = itemTotal.add(new BigDecimal(items[i][4]));
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("creditTotal", new BigDecimal("22400000.00").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "当前授信余额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        String[][] items2 = {
                {"欠税记录", "无欠税、无税务违法记录", "截至 2026-09-30"},
                {"社保公积金缴纳", "正常缴纳，无欠缴", "截至 2026-09-30"},
                {"司法执行信息", "无被执行人、无失信被执行记录", "截至 2026-10-26"},
                {"近 30 日机构查询", "被查询 6 次，其中贷款审批 3 次", "2026-09-26 ~ 2026-10-26"}
        };
        for (int i = 0; i < 4; i++) {
            int n = i + 1;
            if (i >= items2.length) {
                data.put("subName" + n, "—");
                data.put("subContent" + n, "—");
                data.put("subDate" + n, "—");
                continue;
            }
            data.put("subName" + n, items2[i][0]);
            data.put("subContent" + n, items2[i][1]);
            data.put("subDate" + n, items2[i][2]);
        }

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("中国人民银行征信中心　企业信用报告　PBO-20261026-1193");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(34);
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
