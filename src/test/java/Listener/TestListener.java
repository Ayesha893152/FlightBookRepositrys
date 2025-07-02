package Listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import util.Constants;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
       TakesScreenshot driver=(TakesScreenshot) result.getTestContext().getAttribute(Constants.driver);
       String screenshort=driver.getScreenshotAs(OutputType.BASE64);
       String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
       String htmlimage=String.format(htmlImageFormat,screenshort);
        Reporter.log(htmlimage);
    }
}
