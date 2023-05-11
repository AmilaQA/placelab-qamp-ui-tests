package com.PlaceLab.Amila.qamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
    private final WebDriver driver;
    private LoginPage loginPage;
    private final static By USER_ROLE = By.id("user-role");
    private final static String EXPECTED_URL = "https://demo.placelab.com/dashboard/traffic";
    private final static By MAIN_CONTENT = By.className("main-content");
    private final static By USER_NAME = By.className("main-content");
    private final static By USER_DETAILS = By.cssSelector("[data-title='Amila Gicic (QAmp2023/Group admin)']");
    private final static By SIGN_OUT_BTN = By.linkText("Sign out");
    private final static By SIGN_OUT_LINK = By.cssSelector("#user-name > i");

    public HomePage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateUserRole(final String expectedUserRole) {
        final String actualUserRole = driver.findElement(USER_ROLE).getText();
        Assert.assertEquals(actualUserRole, expectedUserRole, "Validate user role is displayed.");
    }

    public void validateUserLoggedInHomePage() {
        // Verify successful login by checking the homepage/dashboard

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, EXPECTED_URL, "Login was successful.");
        Assert.assertTrue(driver.findElement(USER_NAME).isDisplayed(), "Verify if user name found.");
        Assert.assertTrue(driver.findElement(MAIN_CONTENT).isDisplayed(), "Verify if Report page visible.");
        Assert.assertTrue(driver.findElement(USER_DETAILS).isDisplayed(), "Verify if user name and user role found.");

    }

    public void clickSignOutButton() {
        //Verify if Sign out button is visible and active.
        driver.findElement(SIGN_OUT_LINK).click();
        Assert.assertTrue(driver.findElement(SIGN_OUT_LINK).isDisplayed(), "Verify if Sign out button is visible.");
        driver.findElement(SIGN_OUT_BTN).click();

    }
}
