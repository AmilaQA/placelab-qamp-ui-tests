package com.PlaceLab.Amila.qamp.LoginFunctionalityTests;

import com.PlaceLab.Amila.qamp.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//TC 05 - Forgot Password Functionality - Verify user can reset their password using the "Forgot Password" feature.
public class LoginFunctionalityForgotPassword {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest

    public void setup(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
        System.out.println("Opened browser: " + browser);
    }

    @Test
    @Parameters("password")
    public void testForgotPassword(String password) {

        Assert.assertTrue(driver.findElement(By.id("login")).isDisplayed(), "Verify if Login page is visible.");

        // Click on the "Forgot Password" link
        driver.findElement(By.className("link-btn")).click();

        //Verify Forgot Password page is opened
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Change your password')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//small[contains(text(), \"Let's find your account\")]")).isDisplayed());

        // Enter the registered email address
        driver.findElement(By.id("email")).sendKeys("amila.gicic@gmail.com");

        //Click on the "Continue" button
        driver.findElement(By.cssSelector("input[type=\"submit\"].btn")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Verify page with message "We have sent you a link to change your password" is displayed
        Assert.assertTrue(driver.findElement(By.id("login")).isDisplayed());

        //Verify text message is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'We have sent you a link to change your password')]")).isDisplayed());
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}


