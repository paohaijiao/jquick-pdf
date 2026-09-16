package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextStyleDemo {
    public static void main(String[] args) throws Exception {
        String template = "<pdf><body>"
                // 标题：字号建立层级 + 深色
                + "<h1 style=\"fontSize:24;fontColor:#1f2937\">'授信审批摘要'</h1>"
                // 英文副标题：字体候选 + 字符间距
                + "<p style=\"fontFamilyNames:Helvetica,Arial;fontSize:14;characterSpacing:1\">'Customer Risk Summary'</p>"
                // 风险提示：颜色 + 描边
                + "<p style=\"fontSize:18;fontColor:red;strokeColor:#7f1d1d;strokeWidth:1\">'高风险：需要人工复核'</p>"
                // 中文正文：wordSpacing 对连续中文影响有限，仅作对照
                + "<p style=\"fontSize:12;wordSpacing:2\">'收入稳定，近十二个月无逾期记录。'</p>"
                + "</body></pdf>";
        byte[] pdf = new JQuickPdfFactory().executeContent(template);
        Files.write(Paths.get("d://test//text-style-report.pdf"), pdf);
    }
}