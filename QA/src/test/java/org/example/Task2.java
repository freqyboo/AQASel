package org.example;

import org.openqa.selenium.By;
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

public class Task2 {

    private WebDriver driver;
    private final String url = "https://qa-course-01.andersenlab.com/";
    private Actions action;
    private final String message = "Congratulations! Let's test for the best!";

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
        WebElement selectCourse = driver.findElement(By.xpath("//div[contains(text(), 'AQA Practice')]"));
        action.moveToElement(selectCourse).build().perform();
        driver.findElement(By.xpath("//div[contains(text(), 'Drag & Drop')]")).click();
    }

    @Test
    public void dragAndDrop() {
        WebElement man1 = driver.findElement(By.xpath("//span[@id='manual1']"));
        WebElement man2 = driver.findElement(By.xpath("//span[@id='manual2']"));
        WebElement aut1 = driver.findElement(By.xpath("//span[@id='auto1']"));
        WebElement aut2 = driver.findElement(By.xpath("//span[@id='auto2']"));
        WebElement targetMan1 = driver.findElement(By.xpath("//div[@id='target-manual1']"));
        WebElement targetMan2 = driver.findElement(By.xpath("//div[@id='target-manual2']"));
        WebElement targetAut1 = driver.findElement(By.xpath("//div[@id='target-auto1']"));
        WebElement targetAut2 = driver.findElement(By.xpath("//div[@id='target-auto2']"));
        WebElement finishButton = driver.findElement(By.xpath("//button[@id='DragNDropPageFinishButton']"));
        action.dragAndDrop(man1, targetMan1).build().perform();
        action.dragAndDrop(man2, targetMan2).build().perform();
        action.dragAndDrop(aut1, targetAut1).build().perform();
        action.dragAndDrop(aut2, targetAut2).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = "/html/body/div[1]/div/div[2]/div/main/section/div";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertEquals(driver.findElement(By.xpath(xpath)).getText(), message);
        finishButton.click();


    }
}