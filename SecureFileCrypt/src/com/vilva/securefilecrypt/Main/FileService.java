/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vilva.securefilecrypt;

/**
 *
 * @author vilva
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {
    public void inspectFile(String filePath){
        
        Path path = Paths.get(filePath);
        
        if(!Files.exists(path)){
            System.out.println("File does not Exists!");
            return;
        }
        if(!Files.isRegularFile(path)){
            System.out.println("The File is not a Regular File!");
            return;
        }
        try{
        System.out.println();
        System.out.println("File found!");
        System.out.println("File name : " + path.getFileName());
        System.out.println("File size : " + Files.size(path) + " bytes");
        System.out.println("File path : " + path.toAbsolutePath());
        } catch(IOException e){
            System.out.println("Unable to access the file!");
            System.out.println("Message: " + e.getMessage());
        }
    }
}
