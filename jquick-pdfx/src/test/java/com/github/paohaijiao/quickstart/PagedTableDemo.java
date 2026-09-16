package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory; // 导入工厂
import java.nio.file.*; // 导入文件工具
public class PagedTableDemo { // 声明类
    public static void main(String[] args) throws Exception { // 声明入口
        StringBuilder rows = new StringBuilder(); // 创建行缓冲
        for (int i = 1; i <= 80; i++) { // 构造测试长表
            rows.append("<tr><td>'").append(i).append("'</td><td>") // 添加序号
                    .append("'这是用于验证自动换行的业务说明，这是第").append(i).append("行。'</td></tr>"); // 添加长文本
        }
        String template = "<pdf><body><h1>'月度明细'</h1><table>" // 创建模板
                + "<tr><th>'序号'</th><th>'说明'</th></tr>" // 添加表头
                + rows + "</table></body></pdf>"; // 放入所有行
        System.out.println(template);
        byte[] pdf = new JQuickPdfFactory().executeContent(template); // 执行分页渲染
        Files.write(Paths.get("d://test//paged-table.pdf"), pdf); // 保存文件
    }
}
