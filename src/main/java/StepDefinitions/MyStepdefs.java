package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.pqc.jcajce.provider.util.BaseKeyFactorySpi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MyStepdefs {

    @After
    public void closeDriver() {
        driver.quit();
    }

    private WebDriver driver;
    private WebDriverWait wait;
    private String redirectUrl = "https://qa-course-01.andersenlab.com/";
    private String expectedErrorMessage1 = "Email or password is not valid";
    private String expectedErrorMessage2 = "Requried";

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(tagName = "button")
    private WebElement loginButton;

    @FindBy(css = ".text-rose-500")
    private WebElement errorMessage;

    @Given("Set up driver")
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @When("I open login page")
    public void openLoginPage() {
        driver.get("https://qa-course-01.andersenlab.com/login");
    }

    @And("I set valid email {string}")
    public void iSetValidEmail(String arg0) {
        emailField.sendKeys(arg0);
    }

    @And("I set invalid password {string}")
    @And("I set valid password {string}")
    @And("I leave password empty {string}")
    public void iSetValidPassword(String arg0) {
        passwordField.sendKeys(arg0);
    }

    @And("I click on login button")
    public void iClickOnLoginButton() {
        loginButton.click();
    }

    @Then("I see main page")
    public void iSeeMainPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'ANDERSEN')]")));
        Assert.assertEquals(redirectUrl, driver.getCurrentUrl());
    }

    @Then("I see error message {string}")
    public void iSeeErrorMessage(String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-rose-500")));
        switch (message)
        {
            case("wrong"):
                Assert.assertEquals(expectedErrorMessage1, errorMessage.getText());
            case("empty"):
                Assert.assertEquals(expectedErrorMessage2, errorMessage.getText());
        }
    }
}
