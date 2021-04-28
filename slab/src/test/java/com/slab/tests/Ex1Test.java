package com.slab.tests;

import com.slab.models.BasePage;
import com.slab.utils.DateUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import java.lang.invoke.MethodHandles;

public class Ex1Test extends BaseTest {

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

        String dateSectionFormat = new DateUtils().getFilterDate(7, 14, "-");
        String dateFilterFormat = new DateUtils().getFilterDate(7, 14, "–");

        String locationFilter = resultsPage.getFilterLocation();
        String dateFilter = resultsPage.getFilterDate();
        String guestsFilter = resultsPage.getFilterGuests();

        Assert.assertTrue("Search results header is not displayed correctly", resultsPage.checkSearchResultsHeaderIsCorrectlyDisplayed(dateSectionFormat, "3 guests"));
        Assert.assertEquals("Location filter is different", "Rome", locationFilter);
        Assert.assertEquals("Date filter is different", dateFilterFormat, dateFilter);
        Assert.assertEquals("Guests filter is different", "3 guests", guestsFilter);
        Assert.assertFalse("There are properties which could accommodate less than 3 guests", resultsPage.checkPropertiesCanAccommodateAtLeast3Guests());

    }
}
