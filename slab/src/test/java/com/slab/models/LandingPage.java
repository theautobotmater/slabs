package com.slab.models;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import java.lang.invoke.MethodHandles;

public class LandingPage extends BasePage {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    private static LandingPage instance = null;
    private By locationInput = By.xpath("//div[.//div[text()='Location']]/input");
    private String locationDiv = "//div[text()='%s']";
    private By datePanel = By.xpath("//div[@data-testid='structured-search-input-field-dates-panel']");
    private By checkInButton = By.xpath("//div[text()='Check in']");
    private By checkOutButton = By.xpath("//div[text()='Check out']");
    private String datePicker = "//div[@data-testid='datepicker-day-%s']";
    private By addGuests = By.xpath("//div[text()='Add guests' or text()='Guests']");
    private By searchButton = By.xpath("//button[@data-testid='structured-search-input-search-button']");
    private String increaseDecreaseGuests = "//div[.//div[.//div[text()='%s']]]/div/button[@aria-label='%s value']";

    private LandingPage() {

    }

    public static LandingPage getInstance() {
        if(instance == null)
            instance = new LandingPage();
        return instance;
    }

    public void setLocation(String location) {
        logger.info("Setting location as " + location);
        driver.sendKeys(locationInput, location, true);
        driver.click(By.xpath(String.format(locationDiv, location)));
        driver.clickOutsideElement();
    }

    public void selectCheckInDate(String date) {
        logger.info("Selecting checkin date " + date);
        if(!driver.isDisplayed(datePanel)) {
            driver.click(checkInButton);
        }
        driver.click(By.xpath(String.format(datePicker, date)));
        driver.clickOutsideElement();
    }

    public void selectCheckOutDate(String date) {
        logger.info("Selecting checkout date " + date);
        if(!driver.isDisplayed(datePanel)) {
            driver.click(checkOutButton);
        }
        driver.click(By.xpath(String.format(datePicker, date)));
        driver.clickOutsideElement();
    }

    public void increaseDecreaseGuests(String action, String guest, int number) {
        logger.info(action + " " + guest);
        driver.click(addGuests);
        for(int i=1; i<=number; i++) {
            driver.click(By.xpath(String.format(increaseDecreaseGuests, guest, action)));
        }
        driver.clickOutsideElement();
    }

    public void clickSearchButton() {
        logger.info("Clicking search button");
        driver.click(searchButton);
    }

}
