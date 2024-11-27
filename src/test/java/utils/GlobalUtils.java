package utils;

import base.BrowserManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class GlobalUtils extends BrowserManager {
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
}
