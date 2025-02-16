package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.reporters.jq.Main;

public class MainPage {

    private WebDriver driver;

    private Actions action;

    @FindBy(xpath = "//div[contains(text(), 'AQA Practice')]")
    private WebElement selectCourse;

    @FindBy(xpath = "//div[contains(text(), 'Select')]")
    private WebElement select;

    @FindBy(xpath = "//div[contains(text(), 'Drag & Drop')]")
    private WebElement drag;

    @FindBy(xpath = "//div[contains(text(), 'Actions, Alerts & Iframes')]")
    private WebElement alerts;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage hoverOverCourse() {
        action.moveToElement(selectCourse).perform();
        return this;
    }

    public MainPage clickSelect() {
        select.click();
        return this;
    }

    public MainPage clickDrag() {
        drag.click();
        return this;
    }

    public MainPage clickAlerts() {
        alerts.click();
        return this;
    }

}
