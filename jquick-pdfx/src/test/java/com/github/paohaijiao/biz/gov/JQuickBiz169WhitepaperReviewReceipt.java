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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 169：商业白皮书申报 / 专家评审回执（gov 领域第 15 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/169_whitepaper_review_receipt.txt} 中。</p>
 *
 * <p>视觉主题「绛紫·厚框·下线」：主色 #8E24B8、辅色 #769127、浅底 #F5F0F7；
 * 版式为 underline 抬头 + chip 信息区 + solid 明细表 + split 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>商业白皮书申报 / 专家评审回执：白皮书名称与申报类别、评审方式与专家组，</p>
 * <p>逐项评审指标（要点、分值、得分、意见）与评审结论、组织单位签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/169_whitepaper_review_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz169WhitepaperReviewReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "169_whitepaper_review_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/gov/169_whitepaper_review_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "选题价值与意义", "是否紧扣产业发展痛点与决策需求", "15", "13.5", "选题价值突出"},
                {"2", "数据支撑与权威性", "数据来源可靠、样本充分、口径清晰", "20", "17.0", "建议补充微观数据"},
                {"3", "分析框架与方法", "逻辑严密、方法科学、论证充分", "20", "17.6", "框架完整"},
                {"4", "对策建议可操作性", "建议具体、可执行、有落地路径", "20", "17.2", "建议细化分工"},
                {"5", "文字表达与规范", "表述严谨、格式规范、图表清晰", "15", "13.1", "图表需优化"},
                {"6", "创新性与前瞻性", "观点新颖、判断前瞻、有独立见解", "10", "8.0", "前瞻性较好"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("applyNo", "WP-20261101-0022");
        data.put("reviewDate", "2026年11月01日");
        data.put("orgName", "××市工业和信息化局 ××市商务局");
        data.put("paperName", "《××市智能制造产业发展白皮书（2026）》");
        data.put("companyName", "××产业研究院有限公司");
        data.put("creditCode", "91320100MA1Y2P94QD");
        data.put("applyType", "行业研究报告类 / 公开发布");
        data.put("leaderName", "闻仲平（首席研究员）");
        data.put("reviewWay", "专家会议评审（7 人专家组）");
        data.put("reviewResult", "通过评审，建议按专家意见修改后发布");
        data.put("publishWay", "政府门户网 + 行业协会联合发布");
        data.put("reviewScore", "86.4 分（良好）");
        data.put("remark", "申报单位应在收到评审意见后 15 个工作日内提交修改稿；修改稿经复核通过后，方可按计划公开发布。");
        data.put("expertLeader", "闻仲平");
        data.put("orgSign", "××市工业和信息化局");
        data.put("companySign", "崔行知");

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
            itemTotal = itemTotal.add(new BigDecimal(items[i][4]));
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("fullScore", new BigDecimal("100").setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "评审得分合计：" + itemTotal.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + " 分（满分 100 分），评价等次：良好");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市工业和信息化局　白皮书专家评审回执　WP-20261101-0022");
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
