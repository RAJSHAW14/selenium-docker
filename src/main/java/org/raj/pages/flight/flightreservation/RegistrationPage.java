package org.raj.pages.flight.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.raj.pages.AbstractPage;

public class RegistrationPage extends AbstractPage {

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "street")
    private WebElement streetInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(id = "inputState")
    private WebElement stateDrpDwn;

    @FindBy(name = "zip")
    private WebElement zipInput;

    @FindBy(id = "register-btn")
    private WebElement registerBtn;

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        return firstNameInput.isDisplayed();
    }

    public void goTo(String url){
        driver.get(url);
    }

    public void enterUserDetails(String firstName, String lastName){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
    }

    public void enterUserCred(String email, String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }

    public void enterAddress(String street, String city, String zip){
        streetInput.sendKeys(street);
        cityInput.sendKeys(city);
        zipInput.sendKeys(zip);
    }

    public void register(){
        registerBtn.click();
    }
}
