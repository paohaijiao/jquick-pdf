package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TableStyleDemo {
    public static void main(String[] args) throws Exception {
        String template = "<pdf><body>"
                // 外层 div 承载边框、圆角与内边距
                + "<div style=\"border:1px solid #4F46E5;borderRadius:8px;padding:10px\">"
                // 表格只负责列宽
                + "<table style=\"width:500px\">"
                // 表头行设置底色
                + "<tr style=\"backgroundColor:#E0E7FF\"><th>'指标'</th><th>'数值'</th></tr>"
                // 单元格用下边框做分隔线，数值列加浅底色
                + "<tr><td style=\"borderBottom:1px solid #CBD5E1\">'收入'</td>"
                + "<td style=\"backgroundColor:#F8FAFC\">'128000'</td></tr>"
                + "</table></div></body></pdf>";
        // 渲染模板并保存 PDF
        byte[] pdf = new JQuickPdfFactory().executeContent(template);
        Files.write(Paths.get("d://test//table-style.pdf"), pdf);
    }
}