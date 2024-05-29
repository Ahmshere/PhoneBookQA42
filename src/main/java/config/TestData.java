package config;

import helpers.EmailGenerator;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;

public class TestData {
    @Step("Using a data provider")
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {EmailGenerator.generateEmail(4, 5, 3), "fakepassword"},
                {"fakeUser2", "fakepassword2"},
                {"fakeUser3", "fakepassword3"},
        };
    }
}
