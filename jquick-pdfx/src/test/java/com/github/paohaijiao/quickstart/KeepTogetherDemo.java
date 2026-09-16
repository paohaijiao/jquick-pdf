package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KeepTogetherDemo {
    public static void main(String[] args) throws Exception {
        String template = "<pdf><body>"
                + "<h1>'订单确认单'</h1>"
                // 用正文把光标推近分页边界，便于观察分页行为
                + "<p>'以下是订单处理说明，正文用于把光标推近分页边界。'</p>"
                + "<p>'系统将在审核完成后通知客户。'</p>"
                // 整块设置 keepTogether，避免摘要被拆开
                + "<div style=\"keepTogether:true;border:solid 1px #333;padding:12px\">"
                + "<h2>'订单摘要'</h2>"
                + "<p>'订单号：'${orderNo}</p>"
                + "<p>'客户：'${customer}</p>"
                + "<p>'含税金额：'${amount}</p>"
                + "<p>'处理状态：'${status}</p>"
                + "</div>"
                + "<p>'摘要之后的说明从下一个可用位置继续。'</p>"
                + "</body></pdf>";
        // 绑定订单数据并渲染
        byte[] pdf = JQuickPdfFactory.create()
                .bind("orderNo", "SO-20260914-001")
                .bind("customer", "示例客户")
                .bind("amount", "12,800.00 元")
                .bind("status", "待审核")
                .executeContent(template);
        // 保存 PDF 结果
        Files.write(Paths.get("d://test//keep-together.pdf"), pdf);
    }
}
