package com.github.paohaijiao.element.table;

import com.github.paohaijiao.executor.JQuickPdfFactory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JTableCellVisualTest {

    @Test
    public void visual() throws IOException {
        StringBuilder rows = new StringBuilder();
        rows.append("<tr>")
                .append("<th style='width:120px'>'Product'</th>")
                .append("<th>'Description'</th>")
                .append("<th style='width:70px'>'Qty'</th>")
                .append("<th style='width:80px'>'Amount'</th>")
                .append("</tr>");
        rows.append("<tr>")
                .append("<td>'Gaming Mouse'</td>")
                .append("<td>'Wireless Gaming Mouse Ultra with Aluminum Stand and RGB Lighting'</td>")
                .append("<td>'12'</td>")
                .append("<td>'1,234.56'</td>")
                .append("</tr>");
        rows.append("<tr>")
                .append("<td><span style='color:#333333'>'Laptop Stand'</span><br/><span style='color:#888888'>'out of stock'</span></td>")
                .append("<td>'Aluminum Laptop Stand for MacBook Pro, adjustable height'</td>")
                .append("<td>'-3'</td>")
                .append("<td>'-980.00'</td>")
                .append("</tr>");
        rows.append("<tr>")
                .append("<td>'Keyboard'</td>")
                .append("<td>'Mechanical Keyboard with hot swappable switches'</td>")
                .append("<td>'75%'</td>")
                .append("<td>'$12,480'</td>")
                .append("</tr>");
        String content = "<pdf><body>"
                + "<p style='fontSize:14;fontColor:#1f2937'>TableCell Layout Fixes</p>"
                + "<table style='width:100%;fontSize:9'>" + rows + "</table>"
                + "</body></pdf>";
        byte[] pdf = new JQuickPdfFactory().executeContent(content);
        Files.write(Paths.get("D:/test/table-cell-fixes.pdf"), pdf);
        System.out.println("written D:/test/table-cell-fixes.pdf " + pdf.length);
    }
}
