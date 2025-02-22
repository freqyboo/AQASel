package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActionsPage {

    private WebDriver driver;
    private Actions action;

    @FindBy(xpath = "//iframe[@title='Finish your registration']")
    private WebElement iframe;

    @FindBy(xpath = "//button[@id='AlertButton']")
    private WebElement button1;

    @FindBy(xpath = "//button[contains(text(), 'Get Discount')]")
    private WebElement button2;

    @FindBy(xpath = "//button[@data-test-id='PromptButton']")
    private WebElement button3;

    @FindBy(xpath = "//span[@class='font-light flex']")
    private WebElement results;

    public ActionsPage(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public ActionsPage switchToIframe() {
        driver.switchTo().frame(iframe);
        return this;
    }

    public ActionsPage triggerAlert1() {
        button1.click();
        return this;
    }

    public ActionsPage triggerAlert2() {
        action.doubleClick(button2).perform();
        return this;
    }

    public ActionsPage triggerAlert3() {
        action.contextClick(button3).perform();
        return this;
    }

    public ActionsPage acceptAlert() {
        driver.switchTo().alert().accept();
        return this;
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public String getResultsText() {
        return results.getText();
    }

    public ActionsPage alertInputText(String text) {
        driver.switchTo().alert().sendKeys(text);
        return this;
    }

}