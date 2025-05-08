package utils;

import base.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElementUtils {

    public static WebElement find(By locator) {
        return BrowserManager.getDriver().findElement(locator);
    }

    public static void type(By locator, String text) {
        find(locator).sendKeys(text);
    }

    public static boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }
}
