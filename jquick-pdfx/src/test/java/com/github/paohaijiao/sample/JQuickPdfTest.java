package com.github.paohaijiao.sample;

import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.io.IOException;

public class JQuickPdfTest {

    @Test
    public void file() throws IOException {

        JReader fileReader = new JReSourceFileReader("rule.txt");
        JAdaptor context = new JAdaptor(fileReader);
        JQuickPdfXExecutor executor = new JQuickPdfXExecutor();
        executor.execute(context.getRuleContent());
    }
}
