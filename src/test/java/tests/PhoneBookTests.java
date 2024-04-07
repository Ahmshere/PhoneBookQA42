package tests;

import config.BaseTest;
import org.testng.annotations.Test;
import pages.MainPage;

public class PhoneBookTests extends BaseTest {

    @Test
    public void loginTestPositive(){
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickLoginButton();

    }
}
