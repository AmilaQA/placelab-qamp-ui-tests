package com.PlaceLab.Amila.qamp.tests;

import com.PlaceLab.Amila.qamp.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//TC 02 - Invalid Credentials - Verify error message is displayed when the user enters invalid credentials.
public class LoginFunctionalityInvalidCredentials {
    private WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
        System.out.println("Opened browser: " + browser);
    }

    @Test

    public void testInvalidCredentials() {
        Assert.assertTrue(driver.findElement(By.id("login")).isDisplayed(), "Verify Login page visible.");

        // Enter invalid username and password
        driver.findElement(By.id("email")).sendKeys("amila@gmail.com");
        driver.findElement(By.id("password")).sendKeys("User123");

        // Click on the login button
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify that the error message is displayed
        Assert.assertTrue(driver.findElement(By.className("error-area")).isDisplayed(), "Validate Error message Invalid credentials appears.");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
