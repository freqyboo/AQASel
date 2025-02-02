package org.example;

import org.openqa.selenium.WebDriver;
import util.driver.DriverSetup;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Task1.testCase();
        Task2.pageCycle();
        Task3.run();
        WebDriver driver = DriverSetup.driverInit();
        driver.get("https://qa-course-01.andersenlab.com/login");
        Task4.login(driver);
        Task5.uploadImg(driver);
    }
}
