package com.PlaceLab.Amila.qamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ReportPage {
    private final static String EXPECTED_URL_2 = "https://demo.placelab.com/queries";
    private final static By REPORT_CREATOR_NAME = By.xpath("//*[@id=\"table_queries\"]/tbody/tr[1]/td[9]/div/label");
    private final static By REPORT_PAGE_HEADER = By.cssSelector("#action-navbar");
    private final static By REPORT_QUERIES = By.cssSelector("#table_queries");
    private final WebDriver driver;
    private LoginPage loginPage;

    public ReportPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void verifyReportPageContent() {

        String actualUrl2 = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl2, EXPECTED_URL_2, "Verify page for successfully created report opened.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(driver.findElement(REPORT_CREATOR_NAME).isDisplayed(), "Validate if name of the user created report is correct");
        Assert.assertTrue(driver.findElement(REPORT_PAGE_HEADER).isDisplayed(), "Validate page header is displayed");
        Assert.assertTrue(driver.findElement(REPORT_QUERIES).isDisplayed(), "Validate previously created reports are visible");

    }

}
