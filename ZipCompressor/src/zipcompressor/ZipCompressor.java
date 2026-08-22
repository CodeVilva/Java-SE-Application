package zipcompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipCompressor {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("          ZIP COMPRESSOR");
            System.out.println("================================");
            System.out.println("1. Compress to ZIP File");
            System.out.println("2. Extract ZIP File");
            System.out.println("3. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            try {

                switch (choice) {

                    case "1" -> {

                        System.out.println();
                        System.out.println("================================");
                        System.out.println("       COMPRESSOR SELECTED");
                        System.out.println("================================");

                        System.out.print("Enter file/folder path to compress: ");
                        String sourceFile = scan.nextLine();

                        System.out.print("Enter output ZIP file path: ");
                        String zipFile = scan.nextLine();

                        System.out.println("================================");

                        fileCompressor(sourceFile, zipFile);
                    }

                    case "2" -> {

                        System.out.println();
                        System.out.println("================================");
                        System.out.println("        EXTRACTOR SELECTED");
                        System.out.println("================================");

                        System.out.print("Enter ZIP file path: ");
                        String zipFile = scan.nextLine();

                        System.out.print("Enter output/extraction folder: ");
                        String outputFolder = scan.nextLine();

                        System.out.println("================================");

                        extractZip(zipFile, outputFolder);
                    }

                    case "3" -> {

                        System.out.println();
                        System.out.println("Exiting ZIP Compressor...");
                        scan.close();
                        return;
                    }

                    default -> {

                        System.out.println();
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    }
                }

            } catch (IOException e) {

                System.out.println();
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    public static void addToZip(
            File file,
            String entryName,
            ZipOutputStream zos
    ) throws IOException {

        if (file.isDirectory()) {

            File[] files = file.listFiles();

            if (files == null) {
                return;
            }

            for (File child : files) {

                String childEntryName =
                        entryName + "/" + child.getName();

                addToZip(child, childEntryName, zos);
            }

        } else {

            FileInputStream fis = new FileInputStream(file);

            ZipEntry entry = new ZipEntry(entryName);

            zos.putNextEntry(entry);

            byte[] buffer = new byte[4096];

            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {

                zos.write(buffer, 0, bytesRead);
            }

            zos.closeEntry();

            fis.close();
        }
    }

    private static void fileCompressor(
            String sourceFile,
            String zipFile
    ) throws IOException {

        File source = new File(sourceFile);

        if (!source.exists()) {

            throw new IOException(
                    "Source file/folder does not exist: "
                    + sourceFile
            );
        }

        FileOutputStream fos = new FileOutputStream(zipFile);

        ZipOutputStream zos = new ZipOutputStream(fos);

        addToZip(
                source,
                source.getName(),
                zos
        );

        zos.close();
        fos.close();

        System.out.println();
        System.out.println("Compression completed!");
        System.out.println("Created ZIP: " + zipFile);
    }

    public static void extractZip(
            String zipFile,
            String outputFolder
    ) throws IOException {

        File zip = new File(zipFile);

        if (!zip.exists()) {

            throw new IOException(
                    "ZIP file does not exist: "
                    + zipFile
            );
        }

        File destination = new File(outputFolder);

        if (!destination.exists()) {

            if (!destination.mkdirs()) {

                throw new IOException(
                        "Could not create extraction folder: "
                        + outputFolder
                );
            }
        }

        FileInputStream fis = new FileInputStream(zip);

        ZipInputStream zis = new ZipInputStream(fis);

        ZipEntry entry;

        byte[] buffer = new byte[4096];

        while ((entry = zis.getNextEntry()) != null) {

            File outputFile =
                    new File(destination, entry.getName());

            if (entry.isDirectory()) {

                outputFile.mkdirs();

            } else {

                File parent = outputFile.getParentFile();

                if (parent != null && !parent.exists()) {

                    parent.mkdirs();
                }

                FileOutputStream fos =
                        new FileOutputStream(outputFile);

                int bytesRead;

                while ((bytesRead = zis.read(buffer)) != -1) {

                    fos.write(buffer, 0, bytesRead);
                }

                fos.close();
            }

            zis.closeEntry();
        }

        zis.close();
        fis.close();

        System.out.println();
        System.out.println("Extraction completed!");
        System.out.println("Extracted to: " + outputFolder);
    }
}