package org.raj.tests.vendorportal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.raj.pages.vendorportal.DashboardPage;
import org.raj.pages.vendorportal.LoginPage;
import org.raj.tests.BaseTest;
import org.raj.tests.vendorportal.dataprovider.VendorDataProviders;
import org.testng.Assert;
import org.testng.annotations.*;

public class VendorPortalTest extends BaseTest{
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private String username;
    private String password;
    private String monthlyEarning;
    private String annualEarning;
    private String profitMargin;
    private String availableMargin;
    private String searchKeyword;
    private String searchResultCount;
    @BeforeMethod
    public void setPageObjects(){
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test(dataProvider = "googleSheetJsonData", dataProviderClass = VendorDataProviders.class)
    public void dashboardTest(String testJsonString){
        ObjectMapper mapper = new ObjectMapper();
        try {
            //private VendorPortalTestData testData;
            JsonNode testData = mapper.readTree(testJsonString);
            this.username = testData.has("username") ? testData.get("username").asText() : "N/A";
            this.password = testData.has("password") ? testData.get("password").asText() : "N/A";
            this.monthlyEarning = testData.has("monthlyEarning") ? testData.get("monthlyEarning").asText() : "N/A";
            this.annualEarning = testData.has("annualEarning") ? testData.get("annualEarning").asText() : "N/A";
            this.profitMargin = testData.has("profitMargin") ? testData.get("profitMargin").asText() : "N/A";
            this.availableMargin = testData.has("availableMargin") ? testData.get("availableMargin").asText() : "N/A";
            this.searchKeyword = testData.has("searchKeyword") ? testData.get("searchKeyword").asText() : "N/A";
            this.searchResultCount = testData.has("searchResultCount") ? testData.get("searchResultCount").asText() : "N/A";

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(username,password);
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),monthlyEarning);
        Assert.assertEquals(dashboardPage.getAnnualEarning(),annualEarning);
        Assert.assertEquals(dashboardPage.getProfitMargin(),profitMargin);
        Assert.assertEquals(dashboardPage.getAvailableMargin(),availableMargin);
        dashboardPage.searchOrderHistoryBy(searchKeyword);
        Assert.assertEquals(dashboardPage.getSearchResultCount(), Integer.parseInt(searchResultCount));
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
