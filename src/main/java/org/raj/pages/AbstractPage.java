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
    public AbstractPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isAt();

    public void waitMethod(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickMethod(WebElement element){
        waitMethod(element);
        element.click();
    }

    public void inputTextMethod(WebElement element, String value){
        waitMethod(element);
        element.sendKeys(value);
    }

    public String getTextMethod(WebElement element){
        waitMethod(element);
        return element.getText();
    }
}
