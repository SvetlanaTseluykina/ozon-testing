package com.ozon.test;

import com.ozon.OzonStarter;
import com.ozon.pages.AppliancePage;
import com.ozon.pages.BasketPage;
import com.ozon.pages.JuicersPage;
import com.ozon.pages.MainPage;
import com.ozon.utils.InitializeDriver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = OzonStarter.class)
public class JuicersTest extends AbstractTestNGSpringContextTests {

    @Autowired
    InitializeDriver driver;

    @Autowired
    MainPage mainPage;

    @Autowired
    AppliancePage appliancePage;

    @Autowired
    JuicersPage juicersPage;

    @Autowired
    BasketPage basketPage;

    @BeforeClass
    public void openOzonPage() {
        mainPage = driver.goToMainPage();
        mainPage.openOzonPage();
    }

    @Test
    public void checkJuicers() throws InterruptedException {
        appliancePage = mainPage.goToAppliancePage();
        juicersPage = appliancePage.goToJuicersPage();
        juicersPage.checkCost();
    }

    @Test(priority = 1)
    public void checkChangedCost() throws InterruptedException {
         basketPage = juicersPage.chooseJuicer();
         basketPage.chooseFiveJuicers();
         mainPage = basketPage.goToMainPage();
    }

    @Test(priority = 2)
    public void checkCostWithJuicerPower() throws InterruptedException {
        appliancePage = mainPage.goToAppliancePage();
        juicersPage = appliancePage.goToJuicersPage();
        basketPage = juicersPage.chooseJuiceWithSettedPower();
        basketPage.chooseTwoJuicers();
        mainPage = basketPage.goToStartPage();
    }

    @AfterClass
    public void closeDriver() {
        mainPage.closeDriver();
    }
}
