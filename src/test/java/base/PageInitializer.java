package base;

import pages.LoginPage;

public class PageInitializer {
    public static LoginPage loginPage;


    public static void initializePage() {
        loginPage = new LoginPage();
    }

}
