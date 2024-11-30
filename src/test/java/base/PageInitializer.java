package base;

import pages.LoginPage;
import pages.LoginPage2;

public class PageInitializer {
    public static LoginPage loginPage;
    public static LoginPage2 loginPage2;


    public static void initializePage() {
        loginPage = new LoginPage();
        loginPage2 = new LoginPage2();  // new approach, no PageFactory
    }

}
