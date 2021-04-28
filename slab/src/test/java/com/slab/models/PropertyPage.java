package com.slab.models;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import java.lang.invoke.MethodHandles;

public class PropertyPage extends BasePage {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    private static PropertyPage instance = null;

    private By showAmenitiesButton = By.xpath("//a[contains(text(), 'Show') and contains(text(), 'amenities')]");
    private By poolFacilityDiv = By.xpath("//div[contains(text(), 'Pool')]");

    private PropertyPage() {

    }

    public static PropertyPage getInstance() {
        if(instance == null)
            instance = new PropertyPage();
        return instance;
    }

    public void clickShowAllAmenitiesButton() {
        logger.info("Clicking Show All amenities button");
        driver.click(showAmenitiesButton);
    }

    public boolean checkPropertyHasPool() {
        logger.info("Checking if property has pool");
        return driver.isDisplayed(poolFacilityDiv);
    }

}
