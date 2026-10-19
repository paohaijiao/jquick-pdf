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
 * 单据 159：不动产登记受理单 / 不动产权证回执（gov 领域第 5 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/159_real_estate_registration_receipt.txt} 中。</p>
 *
 * <p>视觉主题「天青·厚框·通栏」：主色 #237172、辅色 #912756、浅底 #F1F6F6；
 * 版式为 banner 抬头 + kv 信息区 + solid 明细表 + right 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>不动产登记受理单 / 不动产权证回执：登记类型与不动产单元号、坐落与面积用途，</p>
 * <p>申请材料清单与核验结果、登记结果与登记机构签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/159_real_estate_registration_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz159RealEstateRegistrationReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "159_real_estate_registration_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/gov/159_real_estate_registration_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "不动产登记申请表", "原件", "1", "齐全", "—"},
                {"2", "申请人身份证明 / 营业执照", "复印件盖章", "1", "齐全", "—"},
                {"3", "不动产权属证书（原证）", "原件", "1", "齐全", "—"},
                {"4", "买卖合同 / 成交确认书", "原件", "2", "齐全", "—"},
                {"5", "完税凭证（契税、印花税）", "原件", "1", "齐全", "—"},
                {"6", "测绘报告及附图", "原件", "1", "齐全", "—"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("acceptNo", "BD-20261022-0093");
        data.put("acceptDate", "2026年10月22日");
        data.put("orgName", "××市自然资源和规划局不动产登记中心");
        data.put("registerType", "国有建设用地使用权及房屋所有权转移登记");
        data.put("applicant", "××智能装备制造有限公司");
        data.put("creditCode", "91320100MA1X7K38XG");
        data.put("location", "××市××区智造大道 168 号 A 座 1-6 层");
        data.put("unitNo", "320100 001008 GB00012 F00010001");
        data.put("certNo", "苏（2026）××市不动产权第 0088123 号");
        data.put("areaInfo", "12860.55 ㎡ / 工业用房");
        data.put("feeInfo", "登记费 550.00 元，印花税 2580.00 元");
        data.put("certInfo", "2026年10月29日 / 窗口领证");
        data.put("result", "经审核，申请登记的不动产权利归属清楚、材料齐全，予以登记，不动产权证将于 2026 年 10 月 29 日核发。");
        data.put("remark", "不动产登记自受理之日起 30 个工作日内办结；申请人可凭受理编号在不动产登记大厅或网上查询进度。");
        data.put("acceptor", "苏见宁");
        data.put("reviewer", "柳文渊");
        data.put("applicantSign", "崔行知");

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
        config.getHeaderConfig().setText("××市不动产登记中心　不动产登记受理单　BD-20261022-0093");
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
}
