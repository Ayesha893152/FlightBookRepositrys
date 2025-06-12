import FlightReservation.AbstractPage;
import VendorPortal.Dashboardpage;
import VendorPortal.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Vendorportaltest{

    private WebDriver driver;

    @BeforeTest
    public void setDriver(){
        //this is driver setup
        WebDriverManager.chromedriver().setup();
        this.driver=new ChromeDriver();

    }
    @Test
    public void loginTest(){
        LoginPage loginpage=new LoginPage(driver);
        loginpage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginpage.isAt());
        loginpage.login("sam","sam");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardtest(){
        Dashboardpage dashboardpage=new Dashboardpage(driver);
        Assert.assertTrue(dashboardpage.isAt());

        // finance metric
        Assert.assertEquals(dashboardpage.getMonthlyearning(),"$40,000");
        Assert.assertEquals(dashboardpage.getAnnualearning(),"$215,000");
        Assert.assertEquals(dashboardpage.getProfitMargin(),"50%");
        Assert.assertEquals(dashboardpage.getAvailableinventory(),"18");

        //order history
        dashboardpage.SearchorderHistoryBy("adams");
        Assert.assertEquals(dashboardpage.GetSearchResultsCount(),8);
        //
        dashboardpage.logoutportal();

    }
    @AfterTest
    public void quitdriver(){
        driver.quit();
    }

}
