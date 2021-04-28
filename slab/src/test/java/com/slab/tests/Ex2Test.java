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

public class Ex2Test extends BaseTest {

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

        logger.info("Click more filters button");
        resultsPage.clickMoreFiltersButton();

        logger.info("Adding 5 bedrooms");
        resultsPage.increaseDecreaseRooms("increase", "Bedrooms", 5);

        logger.info("Checking Pool");
        resultsPage.checkFacility("Pool");

        logger.info("Click show button");
        resultsPage.clickModalSubmitButton();

        logger.info("Checking properties from 1st page has at least 5 bedrooms");
        Wait.sleep(2000); //in order for properties to reload
        List<String> numOfBedrooms = new GetByRegex().getByRegex(">(\\d{1,}) bedrooms", resultsPage.getPageSource());
        for(String numOfBedroom : numOfBedrooms) {
            int num = Integer.parseInt(numOfBedroom);
            Assert.assertTrue("Not enough bedrooms", num >= 5);
        }

        WebElement property = resultsPage.getListOfProperties().get(0);
        logger.info("Clicking on the 1st property");
        resultsPage.clickProperty(property);

        logger.info("Checking if property has Pool under facilities");
        propertyPage.clickShowAllAmenitiesButton();
        Assert.assertTrue("No pool", propertyPage.checkPropertyHasPool());

    }
}
