package org.example;

import android_package.AndroidPage;
import android_package.AppiumDriverInit;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Task2 {

    private AppiumDriver driver;
    private AndroidPage androidPage;

    @BeforeClass
    public void setup() {
        driver = AppiumDriverInit.getDriver();
        androidPage = new AndroidPage(driver);
    }

    @Test
    public void setDate() {
        androidPage.clickOnViews();
        androidPage.clickOnDateWidgets();
        androidPage.clickOnDialog();
        androidPage.clickOnChangeTheDate();
        androidPage.setTomorrowDate();
        androidPage.clickChangeTheTimeSpinner();
        androidPage.setTime();
    }
}
