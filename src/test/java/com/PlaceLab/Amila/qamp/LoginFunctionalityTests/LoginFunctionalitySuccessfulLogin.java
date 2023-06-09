package com.PlaceLab.Amila.qamp.LoginFunctionalityTests;

import com.PlaceLab.Amila.qamp.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//TC 01 - Successful Login - Verify user can successfully log in with valid credentials.
public class LoginFunctionalitySuccessfulLogin {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest

    public void setup(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
        System.out.println("Opened browser: " + browser);
    }

    @Test
    @Parameters({"password", "email"})
    public void testSuccessfulLogin(String password, String email) {
        // Validate login page Open
        Assert.assertTrue(driver.findElement(By.id("login")).isDisplayed(), "Login page visible.");

        // Enter valid username and password
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);

        // Click on the login button
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // Verify successful login by checking the homepage/dashboard
        String expectedUrl = "https://demo.placelab.com/dashboard/traffic";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login was successful.");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Verify that user is logged in by checking the presence of an element on the dashboard.
        Assert.assertTrue(driver.findElement(By.id("user-name")).isDisplayed(), "Verify if user name found.");
        Assert.assertTrue(driver.findElement(By.id("user-role")).isDisplayed(), "Verify if User role found.");
        Assert.assertTrue(driver.findElement(By.className("main-content")).isDisplayed(), "Verify if Report page visible.");
        //I am not sure if this correction that I made is OK  because on this way I expose personal info like name, surname..??
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-title='Amila Gicic (QAmp2023/Group admin)']")).isDisplayed(), "Verify if user name and user role found.");
        //Verify if Sign out button is visible and active.
        driver.findElement(By.cssSelector("#user-name > i")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#user-name > i")).isDisplayed(), "Verify if Sign out button is visible.");
        driver.findElement(By.linkText("Sign out")).click();
        //Verify after Sign out user is transferred onto login page
        Assert.assertTrue(driver.findElement(By.id("login")).isDisplayed(), "Login page visible.");
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}


