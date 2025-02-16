package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectCoursePage {

    private WebDriver driver;
    private Actions action;

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

    public SelectCoursePage setCountry(String country) {
        new Select(selectCountry).selectByVisibleText(country);
        return this;
    }

    public SelectCoursePage setLang(String lang) {
        new Select(selectLang).selectByVisibleText(lang);
        return this;
    }

    public SelectCoursePage setType(String type) {
        new Select(selectType).selectByVisibleText(type);
        return this;
    }

    public SelectCoursePage setFirstDate(String date) {
        date1.sendKeys(date);
        return this;
    }

    public SelectCoursePage setSecondDate(String date) {
        date2.sendKeys(date);
        return this;
    }

    public SelectCoursePage setMulti(List<String> values) {
        Select select = new Select(selectMulti);
        for (String element : values) {
            select.selectByValue(element);
        }
        return this;
    }

    public SelectCoursePage clickButton() {
        searchButton.click();
        return this;
    }

    public By getSelectCountry() {
        return By.xpath("//select[@data-lol='SelectCountry']");
    }
}
