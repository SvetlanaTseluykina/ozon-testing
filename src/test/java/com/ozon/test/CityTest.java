package com.ozon.test;

import com.ozon.OzonStarter;
import com.ozon.pages.DeliveryPage;
import com.ozon.pages.MainPage;
import com.ozon.utils.AllureListener;
import com.ozon.utils.InitializeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testdata.CityData;

@Listeners(AllureListener.class)
@SpringBootTest(classes = OzonStarter.class)
public class CityTest extends AbstractTestNGSpringContextTests {

    @Autowired
    InitializeDriver driver;

    @Autowired
    MainPage mainPage;

    @Autowired
    DeliveryPage deliveryPage;

    @BeforeClass
    public void openOzonPage() {
        mainPage = driver.goToMainPage();
        mainPage.setProperty();
        mainPage.openOzonPage();
    }

    @Test
    public void checkChangedCity() throws InterruptedException {
        mainPage.checkChangedCityName(CityData.getCityName());
    }

    @Test(priority = 1)
    public void checkCityName() {
        deliveryPage = mainPage.goToDeliveryPage();
        deliveryPage.checkCityNameInLocality();
        deliveryPage.goToStartPage();
    }
}
