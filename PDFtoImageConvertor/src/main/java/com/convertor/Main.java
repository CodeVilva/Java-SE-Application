package com.converter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class Main {

    // ========= CONFIGURATION =========

    private static final String INPUT_PDF =
            "D:/PDF/output.pdf";

    private static final String OUTPUT_FOLDER =
            "D:/PDF/output";

    private static final String IMAGE_FORMAT = "png";

    private static final int DPI = 300;

    // ================================

    public static void main(String[] args) {

        File pdfFile = new File(INPUT_PDF);

        if (!pdfFile.exists()) {
            System.out.println("PDF not found!");
            return;
        }

        File outputDir = new File(OUTPUT_FOLDER);

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try (PDDocument document = Loader.loadPDF(pdfFile)) {

            PDFRenderer renderer = new PDFRenderer(document);

            int totalPages = document.getNumberOfPages();

            System.out.println("--------------------------------");
            System.out.println("PDF TO IMAGE CONVERTER");
            System.out.println("--------------------------------");
            System.out.println("Pages : " + totalPages);
            System.out.println();

            for (int i = 0; i < totalPages; i++) {

                BufferedImage image =
                        renderer.renderImageWithDPI(i, DPI);

                File outputFile = new File(
                        outputDir,
                        "page_" + (i + 1) + "." + IMAGE_FORMAT
                );

                ImageIO.write(image, IMAGE_FORMAT, outputFile);

                System.out.println(
                        "Converted Page " + (i + 1)
                );
            }

            System.out.println();
            System.out.println("==================================");
            System.out.println("Conversion Completed Successfully");
            System.out.println("Images Saved At:");
            System.out.println(outputDir.getAbsolutePath());
            System.out.println("==================================");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}