package Paremeters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.driver.DriverSetup;

import java.time.Duration;

public class LoginThreeUsersParameters {

    private final String url = "https://qa-course-01.andersenlab.com/login";
    private final String redirect = "https://qa-course-01.andersenlab.com/";
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverSetup.driverInit();
        driver.manage().window().maximize();
    }

    @Test
    @Parameters({"email", "password"})
    public void testLogin(String email, String password) throws InterruptedException {
        driver.get(url);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.tagName("button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'ANDERSEN')]")));
        Assert.assertEquals(driver.getCurrentUrl(), redirect);
    }
}
