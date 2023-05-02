package com.PlaceLab.Amila.qamp.tests;

import com.PlaceLab.Amila.qamp.utils.WebDriverSetup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SmokeTest {
    private WebDriver driver;

    @BeforeSuite
    @Parameters("browser")
    public void setup(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
        System.out.println("Opened browser: " + browser);
    }

    @Test
    public void pageTitle() {
        String pageTitle = driver.getTitle();
        System.out.println("Page title: " + pageTitle);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

}
