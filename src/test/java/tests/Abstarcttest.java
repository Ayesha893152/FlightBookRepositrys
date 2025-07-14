package tests;

import Listener.TestListener;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import util.Config;
import util.Constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public abstract class Abstarcttest {
    public static final Logger log = LoggerFactory.getLogger(Abstarcttest.class);
    protected WebDriver driver;
    @BeforeSuite
    public void SetupConfig(){
        Config.initialize();

    }

    @BeforeTest

    public void setDriver(ITestContext ctx) throws MalformedURLException {
      this.driver= Boolean.parseBoolean( Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver(): getLocalDriver();
      ctx.setAttribute(Constants.driver,this.driver);
    }
    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubhost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubhost);
        return new RemoteWebDriver(new URL(url), capabilities);
    }
    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }


    @AfterTest
    public void quitdriver(){
        driver.quit();
    }

    @AfterMethod
    public void sleep(){
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }
}
