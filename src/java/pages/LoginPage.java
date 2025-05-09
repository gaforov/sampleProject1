package pages;

import org.openqa.selenium.By;
import utils.WaitUtils;

import static utils.ElementUtils.find;

public class LoginPage {

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By submitButton = By.id("submit");
    private final By loginSuccessMessage = By.className("post-title");
    private final By loginErrorMessage = By.id("error");
    private final By testLoginText = By.cssSelector("#login h2");


    public void loginToApplication(String username, String password) {
        find(usernameField).sendKeys(username);
        find(passwordField).sendKeys(password);
        find(submitButton).click();
    }

    public String getLoginSuccessMessageText() {
        return find(loginSuccessMessage).getText();
    }

    public String getLoginErrorMessageText() {
        WaitUtils.waitUntilVisible(loginErrorMessage);
        return find(loginErrorMessage).getText();
    }

    public String verifyLogOutSuccess() {
        return find(testLoginText).getText();
    }
}
