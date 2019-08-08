package helperFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

	static WebDriver driver=null;
	public static WebDriver launchBrowser(String browserName)
	{
		
		if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			driver.get(GetProperties.getProperty("url"));
		}
		
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
			driver=new ChromeDriver();
			driver.get(GetProperties.getProperty("url"));
		}
		return driver;
	}
	
}
