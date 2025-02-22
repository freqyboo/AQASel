package util.listeners;

import io.qameta.allure.Allure;
import util.driver.DriverSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AllureListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        makeScreen(result.getMethod().getMethodName(), DriverSetup.startDriver());
    }

    private static void makeScreen(String methodName, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        try {
            String screenshotPath = "target/allure-results/screenshot-" + methodName + ".png";
            FileUtils.copyFile(source, new File(screenshotPath));
            Allure.addAttachment("Screenshot for " + methodName, new FileInputStream(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
