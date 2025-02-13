package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.driver.DriverSetup;

import java.time.Duration;

public class Task3 {

    private WebDriver driver;
    private final String url = "https://qa-course-01.andersenlab.com/";
    private Actions action;
    private final String expectedAlert1 = "You have called alert!";
    private final String expectedAlert2 = "Are you sure you want to apply the discount?";
    private final String expectedAlert3 = "Here you may describe a reason why you are cancelling your registration (or leave this field empty).";
    private final String expectedResult1 = "Congratulations, you have successfully enrolled in the course!";
    private final String expectedResult2 = "You received a 10% discount on the second course.";
    private WebDriverWait wait;
    private WebElement results;

    @BeforeTest
    public void setup() {
        driver = DriverSetup.driverInit();
        action = new Actions(driver);
        driver.manage().window().maximize();
    }

    @BeforeClass()
    public void login() {
        driver.get(url);
        driver.findElement(By.name("email")).sendKeys("data@mail.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.tagName("button")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'ANDERSEN')]")));
        WebElement selectCourse = driver.findElement(By.xpath("//div[contains(text(), 'AQA Practice')]"));
        action.moveToElement(selectCourse).build().perform();
        driver.findElement(By.xpath("//div[contains(text(), 'Actions, Alerts & Iframes')]")).click();
        WebElement iframe = driver.findElement(By.xpath("//iframe[@title='Finish your registration']"));
        driver.switchTo().frame(iframe);
    }

    @Test
    public void alertTest1() {
        WebElement confirm = driver.findElement(By.xpath("//button[@id='AlertButton']"));
        confirm.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(driver.switchTo().alert().getText(), expectedAlert1);
        driver.switchTo().alert().accept();
        results = driver.findElement(By.xpath("//span[@class='font-light flex']"));
        Assert.assertEquals(results.getText(), expectedResult1);
    }

    @Test
    public void alertTest2() {
        WebElement getDiscount = driver.findElement(By.xpath("//button[contains(text(), 'Get Discount')]"));
        action.doubleClick(getDiscount).build().perform();
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(driver.switchTo().alert().getText(), expectedAlert2);
        driver.switchTo().alert().accept();
        Assert.assertEquals(results.getText(), expectedResult2);
    }

    @Test
    public void alertTest3() {
        WebElement cancel = driver.findElement(By.xpath("//button[@data-test-id='PromptButton']"));
        action.contextClick(cancel).build().perform();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys("Test");
        Assert.assertEquals(driver.switchTo().alert().getText(), expectedAlert3);
        driver.switchTo().alert().accept();
        Assert.assertTrue(results.getText().contains("Test"));
    }
}