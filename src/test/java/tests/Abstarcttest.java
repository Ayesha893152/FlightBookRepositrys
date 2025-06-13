package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public abstract class Abstarcttest {
    public static final Logger log = LoggerFactory.getLogger(Abstarcttest.class);
    protected WebDriver driver;
    @BeforeTest
    public void setDriver(){
        //this is driver setup
        WebDriverManager.chromedriver().setup();
        this.driver=new ChromeDriver();

    }
    @AfterTest
    public void quitdriver(){
        driver.quit();
    }
}
