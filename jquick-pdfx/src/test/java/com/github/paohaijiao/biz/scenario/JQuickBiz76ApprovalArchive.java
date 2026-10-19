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

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景 76：审批归档报告 PDF（模板文件 + bind 数据注入版，多页、流程记录跨页带表头、页眉放审批单号）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/76_approval_archive.txt} 中。</p>
 *
 * <p>业务字段：审批单号、申请人、申请时间、审批流程记录（审批人 / 审批节点 / 审批结果 / 审批意见）、
 * 申请内容、附件说明。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/76_approval_archive.pdf}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz76ApprovalArchive {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "76_approval_archive.pdf";
    private static final String TEMPLATE = "report/biz/76_approval_archive.txt";

    public static void main(String[] args) throws Exception {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 组织数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("approvalNo", "OA-2026-09-0247");
        data.put("applicant", "刘洋（数据平台部）");
        data.put("applyTime", "2026年09月02日 09:12");
        data.put("nodeCount", "12 个");

        // 流程记录：逐条 bind 为 recN_node / recN_approver / recN_result / recN_opinion / recN_time
        List<String[]> records = new ArrayList<String[]>();
        records.add(new String[]{"发起申请", "刘洋", "提交", "申请采购 PDF 报表引擎企业授权 5 套", "2026-09-02 09:12"});
        records.add(new String[]{"部门经理审批", "王敏", "通过", "业务量增长确实需要扩容，同意采购", "2026-09-02 10:35"});
        records.add(new String[]{"财务初审", "赵磊", "驳回", "预算科目填写有误，请调整为「软件购置费-信息化」", "2026-09-02 14:20"});
        records.add(new String[]{"申请人修改", "刘洋", "提交", "已按财务意见调整预算科目并补充报价单", "2026-09-03 08:50"});
        records.add(new String[]{"财务初审", "赵磊", "通过", "科目已更正，金额与报价单一致", "2026-09-03 09:40"});
        records.add(new String[]{"法务审核", "陈静", "通过", "软件许可协议无排他条款，允许签署", "2026-09-03 11:05"});
        records.add(new String[]{"IT 架构评审", "孙浩", "通过", "与现有报表平台兼容，无需额外中间件", "2026-09-03 15:30"});
        records.add(new String[]{"分管副总审批", "周建国", "转交", "由总经理审批（金额超 20 万）", "2026-09-04 09:00"});
        records.add(new String[]{"总经理审批", "李国华", "通过", "同意采购，要求 30 日内完成部署", "2026-09-04 16:45"});
        records.add(new String[]{"合同签署", "王敏", "完成", "合同 HT-2026-0918 已用印", "2026-09-08 10:00"});
        records.add(new String[]{"付款执行", "财务部", "完成", "首付款 30% 已支付", "2026-09-12 14:00"});
        records.add(new String[]{"到货验收", "刘洋", "完成", "5 套授权已激活，验收单 YS-2026-0921", "2026-09-21 11:00"});
        String[] keys = {"node", "approver", "result", "opinion", "time"};
        for (int i = 0; i < records.size(); i++) {
            for (int j = 0; j < keys.length; j++) {
                data.put("rec" + (i + 1) + "_" + keys[j], records.get(i)[j]);
            }
        }

        // 申请内容：三段
        data.put("applyPara1", "因集团报表业务量增长，现有 3 套 PDF 报表引擎授权已无法满足日均 12 万份报表的生成需求，高峰期排队时间超过 20 分钟，影响业务部门早会数据获取。");
        data.put("applyPara2", "本次申请采购企业级授权 5 套，供应商为 ××数字科技有限公司，单价 42,000 元，总价 210,000 元，含一年技术支持与两次现场培训。采购方式为单一来源采购，理由为现有系统基于该引擎开发，更换供应商需重构 300 余个报表模板，成本远高于本次采购。");
        data.put("applyPara3", "预计扩容后日均处理能力提升至 30 万份，高峰排队时间可控制在 2 分钟以内。");

        // 附件清单
        List<String> attachments = Arrays.asList(
                "《PDF 报表引擎采购申请单》",
                "供应商正式报价单（BJ-2026-0912-0031）",
                "单一来源采购论证意见",
                "IT 架构评审记录",
                "合同 HT-2026-0918 扫描件（已用印）",
                "验收单 YS-2026-0921");
        for (int i = 0; i < attachments.size(); i++) {
            data.put("att" + (i + 1), attachments.get(i));
        }

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        // 顶部留出 34pt 给页眉
        config.getDoc().setMargins(Arrays.asList(58, 50, 55, 50));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("审批单号：" + data.get("approvalNo") + "　|　OA 审批归档报告");
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
}
