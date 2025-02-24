package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(tagName = "button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        passwordField.sendKeys(pass);
        return this;
    }

    public LoginPage clickLogin() {
        loginButton.click();
        return this;
    }


}