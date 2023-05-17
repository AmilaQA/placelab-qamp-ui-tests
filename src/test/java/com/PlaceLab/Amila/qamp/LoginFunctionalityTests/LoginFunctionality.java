package com.PlaceLab.Amila.qamp.LoginFunctionalityTests;

import com.PlaceLab.Amila.qamp.pages.ForgotPWDPage;
import com.PlaceLab.Amila.qamp.pages.HomePage;
import com.PlaceLab.Amila.qamp.pages.LoginPage;
import com.PlaceLab.Amila.qamp.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.UUID;


public class LoginFunctionality {


    private WebDriver driver;
    private final SoftAssert softAssert = new SoftAssert();
    private LoginPage loginPage;
    private HomePage homePage;
    private ForgotPWDPage forgotPWDPage;

    @Parameters("browser")
    @BeforeTest(alwaysRun = true, groups = {"Positive", "Negative"})
    public void setup(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
        System.out.println("Opened browser: " + browser);
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.forgotPWDPage = new ForgotPWDPage(driver);
    }

    @Test(priority = 1, description = "Validate user is able to successfully login with valid credentials.",
            testName = "TC 01 - Successful Login - valid credentials.", groups = {"validCredentials", "Positive"})
    @Parameters({"password", "email", "expectedUserRole"})

    public void testSuccessfulLogin(String password, String email, String expectedUserRole) {

        loginPage.validateLoginPageContent();
        loginPage.enterCredentials(email, password);
        loginPage.clickLoginSubmitButton();

        homePage.validateUserLoggedInHomePage();
        homePage.validateUserRole(expectedUserRole);
        homePage.clickSignOutButton();
        loginPage.validateLoginPageContent();
    }

    @Test(priority = 2, description = "Validate user is not able to login with invalid credentials.",
            testName = "TC 02 Invalid credentials - error message", groups = {"invalidCredentials", "Negative"})
    public void testInvalidCredentials() {
        loginPage.validateLoginPageContent();
        final String randomPassword = UUID.randomUUID().toString();
        final String randomEmail = UUID.randomUUID().toString();
        loginPage.enterCredentials(randomEmail, randomPassword);
        loginPage.clickLoginSubmitButton();
        loginPage.errorMessageInvalidCredentials();
    }

    @Test(priority = 4, description = "Validate Forgot Password Functionality is working properly.",
            testName = "TC 03 Forgot Password Functionality - reset password", groups = {"forgotPassword", "Negative"})
    @Parameters("email")
    public void testForgotPassword(String email) {

        loginPage.validateLoginPageContent();
        loginPage.clickForgotPasswordField();
        forgotPWDPage.validateForgotPasswordPageOpened(email);
        forgotPWDPage.enterEmailForgotPWDandClick(email);
        forgotPWDPage.linkToChangePWD();
    }

    @Test(priority = 3, description = "Validate user is not able to login with leaving the credentials fields empty.",
            testName = "TC 04 Empty fields - error message.", groups = {"emptyFields", "Negative"})
    public void testEmptyFields() {

        loginPage.validateLoginPageContent();
        loginPage.clearLoginFormInput();
        loginPage.clickLoginSubmitButton();
        loginPage.invalidCredentialsMessage();
    }

    @AfterTest(alwaysRun = true, groups = {"Positive", "Negative"})
    public void tearDown() {
        driver.close();
    }
}














