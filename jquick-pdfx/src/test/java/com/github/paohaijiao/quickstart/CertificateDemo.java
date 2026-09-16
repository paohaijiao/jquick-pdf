package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CertificateDemo {
    public static void main(String[] args) throws Exception {
        String template = "<pdf><body>"
                + "<h1 style=\"textAlignment:center;fontSize:26;fontColor:#244c6b\">'培训结业证书'</h1>"
                + "<p style=\"textAlignment:center;fontSize:16\">'兹证明：'${student}</p>"
                + "<p>'课程：'${course}</p>"
                + "<p>'结业日期：'${date}</p>"
                + "<div style=\"marginTop:20px;border:solid 1px #999;padding:12px\">"
                + "<p>'本证书用于证明学员完成规定课程并通过结业考核。'</p>"
                + "</div></body></pdf>";

        // 绑定本次证书的数据并生成 PDF
        byte[] pdf = new JQuickPdfFactory()
                .bind("student", "李四")
                .bind("course", "Java 后端开发")
                .bind("date", "2026-09-14")
                .executeContent(template);
        Files.write(Paths.get("d://test//certificate.pdf"), pdf);
    }
}