package pages;

import base.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
Why this Approach (without PageFactory/FindBy) Is Better:

   • Projects where the UI is dynamic, with frequent DOM updates or asynchronous loading.
   • Teams that prefer explicit control over element initialization.
   • Modern frameworks aligned with Selenium’s best practices.
   • Testers who want to avoid potential issues with PageFactory or work on a framework designed to last for the long term.


More in-details explanation:

Lazy Initialization:
PageFactory initializes elements only when they are first accessed.
If the DOM changes after the page object is created (e.g., dynamic content, JavaScript updates), the @FindBy locator might not find the element correctly or fail altogether.

StaleElementReferenceException:
If the DOM changes or the page is reloaded, previously located elements may become stale.
Since PageFactory stores WebElement references, it doesn’t automatically re-locate the element.

Less Control Over Timing:
PageFactory doesn’t allow you to handle explicit waits directly when locating elements, making it harder to work with dynamically loaded content.
*/
public class LoginPage2 {
    private final WebDriver driver;

    public LoginPage2() {
        this.driver = BrowserManager.getDriver();
    }

    // Locators using By (replacing @FindBy)
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By submitButton = By.id("submit");
    private final By loginSuccessMessage = By.className("post-title");
    private final By loginErrorMessage = By.xpath("//div[@id='error']");

    // Methods to interact with the Login Page

    public WebElement getUsernameField() {
        return driver.findElement(usernameField);
    }

    public WebElement getPasswordField() {
        return driver.findElement(passwordField);
    }

    public WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }

    public WebElement getLoginSuccessMessage() {
        return driver.findElement(loginSuccessMessage);
    }

    public WebElement getLoginErrorMessage() {
        return driver.findElement(loginErrorMessage);
    }

    public void loginToApplication(String username, String password) {
        getUsernameField().sendKeys(username);
        getPasswordField().sendKeys(password);
        getSubmitButton().click();
    }

    public String getLoginErrorMessageText() {
        return getLoginErrorMessage().getText();
    }
}
