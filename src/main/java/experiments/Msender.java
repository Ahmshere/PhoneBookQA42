package experiments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Msender {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void mailSwitch() throws InterruptedException {

        WebElement entrance = driver.findElement(By.xpath("//div[@id='mailbox']//button"));
        entrance.click();
        System.out.println("Step -0");
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='ag-popup__frame__layout__iframe']"));
        driver.switchTo().frame(frame);
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='username']"));
        usernameField.sendKeys("testertesterovich2024");
        System.out.println("Step 0");
        WebElement submitButton = driver.findElement(By.xpath("//button[@data-test-id='next-button']"));
        submitButton.click();
        System.out.println("Step 1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("Ahmshere577561");
        System.out.println("Step 2");
        WebElement submitPasswordButton = driver.findElement(By.xpath("//button[@data-test-id='submit-button']"));
        submitPasswordButton.click();
        System.out.println("Step 3");

        WebElement createNewMail = driver.findElement(By.xpath("//span[@class='compose-button__txt']"));
        createNewMail.click();
        System.out.println("Step 4");
        WebElement komuField = driver.findElement(By.xpath("//div[@data-name='to']//input[@type='text']"));
        komuField.sendKeys("testmail@aol.com");
        System.out.println("Step 5");
        WebElement subject = driver.findElement(By.xpath("//input[@name='Subject']"));
        subject.sendKeys("How is it going!");
        System.out.println("Step 6");
        WebElement bodyMail = driver.findElement(By.xpath("//div[@class='cke_widget_editable']"));
        bodyMail.clear();
        bodyMail.sendKeys("TestText");
        System.out.println("Step 7");
        WebElement sendButton = driver.findElement(By.xpath("//button[@data-test-id='send']"));
        sendButton.click();
        System.out.println("Step 8");
    }
    @Test
    public void testIframe1() {

        driver.switchTo().frame("frame1");

        WebElement element1 = driver.findElement(By.xpath("//h1"));
        element1.click();
        System.out.println("ELEMENT: "+element1.getText());

        driver.switchTo().defaultContent();
    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
