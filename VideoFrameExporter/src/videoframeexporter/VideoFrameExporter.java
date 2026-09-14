package videoframeexporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideoFrameExporter {

    private final File videoFile;
    private final File outputDirectory;

    /*
     * FFmpeg executable location.
     */
    private static String ffmpegPath = null;

    public VideoFrameExporter(
            File videoFile,
            File outputDirectory) {

        this.videoFile = videoFile;
        this.outputDirectory = outputDirectory;
    }

    /**
     * Finds FFmpeg.
     *
     * Search order:
     *
     * 1. Project/ffmpeg/ffmpeg.exe
     * 2. Project/ffmpeg/bin/ffmpeg.exe
     * 3. System PATH
     */
    private static String findFFmpeg() {

    System.out.println("Searching for FFmpeg...");

    /*
     * Your FFmpeg installation
     */
    File installedFFmpeg = new File(
            "D:\\git repos\\Java-Web-Applicaions\\VideoFrameExporter\\bin\\ffmpeg.exe"
    );

    System.out.println();
    System.out.println("Checking:");
    System.out.println(installedFFmpeg.getAbsolutePath());

    if (installedFFmpeg.exists()
            && installedFFmpeg.isFile()) {

        System.out.println();
        System.out.println("FFmpeg found!");
        System.out.println(
                installedFFmpeg.getAbsolutePath()
        );

        return installedFFmpeg.getAbsolutePath();
    }

    /*
     * Project directory
     */
    String workingDirectory =
            System.getProperty("user.dir");

    File workingDir =
            new File(workingDirectory);

    System.out.println();
    System.out.println("Working directory:");
    System.out.println(
            workingDir.getAbsolutePath()
    );

    /*
     * Project/ffmpeg/ffmpeg.exe
     */
    File localFFmpeg = new File(
            workingDir,
            "ffmpeg"
                    + File.separator
                    + "ffmpeg.exe"
    );

    if (localFFmpeg.exists()
            && localFFmpeg.isFile()) {

        System.out.println();
        System.out.println("FFmpeg found!");
        System.out.println(
                localFFmpeg.getAbsolutePath()
        );

        return localFFmpeg.getAbsolutePath();
    }

    /*
     * Project/ffmpeg/bin/ffmpeg.exe
     */
    File localBinFFmpeg = new File(
            workingDir,
            "ffmpeg"
                    + File.separator
                    + "bin"
                    + File.separator
                    + "ffmpeg.exe"
    );

    if (localBinFFmpeg.exists()
            && localBinFFmpeg.isFile()) {

        System.out.println();
        System.out.println("FFmpeg found!");
        System.out.println(
                localBinFFmpeg.getAbsolutePath()
        );

        return localBinFFmpeg.getAbsolutePath();
    }

    /*
     * C:\ffmpeg\ffmpeg.exe
     */
    File cDriveFFmpeg = new File(
            "C:\\ffmpeg\\ffmpeg.exe"
    );

    if (cDriveFFmpeg.exists()
            && cDriveFFmpeg.isFile()) {

        System.out.println();
        System.out.println("FFmpeg found!");
        System.out.println(
                cDriveFFmpeg.getAbsolutePath()
        );

        return cDriveFFmpeg.getAbsolutePath();
    }

    /*
     * C:\ffmpeg\bin\ffmpeg.exe
     */
    File cDriveBinFFmpeg = new File(
            "C:\\ffmpeg\\bin\\ffmpeg.exe"
    );

    if (cDriveBinFFmpeg.exists()
            && cDriveBinFFmpeg.isFile()) {

        System.out.println();
        System.out.println("FFmpeg found!");
        System.out.println(
                cDriveBinFFmpeg.getAbsolutePath()
        );

        return cDriveBinFFmpeg.getAbsolutePath();
    }

    /*
     * Check system PATH
     */
    System.out.println();
    System.out.println(
            "Checking system PATH..."
    );

    try {

        ProcessBuilder processBuilder =
                new ProcessBuilder(
                        "ffmpeg",
                        "-version"
                );

        processBuilder.redirectErrorStream(true);

        Process process =
                processBuilder.start();

        int exitCode =
                process.waitFor();

        if (exitCode == 0) {

            System.out.println(
                    "FFmpeg found in system PATH."
            );

            return "ffmpeg";
        }

    } catch (IOException e) {

        // FFmpeg not found in PATH

    } catch (InterruptedException e) {

        Thread.currentThread().interrupt();
    }

    return null;
}

    /**
     * Checks whether FFmpeg is available.
     */
    public static boolean isFFmpegAvailable() {

        ffmpegPath = findFFmpeg();

        try {

            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            ffmpegPath,
                            "-version"
                    );

            processBuilder.redirectErrorStream(true);

            Process process =
                    processBuilder.start();

            int exitCode =
                    process.waitFor();

            return exitCode == 0;

        } catch (IOException e) {

            return false;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            return false;
        }
    }

    /**
     * Checks supported video formats.
     */
    public static boolean isVideoFile(File file) {

        String name =
                file.getName().toLowerCase();

        return name.endsWith(".mp4")
                || name.endsWith(".mkv")
                || name.endsWith(".avi")
                || name.endsWith(".mov")
                || name.endsWith(".wmv")
                || name.endsWith(".flv")
                || name.endsWith(".webm")
                || name.endsWith(".m4v")
                || name.endsWith(".mpeg")
                || name.endsWith(".mpg");
    }

    /**
     * Extracts one frame every N seconds.
     */
    public boolean exportEveryNSeconds(
            double seconds) {

        System.out.println();

        System.out.println(
                "Starting frame extraction..."
        );

        System.out.println(
                "Mode: Every "
                        + seconds
                        + " seconds"
        );

        System.out.println();

        String outputPattern =
                new File(
                        outputDirectory,
                        "frame_%06d.jpg"
                ).getAbsolutePath();

        ProcessBuilder processBuilder =
                new ProcessBuilder(
                        ffmpegPath,

                        "-i",
                        videoFile.getAbsolutePath(),

                        "-vf",
                        "fps=30/" + seconds,

                        "-q:v",
                        "2",

                        outputPattern
                );

        return executeFFmpeg(processBuilder);
    }

    /**
     * Extracts one frame every N frames.
     */
    public boolean exportEveryNFrames(
            int frameInterval) {

        System.out.println();

        System.out.println(
                "Starting frame extraction..."
        );

        System.out.println(
                "Mode: Every "
                        + frameInterval
                        + " frames"
        );

        System.out.println();

        String outputPattern =
                new File(
                        outputDirectory,
                        "frame_%06d.jpg"
                ).getAbsolutePath();

        String videoFilter =
                "select='not(mod(n\\,"
                        + frameInterval
                        + "))'";

        ProcessBuilder processBuilder =
                new ProcessBuilder(
                        ffmpegPath,

                        "-i",
                        videoFile.getAbsolutePath(),

                        "-vf",
                        videoFilter,

                        "-vsync",
                        "vfr",

                        "-q:v",
                        "2",

                        outputPattern
                );

        return executeFFmpeg(processBuilder);
    }

    /**
     * Executes FFmpeg.
     */
    private boolean executeFFmpeg(
            ProcessBuilder processBuilder) {

        try {

            processBuilder.redirectErrorStream(true);

            Process process =
                    processBuilder.start();

            printProcessOutput(process);

            int exitCode =
                    process.waitFor();

            System.out.println();

            System.out.println(
                    "FFmpeg exit code: "
                            + exitCode
            );

            return exitCode == 0;

        } catch (IOException e) {

            System.out.println();

            System.out.println(
                    "Could not start FFmpeg."
            );

            System.out.println(
                    "Error: "
                            + e.getMessage()
            );

            return false;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println();

            System.out.println(
                    "Frame extraction interrupted."
            );

            return false;
        }
    }

    /**
     * Displays FFmpeg output.
     */
    private void printProcessOutput(
            Process process)
            throws IOException {

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                process.getInputStream()
                        )
                );

        String line;

        while ((line = reader.readLine())
                != null) {

            System.out.println(line);
        }

        reader.close();
    }
}