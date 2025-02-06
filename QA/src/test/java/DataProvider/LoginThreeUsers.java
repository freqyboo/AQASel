package DataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.driver.DriverSetup;

import java.time.Duration;

public class LoginThreeUsers {

    private final String url = "https://qa-course-01.andersenlab.com/login";
    private final String redirect = "https://qa-course-01.andersenlab.com/";
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverSetup.driverInit();
        driver.manage().window().maximize();
    }


    @DataProvider(name = "data")
    public String[][] testData() {
        return new String[][] {
                {"data@mail.com","12345678"},
                {"data2@mail.com","12345678"},
                {"data3@mail.com","12345678"}
        };
    }

    @Test(dataProvider = "data")
    public void testLogin(String email, String password) throws InterruptedException {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.tagName("button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'ANDERSEN')]")));
        Assert.assertEquals(driver.getCurrentUrl(), redirect);
    }
}
