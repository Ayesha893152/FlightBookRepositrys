package FlightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegistrationConfirmation extends AbstractPage {
    @FindBy(id="go-to-flights-search")
    private WebElement goToFlightSearchButton;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstnameelement;

    public RegistrationConfirmation(WebDriver driver){

        super(driver);
    }
    public  void GoToFlightSearch()
    {

        this.goToFlightSearchButton.click();
    }
    public String getfirstname(){
        return this.firstnameelement.getText();
    }
    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
        return this.goToFlightSearchButton.isDisplayed();
    }

}
