package com.PlaceLab.Amila.qamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ReportPage {
    private final static String EXPECTED_URL_2 = "https://demo.placelab.com/queries";
    private final static By REPORT_CREATOR_NAME = By.xpath("//*[@id=\"table_queries\"]/tbody/tr[1]/td[9]/div/label");
    private final static By REPORT_PAGE_HEADER = By.cssSelector("#action-navbar");
    private final static By REPORT_QUERIES = By.cssSelector("#table_queries");
    private final static By REPORT_CHECKBOX = By.cssSelector("#table_queries > tbody > tr:nth-child(1) > td.large > div");
    private final static By POPUP_CONFIRM_BUTTON_DELETE = By.id("confirm-link");
    private final static By TRASH_BIN_ICON = By.xpath("//*[@id=\"action-delete\"]/a/i");
    private final static By CONFIRM_DELETE_LINK = By.xpath("//*[@id=\"confirm-link\"]");
    private final static By VERIFY_MESSAGE_DELETE_REPORT = By.id("alert-place");
    private final WebDriver driver;

    public ReportPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void verifyReportPageContent() {

        String actualUrl2 = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl2, EXPECTED_URL_2, "Verify page for successfully created report opened.");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        Assert.assertTrue(driver.findElement(REPORT_CREATOR_NAME).isDisplayed(), "Validate if name of the user created report is correct");
        Assert.assertTrue(driver.findElement(REPORT_PAGE_HEADER).isDisplayed(), "Validate page header is displayed");
        Assert.assertTrue(driver.findElement(REPORT_QUERIES).isDisplayed(), "Validate previously created reports are visible");
    }

    public void verifyReportName() {
        driver.get("https://demo.placelab.com/queries");
        WebElement reportNameElement = driver.findElement(By.linkText("Vrelo Bosne, Sarajevo, Bosnia and Herzegovina"));
        Assert.assertTrue(reportNameElement.isDisplayed());
        String expectedReportName = "Vrelo Bosne, Sarajevo, Bosnia and Herzegovina";
        String actualReportName = reportNameElement.getText();
        Assert.assertEquals(expectedReportName, actualReportName);
    }

    public void openReport(final String reportName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(" //a[contains(text(), '" + reportName + "')]"))));
        driver.findElement(By.xpath("//a[contains(text(), '" + reportName + "')]")).click();
    }

    public void deleteReport(final String reportName) {
        driver.get("https://demo.placelab.com/queries");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Vrelo Bosne, Sarajevo, Bosnia and Herzegovina"))));

        //click on checkbox in front of report with name "Vrelo Bosne, Sarajevo, Bosnia and Herzegovina"
        driver.findElement(REPORT_CHECKBOX).click();
        //click on trash bin icon to delete report
        driver.findElement(TRASH_BIN_ICON).click();
        //confirm delete previously checked report - popup message
        wait.until(ExpectedConditions.elementToBeClickable(POPUP_CONFIRM_BUTTON_DELETE));
        driver.findElement(CONFIRM_DELETE_LINK).click();
    }

    public void verifyMessageReportSuccessfullyDeleted() {

        WebElement messageSuccessfulDeletedReport = driver.findElement(VERIFY_MESSAGE_DELETE_REPORT);
        Assert.assertTrue(messageSuccessfulDeletedReport.isDisplayed());
        String expectedMessage = "×\n" +
                "Successfully deleted selected queries";
        String actualMessage = messageSuccessfulDeletedReport.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}







