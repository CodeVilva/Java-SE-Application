package com.vilva.securefilecrypt;


import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        
        FileService fileservice = new FileService();

        System.out.println("=================================");
        System.out.println("     SECURE FILE CRYPT");
        System.out.println("=================================");

        while (true) {

            System.out.println();
            System.out.println("1. Encrypt File");
            System.out.println("2. Decrypt File");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.println("Encrypt File selected.");
                    String encryptFile = scanner.nextLine();
                    fileservice.inspectFile(encryptFile);
                    
                    break;

                case "2":
                    System.out.println("Decrypt File selected.");
                    String decryptFile = scanner.nextLine();
                    fileservice.inspectFile(decryptFile);
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