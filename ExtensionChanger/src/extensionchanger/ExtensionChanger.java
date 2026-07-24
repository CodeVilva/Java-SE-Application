import java.io.File;

public class ExtensionChanger {

    // Folder containing the files
    private static final String FOLDER_PATH = "D:\\sample\\group16";

    // Extension to add
    private static final String NEW_EXTENSION = "jpg";

    public static void main(String[] args) {

        File folder = new File(FOLDER_PATH);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Error: Folder not found!");
            return;
        }

        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No files found.");
            return;
        }

        int renamed = 0;
        int skipped = 0;

        System.out.println("Adding ." + NEW_EXTENSION + " extension...\n");

        for (File file : files) {

            if (!file.isFile()) {
                continue;
            }

            String fileName = file.getName();

            // Skip files that already have an extension
            if (fileName.contains(".")) {
                System.out.println("[SKIPPED] " + fileName + " (already has an extension)");
                skipped++;
                continue;
            }

            String newFileName = fileName + "." + NEW_EXTENSION;

            File newFile = new File(folder, newFileName);

            if (newFile.exists()) {
                System.out.println("[SKIPPED] " + newFileName + " already exists.");
                skipped++;
                continue;
            }

            if (file.renameTo(newFile)) {
                System.out.println("[OK] " + fileName + " -> " + newFileName);
                renamed++;
            } else {
                System.out.println("[FAILED] " + fileName);
                skipped++;
            }
        }

        System.out.println("\n================================");
        System.out.println("Completed");
        System.out.println("================================");
        System.out.println("Files Renamed : " + renamed);
        System.out.println("Files Skipped : " + skipped);
    }
}