package com.ozon.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class JuicersPage {
    private WebDriver driver;
    private static final By COST_FROM_BUTTON =
            By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[1]/input");
    private static final By COST_TO_BUTTON =
            By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[2]/input");
    private static final String JUCIER_PRICE = "//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/div/div[3]/a/div/span";
    private static final String LIST = "//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div/div[1]/div/div/input";
    private static final String JUICER = "//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div/div[2]/div/div/div[3]/div[2]/div/div/button";
    private static final String BASKET_BUTTON = "//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/a[2]";
    private static final String JUCIER_POWER = "//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[1]/div/aside/div[10]/div[2]/div[2]/div[1]/input";
    private static final String NEW_JUCIER ="//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div/div[1]/div/div/div[3]/div[2]/div/div/button";
    private static final String COST_FROM = "3000";
    private static final String COST_TO = "4000";
    private static final String POWER = "1000";

    @Step("checking page loading")
    public static By getCostFromButton() {
        return COST_FROM_BUTTON;
    }

    @Step("Set cost")
    public void setCost() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        Actions actions = new Actions(driver);
        WebElement costFrom = driver.findElement(COST_FROM_BUTTON);
        actions.doubleClick(costFrom).perform();
        costFrom.sendKeys(COST_FROM);
        WebElement costTo = driver.findElement(COST_TO_BUTTON);
        actions.doubleClick(costTo).perform();
        synchronized (driver) {
            driver.wait(3000);
        }
        js.executeScript("window.scrollBy(0,200)");
        actions.doubleClick(costTo).perform();
        costTo.sendKeys(COST_TO);
        costTo.sendKeys(Keys.ENTER);
        synchronized (driver) {
           driver.wait(3000);
        }
    }

    @Step("Check cost")
    public void checkCost() throws InterruptedException {
        setCost();
        String cost = driver.findElement(By.xpath(JUCIER_PRICE)).getText();
        int juiceCost = Integer.parseInt(cost.substring(0, 1) + cost.substring(2, 5));
        Assert.assertTrue(juiceCost >= 3000 && juiceCost <= 4000);
    }

    @Step("Sorting by cost")
    public void sortByCost() throws InterruptedException {
        synchronized (driver) {
            WebElement webElement = driver.findElement(By.xpath(LIST));
            webElement.click();
            driver.wait(2000);
            webElement.sendKeys(Keys.ARROW_DOWN);
            webElement.sendKeys(Keys.ARROW_DOWN);
            driver.wait(2000);
            webElement.sendKeys(Keys.ENTER);
            driver.wait(2000);
        }
    }

    @Step("Click to add juicer")
    public void clickToAddJuicer() {
        driver.findElement(By.xpath(JUICER)).click();
    }

    @Step("Click basket button")
    public void clickBasketButton() {
        driver.findElement(By.xpath(BASKET_BUTTON)).click();
    }

    @Step("Choosing juicer")
    public BasketPage chooseJuicer() throws InterruptedException {
        sortByCost();
        synchronized (driver) {
            driver.wait(2000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,500)");
            driver.wait(2000);
            clickToAddJuicer();
            js.executeScript("window.scrollBy(0,-500)");
            driver.wait(2000);
            clickBasketButton();
        }
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(BasketPage.getCost()));
        return new BasketPage(driver);
    }

    @Step("Set juicer power")
    public void setJuicerPower() throws InterruptedException {
        synchronized (driver) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,500)");
            WebElement webElement = driver.findElement(By.xpath(JUCIER_POWER));
            Actions actions = new Actions(driver);
            actions.doubleClick(webElement).perform();
            driver.wait(2000);
            webElement.sendKeys(POWER);
            webElement.sendKeys(Keys.ENTER);
            driver.wait(2000);
        }
    }

    @Step("Click to choose new jucier")
    public void clickToChooseNewJucier() {
        driver.findElement(By.xpath(NEW_JUCIER)).click();
    }

    public BasketPage chooseJuiceWithSettedPower() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        setCost();
        setJuicerPower();
        sortByCost();
      //  synchronized (driver) {
            js.executeScript("window.scrollBy(0,350)");
      //      driver.wait(1000);
            clickToChooseNewJucier();
      //      driver.wait(1000);
            js.executeScript("window.scrollBy(0,-350)");
            clickBasketButton();
      //  }
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(BasketPage.getNewCost()));
        return new BasketPage(driver);
    }
}
