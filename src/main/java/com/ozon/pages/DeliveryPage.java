package com.ozon.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPage {

    private static final By LOCALITY =
            By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div[2]/div");
    private static final By CITY_NAME = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[1]/div/button/span");
    private static final By MAIN_PAGE = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[1]/a");
    private WebDriver driver;

    public void checkCityNameInLocality() {
        Assert.assertEquals(driver.findElement(CITY_NAME).getText(), driver.findElement(LOCALITY).getText());
    }

    @Step("Go to start page")
    public MainPage goToStartPage() {
        driver.findElement(MAIN_PAGE).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(MainPage.getCatallogButton()));
        return new MainPage(driver);
    }
}
