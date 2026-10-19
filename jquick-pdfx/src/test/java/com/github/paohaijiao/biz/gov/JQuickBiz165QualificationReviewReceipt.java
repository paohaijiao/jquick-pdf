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
 * 单据 165：企业资质申报 / 资质审核回执（gov 领域第 11 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/165_qualification_review_receipt.txt} 中。</p>
 *
 * <p>视觉主题「群青·斑马·通栏」：主色 #322F80、辅色 #916327、浅底 #F1F1F6；
 * 版式为 banner 抬头 + chip 信息区 + striped 明细表 + split 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>企业资质申报 / 审核回执：资质类别与等级、现有与申报等级，</p>
 * <p>逐项考核指标（标准要求与实际情况）、审核结论与审核机关签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/165_qualification_review_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz165QualificationReviewReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "165_qualification_review_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/gov/165_qualification_review_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "净资产", "4000 万元以上", "5860.42 万元（审计报告）", "符合", "—"},
                {"2", "注册建造师数量", "建筑工程专业不少于 12 人", "12 人（一级 5 人、二级 7 人）", "符合", "—"},
                {"3", "技术负责人资历", "8 年以上从业经历", "路承志，从业 15 年，高工", "符合", "—"},
                {"4", "中级以上职称人员", "不少于 15 人", "18 人，专业配套齐全", "符合", "—"},
                {"5", "工程业绩", "近 5 年承担过 2 类工程", "已提供 3 项业绩证明", "符合", "—"},
                {"6", "技术装备与管理体系", "具备相应施工机械与质量体系", "设备齐全，通过 ISO 9001", "符合", "—"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("applyNo", "QUA-20261028-0088");
        data.put("reviewDate", "2026年10月28日");
        data.put("orgName", "××市住房和城乡建设局");
        data.put("qualType", "建筑工程施工总承包二级资质（增项）");
        data.put("companyName", "××建设工程有限公司");
        data.put("creditCode", "91320100MA1Z3Q71KJ");
        data.put("currentQual", "建筑工程施工总承包三级");
        data.put("applyQual", "建筑工程施工总承包二级");
        data.put("acceptInfo", "GS-20261015-03199 / 2026-10-15");
        data.put("reviewResult", "符合条件，予以核准并公告");
        data.put("certNo", "D2320××××××");
        data.put("validUntil", "至 2031年10月27日");
        data.put("remark", "资质核准结果在机关门户网站公告 5 个工作日；资质证书有效期内须接受动态核查，条件不达标的责令限期整改。");
        data.put("reviewer", "柳文渊");
        data.put("rechecker", "闻仲平");
        data.put("companySign", "崔行知");

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
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市住房和城乡建设局　企业资质审核回执　QUA-20261028-0088");
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
}
