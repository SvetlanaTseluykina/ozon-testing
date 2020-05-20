package com.ozon.utils;

import com.ozon.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class InitializeDriver {
    private WebDriver driver = new ChromeDriver();

//    public InitializeDriver() {
//        driver = new ChromeDriver();
//    }

    public MainPage goToMainPage() {
        return new MainPage(driver);
    }

    public void closeDriver() {
        driver.close();
    }
}
