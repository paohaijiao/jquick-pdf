package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DynamicTableDemo {
    public static void main(String[] args) throws Exception {
        // 模拟数据库查询结果：编号、商品、金额
        List<String[]> rows = Arrays.asList(
                new String[]{"1001", "键盘", "199"},
                new String[]{"1002", "显示器", "1299"});
        // 由后端拼接数据行，模板中不含循环指令
        StringBuilder body = new StringBuilder();
        for (String[] row : rows) {
            body.append("<tr><td>'").append(row[0]).append("'</td><td>'")
                    .append(row[1]).append("'</td><td>'")
                    .append(row[2]).append("'</td></tr>");
        }
        // 固定表头 + 动态行，拼成完整模板
        String template = "<pdf><body><h1>'销售报表'</h1><table>"
                + "<tr><th>'编号'</th><th>'商品'</th><th>'金额'</th></tr>"
                + body + "</table></body></pdf>";
        // 执行渲染并保存结果
        byte[] pdf = new JQuickPdfFactory().executeContent(template);
        Files.write(Paths.get("d://test//dynamic-report.pdf"), pdf);
    }
}
