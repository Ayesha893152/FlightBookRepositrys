package FlightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage {
    protected  final WebDriver driver;
    protected  final WebDriverWait wait;
    protected static final Logger log= LoggerFactory.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(100));
        driver.manage().window().maximize();
        PageFactory.initElements(driver,this);

    }
    public abstract boolean isAt();



}
