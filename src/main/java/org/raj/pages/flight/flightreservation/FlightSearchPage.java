package org.raj.pages.flight.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.raj.pages.AbstractPage;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengersSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsBtn;

    public FlightSearchPage(WebDriver driver){
        super(driver);
    }
    @Override
    public boolean isAt() {
        waitMethod(passengersSelect);
        return passengersSelect.isDisplayed();
    }

    public void selectPassengers(String noOdPassengers){
        Select passengers = new Select(this.passengersSelect);
        passengers.selectByValue(noOdPassengers);
    }

    public void searchFlights(){
        clickMethod(searchFlightsBtn);
    }
}
