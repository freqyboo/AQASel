package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;

import java.util.List;

public class SelectCoursePage {

    private WebDriver driver;
    private Actions action;
    static final Logger logger = LoggerFactory.getLogger(SelectCoursePage.class);

    @FindBy(xpath = "//select[@data-lol='SelectCountry']")
    private WebElement selectCountry;

    @FindBy(xpath = "//select[@id='SelectLanguage']")
    private WebElement selectLang;

    @FindBy(xpath = "//select[@data-doubtful-but-ok='SelectType']")
    private WebElement selectType;

    @FindBy(xpath = "//input[@data-calendar='1']")
    private WebElement date1;

    @FindBy(xpath = "//input[@data-calendar='2']")
    private WebElement date2;

    @FindBy(xpath = "//select[@id='MultipleSelect']")
    private WebElement selectMulti;

    @FindBy(name = "SelectPageSearchButton")
    private WebElement searchButton;

    public SelectCoursePage(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("setCountry")
    public SelectCoursePage setCountry(String country) {
        logger.info("Selecting country");
        new Select(selectCountry).selectByVisibleText(country);
        return this;
    }

    @Step("setLang")
    public SelectCoursePage setLang(String lang) {
        logger.info("Selecting language");
        new Select(selectLang).selectByVisibleText(lang);
        return this;
    }

    @Step("setType")
    public SelectCoursePage setType(String type) {
        logger.info("Selecting type");
        new Select(selectType).selectByVisibleText(type);
        return this;
    }

    @Step("setFirstDate")
    public SelectCoursePage setFirstDate(String date) {
        logger.info("Selecting first date");
        date1.sendKeys(date);
        return this;
    }

    @Step("setSecondDate")
    public SelectCoursePage setSecondDate(String date) {
        logger.info("Selecting second date");
        date2.sendKeys(date);
        return this;
    }

    @Step("setMulti")
    public SelectCoursePage setMulti(List<String> values) {
        logger.info("Selecting multiple values");
        Select select = new Select(selectMulti);
        for (String element : values) {
            select.selectByValue(element);
        }
        return this;
    }

    @Step("clickButton")
    public SelectCoursePage clickButton() {
        logger.info("Clicking search button");
        searchButton.click();
        return this;
    }

    public By getSelectCountry() {
        return By.xpath("//select[@data-lol='SelectCountry']");
    }
}
