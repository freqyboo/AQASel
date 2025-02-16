package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {

    private WebDriver driver;

    @FindBy(tagName = "h2")
    private WebElement searchResultText;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public By getSearchResultHeadXpath() {
        return By.xpath("//h1[contains(text(), 'Search results')]");
    }

    public WebElement getSearchResultText() {
        return searchResultText;
    }



}
