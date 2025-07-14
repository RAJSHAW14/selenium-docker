package org.raj.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.raj.pages.AbstractPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);
    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningLbl;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningLbl;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginLbl;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryLbl;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchInput;

    @FindBy(id = "userDropdown")
    private WebElement userProfileDrpDwn;

    @FindBy(id = "dataTable_info")
    private WebElement datatabletxt;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(xpath = "//div[@class='modal-footer']/a")
    private WebElement modalLogoutBtn;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        waitMethod(monthlyEarningLbl);
        return monthlyEarningLbl.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarningLbl.getText();
    }

    public String getAnnualEarning(){
        return this.annualEarningLbl.getText();
    }

    public String getProfitMargin(){
        return this.profitMarginLbl.getText();
    }

    public String getAvailableMargin(){
        return this.availableInventoryLbl.getText();
    }

    public void searchOrderHistoryBy(String keyword){
        this.searchInput.sendKeys(keyword);
    }

    public int getSearchResultCount(){
        String resultTxt = getTextMethod(datatabletxt);
        String[] arr = resultTxt.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Result Count : {}",count);
        return count;
    }

    public void logout(){
        clickMethod(userProfileDrpDwn);
        clickMethod(logoutLink);
        clickMethod(modalLogoutBtn);
    }
}
