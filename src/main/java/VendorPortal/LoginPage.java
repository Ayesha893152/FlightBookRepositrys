package VendorPortal;

import FlightReservation.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {
    @FindBy(id="username")
    private WebElement usernameinput;

    @FindBy(id="password")
    private WebElement passwordinput;

    @FindBy(id="login")
    private  WebElement loginbutton;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }


    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.loginbutton));
        return this.loginbutton.isDisplayed();
    }
    public void goTo(String url)
    {
        this.driver.get(url);
    }
    public void login(String username,String password)
    {
        this.usernameinput.sendKeys(username);
        this.passwordinput.sendKeys(password);
        this.loginbutton.click();
    }




}
