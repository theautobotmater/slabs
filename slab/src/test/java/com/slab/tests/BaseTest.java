package com.slab.tests;

import com.slab.models.BasePage;
import com.slab.models.LandingPage;
import com.slab.models.PropertyPage;
import com.slab.models.ResultsPage;
import org.junit.AfterClass;

public class BaseTest {

    LandingPage landingPage = LandingPage.getInstance();
    ResultsPage resultsPage = ResultsPage.getInstance();
    PropertyPage propertyPage = PropertyPage.getInstance();

    @AfterClass
    public static void cleanup() {
        BasePage.driver.exit();
    }

}
