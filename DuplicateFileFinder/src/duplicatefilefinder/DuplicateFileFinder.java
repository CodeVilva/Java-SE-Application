package duplicatefilefinder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DuplicateFileFinder {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("         DUPLICATE FILE FINDER");
        System.out.println("========================================");

        System.out.print("\nEnter folder path: ");
        String folderPath = scanner.nextLine();

        File folder = new File(folderPath);

        // Check whether the path exists
        if (!folder.exists()) {
            System.out.println("\nError: Folder does not exist.");
            return;
        }

        // Check whether the path is actually a directory
        if (!folder.isDirectory()) {
            System.out.println("\nError: The path is not a folder.");
            return;
        }

        System.out.println("\nScanning...");
        System.out.println("----------------------------------------");

        List<File> files = new ArrayList<>();

        scanDirectory(folder, files);

        System.out.println("Files found: " + files.size());

        Map<Long, List<File>> filesBySize = groupBySize(files);

        Map<String, List<File>> duplicateGroups = new HashMap<>();

        for (List<File> sameSizeFiles : filesBySize.values()) {

            // Only files with the same size can be duplicates
            if (sameSizeFiles.size() < 2) {
                continue;
            }

            for (File file : sameSizeFiles) {

                try {
                    String hash = calculateHash(file);

                    duplicateGroups
                            .computeIfAbsent(hash, k -> new ArrayList<>())
                            .add(file);

                } catch (IOException | NoSuchAlgorithmException e) {

                    System.out.println(
                            "Could not read: " + file.getAbsolutePath()
                    );
                }
            }
        }

        displayDuplicates(duplicateGroups);

        scanner.close();
    }

    // --------------------------------------------------
    // Scan folder and subfolders
    // --------------------------------------------------

    public static void scanDirectory(File directory, List<File> files) {

        File[] contents = directory.listFiles();

        if (contents == null) {
            return;
        }

        for (File file : contents) {

            if (file.isDirectory()) {

                // Recursively scan subfolder
                scanDirectory(file, files);

            } else if (file.isFile()) {

                files.add(file);
            }
        }
    }

    // --------------------------------------------------
    // Group files according to their size
    // --------------------------------------------------

    public static Map<Long, List<File>> groupBySize(List<File> files) {

        Map<Long, List<File>> filesBySize = new HashMap<>();

        for (File file : files) {

            long size = file.length();

            filesBySize
                    .computeIfAbsent(size, k -> new ArrayList<>())
                    .add(file);
        }

        return filesBySize;
    }

    // --------------------------------------------------
    // Calculate SHA-256 hash
    // --------------------------------------------------

    public static String calculateHash(File file)
            throws IOException, NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (FileInputStream input = new FileInputStream(file)) {

            byte[] buffer = new byte[8192];

            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {

                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] hashBytes = digest.digest();

        StringBuilder hash = new StringBuilder();

        for (byte b : hashBytes) {

            hash.append(String.format("%02x", b));
        }

        return hash.toString();
    }

    // --------------------------------------------------
    // Display duplicate files
    // --------------------------------------------------

    public static void displayDuplicates(
            Map<String, List<File>> duplicateGroups) {

        System.out.println("\n========================================");
        System.out.println("          DUPLICATE FILES");
        System.out.println("========================================");

        int groupNumber = 0;
        int duplicateCount = 0;

        for (Map.Entry<String, List<File>> entry
                : duplicateGroups.entrySet()) {

            List<File> files = entry.getValue();

            // A hash appearing only once is not a duplicate
            if (files.size() < 2) {
                continue;
            }

            groupNumber++;

            System.out.println("\n----------------------------------------");
            System.out.println("Duplicate Group " + groupNumber);
            System.out.println("----------------------------------------");

            System.out.println("SHA-256: " + entry.getKey());

            for (File file : files) {

                System.out.println(file.getAbsolutePath());

                duplicateCount++;
            }
        }

        if (groupNumber == 0) {

            System.out.println("\nNo duplicate files found.");

        } else {

            System.out.println("\n========================================");
            System.out.println("Duplicate groups : " + groupNumber);
            System.out.println("Duplicate files  : " + duplicateCount);
            System.out.println("========================================");
        }
    }
}

