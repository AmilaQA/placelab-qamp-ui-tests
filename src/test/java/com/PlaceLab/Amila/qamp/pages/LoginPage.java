package com.PlaceLab.Amila.qamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    private final static By LOGIN_HEADER = By.cssSelector("#login > img");
    private final static By LOGIN_FORM = By.cssSelector("#login_form");
    private final static By LOGIN_PAGE = By.id("login");
    private final static String EXPECTED_PAGE_TITLE = "PlaceLab";
    private final static By EMAIL_INPUT = By.id("email");
    private final static By PASSWORD_INPUT = By.id("password");
    private final static By LOGIN_SUBMIT_BTN = By.xpath("//input[@type='submit']");
    private final static By ACTUAL_ERROR_MESSAGE_INVALID_CRED = By.className("error-area");
    private final static By FORGOT_PASSWORD_FIELD = By.className("link-btn");

    private final static By INVALID_CREDENTIALS = By.xpath("//div[contains(text(), 'Invalid credentials!')]");
    private final static By LOGIN_PAGE_INV = By.xpath("//*[@id=\"login-content\"]");
    private final WebDriver driver;

    public LoginPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateLoginPageContent() {
        final String actualPageTitle = driver.getTitle();

        Assert.assertEquals(actualPageTitle, EXPECTED_PAGE_TITLE, "Validate page title is correct");
        Assert.assertTrue(driver.findElement(LOGIN_PAGE).isDisplayed(), "Login page visible.");
        final boolean isHeaderDisplayed = driver.findElement(LOGIN_HEADER).isDisplayed();
        final boolean isLoginFormDisplayed = driver.findElement(LOGIN_FORM).isDisplayed();
        Assert.assertTrue(isLoginFormDisplayed, "Validate login form is displayed");
        Assert.assertTrue(isHeaderDisplayed, "Validate header is displayed");
    }

    public void enterCredentials(final String email, final String password) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clearLoginFormInput() {
        driver.findElement(EMAIL_INPUT).clear();
        driver.findElement(PASSWORD_INPUT).clear();
    }

    public void clickLoginSubmitButton() {
        driver.findElement(LOGIN_SUBMIT_BTN).click();
    }

    public void errorMessageInvalidCredentials() {

        final boolean isErrorMessageDisplayed = driver.findElement(ACTUAL_ERROR_MESSAGE_INVALID_CRED).isDisplayed();
        Assert.assertTrue(isErrorMessageDisplayed, "Validate error message is displayed");
        final String actualErrorMessage = driver.findElement(ACTUAL_ERROR_MESSAGE_INVALID_CRED).getText();
        final String expectedErrorMessage = "Invalid credentials!";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Validate error message is correct");
    }

    public void clickForgotPasswordField() {
        driver.findElement(FORGOT_PASSWORD_FIELD).click();
    }



    public void invalidCredentialsMessage() {

        Assert.assertTrue(driver.findElement(INVALID_CREDENTIALS).isDisplayed(), "Validate Error message Invalid credentials appears.");
        Assert.assertTrue(driver.findElement(LOGIN_PAGE_INV).isDisplayed(), "Validate User is not transferred to homepage.");
    }

}

