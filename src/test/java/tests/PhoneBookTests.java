package tests;

import config.BaseTest;
import helpers.AlertHandler;
import helpers.TopMenuItem;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTests extends BaseTest {

    @Test
    public void loginTestPositive() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage.fillEmailField("kjdgljf@mail.com").clickByRegistrationButton();
        String expectedAlertText = "Wrong";
        boolean isAlertHandled = AlertHandler.handleAlert(alert, expectedAlertText);
        Assert.assertTrue(isAlertHandled);
        Thread.sleep(3000);
    }

    @Test
    public void registrationOfAnAlreadyRegisteredUser()  {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickByRegistrationButton();
        String expectedAlertText = "User already exist";
        boolean result = AlertHandler.handleAlert(alert, expectedAlertText);
        Assert.assertTrue(result);

    }
    @Test
    public void successfulLogin(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickByLoginButton();

    }
}
