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
package com.github.paohaijiao.biz.customs;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 137：货物查验通知单 / 查验结果单（customs 领域第 13 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/137_goods_inspection_notice.txt} 中。</p>
 *
 * <p>视觉主题「群青·疏朗·竖栏」：主色 #2D3194、辅色 #915C27、浅底 #F1F1F6；
 * 版式为 side 抬头 + chip 信息区 + airy 明细表 + two 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>货物查验通知单 / 查验结果单：通知单号与查验地点、查验类型与到场要求，</p>
 * <p>逐项查验项目与判定处置、查验结论与双方签收栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/137_goods_inspection_notice.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz137GoodsInspectionNotice {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "137_goods_inspection_notice.pdf";
    private static final String TEMPLATE = "demo/biz/customs/137_goods_inspection_notice.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "品名规格查验", "核对申报品名与实物一致性", "一致", "正常", "放行"},
                {"2", "数量重量查验", "过磅 / 开箱清点", "46 件 / 12140 kg", "正常", "放行"},
                {"3", "原产地查验", "核对原产地证书与实物标识", "一致", "正常", "放行"},
                {"4", "价格质疑核查", "核对成交价格申报合理性", "申报价合理", "正常", "放行"},
                {"5", "归类质疑核查", "核对 HS 编码归类", "归类正确", "正常", "放行"},
                {"6", "木质包装查验", "IPPC 标识与除害处理", "标识有效", "正常", "放行"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("noticeNo", "INS-20261015-0077");
        data.put("noticeDate", "2026年10月15日");
        data.put("checkPlace", "××海关××查验中心 5 号台");
        data.put("declarationNo", "220120261012003388");
        data.put("conclusion", "单货相符，予以放行");
        data.put("consignee", "××国际贸易有限公司");
        data.put("checkType", "随机抽查 / 指定查验");
        data.put("arriveTime", "2026年10月16日 09:30");
        data.put("contactInfo", "崔行知 / 021-66××××××");
        data.put("officer", "闻仲平　执法证号 3301××××");
        data.put("feedbackDate", "2026年10月18日");
        data.put("customsOffice", "××海关");
        data.put("remark", "收发货人或其代理人须按通知时间到场协助查验；逾期不到场，海关可径行查验。");
        data.put("consigneeSign", "崔行知");
        data.put("inputer", "苏见宁");

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
        config.getHeaderConfig().setText("××海关　货物查验通知单　INS-20261015-0077");
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
}
