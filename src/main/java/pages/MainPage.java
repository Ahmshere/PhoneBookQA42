package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    @FindBy(xpath = "//a[contains(text(),'HOME')]")
    WebElement homeTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'ABOUT')]")
    WebElement aboutTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'LOGIN')]")
    WebElement loginTopMenuItem;

}
