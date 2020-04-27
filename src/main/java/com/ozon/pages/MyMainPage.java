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
@AllArgsConstructor
@NoArgsConstructor
public class MyMainPage {
    private WebDriver driver;
    private static final String CHANGE_PROFILE = "//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div/div[1]/div[1]/a";

    @Step("Get change profile")
    public static By getChangeProfile() {
        return By.xpath(CHANGE_PROFILE);
    }

    public LoginPage goToLoginPage() {
        driver.findElement(By.xpath(CHANGE_PROFILE)).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(LoginPage.getExitButton()));
        return new LoginPage(driver);
    }
}
