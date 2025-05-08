package utils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileUtils {

    /**
     * Default cleanup method that reads max file limit from config.properties
     */
    public static void cleanUpOldScreenshots() {
        int maxFiles = PropertiesUtil.getIntProperty("max.screenshots.retained", 20);
        cleanUpOldScreenshots(maxFiles);
    }

    /**
     * Cleanup method that accepts max file count as argument
     */
    public static void cleanUpOldScreenshots(int maxFilesToKeep) {
        File screenshotsDir = new File("logs/screenshots");
        if (screenshotsDir.exists() && screenshotsDir.isDirectory()) {
            File[] screenshotFiles = screenshotsDir.listFiles((dir, name) -> name.endsWith(".png"));
            if (screenshotFiles != null && screenshotFiles.length > maxFilesToKeep) {
                Arrays.sort(screenshotFiles, Comparator.comparingLong(File::lastModified));
                for (int i = 0; i < screenshotFiles.length - maxFilesToKeep; i++) {
                    if (screenshotFiles[i].delete()) {
                        System.out.println("🧹 Deleted old screenshot: " + screenshotFiles[i].getName());
                    }
                }
            }
        }
    }
}
