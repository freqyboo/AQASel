package org.example;

import android_package.AndroidPage;
import android_package.AppiumDriverInit;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Task1 {

    private AppiumDriver driver;
    private AndroidPage androidPage;

    @BeforeClass
    public void setup() {
        driver = AppiumDriverInit.getDriver();
        androidPage = new AndroidPage(driver);
    }

    @Test
    public void countElements() throws InterruptedException {
        androidPage.clickOnViews();
        Assert.assertEquals(androidPage.countElements(), 42);

    }


}
