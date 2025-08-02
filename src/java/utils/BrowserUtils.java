package utils;

import base.BrowserManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import static base.BrowserManager.getDriver;
import static utils.FileUtils.cleanUpOldScreenshots;

public class BrowserUtils {

    private static WebDriver driver() {
        return BrowserManager.getDriver();
    }

    public static void refreshPage() {
        driver().navigate().refresh();
    }

    public static void goBack() {
        driver().navigate().back();
    }

    public static void goForward() {
        driver().navigate().forward();
    }

    public static void switchToNewTab() {
        Set<String> handles = driver().getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(handles);
        if (tabs.size() > 1) {
            driver().switchTo().window(tabs.get(1));
        }
    }

    public static void switchToTabByIndex(int index) {
        Set<String> handles = driver().getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(handles);
        if (index < tabs.size()) {
            driver().switchTo().window(tabs.get(index));
        } else {
            throw new IllegalArgumentException("Invalid tab index: " + index);
        }
    }

    public static void closeCurrentTab() {
        driver().close();
    }

    public static void switchToDefaultTab() {
        String firstHandle = driver().getWindowHandles().iterator().next();
        driver().switchTo().window(firstHandle);
    }

    public static void scrollToTop() {
        ((JavascriptExecutor) driver()).executeScript("window.scrollTo(0, 0);");
    }

    public static void scrollToBottom() {
        ((JavascriptExecutor) driver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void scrollByPixels(int pixels) {
        ((JavascriptExecutor) driver()).executeScript("window.scrollBy(0, arguments[0]);", pixels);
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
     * Launches Chrome with a specific user profile directory.
     * Useful for reusing a trusted session and bypassing MFA locally.
     *
     * @param userDataDir   Full path to Chrome's user data directory
     * @param profileName   Profile folder name (e.g., "Default", "Profile 1")
     * @return A configured ChromeDriver instance
     */
    public static WebDriver launchBrowserWithUserProfile(String userDataDir, String profileName) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + userDataDir);
        options.addArguments("profile-directory=" + profileName);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("✅ Launched Chrome with profile: " + profileName);
        return driver;
    }

}
