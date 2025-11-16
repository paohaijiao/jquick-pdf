package com.github.paohaijiao;

import com.github.paohaijiao.factory.JFontProviderFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.ListNumberingType;

import java.io.File;
import java.io.IOException;

public class ITextListExample {
    public static void main(String[] args) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(new File("d://test//itext_list_example.pdf")));
        Document document = new Document(pdf);
        Paragraph unorderedTitle = new Paragraph("无序列表示例:");
        unorderedTitle.setFont(JFontProviderFactory.defualtFont());
        document.add(unorderedTitle);
        List unorderedList = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022"); // 使用圆点作为项目符号
        ListItem list1 = new ListItem("第一项");
        list1.setFont(JFontProviderFactory.defualtFont());
        ListItem list2 = new ListItem("第二项");
        list2.setFont(JFontProviderFactory.defualtFont());
        ListItem list3 = new ListItem("第三项");
        list3.setFont(JFontProviderFactory.defualtFont());
        unorderedList.add(list1);
        unorderedList.add(list2);
        unorderedList.add(list3);
        document.add(unorderedList);
        document.add(new Paragraph("\n"));
        Paragraph orderedTitle = new Paragraph("有序列表示例:");
        orderedTitle.setFont(JFontProviderFactory.defualtFont());
        document.add(orderedTitle);

        List orderedList = new List()
                .setSymbolIndent(12)
                .setListSymbol(ListNumberingType.DECIMAL); // 使用数字编号

        orderedList.add(list1);
        orderedList.add(list2);
        orderedList.add(list3);

        document.add(orderedList);

        Paragraph nestedTitle = new Paragraph("\n嵌套列表示例:");
        nestedTitle.setFont(JFontProviderFactory.defualtFont());
        document.add(nestedTitle);

        List mainList = new List()
                .setSymbolIndent(12)
                .setListSymbol(ListNumberingType.ENGLISH_LOWER);

        ListItem mainItem1 = new ListItem("主要项目A");
        mainItem1.setFont(JFontProviderFactory.defualtFont());
        ListItem mainItem2 = new ListItem("主要项目B");
        mainItem2.setFont(JFontProviderFactory.defualtFont());
        List subList = new List()
                .setSymbolIndent(24)
                .setListSymbol(ListNumberingType.ROMAN_LOWER); // 使用小写罗马数字

        subList.add(list1);
        subList.add(list2);
        subList.add(list3);
        mainItem2.add(subList);
        mainList.add(mainItem1);
        mainList.add(mainItem2);

        document.add(mainList);
        document.close();
    }
}
