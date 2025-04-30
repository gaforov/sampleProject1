package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SimpleLogger {
//    Simple logger that prints to console only:

//    Private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    public static void log(String level, String message) {
//        String timestamp = LocalDateTime.now().format(formatter);
//        System.out.println(timestamp + " " + level + ": " + message);
//    }

    // Advanced logger, exporting to an external file.
    private static final String LOG_DIRECTORY = "logs";
    private static final int MAX_LOG_FILES = 50; // todo: Keep the most recent N log files
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    private static BufferedWriter writer;
    private static String logFileName; // Current log file name

    public static void initialize(String testClassName) {
        try {
            File logDir = new File(LOG_DIRECTORY);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // Create log file without "IN_PROGRESS" suffix
            logFileName = String.format("test-log_%s_%s.txt", testClassName,
                    LocalDateTime.now().format(formatter));

            File logFile = new File(logDir, logFileName);
            writer = new BufferedWriter(new FileWriter(logFile));

            // Clean up old log files
            cleanUpOldLogs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String level, String message) {
        if (writer == null) {
            throw new IllegalStateException("Logger is not initialized. Call initialize() first.");
        }
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String logMessage = timestamp + " " + level + ": " + message;
            System.out.println(logMessage); // Print to console
            writer.write(logMessage);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void finalizeLogger(String finalStatus) {
        if (writer != null) {
            try {
                writer.close();

                // Rename log file to include the final status
                if (!logFileName.contains(finalStatus)) {
                    File oldFile = new File(LOG_DIRECTORY + "/" + logFileName);
                    String newLogFileName = logFileName.replace(".txt", "_" + finalStatus + ".txt");
                    File newFile = new File(LOG_DIRECTORY + "/" + newLogFileName);
                    if (oldFile.renameTo(newFile)) {
                        logFileName = newLogFileName; // Update the logFileName to the new name
                    } else {
                        System.err.println("Failed to rename log file to include final status.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void cleanUpOldLogs() {
        File logDir = new File(LOG_DIRECTORY);
        if (logDir.exists()) {
            File[] logFiles = logDir.listFiles((dir, name) -> name.endsWith(".txt"));
            if (logFiles != null && logFiles.length > MAX_LOG_FILES) {
                Arrays.sort(logFiles, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));
                for (int i = MAX_LOG_FILES; i < logFiles.length; i++) {
                    try {
                        Files.delete(logFiles[i].toPath());
                    } catch (IOException e) {
                        System.err.println("Failed to delete old log file: " + logFiles[i].getName());
                    }
                }
            }
        }
    }
}
