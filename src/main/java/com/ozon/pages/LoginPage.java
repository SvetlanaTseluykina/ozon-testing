package com.ozon.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class LoginPage {

    private static final String EXIT_BUTTON = "//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div/div[2]/div[2]/a";
    private static final String CONFIRMATION_BUTTON = "//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/div[2]/div[1]/button";
    private WebDriver driver;

    @Step("Get exit button")
    public static By getExitButton() {
        return By.xpath(EXIT_BUTTON);
    }

    @Step("Click exit button")
    public void clickExitButton() {
        driver.findElement(By.xpath(EXIT_BUTTON)).click();
    }

    @Step("Click confirmation button")
    public MainPage clickConfirmationButton() throws InterruptedException {
        synchronized (driver) {
            clickExitButton();
            driver.wait(1000);
            driver.findElement(By.xpath(CONFIRMATION_BUTTON));
            WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(MainPage.getCatallogButton()));
            return new MainPage(driver);
        }
    }
}
