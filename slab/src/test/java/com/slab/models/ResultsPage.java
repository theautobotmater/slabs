package com.slab.models;

import com.slab.utils.Wait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class ResultsPage extends BasePage {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    private static ResultsPage instance = null;

    private By location = By.xpath("//button[.//span[text()='Location']]/div");
    private By date = By.xpath("//button[.//span[text()='Check in / Check out']]/div");
    private By guests = By.xpath("//button[.//span[text()='Guests']]/div");
    private String sectionResults = "//div/section[contains(., '%s') and contains(., '%s')]";
    private By numOfGuests = By.xpath("//span[text()='2 guests' or text()='1 guest']");
    private String increaseDecreaseRooms = "//div[.//div[.//div[text()='%s']]]/div/button[@aria-label='%s value']";
    private By moreFiltersButton = By.xpath("//div[@data-testid='menuItemButton-dynamicMoreFilters']");
    private String facilitiesButton = "//div[text()='%s']";
    private By modalSubmitButton = By.xpath("//button[@data-testid='more-filters-modal-submit-button']");
    private By listOfProperties = By.xpath("//div[@class='_8ssblpx']");
    private By hoveredPropertyMap = By.xpath("//div[contains(@style,'z-index: 9999')]");
    private By modalMap = By.xpath("//div[@class='_9m9ayv']");
    private By houseLocation = By.xpath("(//div[@class='_b14dlit'])[1]");
    private String propertyName = "//div[.//div[contains(.,'%s')]]/div[@class='_5kaapu']/span";

    private ResultsPage() {

    }

    public static ResultsPage getInstance() {
        if(instance == null)
            instance = new ResultsPage();
        return instance;
    }

    public String getFilterLocation() {
        logger.info("Getting filter location");
        return driver.getElementText(location);
    }

    public String getFilterDate() {
        logger.info("Getting filter date");
        return driver.getElementText(date);
    }

    public String getFilterGuests() {
        logger.info("Getting filter guests");
        return driver.getElementText(guests);
    }

    public boolean checkSearchResultsHeaderIsCorrectlyDisplayed(String date, String guests) {
        logger.info("Checking Search Results Header is correctly displayed");
        return driver.isDisplayed(By.xpath(String.format(sectionResults, date, guests)));
    }

    public boolean checkPropertiesCanAccommodateAtLeast3Guests() {
        logger.info("Checking properties can accomodate at least 3 guests");
        return driver.isDisplayed(numOfGuests);
    }

    public void increaseDecreaseRooms(String action, String guest, int number) {
        logger.info(action + " " + guest);
        for(int i=1; i<=number; i++) {
            driver.click(By.xpath(String.format(increaseDecreaseRooms, guest, action)));
        }
    }

    public void clickMoreFiltersButton() {
        logger.info("Clicking on More Filters button");
        driver.click(moreFiltersButton);
    }

    public void checkFacility(String facility) {
        logger.info("Checking " + facility);
        driver.click(By.xpath(String.format(facilitiesButton, facility)));
    }

    public void clickModalSubmitButton() {
        logger.info("Clicking modal submit button");
        Wait.sleep(2000);
        driver.click(modalSubmitButton);
    }

    public String getPageSource() {
        logger.info("Getting page source");
        return driver.webDriver.getPageSource();
    }

    public List<WebElement> getListOfProperties() {
        logger.info("Getting the list of properties");
        return driver.getListOfWebelements(listOfProperties);
    }

    public void clickProperty(WebElement e) {
        logger.info("Clicking on property");
        driver.click(e);
    }

    public void hoverProperty(WebElement e) {
        logger.info("Mouse over property");
        driver.hover(e);
    }

    public WebElement getPinFromMap() {
        logger.info("Getting pin webelement when property is hovered");
        return driver.webDriver.findElement(hoveredPropertyMap);
    }

    public void clickOnPin(WebElement e) {
        logger.info("Clicking on pin");
        driver.click(e, true);
    }

    public String getModalMapText() {
        logger.info("Getting modal map text");
        return driver.getElementText(modalMap);
    }

    public String getHouseLocation() {
        logger.info("Getting house location");
        return driver.getElementText(houseLocation);
    }

    public String getPropertyName(String location) {
        logger.info("Getting property name of house " + location);
        return driver.getElementText(By.xpath(String.format(propertyName, location)));
    }

}
