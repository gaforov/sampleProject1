package pages;

import base.BrowserManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GlobalUtils;

/* Remember: Defining related locators and methods within their own page(s) is also known as:
    "single responsibility principle" (locators and actions are grouped logically).
*/
public class LoginPage {

    // web elements ( no need for dedicated PageFactory Class, this is more organized per page)
    @FindBy(id = "username")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "submit")
    public WebElement submitButton;

    @FindBy(className = "post-title")
    public WebElement loginSuccessMessage;

    @FindBy(xpath = "//div[@id='error']")
    public WebElement loginErrorMessage;

    public LoginPage() {
        PageFactory.initElements(BrowserManager.getDriver(), this);
    }



    // methods specific to this page (methods that interact in this page)

    public String getLoginErrorMessage(int timeoutInSeconds) {
        WebElement errorElement = GlobalUtils.waitForVisibility(loginErrorMessage, timeoutInSeconds);
        return errorElement.getText();
    }

    public void loginToApplication(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    /* Alternative Option. Group Actions into Fluent Methods
    For a simpler, more fluent API, you could chain actions for common flows.
    This is useful for improving readability in tests.*/

    public LoginPage enterUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void clickSubmit() {
        submitButton.click();
    }

    // Usage in Tests:
//    loginPage.enterUsername("testuser")
//            .enterPassword("password123")
//         .clickSubmit();


}
