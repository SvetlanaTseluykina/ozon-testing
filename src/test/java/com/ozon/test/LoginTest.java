package com.ozon.test;

import com.ozon.pages.LoginPage;
import com.ozon.OzonStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import testdata.LoginData;

@SpringBootTest(classes = OzonStarter.class)
public class LoginTest extends AbstractTestNGSpringContextTests {
    @Autowired
    LoginPage loginPage;

    @Test(priority = 1)
    public void openLoginPage()  {
        loginPage.openPage(LoginData.getCorrectEmail());
    }

    @Test
    public void checkIncorrectEmail() {
        loginPage.checkIncorrectEmail(LoginData.getIncorrectEmail());
    }

    @Test
    public void checkEmptyEmail() {
        loginPage.checkEmptyEmail(LoginData.getEmptyEmail());
    }

    @AfterClass
    public void closeDriver() {
        loginPage.closeDriver();
    }
}
