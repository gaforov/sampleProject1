package base;

import pages.*;
import pages.legacy.LoginPage_Fluent;
import pages.legacy.LoginPage_Manual_Locator;
import pages.legacy.LoginPage_PF;

public class PageInitializer {
    public static LoginPage_PF loginPagePF;
    public static LoginPage_Manual_Locator loginPage2;
    public static LoginPage_Fluent loginPageFluent;
    public static LoginPage loginPage;
    public static SuccessLoginPage successLoginPage;


    public static void initializePage() {
        loginPagePF = new LoginPage_PF();
        loginPage2 = new LoginPage_Manual_Locator();  // new approach, no PageFactory
        loginPageFluent = new LoginPage_Fluent();  // newest approach, no PageFactory, Fluent Wait
        loginPage = new LoginPage();  // newest approach, no PageFactory, PageInitializer (installed of Fluent Wait)
        successLoginPage = new SuccessLoginPage();
    }
}






