/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.sample;


import cn.hutool.core.date.DateUtil;
import com.github.paohaijiao.sample.data.*;
import com.github.paohaijiao.sample.event.CatalogMoveEvent;
import com.github.paohaijiao.sample.event.HeaderTextEvent;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.List;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.properties.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * packageName com.github.paohaijiao.sample
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/12
 */
public class MyPdf {
    protected PdfWriter writer;
    protected PdfDocument pdf;
    protected Document doc;
    protected PdfFont font;
    protected String outPath;
    @Setter
    @Getter
    protected Integer part = 0;
    protected String fontPath;
    protected ConverterProperties proper=new ConverterProperties();
    protected Map<CatalogType, java.util.List<CataLog>> cataLogsMap = new LinkedHashMap<>();
    protected Properties properties = new Properties();
    protected Set<Integer> pageSet = new HashSet<>();

    private FontProvider getFontProvider(){
        FontProvider fontProvider = new FontProvider();
        try {
            // Load the font from resources
            String fontPath = "fonts/simhei.ttf";
            fontProvider.addFont(fontPath, PdfEncodings.IDENTITY_H);

            // Also set the global font variable if not already set
            if (font == null) {
                font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Fallback to a standard font if Chinese font fails
            try {
                fontProvider.addFont("Helvetica");
                if (font == null) {
                    font = PdfFontFactory.createFont("Helvetica");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fontProvider;
    }
    public  void initPdf(String outPath) {
        this.outPath = outPath;
        String inPath = outPath;
        if (part <= 0 || part > 20) {
            inPath = getInPath();
        }
        try {
            writer = new PdfWriter(new File(inPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pdf = new PdfDocument(writer);
        pdf.setDefaultPageSize(PageSize.A4);
        pdf.getDefaultPageSize().applyMargins(0, 0, 0, 0, true);
        FontProvider fontProvider = getFontProvider();
        doc = new Document(pdf);
        doc.setMargins(50, 60, 50, 60);
        doc.setFontProvider(fontProvider);
        doc.setFont(font);
        doc.setFontSize(10.5f);
        doc.setCharacterSpacing(0.1f);
        proper = new ConverterProperties();
        proper.setFontProvider(fontProvider);
    }

    public static void main(String[] args) {
        MyPdf proxy = new MyPdf();
        String prefix = "d://test//";
        proxy.initPdf(prefix);
        proxy.addIndex();
        proxy.addHello();
        proxy.addExaminee();
        proxy.addDetectionContent();
        proxy.addResultSummary();
        proxy.addContext();
        proxy.addThanks();
        proxy.addBackCover();
        proxy.addCatalog();
        proxy.addPageNumber();
    }
    public void addIndex() {
        Image indexImage = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/征信中心.png")));
        indexImage.setMargins(-50, -60, -60, -60);
        Image introductionImage = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/信贷记录.jpg")));
        introductionImage.setMargins(-50, -60, -60, -60);
        Image honorImage = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/qyzx.jpeg")));
        honorImage.setMargins(-50, -60, -60, -60);
        doc.add(indexImage);
        pdf.addNewPage(2).flush();
        doc.add(introductionImage);
        doc.add(honorImage);
        return ;
    }
    public void addHello() {
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        Div div = new Div();
        div.setWidth(UnitValue.createPercentValue(100));
        div.setHeight(UnitValue.createPercentValue(100));
        div.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Paragraph p1 = new Paragraph();
        p1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        p1.setMaxWidth(UnitValue.createPercentValue(75));
        p1.setMarginTop(180f);
        p1.setCharacterSpacing(0.4f);
        Style large = new Style();
        large.setFontSize(22);
        large.setFontColor(ReportColor.getThemeColor());
        p1.add(new Text("尊敬的 ").addStyle(large));
        p1.add(new Text("hellow").addStyle(large).setBorderBottom(new SolidBorder(0.5f)));
        p1.add(new Text("女士") + "：\n").addStyle(large);
        p1.add(new Text("您好！\n"));
        p1.add(new Text("\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0衷心感谢您对我们的信任，选择疾病遗传风险基因检测服务!\n" +
                "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0当您收到这份报告书的时候，我们已经根据您的需求，秉持科学、专业、严谨和保密的态度为您完成了本次基因检测，并根据您的个人特有基因检测结果进行了全面深入的分析。\n" +
                "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0现代医学研究表明，所有疾病（除外伤）的发生均与基因有关，而易感基因与疾病的发生有着非常密切的关系，患病是基因（内因）与外部环境（外因）共同作用的结果。我们只有了解自身的基因奥秘、预测出疾病的发生风险，才能更好的利用现代医学手段，做到早预防、早诊断、早治疗，实现“上医治未病”的目的。\n" +
                "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0本报告是通过对受检者提供的生物样本（一般为口腔黏膜细胞）进行基因检测，依据国际有关疾病易感基因的公开研究成果和数据，参考国际权威23andme，对检测结果进行分析，计算出受检者患某些疾病的风险指数。医学专家顾问团队依据患病风险等级给出针对性的饮食、运动、常规体检等方面的健康建议，旨在帮助受检者根据个人基因信息，科学合理地加强自身健康管理，预防疾病的发生。\n" +
                "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0希望本次检测能为您带来舒适满意的体验，针对本次检测，如果您有任何疑问需要解答，敬请拨打我们的健康热线400-163-5588。我们恭候您的来电，并保证给予及时、专业、贴心的回复。\n" +
                "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0衷心祝愿您身体健康、享受品质生活！"));
        div.add(p1);


        ReportPainting painting = new ReportPainting(pdf, font);
        painting.drawHello("image/纸质报告-03.png");
        painting.close();

        doc.add(div);
    }
    public void addExaminee() {
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        Div div1 = new Div();
        Image iconImage27 = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/icon-27.png")));
        Paragraph p1 = new Paragraph();
        p1.add(iconImage27.addStyle(ReportStyle.getIconStyle()));
        p1.add(new Text("受检人信息").addStyle(ReportStyle.getTitleStyle()));
        div1.add(p1);
        doc.add(div1);
        Div div2 = new Div();
        div2.addStyle(ReportStyle.getDefaultTableOuter().setBorder(new SolidBorder(ReportColor.getThemeColor(), 0.7f)));
        Table table = new Table(3).useAllAvailableWidth();
        table.addCell(ReportComponent.getDefaultCell().add(ReportComponent.getSignParagraph("姓 名：" + "paohaijiao")));
        table.addCell(ReportComponent.getDefaultCell().add(ReportComponent.getSignParagraph("性 别： " +"男")));
        table.addCell(ReportComponent.getDefaultCell().add(ReportComponent.getSignParagraph("年 龄：" + 34)));
        table.startNewRow();
        table.addCell(ReportComponent.getDefaultCell().add(ReportComponent.getSignParagraph("样本编号：" + "NO00001")));
        table.addCell(ReportComponent.getDefaultCell().add(ReportComponent.getSignParagraph("样本类型：口腔粘膜细胞")));
        table.startNewRow();
        table.addCell(ReportComponent.getDefaultCell().add(ReportComponent.getSignParagraph("样本检测结果：合格")));
        table.addCell(ReportComponent.getDefaultCell().add(ReportComponent.getSignParagraph("报告日期：20250101" )));
        div2.add(table);
        doc.add(div2);
        return ;
    }


    public void addDetectionContent() {
        Div div1 = new Div();
        div1.setMargins(30, 0, 20, 0);
        Image iconImage27 = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/icon-29.png")));
        Paragraph p1 = new Paragraph();
        p1.add(iconImage27.addStyle(ReportStyle.getIconStyle()));
        p1.add(new Text("检测内容").addStyle(ReportStyle.getTitleStyle()));
        div1.add(p1);

        Paragraph p2 = new Paragraph();
        p2.setFontSize(11);
        p2.add("检测套餐："  + "\n");
        p2.add("检测方法：高通量基因分型技术\n");
        p2.add("检测形式：对受检者基因组中与各项检测项目密切相关的基因信息进行DNA分型检测。\n");
        div1.add(p2);

        Paragraph p3 = new Paragraph();
        p3.add(new Text("用户须知\n").addStyle(ReportStyle.getSecondTitleStyle()));
        p3.add(new Text("1、检测基因和位点的筛选均基于国际权威数据库（HGMD、OMIM等）和权威学术研究报道。\n" +
                "2、检测基因和位点以及用于解读报告的数据库，会随着基因库的增大和医学研究的更新而升级优化。\n" +
                "3、检测结果可用于指导生活习惯、健康管理和疾病预防⽅案的制定，不作为临床诊断和治疗的依据。\n" +
                "4、疾病的发生、发展与基因变异密切相关，是基因（内因）与外部环境（外因）共同作用的结果。\n" +
                "5、基因检测的目的不是去忽视低风险疾病，而是找出高风险疾病，并进行针对性的预防和健康管理，" +
                "以延缓或规避相应疾病的发生。"));
        Div div2 = new Div();
        div2.setMarginTop(30);
        Table signatureTable = new Table(2);
        signatureTable.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        signatureTable.setWidth(UnitValue.createPercentValue(50));
        signatureTable.addCell(ReportComponent.getSignCell().add(ReportComponent.getSignParagraph("检测人：舒平")));
        signatureTable.addCell(ReportComponent.getSignCell().add(ReportComponent.getSignParagraph("复核人：张萍")));
        signatureTable.startNewRow();
        signatureTable.addCell(ReportComponent.getSignCell().add(ReportComponent.getSignParagraph("日期：" + (DateUtil.format(new Date(), "yyyy年M月d日")))));
        signatureTable.addCell(ReportComponent.getSignCell().add(ReportComponent.getSignParagraph("日期：" + (DateUtil.format(new Date(), "yyyy年M月d日")))));
        div2.add(signatureTable);

        Image iconImage01 = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/image-01.png")));
        iconImage01.setWidth(105);
        iconImage01.setHeight(105);
        iconImage01.setRelativePosition(295, 0, 60, 90);
        div2.add(iconImage01);
        doc.add(div1);
        doc.add(p3);
        doc.add(div2);

        return ;
    }

    public void addResultSummary() {

        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        Paragraph header = ReportComponent.getHeaderLineText("检测结果概况");

        Paragraph p1 = new Paragraph();
        p1.add(new Text("检测结果概况\n").addStyle(ReportStyle.getTitleStyle().setFontSize(22f)));
        p1.add(new Text("本次检测包括"));
        int size = 3;
        int count = 0;
        StringBuilder categoryContent = new StringBuilder();
        for (int i = 0; i < size; i++) {
                categoryContent.append("项目").append("、");
                count++;

        }
        p1.add(new Text(categoryContent.substring(0, categoryContent.length() - 1)).addStyle(ReportStyle.getThirdTitleStyle()));
        p1.add(new Text(count + "部分内容\n"));
        p1.add(new Text("共计"));
        p1.add(new Text("nihao" + "").addStyle(ReportStyle.getThirdTitleStyle()));
        p1.add(new Text("项检测项目"));

        Table t1 = new Table(2).useAllAvailableWidth();
        t1.setMargins(30, -20, 20, 0);
        Image goodTipImage = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/goodtip.png")));
        t1.addCell(ReportComponent.getDefaultCell(2, 1).setWidth(65).setPaddingBottom(30).add(goodTipImage.addStyle(ReportStyle.getLargeIconStyle())));
        t1.addCell(ReportComponent.getDefaultCell().add(new Paragraph("优势标签").addStyle(ReportStyle.getThirdTitleStyle())));
        // 优势标签
        int goodSize =3;
        if (goodSize <= 0) {
            t1.addCell(ReportComponent.getDefaultCell().add(new Paragraph("暂无优势项目")));
        } else {
            Div tabDiv = new Div();
            Paragraph element = new Paragraph();
            for (int i = 0; i < goodSize; i++) {
                Text text = new Text("很好" + ((i + 1) % 3 == 0 ? "\n" : ""));
                Style style = new Style();
                style.setPaddings(3, 10, 3, 10);
                style.setBackgroundColor(ReportColor.getThemeColor());
                style.setBorderRadius(new BorderRadius(10));
                style.setFontColor(ColorConstants.WHITE);
                style.setMargins(0, 3, 0, 3);
                text.addStyle(style);
                element.add(text);
            }
            tabDiv.add(element);
            t1.addCell(ReportComponent.getDefaultCell().add(tabDiv));
        }
        t1.startNewRow();

        // 需要注意
        Image badTipImage = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/badtip.png")));
        t1.addCell(ReportComponent.getDefaultCell(2, 1).setWidth(70).setPaddingRight(20).add(badTipImage.addStyle(ReportStyle.getLargeIconStyle())));
        t1.addCell(ReportComponent.getDefaultCell().add(new Paragraph("需要注意").addStyle(ReportStyle.getThreeTitleOrangeStyle())));


        int badSize = 3;
        if (badSize == 0) {
            t1.addCell(ReportComponent.getDefaultCell().add(new Paragraph("暂无优势项目")));
        } else {
            Div tabDiv = new Div();
            Paragraph element = new Paragraph();
            for (int i = 0; i < badSize; i++) {
                Text text = new Text("good" + ((i + 1) % 3 == 0 ? "\n" : ""));
                Style style = new Style();
                style.setPaddings(3, 10, 3, 10);
                style.setBackgroundColor(ReportColor.getOrangeColor());
                style.setBorderRadius(new BorderRadius(10));
                style.setFontColor(ColorConstants.WHITE);
                style.setMargins(0, 3, 0, 3);
                text.addStyle(style);
                element.add(text);
            }
            tabDiv.add(element);
            t1.addCell(ReportComponent.getDefaultCell().add(tabDiv));
        }

        Paragraph p2 = new Paragraph();
        p2.setFixedPosition(60, 90, UnitValue.createPercentValue(110));
        p2.add(ReportComponent.getSecondTitle("温馨提示\n"));
        p2.add(new Text("1、本检测报告的每项内容主要由检测结果、基因位点信息、简介、健康建议等组成。\n" +
                "2、我们用基因风险指数来解释和划分您的疾病遗传风险等级，共分为五级，五级及分级" +
                "标准为：低风险<0.5、0.5≤稍低风险<0.8、0.8≤中等或正常水平风险<1.2、1.2≤稍高风险<1.8、高风险≥1.8。\n" +
                "3、基因位点信息由四列内容组成，分别为本项目所检测的位点名称、参考基因型(参考型)、检测出" +
                "的基因型(检出型)及该基因型的作用。\n" +
                "4、因篇幅限制，本页仅展示了部分\"需要注意\"的项目，请在目录或详细检测结果查看全部该类项目。"));

        doc.add(header);
        doc.add(p1);
        doc.add(t1);
        doc.add(p2);

        ReportPainting painting = new ReportPainting(pdf, font);
        painting.drawHeader();
        painting.close();

        // 加个详细检测结果页
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        Image image88 = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/成人纸质报告8.8-10.png")));
        doc.add(image88);
        return ;
    }

    public void addContext() {
        java.util.List<Categories.ItemsBean> gaoLevel = new LinkedList<>();
        java.util.List<Categories> categories= Categories.getDatas();
        java.util.Set<Categories> normalLecel = new LinkedHashSet<>();
        for (Categories category : categories) {
            java.util.List<Categories.ItemsBean> items = category.getItems();
            for (Categories.ItemsBean item : items) {
                if (true) {
                    Random random=new Random();
                    int i=random.nextInt(5);
                    if (i >= 3) {
                        gaoLevel.add(item);
                    } else if (i < 3) {
                        normalLecel.add(category);
                    }
                }
            }
        }

        for (Categories.ItemsBean itemsBean : gaoLevel) {
            ReportExtraParam extraParam = new ReportExtraParam(CatalogType.ATTENTION);
            this.addBodyText(itemsBean, extraParam);
        }

        for (Categories categoriesBean : normalLecel) {

            doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            pageSet.add(pdf.getNumberOfPages());
            URL resource = MyPdf.class.getClassLoader().getResource("image/cjzl.png");
            Image backgroundImage = new Image(ImageDataFactory.create(resource));
            doc.add(backgroundImage);

            java.util.List<Categories.ItemsBean> items = categoriesBean.getItems();
            for (Categories.ItemsBean item : items) {
                if (item.isLocked() || item.getIndex() >= 3) {
                    continue;
                }
                ReportExtraParam extraParam = new ReportExtraParam(CatalogType.NORMAL);
                this.addBodyText(item, extraParam);
            }

        }

       return ;
    }

    /**
     * 添加主体文本
     *
     * @param itemsBean
     */
    private void addBodyText(Categories.ItemsBean itemsBean, ReportExtraParam extraParam) {
        float score = itemsBean.getScore();
        String title = "降血压类";
        java.util.List<GeneDesc> geneDesc =GeneDesc.getDatas();
        java.util.List<Content> contents = this.handlerContents(itemsBean);
        java.util.List<Literatures> literatures = Literatures.getData();
        HeaderTextEvent headerTextEvent = new HeaderTextEvent(title, font);
        pdf.addEventHandler(PdfDocumentEvent.START_PAGE, headerTextEvent);
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        CataLog cataLog = new CataLog(itemsBean.getIndex(), itemsBean.getName(), itemsBean.getName(), itemsBean.getLabel(), pdf.getNumberOfPages(), extraParam);
        java.util.List<CataLog> cataLogs = this.cataLogsMap.getOrDefault(extraParam.getType(), new ArrayList<>());
        cataLogs.add(cataLog);
        this.cataLogsMap.put(extraParam.getType(), cataLogs);
        Paragraph p1 = new Paragraph();
        p1.add(new Text(itemsBean.getName() + "\n").addStyle(ReportStyle.getTitleStyle()));
        p1.add(ReportComponent.getSecondTitle("检测结果"));
        doc.add(p1);
        ReportPainting painting = new ReportPainting(pdf, font);
        String categoryCode = itemsBean.getCode();
        boolean gaoLevelFlag = "cjzl".equals(categoryCode) || "xhxt".equals(categoryCode) || "cs".equals(categoryCode) || "jsxl".equals(categoryCode) || "ln".equals(categoryCode) || "nfm".equals(categoryCode) || "xnxg".equals(categoryCode) || "zsmy".equals(categoryCode);
        if (gaoLevelFlag) {
            painting.drawSegment(score);
            Div progressBlock = new Div();
            Paragraph level = new Paragraph();
            level.setWidth(200);
            Style style = new Style();
            style.setFontSize(11);
            style.setMargins(2, -4, 2, 0);
            style.setPaddings(3, 12, 3, 12);
            Style attentionStyle = new Style();
            attentionStyle.setBorderRadius(new BorderRadius(5));
            attentionStyle.setFontColor(ColorConstants.WHITE);
            Text level1 = new Text("低").addStyle(style);
            Text level2 = new Text("稍低").addStyle(style);
            Text level3 = new Text("正常").addStyle(style);
            Text level4 = new Text("稍高").addStyle(style);
            Text level5 = new Text("高").addStyle(style);
            String[] segmenets = ReportConstant.SEGMENETS;
            Color color = null;
            if (score >= Float.parseFloat(segmenets[0]) && score < Float.parseFloat(segmenets[1])) {
                color = ReportColor.getLightBlueColor();
                attentionStyle.setBackgroundColor(color);
                level1.addStyle(attentionStyle);
            } else if (score >= Float.parseFloat(segmenets[1]) && score < Float.parseFloat(segmenets[2])) {
                color = ReportColor.getLightBlueColor();
                attentionStyle.setBackgroundColor(color);
                level2.addStyle(attentionStyle);
            } else if (score >= Float.parseFloat(segmenets[2]) && score < Float.parseFloat(segmenets[3])) {
                color = ReportColor.getThemeColor();
                attentionStyle.setBackgroundColor(color);
                level3.addStyle(attentionStyle);
            } else if (score >= Float.parseFloat(segmenets[3]) && score < Float.parseFloat(segmenets[4])) {
                color = ReportColor.getOrangeColor();
                attentionStyle.setBackgroundColor(color);
                level4.addStyle(attentionStyle);
            } else if (score >= Float.parseFloat(segmenets[4]) && score <= Float.parseFloat(segmenets[5])) {
                color = ReportColor.getRedColor();
                attentionStyle.setBackgroundColor(color);
                level5.addStyle(attentionStyle);
            }
            level.add(level1);
            level.add(level2);
            level.add(level3);
            level.add(level4);
            level.add(level5);
            level.setHorizontalAlignment(HorizontalAlignment.CENTER);
            progressBlock.add(level);
            doc.add(progressBlock);
            int index = 0;
            Paragraph p2 = new Paragraph();
            p2.setMarginTop(60);
            p2.add(new Tab());
            p2.addTabStops(new TabStop(20, TabAlignment.LEFT));
            p2.add("您的基因风险指数为");
            p2.add(new Text(score + "").setFontColor(color));
            p2.add("，" +"xsasasswq");
            p2.add(new Text("xsa123").setFontColor(color));
            switch (index) {
                case 0:
                case 1:
                    p2.add("。在相同外界条件下，与大多数人相比，您具有较好的基因优势。但也不要忽视了预防，疾病是由基因和外界环境共同作用而导致的，因此仍建议您注意规避外界风险因素，保持良好的生活习惯，加强锻炼，定期进行体检和相关筛查。");
                    break;
                case 2:
                    p2.add("。在相同外界条件下，您与大多数人的患病风险相同。基因风险相同情况下，最终患病与否，与外界环境、生活习惯和医疗条件的差异密切相关。因此，仍建议您重视预防" + itemsBean.getName() + "，注意规避外界风险因素，保持良好的生活习惯，加强锻炼，定期进行体检和相关筛查。");
                    break;
                case 3:
                    p2.add("。在相同外界条件下，您患" + "xsaxsa" + "的风险比正常人群稍高。建议您尽可能规避相关高危因素、调整生活习惯，改变不利的生活方式，定期进行体检和相关筛查，认真对待相关急慢性疾病（如发生）的治疗和医疗干预。");
                    break;
                case 4:
                    p2.add("。在相同外界条件下，您患" +"xsaxsaxsa" + "的风险高于正常人群。建议您尽可能规避" + itemsBean.getName() + "相关高危因素、调整生活习惯，改变不利的生活方式，定期进行体检和相关筛查，认真对待相关急慢性疾病（如发生）的治疗和医疗干预。");
                    break;
                default:
                    break;
            }
            doc.add(p2);
        }

        if ("ycb".equals(categoryCode)) {
            Div checkDiv = new Div();
            Paragraph p3 = new Paragraph();
            p3.add(new Text("遗传病" + "\n").addStyle(ReportStyle.getTitleStyle()));
            if ("未检出".equals("未检出")) {
                p3.setBorder(new SolidBorder(ReportColor.getThemeColor(), 3));
            } else if ("携带".equals("携带")) {
                p3.setBorder(new SolidBorder(ReportColor.getOrangeColor(), 3));
            } else if ("携带".equals("携带")) {
                p3.setBorder(new SolidBorder(ReportColor.getRedColor(), 3));
            }
            p3.setTextAlignment(TextAlignment.CENTER);
            p3.setWidth(200);
            p3.setHorizontalAlignment(HorizontalAlignment.CENTER);
            checkDiv.add(p3);

            Paragraph p4 = new Paragraph();
            p4.add(new Text("检出:检出致病突变且为致病基因型\n"));
            p4.add(new Text("携带:携带致病突变但为非致病基因型\n"));
            p4.add(new Text("未检出：未检出致病突变"));
            p4.setTextAlignment(TextAlignment.CENTER);
            checkDiv.add(p4);
            doc.add(checkDiv);
        }
        boolean wssFlag = "wss".equals(categoryCode) || "kwz".equals(categoryCode);
        if (wssFlag) {
            painting.drawWss(1, itemsBean);
        }
        boolean personFlag = "gxtz".equals(categoryCode) || "jfss".equals(categoryCode) || "mrhf".equals(categoryCode) || "jfss".equals(categoryCode) || "categoryCode".equals(itemsBean.getCode());
        if (personFlag) {
            this.addPersonalityTraits(itemsBean, categoryCode);
        }
        Paragraph p2 = new Paragraph("基因位点信息").addStyle(ReportStyle.getSecondTitleStyle());
        if (wssFlag) {
            p2.setMarginTop(110);
        }
        doc.add(p2);
        Table geneLocusTable = new Table(4).useAllAvailableWidth();
        geneLocusTable.addCell(ReportComponent.getTableCell().add(new Paragraph("基因位点")).addStyle(ReportStyle.getTableHeader()));
        geneLocusTable.addCell(ReportComponent.getTableCell().add(new Paragraph("参考型")).addStyle(ReportStyle.getTableHeader()));
        geneLocusTable.addCell(ReportComponent.getTableCell().add(new Paragraph("检出型")).addStyle(ReportStyle.getTableHeader()));
        geneLocusTable.addCell(ReportComponent.getTableCell().add(new Paragraph("基因型解释")).addStyle(ReportStyle.getTableHeader()));
        for (GeneDesc geneDescBean : geneDesc) {
            int padding = -2;
            geneLocusTable.startNewRow();
            geneLocusTable.addCell(ReportComponent.getTableCell().setPadding(padding).add(new Paragraph(geneDescBean.getOg_id())));
            geneLocusTable.addCell(ReportComponent.getTableCell().setPadding(padding).add(new Paragraph(geneDescBean.getRef_genotype())));
            geneLocusTable.addCell(ReportComponent.getTableCell().setPadding(padding).add(new Paragraph(geneDescBean.getGenotype())));
            geneLocusTable.addCell(ReportComponent.getTableCell().setPadding(padding).add(new Paragraph(geneDescBean.getLabel())));
        }
        doc.add(geneLocusTable);
        for (Content content : contents) {
            if (content.getLabel().contains("线上") || content.getLabel().contains("卡路里表") ||
                    content.getLabel().contains("减肥建议") || content.getLabel().contains("饮食护理") || content.getLabel().contains("外部护理")) {
                continue;
            }
            Div overall = new Div();
            if ("健康建议".equals(content.getLabel())) {
                overall.setKeepTogether(true);
            }
            if ("症状".equals(content.getLabel())) {
                overall.setKeepTogether(true);
            }
            if ("基因解读".equals(content.getLabel())) {
                overall.setKeepTogether(true);
            }
            overall.add(ReportComponent.getTitleParagraph(ReportComponent.getSecondTitle(content.getLabel())));
            String value = content.getValue();
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            java.util.List<IElement> iElements = getFixContent(value);
            for (IElement iElement : iElements) {
                Style style = new Style();
                style.setFontSize(10);
                style.setCharacterSpacing(0.7f);
                if (iElement instanceof Div) {
                    Div div = (Div) iElement;
                    java.util.List<IElement> children = div.getChildren();
                    // 全部段落改成相同样式
                    this.addParagraphStyleCircle(style, children);
                    overall.add(div);
                } else if (iElement instanceof Paragraph) {
                    Paragraph element = (Paragraph) iElement;
                    overall.add(element.addStyle(style));
                }
            }
            doc.setFontProvider(getFontProvider());
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFont("fonts/simhei.ttf", PdfEncodings.IDENTITY_H);
            doc.setFontProvider(fontProvider);
            doc.setFontFamily("simhei");
            doc.add(overall);
        }
        int number = 1;
        Div literatureDiv = new Div();
        Paragraph titleParagraph = ReportComponent.getTitleParagraph(new Text("参考文献（部分）").addStyle(ReportStyle.getSecondTitleStyle()));
        literatureDiv.add(titleParagraph);
        literatureDiv.setKeepTogether(true);
        for (int j=0;j<3;j++) {
            Paragraph segment = new Paragraph();
            segment.setFixedLeading(14);
            segment.setFontSize(9f).setFontColor(new DeviceRgb(85, 85, 85));
            segment.add(new Text("[" + (number++) + "]"));
            segment.add(new Text("literature" + ". "));
            segment.add(new Text("title" + ". "));
            segment.add(new Text(  "Journal. "));
            segment.add(new Text( "getSerialNumber. "));
            literatureDiv.add(segment);
        }
        doc.add(literatureDiv);
        pdf.removeEventHandler(PdfDocumentEvent.START_PAGE, headerTextEvent);
        painting.close();
    }

    private void addParagraphStyleCircle(Style style, java.util.List<IElement> children) {
        for (IElement child : children) {
            if (child instanceof Paragraph) {
                Paragraph element = (Paragraph) child;
                element.addStyle(style);
                java.util.List<IElement> children1 = element.getChildren();
                this.addParagraphStyleCircle(style, children1);
            }
            if (child instanceof Div) {
                Div div = (Div) child;
                java.util.List<IElement> children1 = div.getChildren();
                this.addParagraphStyleCircle(style, children1);
            }
            if (child instanceof Text) {
                Text text = (Text) child;
                text.addStyle(style);
            }
        }
    }

    private java.util.List<IElement> getFixContent(String content) {
        if (content.startsWith("<div>")) {
            content = content.replaceAll("<div>", "<div style='line-height:18pt;font-size:16px;'>");
        } else {
            content = "<div style='line-height:18pt;font-size:16px;'>" + content + "</div>";
        }
        return HtmlConverter.convertToElements(content, proper);
    }

    private java.util.List<Content> handlerContents(Categories.ItemsBean itemsBean) {
        java.util.List<Content> contents =  Content.getDatas();
        int size = contents.size();
        java.util.List<Content> result =  Content.getDatas();
        if (size > 6) {
            java.util.List<Content> contentsBeans = contents.subList(2, 6);
            result.add(contents.get(0));
            result.add(contents.get(1));
            for (int i = 6; i < size; i++) {
                Content contentsBean = contents.get(i);
                contentsBean.setLabel(contentsBean.getLabel().split("-")[0]);
                result.add(contentsBean);
            }
            result.add(contentsBeans.get(contentsBeans.size() - 1));
        } else {
            for (Content content : contents) {
                if (content.getLabel().contains("线下")) {
                    content.setLabel(content.getLabel().split("-")[0]);
                }
            }
        }
        return result;
    }

    private void addPersonalityTraits(Categories.ItemsBean itemsBean, String categoryCode) {
        Color[] colors = new Color[]{new DeviceRgb(112, 223, 191), new DeviceRgb(30, 191, 190), new DeviceRgb(37, 98, 206), new DeviceRgb(248, 182, 45), new DeviceRgb(242, 107, 111)};
        URL resource = ReportPainting.class.getClassLoader().getResource("image/gene/individual/" + (itemsBean.getIndex() + 1) + ".png");
        Image personImage = new Image(ImageDataFactory.create(resource));
        personImage.scale(0.25f, 0.25f);
        personImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
        doc.add(personImage);
        boolean which = !"jfss".equals(categoryCode) && !"gxtz".equals(categoryCode);
        if (which) {
            Paragraph element = new Paragraph("您的" + itemsBean.getName());
            element.setTextAlignment(TextAlignment.CENTER);
            element.add(new Text(itemsBean.getLabel()).setFontColor(colors[itemsBean.getIndex()]));
            doc.add(element);
        }
        which = "gxtz".equals(categoryCode) || "jfss".equals(categoryCode) ;
        if (which) {
            Paragraph element = new Paragraph();
            element.setTextAlignment(TextAlignment.CENTER);
            element.add(new Text(itemsBean.getDescription()).setFontColor(colors[itemsBean.getIndex()]));
            doc.add(element);
        }
    }

    public void addThanks() {
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        properties.setProperty(ReportConstant.FORBIDDE, pdf.getNumberOfPages() + "");
        Paragraph p1 = new Paragraph();
        p1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        p1.setMaxWidth(UnitValue.createPercentValue(80));
        p1.setMarginTop(200f);
        p1.setCharacterSpacing(0.4f);
        p1.add(new Text("\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0再次感谢您选用提供的基因检测服务。\n" +
                "\n" +
                "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0疾病的发生受到先天和后天两个因素的共同影响，某疾病先天遗传风险低并不代表患病概率是零，如果后天生活习惯很差，生存环境不好，都会提升疾病的发生风险，促进疾病的最终发生；做基因检测的目的，不是去忽视低风险的疾病，而是要找出高风险疾病进行重点预防，采取措施进行针对性干预，以便阻断或者延缓疾病的发生。\n" +
                "\n" +
                "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0您可以通过我们提供的健康建议开展健康管理，对风险较高项目积极预防，如改变饮食习惯，定期进行针对性的临床体检等。若您携带某种疾病易感基因型，该基因型很可能也存在于您亲属的基因中并遗传给他们的后代。因此，建议您的亲属也针对这些高风险项目进行检测，以了解自身健康风险，及早采取干预措施，拥有健康品质生活。\n" +
                "\n" +
                "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0如果您对我们检测服务和体验有任何意见或建议，敬请拨打我们的健康热线400-163-5588，或者手机扫描下部二维码，联系您的专属健康顾问。"));
        doc.add(p1);
        URL resource = MyPdf.class.getClassLoader().getResource("image/结束语.png");
        Image backImage = new Image(ImageDataFactory.create(resource));
        int pageNum = pdf.getNumberOfPages();
        backImage.setFixedPosition(pageNum, 0, 0, UnitValue.createPercentValue(125));
        backImage.scale(1, 1.05f);
        doc.add(backImage);
        try {
            URL resource1 = MyPdf.class.getClassLoader().getResource("image/cjzl.png");
            Image csQrCodeImage = new Image(ImageDataFactory.create(resource1));
            csQrCodeImage.setMarginTop(20);
            csQrCodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
            csQrCodeImage.setWidth(90);
            csQrCodeImage.setHeight(90);
            doc.add(csQrCodeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ;
    }

    public void addBackCover() {
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        Image backCoverImage = new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/封底-02.png")));
        backCoverImage.setWidth(UnitValue.createPercentValue(100));
        backCoverImage.scale(1.3f, 1.3f);
        backCoverImage.setMarginLeft(-70);
        backCoverImage.setMarginTop(-80);
        doc.add(backCoverImage);
        return ;
    }

    public void addCatalog() {
        CatalogMoveEvent catalogMoveEvent = new CatalogMoveEvent(properties);
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, catalogMoveEvent);
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        int startNum = pdf.getNumberOfPages();
        Div div1 = getCataLogDiv(0);
        doc.add(div1);
        pdf.removeEventHandler(PdfDocumentEvent.END_PAGE, catalogMoveEvent);
        int pageSize = catalogMoveEvent.getPageSize();
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        Div cataLogDiv = getCataLogDiv(pageSize);
        doc.add(cataLogDiv);

        for (int i = startNum; i < startNum + pageSize; i++) {
            pdf.removePage(startNum);
        }
        return ;
    }

    private Div getCataLogDiv(int offPage) {
        Div div1 = new Div();
        Table tableCatalog = new Table(4).useAllAvailableWidth();
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("检测结果概况").addStyle(ReportStyle.getSecondTitleStyle())));
        tableCatalog.addCell(ReportComponent.getCatelogCell(2).add(ReportComponent.getCatelogDottedLine(1)));
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("8")));
        tableCatalog.startNewRow();
        Paragraph p1 = new Paragraph();
        p1.add(new Text("目录").addStyle(ReportStyle.getTitleStyle()).setFontSize(32));
        java.util.List<CataLog> cataLogs = cataLogsMap.getOrDefault(CatalogType.ATTENTION, new LinkedList<>());
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("需要注意").addStyle(ReportStyle.getSecondTitleStyle())));
        tableCatalog.startNewRow();
        this.addCatalogDetail(offPage, tableCatalog, cataLogs);

        cataLogs = cataLogsMap.getOrDefault(CatalogType.NORMAL, new LinkedList<>());
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("正常项目").addStyle(ReportStyle.getSecondTitleStyle())));
        tableCatalog.startNewRow();
        Map<String, java.util.List<CataLog>> cataLogMap = cataLogs.stream().collect(Collectors.groupingBy(CataLog::getCategoryName, LinkedHashMap::new, Collectors.toList()));
        Set<Map.Entry<String, java.util.List<CataLog>>> entries1 = cataLogMap.entrySet();
        for (Map.Entry<String, java.util.List<CataLog>> cataLogEntry : entries1) {
            String categoryName = cataLogEntry.getKey();
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph(categoryName).addStyle(ReportStyle.getSecondTitleStyle().setFontSize(13))));
            tableCatalog.startNewRow();
            java.util.List<CataLog> values = cataLogEntry.getValue();
            this.addCatalogDetail(offPage, tableCatalog, values);
        }
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("结束语").addStyle(ReportStyle.getSecondTitleStyle())));
        div1.add(p1);
        div1.add(tableCatalog);
        return div1;
    }

    private void addCatalogDetail(int offPage, Table tableCatalog, java.util.List<CataLog> values) {
        for (CataLog cataLog : values) {
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph(cataLog.getName())));
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(ReportComponent.getCatelogDottedLine(2)));
            System.out.println(selectColor(cataLog));
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new List().add(new ListItem(cataLog.getLabel())
                    .setListSymbol(new Image(ImageDataFactory.create(MyPdf.class.getClassLoader().getResource("image/dark-green-point.png")))
                            .addStyle(ReportStyle.getDefaultPoint())))));
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph((cataLog.getPageNumber() + offPage) + "")));
            tableCatalog.startNewRow();
        }
    }

    private String selectColor(CataLog cataLog) {
        switch (cataLog.getIndex()) {
            case 0:
                return "green";
            case 1:
                return "dark-green";
            case 2:
                return "blue";
            case 3:
                return "orange";
            case 4:
                return "red";
            default:
                break;
        }
        return "blue";
    }
    protected String getInPath() {
        int index = outPath.lastIndexOf("/");
        int index2 = outPath.lastIndexOf("/", index-1);
        String prefix = outPath.substring(0, index2);
        String fileName = outPath.substring(index);
        String name = fileName.split("\\.")[0];
        String pre = prefix + "/temp";
        if (!Files.exists(Paths.get(pre))) {
            try {
                Files.createDirectories(Paths.get(pre));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pre + name + "_temp.pdf";
    }
    public void addPageNumber() {
        Integer catalogSize = Integer.parseInt(properties.getProperty(ReportConstant.CATALOG_SIZE));
        pdf.close();
        PdfReader reader = null;
        PdfWriter writer = null;
        String inPath = getInPath();
        try {
            reader = new PdfReader(new File(inPath));
            writer = new PdfWriter(new File("d://test//hello.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PdfDocument pdf = new PdfDocument(reader, writer);
        Document doc = new Document(pdf);
        int startPage = 7;
        int numberOfPages = pdf.getNumberOfPages();
        for (int i = 0; i < catalogSize; i++) {
            pdf.movePage(numberOfPages, startPage);
        }
        String forbidPage = properties.getProperty(ReportConstant.FORBIDDE);
        for (int pageNumber = 1; pageNumber < numberOfPages + 1; pageNumber++) {

            if (pageNumber > 6 + catalogSize && pageNumber != 8 + catalogSize) {
                if (forbidPage != null && (pageNumber - catalogSize) >= Integer.parseInt(forbidPage)) {
                    continue;
                }
                if (pageSet.contains(pageNumber - catalogSize)) {
                    continue;
                }
                PageSize pageSize = pdf.getDefaultPageSize();
                doc.showTextAligned(new Paragraph(String.format("- %d -", pageNumber)), pageSize.getWidth() / 2, 30, pageNumber, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
            }
        }
        pdf.close();
        // 删除临时文件
        try {
            Files.delete(Paths.get(inPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ;
    }

}
