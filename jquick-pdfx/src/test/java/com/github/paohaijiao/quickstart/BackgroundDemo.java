package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BackgroundDemo {
    public static void main(String[] args) throws Exception {
        String image = "src/main/resources/image/background.png";
        String template = "<pdf><body>"
                // 固定尺寸、带内边距的内容块承载背景
                + "<div style=\"backgroundColor:lightgray;backgroundImage:'" + image + "';"
                + "width:520px;height:700px;padding:30px\">"
                + "<h1>'年度经营报告'</h1><p>'统一背景区域中的正文内容'</p>"
                + "</div></body></pdf>";
        byte[] pdf = new JQuickPdfFactory().executeContent(template);
        Files.write(Paths.get("d://test//background-report.pdf"), pdf);
    }
}
