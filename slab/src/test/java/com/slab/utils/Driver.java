package com.slab.utils;

import com.slab.models.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    
    private static Driver instance = null;
    public RemoteWebDriver webDriver;

    private Driver() {
        setNewDriver();
    }

    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    public void setNewDriver() {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        } finally {
            webDriver = null;
        }
        

        logger.info("Initializing webdriver");

        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
            webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            webDriver.manage().deleteAllCookies();
            logger.info("Selenium screen resolution: " + webDriver.manage().window().getSize().toString());
            webDriver.manage().window().maximize();
            logger.info("Initialized webDriver.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.error("Invalid URL: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to initialize driver " + e.getMessage());
        }

    }

    public void setDriver(RemoteWebDriver driver) {
        this.webDriver = driver;
    }

    public void exit() {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        } finally {
            webDriver = null;
        }
    }

    /**
     * Click on an element
     *
     * @param by      element locator
     * @param actions optional parameter, used if the conventional click doesn't work, tries click using actions
     */
    public void click(By by, boolean... actions) {
        try {
            isDisplayed(by);
            boolean clickActions = ((actions != null) && (actions.length > 0)) && actions[0];
            if (clickActions) {
                logger.info("Trying click using actions on " + by);
                clickElementUsingActions(by);
            } else {
                webDriver.findElement(by).click();
            }
        } catch (NoSuchElementException | StaleElementReferenceException | ElementClickInterceptedException e) {
            logger.error("Cannot click element with locator " + by);
            logger.info("Trying click using actions on " + by);
            clickElementUsingActions(by);
        }
    }

    /**
     * Click on an element
     *
     * @param element WebElement
     * @param actions optional parameter, used if the conventional click doesn't work, tries click using actions
     */
    public void click(WebElement element, boolean... actions) {
        Wait.waitForElementVisible(element);
        try {
            Wait.waitForElementToBeClickable(element);
            element.click();
        } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
            logger.error("Cannot click element with locator " + element);
            boolean clickActions = ((actions != null) && (actions.length > 0)) && actions[0];
            if (clickActions) {
                logger.info("Trying click using actions on " + element);
                clickElementUsingActions(element);
            }
        }

    }

    /**
     * Hover element
     *
     * @param element web element
     */
    public void hover(WebElement element) {
        Wait.waitForElementVisible(element);
        Actions action = new Actions(webDriver);
        action.moveToElement(element).perform();
    }

    /**
     * Click an element using Actions class
     *
     * @param by element locator
     */
    public void clickElementUsingActions(By by) {
        logger.info("CLicking on element: " + by);
        Actions actions = new Actions(BasePage.driver.webDriver);
        actions.moveToElement(BasePage.driver.webDriver.findElement(by));
        actions.click(BasePage.driver.webDriver.findElement(by));
        actions.build().perform();
    }

    /**
     * Click an element using Actions class
     *
     * @param element web element
     */
    public void clickElementUsingActions(WebElement element) {
        logger.info("CLicking on element: " + element);
        Actions actions = new Actions(BasePage.driver.webDriver);
        actions.moveToElement(element);
        actions.click(element);
        actions.perform();
    }

    /**
     * Fill in an input element
     *
     * @param by    element locator
     * @param keys  text to fill
     * @param clear optional parameter if previous text should be cleared before inserting the text
     */
    public void sendKeys(By by, String keys, boolean... clear) {
        Wait.waitForElementPresent(by);
        Wait.waitForElementToBeClickable(by);
        boolean shouldClear = ((clear != null) && (clear.length > 0)) && clear[0];
        try {
            if (shouldClear) clear(by);
            webDriver.findElement(by).sendKeys(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get element text
     *
     * @param by element locator
     */
    public String getElementText(By by) {
        Wait.waitForElementVisible(by, 10);
        logger.info("Get text of element: " + by);
        return webDriver.findElement(by).getText();
    }

    /**
     * Check if an element is displayed
     *
     * @param by element locator
     * @return true if element is displayed
     */
    public boolean isDisplayed(By by) {
        logger.info("Checking if element is displayed");
        try {
            Wait.waitForElementVisible(by);
            return webDriver.findElement(by).isDisplayed();
        } catch (NullPointerException | NoSuchElementException | TimeoutException | StaleElementReferenceException | ElementNotVisibleException e) {
            logger.error("Cannot find element with locator " + by);
            return false;
        }
    }

    /**
     * Clear input field
     *
     * @param by input field locator
     */
    private void clear(By by) {
        logger.info("Clearing filed");
        Wait.waitForElementPresent(by);
        try {
            webDriver.findElement(by).clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Getting the list of webelements
     *
     * @param by locator
     * @return list of elements
     */
    public List<WebElement> getListOfWebelements(By by) {
        logger.info("Getting the list of webelements");
        Wait.waitForElementPresent(by);
        return webDriver.findElements(by);
    }

    public void clickOutsideElement(){
        click(By.xpath("//html"));
    }
    
}
