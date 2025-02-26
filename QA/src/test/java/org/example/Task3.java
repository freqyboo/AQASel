package org.example;

import android_package.AndroidPage;
import android_package.AppiumDriverInit;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Task3 {

    private AppiumDriver driver;
    private AndroidPage androidPage;

    @BeforeClass
    public void setup() {
        driver = AppiumDriverInit.getDriver();
        androidPage = new AndroidPage(driver);
    }

    @Test
    public void testValue() {
        androidPage.clickOnViews();
        androidPage.scrollAndClickOnTextSwitcher();
        for (int i = 0; i<4; i++) {
            androidPage.clickNextButton();
        }
        Assert.assertEquals(androidPage.getOnScreenValue(), androidPage.getCounter());
        androidPage.resetCounter();
    }
}
