package tests;

import base.BrowserManager;
import listeners.TestLoggerListener;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static base.PageInitializer.*;

@Listeners(TestLoggerListener.class)
public class LoginTest extends BrowserManager {

    @Test
    public void testValidLogin() {
        String expectedMessage = "Logged In Successfully";

        loginPage.loginToApplication("student", "Password123");
        String actualMessage = loginPage.getLoginSuccessMessageText();

        Assert.assertEquals(actualMessage, expectedMessage, "Login success message mismatch.");
    }

    @Test
    public void testInvalidUsername() {
        String expectedError = "Your username is invalid!";

        loginPage.loginToApplication("invalidUser", "Password123");
        String actualError = loginPage.getLoginErrorMessageText();

        Assert.assertEquals(actualError, expectedError, "Invalid username error mismatch.");
    }

    @Test
    public void testInvalidPassword() {
        String expectedError = "Your password is invalid!";

        loginPage.loginToApplication("student", "invalidPassword");
        String actualError = loginPage.getLoginErrorMessageText();

        Assert.assertEquals(actualError, expectedError, "Invalid password error mismatch.");
    }

    @Test
    public void testSkip() {
        throw new SkipException("Skipping this test for demonstration.");
    }
}
