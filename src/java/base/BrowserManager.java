package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserUtils;
import utils.GlobalUtils;
import utils.PropertiesUtil;

import java.time.Duration;

import static utils.SimpleLogger.log;

public class BrowserManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public WebDriver startBrowser() {
        if (driver.get() == null) { // Avoid reinitializing if a session is already active
            String browser = PropertiesUtil.getProperty("browser").toLowerCase();
            boolean isHeadless = Boolean.parseBoolean(PropertiesUtil.getProperty("headless"));

            // Use below code (uncomment) when to launch Chrome with a specific user profile. Purpose: with saved cookies to bypass MFA.
//            String userDataDir = "C:/Users/yourname/AppData/Local/Google/Chrome/User Data";
//            String profileName = "Profile 1";
//            driver.set(BrowserUtils.launchBrowserWithUserProfile(userDataDir, profileName));
//            driver.get().get(PropertiesUtil.getProperty("url"));

            driver.set(
                    switch (browser) {
                        case "chrome" -> {
                            ChromeOptions chromeOptions = new ChromeOptions();
                            if (isHeadless) chromeOptions.addArguments("---headless=new");
                            yield new ChromeDriver(chromeOptions);
                        }
                        case "edge" -> {
                            EdgeOptions edgeOptions = new EdgeOptions();
                            if (isHeadless) edgeOptions.addArguments("--headless=new");
                            yield new EdgeDriver(edgeOptions);
                        }
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
