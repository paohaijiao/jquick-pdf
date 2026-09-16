package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SvgPdfDemo {
    public static void main(String[] args) throws Exception {
        // 服务端拼接 SVG 字符串，只使用基础 rect / text，兼容性最好
        String svg = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"360\" height=\"180\">"
                + "<rect width=\"360\" height=\"180\" fill=\"#f4f7fb\"/>"
                + "<text x=\"24\" y=\"35\" font-size=\"20\" fill=\"#222\">月度订单</text>"
                + "<rect x=\"30\" y=\"120\" width=\"45\" height=\"35\" fill=\"#4169e1\"/>"
                + "<rect x=\"100\" y=\"80\" width=\"45\" height=\"75\" fill=\"#4169e1\"/>"
                + "<rect x=\"170\" y=\"50\" width=\"45\" height=\"105\" fill=\"#4169e1\"/>"
                + "</svg>";
        String template = "<pdf><body><h1>'销售看板'</h1><svg>${svg}</svg>"
                + "<p>'矢量图由服务端生成'</p></body></pdf>";
        // bind 注入上下文，executeContent 返回 PDF 字节数组
        byte[] pdf = new JQuickPdfFactory().bind("svg", svg).executeContent(template);
        Files.write(Paths.get("d://test//svg-report.pdf"), pdf);
    }
}
