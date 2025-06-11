import FlightReservation.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest {
    private static final Logger log = LoggerFactory.getLogger(FlightReservationTest.class);
    private WebDriver driver;
    private String noofpassenger;
    private String expectedprice;

    @Parameters({"noofpassengers","expectedprice"})
    @BeforeTest
    public void setDriver(String noofpassengers,String expectedprice){
        this.noofpassenger =noofpassengers;
        this.expectedprice=expectedprice;
        //this is driver setup
        WebDriverManager.chromedriver().setup();
        this.driver=new ChromeDriver();

    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enteruserdetails("selenium","docker");
        registrationPage.enterusercredentials("selenium@docker.com","docker");
        registrationPage.enteraddress("123 non main street","atlanata","30001");
        registrationPage.register();

    }
    @Test(dependsOnMethods="userRegistrationTest")
    public void registrationconfirmationTest(){
        RegistrationConfirmation registrationConfirmation=new RegistrationConfirmation(driver);
        Assert.assertTrue(registrationConfirmation.isAt());
        registrationConfirmation.GoToFlightSearch();
    }
    @Test(dependsOnMethods="registrationconfirmationTest")
    public void FlighsearchTest(){
        FlightSearchPage flightSearchPage=new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        log.info("noofpassengers");
        flightSearchPage.Selectpassenger(noofpassenger);
        flightSearchPage.SearchFlights();
    }
    @Test(dependsOnMethods="FlighsearchTest")
    public void flightsSelectionTest(){
        FlightSelectionPage flightSelectionPage=new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmflight();

    }
    @Test(dependsOnMethods="flightsSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightConfirmationpage flightConfirmationpage=new FlightConfirmationpage(driver);
        Assert.assertTrue(flightConfirmationpage.isAt());
        Assert.assertEquals("Total Price", flightConfirmationpage.getBookingConfirmationId());
        Assert.assertEquals(expectedprice, flightConfirmationpage.getTotalPrice());

    }
    @AfterTest
    public void quitdriver(){

        this.driver.quit();
    }



}
