package com.PlaceLab.Amila.qamp.tests;

import com.PlaceLab.Amila.qamp.pages.LoginPage;
import com.PlaceLab.Amila.qamp.pages.ReportPage;
import com.PlaceLab.Amila.qamp.pages.SinglePlaceSearchPage;
import com.PlaceLab.Amila.qamp.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SinglePlaceSearch_SuccessfulCreateReport {
    private WebDriver driver;
    private LoginPage loginPage;
    private SinglePlaceSearchPage singlePlaceSearchPage;
    private ReportPage reportPage;

    @Parameters({"browser", "email", "password"})
    @BeforeTest

    public void setup(String browser, String email, String password) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
        System.out.println("Opened browser: " + browser);
        this.loginPage = new LoginPage(driver);
        loginPage.validateLoginPageContent();
        loginPage.enterCredentials(email, password);
        loginPage.clickLoginSubmitButton();
        this.singlePlaceSearchPage = new SinglePlaceSearchPage(driver);
        this.reportPage = new ReportPage(driver);
    }

    @Parameters({"location", "place"})
    @Test(priority = 1, description = "Verify user can successfully create SPS report",
            testName = "TC 01 Successful Create Single Place Search report")

    public void testSuccessfulCreateReport(String location, String place) {

        singlePlaceSearchPage.validateSinglePlacePageContent();
        singlePlaceSearchPage.clickSPSbuttonDropdownMeni();
        singlePlaceSearchPage.validateCreateSPSreportPageContent();
        singlePlaceSearchPage.enterLocation(location);
        singlePlaceSearchPage.isThisYourLocationPopup();
        singlePlaceSearchPage.enterPlaceName(place);
        singlePlaceSearchPage.clickCreateReportBTN();
        reportPage.verifyReportPageContent();
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}


