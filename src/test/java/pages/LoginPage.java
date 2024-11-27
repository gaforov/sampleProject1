package pages;

import base.BrowserManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GlobalUtils;

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

}
