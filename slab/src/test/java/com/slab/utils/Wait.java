package com.slab.utils;

import com.slab.models.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.invoke.MethodHandles;

public class Wait {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /**
     * Wait for an element to be visible
     *
     * @param element WebElement to wait for
     * @param timeout maximum wait time
     */
    public static void waitForElementVisible(WebElement element, long timeout) {
        logger.debug("Waiting for element to be visible.");
        WebDriverWait wait = new WebDriverWait(BasePage.driver.webDriver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for an element to be clickable
     *
     * @param element WebElement to wait for.
     * @param timeout maximum wait time
     */
    public static void waitForElementToBeClickable(WebElement element, long timeout) {
        logger.debug("Waiting for element to be clickable.");
        WebDriverWait wait = new WebDriverWait(BasePage.driver.webDriver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for an element to be visible
     *
     * @param by      - element to wait for
     * @param timeoutInSeconds - maximum wait time in seconds
     */
    public static void waitForElementVisible(By by, long timeoutInSeconds) {
        logger.debug("Waiting for element to be visible. Element = " + by);
        WebDriverWait wait = new WebDriverWait(BasePage.driver.webDriver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Wait for an element to be present
     *
     * @param by      - element to wait for
     * @param timeout - maximum wait time
     */
    public static void waitForElementPresent(By by, long timeout) {
        logger.debug("Waiting for element to be displayed. Element = " + by);
        WebDriverWait wait = new WebDriverWait(BasePage.driver.webDriver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Wait for an element to be clickable
     *
     * @param by      - element to wait for to be clickable
     * @param timeout - maximum wait time
     */
    public static void waitForElementToBeClickable(By by, long timeout) {
        WebDriverWait wait = new WebDriverWait(BasePage.driver.webDriver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementPresent(By by) {
        waitForElementPresent(by, 30L);
    }

    public static void waitForElementVisible(By by) {
        waitForElementVisible(by, 30L);
    }

    public static void waitForElementVisible(WebElement element) {
        waitForElementVisible(element, 30L);
    }

    public static void waitForElementToBeClickable(WebElement element) {
        waitForElementToBeClickable(element, 30L);
    }

    public static void waitForElementToBeClickable(By by) {
        waitForElementToBeClickable(by, 30L);
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
