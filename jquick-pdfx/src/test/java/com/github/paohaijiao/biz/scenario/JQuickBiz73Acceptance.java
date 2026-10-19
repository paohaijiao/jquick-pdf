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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 73：项目验收单 PDF（多页、大段描述、甲乙双方签名，模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件 {@code report/biz/73_acceptance.txt} 中。
 * 验收项逐条 bind 为 chkNameN / chkStdN / chkResultN / chkRemarkN；
 * 验收结论按段 bind 为 conclusion1~4。</p>
 *
 * <p>产物目录约定：</p>
 * <ul>
 *   <li>PDF：{@code D:/test/pdf/73_acceptance.pdf}</li>
 *   <li>签名：{@code D:/test/image/73_sign_a.png}、{@code D:/test/image/73_sign_b.png}</li>
 * </ul>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz73Acceptance {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "73_acceptance.pdf";
    private static final String TEMPLATE = "report/biz/73_acceptance.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 图片：甲乙双方签名 ----------
        generateSignature(IMG_DIR + "73_sign_a.png", "李明");
        generateSignature(IMG_DIR + "73_sign_b.png", "张国强");

        // ---------- 2. 数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("projectName", "××市智慧交通数据中台建设项目");
        data.put("contractNo", "HT-2026-0219");
        data.put("acceptanceDate", "2026年10月15日 - 2026年10月18日");
        data.put("partyAName", "××市交通运输局");
        data.put("partyAContact", "王海涛");
        data.put("partyAPhone", "0571-8888-1234");
        data.put("partyAAddress", "××市××区解放路 128 号");
        data.put("partyBName", "××数字科技有限公司");
        data.put("partyBContact", "陈立");
        data.put("partyBPhone", "0571-6666-5678");
        data.put("partyBAddress", "××市××区科技园 8 号楼");

        // 验收项：8 条
        String[][] items = {
                {"功能完整性", "合同附件一《功能清单》全部 86 项功能点可正常使用，无缺项", "合格", "无"},
                {"性能指标", "单节点并发 200 时，报表生成平均响应 ≤ 1.5s，P99 ≤ 3s", "合格", "实测 P99 2.3s"},
                {"数据准确性", "抽检 30 份报表，与源系统比对金额误差为 0", "合格", "无"},
                {"中文渲染", "全部模板中文、特殊符号（※、￥、℃，）显示正常，无方框乱码", "合格", "无"},
                {"图表渲染", "12 类 SVG 图表在 PDF 中矢量输出，缩放不失真", "合格", "无"},
                {"长表格分页", "明细超过 500 行时分页正确，表头每页重复", "合格", "实测 1200 行"},
                {"安全合规", "无高危漏洞，权限校验覆盖全部接口", "合格", "第三方扫描通过"},
                {"文档交付", "部署手册、运维手册、接口文档齐全并完成交付培训", "合格", "培训 2 场"}
        };
        for (int i = 0; i < items.length; i++) {
            String suffix = String.valueOf(i + 1);
            data.put("chkName" + suffix, items[i][0]);
            data.put("chkStd" + suffix, items[i][1]);
            data.put("chkResult" + suffix, items[i][2]);
            data.put("chkRemark" + suffix, items[i][3]);
        }

        // 验收结论：4 段
        data.put("conclusion1", "甲乙双方于 2026 年 10 月 15 日至 10 月 18 日对本项目进行了为期四天的联合验收。"
                + "验收组依据《项目建设合同》（合同编号 HT-2026-0219）及附件一《功能清单》、附件二《技术指标要求》，"
                + "采用功能逐项验证、性能压测、数据比对、文档审查四种方式开展验收工作。");
        data.put("conclusion2", "经核验，系统全部 86 项功能点均已实现并可正常使用；在 200 并发压力下，报表生成平均响应时间 1.2 秒，"
                + "P99 响应时间 2.3 秒，满足合同约定的性能指标；抽检 30 份报表与源系统数据比对，金额与数量误差均为零；"
                + "中文字体、特殊符号、SVG 图表在 PDF 输出中渲染正常，长表格分页与表头重复符合使用要求。");
        data.put("conclusion3", "验收过程中发现的问题共 3 项，其中 2 项已在验收期内完成整改并复验通过，剩余 1 项（报表导出文件名规则）"
                + "为一般性问题，双方约定于 2026 年 11 月 10 日前完成优化，不影响系统正式上线。");
        data.put("conclusion4", "综上，验收组一致认为本项目已达到合同约定的建设目标与质量标准，同意通过验收，自本单签署之日起进入质保期，"
                + "质保期为 12 个月。");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(50, 50, 55, 50));
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

    /**
     * 生成手写签名占位图：透明底 + 楷体字 + 轻微旋转，模拟真实签字。
     */
    private static void generateSignature(String path, String name) throws IOException {
        BufferedImage image = new BufferedImage(220, 108, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(-4), 110, 54);
        g.setColor(new Color(0x14, 0x1E, 0x28));
        g.setFont(new Font("KaiTi", Font.PLAIN, 52));
        g.drawString(name, 34, 74);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
