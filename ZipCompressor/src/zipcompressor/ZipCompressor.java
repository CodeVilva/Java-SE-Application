/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package zipcompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 *
 * @author vilva
 */
public class ZipCompressor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String sourceFile = "/inputfile"; //place file path that to be zipped
        String zipFile = "/outputfile.zip"; //place output file path
        
        try {
            fileCompressor(sourceFile, zipFile);
            System.out.println("Compression completed!");

        } catch (IOException e) {
            System.out.println("Compression failed.");
            e.printStackTrace();
        }
    }

    private static void fileCompressor(String sourceFile, String zipFile) throws FileNotFoundException, IOException {
        
        FileInputStream fis = new FileInputStream(sourceFile);
        
        FileOutputStream fos = new FileOutputStream(zipFile);
        
        ZipOutputStream zos = new ZipOutputStream(fos);
        
        ZipEntry entry = new ZipEntry(new File(sourceFile).getName());
        
        zos.putNextEntry(entry);
        
        byte[] buffer = new byte[4096];
        
        int byteRead;
        
        while((byteRead = fis.read(buffer)) != -1) {
            zos.write(buffer, 0, byteRead);
        }
        
        zos.closeEntry();
        
        zos.close();
        fis.close();
        
        System.out.println("create ZIP: " + zipFile);
        
    }
    
    
    
}
