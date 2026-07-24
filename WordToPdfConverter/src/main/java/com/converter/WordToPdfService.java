package com.converter;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

public class WordToPdfService {

    private static final IConverter CONVERTER =
            LocalConverter.builder()
                    .workerPool(5, 10, 2, TimeUnit.MINUTES)
                    .build();

    public static void convertFolder(
            String inputFolder,
            String outputFolder) {

        try {

            File folder = new File(inputFolder);

            File[] files = folder.listFiles(
                    (dir, name) ->
                            name.toLowerCase().endsWith(".docx"));

            if (files == null || files.length == 0) {

                System.out.println("No DOCX files found.");
                return;
            }

            File outDir = new File(outputFolder);

            if (!outDir.exists()) {
                outDir.mkdirs();
            }

            System.out.println("Total Files : " + files.length);

            for (File docx : files) {

                String pdfName =
                        docx.getName()
                                .replaceFirst("(?i)\\.docx$", ".pdf");

                File pdf =
                        new File(outDir, pdfName);

                convert(docx, pdf);
            }

        } finally {

            try {
                CONVERTER.shutDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void convert(
            File docx,
            File pdf) {

        try {

            CONVERTER.convert(
                            new FileInputStream(docx))
                    .as(DocumentType.DOCX)
                    .to(new FileOutputStream(pdf))
                    .as(DocumentType.PDF)
                    .execute();

            System.out.println(
                    "SUCCESS : " + docx.getName());

        } catch (Exception e) {

            System.out.println(
                    "FAILED : " + docx.getName());

            e.printStackTrace();
        }
    }
}