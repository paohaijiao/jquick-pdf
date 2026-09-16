package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExportServiceDemo {
    public static byte[] export(String department, String month) throws Exception {
        String template = "<pdf><body>"
                + "<h1 style=\"textAlignment:center;fontSize:22\">'月度汇总'</h1>"
                + "<p>'部门：'${department}</p>"
                + "<p>'月份：'${month}</p>"
                + "<table style=\"width:520px;border:solid 1px #999\">"
                + "<tr><th>'指标'</th><th>'数值'</th></tr>"
                + "<tr><td>'完成订单'</td><td>'128'</td></tr>"
                + "<tr><td>'客户满意度'</td><td>'96%'</td></tr>"
                + "</table></body></pdf>";

        // 每次导出使用本次请求的数据，不跨请求共享工厂
        return new JQuickPdfFactory()
                .bind("department", department)
                .bind("month", month)
                .executeContent(template);
    }

    public static void main(String[] args) throws Exception {
        Files.write(Paths.get("d://test//monthly.pdf"), export("研发部", "2026-09"));
    }
}
