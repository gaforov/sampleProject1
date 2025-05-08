package utils;

import base.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);

    private static WebDriverWait getWait() {
        return new WebDriverWait(BrowserManager.getDriver(), DEFAULT_TIMEOUT);
    }

    public static WebElement waitUntilVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitUntilClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitUntilTextPresent(By locator, String text) {
        return getWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static boolean waitUntilUrlContains(String partialUrl) {
        return getWait().until(ExpectedConditions.urlContains(partialUrl));
    }

    public static boolean waitUntilTitleContains(String partialTitle) {
        return getWait().until(ExpectedConditions.titleContains(partialTitle));
    }
}
