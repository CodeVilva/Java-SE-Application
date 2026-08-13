package com.vilva.securefilecrypt;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.SecretKey;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, Exception{

        Scanner scanner = new Scanner(System.in);
        
        FileService fileService = new FileService();
        
        System.out.println("=================================");
        System.out.println("     SECURE FILE CRYPT");
        System.out.println("=================================");

        while (true) {

            System.out.println();
            System.out.println("1. Encrypt File");
            System.out.println("2. Decrypt File");
            System.out.println("3. Exit");
            System.out.print("Choose an option: (1, 2, 3): ");
            

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    System.out.print("Enter file path: ");
                    String inputFile = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    String outputFile = inputFile + ".enc";

                    fileService.encryptFile(
                            inputFile,
                            outputFile,
                            password
                    );

                    break;
                    


                case "2":

                    System.out.print(
                            "Enter encrypted file path: "
                    );

                    String encryptedFile =
                            scanner.nextLine();

                    System.out.print(
                            "Enter password: "
                    );

                    String decryptPassword =
                            scanner.nextLine();

                    String outPutFile =
                            encryptedFile + ".decrypted";

                    fileService.decryptFile(
                            encryptedFile,
                            outPutFile,
                            decryptPassword
                    );

                    break;
                    
                case "3":
                    System.out.println("Exiting application...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}