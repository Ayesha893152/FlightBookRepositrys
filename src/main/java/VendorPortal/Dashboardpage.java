package VendorPortal;

import FlightReservation.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Dashboardpage extends AbstractPage {
    @FindBy(id="monthly-earning")
    private WebElement monthlyearningElement;

    @FindBy(id="annual-earning")
    private WebElement annualearningElement;

    @FindBy(id="profit-margin")
    private WebElement profitmarginElement;

    @FindBy(id="available-inventory")
    private  WebElement availableInventoryElement;

    @FindBy(css="#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id="dataTable_info")
    private WebElement searchresultCount;

    @FindBy(css="img.img-profile")
    private WebElement userprofileElement;

    //prefer id/name/css
    @FindBy(linkText = "Logout")
    private WebElement logoutbutton;

    @FindBy(css="#logoutModal  a")
    private WebElement logoutprompt;

    public Dashboardpage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyearningElement));
        return this.monthlyearningElement.isDisplayed();
    }
    public String getMonthlyearning(){
        return this.monthlyearningElement.getText();
    }
    public String getAnnualearning()
    {
        return this.annualearningElement.getText();
    }
    public String getProfitMargin(){
        return this.profitmarginElement.getText();
    }
    public String getAvailableinventory(){
        return this.availableInventoryElement.getText();
    }
    public String SearchorderHistoryBy(String keyword){
        this.searchInput.sendKeys(keyword);
        return keyword;
    }

    public int GetSearchResultsCount(){
        String resulttext=this.searchresultCount.getText();
        String [] arr=resulttext.split(" ");
        int count=Integer.parseInt(arr[5]);
        log.info("Results count: {}",count);
        return count ;
    }
    public  void logoutportal(){
        this.userprofileElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutbutton));
        this.logoutbutton.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutprompt));
        this.logoutprompt.click();
    }
}
