package com.ozon.pages;

import com.ozon.utils.PropertyLoader;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class MainPage {

    private WebDriver driver;
    private static final By LOGIN_BUTTON_SETTINGS =
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
    private static final By CITY = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[1]/div/button");
    private static final By CITY_FIELD = By.xpath("//*[@id=\"__nuxt\"]/div/div[2]/div/div/div/div/div/label/div/input");
    private static final By CITY_NAME = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[1]/div/button/span");
    private static final By DELIVERY_POINT = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[1]/div/ul/li[5]/div");
    private static final By CATALOG =
            By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[2]/div/div[1]/button");
    private static final By APPLIANCE =
            By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[2]/div/div[2]/div/div[1]/div/a[12]");
    private static final By CLOSE_BUTTON = By.xpath("/html/body/div[3]/div/div/div/button");
    private static final String REF_ATTRIBUTE = "href";
    private static final int IMPLICIT_WAIT = 10;
    private static final int EXPLICIT_WAIT = 30;

    @Step("Get catalog button")
    public static By getCatallogButton() {
        return CATALOG;
    }

    @Step("Click login button")
    public LoginPage clickLoginButton() {
        driver.findElement(CURRENT_PROFILE_BUTTON).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, EXPLICIT_WAIT);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(LoginPage.getExitButton()));
        return new LoginPage(driver);
    }

    @Step("Click login settings button")
    public void clickLoginSettingsButton() {
        driver.findElement(LOGIN_BUTTON_SETTINGS).click();
    }

    @Step("Click enter by email button")
    public void clickEnterByEmailButton() {
        driver.findElement(ENTER_BY_EMAIL_BUTTON).click();
    }

    @Step("Click to fill email field")
    public void clickEmailField() {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(EMAIL_FIELD)).perform();
    }

    @Step("Fill email field")
    public void fillEmailField(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Click get code button")
    public void clickGetCodeButton() {
        driver.findElement(GET_CODE_BUTTON).click();
    }

    public void setProperty() {
        System.setProperty(PropertyLoader.getProperty("keyPropertyDriver"),
                PropertyLoader.getProperty("valuePropertyDriver"));
    }

    @Step("Open ozon page")
    public void openOzonPage() {
        driver.get(PropertyLoader.getProperty("host"));
        synchronized (driver) {
            try {
                driver.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Step("Click city button")
    public void clickCityButton() {
        driver.findElement(CITY).click();
    }

    @Step("Click city field")
    public void clickCityField() {
        driver.findElement(CITY_FIELD).click();
    }

    @Step("Click delivery point")
    public void clickDeliveryPoint() {
        driver.findElement(DELIVERY_POINT).click();
    }

    @Step("Click catalog button")
    public void clickCatalogButton() {
        driver.findElement(CATALOG).click();
    }

    @Step("Click appliance button")
    public void clickApplianceButton() {
        driver.findElement(APPLIANCE).click();
    }

    @Step("Click close button")
    public void clickCloseButton() {
        driver.findElement(CLOSE_BUTTON).click();
    }

    @Step("Go to appliance page")
    public AppliancePage goToAppliancePage() {
        clickCatalogButton();
        clickApplianceButton();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(AppliancePage.getAllGoods()));
        return new AppliancePage(driver);
    }

    @Step("Fill city field")
    public void fillCityField(String cityName) {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement element = driver.findElement(CITY_FIELD);
        element.sendKeys(cityName);
        synchronized(driver) {
            try {
                driver.wait(1000);
                element.sendKeys(Keys.ENTER);
                driver.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Step("Change city in the top left corner")
    public void changeCity(String cityName) {
        clickCityButton();
        clickCityField();
        fillCityField(cityName);
    }

    @Step("Check changed city name")
    public void checkChangedCityName(String cityName) {
        changeCity(cityName);
        Assert.assertEquals(driver.findElement(CITY_NAME).getText(), cityName);
    }

    @Step("Go to delivery page")
    public DeliveryPage goToDeliveryPage() {
        clickDeliveryPoint();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        return new DeliveryPage(driver);
    }

    @Step("fill all data to login")
    public void fillData(String email)  {
        clickLoginSettingsButton();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        clickEnterByEmailButton();
        clickEmailField();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
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
        clickCloseButton();
    }

    @Step("Check empty email")
    public void checkEmptyEmail(String email) {
        fillData(email);
        Assert.assertEquals(driver.findElement(INCORRECT_EMAIL_MESSAGE).getText(), "Заполните почту");
        clickCloseButton();
    }

    @Step("Close driver")
    public void closeDriver() {
        driver.close();
    }
}
