package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;


public abstract class Abstarcttest {
    public static final Logger log = LoggerFactory.getLogger(Abstarcttest.class);
    protected WebDriver driver;
    @BeforeTest
    public void setDriver() throws MalformedURLException {
        log.info("Running with Selenium Grid: {}", Boolean.getBoolean("selenium.grid.enabled"));
        if (Boolean.getBoolean("selenium.grid.enabled")) {
            this.driver = getRemoteDriver();
        } else {
            this.driver = getLocalDriver();
        }
    }
    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities;
        if (System.getProperty("browser").equalsIgnoreCase("chrome")){
            capabilities=new ChromeOptions();
        }else {
            capabilities=new FirefoxOptions();
        }
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
    }
    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }


    @AfterTest
    public void quitdriver(){
        driver.quit();
    }
}
