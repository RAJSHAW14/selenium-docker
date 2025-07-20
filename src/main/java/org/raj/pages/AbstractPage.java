package org.raj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    /**
     * Initializes WebDriver, explicit wait, and page elements.
     *
     * @param driver WebDriver instance
     */
    public AbstractPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    /**
     * Checks if the expected page is loaded.
     *
     * @return true if on the correct page
     */
    public abstract boolean isAt();

    /**
     * Waits until the element is visible.
     *
     * @param element Element to wait for
     */
    public void waitMethod(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits and clicks the given element.
     *
     * @param element Element to click
     */
    public void clickMethod(WebElement element){
        waitMethod(element);
        element.click();
    }

    /**
     * Waits and inputs text into the given element.
     *
     * @param element Element to enter text
     * @param value   Text to input
     */
    public void inputTextMethod(WebElement element, String value){
        waitMethod(element);
        element.sendKeys(value);
    }

    /**
     * Waits and returns the text of the element.
     *
     * @param element Element to read text from
     * @return Text content
     */
    public String getTextMethod(WebElement element){
        waitMethod(element);
        return element.getText();
    }
}
