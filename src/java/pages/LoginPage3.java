package pages;

import base.BasePage;
import org.openqa.selenium.By;

// Even further Improving Login2 by relocating some heavy-lifting to BasePage. This approach reduces boilerplate.

public class LoginPage3 extends BasePage {

    // Locators using By (replacing @FindBy)
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By submitButton = By.id("submit");
    private final By loginSuccessMessage = By.className("post-title");
    private final By loginErrorMessage = By.xpath("//div[@id='error']");
    private final By testLoginText = By.cssSelector("#login h2");


    // Methods to interact with the Login Page
    public SuccessLoginPage loginToApplication(String username, String password) {
        find(usernameField).sendKeys(username);
        find(passwordField).sendKeys(password);
        find(submitButton).click();
        return new SuccessLoginPage();
    }

    public String getLoginSuccessMessageText() {
        return find(loginSuccessMessage).getText();
    }

    public String getLoginErrorMessageText() {
        return find(loginErrorMessage).getText();
    }

    public String verifyLogOutSuccess() {
        return find(testLoginText).getText();
    }
}
