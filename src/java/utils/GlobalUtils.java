package utils;

import base.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static base.BrowserManager.getDriver;

public class GlobalUtils {
    // Centralized method to create a WebDriverWait instance
    private static WebDriverWait createWait(int timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
    }

    // Wait for visibility of an element
    public static WebElement waitForVisibility(WebElement element, int timeoutInSeconds) {
        return createWait(timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for an element to be clickable
    public static WebElement waitForClickable(WebElement element, int timeoutInSeconds) {
        return createWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
    }

    // Switch to another window
    public static void switchToWindow(String windowTitle) {
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
            if (Objects.equals(getDriver().getTitle(), windowTitle)) {
                break;
            }
        }
    }

    public static void captureScreenshot(String testName) {
        if (getDriver() == null) {
            System.out.println("Driver is not initialized, cannot capture screenshot.");
            return;
        }

        // Clean up old screenshots before saving a new one
        cleanUpOldScreenshots();

        // Define screenshot file name and path
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String screenshotPath = "logs/screenshots/" + testName + "_" + timestamp + ".png";

        // Take and save the screenshot
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.createDir(new File("logs/screenshots"));
            FileHandler.copy(screenshot, new File(screenshotPath));
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    /**
     * Cleans up old screenshot files from the "logs/screenshots" directory.
     * <p>
     * This method ensures only the 20 most recent screenshot files are retained.
     * It identifies the files with a `.png` extension in the directory,
     * sorts them by last modified date (oldest first), and deletes any files
     * exceeding the most recent 20. Log messages indicate the success or failure
     * of each deletion operation.
     * <p>
     * If the directory does not exist or if there are 20 or fewer screenshots,
     * no action is taken.
     */
    public static void cleanUpOldScreenshots() {
        File screenshotsDir = new File("logs/screenshots");
        if (screenshotsDir.exists() && screenshotsDir.isDirectory()) {
            File[] screenshotFiles = screenshotsDir.listFiles((dir, name) -> name.endsWith(".png"));
            if (screenshotFiles != null && screenshotFiles.length > 20) {
                // Sort files by last modified date (oldest first)
                Arrays.sort(screenshotFiles, Comparator.comparingLong(File::lastModified));
                // Delete older files beyond the most recent 20
                for (int i = 0; i < screenshotFiles.length - 20; i++) {
                    if (screenshotFiles[i].delete()) {
                        System.out.println("Deleted old screenshot: " + screenshotFiles[i].getName());
                    } else {
                        System.out.println("Failed to delete screenshot: " + screenshotFiles[i].getName());
                    }
                }
            }
        }
    }

    public static WebElement findElement(By locator) {
        return BrowserManager.getDriver().findElement(locator);
    }

}
