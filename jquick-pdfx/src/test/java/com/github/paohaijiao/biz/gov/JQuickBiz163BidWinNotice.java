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
 * 单据 163：中标通知书 / 投标报名回执（gov 领域第 9 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/163_bid_win_notice.txt} 中。</p>
 *
 * <p>视觉主题「宝蓝·密排·下线」：主色 #183DA9、辅色 #914527、浅底 #F0F2F7；
 * 版式为 underline 抬头 + kv 信息区 + compact 明细表 + three 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>中标通知书 / 投标报名回执：招标项目与编号、招标方式与中标人、</p>
 * <p>各投标人报价与评审得分排名、中标结果与签约提示、三方签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/163_bid_win_notice.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz163BidWinNotice {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "163_bid_win_notice.pdf";
    private static final String TEMPLATE = "demo/biz/gov/163_bid_win_notice.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "××建设工程有限公司", "1860.00", "420", "93.86", "第 1 名 / 中标"},
                {"2", "××建工集团有限公司", "1912.00", "430", "90.12", "第 2 名"},
                {"3", "××建筑安装有限公司", "1948.00", "450", "87.45", "第 3 名"},
                {"4", "××市政工程有限公司", "2016.00", "420", "82.30", "第 4 名"},
                {"5", "××装饰工程有限公司", "2088.00", "480", "76.18", "第 5 名"},
                {"6", "××钢结构有限公司", "2140.00", "400", "71.05", "第 6 名"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("noticeNo", "ZB-20261026-0071");
        data.put("issueDate", "2026年10月26日");
        data.put("tenderee", "××城市投资开发有限公司");
        data.put("agencyName", "××工程咨询有限公司");
        data.put("projectName", "××产业园 3 号厂房施工总承包");
        data.put("bidNo", "GC-2026-0713 / 一标段");
        data.put("bidMethod", "公开招标（资格后审）");
        data.put("winner", "××建设工程有限公司");
        data.put("bidPrice", "￥18,600,000.00");
        data.put("durationInfo", "420 日历天 / 合格，争创优质工程");
        data.put("managerInfo", "路承志 / 一级建造师 苏 1322026××××");
        data.put("signupNo", "SIGN-20260912-0188");
        data.put("resultNote", "经评标委员会评审并公示无异议，确定 ××建设工程有限公司为中标人，中标价人民币壹仟捌佰陆拾万元整。");
        data.put("signNote", "中标人应自收到中标通知书之日起 30 日内，按照招标文件和中标人的投标文件订立书面合同。");
        data.put("tendereeSign", "××城市投资开发有限公司");
        data.put("agencySign", "××工程咨询有限公司");
        data.put("winnerSign", "路承志");

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
        config.getHeaderConfig().setText("××城市投资开发有限公司　中标通知书　ZB-20261026-0071");
        config.getHeaderConfig().setFontSize(8f);
        config.getHeaderConfig().setHeight(30);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("— %d —");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE + "（" + pdf.length + " 字节）");
    }
}
