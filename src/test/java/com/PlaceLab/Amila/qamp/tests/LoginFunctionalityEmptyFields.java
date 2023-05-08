package com.PlaceLab.Amila.qamp.tests;

import com.PlaceLab.Amila.qamp.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//TC 03 - Empty fields - Verify that an error message is displayed when the user submits the login by leaving fields empty.
public class LoginFunctionalityEmptyFields {
    private WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
        System.out.println("Opened browser: " + browser);
    }

    @Test
    public void testEmptyFields() {

        Assert.assertTrue(driver.findElement(By.id("login")).isDisplayed(), "Validate Login page visible.");

        // Leave username and password fields empty
        driver.findElement(By.id("email")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");

        // Click on the login button
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Verify that the error message is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Invalid credentials!')]")).isDisplayed(), "Validate Error message Invalid credentials appears.");

        //Verify user is still on login page
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"login-content\"]")).isDisplayed(), "Validate User is not transferred to homepage.");
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}

