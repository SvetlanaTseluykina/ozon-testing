package com.ozon.pages;

import com.ozon.utils.PropertyLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

@Component
public class LoginPage {

    private WebDriver driver = new ChromeDriver();
    private static final By LOGIN_BUTTON =
            By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/div[1]/div[1]");
    private static final By ENTER_BY_EMAIL_BUTTON =
            By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[4]/a[1]");
    private static final By EMAIL_FIELD =
            By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[2]/label/div/input");
    private static final By GET_CODE_BUTTON = By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[3]/button");
    private static final By CURRENT_PROFILE_BUTTON =
            By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/div[1]/a");
    private static final By INCORRECT_EMAIL_MESSAGE =
            By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[2]/div/p");
    private static final String REF_ATTRIBUTE = "href";
    private static final int IMPLICIT_WAIT = 10;
    private static final int EXPLICIT_WAIT = 30;

    @Step("Click login button")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Click enter by email button")
    public void clickEnterByEmailButton() {
        driver.findElement(ENTER_BY_EMAIL_BUTTON).click();
    }

    @Step("Click to fill email field")
    public void clickEmailField() {
        driver.findElement(EMAIL_FIELD).click();
    }

    @Step("Fill email field")
    public void fillEmailField(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Click get code button")
    public void clickGetCodeButton() {
        driver.findElement(GET_CODE_BUTTON).click();
    }

    @Step("fill all data to login")
    public void fillData(String email)  {
        System.setProperty(PropertyLoader.getProperty("keyPropertyDriver"),
                PropertyLoader.getProperty("valuePropertyDriver"));
        driver.get(PropertyLoader.getProperty("host"));
        clickLoginButton();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        clickEnterByEmailButton();
        clickEmailField();
        fillEmailField(email);
        clickGetCodeButton();
    }

    @Step("Open login page")
    public void openPage(String email) {
        fillData(email);
        WebElement firstResult = new WebDriverWait(driver, EXPLICIT_WAIT)
                .until(ExpectedConditions.elementToBeClickable(CURRENT_PROFILE_BUTTON));
        Assert.assertEquals(firstResult.getAttribute(REF_ATTRIBUTE), PropertyLoader.getProperty("settings"));
    }

    @Step("Check incorrect mail format")
    public void checkIncorrectEmail(String email) {
        fillData(email);
        Assert.assertEquals(driver.findElement(INCORRECT_EMAIL_MESSAGE).getText(), "Некорректный формат почты");
    }

    @Step("Check empty email")
    public void checkEmptyEmail(String email) {
        fillData(email);
        Assert.assertEquals(driver.findElement(INCORRECT_EMAIL_MESSAGE).getText(), "Заполните почту");
    }

    @Step("Close driver")
    public void closeDriver() {
        driver.close();
    }
}
