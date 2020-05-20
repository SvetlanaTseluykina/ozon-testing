package com.ozon.test;

import com.ozon.pages.MainPage;
import com.ozon.OzonStarter;
import com.ozon.utils.AllureListener;
import com.ozon.utils.InitializeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testdata.LoginData;

@Listeners(AllureListener.class)
@SpringBootTest(classes = OzonStarter.class)
public class LoginTest extends AbstractTestNGSpringContextTests {

    @Autowired
    MainPage mainPage;

    @Autowired
    InitializeDriver driver;

    @BeforeClass
    public void openOzonPage() {
        mainPage = driver.goToMainPage();
        mainPage.setProperty();
        mainPage.openOzonPage();
    }

    @Test(priority = 1)
    public void openLoginPage() {
        mainPage.openPage(LoginData.getCorrectEmail());
    }

}
