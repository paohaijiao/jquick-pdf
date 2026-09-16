package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ComparisonDemo {
    public static void main(String[] args) throws Exception {
        String template = ""
                + "<pdf><body>"
                + "<h1 style=\"fontSize:22;textAlignment:center\">'月度对账单'</h1>"
                + "<p>'客户：'${customer}</p>"
                + "<p>'周期：'${period}</p>"
                + "<table style=\"width:100%\">"
                + "<tr><th>'项目'</th><th>'金额'</th><th>'状态'</th></tr>"
                + "<tr><td>'技术服务费'</td><td>${amount}</td><td>'已确认'</td></tr>"
                + "</table>"
                + "<p style=\"fontColor:#666666\">'本单由系统自动生成，请以财务确认结果为准。'</p>"
                + "</body></pdf>";
        // 业务代码只负责准备动态数据
        byte[] pdf = JQuickPdfFactory.create()
                .bind("customer", "示例科技有限公司")
                .bind("period", "2026 年 9 月")
                .bind("amount", "18,560.00")
                .executeContent(template);
        // 将 PDF 输出到文件
        Files.write(Paths.get("d://test//statement.pdf"), pdf);
    }
}
