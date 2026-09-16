package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BorderDemo {
    public static void main(String[] args) throws Exception {
        String template = "<pdf><body>"
                // 卡片外层：完整边框 + 圆角
                + "<div style=\"width:420px;padding:18px;backgroundColor:#f8fafc;"
                + "border:'solid 2px #334155';borderRadius:'12px 12px'\">"
                + "<h1>'订单审核'</h1>"
                // 状态行：仅左边框充当状态色条，用 padding 与色条隔开
                + "<div style=\"borderLeft:'solid 8px #16a34a;paddingLeft:12px\">'审核通过'</div>"
                // 金额行：仅下边框充当分隔线
                + "<div style=\"borderBottom:'solid 1px #cbd5e1;marginTop:12px\">'订单金额：12800元'</div>"
                + "</div></body></pdf>";
        byte[] pdf = new JQuickPdfFactory().executeContent(template);
        Files.write(Paths.get("d://test//border-report.pdf"), pdf);
    }
}
