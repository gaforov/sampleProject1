package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.GlobalUtils;
import utils.PropertiesUtil;

import java.time.Duration;

import static utils.SimpleLogger.log;

public class BrowserManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public WebDriver startBrowser() {
        if (driver.get() == null) { // Avoid reinitializing if a session is already active
            System.setProperty("webdriver.http.factory", "jdk-http-client"); // Applies to all browsers, Ensures compatibility with SeleniumManager as of Selenium 4.6.0 and later

            String browser = PropertiesUtil.getProperty("browser").toLowerCase();

            driver.set(
                    switch (browser) {
                        case "chrome" -> new ChromeDriver();
                        case "edge" -> new EdgeDriver();
                        default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
                    }
            );

            log("INFO", "Browser launched: " + browser.toUpperCase());
            driver.get().manage().window().maximize();
            driver.get().get(PropertiesUtil.getProperty("url"));
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        PageInitializer.initializePage();
        return driver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {
        if (driver.get() != null) {
            try {
                log("INFO", "Quitting the browser...");
                driver.get().quit();
                log("INFO", "Browser quit successfully.");
            } catch (Exception e) {
                log("ERROR", "Error while quitting the browser: " + e.getMessage());
            } finally {
                driver.remove(); // Thread-safe cleanup
            }
        } else {
            log("INFO", "No active browser session to quit.");
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
