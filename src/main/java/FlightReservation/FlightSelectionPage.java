package FlightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage{
    @FindBy(css = "input[type='radio'][name='departure-flight']")
    private List<WebElement>departureflightoptions;

    @FindBy(css = "input[type='radio'][name='arrival-flight']")
    private List<WebElement>arrivalflightoptions;

    @FindBy(id = "confirm-flights")
    private WebElement Confirmflightbutton;

    public FlightSelectionPage(WebDriver driver)
    {

        super(driver);
    }
    @Override
    public boolean isAt(){
        try {
           this.wait.until(ExpectedConditions.visibilityOfAllElements(departureflightoptions));
            return !departureflightoptions.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    public void selectFlights(){
        wait.until(ExpectedConditions.visibilityOfAllElements(departureflightoptions));
        wait.until(ExpectedConditions.visibilityOfAllElements(departureflightoptions));

        if (departureflightoptions.isEmpty() || arrivalflightoptions.isEmpty()) {
            throw new RuntimeException("Flight options are not available.");
        }

        int departureIndex = ThreadLocalRandom.current().nextInt(departureflightoptions.size());
        int arrivalIndex = ThreadLocalRandom.current().nextInt(arrivalflightoptions.size());

        WebElement departureOption = departureflightoptions.get(departureIndex);
        WebElement arrivalOption = arrivalflightoptions.get(arrivalIndex);

        // Ensure they are interactable
        wait.until(ExpectedConditions.elementToBeClickable(departureOption));
        wait.until(ExpectedConditions.elementToBeClickable(arrivalOption));

        // Now click
        departureOption.click();
        arrivalOption.click();
    }
    public void confirmflight(){

        wait.until(ExpectedConditions.elementToBeClickable(Confirmflightbutton));
        Confirmflightbutton.click();
    }
}
