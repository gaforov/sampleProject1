package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertiesUtil;

import java.time.Duration;

import static utils.SimpleLogger.log;

public class BrowserManager {
    private static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public WebDriver startBrowser() {
        if (driver == null) { // Avoid reinitializing if a session is already active
            System.setProperty("webdriver.http.factory", "jdk-http-client"); // Applies to all browsers, Ensures compatibility with SeleniumManager as of Selenium 4.6.0 and later

            String browser = PropertiesUtil.getProperty("browser").toLowerCase();

            switch (browser) {
                case "chrome" -> driver = new ChromeDriver();
                case "edge" -> driver = new EdgeDriver();
                default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            log("INFO", "Browser launched: " + browser.toUpperCase());
            driver.manage().window().maximize();
            driver.get(PropertiesUtil.getProperty("url"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

//        PageInitializer.initializePage();
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {
        if (driver != null) {
            try {
                log("INFO", "Quitting the browser...");
                driver.quit();
                log("INFO", "Browser quit successfully.");
            } catch (Exception e) {
                log("ERROR", "Error while quitting the browser: " + e.getMessage());
            } finally {
                driver = null; // Reset for proper re-initialization
            }
        } else {
            log("INFO", "No active browser session to quit.");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
