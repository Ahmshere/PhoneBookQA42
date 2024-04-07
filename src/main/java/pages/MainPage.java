package pages;

import config.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class MainPage extends BasePage{

    @FindBy(xpath = "//a[contains(text(),'HOME')]")
    WebElement homeTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'ABOUT')]")
    WebElement aboutTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'LOGIN')]")
    WebElement loginTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'CONTACTS')]")
    WebElement contactsTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'ADD')]")
    WebElement addTopMenuItem;

public MainPage(WebDriver driver){
    setDriver(driver);
    driver.get("https://telranedu.web.app/home");
    PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
}

public LoginPage clickLoginButton(){
    loginTopMenuItem.click();
    return new LoginPage(driver);
}


}
