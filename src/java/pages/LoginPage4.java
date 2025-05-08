package pages;

import base.BrowserManager;
import org.openqa.selenium.By;

import static utils.GlobalUtils.*;

// Even further Improving Login2 by relocating some heavy-lifting to BasePage. This approach reduces boilerplate.

public class LoginPage4 extends BrowserManager {

    // Locators using By (replacing @FindBy)
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By submitButton = By.id("submit");
    private final By loginSuccessMessage = By.className("post-title");
    private final By loginErrorMessage = By.xpath("//div[@id='error']");
    private final By testLoginText = By.cssSelector("#login h2");


    // Methods | Using void type | No need to return a new page object because PageInitializer take care of it.
    public void loginToApplication(String username, String password) {
        findElement(usernameField).sendKeys(username);
        findElement(passwordField).sendKeys(password);
        findElement(submitButton).click();
    }

    public String getLoginSuccessMessageText() {
        return findElement(loginSuccessMessage).getText();
    }

    public String getLoginErrorMessageText() {
        return findElement(loginErrorMessage).getText();
    }

    public String verifyLogOutSuccess() {
        return findElement(testLoginText).getText();
    }
}
