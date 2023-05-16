package com.PlaceLab.Amila.qamp.LoginFunctionalityTests;

import com.PlaceLab.Amila.qamp.utils.WebDriverSetup;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Random;

public class SmokeTest {
    public static void main(String[] args) {
        Faker faker = new Faker ();
        Random random = new Random();
        System.out.println("faker: " + faker.funnyName().name());
        System.out.println("faker: " + faker.address());
    }
    private WebDriver driver;

    @BeforeTest
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

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

}
