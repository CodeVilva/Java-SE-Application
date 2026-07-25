/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package mergerpdf;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
/**
 *
 * @author vilva
 */
public class MergerPDF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // code application logic here
        
        PDFMergerUtility merger = new PDFMergerUtility();
        
        String folderPath = ""; // Paste path for output stream folder
        String fileName = "merged_pdf.pdf";
        
        //add much as files location needed
        
        String FileLoc1 = ""; // Paste path for input file location
        String FileLoc2 = ""; // Paste path for input file location
        
        try{
            
        File destinationFile = new File(folderPath, fileName);
        
        // add the file sources correspond to the files declared
        
        merger.addSource(new File(FileLoc1));
        merger.addSource(new File(FileLoc2));
        
        Path path = Paths.get(folderPath);
        
        if(!Files.exists(path)){
            
            Files.createDirectories(path); // create folder if not exists
            
            System.out.println("Output directory created: " + folderPath);
        
        }
        
        merger.setDestinationFileName(destinationFile.getAbsolutePath());
        
        merger.mergeDocuments(null);
            System.out.println("PDF files combined seamlessly!");
            
        } catch (IOException e){
            
            System.out.println("Failed to Merge!");
            
            e.printStackTrace();
            
        }       
        
    }
    
}
