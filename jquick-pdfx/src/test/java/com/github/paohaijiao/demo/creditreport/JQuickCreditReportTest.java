package com.github.paohaijiao.demo.creditreport;

import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.config.JTemplateConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class JQuickCreditReportTest {
    public static final String  path=JQuickConstant.path;

    @Test
    public void reportByContent() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path+"test.pdf");
        JReader htmlReader = new JReSourceFileReader("html.txt");
        JAdaptor htmlAdaptor = new JAdaptor(htmlReader);
        JPdfConfig config = new JPdfConfig();
        JTemplateConfig templateConfig = config.getTemplateConfig();
        templateConfig.put("html", htmlAdaptor.getRuleContent());
        config.setTemplateConfig(templateConfig);
        JReader fileReader = new JReSourceFileReader("report.txt");
        JAdaptor adaptor = new JAdaptor(fileReader);
        JReader svgReader = new JReSourceFileReader("radar.txt");
        JAdaptor svgAdaptor = new JAdaptor(svgReader);
        JQuickPdfFactory factory=new JQuickPdfFactory(config);
        factory.bind("svg",svgAdaptor.getRuleContent());
        byte[] bytes=factory.executeContent(adaptor.getRuleContent());
        fileOutputStream.write(bytes);
    }
    @Test
    public void reportByClassResourceFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path+"test.pdf");
        JReader htmlReader = new JReSourceFileReader("html.txt");
        JAdaptor htmlAdaptor = new JAdaptor(htmlReader);
        JPdfConfig config = new JPdfConfig();
        JTemplateConfig templateConfig = config.getTemplateConfig();
        templateConfig.put("html", htmlAdaptor.getRuleContent());
        config.setTemplateConfig(templateConfig);
        JReader svgReader = new JReSourceFileReader("radar.txt");
        JAdaptor svgAdaptor = new JAdaptor(svgReader);
        JQuickPdfFactory factory=new JQuickPdfFactory(config);
        factory.bind("svg",svgAdaptor.getRuleContent());
        byte[] bytes=factory.executeResource("report.txt");
        fileOutputStream.write(bytes);
    }
    @Test
    public void reportByFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path+"test.pdf");
        JReader htmlReader = new JReSourceFileReader("html.txt");
        JAdaptor htmlAdaptor = new JAdaptor(htmlReader);
        JPdfConfig config = new JPdfConfig();
        JTemplateConfig templateConfig = config.getTemplateConfig();
        templateConfig.put("html", htmlAdaptor.getRuleContent());
        config.setTemplateConfig(templateConfig);
        JReader svgReader = new JReSourceFileReader("radar.txt");
        JAdaptor svgAdaptor = new JAdaptor(svgReader);
        JQuickPdfFactory factory=new JQuickPdfFactory(config);
        factory.bind("svg",svgAdaptor.getRuleContent());
        byte[] bytes=factory.executeFile("D:\\my\\jquick-pdf\\jquick-pdfx\\src\\test\\resources\\report.txt");
        fileOutputStream.write(bytes);
    }
}
