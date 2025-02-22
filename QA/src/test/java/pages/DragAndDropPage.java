package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage {

    private WebDriver driver;
    private Actions action;

    @FindBy(xpath = "//span[@id='manual1']")
    private WebElement manual1;

    @FindBy(xpath = "//span[@id='manual2']")
    private WebElement manual2;

    @FindBy(xpath = "//span[@id='auto1']")
    private WebElement auto1;

    @FindBy(xpath = "//span[@id='auto2']")
    private WebElement auto2;

    @FindBy(xpath = "//div[@id='target-manual1']")
    private WebElement targetManual1;

    @FindBy(xpath = "//div[@id='target-manual2']")
    private WebElement targetManual2;

    @FindBy(xpath = "//div[@id='target-auto1']")
    private WebElement targetAuto1;

    @FindBy(xpath = "//div[@id='target-auto2']")
    private WebElement targetAuto2;

    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/main/section/div")
    private WebElement textBox;

    @FindBy(xpath = "//button[@id='DragNDropPageFinishButton']")
    private WebElement finishButton;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getTextBox() {
        return textBox;
    }
    public By getTextBoxXpath() {
        return By.xpath("/html/body/div[1]/div/div[2]/div/main/section/div");
    }

    public DragAndDropPage dndFirstManual() {
        action.dragAndDrop(manual1, targetManual1).perform();
        return this;
    }

    public DragAndDropPage dndSecondManual() {
        action.dragAndDrop(manual2, targetManual2).perform();
        return this;
    }

    public DragAndDropPage dndFirstAuto() {
        action.dragAndDrop(auto1, targetAuto1).perform();
        return this;
    }

    public DragAndDropPage dndSecondAuto() {
        action.dragAndDrop(auto2, targetAuto2).perform();
        return this;
    }

    public void clickFinishButton() {
        finishButton.click();
    }

}