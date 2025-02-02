package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.driver.DriverSetup;

public class Task3 {

    public static void compareTwoElements(WebElement firstElement, WebElement secondElement) {
        int y1 = firstElement.getLocation().getY();
        int y2 = secondElement.getLocation().getY();
        int x1 = firstElement.getLocation().getX();
        int x2 = secondElement.getLocation().getX();

        if (y1 < y2) {
            System.out.println("Первый элемент расположен выше.");
        } else if (y1 > y2) {
            System.out.println("Второй элемент расположен выше.");
        } else {
            System.out.println("Оба элемента находятся на одной высоте.");
        }

        if (x1 < x2) {
            System.out.println("Первый элемент расположен левее.");
        } else if (x1 > x2) {
            System.out.println("Второй элемент расположен левее.");
        } else {
            System.out.println("Оба элемента находятся на одном уровне по горизонтали.");
        }

        int area1 = firstElement.getSize().getWidth() * firstElement.getSize().getHeight();
        int area2 = secondElement.getSize().getWidth() * secondElement.getSize().getHeight();

        if (area1 > area2) {
            System.out.println("Первый элемент занимает большую площадь.");
        } else if (area1 < area2) {
            System.out.println("Второй элемент занимает большую площадь.");
        } else {
            System.out.println("Оба элемента имеют одинаковую площадь.");
        }

    }

    public static void run() {
        WebDriver driver = DriverSetup.driverInit();
        driver.get("https://www.w3schools.com/");
        WebElement button = driver.findElement(By.xpath("//a[contains(text(), 'Sign Up')]"));
        WebElement header = driver.findElement(By.xpath("//h1[contains(text(), 'HTML')]"));
        compareTwoElements(header, button);
        driver.quit();
    }
}