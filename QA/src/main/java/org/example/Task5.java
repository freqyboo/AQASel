package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Task5 {

    public static void uploadImg(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        String filePath = "C:\\Users\\stivt\\IdeaProjects\\AQASele2\\resources\\img\\cat.jpg";
        driver.findElement(By.tagName("input")).sendKeys(filePath);
    }
}
