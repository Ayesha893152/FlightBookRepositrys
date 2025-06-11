package FlightReservation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.slf4j.ILoggerFactory.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RegistrationPage extends AbstractPage {
    private static final Logger log = LoggerFactory.getLogger(RegistrationPage.class);


    @FindBy(id = "firstName")
    private WebElement firstnameinput;

    @FindBy(id = "lastName")
    private WebElement lastnameinput;

    @FindBy(id = "email")
    private WebElement emailinput;

    @FindBy(id = "password")
    private WebElement passwordinput;

    @FindBy(name = "street")
    private WebElement streerinput;

    @FindBy(name = "city")
    private WebElement cityinput;

    @FindBy(name = "zip")
    private WebElement Zipinput;

    @FindBy(id = "register-btn")
    private WebElement RegistrationButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void goTo(String url)
    {
        this.driver.get(url);
    }
    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstnameinput));
        return this.firstnameinput.isDisplayed();
    }
    public void enteruserdetails(String firstname, String lastname) {
        this.firstnameinput.sendKeys(firstname);
        this.lastnameinput.sendKeys(lastname);
    }
    public void enterusercredentials(String email,String password) {
        this.emailinput.sendKeys(email);
        this.passwordinput.sendKeys(password);
    }
    public void enteraddress(String street, String city, String zip) {
        this.streerinput.sendKeys(street);
        this.cityinput.sendKeys(city);
        this.Zipinput.sendKeys(zip);
    }
    public void register() {

        this.RegistrationButton.click();
    }

}