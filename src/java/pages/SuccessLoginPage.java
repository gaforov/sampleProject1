package pages;

import base.BrowserManager;
import org.openqa.selenium.By;

public class SuccessLoginPage extends BrowserManager {
    // Locators
    private final By loginSuccessMessage = By.cssSelector("h1.post-title");
    private final By logoutButton = By.xpath("//a[contains(text(),'Log out')]");


    // Methods
    public String getLoginSuccessMessageText() {
        return getDriver().findElement(loginSuccessMessage).getText();
    }
    public void clickLogoutButton() {
        getDriver().findElement(logoutButton).click();
    }
}

