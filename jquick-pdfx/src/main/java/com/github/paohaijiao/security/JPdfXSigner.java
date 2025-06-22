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
package com.github.paohaijiao.security;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.signatures.BouncyCastleDigest;
import com.itextpdf.signatures.DigestAlgorithms;
import com.itextpdf.signatures.IExternalDigest;
import com.itextpdf.signatures.IExternalSignature;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.PrivateKeySignature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * packageName com.github.paohaijiao.security
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXSigner
 * @date 2025/6/22
 * @description
 */
public class JPdfXSigner {
    public static final String DEST = "./signed_output.pdf";
    public static final String SRC = "./original.pdf";
    public static final String KEYSTORE = "./keystore.p12";
    public static final char[] PASSWORD = "password".toCharArray();

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        createOriginalPdfIfNotExists(SRC);
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new JPdfXSigner().sign(SRC, DEST, KEYSTORE, PASSWORD);
    }

    public void sign(String src, String dest, String keystorePath, char[] password)
            throws GeneralSecurityException, IOException {
        KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
        ks.load(new FileInputStream(keystorePath), password);
        String alias = ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, password);
        Certificate[] chain = ks.getCertificateChain(alias);
        PdfReader reader = new PdfReader(src);
        PdfWriter writer = new PdfWriter(dest);
        PdfSigner signer = new PdfSigner(reader, writer, null);
        signer.setFieldName("signature");

        // 自定义签名外观
        // signer.getSignatureAppearance()
        //     .setReason("Test")
        //     .setLocation("Location")
        //     .setPageRect(new Rectangle(36, 748, 200, 100))
        //     .setPageNumber(1);

        IExternalDigest digest = new BouncyCastleDigest();
        IExternalSignature signature = new PrivateKeySignature(pk, DigestAlgorithms.SHA256, "BC");
        signer.signDetached(digest, signature, chain, null, null, null, 0, PdfSigner.CryptoStandard.CMS);
    }

    private static void createOriginalPdfIfNotExists(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(path));
            pdfDoc.addNewPage();
            pdfDoc.close();
            System.out.println("Created original PDF file at: " + path);
        }
    }
    /**
     * keytool -genkeypair -alias test -keyalg RSA -keysize 2048 -validity 365 -storetype PKCS12 -keystore keystore.p12 -storepass password
     */
}
