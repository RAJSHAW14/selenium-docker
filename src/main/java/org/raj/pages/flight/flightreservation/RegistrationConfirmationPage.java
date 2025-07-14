package org.raj.pages.flight.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.raj.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {
    @FindBy (id = "go-to-flights-search")
    private WebElement goToFlightSearchBtn;

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        waitMethod(goToFlightSearchBtn);
        return goToFlightSearchBtn.isDisplayed();
    }

    public void goToFlightSearch(){
        clickMethod(goToFlightSearchBtn);
    }
}
