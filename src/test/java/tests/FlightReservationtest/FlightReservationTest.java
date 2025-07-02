package tests.FlightReservationtest;

import FlightReservation.*;
import VendorPortal.model.flightreservationtestdata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.Abstarcttest;
import util.Config;
import util.Constants;
import util.JsonUtil;

public class FlightReservationTest extends Abstarcttest {
    private static final Logger log = LoggerFactory.getLogger(FlightReservationTest.class);
    private flightreservationtestdata testdata;

    @Parameters("testDataPath")
    @BeforeTest
    public void Setparameter(String testDataPath)
    {
        this.testdata= JsonUtil.getTestdata(testDataPath,flightreservationtestdata.class);
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enteruserdetails(testdata.firstname(),testdata.lastname());
        registrationPage.enterusercredentials(testdata.email(),testdata.password());
        registrationPage.enteraddress(testdata.street(),testdata.city(),testdata.zip());
        registrationPage.register();

    }
    @Test(dependsOnMethods="userRegistrationTest")
    public void registrationconfirmationTest(){
        RegistrationConfirmation registrationConfirmation=new RegistrationConfirmation(driver);
        Assert.assertTrue(registrationConfirmation.isAt());
        Assert.assertEquals(registrationConfirmation.getfirstname(),testdata.firstname());
        registrationConfirmation.GoToFlightSearch();
    }
    @Test(dependsOnMethods="registrationconfirmationTest")
    public void FlighsearchTest(){
        FlightSearchPage flightSearchPage=new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        log.info("noofpassengers");
        flightSearchPage.Selectpassenger(testdata.passengersCount());
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
        Assert.assertEquals(flightConfirmationpage.getBookingConfirmationId(), "Total Price");
        Assert.assertEquals(testdata.expectedPrice(), flightConfirmationpage.getTotalPrice());

    }



}
