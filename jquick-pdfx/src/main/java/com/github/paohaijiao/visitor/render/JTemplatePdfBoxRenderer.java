package com.github.paohaijiao.visitor.render;

import com.github.paohaijiao.model.JQuickTemplateRenderModel;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Render html template blocks to standalone PDF bytes, then merge into target PDDocument.
 */
public class JTemplatePdfBoxRenderer {

    private static final String DEFAULT_FONT_FAMILY = "SimHei";

    private static final String DEFAULT_CLASSPATH_FONT = "fonts/simhei.ttf";

    public byte[] renderToBytes(JQuickTemplateRenderModel model) throws IOException {
        if (model == null || model.getHtml() == null || model.getHtml().trim().isEmpty()) {
            return new byte[0];
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            registerFonts(builder, model);
            if (model.getBaseUri() != null && !model.getBaseUri().trim().isEmpty()) {
                builder.withHtmlContent(model.getHtml(), model.getBaseUri());
            } else {
                builder.withHtmlContent(model.getHtml(), null);
            }
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new IOException("Failed to render template html with OpenHTMLToPDF", e);
        }
    }

    public PDDocument renderToDocument(JQuickTemplateRenderModel model) throws IOException {
        byte[] pdfBytes = renderToBytes(model);
        if (pdfBytes.length == 0) {
            return new PDDocument();
        }
        return Loader.loadPDF(pdfBytes);
    }

    public void mergeInto(PDDocument target, JQuickTemplateRenderModel model) throws IOException {
        if (target == null) {
            throw new IOException("Target PDDocument must not be null");
        }
        byte[] pdfBytes = renderToBytes(model);
        if (pdfBytes.length == 0) {
            return;
        }
        try (PDDocument templateDocument = Loader.loadPDF(pdfBytes)) {
            PDFMergerUtility mergerUtility = new PDFMergerUtility();
            mergerUtility.appendDocument(target, templateDocument);
        }
    }

    protected void registerFonts(PdfRendererBuilder builder, JQuickTemplateRenderModel model) {
        if (builder == null) {
            return;
        }
        if (model != null && model.getFontPath() != null && !model.getFontPath().trim().isEmpty()) {
            File fontFile = new File(model.getFontPath().trim());
            if (fontFile.exists() && fontFile.isFile()) {
                builder.useFont(fontFile, DEFAULT_FONT_FAMILY);
                return;
            }
        }
        try {
            File classpathFont = extractClasspathFont(DEFAULT_CLASSPATH_FONT);
            if (classpathFont != null && classpathFont.exists()) {
                builder.useFont(classpathFont, DEFAULT_FONT_FAMILY);
            }
        } catch (IOException ignored) {
        }
    }

    protected File extractClasspathFont(String classpathLocation) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(classpathLocation)) {
            if (inputStream == null) {
                return null;
            }
            String suffix = classpathLocation.contains(".") ? classpathLocation.substring(classpathLocation.lastIndexOf('.')) : ".ttf";
            Path tempFile = Files.createTempFile("jquick-template-font-", suffix);
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            tempFile.toFile().deleteOnExit();
            return tempFile.toFile();
        }
    }
}
