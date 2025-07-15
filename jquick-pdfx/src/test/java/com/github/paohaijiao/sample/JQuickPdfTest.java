package com.github.paohaijiao.sample;

import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JQuickPdfTest {

    @Test
    public void file() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("rule.txt")) {
            if (inputStream != null) {
                // 读取输入流内容
                byte[] bytes = inputStream.readAllBytes();
                String content = new String(bytes, StandardCharsets.UTF_8);
                JQuickPdfXExecutor executor = new JQuickPdfXExecutor();
                System.out.println(content);
                executor.execute(content);
            } else {
                System.out.println("文件未找到");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
