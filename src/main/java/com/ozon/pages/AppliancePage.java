package com.ozon.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class AppliancePage {
    private WebDriver driver;
    private static final By ALL_GOODS = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/div[1]/aside/div[2]/span");
    private static final By JUICERS = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/div[1]/aside/div[2]/div[6]");

    public static By getAllGoods() {
        return ALL_GOODS;
    }

    @Step("Click show all button")
    public void clickAllGoods() {
        driver.findElement(ALL_GOODS).click();
    }

    @Step("Click juicers button")
    public void clickJuicersButton() {
        driver.findElement(JUICERS).click();
    }

    @Step("Go to juicers page")
    public JuicersPage goToJuicersPage() {
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        clickAllGoods();
        clickJuicersButton();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(JuicersPage.getCostFromButton()));
        return new JuicersPage(driver);
    }
}
