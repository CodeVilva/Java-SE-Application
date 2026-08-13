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
import javax.crypto.SecretKey;

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
    public void readFile(String filePath){
        
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
        byte[] data = Files.readAllBytes(path);
        
        System.out.println("File syccessfuly read!");
        System.out.println("Number of bytes :" + data.length);
        } catch(IOException e){
        System.out.println("Uable to read file");
        System.out.println("Error Messsage" + e.getMessage());
        }
    };
    
    public void encryptFile(
            String inputFile,
            String outputFile,
            String password) {

        Path inputPath = Paths.get(inputFile);

        try {

            if (!Files.exists(inputPath)) {
                System.out.println("Input file does not exist.");
                return;
            }

            if (!Files.isRegularFile(inputPath)) {
                System.out.println("Input path is not a regular file.");
                return;
            }

            byte[] plaintext =
                    Files.readAllBytes(inputPath);

            CryptoService cryptoService =
                    new CryptoService();

            byte[] salt =
                    cryptoService.generateSalt();

            byte[] iv =
                    cryptoService.generateIV();

            SecretKey key =
                    cryptoService.deriveKey(
                            password,
                            salt
                    );

            byte[] ciphertext =
                    cryptoService.encrypt(
                            plaintext,
                            key,
                            iv
                    );

            EncryptedFileFormat encryptedFile =
                    new EncryptedFileFormat(
                            salt,
                            iv,
                            ciphertext
                    );

            EncryptedFileWriter writer =
                    new EncryptedFileWriter();

            writer.write(
                    outputFile,
                    encryptedFile
            );

            System.out.println(
                    "File encrypted successfully."
            );

            System.out.println(
                    "Output: " +
                    Paths.get(outputFile).toAbsolutePath()
            );

        } catch (Exception e) {

            System.out.println(
                    "Encryption failed."
            );

            System.out.println(
                    "Reason: " +
                    e.getMessage()
            );
        }
    }

    public void decryptFile(
            String inputFile,
            String outputFile,
            String password) {

        try {

            Path inputPath =
                    Paths.get(inputFile);

            if (!Files.exists(inputPath)) {

                System.out.println(
                        "Encrypted file does not exist."
                );

                return;
            }

            if (!Files.isRegularFile(inputPath)) {

                System.out.println(
                        "Input path is not a regular file."
                );

                return;
            }

            EncryptedFileReader reader =
                    new EncryptedFileReader();

            EncryptedFileFormat encryptedFile =
                    reader.read(inputFile);

            byte[] salt =
                    encryptedFile.getSalt();

            byte[] iv =
                    encryptedFile.getIv();

            byte[] ciphertext =
                    encryptedFile.getCiphertext();

            CryptoService cryptoService =
                    new CryptoService();

            SecretKey key =
                    cryptoService.deriveKey(
                            password,
                            salt
                    );

            byte[] plaintext =
                    cryptoService.decrypt(
                            ciphertext,
                            key,
                            iv
                    );

            Files.write(
                    Paths.get(outputFile),
                    plaintext
            );

            System.out.println(
                    "File decrypted successfully."
            );

            System.out.println(
                    "Output: " +
                    Paths.get(outputFile)
                            .toAbsolutePath()
            );

        } catch (Exception e) {

            System.out.println(
                    "Decryption failed."
            );

            System.out.println(
                    "Reason: " +
                    e.getMessage()
            );
        }
    }
    
}
