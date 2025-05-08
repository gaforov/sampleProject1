package tests.legacy;

import base.BrowserManager;
import listeners.TestLoggerListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static base.PageInitializer.loginPageFluent;
import static utils.PropertiesUtil.getProperty;

@Listeners(TestLoggerListener.class)
public class SuccessLoginTest_Fluent extends BrowserManager {

    @Test
    public void verifySuccessLoginMessage() {
        loginPageFluent.loginToApplication(getProperty("username"), getProperty("password"));
        String expectedMessage = "Logged In Successfully";
        String actualMessage = loginPageFluent.getLoginSuccessMessageText();
        Assert.assertEquals(actualMessage, expectedMessage, "Login success message does not match!");
//        assert actualMessage.equals(expectedMessage) : "Login success message does not match!"; // Java built-in assertion, not recommended for TestNG framework, but still works.
    }

    @Test
    public void testSuccessfulLogout() {
        var successLoginPage = loginPageFluent.loginToApplication(getProperty("username"), getProperty("password"));
        successLoginPage.clickLogoutButton();
        String expectedMessage = "Test login";
        String actualMessage = loginPageFluent.verifyLogOutSuccess();
        Assert.assertEquals(actualMessage, expectedMessage, "Logout was not successful!");
    }
}
