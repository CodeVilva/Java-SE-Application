package videoframeexporter;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        VIDEO FRAME EXPORTER");
        System.out.println("==========================================");

        System.out.println();
        System.out.println("This application extracts frames from");
        System.out.println("a video and saves them as JPG images.");
        System.out.println();

        // Check FFmpeg
        if (!VideoFrameExporter.isFFmpegAvailable()) {

            System.out.println("ERROR: FFmpeg was not found.");
            System.out.println();

            System.out.println("Please place ffmpeg.exe in:");
            System.out.println();

            System.out.println("ffmpeg\\ffmpeg.exe");
            System.out.println();

            System.out.println("or:");
            System.out.println();

            System.out.println("ffmpeg\\bin\\ffmpeg.exe");

            System.out.println();
            System.out.println("Application terminated.");

            return;
        }

        System.out.println("FFmpeg detected successfully.");
        System.out.println();

        // Get video
        File videoFile = getVideoFile();

        if (videoFile == null) {
            System.out.println("Invalid video file.");
            return;
        }

        // Get output directory
        File outputDirectory = getOutputDirectory();

        if (outputDirectory == null) {
            System.out.println("Invalid output directory.");
            return;
        }

        // Select extraction mode
        int mode = getExtractionMode();

        VideoFrameExporter exporter =
                new VideoFrameExporter(
                        videoFile,
                        outputDirectory
                );

        boolean success = false;

        if (mode == 1) {

            double seconds = getSeconds();

            success =
                    exporter.exportEveryNSeconds(seconds);

        } else if (mode == 2) {

            int frameInterval = getFrameInterval();

            success =
                    exporter.exportEveryNFrames(frameInterval);
        }

        System.out.println();

        if (success) {

            System.out.println("==========================================");
            System.out.println("        EXPORT COMPLETED");
            System.out.println("==========================================");

            System.out.println();
            System.out.println("Frames saved to:");

            System.out.println(
                    outputDirectory.getAbsolutePath()
            );

        } else {

            System.out.println("==========================================");
            System.out.println("        EXPORT FAILED");
            System.out.println("==========================================");
        }

        scanner.close();
    }

    private static File getVideoFile() {

        while (true) {

            System.out.print("Enter video file path: ");

            String path = scanner.nextLine().trim();

            path = removeQuotes(path);

            File videoFile = new File(path);

            if (!videoFile.exists()) {

                System.out.println("File does not exist.");
                System.out.println();

                continue;
            }

            if (!videoFile.isFile()) {

                System.out.println(
                        "The selected path is not a file."
                );

                System.out.println();

                continue;
            }

            if (!VideoFrameExporter.isVideoFile(videoFile)) {

                System.out.println(
                        "Unsupported video format."
                );

                System.out.println(
                        "Supported formats:"
                );

                System.out.println(
                        "MP4, MKV, AVI, MOV, WMV, FLV, WEBM"
                );

                System.out.println();

                continue;
            }

            return videoFile;
        }
    }

    private static File getOutputDirectory() {

        while (true) {

            System.out.print("Enter output folder path: ");

            String path = scanner.nextLine().trim();

            path = removeQuotes(path);

            File directory = new File(path);

            if (!directory.exists()) {

                System.out.println(
                        "Output folder does not exist."
                );

                System.out.print(
                        "Create it? (y/n): "
                );

                String choice =
                        scanner.nextLine().trim();

                if (choice.equalsIgnoreCase("y")) {

                    if (directory.mkdirs()) {

                        System.out.println(
                                "Output folder created."
                        );

                        return directory;

                    } else {

                        System.out.println(
                                "Could not create folder."
                        );
                    }

                }

                System.out.println();

                continue;
            }

            if (!directory.isDirectory()) {

                System.out.println(
                        "The selected path is not a directory."
                );

                System.out.println();

                continue;
            }

            return directory;
        }
    }

    private static int getExtractionMode() {

        while (true) {

            System.out.println();

            System.out.println(
                    "Select extraction mode:"
            );

            System.out.println(
                    "1. Extract every N seconds"
            );

            System.out.println(
                    "2. Extract every N frames"
            );

            System.out.println();

            System.out.print("Enter choice: ");

            String input =
                    scanner.nextLine().trim();

            try {

                int choice =
                        Integer.parseInt(input);

                if (choice == 1 || choice == 2) {

                    return choice;
                }

            } catch (NumberFormatException e) {

                // Invalid input
            }

            System.out.println(
                    "Please enter 1 or 2."
            );
        }
    }

    private static double getSeconds() {

        while (true) {

            System.out.print(
                    "Extract one frame every how many seconds? "
            );

            String input =
                    scanner.nextLine().trim();

            try {

                double seconds =
                        Double.parseDouble(input);

                if (seconds > 0) {

                    return seconds;
                }

            } catch (NumberFormatException e) {

                // Invalid input
            }

            System.out.println(
                    "Please enter a value greater than 0."
            );
        }
    }

    private static int getFrameInterval() {

        while (true) {

            System.out.print(
                    "Extract one frame every how many frames? "
            );

            String input =
                    scanner.nextLine().trim();

            try {

                int interval =
                        Integer.parseInt(input);

                if (interval > 0) {

                    return interval;
                }

            } catch (NumberFormatException e) {

                // Invalid input
            }

            System.out.println(
                    "Please enter an integer greater than 0."
            );
        }
    }

    private static String removeQuotes(String path) {

        if (path.startsWith("\"")
                && path.endsWith("\"")) {

            return path.substring(
                    1,
                    path.length() - 1
            );
        }

        return path;
    }
}