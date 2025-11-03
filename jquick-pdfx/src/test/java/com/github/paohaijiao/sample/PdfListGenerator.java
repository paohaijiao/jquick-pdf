package com.github.paohaijiao.sample;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.File;
import java.io.IOException;
public class PdfListGenerator {

    private static final DeviceRgb HEADER_COLOR = new DeviceRgb(66, 133, 244); // 蓝色表头
    private static final DeviceRgb HEADER_TEXT_COLOR = new DeviceRgb(255, 255, 255); // 白色文字
    private static final DeviceRgb EVEN_ROW_COLOR = new DeviceRgb(245, 245, 245); // 浅灰色偶数行
    private static final DeviceRgb TEXT_COLOR = new DeviceRgb(51, 51, 51); // 深灰色文字
    private static final DeviceRgb BORDER_COLOR = new DeviceRgb(221, 221, 221); // 浅灰色边框
    public static final String DEST = "d://test//list_example.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PdfListGenerator().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        Paragraph title = new Paragraph("产品信息表")
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(title);
        Table table = new Table(3)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setMarginBottom(30);
        Border tableBorder = new SolidBorder(BORDER_COLOR, 1);
        String[] headers = {"产品ID", "产品名称", "价格(元)"};
        for (String header : headers) {
            Cell headerCell = new Cell()
                    .add(new Paragraph(header).setBold())
                    .setBackgroundColor(HEADER_COLOR)
                    .setFontColor(HEADER_TEXT_COLOR)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setWidth(250)
                    .setBorder(tableBorder)
                    .setPadding(8);
            table.addHeaderCell(headerCell);
        }
        String[][] data = {
                {"P001", "智能手表 Pro", "1299"},
                {"P002", "无线蓝牙耳机", "799"},
                {"P003", "便携式充电器 20000mAh", "199"},
                {"P004", "智能手环", "159"},
                {"P005", "高清摄像头 1080P", "299"}
        };
        for (int i = 0; i < data.length; i++) {
            DeviceRgb rowColor = (i % 2 == 0) ? EVEN_ROW_COLOR : null;
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = new Cell()
                        .add(new Paragraph(data[i][j]))
                        .setFontColor(TEXT_COLOR)
                        .setBorder(tableBorder)
                        .setPadding(8)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE);
                if (rowColor != null) {
                    cell.setBackgroundColor(rowColor);
                }
                if (j == 2) {
                    cell.setTextAlignment(TextAlignment.RIGHT);
                } else {
                    cell.setTextAlignment(TextAlignment.LEFT);
                }
                table.addCell(cell);
            }
        }

        Cell totalLabelCell = new Cell(1, 2) // 跨2列
                .add(new Paragraph("总计").setBold())
                .setFontColor(TEXT_COLOR)
                .setBackgroundColor(ReportColor.getThemeColor())
                .setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBorder(tableBorder)
                .setPadding(8);
        Cell totalValueCell = new Cell()
                .add(new Paragraph("2755").setBold())
                .setFontColor(TEXT_COLOR)
                .setBackgroundColor(ReportColor.getThemeColor())
                .setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBorder(tableBorder)
                .setPadding(8);
        table.addCell(totalLabelCell);
        table.addCell(totalValueCell);
        document.add(table);
        document.close();
        System.out.println("PDF表格生成成功：" + DEST);
    }
}
