package tests;

import base.BrowserManager;
import listeners.TestLoggerListener;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static base.PageInitializer.loginPage;
import static utils.GlobalUtils.waitForVisibility;

@Listeners(TestLoggerListener.class)
public class LoginTest extends BrowserManager {

    @Test
    public void testValidLogin() {
        String actualLoginMessage;
        String expectedLoginMessage = "Logged In Successfully";
        loginPage.loginToApplication("student", "Password123");
        actualLoginMessage = loginPage.loginSuccessMessage.getText();
        Assert.assertEquals(actualLoginMessage, expectedLoginMessage, "Incorrect Login Message.");
    }

    @Test
    public void testInvalidUsername() {
        String actualErrorMessage;
        String expectedErrorMessage = "Your username is invalid!";
        loginPage.loginToApplication("wrongUser", "Password123"); // Invalid username, valid password
        actualErrorMessage = loginPage.getLoginErrorMessage(5);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match for invalid username.");
    }

    @Test
    public void testInvalidPassword() throws InterruptedException {
        String actualErrorMessage;
        String expectedErrorMessage = "Your password is invalid!";
        loginPage.loginToApplication("student", "wrongPassword"); // Valid username, invalid password
        actualErrorMessage = loginPage.getLoginErrorMessage(5);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match for invalid password.");
    }

    @Test
    public void testSkip() {
        throw new SkipException("Skipping this test for demonstration purposes.");
    }
}
