package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OpacityDemo {
    public static void main(String[] args) throws Exception {
        String template = "<pdf><body>"
                + "<div style=\"width:460px;height:100px;backgroundColor:#dbeafe;"
                + "opacity:0.45;padding:15px\">'内部资料'</div>"
                + "<div style=\"backgroundColor:white;padding:10px\">"
                + "<h1>'月度经营报告'</h1>"
                + "<p>'透明辅助层不应影响正文阅读。'</p>"
                + "</div></body></pdf>";
        byte[] pdf = new JQuickPdfFactory().executeContent(template);
        Files.write(Paths.get("d://test//opacity-report.pdf"), pdf);
    }
}
