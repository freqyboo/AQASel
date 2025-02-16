package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import util.driver.DriverSetup;

import java.time.Duration;

public class Task2 {

    private WebDriver driver;

    private WebDriverWait wait;

    @BeforeTest
    public void setup() {
        driver = DriverSetup.driverInit();
        driver.manage().window().maximize();
        driver.get("https://qa-course-01.andersenlab.com/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Parameters({"email", "password"})
    public void loginTest(String email, String password) {
        new LoginPage(driver).enterEmail(email)
                .enterPassword(password)
                .clickLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'ANDERSEN')]")));
    }

}
