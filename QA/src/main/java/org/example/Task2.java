package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import util.driver.DriverSetup;

import java.util.*;

public class Task2 {

    public static void pageCycle() throws InterruptedException {
        WebDriver driver = DriverSetup.driverInit();
        String[] urls = {
                "http://www.automationpractice.pl/index.php",
                "https://zoo.waw.pl/",
                "https://www.w3schools.com/",
                "https://www.clickspeedtester.com/click-counter/",
                "https://andersenlab.com/"
        };

        for (String url : urls) {
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(url);
            Thread.sleep(2000);
        }

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        for (String tab : tabs) {
            driver.switchTo().window(tab);
            String title = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Страница: " + title + " \nСсылка: " + currentUrl + "\n");

            assert title != null;
            if (title.contains("Zoo")) {
                driver.close();
            }
            Thread.sleep(2000);
        }
        driver.quit();
    }
}