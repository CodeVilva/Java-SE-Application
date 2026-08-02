package imagetopdfconverter;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class Converter {

    public static void convertImageToPdf(String imagePath, String pdfPath) {

        try (PDDocument document = new PDDocument()) {
            
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            
            PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                float width = Math.min(pdImage.getWidth(), PDRectangle.A4.getWidth() - 40);
                float height = Math.min(pdImage.getHeight(), PDRectangle.A4.getHeight() - 40);
                
                contentStream.drawImage(pdImage, 20, 20, width, height);
            }
            
            document.save(pdfPath);
            System.out.println("Success! PDF created successfully at: " + pdfPath);
            
        } catch (IOException e) {
            System.err.println("Error during PDF conversion: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        String inputImagePath = "D:/image/images.jpg"; 
        String outputPdfPath = "D:/image/output.pdf"; 
        
        convertImageToPdf(inputImagePath, outputPdfPath);
    }
}
