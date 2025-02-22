package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import util.driver.DriverSetup;
import util.listeners.AllureListener;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Listeners({AllureListener.class})
public class Task1 {

    private WebDriver driver;
    private Actions action;
    private WebDriverWait wait;
    private ActionsPage apObject;
    private String searchMessage = "Unfortunately, we did not find any courses matching your chosen criteria.";
    private String finishMessage = "Congratulations! Let's test for the best!";
    private final String expectedAlert1 = "You have called alert!";
    private final String expectedAlert2 = "Are you sure you want to apply the discount?";
    private final String expectedAlert3 = "Here you may describe a reason why you are cancelling your registration (or leave this field empty).";
    private final String expectedResult1 = "Congratulations, you have successfully enrolled in the course!";
    private final String expectedResult2 = "You received a 10% discount on the second course.";
    private MainPage mainPage;

    @BeforeTest
    public void setup() {
        driver = DriverSetup.startDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-course-01.andersenlab.com/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);
    }

    public void login() {
        new LoginPage(driver).enterEmail("data@mail.com").enterPassword("12345678").clickLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'ANDERSEN')]")));
        mainPage = new MainPage(driver);
    }

    @BeforeMethod
    public void checkIfMain() {
        if (!Objects.equals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/") && !Objects.equals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/actions")) {
            driver.get("https://qa-course-01.andersenlab.com");
            login();
        }
    }

    @Test
    public void test1() {
        mainPage.hoverOverCourse().clickSelect();
        SelectCoursePage sPage = new SelectCoursePage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sPage.getSelectCountry()));
        sPage.setCountry("USA")
                .setLang("English")
                .setType("Testing")
                .setFirstDate("17-02-2025")
                .setSecondDate("03-03-2025")
                .setMulti(new ArrayList<>(Arrays.asList("AQA Java", "AQA Python")))
                .clickButton();
        SearchResultPage search = new SearchResultPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(search.getSearchResultHeadXpath()));
        Assert.assertEquals(search.getSearchResultText().getText(), searchMessage);
    }

    @Test
    public void test2() {
        mainPage.hoverOverCourse().clickDrag();

        DragAndDropPage dndPage = new DragAndDropPage(driver).dndFirstManual()
                .dndSecondManual()
                .dndFirstAuto()
                .dndSecondAuto();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dndPage.getTextBoxXpath()));
        Assert.assertEquals(dndPage.getTextBox().getText(), finishMessage);
        dndPage.clickFinishButton();
    }

    @Test
    public void test3() { //failing test
        mainPage.hoverOverCourse().clickSelect();
        SelectCoursePage sPage = new SelectCoursePage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sPage.getSelectCountry()));
        sPage.setCountry("USA")
                .setLang("English")
                .setType("TestNG")
                .setFirstDate("17-02-2025")
                .setSecondDate("03-03-2025")
                .setMulti(new ArrayList<>(Arrays.asList("AQA Java", "AQA Python")))
                .clickButton();
        SearchResultPage search = new SearchResultPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(search.getSearchResultHeadXpath()));
        Assert.assertEquals(search.getSearchResultText().getText(), searchMessage);
    }

    @Test
    public void test4() {
        mainPage.hoverOverCourse().clickAlerts();

        apObject = new ActionsPage(driver);
        apObject.switchToIframe().triggerAlert1();
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(apObject.getAlertText(), expectedAlert1);
        apObject.acceptAlert();
        Assert.assertEquals(apObject.getResultsText(), expectedResult1);
    }

    @Test
    public void test5() {
        apObject.triggerAlert2();
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(apObject.getAlertText(), expectedAlert2);
        apObject.acceptAlert();
        Assert.assertEquals(apObject.getResultsText(), expectedResult2);
    }

    @Test
    public void test6() {
        apObject.triggerAlert3();
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(apObject.getAlertText(), expectedAlert3);
        apObject.alertInputText("Test")
                .acceptAlert();
        Assert.assertTrue(apObject.getResultsText().contains("Test"));
    }

}