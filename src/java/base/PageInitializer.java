package base;

import pages.LoginPage;
import pages.LoginPage2;
import pages.LoginPage3;
import pages.SuccessLoginPage;

public class PageInitializer {
    public static LoginPage loginPage;
    public static LoginPage2 loginPage2;
    public static LoginPage3 loginPage3;
    public static SuccessLoginPage successLoginPage;


    public static void initializePage() {
        loginPage = new LoginPage();
        loginPage2 = new LoginPage2();  // new approach, no PageFactory
        loginPage3 = new LoginPage3();  // newest approach, no PageFactory, boilerplate reduced by moving some logic to BasePage
        successLoginPage = new SuccessLoginPage();
    }
}






