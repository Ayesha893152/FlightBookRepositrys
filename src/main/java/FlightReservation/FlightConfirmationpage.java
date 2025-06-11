package FlightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class FlightConfirmationpage extends AbstractPage{

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(1)")
    private WebElement FlightConfirmationElement;

    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(2) .col:nth-child(2)")
    private WebElement totalpriceElement;


    public FlightConfirmationpage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.FlightConfirmationElement));
        log.error("this is something");
        return this.FlightConfirmationElement.isDisplayed();
    }
    public String getBookingConfirmationId() {
        return this.FlightConfirmationElement.getText();
    }

    public String getTotalPrice() {
        return this.totalpriceElement.getText();
    }
}
