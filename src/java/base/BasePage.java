package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage extends BrowserManager {
    protected WebElement find(By locator) {
        return getDriver().findElement(locator);
    }

}
