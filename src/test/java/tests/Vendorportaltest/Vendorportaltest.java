package tests.Vendorportaltest;

import VendorPortal.Dashboardpage;
import VendorPortal.LoginPage;
import VendorPortal.model.VendorPortaltestdata;
import ch.qos.logback.classic.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.Abstarcttest;
import util.JsonUtil;

public class Vendorportaltest extends Abstarcttest {


    private LoginPage loginPage;
    private Dashboardpage dashboardpage;
    private VendorPortaltestdata testdata;

    @BeforeTest
    @Parameters("testDataPath")
    public void setpageobjects(String testDataPath){
        //this is driver setup
        this.loginPage= new LoginPage(driver);
        this.dashboardpage=new Dashboardpage(driver);
        this.testdata = JsonUtil.getTestdata(testDataPath,VendorPortaltestdata.class);

    }
    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        log.info(testdata.username());
        loginPage.login(testdata.username(),testdata.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardtest(){
        Assert.assertTrue(dashboardpage.isAt());

        // finance metric
        Assert.assertEquals(dashboardpage.getMonthlyearning(),testdata.monthlyearning());
        Assert.assertEquals(dashboardpage.getAnnualearning(),testdata.Annualearning());
        Assert.assertEquals(dashboardpage.getProfitMargin(),testdata.Profitmargin());
        Assert.assertEquals(dashboardpage.getAvailableinventory(),testdata.Availableinventory());

        //order history
        dashboardpage.SearchorderHistoryBy(testdata.SearchResult());
        Assert.assertEquals(dashboardpage.GetSearchResultsCount(),testdata.Searchresultcount());

    }
    @Test(dependsOnMethods = "dashboardtest")
    public void logouttest()
    {
        dashboardpage.logoutportal();
        Assert.assertTrue(loginPage.isAt());
    }


}
