package com.ozon.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BasketPage {

    private WebDriver driver;
    private static final String COST = "//*[@id=\"__nuxt\"]/div/div[1]/div/div/div[3]/div[5]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div[1]/div/span";
    private static final String COST_LIST = "//*[@id=\"__nuxt\"]/div/div[1]/div/div/div[3]/div[5]/div[1]/div[1]/div/div[2]/div[3]/div[4]/div/div[1]/div/div[1]/div/div/input";
    private static final String MAIN_PAGE = "//*[@id=\"__nuxt\"]/div/div[1]/div/div/div[2]/div/header/div[1]/div[1]/a";
    private static final String DELETE_BUTTON = "//*[@id=\"__nuxt\"]/div/div[1]/div/div/div[3]/div[5]/div[1]/div[1]/div/div[1]/span";
    private static final String CONFIRM_DELETION_FOR_NEW_JUICE = "/html/body/div[34]/div/div/div/div/section/div[3]/div/button";
    private static final String CONFIRM_DELETION = "/html/body/div[19]/div/div/div/div/section/div[3]/div/button";
    private static final String NEW_COST = "//*[@id=\"__nuxt\"]/div/div[1]/div/div/div[3]/div[5]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div/div/span";

    @Step("Getting cost element")
    public static By getCost() {
        return By.xpath(COST);
    }

    @Step("Getting new cost element")
    public static By getNewCost() {
        return By.xpath(NEW_COST);
    }

    @Step("Click list")
    public void clickList() {
        driver.findElement(By.xpath(COST_LIST)).click();
    }

    @Step("Choose 5 juicers")
    public void chooseFiveJuicers() throws InterruptedException {
        synchronized (driver) {
            String cost = driver.findElement(By.xpath(COST)).getText();
            int oldJuiceCost = Integer.parseInt(cost.substring(0, 1) + cost.substring(2, 5));
            clickList();
            driver.wait(2000);
            WebElement webElement = driver.findElement(By.xpath(COST_LIST));
            for (int i = 0; i < 4; i++) {
                webElement.sendKeys(Keys.ARROW_DOWN);
            }
            driver.wait(2000);
            webElement.sendKeys(Keys.ENTER);
            driver.wait(2000);
            cost = driver.findElement(By.xpath(COST)).getText();
            int newJuiceCost = Integer.parseInt(cost.substring(0, 2) + cost.substring(3, 6));
            System.out.println(oldJuiceCost + " " + newJuiceCost + " " + newJuiceCost / oldJuiceCost);
            Assert.assertEquals(newJuiceCost / oldJuiceCost, 5);
        }
    }

    @Step("Choose 2 juicers")
    public void chooseTwoJuicers() throws InterruptedException {
        synchronized (driver) {
            String cost = driver.findElement(By.xpath(NEW_COST)).getText();
            int oldJuiceCost = Integer.parseInt(cost.substring(0, 1) + cost.substring(2, 5));
            clickList();
            driver.wait(2000);
            WebElement webElement = driver.findElement(By.xpath(COST_LIST));
            webElement.sendKeys(Keys.ARROW_DOWN);
            driver.wait(2000);
            webElement.sendKeys(Keys.ENTER);
            driver.wait(2000);
            cost = driver.findElement(By.xpath(NEW_COST)).getText();
            int newJuiceCost = Integer.parseInt(cost.substring(0, 1) + cost.substring(2, 5));
            System.out.println(oldJuiceCost + " " + newJuiceCost + " " + newJuiceCost / oldJuiceCost);
            Assert.assertEquals(newJuiceCost / oldJuiceCost, 2);
        }
    }

    @Step("Click delete button")
    public void clickDeleteButton() {
        driver.findElement(By.xpath(DELETE_BUTTON)).click();
    }

    @Step("Click confirm deletion button")
    public void clickConfirmDeletionButton() {
        driver.findElement(By.xpath(CONFIRM_DELETION)).click();
    }

    @Step("Click confirm deletion button")
    public void clickConfirmNewDeletionButton() {
        driver.findElement(By.xpath(CONFIRM_DELETION_FOR_NEW_JUICE)).click();
    }

    @Step("Delete juicers")
    public void deleteJuicers() throws InterruptedException {
        synchronized (driver) {
            clickDeleteButton();
            driver.wait(1000);
            clickConfirmDeletionButton();
            driver.wait(1000);
        }
    }

    @Step("Delete juicers")
    public void deleteNewJuicers() throws InterruptedException {
        synchronized (driver) {
            clickDeleteButton();
            driver.wait(1000);
            clickConfirmNewDeletionButton();
            driver.wait(1000);
        }
    }

    @Step("Go to start page")
    public MainPage goToMainPage() throws InterruptedException {
        deleteJuicers();
        driver.findElement(By.xpath(MAIN_PAGE)).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(MainPage.getCatallogButton()));
        return new MainPage(driver);
    }

    @Step("Go to start page")
    public MainPage goToStartPage() throws InterruptedException {
        deleteNewJuicers();
        driver.findElement(By.xpath(MAIN_PAGE)).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(MainPage.getCatallogButton()));
        return new MainPage(driver);
    }
}
