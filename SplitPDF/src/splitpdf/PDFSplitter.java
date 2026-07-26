package splitpdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PDFSplitter {

    public static int getPageCount(String inputPath) throws IOException {

        File inputFile = new File(inputPath);

        try (PDDocument document = Loader.loadPDF(inputFile)) {
            return document.getNumberOfPages();
        }
    }

    public static void splitPDF(
            String inputPath,
            String outputDirectory,
            int startPage,
            int endPage,
            int partNumber) throws IOException {

        File inputFile = new File(inputPath);

        try (PDDocument sourceDocument = Loader.loadPDF(inputFile);
             PDDocument outputDocument = new PDDocument()) {

            for (int i = startPage - 1; i < endPage; i++) {

                PDPage page = sourceDocument.getPage(i);

                outputDocument.importPage(page);
            }

            File outputFile = new File(
                    outputDirectory,
                    "part_" + partNumber + ".pdf"
            );

            outputDocument.save(outputFile);

            System.out.println(
                    "Created: " + outputFile.getAbsolutePath()
            );
        }
    }
}