package com.github.paohaijiao.sample;

import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JFileReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreak;
import com.itextpdf.html2pdf.attach.impl.layout.form.element.*;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Text;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JQuickPdfTest {

    @Test
    public void file() throws IOException {
        LineSeparator line = new LineSeparator(new SolidLine());

        InputField text=new InputField();
        Document document=new Document(null);
        document.add(text);
        JReader fileReader = new JReSourceFileReader("rule1.txt");
        JAdaptor context = new JAdaptor(fileReader);
        JQuickPdfXExecutor executor = new JQuickPdfXExecutor();
        executor.execute(context.getRuleContent());
    }
}
