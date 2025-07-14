package org.raj.pages.flight.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.raj.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {
    @FindBy (id = "go-to-flights-search")
    private WebElement goToFlightSearchBtn;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        waitMethod(goToFlightSearchBtn);
        return goToFlightSearchBtn.isDisplayed();
    }

    public String getFirstName(){
        return getTextMethod(firstNameElement);
    }

    public void goToFlightSearch(){
        clickMethod(goToFlightSearchBtn);
    }
}
