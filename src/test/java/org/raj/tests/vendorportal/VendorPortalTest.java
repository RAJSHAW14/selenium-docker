package org.raj.tests.vendorportal;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.raj.pages.vendorportal.DashboardPage;
import org.raj.pages.vendorportal.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeTest
    public void setDriver(){
        WebDriverManager.edgedriver().setup();
        this.driver = new EdgeDriver();
        driver.manage().window().maximize();
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login("sam","sam");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),"$40,000");
        Assert.assertEquals(dashboardPage.getAnnualEarning(),"$215,000");
        Assert.assertEquals(dashboardPage.getProfitMargin(),"50%");
        Assert.assertEquals(dashboardPage.getAvailableMargin(),"18");
        dashboardPage.searchOrderHistoryBy("adams");
        Assert.assertEquals(dashboardPage.getSearchResultCount(), 8);
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logout(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
