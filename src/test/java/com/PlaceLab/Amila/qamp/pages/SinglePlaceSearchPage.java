package com.PlaceLab.Amila.qamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SinglePlaceSearchPage {
    private final static By SPS_NAV_BAR = By.xpath("/html/body/div[2]");
    private final static String SPS_EXPECTED_URL = "https://demo.placelab.com/dashboard/traffic";
    private final static By SPS_DROPDOWN_MENI_CLICK = By.xpath("//*[@id=\"create-menu\"]/i");
    private final static By SPS_DROPDOWN_MENI_OPEN = By.xpath("/html/body/div[2]/div[2]");
    private final static By TITLE_PLACES = By.xpath("/html/body/div[2]/div[2]/div/div[2]/h6");
    private final static By SINGLE_PLACE_SEARCH_FIELD_DROPDOWN_MENI = By.cssSelector("#singleplacesearch");
    private final static By SINGLE_PLACE_SEARCH_BTN = By.cssSelector("#singleplacesearch");
    private final static String SPS_EXPECTED_URL_1 = "https://demo.placelab.com/places/single_place_searches/new";
    private final static By SPS_PAGE_HEADER = By.cssSelector("#single_poi_dialog > div.create-report-header > div");
    private final static By VISIBLE_REPORT_CONTENT = By.cssSelector("#sp_query");
    private final static By REQUIRED_FIELDS_MARK = By.cssSelector("#single_poi_dialog > div.required-fields-notice > span");
    private final static By USAGE_PIE_CALCULATOR = By.className("usage-calculator");
    private final static By CREATE_SPS_REPORT_BTN = By.cssSelector("button.btn.large-btn.run-btn.run-all-btn");
    private final static By REPORT_NAME = By.cssSelector("#name");
    private final static By PLACE_NAME = By.cssSelector("#single_text");
    private final static By CREATE_REPORT_BTN_DISABLED = By.cssSelector("button.btn.large-btn.run-btn.run-all-btn[disabled='disabled']");
    private final static By LOCATION_NAME = By.cssSelector("#location_name");
    private final static By AUTOCOMPLETE_DROPDOWN = By.cssSelector(".typeahead.dropdown-menu");
    private final static By POP_UP = By.xpath("/html/body/div[10]/div[3]/div/button[1]");
    private final static By REPORT_PAGE = By.cssSelector("#action-navbar > h2");
    private final WebDriver driver;

    public SinglePlaceSearchPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateSinglePlacePageContent() {

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, SPS_EXPECTED_URL, "Verify dashboard traffic page successfully opened.");
        Assert.assertTrue(driver.findElement(SPS_NAV_BAR).isDisplayed(), "Verify if Navigation bar is visible.");

        driver.findElement(SPS_DROPDOWN_MENI_CLICK).click();
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);

        Assert.assertTrue(driver.findElement(SPS_DROPDOWN_MENI_OPEN).isDisplayed(), "Verify dropdown meni opens");
        Assert.assertTrue(driver.findElement(TITLE_PLACES).isDisplayed(), "Verify title PLACES is visible in dropdown meni");
        Assert.assertTrue(driver.findElement(SINGLE_PLACE_SEARCH_FIELD_DROPDOWN_MENI).isDisplayed(), "Verify Single place search button is visible");
    }

    public void clickSPSbuttonDropdownMeni() {
        //Click on Single place search button
        driver.findElement(SINGLE_PLACE_SEARCH_BTN).click();
        //Verify SPS page for creating report opened
        String actualUrl1 = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl1, SPS_EXPECTED_URL_1, "Verify page for report creating successfully opened.");
    }

    public void validateCreateSPSreportPageContent() {

        Assert.assertTrue(driver.findElement(SPS_PAGE_HEADER).isDisplayed(), "Verify Header - Create Single Place Search Report - header is visible");
        Assert.assertTrue(driver.findElement(VISIBLE_REPORT_CONTENT).isDisplayed(), "Verify Report content - Single Place Search is visible");
        Assert.assertTrue(driver.findElement(REQUIRED_FIELDS_MARK).isDisplayed(), "Verify Report content - Single Place Search is visible");
        Assert.assertTrue(driver.findElement(USAGE_PIE_CALCULATOR).isDisplayed(), "Verify usage calculator (usage pie) is displayed");
        Assert.assertTrue(driver.findElement(CREATE_SPS_REPORT_BTN).isDisplayed(), "Verify click button Create report is displayed");
        Assert.assertTrue(driver.findElement(REPORT_NAME).isDisplayed(), "Verify report name field is displayed");
        Assert.assertTrue(driver.findElement(PLACE_NAME).isDisplayed(), "Verify place name is displayed");
        Assert.assertTrue(driver.findElement(CREATE_REPORT_BTN_DISABLED).isDisplayed(), "Verify Create report button is disabled");
    }

    public void enterLocation(String location) {

        Assert.assertTrue(driver.findElement(LOCATION_NAME).isDisplayed(), "Verify location name field is visible");
        WebElement locationInput = driver.findElement(LOCATION_NAME);
        locationInput.sendKeys(location);

        WebDriverWait waitForAutocompleteLocation = new WebDriverWait(driver, Duration.ofSeconds(2));
        waitForAutocompleteLocation.until(ExpectedConditions.visibilityOfElementLocated(AUTOCOMPLETE_DROPDOWN));

        locationInput.sendKeys(Keys.ARROW_DOWN);
        locationInput.sendKeys(Keys.ENTER);
    }

    public void isThisYourLocationPopup() {

        WebDriverWait waitForPopup = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitForPopup.until(ExpectedConditions.visibilityOfElementLocated(POP_UP));
        driver.findElement(POP_UP).click();
    }

    public void enterPlaceName(String place) {

        driver.findElement(PLACE_NAME).sendKeys(place);
    }

    public void clickCreateReportBTN() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(CREATE_SPS_REPORT_BTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(REPORT_PAGE));
    }
}

