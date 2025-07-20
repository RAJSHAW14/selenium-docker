package org.raj.pages.flight.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    /**
     * Confirms the page is visible by checking total price element.
     *
     * @return true if search page is loaded
     */
    @Override
    public boolean isAt() {
        waitMethod(passengersSelect);
        return passengersSelect.isDisplayed();
    }

    /**
     * Selects number of passengers from the dropdown by value.
     *
     * @param noOdPassengers Number of passengers to select
     */
    public void selectPassengers(String noOdPassengers){
        Select passengers = new Select(this.passengersSelect);
        passengers.selectByValue(noOdPassengers);
    }

    /**
     * Clicks the search flights button to initiate the search.
     */
    public void searchFlights(){
        clickMethod(searchFlightsBtn);
    }
}
