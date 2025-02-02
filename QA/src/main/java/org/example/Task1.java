package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import util.driver.DriverSetup;

public class Task1 {

    public static void testCase() throws InterruptedException {
        WebDriver driver = DriverSetup.driverInit();
        String loginUrl = "https://qa-course-01.andersenlab.com/login";
        String regUrl = "https://qa-course-01.andersenlab.com/registration";
        LoginData.add("example@mail.mail", "12345678"); //TC-106
        LoginData.add("example@mail.mail", ""); //TC-107
        LoginData.add("example@mail.mail", "123456789"); //TC-111
        LoginData.add("", ""); //TC-109

        for (int i = 0; i < LoginData.getLengthHash(); i++) {
            String mail = LoginData.getKey(i);
            for (int j = 0; j < LoginData.getLengthPass(mail); j++) {
                driver.switchTo().newWindow(WindowType.TAB);
                driver.get(loginUrl);
                Thread.sleep(2000);
                driver.findElement(By.name("email")).sendKeys(mail);
                driver.findElement(By.name("password")).sendKeys(LoginData.getPassword(mail, j));
                driver.findElement(By.tagName("button")).click();
                Thread.sleep(2000);
            }
        }
        RegData.add("firstName", "A"); //TC-101
        RegData.add("lastName", "B");
        RegData.add("dateOfBirth", "12/12/24");
        RegData.add("email", "example@mail.com");
        RegData.add("password", "12345678");
        RegData.add("passwordConfirmation", "12345678");

        RegData.add("firstName", "A");  //TC-103
        RegData.add("lastName", "");
        RegData.add("dateOfBirth", "12/12/24");
        RegData.add("email", "ex@mail.com");
        RegData.add("password", "12345678");
        RegData.add("passwordConfirmation", "12345678");
        for (int i = 0; i < RegData.getLengthMaxValue(); i++) {
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(regUrl);
            Thread.sleep(2000);
            for (int j = 0; j < RegData.getLengthHash(); j++) {
                String element = RegData.getKey(j);
                driver.findElement(By.name(element)).sendKeys(RegData.getValue(element, i));
            }
            Thread.sleep(1000);
            driver.findElement(By.tagName("button")).click();
        }

    }

}