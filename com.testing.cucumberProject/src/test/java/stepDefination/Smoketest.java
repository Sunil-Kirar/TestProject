package stepDefination;

import helperFunctions.Browser;
import helperFunctions.CaptureScreenShot;
import helperFunctions.ExcelReader;
import helperFunctions.GetProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Smoketest extends CaptureScreenShot{
	WebDriver driver=null;
	CaptureScreenShot obj=new CaptureScreenShot();
	ExtentTest testLog=null;
	int rownumber=0;
	List<HashMap<String,String>> excelData=new ArrayList<HashMap<String, String>>();
	@Given("^Open the browser and navigate to home page$")
	public void Open_the_browser_and_navigate_to_home_page () throws Throwable {
		this.driver=Browser.launchBrowser("chrome");
		Scenario sc=null;	
		obj.startReport(sc);
			
		
	}

	
	@When("^user click on New Customer$")
	public void user_click_on_New_Customer() throws Throwable {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	}

	@Then("^user insert the values with data from test data sheet row \"([^\"]*)\"$")
	public void user_insert_the_values_with_data_from_test_data_sheet_row(String testCase) throws Throwable {
		excelData=ExcelReader.readExceldata(GetProperties.getProperty("filepath"),"Test");
		rownumber=Integer.parseInt(testCase)-1;
		driver.findElement(By.name("name")).sendKeys(excelData.get(rownumber).get("name"));
		driver.findElement(By.name("rad1")).click();
		//		driver.findElement(By.name("dob")).sendKeys(excelData.get(rownumber).get("dob"));
		driver.findElement(By.name("dob")).sendKeys("1232568952355665");
		driver.findElement(By.name("addr")).sendKeys(excelData.get(rownumber).get("address"));
		driver.findElement(By.name("city")).sendKeys(excelData.get(rownumber).get("city"));
		driver.findElement(By.name("state")).sendKeys(excelData.get(rownumber).get("state"));
		driver.findElement(By.name("pinno")).sendKeys(excelData.get(rownumber).get("pinno"));
		driver.findElement(By.name("telephoneno")).sendKeys(excelData.get(rownumber).get("telephoneno"));
		driver.findElement(By.name("emailid")).sendKeys(excelData.get(rownumber).get("emailid"));
		driver.findElement(By.name("password")).sendKeys(excelData.get(rownumber).get("password"));
		Thread.sleep(2000);


	}
	@Then("^user click on submit button$")
	public void user_click_on_submit_button() throws Throwable 
	{
		Actions actions=new Actions(driver);
		actions.doubleClick(driver.findElement(By.xpath("//label[contains(@id,'message') and text()='Date Field must not be blank']"))).perform();
		System.out.println(driver.findElements(By.xpath("//label[contains(@id,'message')]")).size());

		if(driver.findElements(By.xpath("//label[contains(@id,'message') and text()='Date Field must not be blank']")).size()==0)
		{
			System.out.println("Data entered Successfully");
		}
		else
		{
			obj.getScreenshot(driver, "Test");
			System.out.println("Invalid data");
			Assert.assertEquals(0, driver.findElements(By.xpath("//label[contains(@id,'message') and text()='Date Field must not be blank']")).size());
		}
		//		driver.findElement(By.name("sub")).click();

	}
	@After(order=0)
	public void close_the_application()
	{
		driver.close();
		obj.closeReport();
	}
}
