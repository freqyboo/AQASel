package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.driver.DriverSetup;

public class Task4 {

    public static void login(WebDriver driver) throws InterruptedException {
        String mail = "example@mail.mail";
        String password = "12345678";
        Thread.sleep(2000);
        driver.findElement(By.name("email")).sendKeys(mail);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.tagName("button")).click();
    }
}