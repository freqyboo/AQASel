package android_package;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class AndroidPage {

    private AppiumDriver driver;
    private WebDriverWait wait;
    private static final By VIEWS = MobileBy.AccessibilityId("Views");
    private static final By DATE_WIDGETS = MobileBy.AccessibilityId("Date Widgets");
    private static final By DIALOG = MobileBy.AccessibilityId("1. Dialog");
    private static final By CHANGE_THE_DATE = MobileBy.AccessibilityId("change the date");
    private static final By CHANGE_THE_TIME_SPINNER = MobileBy.AccessibilityId("change the time (spinner)");
    private static final By NEXT_MONTH = MobileBy.AccessibilityId("Next month");
    private static final By OK_BUTTON = MobileBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");
    private static final By HOUR_PICKER = MobileBy.xpath("//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.LinearLayout/android.widget.NumberPicker[1]/*");
    private static final By MINUTE_PICKER = MobileBy.xpath("//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.LinearLayout/android.widget.NumberPicker[2]/*");
    private static final By AM_PM = MobileBy.xpath("//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.NumberPicker/*");
    private static final By NEXT_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc=\"Next\"]");
    private static List<MobileElement> hours = new ArrayList<>();
    private static List<MobileElement> minutes = new ArrayList<>();
    private int counter;

    public AndroidPage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void clickOnViews() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(VIEWS)).click();
    }

    public int countElements() throws InterruptedException {
        HashSet<String> count = new HashSet<>();
        while (true) {
            List<MobileElement> elements = driver.findElements(MobileBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]//android.widget.TextView"));
            for (MobileElement element : elements) {
                count.add(element.getText());
            }
            String temp = driver.getPageSource();
            scroll();
            Thread.sleep(2000);
            if (temp.equals(driver.getPageSource())) {
                break;
            }
        }
        return count.size();
    }

    public void scroll() {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
        } catch (InvalidSelectorException ignored) {
        }
    }

    public void clickOnDateWidgets() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DATE_WIDGETS)).click();
    }

    public void clickOnDialog() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DIALOG)).click();
    }

    public void clickOnChangeTheDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHANGE_THE_DATE)).click();
    }

    public void setTomorrowDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        if (c.get(Calendar.DATE) == 1) {
            driver.findElement(NEXT_MONTH).click();
        }
        List<MobileElement> dayChoose = driver.findElements(MobileBy.xpath("//android.view.View[@resource-id=\"android:id/month_view\"]//android.view.View"));
        dayChoose.get(c.get(Calendar.DATE) - 1).click();
        driver.findElement(OK_BUTTON).click();
    }

    public MobileElement getPreviousHour() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(HOUR_PICKER));
        hours = driver.findElements(HOUR_PICKER);
        return hours.get(0);
    }

    public MobileElement getCurrentHour() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(HOUR_PICKER));
        hours = driver.findElements(HOUR_PICKER);
        return hours.get(1);
    }

    public MobileElement getNextHour() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(HOUR_PICKER));
        hours = driver.findElements(HOUR_PICKER);
        return hours.get(2);
    }

    public MobileElement getPreviousMinute() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MINUTE_PICKER));
        minutes = driver.findElements(MINUTE_PICKER);
        return minutes.get(0);
    }

    public MobileElement getCurrentMinute() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MINUTE_PICKER));
        minutes = driver.findElements(MINUTE_PICKER);
        return minutes.get(1);
    }

    public MobileElement getNextMinute() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MINUTE_PICKER));
        minutes = driver.findElements(MINUTE_PICKER);
        return minutes.get(2);
    }

    public void clickChangeTheTimeSpinner() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHANGE_THE_TIME_SPINNER)).click();
    }

    public void setTime() {
        int hour = Integer.parseInt(getCurrentHour().getText());
        int minute = Integer.parseInt(getCurrentMinute().getText());
        int differenceHour = hour - 11;
        int differenceMinute = minute - 11;
        if (differenceHour > 0) {
            getPreviousHour().click();
        } else if (differenceHour < 0) {
            while (differenceHour < 0) {
                getNextHour().click();
                differenceHour++;
            }
        }
        if (differenceMinute > 0) {
            getPreviousMinute().click();
        } else if (differenceMinute < 0) {
            while (differenceMinute < 0) {
                getNextMinute().click();
                differenceMinute++;
            }
        }
        List<MobileElement> am_pm = driver.findElements(AM_PM);
        am_pm.get(1).click();
    }

    public void scrollAndClickOnTextSwitcher() {
        MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"TextSwitcher\"))"));
        element.click();
    }

    public void clickNextButton() {
        counter++;
        driver.findElement(NEXT_BUTTON).click();
    }

    public int getOnScreenValue() {
        List<MobileElement> list = driver.findElements(By.xpath("//android.widget.TextSwitcher[@resource-id=\"io.appium.android.apis:id/switcher\"]/*"));
        int value = Integer.parseInt(list.get(0).getText());
        return value;
    }

    public int getCounter() {
        return counter;
    }

    public void resetCounter() {
        counter=0;
    }

}