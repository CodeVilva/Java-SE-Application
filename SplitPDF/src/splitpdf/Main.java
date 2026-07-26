/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package splitpdf;

/**
 *
 * @author vilva
 */
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("          PDF SPLITTER UTILITY");
        System.out.println("======================================");

        System.out.print("Enter PDF file path: ");
        String inputPath = scanner.nextLine().trim();

        File inputFile = new File(inputPath);

        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("\nError: PDF file not found.");
            scanner.close();
            return;
        }

        if (!inputFile.getName().toLowerCase().endsWith(".pdf")) {
            System.out.println("\nError: Please provide a PDF file.");
            scanner.close();
            return;
        }

        try {

            int totalPages = PDFSplitter.getPageCount(inputPath);

            System.out.println("\nPDF loaded successfully.");
            System.out.println("Total pages: " + totalPages);

            System.out.print("\nEnter output directory: ");
            String outputDirectory = scanner.nextLine().trim();

            File outputFolder = new File(outputDirectory);

            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }

            System.out.print("\nHow many parts do you want? ");
            int parts = Integer.parseInt(scanner.nextLine());

            if (parts <= 0) {
                System.out.println(
                        "Error: Number of parts must be greater than 0."
                );
                scanner.close();
                return;
            }

            for (int i = 1; i <= parts; i++) {

                System.out.println("\n--- Part " + i + " ---");

                System.out.print("Start page: ");
                int startPage = Integer.parseInt(scanner.nextLine());

                System.out.print("End page: ");
                int endPage = Integer.parseInt(scanner.nextLine());

                if (startPage < 1
                        || endPage > totalPages
                        || startPage > endPage) {

                    System.out.println("Invalid page range.");
                    i--;
                    continue;
                }

                PDFSplitter.splitPDF(
                        inputPath,
                        outputDirectory,
                        startPage,
                        endPage,
                        i
                );
            }

            System.out.println("\n======================================");
            System.out.println("       PDF SPLITTING COMPLETED");
            System.out.println("======================================");
            System.out.println(
                    "Output: " + outputFolder.getAbsolutePath()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "\nError: Please enter valid numbers."
            );

        } catch (Exception e) {

            System.out.println(
                    "\nError while processing PDF:"
            );

            System.out.println(e.getMessage());

        } finally {

            scanner.close();
        }
    }
}
