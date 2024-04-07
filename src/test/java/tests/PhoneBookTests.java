package tests;

import config.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTests extends BaseTest {

    @Test
    public void loginTestPositive() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickLoginButton();



    }
}
