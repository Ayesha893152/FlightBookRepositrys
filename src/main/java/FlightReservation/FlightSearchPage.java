package FlightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.ILoggerFactory;

public class FlightSearchPage extends AbstractPage{

    @FindBy(id="passengers")
    private WebElement Passengerselect;

    @FindBy (css="#search-flights")
    private WebElement searchflightbutton;

    public FlightSearchPage(WebDriver driver){
        super(driver);
    }
    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.Passengerselect));
        return this.Passengerselect.isDisplayed();
    }
    public void Selectpassenger(String noOfpassengers)
    {
        Select Passengers=new Select(this.Passengerselect);
        Passengers.selectByValue(noOfpassengers);
    }
    public void SearchFlights(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.searchflightbutton));
        this.searchflightbutton.click();
    }

}
