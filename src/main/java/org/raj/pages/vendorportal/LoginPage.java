package org.raj.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.raj.pages.AbstractPage;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "username")
    private WebElement userNameTxt;

    @FindBy(id = "password")
    private WebElement passwordTxt;

    @FindBy(id = "login")
    private WebElement loginBtn;
    @Override
    public boolean isAt() {
        waitMethod(userNameTxt);
        return userNameTxt.isDisplayed();
    }

    public void goTo(String url){
        driver.get(url);
    }

    public void login(String username, String password){
        inputTextMethod(userNameTxt, username);
        inputTextMethod(passwordTxt, password);
        clickMethod(loginBtn);
    }
}
