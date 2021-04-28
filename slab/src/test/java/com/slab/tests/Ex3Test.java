package com.slab.tests;

import com.slab.models.BasePage;
import com.slab.utils.DateUtils;
import com.slab.utils.GetByRegex;
import com.slab.utils.Wait;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Ex3Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    @Test
    public void test() {
        logger.info("Go to airbnb.com");
        BasePage.driver.webDriver.get("https://airbnb.com");

        logger.info("Set Rome, Italy as location");
        landingPage.setLocation("Rome, Italy");

        String checkInDate = new DateUtils().getDate("yyyy-MM-dd", 7);
        String checkOutDate = new DateUtils().getDate("yyyy-MM-dd", 14);
        logger.info("Setting checkin date - " + checkInDate);
        landingPage.selectCheckInDate(checkInDate);

        logger.info("Setting checkout date - " + checkOutDate);
        landingPage.selectCheckOutDate(checkOutDate);

        logger.info("Adding 2 adults as guests");
        landingPage.increaseDecreaseGuests("increase", "Adults", 2);

        logger.info("Adding 1 child as guest");
        landingPage.increaseDecreaseGuests("increase", "Children", 1);

        logger.info("Click search button");
        landingPage.clickSearchButton();

        WebElement property = resultsPage.getListOfProperties().get(0);
        logger.info("Mouse over 1st property");
        resultsPage.hoverProperty(property);

        WebElement pin = resultsPage.getPinFromMap();
        logger.info("Clicking on pin");
        resultsPage.clickOnPin(pin);

        logger.info("Getting property details from results search and modal map");
        String modalMap = resultsPage.getModalMapText();
        String propertyLocation = resultsPage.getHouseLocation();
        String house = propertyLocation.split(" in ")[0];
        String location = propertyLocation.split(" in ")[1];
        String propertyName = resultsPage.getPropertyName(propertyLocation);

        logger.info("Checking all info are correct");
        Assert.assertTrue("house not in modal", modalMap.contains(house));
        Assert.assertTrue("location not in modal", modalMap.contains(location));
        Assert.assertTrue("name not in modal", modalMap.contains(propertyName));
    }
}
