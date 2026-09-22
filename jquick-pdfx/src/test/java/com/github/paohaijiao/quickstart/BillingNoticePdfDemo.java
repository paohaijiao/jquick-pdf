package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BillingNoticePdfDemo {
    public static void main(String[] args) throws Exception {
        String template = ""
                + "<pdf><body>"
                + "<h1 style=\"fontSize:22;fontColor:#1d4ed8\">'客户账单通知单'</h1>"
                + "<p>'尊敬的 '<span style=\"fontColor:#0f766e;bold:true;width:600px\">${customer}</span><span style=\"margin-left:500px;\">'  ，您好：'</span></p>"
                + "<p>'账单周期：'<span style=\"bold:true\">${period}</span></p>"
                + "<p>'本期应付金额：￥'<span style=\"fontColor:#dc2626;fontSize:16;bold:true\">${amount}</span></p>"
                + "<p>'付款状态：'<span style=\"fontColor:#dc2626;bold:true\">${status}</span></p>"
                + "<p>'收款账户：'<tab style=\"width:48px\"></tab>${account}</p>"
                + "<p>'开户银行：'<tab style=\"width:48px\"></tab>${bank}</p>"
                + "<p>'付款说明：请保留付款回单。'<br>'如需发票，请联系客户经理。'</p>"
                + "</body></pdf>";
        System.out.println(template);
        // 绑定账单业务数据
        byte[] bytes = JQuickPdfFactory.create()
                .bind("customer", "杭州星河信息技术有限公司     ")
                .bind("period", "2026 年 9 月")
                .bind("amount", "18,560.00")
                .bind("status", "待付款（已逾期）")
                .bind("account", "北京云数科技有限公司")
                .bind("bank", "招商银行北京中关村支行")
                .executeContent(template);
        // 写入 PDF 文件
        Files.write(Paths.get("d://test//billing-notice.pdf"), bytes);
    }
}