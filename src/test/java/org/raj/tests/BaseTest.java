package org.raj.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {
    protected WebDriver driver;
    @BeforeTest
    public void setDriver(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
