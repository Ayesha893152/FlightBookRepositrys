package tests.Vendorportaltest;

import VendorPortal.Dashboardpage;
import VendorPortal.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.Abstarcttest;

public class Vendorportaltest extends Abstarcttest {


    private LoginPage loginPage;
    private Dashboardpage dashboardpage;

    @BeforeTest
    public void setpageobjects(){
        //this is driver setup
        this.loginPage= new LoginPage(driver);
        this.dashboardpage=new Dashboardpage(driver);

    }
    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login("sam","sam");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardtest(){
        Assert.assertTrue(dashboardpage.isAt());

        // finance metric
        Assert.assertEquals(dashboardpage.getMonthlyearning(),"$40,000");
        Assert.assertEquals(dashboardpage.getAnnualearning(),"$215,000");
        Assert.assertEquals(dashboardpage.getProfitMargin(),"50%");
        Assert.assertEquals(dashboardpage.getAvailableinventory(),"18");

        //order history
        dashboardpage.SearchorderHistoryBy("adams");
        Assert.assertEquals(dashboardpage.GetSearchResultsCount(),8);

    }
    @Test(dependsOnMethods = "dashboardtest")
    public void logouttest()
    {
        dashboardpage.logoutportal();
        Assert.assertTrue(loginPage.isAt());
    }


}
