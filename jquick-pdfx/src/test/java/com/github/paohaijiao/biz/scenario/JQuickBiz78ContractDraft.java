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
package com.github.paohaijiao.biz.scenario;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景 78：合同协议初稿 PDF（模板文件 + bind 数据注入版，长文档自动分页、条款分段、末页双方公章位）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/78_contract_draft.txt} 中。</p>
 *
 * <p>条款正文按 \n 分段，逐段 bind 为 clauseN_p1~p3（模板为每条预留 3 个段落占位符，
 * 不足的段落绑空串）。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/78_contract_draft.pdf}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz78ContractDraft {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "78_contract_draft.pdf";
    private static final String TEMPLATE = "report/biz/78_contract_draft.txt";

    public static void main(String[] args) throws Exception {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成双方印章图片（模板 image src 按字面量引用固定路径） ----------
        String sealA = IMG_DIR + "78_seal_a.png";
        String sealB = IMG_DIR + "78_seal_b.png";
        generateSeal(sealA, "甲方单位", "合同专用章");
        generateSeal(sealB, "乙方单位", "合同专用章");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("contractNo", "HT-2026-0918");
        data.put("title", "P D F 报 表 引 擎 采 购 合 同");
        data.put("partyALine1", "××城市商业银行股份有限公司　　法定代表人：周敏");
        data.put("partyALine2", "地址：××市浦东新区××路 88 号　　联系人：李伟");
        data.put("partyBLine1", "××数字科技有限公司　　法定代表人：陈立");
        data.put("partyBLine2", "地址：××市××区科技园 8 号楼　　联系人：王芳");
        data.put("partyAName", "××城市商业银行股份有限公司");
        data.put("partyBName", "××数字科技有限公司");

        // 条款：逐条 bind 为 clauseN_title / clauseN_p1~p3
        List<String[]> clauses = new ArrayList<String[]>();
        clauses.add(new String[]{"项目内容",
                "乙方向甲方提供 PDF 报表引擎企业版软件许可 5 套，并完成报表模板迁移、系统部署、人员培训等相关服务，具体交付内容以附件一《功能清单》与附件二《实施计划》为准。"});
        clauses.add(new String[]{"合同金额与支付方式",
                "本合同总金额为人民币 210,000 元（大写：贰拾壹万元整），含 13% 增值税专用发票。\n"
                        + "付款方式：合同签订之日起 5 个工作日内支付 30%（63,000 元）；系统上线验收合格后 10 个工作日内支付 60%（126,000 元）；\n"
                        + "质保期满且无重大质量问题后 10 个工作日内支付余款 10%（21,000 元）。"});
        clauses.add(new String[]{"交付与验收",
                "乙方应于合同生效之日起 30 个日历日内完成部署与模板迁移，并提交验收申请。\n"
                        + "甲方应在收到验收申请之日起 10 个工作日内组织验收；逾期未组织验收且未提出书面异议的，视为验收合格。\n"
                        + "验收标准以附件二《实施计划》约定的功能、性能指标为准。"});
        clauses.add(new String[]{"知识产权",
                "乙方保证所交付软件不侵犯任何第三方知识产权。甲方在授权范围内享有软件使用权。\n"
                        + "因甲方业务需要定制的报表模板，其著作权归甲方所有，乙方保留在非竞争领域复用的权利。"});
        clauses.add(new String[]{"保密条款",
                "双方对在履行本合同过程中知悉的对方商业秘密、技术资料、客户数据负有保密义务，保密期限自合同签订之日起 5 年。\n"
                        + "未经对方书面同意，任何一方不得向第三方披露或用于本合同以外的目的。"});
        clauses.add(new String[]{"质保与技术支持",
                "乙方提供自验收合格之日起 12 个月的免费质保服务，包括缺陷修复、版本升级与远程技术支持。\n"
                        + "质保期内工作日 2 小时内响应，重大故障 24 小时内提供解决方案。"});
        clauses.add(new String[]{"违约责任",
                "乙方逾期交付的，每逾期一日按合同总金额的 0.5‰ 向甲方支付违约金，累计不超过合同总金额的 5%。\n"
                        + "甲方逾期付款的，每逾期一日按未付金额的 0.5‰ 向乙方支付违约金。"});
        clauses.add(new String[]{"争议解决",
                "因本合同引起的争议，双方应友好协商解决；协商不成的，任何一方均可向合同签订地有管辖权的人民法院提起诉讼。"});
        clauses.add(new String[]{"其他约定",
                "本合同未尽事宜，双方可另行签订补充协议，补充协议与本合同具有同等法律效力。\n"
                        + "本合同附件为合同不可分割的组成部分，与正文具有同等效力。"});

        String[] cnNums = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
        for (int i = 0; i < clauses.size(); i++) {
            String[] parts = clauses.get(i)[1].split("\n");
            data.put("clause" + (i + 1) + "Title", clauses.get(i)[0]);
            for (int p = 0; p < 3; p++) {
                data.put("clause" + (i + 1) + "P" + (p + 1), p < parts.length ? parts[p] : "");
            }
        }

        data.put("totalAmountLine", "人民币 ￥210000.00 元（含税）");
        data.put("validPeriod", "2026年09月18日 至 2029年09月17日（三年）");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(58, 62, 55, 62));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("合同编号：" + data.get("contractNo"));
        config.getHeaderConfig().setFontSize(8);
        config.getHeaderConfig().setHeight(34);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);

        FileOutputStream out = new FileOutputStream(OUT_FILE);
        out.write(pdf);
        out.close();
        System.out.println("已生成：" + OUT_FILE);
    }

    /** 生成合同专用章（双行文字 + 年份），与 93 样板同一套绘图逻辑。 */
    private static void generateSeal(String path, String top, String bottom) throws IOException {
        int w = 220;
        int h = 220;
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(10, 10, w - 20, h - 20);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        g.drawString(top, w / 2 - 36, 66);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        g.drawString(bottom, w / 2 - 40, 150);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
        g.drawString("(2026)", w / 2 - 20, 175);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
