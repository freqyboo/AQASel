package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import util.driver.DriverSetup;

import java.time.Duration;

public class Task1 {

    private WebDriver driver;
    private final String url = "https://qa-course-01.andersenlab.com/";
    private Actions action;
    private final String message = "Unfortunately, we did not find any courses matching your chosen criteria.";

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'ANDERSEN')]")));
    }

    @Test
    public void test() {
        WebElement selectCourse = driver.findElement(By.xpath("//div[contains(text(), 'AQA Practice')]"));
        action.moveToElement(selectCourse).build().perform();
        driver.findElement(By.xpath("//div[contains(text(), 'Select')]")).click();
        Select select1 = new Select(driver.findElement(By.xpath("//select[@data-lol='SelectCountry']")));
        select1.selectByVisibleText("USA");
        Select select2 = new Select(driver.findElement(By.xpath("//select[@id='SelectLanguage']")));
        select2.selectByVisibleText("English");
        Select select3 = new Select(driver.findElement(By.xpath("//select[@data-doubtful-but-ok='SelectType']")));
        select3.selectByVisibleText("Testing");
        driver.findElement(By.xpath("//input[@data-calendar='1']")).sendKeys("17-02-2025");
        driver.findElement(By.xpath("//input[@data-calendar='2']")).sendKeys("03-03-2025");
        Select select4 = new Select(driver.findElement(By.xpath("//select[@id='MultipleSelect']")));
        select4.selectByValue("AQA Java");
        select4.selectByValue("AQA Python");
        driver.findElement(By.name("SelectPageSearchButton")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Search results')]")));
        Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(), message);
    }
}