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
 * 单据 164：行政处罚告知书 / 罚款缴款回执（gov 领域第 10 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/164_penalty_notice_receipt.txt} 中。</p>
 *
 * <p>视觉主题「群青·厚框·公文」：主色 #1E1C73、辅色 #916127、浅底 #F1F0F7；
 * 版式为 doc 抬头 + grid2 信息区 + solid 明细表 + card 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>行政处罚告知书 / 罚款缴款回执：当事人与违法行为、违反条款与处罚依据，</p>
 * <p>逐项违法事实与裁量情节、拟处罚内容与救济途径、执法人员与当事人签收栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/164_penalty_notice_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz164PenaltyNoticeReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "164_penalty_notice_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/gov/164_penalty_notice_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "自动监测设备未安装", "现场检查（勘验）笔录、调查询问笔录", "首次违法", "2 万 — 20 万", "罚款 6 万"},
                {"2", "监测数据未联网上传", "在线监控平台数据截图", "逾期未改正", "2 万 — 20 万", "罚款 6 万"},
                {"3", "台账记录不完整", "台账抽查记录", "一般情节", "责令改正", "责令改正"},
                {"4", "积极配合调查整改", "整改报告及照片", "从轻情节", "下浮 20%", "从轻"},
                {"5", "主动公开环境信息", "公开信息截图", "从轻情节", "下浮 10%", "从轻"},
                {"6", "合并执行", "案件集体讨论记录", "综合裁量", "—", "罚款 20 万"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("noticeNo", "××环罚告〔2026〕88 号");
        data.put("noticeDate", "2026年10月27日");
        data.put("orgName", "××市生态环境局");
        data.put("partyName", "××环保科技有限公司");
        data.put("creditCode", "91320100MA1X7K38XG");
        data.put("violation", "未按规定安装使用污染物排放自动监测设备");
        data.put("violateClause", "《中华人民共和国大气污染防治法》第二十四条第一款");
        data.put("penaltyBasis", "《大气污染防治法》第一百条第（三）项");
        data.put("penaltyContent", "罚款人民币贰拾万元整，责令改正违法行为");
        data.put("penaltyAmount", "￥200,000.00");
        data.put("defenseDeadline", "自收到本告知书之日起 5 个工作日内");
        data.put("payInfo", "收到决定书之日起 15 日内 / ××市非税收入专户");
        data.put("remedyNote", "当事人有权在收到本告知书之日起 5 个工作日内提出陈述、申辩；符合听证条件的，可在 5 个工作日内书面提出听证申请。");
        data.put("remark", "逾期不缴纳罚款的，每日按罚款数额的 3% 加处罚款；拒不履行的，依法申请人民法院强制执行。");
        data.put("officer", "闻仲平 / 路承志");
        data.put("officerNo", "3301×××× / 3301××××");
        data.put("partySign", "崔行知");

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
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市生态环境局　行政处罚告知书　××环罚告〔2026〕88 号");
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
}
