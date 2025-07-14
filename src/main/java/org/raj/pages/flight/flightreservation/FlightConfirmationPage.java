package org.raj.pages.flight.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.raj.pages.AbstractPage;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class FlightConfirmationPage extends AbstractPage {
    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(xpath = "//form[@class='row g-3']//div[contains(text(),'Total Price')]/following-sibling::div/p")
    private WebElement totalPriceTxt;

    @FindBy(xpath = "//form[@class='row g-3']//div[contains(text(),'Tax')]/following-sibling::div/p")
    private WebElement taxTxt;

    @FindBy(xpath = "//form[@class='row g-3']//div[contains(text(),'Flight Confirmation')]/following-sibling::div/p")
    private WebElement flightConfirmationTxt;

    public FlightConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        waitMethod(totalPriceTxt);
        return totalPriceTxt.isDisplayed();
    }
    
    public String getPrice(){
        String confirmation = getTextMethod(flightConfirmationTxt);
        String price = getTextMethod(totalPriceTxt);
        log.info("Flight Confirmation : {}",confirmation);
        log.info("Total Price : {}", price);
        return getTextMethod(totalPriceTxt);
    }
}
