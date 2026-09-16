package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BasicSyntaxDemo {
    public static void main(String[] args) throws Exception {
        String template = ""
                + "<pdf>"
                + "<body>"
                + "<h1 style=\"fontSize:24;textAlignment:center\">'项目交付说明'</h1>"
                + "<div style=\"backgroundColor:#eeeeee;padding:10px\">"
                + "<p>'负责人：'${user}</p>"
                + "<p>'当前状态：'${status}</p>"
                + "</div>"
                + "<h2>'交付清单'</h2>"
                + "<table style=\"width:100%\">"
                + "<tr><th>'项目'</th><th>'结果'</th></tr>"
                + "<tr><td>'模板校验'</td><td>'已完成'</td></tr>"
                + "<tr><td>'PDF 输出'</td><td>${status}</td></tr>"
                + "</table>"
                + "</body>"
                + "</pdf>";
        // 绑定占位符并渲染模板
        byte[] pdf = JQuickPdfFactory.create()
                .bind("user", "王五")
                .bind("status", "已完成")
                .executeContent(template);
        // 保存生成结果
        Files.write(Paths.get("d://test//syntax.pdf"), pdf);
    }
}