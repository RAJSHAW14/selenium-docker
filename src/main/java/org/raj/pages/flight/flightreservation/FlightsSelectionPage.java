package org.raj.pages.flight.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.raj.pages.AbstractPage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage extends AbstractPage {

    public FlightsSelectionPage(WebDriver driver){
        super(driver);
    }

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightsOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightsOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightBtn;

    /**
     * Confirms the page is visible by checking total price element.
     *
     * @return true if FlightSelection page is loaded
     */
    @Override
    public boolean isAt() {
        waitMethod(confirmFlightBtn);
        return confirmFlightBtn.isDisplayed();
    }

    /**
     * Randomly selects one departure and one arrival flight option.
     */
    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0,departureFlightsOptions.size());
        this.departureFlightsOptions.get(random).click();
        this.arrivalFlightsOptions.get(random).click();
    }

    /**
     * Clicks the confirm flight button.
     */
    public void confirmFlight(){
        clickMethod(confirmFlightBtn);
    }


}
