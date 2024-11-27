package utils;

import listeners.TestLoggerListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleLogger {
    //Simple logger that prints to console only.
//    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    public static void log(String level, String message) {
//        String timestamp = LocalDateTime.now().format(formatter);
//        System.out.println(timestamp + " " + level + ": " + message);
//    }

    // Advanced logger, exporting to external file.
    private static BufferedWriter writer;
    private static String logFileName;
    private static boolean anyTestFailed = false;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void initialize(String testClassName) {
        try {
            LocalDateTime now = LocalDateTime.now();
            logFileName = "logs/test-log_" + testClassName + "_" + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".txt";
            writer = new BufferedWriter(new FileWriter(new File(logFileName)));
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = timestamp + " " + level + ": " + message;

        try {
            if (writer != null) {
                writer.write(logEntry);
                writer.newLine();
            }
            System.out.println(logEntry); // Log to console
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }

    public static void setTestFailed(boolean testFailed) {
        anyTestFailed = anyTestFailed || testFailed;
    }

    public static void finalizeLogger() {
        try {
            if (writer != null) {
                writer.close();
                appendStatusToLogFileName();
            }
        } catch (IOException e) {
            System.err.println("Failed to close logger: " + e.getMessage());
        }
    }

    private static void appendStatusToLogFileName() {
        if (logFileName != null) {
            File oldFile = new File(logFileName);
            String newFileName = logFileName.replace(".txt", (anyTestFailed ? "_FAILED" : "_PASSED") + ".txt");
            File newFile = new File(newFileName);
            if (!oldFile.renameTo(newFile)) {
                System.err.println("Failed to rename log file.");
            }
        }
    }
}
