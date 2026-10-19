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
 * 单据 167：公文流转签收单 / 办件办结回执（gov 领域第 13 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/167_document_flow_receipt.txt} 中。</p>
 *
 * <p>视觉主题「紫棠·疏朗·竖栏」：主色 #4E227A、辅色 #8D9127、浅底 #F4F1F6；
 * 版式为 side 抬头 + kv 信息区 + airy 明细表 + three 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>公文流转签收单 / 办件办结回执：公文编号标题与种类、承办处室，</p>
 * <p>逐环节流转签收记录（签收人、时间、办理意见）、办结结论与归档信息。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/167_document_flow_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz167DocumentFlowReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "167_document_flow_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/gov/167_document_flow_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "拟稿与核稿", "市政府办公室综合一处 / 崔行知", "2026-10-18 09:12", "同意，报领导审签", "已完成"},
                {"2", "领导审签", "市政府办公室 / 闻仲平", "2026-10-19 15:30", "同意印发", "已完成"},
                {"3", "会签", "市工信局 / 柳文渊", "2026-10-19 17:02", "无意见，会签同意", "已完成"},
                {"4", "编号与用印", "办公室文秘科 / 苏见宁", "2026-10-20 10:25", "已编号用印", "已完成"},
                {"5", "分发与签收", "各区县人民政府（12 家）", "2026-10-22 16:40", "已签收", "已完成"},
                {"6", "归档", "办公室档案科 / 邵怀信", "2026-10-30 11:05", "已归入 DA-2026-10-118", "已完成"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("docNo", "××办发〔2026〕118 号");
        data.put("docTitle", "关于推进智能制造产业高质量发展的实施意见");
        data.put("orgName", "××市人民政府办公室");
        data.put("docType", "下行文（意见）/ 公开发布");
        data.put("issueInfo", "2026年10月20日 / 2026年10月22日");
        data.put("caseNo", "CASE-20261030-0166");
        data.put("undertakerInfo", "综合一处 / 崔行知");
        data.put("flowScope", "各区县人民政府、市政府各部门、各直属机构");
        data.put("finishResult", "已办结，文件已印发并归档");
        data.put("archiveNo", "DA-2026-10-118");
        data.put("urgency", "平件（常规办理）");
        data.put("finishDate", "2026年10月30日");
        data.put("remark", "公文流转各环节的签收时间以电子政务平台时间戳为准；涉密公文须按保密规定另行登记流转。");
        data.put("undertaker", "崔行知");
        data.put("deptHead", "闻仲平");
        data.put("archivist", "邵怀信");

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
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市人民政府办公室　公文流转签收单　××办发〔2026〕118 号");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(34);
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
