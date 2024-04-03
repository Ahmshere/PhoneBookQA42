package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static boolean isElementPersist(WebElement element) {

        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException | NullPointerException e) {
            return false;
        }
    }
}
