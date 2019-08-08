package helperFunctions;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.Scenario;


public class CaptureScreenShot{
	ExtentHtmlReporter reporter=null;
	protected ExtentReports reports=null;
	protected ExtentTest testLog=null;
    
    public void startReport(Scenario sc)
    {
    	System.out.println(sc.getName());
    	String resultFile = getDestination()+"Report"+getDate()+".html";
		reporter=new ExtentHtmlReporter(new File(resultFile));
        reports=new ExtentReports();
        reports.attachReporter(reporter);


        testLog=reports.createTest("test case name");
        testLog.log(Status.INFO, "Testin info");
        
    }
	//Creating a method getScreenshot and passing two parameters 
	//driver and screenshotName
	public String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		//below line is just to append the date format with the screenshot name to avoid duplicate names		
		String dateName = getDate();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = getDestination()+screenshotName+dateName+".png";
		
		System.out.println(destination);
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		//Attach test case details and add screenshot on failure
        
        testLog.log(Status.FAIL, "This test is failed");
        testLog.addScreenCaptureFromPath(destination);
        
		return destination;
	}
	private String getDate() {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		return dateName;
	}
	private String getDestination()
	{
		return System.getProperty("user.dir") + "/FailedTestsScreenshots/";
	}
	public void closeReport()
	{
		reports.flush();
	}
}