package config;

import helpers.EmailGenerator;
import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return new Object[][]{
            {EmailGenerator.generateEmail(4,5,3),"fakepassword"},
            {"fakeUser2","fakepassword2"},
            {"fakeUser3","fakepassword3"},
        };
    }
}
