package com.PlaceLab.Amila.qamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ForgotPWDPage {
    private final static By EMAIL_INPUT = By.id("email");
    private final static By CHANGE_YOUR_PASSWORD = By.xpath("//p[contains(text(), 'Change your password')]");
    private final static By LET_FIND_YOUR_ACCOUNT = By.xpath("//small[contains(text(), \"Let's find your account\")]");
    private final static By FORGOT_PWD_CONTINUE_BUTTON = By.cssSelector("input[type=\"submit\"].btn");
    private final static By FORGOT_PWD_NOTIFICATION_MESSAGE = By.xpath("//p[contains(text(), 'We have sent you a link to change your password')]");
    private final static By NEW_PWD_NOTIFICATION_MESSAGE = By.id("login");
    private final WebDriver driver;

    public ForgotPWDPage (final WebDriver webDriver) {
        this.driver = webDriver;
    }
    public void validateForgotPasswordPageOpened(final String email) {

        Assert.assertTrue(driver.findElement(CHANGE_YOUR_PASSWORD).isDisplayed());
        Assert.assertTrue(driver.findElement(LET_FIND_YOUR_ACCOUNT).isDisplayed());
    }

    public void enterEmailForgotPWDandClick(String email) {

        driver.findElement(EMAIL_INPUT).sendKeys(email);

        driver.findElement(FORGOT_PWD_CONTINUE_BUTTON).click();
    }

    public void linkToChangePWD() {

        //Verify page with message "We have sent you a link to change your password" is opened
        Assert.assertTrue(driver.findElement(NEW_PWD_NOTIFICATION_MESSAGE).isDisplayed());


        final boolean isLinkMessageDisplayed = driver.findElement(NEW_PWD_NOTIFICATION_MESSAGE).isDisplayed();
        Assert.assertTrue(isLinkMessageDisplayed, "Validate Link message is displayed");
        final String actualLinkMessage = driver.findElement(NEW_PWD_NOTIFICATION_MESSAGE).getText();
        final String expectedLinkMessage = "We have sent you a link to change your password\n" +
                "We have sent you an email that will alow you to\n" +
                "reset your password quickly and easily.\n" +
                "Email sent. Didn't get it?";
        Assert.assertEquals(actualLinkMessage, expectedLinkMessage, "Validate message with link is correct");

        //Verify text message is displayed
        Assert.assertTrue(driver.findElement(FORGOT_PWD_NOTIFICATION_MESSAGE).isDisplayed());
    }
}
